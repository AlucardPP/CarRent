package pl.myproject.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import pl.myproject.dao.CarDAO;
import pl.myproject.model.Car;
import pl.myproject.util.ConnectionProvider;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet("/CarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
				maxFileSize = 1024 * 1024 * 10,		 // 10MB
				maxRequestSize = 1024 * 1024 * 50) 	 // 50MB
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarServlet() {
		// g³upie wo³anie - nie ma po co i tak zawo³a konstruktor nadrzêdny
		// defaultowy ;)
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// porozbijaæ na mniejsze metody
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CarDAO dao = new CarDAO();
		Car car = null;
		boolean result = false;
		try (Connection conn = ConnectionProvider.getConnection();
				PreparedStatement prepstmt=null;
				InputStream inputStream = null;) {
			checkOption(request, response, result, car, dao, conn, prepstmt, getFile(request));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void checkOption(HttpServletRequest request, HttpServletResponse response, boolean result, Car car,
			CarDAO dao, Connection conn, PreparedStatement prepstmt, InputStream inputStream) throws SQLException, IOException, ServletException {
		if (request.getParameter("save") != null) {
			car = getData(request, inputStream);
			result = dao.create(car, conn, request);
		} else if (request.getParameter("delete") != null) {
			String idCar = request.getParameter("carID");
			int id = Integer.parseInt(idCar);
			result = dao.delete(id, conn);
		} else if (request.getParameter("update") != null) {
			String idCar = request.getParameter("IDcar");
			int id = Integer.parseInt(idCar);
			car = getDataToUpdate(request);
			result = dao.update(car, id, conn);
		}
		if (car != null || result) {
			request.setAttribute("carlist", dao.read(conn));
			request.getRequestDispatcher("car.jsp").forward(request, response);
		}
	}

	private Car getData(HttpServletRequest request, InputStream inputStream) throws IOException, ServletException {
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
		Part filePart = request.getPart("files");
		String file = filePart.getName();
		if(filePart != null){
			System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
			inputStream = filePart.getInputStream();
		}
		Car car = new Car(brand, model, plate, produced, firstRegistration, engineSize, value, rentPerHour, distance,
				available,file);
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
	private InputStream getFile(HttpServletRequest request) throws IOException, ServletException {
		InputStream inputStream = null;
		Part filePart = request.getPart("files");
		if (filePart != null){
			inputStream = filePart.getInputStream();
		}
		return inputStream;
	}

}
