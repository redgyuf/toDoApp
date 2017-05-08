package data;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class Data {

	private static volatile Data instance;
	private Data() {
	}
	
	private List<User> users = new ArrayList<User>();
	
	
	
	
	
	
	
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
