package pl.myproject.servlet;

import java.io.IOException;

import java.sql.Connection;
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
import pl.myproject.util.ConnectionProvider;

/**
 * Servlet implementation class RentedServlet
 */
@WebServlet("/RentedServlet")
public class RentedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RentedServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		Rented rented = null;
		Car car = null;
		boolean result = false;

		try (Connection conn = ConnectionProvider.getConnection();) {

			checkOption(request, response, dao, car, rented, conn, result);

		} catch (SQLException | NumberFormatException | ParseException e) {

			e.printStackTrace();
		}

	}

	private void checkOption(HttpServletRequest request, HttpServletResponse response, RentedDAO dao, Car car,
			Rented rented, Connection conn, boolean result)
			throws IOException, ServletException, ParseException, NumberFormatException, SQLException {
		if (request.getParameter("rent") != null) {
			String from = request.getParameter("from");
			String till = request.getParameter("till");
			String carID = request.getParameter("cars");
			
			int id = Integer.parseInt(carID);
			car = (Car) dao.readCarTable(conn, id);
			rented = getData(request, dao, car);
			
			String pricePerHour = car.getRentPerHour();
			double perHour = Double.valueOf(pricePerHour);
			result = dao.create(rented, conn, perHour, from, till);

		}
		else if(request.getParameter("cancel") != null){
			String idRented = request.getParameter("PayedID");
			int id = Integer.parseInt(idRented);
			result = dao.delete(id, conn);
		}
		else if(request.getParameter("Payed") != null){
			String id = request.getParameter("PayedID");
			String rowId = request.getParameter("RowID");
			System.out.println(rowId);
			request.setAttribute("success", "payed");
			
			
			
			request.getRequestDispatcher("rented.jsp").forward(request, response);
		}
		if (rented != null || result == true) {
			request.setAttribute("rentedlist", dao.read(conn));
			request.getRequestDispatcher("rented.jsp").forward(request, response);
		}
	}

	private Rented getData(HttpServletRequest request, RentedDAO dao, Car car) throws ParseException {
		String employee = request.getParameter("employee");
		String client = request.getParameter("client");
		String model = car.getModel();
		String brand  = car.getBrand();
		String engine = car.getEngineSize();
		String cars = model + " " + brand + " " +engine;
		String fromDate = request.getParameter("from");
		String tillDate = request.getParameter("till");
		String days = dao.daysRented(fromDate, tillDate);
		String pricePerHour = car.getRentPerHour();
		double perHour = Double.valueOf(pricePerHour);
		String price = dao.price(days, perHour);
		Rented rented = new Rented(employee, client, cars, fromDate, tillDate, days, price);
		return rented;
	}

}
