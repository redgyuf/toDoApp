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

	public void removeTask(Task task) {
		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i) == task) {
				toDoList.remove(i);
			}
		}
	}

}
