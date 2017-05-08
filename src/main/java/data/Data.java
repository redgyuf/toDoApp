package data;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class Data {

	private static volatile Data instance;
	private Data() {
		User admin = new User("admin@gmail.com", "admin");
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
