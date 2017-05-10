package data;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;
import tasks.TaskStatus;
import user.User;

public class Data {

	private static volatile Data instance;

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
		currentUser.addTask(taskName);
	}
		
	public void taskDone(User currentUser, Integer taskId){		
		for (Task task : currentUser.getToDoList()) {
			if(task.getId() == taskId){
				task.setStatus(TaskStatus.COMPLETED);
			}
		}	
	}
	
	public void removeTask(User currentUser, Integer taskId){
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
