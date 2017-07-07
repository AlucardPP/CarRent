package pl.myproject.model;

public class User {
	private int user_id;
	private String email;
	private String password;
	private String role;
	private String username;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public User(User user) {
		this.user_id = user.user_id;
		this.email = user.email;
		this.password = user.password;
		this.role = user.role;
		this.username = user.username;
	}

	public User() {
	}

}
