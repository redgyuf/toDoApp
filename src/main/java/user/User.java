package user;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

public class User {
	private Integer id;
	private String email;
	private String password;
	private List<Task> toDoList = new ArrayList<Task>();

	public User(Integer id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public List<Task> getToDoList() {
		return toDoList;
	}

	public void addTask(String name) {
		Task newTask = new Task(getBiggestID(), name);
		toDoList.add(newTask);
	}
	
	private int getBiggestID(){
		int maxID = 0;
		for (Task task : toDoList) {
			if(task.getId() > maxID){
				maxID = task.getId();
			}
		}
		return maxID+1;
	}

	public void removeTask(int id) {
		System.out.println("Task removed");
		for (Task task : toDoList) {
			if(task.getId() == id){
				System.out.println("megtalaltam");
				toDoList.remove(task);
				return;
			}
		}
	}

}
