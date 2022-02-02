
public class ReviewJoin {
   protected int id;
	
	protected int user_id;
	
	protected int clinic_id;
	
	protected String review;
	
	protected int rating_score;
	
	protected String review_title;
	
	protected String username;
	
	protected String email;

	public ReviewJoin(int id, int user_id, int clinic_id, String review, int rating_score, String review_title,
			String username, String email) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.clinic_id = clinic_id;
		this.review = review;
		this.rating_score = rating_score;
		this.review_title = review_title;
		this.username = username;
		this.email = email;
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

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating_score() {
		return rating_score;
	}

	public void setRating_score(int rating_score) {
		this.rating_score = rating_score;
	}

	public String getReview_title() {
		return review_title;
	}

	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
}
