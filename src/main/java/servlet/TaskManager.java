package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import data.Data;
import logger.Logger;
import tasks.Task;
import user.User;

@WebServlet("/TaskManager")
public class TaskManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TaskManager() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = new Logger();
		logger.log("Get request");
		PrintWriter out = response.getWriter();

		Data data = Data.getInstance();
		List<User> users = data.getUsers();

		Gson gson = new Gson();
		String jsonString = "";

		for (User user : users) {
			List<Task> tasks = user.getToDoList();
			jsonString += gson.toJson(tasks);
			System.out.println(jsonString);
		}
		response.setContentType("application/json");
		out.print(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Data data = Data.getInstance();
		List<User> users = data.getUsers();
		
		for (User user : users) {
			if(user == session.getAttribute("user")){
				Integer removedTaskID = Integer.valueOf(request.getParameter("removeTask"));
				System.out.println(request.getParameter("removeTask"));
				user.removeTask(removedTaskID);
			}				
		}	
		
		PrintWriter out = response.getWriter();
		out.print(HttpServletResponse.SC_OK);
	}

}
