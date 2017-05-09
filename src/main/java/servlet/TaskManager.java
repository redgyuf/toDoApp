package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import tasks.TaskStatus;
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
		String filter = request.getParameter("filter");
		if(filter == null)
			filter = "All";		
		
		System.out.println(filter);
		PrintWriter out = response.getWriter();

		Data data = Data.getInstance();
		List<User> users = data.getUsers();

		Gson gson = new Gson();
		String jsonString = "";

		for (User user : users) {
			
			List<Task> tasks = user.getToDoList();
			List<Task> tasksToSend = new ArrayList<Task>();
			for (Task task : tasks) {
				System.out.println("Taskok");
				if(filter.equals("All")){
					tasksToSend.add(task);
					System.out.println("allban");
				}else{
					if(filter.equals("Active")){
						if(task.getStatus().equals(TaskStatus.ACTIVE)){
							tasksToSend.add(task);
							System.out.println("activeban");
						}
					}else{
						if(filter.equals("Completed")){
							if(task.getStatus() == TaskStatus.COMPLETED){
								tasksToSend.add(task);
								System.out.println("completedben");
							}
						}
					}
				}
			}
			if(!tasksToSend.isEmpty()){
				jsonString += gson.toJson(tasksToSend);
				System.out.println(jsonString);
			}
			else{
				logger.log("No task found!");
			}
			
		}
		response.setContentType("application/json");
		out.print(jsonString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = new Logger();
		logger.log("Post request @ TaskManager");
		
		HttpSession session = request.getSession(false);
		User currentUser = (User) session.getAttribute("user");
		
		System.out.println(request.getParameter("taskName"));
		currentUser.addTask(request.getParameter("taskName"));
		
	}

	
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Logger logger = new Logger();
		logger.log("Put request");
		
		HttpSession session = request.getSession(false);
		Data data = Data.getInstance();
		List<User> users = data.getUsers();
		
		for (User user : users) {
			if(user == session.getAttribute("user")){
				Integer taskDoneID = Integer.valueOf(request.getParameter("taskDone"));
				System.out.println(request.getParameter("taskDone"));
				for (Task task : user.getToDoList()) {
					if(task.getId() == taskDoneID){
						task.setStatus(TaskStatus.COMPLETED);
					}
				}
			}				
		}	
		
		PrintWriter out = response.getWriter();
		out.print(HttpServletResponse.SC_OK);
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
