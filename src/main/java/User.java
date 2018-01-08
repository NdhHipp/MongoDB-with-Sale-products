
public class User {
	private String user;
	private String password;
	private String position;
	//
	public User() {};
	public User(String user, String password, String position) {
		super();
		this.user = user;
		this.password = password;
		this.position = position;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	public String getPosition() {
		return position;
	}
	//
}
