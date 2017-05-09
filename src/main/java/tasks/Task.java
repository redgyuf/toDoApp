package tasks;

public class Task {
	private Integer id;
	private String name;
	private TaskStatus status;
	
	public Task(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		status = TaskStatus.ACTIVE;
	}

	public Task(Integer id, String name, TaskStatus status) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public TaskStatus getStatus() {
		return status;
	}
	
}
