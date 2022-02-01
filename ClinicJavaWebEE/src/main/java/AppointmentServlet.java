
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AppointmentServlet
 */
@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String jdbcURL = "jdbc:mysql://localhost:3306/clinic_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String INSERT_APPOINTMENT_SQL = "INSERT INTO appointment"
			+ "(id, user_id, clinic_id, date_time, status, appointment_type) " + "VALUES (?,?,?,?,?,?)";
	private static final String SELECT_APPOINTMENT_BY_ID = "SELECT `id`, `user_id`, `clinic_id`, `date_time`, `status`, `appointment_type` FROM `appointment` where id = ?";
	private static final String SELECT_APPOINTMENT_BY_USERID = "SELECT appointment.id,users.full_name, users.contact_number, users.email, users.username, appointment.clinic_id , appointment.user_id , appointment.date_time, appointment.status, appointment.appointment_type,clinic.clinic_name, clinic.address, clinic.location_name, clinic.image, clinic.contact_number"
			+ " FROM users" + " JOIN appointment on users.id = appointment.user_id"
			+ " JOIN clinic on appointment.clinic_id = clinic.id" + " where users.id = ?";
	private static final String SELECT_APPOINTMENT_BY_CLINICID = "SELECT appointment.id,users.full_name, users.contact_number, users.email, users.username, appointment.clinic_id , appointment.user_id , appointment.date_time, appointment.status, appointment.appointment_type,clinic.clinic_name, clinic.address, clinic.location_name, clinic.image, clinic.contact_number"
			+ " FROM users" + " JOIN appointment on users.id = appointment.user_id"
			+ " JOIN clinic on appointment.clinic_id = clinic.id" + " where appointment.clinic_id = ?";
	private static final String SELECT_ALL_APPOINTMENT = "SELECT * FROM appointment";
	private static final String DELETE_APPOINTMENT_SQL = "delete from appointment where id = ?;";
	private static final String UPDATE_APPOINTMENT_SQL = "UPDATE appointment SET id=?,user_id=?,clinic_id=?,date_time=?,status=?,appointment_type=? WHERE id=?";
	private static final String SELECT_CLINIC_BY_ID = "select id, clinic_name,address,location_name,image,description,opening_hours,opening_days,contact_number from clinic where id = ?";

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
	public AppointmentServlet() {
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
			case "/AppointmentServlet/BookAppointment":
				BookAppointment(request, response);
				break;
			case "/AppointmentServlet/AppointmentClinic":
				GetAppointmentClinic(request, response);
				break;
			case "/AppointmentServlet/PatientAppointments":
				GetPatientAppointments(request, response);
				break;
			case "/AppointmentServlet/ClinicAppointments":
				GetClinicAppointments(request, response);
				break;
			case "/AppointmentServlet/ShowAppointmentDetails":
				ShowAppointmentDetails(request, response);
				break;
			case "/AppointmentServlet/UpdateAppointmentDetails":
				UpdateAppointmentDetails(request, response);
				break;
			case "/AppointmentServlet/DeleteAppointment":
				DeleteAppointment(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}

	}

	private void GetAppointmentClinic(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// get parameter passed in the URL
		String id = request.getParameter("id");
		System.out.print(id);
		Clinic clinicDetails = new Clinic(0, "", "", "", "", "", "", "", "");

		// check if user is logged in
		HttpSession session = request.getSession();
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
		}

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLINIC_BY_ID);) {
			preparedStatement.setString(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("EXECUTED QUERY");
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				System.out.println("FOUND CLINIC");
				int clinic_id = rs.getInt("id");
				String clinic_name = rs.getString("clinic_name");
				String address = rs.getString("address");
				String location_name = rs.getString("location_name");
				String image = rs.getString("image");
				String description = rs.getString("description");
				String opening_hours = rs.getString("opening_hours");
				String opening_days = rs.getString("opening_days");
				String contact_number = rs.getString("contact_number");
				clinicDetails = new Clinic(clinic_id, clinic_name, address, location_name, image, description,
						opening_hours, opening_days, contact_number);
				System.out.println("Details: " + clinicDetails);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		// Step 5: Set existingUser to request and serve up the userEdit form
		request.setAttribute("clinicDetails", clinicDetails);
		request.getRequestDispatcher("/BookAppointment.jsp").forward(request, response);

	}

	private void BookAppointment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// Set User session storage here!
		HttpSession session = request.getSession();

		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}

		response.setContentType("text/html");
		String appointment_type = request.getParameter("appointment_type");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String clinicid = request.getParameter("clinicid");
		String date_time = date + " " + time + ":00";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_db", "root", "password");
			PreparedStatement ps = con.prepareStatement(INSERT_APPOINTMENT_SQL);
			ps.setInt(1, 0);
			ps.setString(2, session.getAttribute("id").toString());
			ps.setString(3, clinicid);
			ps.setString(4, date_time);
			ps.setString(5, "upcoming");
			ps.setString(6, appointment_type);

			int i = ps.executeUpdate();
			System.out.println("SQL query executed");
			if (i > 0) {
				System.out.println("Successfully inserted: " + i);

				// display success page with the data

				response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/BookSuccess.jsp");
			}
		} catch (Exception exception) {
			System.out.println(exception);
			// out.close();
		}

		// doGet(request, response);

	}

	private void GetPatientAppointments(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<AppointmentJoin> appointments = new ArrayList<>();
		HttpSession session = request.getSession();
		String userid = request.getParameter("userid");
		// Redirect them back to home page if they are trying to access different user's
		// appointments

		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		if (session.getAttribute("role").equals("doctor")) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/UserServlet/logout");
			return;
		}

		if (!session.getAttribute("id").toString().equals(userid.toString())) {

			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/PatientHome.jsp");
			return;
		}

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_USERID)) {
			preparedStatement.setString(1, userid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String full_name = rs.getString("full_name");
				String email = rs.getString("email");
				String username = rs.getString("username");
				int user_id = rs.getInt("user_id");
				int clinic_id = rs.getInt("clinic_id");
				String date_time = rs.getString("date_time");
				String status = rs.getString("status");
				String appointment_type = rs.getString("appointment_type");
				String clinic_name = rs.getString("clinic_name");
				String address = rs.getString("address");
				String location_name = rs.getString("location_name");
				String image = rs.getString("image");
				String contact_number = rs.getString("contact_number");

				appointments.add(new AppointmentJoin(id, full_name, email, username, user_id, clinic_id, date_time,
						status, appointment_type, clinic_name, address, location_name, image, contact_number));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the appointments list into the listUsers attribute to be pass
		// to the
		request.setAttribute("listAppointments", appointments);
		request.getRequestDispatcher("/PatientAppointments.jsp").forward(request, response);

	}

	private void GetClinicAppointments(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<AppointmentJoin> appointments = new ArrayList<>();
		Clinic clinicDetails = new Clinic(0, "", "", "", "", "", "", "", "");
		String clinicid = request.getParameter("clinicid");

		HttpSession session = request.getSession();

		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		// check user role
		if (session.getAttribute("role").equals("patient")) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/UserServlet/logout");
			return;
		}

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLINIC_BY_ID);) {
			preparedStatement.setString(1, clinicid);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("EXECUTED QUERY");
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				System.out.println("FOUND CLINIC");
				int clinic_id = rs.getInt("id");
				String clinic_name = rs.getString("clinic_name");
				String address = rs.getString("address");
				String location_name = rs.getString("location_name");
				String image = rs.getString("image");
				String description = rs.getString("description");
				String opening_hours = rs.getString("opening_hours");
				String opening_days = rs.getString("opening_days");
				String contact_number = rs.getString("contact_number");
				clinicDetails = new Clinic(clinic_id, clinic_name, address, location_name, image, description,
						opening_hours, opening_days, contact_number);
				System.out.println("Details: " + clinicDetails);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		try (Connection connection = getConnection();

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_CLINICID)) {
			preparedStatement.setString(1, clinicid);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String full_name = rs.getString("full_name");
				String email = rs.getString("email");
				String username = rs.getString("username");
				int user_id = rs.getInt("user_id");
				int clinic_id = rs.getInt("clinic_id");
				String date_time = rs.getString("date_time");
				String status = rs.getString("status");
				String appointment_type = rs.getString("appointment_type");
				String clinic_name = rs.getString("clinic_name");
				String address = rs.getString("address");
				String location_name = rs.getString("location_name");
				String image = rs.getString("image");
				String contact_number = rs.getString("contact_number");

				appointments.add(new AppointmentJoin(id, full_name, email, username, user_id, clinic_id, date_time,
						status, appointment_type, clinic_name, address, location_name, image, contact_number));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// Step 5.4: Set the appointments list into the listUsers attribute to be pass
		// to the
		request.setAttribute("listAppointments", appointments);
		request.setAttribute("clinicDetails", clinicDetails);
		request.getRequestDispatcher("/DoctorClinicAppt.jsp").forward(request, response);

	}

	private void ShowAppointmentDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();

		// get parameter passed in the URL
		String id = request.getParameter("id");

		Appointment appointment = new Appointment(0, 0, 0, "", "", "");

		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_APPOINTMENT_BY_ID);) {
			preparedStatement.setString(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("EXECUTED QUERY");
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				System.out.println("FOUND CLINIC");
				int appt_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int clinic_id = rs.getInt("clinic_id");
				String date_time = rs.getString("date_time");
				String status = rs.getString("status");
				String appointment_type = rs.getString("appointment_type");
				appointment = new Appointment(appt_id, user_id, clinic_id, date_time, status, appointment_type);
				System.out.print(appointment);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

		// If Patient request
		if (session.getAttribute("role").equals("patient")) {
			request.setAttribute("appointment", appointment);
			request.getRequestDispatcher("/PatientUpdateAppt.jsp").forward(request, response);
		}
		// If Doctor Request
		if (session.getAttribute("role").equals("doctor")) {
			request.setAttribute("appointment", appointment);
			request.getRequestDispatcher("/DoctorUpdateAppointment.jsp").forward(request, response);
		}

	}

	private void UpdateAppointmentDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();

		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		
		// Step 1: Retrieve value from the request
		String apptid = request.getParameter("id");
		String userid = request.getParameter("user_id");
		String clinic_id = request.getParameter("clinicid");
		String date_time = request.getParameter("datetime");
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String status = request.getParameter("status");
		String appointment_type = request.getParameter("appointment_type");
		String update_time = "";
		System.out.print(update_time);

		// use the update time
		if (date.isEmpty() && time.isEmpty()) {
			System.out.print("NO DATE TIME UPDATE");
			update_time = date_time;
			System.out.print(update_time);

		} else {
			// means the user wants to update the date and time
			System.out.print("DATE TIME UPDATE");
			// check both boxes empty or not
			if (date.isEmpty() || time.isEmpty()) {
				System.out.print("DATE TIME EMPTY ERROR");
				session.setAttribute("update_datetime_error", true);

				// if patient update
				if (session.getAttribute("role").equals("patient")) {
					response.sendRedirect(
							"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ShowAppointmentDetails?id="
									+ apptid);

				}

				// if Doctor Update
				if (session.getAttribute("role").equals("doctor")) {
					response.sendRedirect(
							"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ShowAppointmentDetails-Doctors?id="
									+ apptid);

				}

				return;
			}

			// Concat the date and time into one
			update_time = date + " " + time + ":00";
			System.out.print(update_time);
		}

		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_APPOINTMENT_SQL);) {
			statement.setString(1, apptid);
			statement.setString(2, userid);
			statement.setString(3, clinic_id);
			statement.setString(4, update_time);
			statement.setString(5, status);
			statement.setString(6, appointment_type);
			statement.setString(7, apptid);
			int i = statement.executeUpdate();
			// Step 3: redirect back to UserServlet (note: remember to change the url to
			// your project name)
			if (session.getAttribute("role").equals("doctor")) {
				response.sendRedirect(
						"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ClinicAppointments?clinicid="
								+ clinic_id);
			}
			if (session.getAttribute("role").equals("patient")) {
				response.sendRedirect(
						"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="
								+ session.getAttribute("id"));
			}

		} catch (Exception exception) {
			System.out.println(exception);
			// out.close();
		}

	}

	private void DeleteAppointment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession();
		
		// check if user is logged in
		if (session.getAttribute("logged_in") == null) {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
			return;
		}
		
		// Step 1: Retrieve value from the request
		String apptid = request.getParameter("id");
		String clinicid = request.getParameter("clinicid");
		// Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_APPOINTMENT_SQL);) {
			statement.setString(1, apptid);
			int i = statement.executeUpdate();
			// Step 3: redirect back to UserServlet (note: remember to change the url to
			// your project name)

			// if Patient Request
			if (session.getAttribute("role").equals("patient")) {
				response.sendRedirect(
						"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="
								+ session.getAttribute("id"));
			}
			// if Doctor Request
			if (session.getAttribute("role").equals("doctor")) {
				response.sendRedirect(
						"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ClinicAppointments?clinicid="
								+ clinicid);
			}

		} catch (Exception exception) {
			System.out.println(exception);
			// out.close();
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
