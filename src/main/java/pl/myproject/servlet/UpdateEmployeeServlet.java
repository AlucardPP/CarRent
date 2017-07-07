package pl.myproject.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.EmployeeDAO;
import pl.myproject.dao.UserDAO;
import pl.myproject.model.Employee;

/**
 * Servlet implementation class UpdateEmployeeServlet
 */
@WebServlet("/UpdateEmployee")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UpdateEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		if (request.getParameter("update") != null) {
			String idEmployee = request.getParameter("IDemployee");
			int id = Integer.parseInt(idEmployee);
			employee = getUpdateData(request);
			dao.update(employee, id);
			result = updateUser(employee, user, id);

		}
		if (employee != null || result) {
			request.setAttribute("employeelist", dao.read());
			request.getRequestDispatcher("WEB-INF/employee.jsp").forward(request, response);
		}
	}

	private boolean updateUser(Employee employee, UserDAO user, int id) throws SQLException {
		boolean result = false;
		String name = employee.getName();
		String surname = employee.getSurname();
		String username = name + " " + surname;
		result = user.update(id, username, employee.getEmail(), employee.getRole());
		return result;
	}

	private Employee getUpdateData(HttpServletRequest request) throws SQLException {
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
