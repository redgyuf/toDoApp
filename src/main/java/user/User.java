package user;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

public class User {
	private String email;
	private String password;	
	private List<Task> toDoList = new ArrayList<Task>();
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public void addTask(Task task){
		toDoList.add(task);
	}
	
	public void removeTask(Task task){		
		for(int i = 0; i < toDoList.size(); i++ ){
			if(toDoList.get(i) == task){
				toDoList.remove(i);
			}
		}
	}

}
