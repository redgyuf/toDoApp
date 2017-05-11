package user;

public class User {
	private Integer id;
	private String email;
	private String password;

	public User(Integer id, String email, String password){
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

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		User otherUser = (User) obj;
		return this.id.equals(otherUser.id);
	}	
}
