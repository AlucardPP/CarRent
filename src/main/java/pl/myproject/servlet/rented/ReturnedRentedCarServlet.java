package pl.myproject.servlet.rented;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.CarDAO;

import pl.myproject.dao.RentedDAO;
import pl.myproject.model.Car;
import pl.myproject.model.Rented;

/**
 * Servlet implementation class ReturnedRentedCarServlet
 */
@WebServlet("/ReturnedServlet")
public class ReturnedRentedCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		RentedDAO dao = new RentedDAO();
		CarDAO carDao = new CarDAO();
		Rented rented = null;
		Car car = null;
		boolean result = false;
		try {
			checkOption(request, response, dao, car, rented, carDao, result);
		} catch (SQLException | NumberFormatException | ParseException e) {
			e.printStackTrace();
		}

	}

	private void checkOption(HttpServletRequest request, HttpServletResponse response, RentedDAO dao, Car car,
			Rented rented, CarDAO carDao, boolean result)
			throws IOException, ServletException, ParseException, NumberFormatException, SQLException {
		if (request.getParameter("returned") != null) {
			String status = request.getParameter("returned");
			String idRented = request.getParameter("ReturnedID");
			int id = Integer.parseInt(idRented);
			String carId = request.getParameter("ReturnedCar_ID");
			int IdCar = Integer.parseInt(carId);
			dao.udateRented(rented, id, 0, 0, status);
			result = dao.updateCar(car, IdCar, "yes");

		}
		if (request.getParameter("destroyed") != null) {
			String status = request.getParameter("destroyed");
			String idRented = request.getParameter("ReturnedID");
			int id = Integer.parseInt(idRented);
			result = dao.udateRented(rented, id, 0, 0, status);
		}
		if (rented != null || result == true) {
			request.setAttribute("rentedlist", dao.read());
			request.setAttribute("carlist", carDao.read());
			request.getRequestDispatcher("WEB-INF/rented.jsp").forward(request, response);
		}
	}

}
