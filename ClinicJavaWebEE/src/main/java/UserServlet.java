
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/clinic_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// SQL prepared statements to perform UserCRUD
	private static final String INSERT_USERS_SQL = "INSERT INTO users"
			+ " (username,role,contact_number, email, password, full_name) VALUES " + " (?, ?, ?,?, ?, ?);";
	private static final String SELECT_USER_BY_ID = "select id,username,full_name,password,email,contact_number,role from users where id = ?";
	private static final String SELECT_USER_BY_USERNAME = "select id,username,role,contact_number,email,password,full_name from users where username = ?";
	private static final String SELECT_ALL_USERS = "select * from users ";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set id=?, username = ?, role = ? , contact_number = ? , email = ?, password= ? , full_name=? where id = ?;";

	// Implement the getConnection method which facilitates connection to
	// the database via JDBC
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
	public UserServlet() {
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
			case "/UserServlet/login":
				loginUser(request, response);
				break;
			case "/UserServlet/logout":
				logoutUser(request, response);
				break;
			case "/UserServlet/delete":
				deleteUser(request, response);
				break;
			case "/UserServlet/edit":
				// showEditForm(request, response);
				break;
			case "/UserServlet/showUpdateForm":
				showUserDetails(request, response);
				break;
			case "/UserServlet/dashboard":
				// listUsers(request, response);
				break;
			case "/UserServlet/home":
				displayHomePage(request, response);
				break;
			case "/UserServlet/account-details":
				showUserDetails(request, response);
				break;
			case "/UserServlet/showPasswordForm":
				showUserDetails(request, response);
				break;
			case "/UserServlet/updatePassword":
				updateDetails(request, response);
				break;
			case "/UserServlet/updateAccount":
				updateDetails(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// Set User session storage here!
		HttpSession session = request.getSession();

		// get parameter passed in the URL
		String iusername = request.getParameter("username");
		String ipassword = request.getParameter("password");
		System.out.println(iusername);
		System.out.println(ipassword);
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);) {
			preparedStatement.setString(1, iusername); // pass the ID into the sql query '?' character
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			boolean noBreak = true;
			while (rs.next()) {
				// check if the username exists
				if (rs.getString("username").equals(iusername)) {
					if (rs.getString("password").equals(ipassword)) {
						int id = rs.getInt("id");
						String username = rs.getString("username");
						// String contact_number = rs.getString("contact_number");
						// String email = rs.getString("email");
						String role = rs.getString("role");
						// String password = rs.getString("password");
						// String full_name = rs.getString("full_name");

						session.setAttribute("id", id);
						session.setAttribute("username", username);
						session.setAttribute("logged_in", true);
						session.setAttribute("role", role);

						System.out.println(session.getAttribute("username"));

						// If else statement to redirect user based on their user role
						if (role.equals("patient")) {
							response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");
						}
						if (role.equals("doctor")) {
							response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");
						}

						// stop the while loop as we have reached our condition
						noBreak = false;
						break;
					} else {
						// password incorrect , return error
						response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
						session.setAttribute("password_err", true);
						// stop the while loop as we have reached our condition
						noBreak = false;
						break;
					}
				}

			}
			if (noBreak) {
				// username not found return error
				response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
				session.setAttribute("username_err", true);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// Set User session storage here!
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("username");
		session.removeAttribute("logged_in");
		session.removeAttribute("role");
		response.sendRedirect("http://localhost:8090/ClinicJavaWebEE");

	}

	private void displayHomePage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		// Set User session storage here!
		HttpSession session = request.getSession();

		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		if (session.getAttribute("role").equals("patient")) {
			request.getRequestDispatcher("/PatientHome.jsp").forward(request, response);
		}
		if (session.getAttribute("role").equals("doctor")) {
			request.getRequestDispatcher("/DoctorHome.jsp").forward(request, response);
		}
		// response.sendRedirect("http://localhost:8090/ClinicJavaWebEE");

	}

	private void showUserDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		HttpSession session = request.getSession();
		
		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		
		// get parameter passed in the URL
		String id = request.getParameter("id");
		System.out.print(id);
		User userDetails = new User(0, "", "", "", "", "", "");

		if (!session.getAttribute("id").toString().equals(id)) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/UserServlet/home");
			return;
		}

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setString(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("EXECUTED QUERY");
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				System.out.println("FOUND USER");
				int user_id = rs.getInt("id");
				String username = rs.getString("username");
				String role = rs.getString("role");
				String contact_number = rs.getString("contact_number");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String full_name = rs.getString("full_name");
				userDetails = new User(user_id, username, role, contact_number, email, password, full_name);
				System.out.println("Details: " + userDetails);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		if (request.getServletPath().equals("/UserServlet/account-details")) {
			request.setAttribute("userDetails", userDetails);
			request.getRequestDispatcher("/AccountPage.jsp").forward(request, response);
		}
		if (request.getServletPath().equals("/UserServlet/showPasswordForm")) {
			request.setAttribute("userDetails", userDetails);
			request.getRequestDispatcher("/ChangePassword.jsp").forward(request, response);
		}
		if (request.getServletPath().equals("/UserServlet/showUpdateForm")) {
			request.setAttribute("userDetails", userDetails);
			request.getRequestDispatcher("/AccountEdit.jsp").forward(request, response);
		}

	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			int i = statement.executeUpdate();
		}
		response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/UserServlet/logout");
	}

	private void updateDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		HttpSession session = request.getSession();

		System.out.print("Update function called");
		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String full_name = request.getParameter("full_name");
		String email = request.getParameter("email");
		String contact_number = request.getParameter("contact_number");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		
		
		boolean checkForm = false;
		if(request.getServletPath().equals("/UserServlet/updatePassword"))
		{	
			String cfmpassword = request.getParameter("cfm_password");
			if(password.equals(cfmpassword)) {
				checkForm = true;
			}else {
				session.setAttribute("password_error", true);
				response.sendRedirect(
						"http://localhost:8090/ClinicJavaWebEE/UserServlet/showPasswordForm?id="
								+ session.getAttribute("id"));
				return;
			}
		}
		if(request.getServletPath().equals("/UserServlet/updateAccount")) {
			checkForm= true;
			
		}
		System.out.println("Excecuting user update function");
		if(checkForm == true) {
			// Step 2: Attempt connection with database and execute update user SQL query
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
				statement.setString(1, id);
				statement.setString(2, username);
				statement.setString(3, role);
				statement.setString(4, contact_number);
				statement.setString(5, email);
				statement.setString(6, password);
				statement.setString(7, full_name);
				statement.setString(8, id);
				int i = statement.executeUpdate();
				System.out.println(password);

			} catch (Exception exception) {
				System.out.println(exception);
				// out.close();
			}

			response.sendRedirect(
					"http://localhost:8090/ClinicJavaWebEE/UserServlet/account-details?id="
							+ session.getAttribute("id"));
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
