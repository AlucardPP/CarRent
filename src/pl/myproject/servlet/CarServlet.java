package pl.myproject.servlet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import java.sql.Connection;

import java.util.ArrayList;

import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pl.myproject.dao.CarDAO;
import pl.myproject.model.Car;
import pl.myproject.util.ConnectionProvider;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/CarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CarServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "D:/aaa";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarServlet() {

		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CarDAO dao = new CarDAO();
		Car car = null;
		boolean result = false;
		try (Connection conn = ConnectionProvider.getConnection();) {
			checkOption(request, response, result, car, dao, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkOption(HttpServletRequest request, HttpServletResponse response, boolean result, Car car,
			CarDAO dao, Connection conn) throws Exception {
		if (request.getParameter("save") != null || ServletFileUpload.isMultipartContent(request)) {
			car = getData(request);
			String saveDir = request.getParameter("plate");
			saveFile(request, saveDir);
			result = dao.create(car, conn);
		} else if (request.getParameter("delete") != null) {
			String idCar = request.getParameter("carID");
			int id = Integer.parseInt(idCar);
			result = dao.delete(id, conn);
		} else if (request.getParameter("update") != null) {
			String idCar = request.getParameter("IDcar");
			int id = Integer.parseInt(idCar);
			car = getDataToUpdate(request);
			result = dao.update(car, id, conn);
		} else if (request.getParameter("download") != null) {
			String plate = request.getParameter("carPlate");
			downloadFile(response, plate);

		}
		if (car != null || result) {
			request.setAttribute("carlist", dao.read(conn));
			request.getRequestDispatcher("car.jsp").forward(request, response);
		}
	}

	private Car getData(HttpServletRequest request) throws IOException, ServletException {
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String plate = request.getParameter("plate");
		String produced = request.getParameter("produced");
		String firstRegistration = request.getParameter("firstregistration");
		String engineSize = request.getParameter("engine");
		String value = request.getParameter("value");
		String rentPerHour = request.getParameter("rentperhour");
		String distance = request.getParameter("distance");
		String available = request.getParameter("available");
		String files = listFiles(request).toString();

		Car car = new Car(brand, model, plate, produced, firstRegistration, engineSize, value, rentPerHour, distance,
				available, files);
		return car;
	}

	private Car getDataToUpdate(HttpServletRequest request) {
		String brand = request.getParameter("upbrand");
		String model = request.getParameter("upmodel");
		String plate = request.getParameter("upplate");
		String produced = request.getParameter("upproduced");
		String firstRegistration = request.getParameter("upfirstregistration");
		String engineSize = request.getParameter("upengine");
		String value = request.getParameter("upvalue");
		String rentPerHour = request.getParameter("uprentperhour");
		String distance = request.getParameter("updistance");
		String available = request.getParameter("upavailable");
		Car car = new Car(brand, model, plate, produced, firstRegistration, engineSize, value, rentPerHour, distance,
				available);
		return car;
	}


	private void saveFile(HttpServletRequest request, String dir) throws IOException, ServletException {
		// String appPath = request.getServletContext().getRealPath("");
		String savePath = SAVE_DIR + File.separator + dir;// appPath +
															// File.separator +
															// SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		for (Part part : request.getParts()) {
			if (part.getContentType() != null) {
				String fileName = extractFileName(part);
				fileName = new File(fileName).getName();
				part.write(savePath + File.separator + fileName);
			}
		}

	}

	private void downloadFile(HttpServletResponse response, String dir) throws IOException, ServletException {
		String path = SAVE_DIR + File.separator + dir;
		File fileDir = new File(path);
		String[] files = fileDir.list();
		if (files != null && files.length > 0) {
			byte[] zip = zipFiles(fileDir, files);
			ServletOutputStream sos = response.getOutputStream();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment; filename=DATA.ZIP");
			sos.write(zip);
			sos.flush();
		}
	}

	private List<String> listFiles(HttpServletRequest request) throws IOException, ServletException {
		List<String> files = new ArrayList<>();
		for (Part part : request.getParts()) {
			if (part.getContentType() != null) {
				String fileName = extractFileName(part);
				files.add(fileName);

			}
		}
		return files;
	}

	private byte[] zipFiles(File directory, String[] files) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		byte[] bytes = new byte[2048];
		for (String fileName : files) {
			FileInputStream fis = new FileInputStream(directory.getPath() + File.separator + fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			zos.putNextEntry(new ZipEntry(fileName));
			int bytesRead;
			while ((bytesRead = bis.read(bytes)) != -1) {
				zos.write(bytes, 0, bytesRead);
			}
			zos.closeEntry();
			bis.close();
			fis.close();
		}
		zos.flush();
		baos.flush();
		zos.close();
		baos.close();
		return baos.toByteArray();
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}
