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
import pl.myproject.util.FileOperations;

/**
 * Servlet implementation class DownloadCustomerFileServlet
 */
@WebServlet("/downloadCustomerFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DownloadCustomerFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/CustomerFiles";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		if (request.getParameter("download") != null) {
			String clientID = request.getParameter("id");
			int id = Integer.parseInt(clientID);
			customer = dao.showCustomer(id);
			downloadFile(response, customer);

		}
	}

	private void downloadFile(HttpServletResponse response, Customer customer) throws ServletException, IOException {
		String name = customer.getSurname();
		String born = customer.getBorn();
		String fileDir = name + "_" + born;
		FileOperations.downloadFile(response, SAVE_DIR, fileDir);
	}
}
