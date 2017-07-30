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
 * Servlet implementation class RepairedRentedCarServlet
 */
@WebServlet("/RepairedServlet")
public class RepairedRentedCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		if (request.getParameter("repaired") != null) {
			String idRented = request.getParameter("RentedID");
			int id = Integer.parseInt(idRented);
			String carId = request.getParameter("Car_ID");
			int IdCar = Integer.parseInt(carId);
			String status = request.getParameter("repaired");
			dao.udateRented(rented, id, 0, 0, status);
			result = dao.updateCar(car, IdCar, "yes");

		}
		if (request.getParameter("scrap") != null) {
			String idRented = request.getParameter("RentedID");
			int id = Integer.parseInt(idRented);
			String status = request.getParameter("scrap");
			String carId = request.getParameter("Car_ID");
			int IdCar = Integer.parseInt(carId);
			dao.udateRented(rented, id, 0, 0, status);
			result = carDao.delete(IdCar);
		}
		if (rented != null || result == true) {
			request.setAttribute("rentedlist", dao.read());
			request.setAttribute("carlist", carDao.read());
			request.getRequestDispatcher("WEB-INF/rented.jsp").forward(request, response);
		}
	}

}
