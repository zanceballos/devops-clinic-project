
public class Appointment {

	protected int id;

	protected int user_id;

	protected int clinic_id;

	protected String date_time;

	protected String status;

	protected String appointment_type;

	public Appointment(int id, int user_id, int clinic_id, String date_time, String status, String appointment_type) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.clinic_id = clinic_id;
		this.date_time = date_time;
		this.status = status;
		this.appointment_type = appointment_type;
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

}
