package tasks;

public enum TaskStatus {
	ACTIVE,
	COMPLETED;
	
	public static void main(String[] args) {
		String s = "ACTIVE";
		TaskStatus ts = TaskStatus.valueOf(s);
		String s2 = ts.name();
		System.out.println();
	}
}
