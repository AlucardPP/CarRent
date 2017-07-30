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
 * Servlet implementation class PayForRentedCarServlet
 */
@WebServlet("/Pay")
public class PayForRentedCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		if (request.getParameter("Payed") != null) {
			String idRented = request.getParameter("PayedID");
			String status = request.getParameter("Payed");
			int id = Integer.parseInt(idRented);
			int paid = isPaid(request.getParameter("Payed"));
			int isRented = 1;
			result = dao.udateRented(rented, id, paid, isRented, status);

		}
		if (rented != null || result == true) {
			request.setAttribute("rentedlist", dao.read());
			request.setAttribute("carlist", carDao.read());
			request.getRequestDispatcher("WEB-INF/rented.jsp").forward(request, response);
		}
	}

	private int isPaid(String Payed) {
		int paid = 1;
		if (Payed != null) {
			paid = 0;
		}
		return paid;
	}

}
