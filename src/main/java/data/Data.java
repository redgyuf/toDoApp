package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logger.Logger;
import sql.SQLConnector;
import tasks.Task;
import tasks.TaskStatus;
import user.User;

public class Data {

	private static volatile Data instance;
	Logger logger = new Logger();
	SQLConnector sqlc = new SQLConnector();

	private Data() {
	}

	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
	}
	
	private User searchUserbyID(int id){
		for (User user : users) {
			if(user.getId()==id)
				return user;
		}
		logger.log("User not found with id: " + id);
		return null;
	}
	
	public List<User> getUsersFromDB(){
		String sqlGetUserCommand = "SELECT * FROM todo.users;";
		ResultSet rs = sqlc.getData(sqlGetUserCommand);
		
		try {
			while (rs.next()) {
				int id = rs.getInt("userID");
				String email = rs.getString("Email");
				String password = rs.getString("Password");
				users.add(new User(id, email, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;		
	}
	
	public void addTasksToUsersFromDB(){
		String sqlGetUserCommand = "SELECT * FROM todo.tasks;";
		ResultSet rs = sqlc.getData(sqlGetUserCommand);
		
		try {
			while (rs.next()) {
				int taskID = rs.getInt("taskID");
				String name = rs.getString("Name");
				String Status = rs.getString("Status");
				int userID = rs.getInt("userID");
				searchUserbyID(userID).addTask(new Task(taskID, name, TaskStatus.valueOf(Status)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addTask(User currentUser, String taskName){
		if(!taskName.equals("")){
			currentUser.addTask(taskName);
			logger.log(currentUser.getEmail() + " Added a new task: " + taskName);
		}
		else{
			logger.log(currentUser.getEmail() + " Tried to add an empty task!");
		}
		
	}
		
	public void taskDone(User currentUser, Integer taskId){		
		for (Task task : currentUser.getToDoList()) {
			if(task.getId() == taskId){
				task.setStatus(TaskStatus.COMPLETED);
				logger.log(currentUser.getEmail() + " Completed the: " + task.getName());
			}
		}	
	}
	
	public void removeTask(User currentUser, Integer taskId){
		logger.log(currentUser.getEmail() + " Removed task with ID: " + taskId);
		currentUser.removeTask(taskId);
	}
	
	public List<Task> filterTasks(User currentUser, String filter){
		List<Task> tasks = currentUser.getToDoList();
		List<Task> tasksToSend = new ArrayList<Task>();
		
		for (Task task : tasks) {
			if(filter.equals("All") || filter == null){
				tasksToSend.add(task);
			}else{
				if(filter.equals("Active")){
					if(task.getStatus().equals(TaskStatus.ACTIVE)){
						tasksToSend.add(task);
					}
				}else{
					if(filter.equals("Completed")){
						if(task.getStatus() == TaskStatus.COMPLETED){
							tasksToSend.add(task);
						}
					}
				}
			}
		}
		
		return tasksToSend;
	}

	public static Data getInstance() {
		if (instance == null) {
			synchronized (Data.class) {
				if (instance == null) {
					instance = new Data();
				}
			}
		}
		return instance;
	}
}
