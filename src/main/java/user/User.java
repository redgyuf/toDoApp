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

	public void addTask(Task task) {
		toDoList.add(task);
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
