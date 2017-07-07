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

import pl.myproject.dao.EmployeeDAO;
import pl.myproject.dao.UserDAO;
import pl.myproject.model.Employee;
import pl.myproject.util.FileOperations;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddEmployee")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/EmployeeFiles";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		EmployeeDAO dao = new EmployeeDAO();
		Employee employee = null;
		UserDAO user = new UserDAO();
		boolean result = false;
		try {
			checkOptions(employee, dao, user, result, request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void checkOptions(Employee employee, EmployeeDAO dao, UserDAO user, boolean result,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		if (request.getParameter("save") != null || ServletFileUpload.isMultipartContent(request)) {
			employee = getData(request);
			uploadFile(employee, request);
			dao.create(employee);
			String email = employee.getEmail();
			result = user.create(email);

		}
		if (employee != null || result) {
			request.setAttribute("employeelist", dao.read());
			request.getRequestDispatcher("WEB-INF/employee.jsp").forward(request, response);
		}
	}

	private void uploadFile(Employee employee, HttpServletRequest request) throws ServletException, IOException {
		String name = employee.getSurname();
		String email = employee.getEmail();
		String fileDir = name + "_" + email;
		FileOperations.saveFile(request, SAVE_DIR, fileDir);
	}

	private Employee getData(HttpServletRequest request) throws IOException, ServletException {
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
		String education = request.getParameter("education");
		String salary = request.getParameter("salary");
		String role = request.getParameter("role");
		String email = request.getParameter("email");
		String files = FileOperations.listFiles(request).toString();
		Employee employee = new Employee(name, surname, born, idCardNumber, street, houseNumber, city, country, gender,
				telephone, education, salary, role, email, files);
		return employee;
	}

}
