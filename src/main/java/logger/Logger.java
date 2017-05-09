package logger;

import java.util.Date;

public class Logger {
	
	public void log(String log){
		Date date = new Date();
		System.out.println(date.toLocaleString() + ": " + log);
	}

}
