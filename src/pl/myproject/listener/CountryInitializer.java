package pl.myproject.listener;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pl.myproject.dao.CarDAO;
import pl.myproject.dao.CustomerDAO;
import pl.myproject.dao.EmployeeDAO;
import pl.myproject.dao.RentedDAO;

import pl.myproject.util.CountryCode;

/**
 * Application Lifecycle Listener implementation class CountryInitializer
 *
 */
@WebListener
public class CountryInitializer implements ServletContextListener {

	private CustomerDAO customerDao = new CustomerDAO();
	private EmployeeDAO employeDao = new EmployeeDAO();
	private CarDAO carDao = new CarDAO();
	private RentedDAO rentedDao = new RentedDAO();

	/**
	 * Default constructor.
	 */
	public CountryInitializer() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		Map<String, String> countries = CountryCode.getCode();
		sce.getServletContext().setAttribute("countries", countries);
		sce.getServletContext().setAttribute("success", "default");
		try {
			sce.getServletContext().setAttribute("clientlist", customerDao.read());
			sce.getServletContext().setAttribute("employeelist", employeDao.read());
			sce.getServletContext().setAttribute("carlist", carDao.read());
			sce.getServletContext().setAttribute("rentedlist", rentedDao.read());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
