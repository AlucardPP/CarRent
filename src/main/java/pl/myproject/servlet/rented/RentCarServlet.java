package pl.myproject.servlet.rented;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.RentedDAO;
import pl.myproject.model.Car;
import pl.myproject.model.Rented;

/**
 * Servlet implementation class RentCarServlet
 */
@WebServlet("/RentCar")
public class RentCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		RentedDAO dao = new RentedDAO();
		Rented rented = null;
		Car car = null;
		boolean result = false;
		try {
			checkOption(request, response, dao, car, rented, result);
		} catch (SQLException | NumberFormatException | ParseException e) {
			e.printStackTrace();
		}
	}

	private void checkOption(HttpServletRequest request, HttpServletResponse response, RentedDAO dao, Car car,
			Rented rented, boolean result)
			throws IOException, ServletException, ParseException, NumberFormatException, SQLException {
		if (request.getParameter("rent") != null) {
			String from = request.getParameter("from");
			String till = request.getParameter("till");
			String carID = request.getParameter("cars");
			int id = Integer.parseInt(carID);
			result = rentAndUpdateCar(request, id, car, dao, rented, result, from, till);

		}
		if (rented != null || result == true) {
			request.setAttribute("rentedlist", dao.read());
			request.getRequestDispatcher("WEB-INF/rented.jsp").forward(request, response);
		}
	}

	private boolean rentAndUpdateCar(HttpServletRequest request, int id, Car car, RentedDAO dao, Rented rented,
			boolean result, String from, String till) throws SQLException, ParseException {

		car = (Car) dao.readCarTable(id);
		rented = getData(request, dao, car);
		dao.updateCar(car, id, "no");
		String pricePerHour = car.getRentPerHour();
		double perHour = Double.valueOf(pricePerHour);
		result = dao.create(rented, perHour, from, till, id);
		return result;
	}

	private Rented getData(HttpServletRequest request, RentedDAO dao, Car car) throws ParseException {
		String employee = request.getParameter("employee");
		String client = request.getParameter("client");
		String model = car.getModel();
		String brand = car.getBrand();
		String engine = car.getEngineSize();
		String cars = model + " " + brand + " " + engine;
		String fromDate = request.getParameter("from");
		String tillDate = request.getParameter("till");
		String days = dao.daysRented(fromDate, tillDate);
		String pricePerHour = car.getRentPerHour();
		double perHour = Double.valueOf(pricePerHour);
		String price = dao.price(days, perHour);
		int paid = isPaid(request.getParameter("Payed"));
		int isRented = isPaid(request.getParameter("Rented"));
		String id = request.getParameter("cars");
		int carId = Integer.parseInt(id);

		Rented rented = new Rented(employee, client, cars, fromDate, tillDate, days, price, paid, isRented, carId);
		return rented;
	}

	private int isPaid(String Payed) {
		int paid = 1;
		if (Payed != null) {
			paid = 0;
		}
		return paid;
	}

}
