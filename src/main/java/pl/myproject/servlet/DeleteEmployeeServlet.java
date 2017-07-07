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
 * Servlet implementation class DeleteEmployeeServlet
 */
@WebServlet("/DeleteEmployee")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DeleteEmployeeServlet extends HttpServlet {
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
		if (request.getParameter("delete") != null) {
			String idEmployee = request.getParameter("employeeID");
			int id = Integer.parseInt(idEmployee);
			dao.delete(id);
			result = user.delete(id);
		}
		if (employee != null || result) {
			request.setAttribute("employeelist", dao.read());
			request.getRequestDispatcher("WEB-INF/employee.jsp").forward(request, response);
		}
	}
}
