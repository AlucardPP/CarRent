package pl.myproject.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.EmployeeDAO;
import pl.myproject.dao.UserDAO;

import pl.myproject.model.Employee;
import pl.myproject.util.FileOperations;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/EmployeeFiles";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
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
		if (request.getParameter("save") != null) {
			employee = getData(request);
			uploadFile(employee, request);
			dao.create(employee);
			String email = employee.getEmail();
			result = user.create(email);

		} else if (request.getParameter("delete") != null) {
			String idEmployee = request.getParameter("employeeID");
			int id = Integer.parseInt(idEmployee);
			result = dao.delete(id);
		} else if (request.getParameter("update") != null) {
			String idEmployee = request.getParameter("IDemployee");
			int id = Integer.parseInt(idEmployee);
			employee = getUpdateData(request);
			dao.update(employee, id);
			result = updateUser(employee, user, id);

		} else if (request.getParameter("download") != null) {
			String idEmployee = request.getParameter("id");
			int id = Integer.parseInt(idEmployee);
			employee = dao.readById(id);
			downloadFile(response, employee);
		}
		if (employee != null || result) {
			request.setAttribute("employeelist", dao.read());
			request.getRequestDispatcher("employee.jsp").forward(request, response);
		}
	}

	private void uploadFile(Employee employee, HttpServletRequest request) throws ServletException, IOException {
		String name = employee.getSurname();
		String email = employee.getEmail();
		String fileDir = name + "_" + email;
		FileOperations.saveFile(request, SAVE_DIR, fileDir);
	}

	private void downloadFile(HttpServletResponse response, Employee employee) throws ServletException, IOException {
		String name = employee.getSurname();
		String email = employee.getEmail();
		String fileDir = name + "_" + email;
		FileOperations.downloadFile(response, SAVE_DIR, fileDir);
	}

	private boolean updateUser(Employee employee, UserDAO user, int id) {
		boolean result = false;
		String name = employee.getName();
		String surname = employee.getSurname();
		String username = name + " " + surname;
		result = user.update(id, username, employee.getEmail(), employee.getRole());
		return result;
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

	private Employee getUpdateData(HttpServletRequest request) {
		String name = request.getParameter("upname");
		String surname = request.getParameter("upsurname");
		String born = request.getParameter("upborn");
		String idCardNumber = request.getParameter("upidcardnumber");
		String street = request.getParameter("upstreet");
		String houseNumber = request.getParameter("uphousenumber");
		String city = request.getParameter("upcity");
		String country = request.getParameter("upcountry");
		String gender = request.getParameter("upgender");
		String telephone = request.getParameter("uptelephone");
		String education = request.getParameter("upeducation");
		String salary = request.getParameter("upsalary");
		String role = request.getParameter("uprole");
		String email = request.getParameter("upemail");
		Employee employee = new Employee(name, surname, born, idCardNumber, street, houseNumber, city, country, gender,
				telephone, education, salary, role, email);
		return employee;
	}

}
