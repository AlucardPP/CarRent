package pl.myproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.CustomerDAO;
import pl.myproject.model.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Ÿle wy³¹cz to do zewnêtrznej metody i doGet i doPost niech j¹ wo³aj¹
		// ogólnie nie jest to dobre by dzia³a³o zarówno dla posta i geta - ot
		// chocia¿ny bezpieczeñstwo
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		CustomerDAO dao = new CustomerDAO();
		Customer customer = null;
		boolean result = false;

		if (request.getParameter("save") != null) {
			customer = getData(request);
			result = dao.create(customer);

		} else if (request.getParameter("update") != null) {
			String client = request.getParameter("idClient123");
			int idC = Integer.parseInt(client);
			customer = updateData(request);
			result = dao.update(customer, idC);

		} else if (request.getParameter("delete") != null) {
			String clientID = request.getParameter("clientID");
			int ID = Integer.parseInt(clientID);
			result = dao.delete(ID);

		}
		if (customer != null || result) {
			request.setAttribute("clientlist", dao.read());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	private Customer getData(HttpServletRequest request) {
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String born = request.getParameter("born");
		String idCardNumber = request.getParameter("idcardnumber");
		String street = request.getParameter("street");
		String houseNumber = request.getParameter("housenumber");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String gender = request.getParameter("gender");
		String telephone = request.getParameter("telephone");
		Customer customer = new Customer(name, surname, born, idCardNumber, street, houseNumber, city, country, gender,
				telephone);
		return customer;
	}

	private Customer updateData(HttpServletRequest request) {
		String updatename = request.getParameter("upname");
		String upsurname = request.getParameter("upsurname");
		String upborn = request.getParameter("upborn");
		String upidCardNumber = request.getParameter("upidcardnumber");
		String upstreet = request.getParameter("upstreet");
		String uphouseNumber = request.getParameter("uphousenumber");
		String upcity = request.getParameter("upcity");
		String upcountry = request.getParameter("upcountry");
		String upgender = request.getParameter("upgender");
		String uptelephone = request.getParameter("uptelephone");
		Customer customer = new Customer(updatename, upsurname, upborn, upidCardNumber, upstreet, uphouseNumber, upcity,
				upcountry, upgender, uptelephone);
		return customer;
	}

}
