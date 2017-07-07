package pl.myproject.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pl.myproject.dao.CustomerDAO;
import pl.myproject.model.Customer;
import pl.myproject.util.FileOperations;

/**
 * Servlet implementation class AddCustomerServlet
 */
@WebServlet("/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/CustomerFiles";

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
		if (request.getParameter("save") != null || ServletFileUpload.isMultipartContent(request)) {
			customer = getData(request);
			uploadFile(customer, request);
			result = dao.create(customer);
		}
		if (customer != null || result) {
			request.setAttribute("clientlist", dao.read());
			request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
		}
	}

	private Customer getData(HttpServletRequest request) throws ServletException, IOException {
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
		String files = FileOperations.listFiles(request).toString();
		Customer customer = new Customer(name, surname, born, idCardNumber, street, houseNumber, city, country, gender,
				telephone, files);
		return customer;
	}

	private void uploadFile(Customer customer, HttpServletRequest request) throws ServletException, IOException {
		String name = customer.getSurname();
		String born = customer.getBorn();
		String fileDir = name + "_" + born;
		FileOperations.saveFile(request, SAVE_DIR, fileDir);
	}

}
