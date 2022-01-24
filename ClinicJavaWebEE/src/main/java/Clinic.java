
public class Clinic {
	protected int id;
	protected String clinic_name;
	protected String address;
	protected String location_name;
	protected String image;
	protected String description;
	protected String opening_hours;
	protected String opening_days;
	protected String contact_number;
	
	public Clinic(int id, String clinic_name, String address, String location_name, String image, String description,
			String opening_hours, String opening_days, String contact_number) {
		super();
		this.id = id;
		this.clinic_name = clinic_name;
		this.address = address;
		this.location_name = location_name;
		this.image = image;
		this.description = description;
		this.opening_hours = opening_hours;
		this.opening_days = opening_days;
		this.contact_number = contact_number;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(String opening_hours) {
		this.opening_hours = opening_hours;
	}
	public String getOpening_days() {
		return opening_days;
	}
	public void setOpening_days(String opening_days) {
		this.opening_days = opening_days;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	
}
