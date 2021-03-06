package pl.myproject.servlet.rented;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.myproject.dao.CarDAO;
import pl.myproject.dao.CustomerDAO;
import pl.myproject.dao.EmployeeDAO;
import pl.myproject.dao.RentedDAO;
import pl.myproject.model.Car;
import pl.myproject.model.Rented;

/**
 * Servlet implementation class RentedServlet
 */
@WebServlet("/RentedServlet")

public class RentedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		RentedDAO dao = new RentedDAO();
		CarDAO carDao = new CarDAO();
		CustomerDAO clientDao = new CustomerDAO();
		EmployeeDAO employeeDao = new EmployeeDAO();
		try {
			if (session != null) {
				request.setAttribute("rentedlist", dao.read());
				request.setAttribute("carlist", carDao.read());
				request.setAttribute("clientlist", clientDao.read());
				request.setAttribute("employeelist", employeeDao.read());
				request.getRequestDispatcher("WEB-INF/rented.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

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
		if (request.getParameter("rented") != null) {
			String idRented = request.getParameter("RentedID");
			String status = request.getParameter("rented");
			int id = Integer.parseInt(idRented);
			int paid = 0;
			int isRented = isPaid(request.getParameter("rented"));
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
