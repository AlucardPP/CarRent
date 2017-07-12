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

/**
 * Servlet implementation class DeleteCarServlet
 */
@WebServlet("/DeleteCarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DeleteCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		CarDAO dao = new CarDAO();
		Car car = null;
		boolean result = false;
		try {
			checkOptions(request, response, result, car, dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkOptions(HttpServletRequest request, HttpServletResponse response, boolean result, Car car,
			CarDAO dao) throws Exception {
		if (request.getParameter("delete") != null) {
			String idCar = request.getParameter("carID");
			int id = Integer.parseInt(idCar);
			result = dao.delete(id);
			
		}
		if (car != null || result) {
			request.setAttribute("carlist", dao.read());
			request.getRequestDispatcher("WEB-INF/car.jsp").forward(request, response);
		}
	}

}
