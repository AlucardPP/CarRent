package pl.myproject.servlet;

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
 * Servlet implementation class CancelRentedCarServlet
 */
@WebServlet("/CancelRent")
public class CancelRentedCarServlet extends HttpServlet {
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
		if (request.getParameter("cancel") != null) {
			String idRented = request.getParameter("PayedID");
			int id = Integer.parseInt(idRented);
			String idcar = request.getParameter("Car_ID");
			int carid = Integer.parseInt(idcar);
			dao.updateCar(car, carid, "yes");
			result = dao.delete(id);
		}
		if (rented != null || result == true) {
			request.setAttribute("rentedlist", dao.read());
			request.getRequestDispatcher("WEB-INF/rented.jsp").forward(request, response);
		}
	}

}
