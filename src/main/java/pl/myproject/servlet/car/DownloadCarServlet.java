package pl.myproject.servlet.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.CarDAO;
import pl.myproject.model.Car;
import pl.myproject.util.FileOperations;

/**
 * Servlet implementation class DownloadCarServlet
 */
@WebServlet("/download")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DownloadCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/CarFiles";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		CarDAO dao = new CarDAO();
		Car car = null;
		boolean result = false;
		try {
			checkOption(request, response, result, car, dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkOption(HttpServletRequest request, HttpServletResponse response, boolean result, Car car,
			CarDAO dao) throws Exception {

		if (request.getParameter("download") != null) {
			String plate = request.getParameter("carPlate");
			FileOperations.downloadFile(response, SAVE_DIR, plate);

		}
		if (car != null || result) {
			request.setAttribute("carlist", dao.read());
			request.getRequestDispatcher("WEB-INF/car.jsp").forward(request, response);
		}
	}

}
