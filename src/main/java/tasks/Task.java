package tasks;

public class Task {
	private String name;
	private TaskStatus status;
	
	public Task(String name) {
		super();
		this.name = name;
		status = TaskStatus.ACTIVE;
	}

	public Task(String name, TaskStatus status) {
		super();
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public TaskStatus getStatus() {
		return status;
	}
	
}
