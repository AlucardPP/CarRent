package pl.myproject.listener;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import pl.myproject.dao.CustomerDAO;
import pl.myproject.util.CountryCode;

/**
 * Application Lifecycle Listener implementation class CountryInitializer
 *
 */
@WebListener
public class CountryInitializer implements ServletContextListener {
	CustomerDAO dao=new CustomerDAO();

    /**
     * Default constructor. 
     */
    public CountryInitializer() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	 Map<String, String> countries = CountryCode.getCode();
    	    sce.getServletContext().setAttribute("countries", countries);
    	    sce.getServletContext().setAttribute("clientlist", dao.read());
    }
	
}
