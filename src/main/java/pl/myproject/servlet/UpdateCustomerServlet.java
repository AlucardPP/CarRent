package pl.myproject.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.CustomerDAO;
import pl.myproject.model.Customer;

/**
 * Servlet implementation class UpdateCustomerServlet
 */
@WebServlet("/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		CustomerDAO dao = new CustomerDAO();
		Customer customer = null;
		boolean result = false;
		try {
			checkingOptions(customer, dao, result, request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void checkingOptions(Customer customer, CustomerDAO dao, boolean result, HttpServletRequest request,
			HttpServletResponse response) throws SQLException, ServletException, IOException {
		if (request.getParameter("update") != null) {
			String client = request.getParameter("idClient123");
			int idC = Integer.parseInt(client);
			customer = updateData(request);
			result = dao.update(customer, idC);

		}

		if (customer != null || result) {
			request.setAttribute("clientlist", dao.read());
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
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
