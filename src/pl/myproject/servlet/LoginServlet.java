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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserDAO dao = new UserDAO();
		String email = request.getParameter("j_email");
		String password = request.getParameter("j_password");
		try {
			String hashPassword = Validate.getMD5Hash(password);

			if (Validate.checkUser(email, hashPassword)) {
				request.getSession(true).setAttribute("user", dao.readUserByEmail(email));
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				response.sendError(403);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

}
