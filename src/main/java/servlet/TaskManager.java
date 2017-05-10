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
	Logger logger = new Logger();
	Data data = Data.getInstance();

	public TaskManager() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");		
		String filter = request.getParameter("filter");
		logger.log("Get request @ TaskManager by " + currentUser.getEmail());
		logger.log(filter + " Tasks requested by " + currentUser.getEmail());

		Gson gson = new Gson();
		String jsonString = "";

		List<Task> tasksToSend = data.filterTasks(currentUser, filter);
		
		if(!tasksToSend.isEmpty()){
			jsonString += gson.toJson(tasksToSend);
		}
		else{
			logger.log("No task found!");
		}
			
		response.setContentType("application/json");
		out.print(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");
		String taskName = request.getParameter("taskName");
		logger.log("Post request @ TaskManager " + currentUser.getEmail());
		
		data.addTask(currentUser, taskName);		
	}	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");
		Integer taskDoneID = Integer.valueOf(request.getParameter("taskDone"));
		logger.log("Put request @ TaskManager " + currentUser.getEmail());
		
		data.taskDone(currentUser, taskDoneID);
		
		PrintWriter out = response.getWriter();
		out.print(HttpServletResponse.SC_OK);
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");
		Integer removedTaskID = Integer.valueOf(request.getParameter("removeTask"));
		logger.log("Delete request @ TaskManager " + currentUser.getEmail());
		
		data.removeTask(currentUser, removedTaskID);
		
		PrintWriter out = response.getWriter();
		out.print(HttpServletResponse.SC_OK);
	}

}
