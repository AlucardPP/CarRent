package pl.myproject.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.myproject.dao.UserDAO;
import pl.myproject.dao.Validate;
import pl.myproject.model.User;

/**
 * Servlet implementation class PasswordServlet
 */
@WebServlet("/PasswordServlet")
public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO();
		boolean result = false;
		if (request.getParameter("save") != null) {
			String userId = request.getParameter("id");
			int id = Integer.parseInt(userId);
			User user = dao.readUserById(id);
			try {
				String old = request.getParameter("old_password");
				String newPassword = request.getParameter("new_password");
				String repeat = request.getParameter("repeat_password");
				if (user.getPassword().equals(old) && newPassword.equals(repeat)) {

					String hashPassword = Validate.getMD5Hash(newPassword);
					System.out.println(hashPassword);

					result = dao.updatePassword(id, hashPassword);
				}
			}

			catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			if (result)
				request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}
