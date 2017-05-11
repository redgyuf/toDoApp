package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
	
	public List<User> getUsersFromDB() {
		List<User> users = new ArrayList<User>();
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

	public List<Task> getTaskForUserFromDB(int userID) {
		List<Task> fromDB = new CopyOnWriteArrayList<Task>();

		ResultSet rs = sqlc.getData("Select * from todo.tasks where userID=" + userID);
		try {
			while (rs.next()) {
				int taskID = rs.getInt("taskID");
				String name = rs.getString("Name");
				String Status = rs.getString("Status");
				fromDB.add(new Task(taskID, name, TaskStatus.valueOf(Status)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fromDB;
	}

	public void addTask(User currentUser, String taskName) {
		if (!taskName.equals("")) {
			sqlc.sendQuery("insert into todo.tasks (Name,Status,userID) values ('" + taskName + "','ACTIVE',"
					+ currentUser.getId() + ")");
			logger.log(currentUser.getEmail() + " Added a new task: " + taskName);
		} else {
			logger.log(currentUser.getEmail() + " Tried to add an empty task!");
		}

	}

	public void taskDone(User currentUser, Integer taskId) {
		List<Task> tasks = getTaskForUserFromDB(currentUser.getId());
		for (Task task : tasks) {
			if (task.getId() == taskId) {
				sqlc.sendQuery("update todo.tasks set Status = 'COMPLETED' where taskID =" + taskId);
				getTaskForUserFromDB(currentUser.getId());
				logger.log(currentUser.getEmail() + " Completed the: " + task.getName());
			}
		}
	}

	public void removeTask(User currentUser, Integer taskId) {
		sqlc.sendQuery("delete from todo.tasks where taskID =" + taskId);
		logger.log(currentUser.getEmail() + " Removed task with ID: " + taskId);
	}

	public List<Task> filterTasks(User currentUser, String filter) {
		List<Task> tasks = getTaskForUserFromDB(currentUser.getId());
		List<Task> tasksToSend = new ArrayList<Task>();

		for (Task task : tasks) {
			if (filter.equals("All") || filter == null) {
				tasksToSend.add(task);
			} else {
				if (filter.equals("Active")) {
					if (task.getStatus().equals(TaskStatus.ACTIVE)) {
						tasksToSend.add(task);
					}
				} else {
					if (filter.equals("Completed")) {
						if (task.getStatus() == TaskStatus.COMPLETED) {
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
