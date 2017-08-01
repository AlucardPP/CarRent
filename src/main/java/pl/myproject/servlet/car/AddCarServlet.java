package pl.myproject.servlet.car;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pl.myproject.dao.CarDAO;
import pl.myproject.model.Car;
import pl.myproject.util.FileOperations;

/**
 * Servlet implementation class AddCarServlet
 */
@WebServlet("/AddCarServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String SAVE_DIR = "C:/CarFiles";

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		CarDAO dao = new CarDAO();
		Car car = null;
		boolean result = false;
		try {
			checkOptions(request, response, result, car, dao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkOptions(HttpServletRequest request, HttpServletResponse response, boolean result, Car car,
			CarDAO dao) throws Exception {
		if (request.getParameter("save") != null || ServletFileUpload.isMultipartContent(request)) {
			car = getData(request);
			if (FileOperations.isFile(request) == true) {
				String saveDir = request.getParameter("plate");
				FileOperations.saveFile(request, SAVE_DIR, saveDir);
				result = dao.create(car);
			} else {
				result = dao.create(car);
			}

		}

		if (car != null || result) {
			request.setAttribute("carlist", dao.read());
			request.getRequestDispatcher("WEB-INF/car.jsp").forward(request, response);
		}
	}

	private Car getData(HttpServletRequest request) throws IOException, ServletException {
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String plate = request.getParameter("plate");
		String produced = request.getParameter("produced");
		String firstRegistration = request.getParameter("firstregistration");
		String engineSize = request.getParameter("engine");
		String value = request.getParameter("value");
		String rentPerHour = request.getParameter("rentperhour");
		String distance = request.getParameter("distance");
		String available = request.getParameter("available");
		String files = FileOperations.listFiles(request).toString();

		Car car = new Car(brand, model, plate, produced, firstRegistration, engineSize, value, rentPerHour, distance,
				available, files);
		return car;
	}

}
