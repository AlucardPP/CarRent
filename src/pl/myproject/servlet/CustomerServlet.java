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
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/CustomerFiles";

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

		} else if (request.getParameter("update") != null) {
			String client = request.getParameter("idClient123");
			int idC = Integer.parseInt(client);
			customer = updateData(request);
			result = dao.update(customer, idC);

		} else if (request.getParameter("delete") != null) {
			String clientID = request.getParameter("clientID");
			int ID = Integer.parseInt(clientID);
			result = dao.delete(ID);

		} else if (request.getParameter("download") != null) {
			String clientID = request.getParameter("id");
			int id = Integer.parseInt(clientID);
			customer = dao.showCustomer(id);
			downloadFile(response, customer);

		}
		if (customer != null || result) {
			request.setAttribute("clientlist", dao.read());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	private void uploadFile(Customer customer, HttpServletRequest request) throws ServletException, IOException {
		String name = customer.getSurname();
		String born = customer.getBorn();
		String fileDir = name + "_" + born;
		FileOperations.saveFile(request, SAVE_DIR, fileDir);
	}

	private void downloadFile(HttpServletResponse response, Customer customer) throws ServletException, IOException {
		String name = customer.getSurname();
		String born = customer.getBorn();
		String fileDir = name + "_" + born;
		FileOperations.downloadFile(response, SAVE_DIR, fileDir);
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
