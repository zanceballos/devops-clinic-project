
public class AppointmentJoin {
protected int id;
	
	protected String full_name;
	
	protected String email;
	
	protected String username;
	
	protected int user_id;
	
	protected int clinic_id;
	
	protected String date_time;
	
	protected String status;
	
	protected String appointment_type;
	
	protected String clinic_name;
	
	protected String address;
	
	protected String location_name;
	
	protected String image;
	
	protected String contact_number;
	

	public AppointmentJoin(int id, String full_name, String email, String username, int user_id, int clinic_id,
			String date_time, String status, String appointment_type, String clinic_name, String address,
			String location_name, String image, String contact_number) {
		super();
		this.id = id;
		this.full_name = full_name;
		this.email = email;
		this.username = username;
		this.user_id = user_id;
		this.clinic_id = clinic_id;
		this.date_time = date_time;
		this.status = status;
		this.appointment_type = appointment_type;
		this.clinic_name = clinic_name;
		this.address = address;
		this.location_name = location_name;
		this.image = image;
		this.contact_number = contact_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getClinic_id() {
		return clinic_id;
	}

	public void setClinic_id(int clinic_id) {
		this.clinic_id = clinic_id;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppointment_type() {
		return appointment_type;
	}

	public void setAppointment_type(String appointment_type) {
		this.appointment_type = appointment_type;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClinic_name() {
		return clinic_name;
	}

	public void setClinic_name(String clinic_name) {
		this.clinic_name = clinic_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
