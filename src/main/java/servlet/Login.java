package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Data;
import logger.Logger;
import user.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		Data data = Data.getInstance();
		Logger logger = new Logger();
		List<User> users = data.getUsers();
		logger.Log("Inbound POST request @ " + getClass().toString());
		
		String inputEmail = request.getParameter("inputEmail");
		String inputPassword = request.getParameter("inputPassword");		
		
		for (User user : users) {
			if(user.getEmail().equals(inputEmail) && user.getPassword().equals(inputPassword)){
				out.println(HttpServletResponse.SC_ACCEPTED);
				logger.Log("Login successfull");
				return;
			}
		}
		
		out.println(HttpServletResponse.SC_FORBIDDEN);
		logger.Log("Login failed");		
	}

}
