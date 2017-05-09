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
		Task task = new Task(1, "Teszt", TaskStatus.ACTIVE);
		Task task2 = new Task(2, "Kettteske", TaskStatus.ACTIVE);
		admin.addTask(task);
		admin.addTask(task2);
		users.add(admin);
	}

	private List<User> users = new ArrayList<User>();

	public List<User> getUsers() {
		return users;
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
