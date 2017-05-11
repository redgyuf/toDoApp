package servlet;

import java.io.FileNotFoundException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import data.Data;
import sql.SQLConnector;

@WebListener
public class ContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	SQLConnector sqlc = new SQLConnector();
    	try {
			sqlc.runStartingScript();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	Data data = Data.getInstance();
    	data.getUsersFromDB();
    	
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {    	    	
    }
	
}