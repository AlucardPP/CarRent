package pl.myproject.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.CarDAO;
import pl.myproject.model.Car;

/**
 * Servlet implementation class UpdateCarServlet
 */
@WebServlet("/UpdateCarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		if (request.getParameter("update") != null) {
			String idCar = request.getParameter("IDcar");
			int id = Integer.parseInt(idCar);
			car = getDataToUpdate(request);
			result = dao.update(car, id);
		}
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
}
