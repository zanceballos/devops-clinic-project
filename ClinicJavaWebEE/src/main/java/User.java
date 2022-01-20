
public class User {

	protected int id;
	protected String username;
	protected String role;
	protected String contact_number;
	protected String email;
	protected String password;
	protected String full_name;
	
	public User(int id, String username, String role, String contact_number, String email, String password,
			String full_name) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.contact_number = contact_number;
		this.email = email;
		this.password = password;
		this.full_name = full_name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
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
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	
}
