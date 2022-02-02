
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/clinic_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// sql prepared statements to perform ReviewCRUD
	private static final String INSERT_REVIEW = "INSERT into review (id, user_id, clinic_id, review, rating_score, review_title) VALUES(?,?,?,?,?,?)";
	private static final String SELECT_REVIEW_BY_ID = "SELECT id, user_id, clinic_id, review, rating_score, review_title from review where id = ?";
	private static final String SELECT_REVIEW_BY_USERID = "SELECT id, user_id, clinic_id, review, rating_score, review_title from review where user_id = ?";
	private static final String SELECT_REVIEW_BY_CLINICID = "SELECT id, user_id, clinic_id, review, rating_score, review_title from review where clinic_id = ?";;
	private static final String DELETE_REVIEW = "DELETE from review where id = ?";
	private static final String UPDATE_REVIEW = "UPDATE review SET id = ?, user_id = ?, clinic_id = ?, review = ?, rating_score = ?, review_title = ? where id = ? ";

	// allow connection to database using jdbc
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/ReviewServlet/ListClinicReviews":
				ListClinicReviews(request, response); // list reviews for the particular clinic
				break;
			case "/ReviewServlet/ClinicReviewForm":
				ShowReviewClinicForm(request, response); // show review form
				break;
			case "/ReviewServlet/ReviewClinic":
				ReviewClinic(request, response); // create review (POST to db)
				break;
			case "/ReviewServlet/delete":
				DeleteReview(request, response); // delete review
				break;
			case "/ReviewServlet/ShowUpdateForm": // Show update form
				ShowUpdateForm(request, response); // update review
				break;
			case "/ReviewServlet/update": // Show update form
				UpdateReview(request, response); // update review
				break;

			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void ListClinicReviews(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") != null)
		{
			int currentUID = Integer.parseInt(session.getAttribute("id").toString());
			request.setAttribute("uid", currentUID);
		}

		// get parameter passed in the URL
		String id = request.getParameter("id");

		// Call database to get all reviews based on clinic id
		List<Review> reviews = new ArrayList<>();
		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEW_BY_CLINICID);) {
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int reviewid = rs.getInt("id");
				int clinic_id = rs.getInt("clinic_id");
				int user_id = rs.getInt("user_id");
				String review = rs.getString("review");
				int rating_score = rs.getInt("rating_score");
				String review_title = rs.getString("review_title");
				reviews.add(new Review(reviewid, user_id, clinic_id, review, rating_score, review_title));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		request.setAttribute("clinicid", id);
		request.setAttribute("reviews", reviews);
		request.getRequestDispatcher("/ClinicReviews.jsp").forward(request, response);
	}

	private void ShowReviewClinicForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// get parameter passed in the URL
		String id = request.getParameter("id");

		request.setAttribute("clinicid", id);
		request.getRequestDispatcher("/ReviewClinic.jsp").forward(request, response);

	}

	private void ReviewClinic(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// get session storage
		HttpSession session = request.getSession();

		String user_id = session.getAttribute("id").toString();

		// retrieve parameter from request in web form
		String clinic_id = request.getParameter("clinicid");
		String review = request.getParameter("review");
		String rating_score = request.getParameter("rating_score");
		String review_title = request.getParameter("review_title");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_db", "root", "password");
			PreparedStatement ps = con.prepareStatement(INSERT_REVIEW);
			ps.setInt(1, 0);
			ps.setString(2, user_id);
			ps.setString(3, clinic_id);
			ps.setString(4, review);
			ps.setString(5, rating_score);
			ps.setString(6, review_title);

			int i = ps.executeUpdate();
			System.out.println("SQL query executed");
			if (i > 0) {
				System.out.println("Successfully inserted: " + i);

				// display success page with the data

				response.sendRedirect(
						"http://localhost:8090/ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id=" + clinic_id);
			}
		} catch (Exception exception) {
			System.out.println(exception);
			// out.close();
		}

	}

	// Show update form
	private void ShowUpdateForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String id = request.getParameter("id");
		System.out.print(id);

		Review current_review = new Review(0, 0, 0, id, 0, id);

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEW_BY_ID);) {
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int review_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int clinic_id = rs.getInt("clinic_id");
				String review = rs.getString("review");
				int rating_score = rs.getInt("rating_score");
				String review_title = rs.getString("review_title");
				current_review = new Review(review_id, user_id, clinic_id, review, rating_score, review_title);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("review", current_review);
		request.getRequestDispatcher("/EditReview.jsp").forward(request, response);

	}

	// Perform update function
	private void UpdateReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		int clinic_id = Integer.parseInt(request.getParameter("clinic_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String review = request.getParameter("review");
		int rating_score = Integer.parseInt(request.getParameter("rating_score"));
		String review_title = request.getParameter("review_title");
		
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW);){
			statement.setInt(1, id);
			statement.setInt(2, user_id);
			statement.setInt(3, clinic_id);
			statement.setString(4, review);
			statement.setInt(5, rating_score);
			statement.setString(6, review_title);
			statement.setInt(7, id);
			int i = statement.executeUpdate();
		}
		
		response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id=" + clinic_id);
		

	}

	private void DeleteReview(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		int clinicid = Integer.parseInt(request.getParameter("clinicid"));
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW);) {
			statement.setInt(1, id);
			statement.executeUpdate();
			System.out.print(statement);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id=" + clinicid);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
