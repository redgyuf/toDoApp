package data;

import java.util.ArrayList;
import java.util.List;

import logger.Logger;
import tasks.Task;
import tasks.TaskStatus;
import user.User;

public class Data {

	private static volatile Data instance;
	Logger logger = new Logger();

	private Data() {
		User admin = new User(1, "admin@gmail.com", "admin");
		admin.addTask("Teszt");
		admin.addTask("Ketteske");
		users.add(admin);
	}

	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
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
