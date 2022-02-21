import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 */

/**
 * @author Izzan
 *
 */
class JUnitAppointmentServletTest {

	// Step 1: Mockito mocks for server side classes!
	HttpSession session = mock(HttpSession.class);
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

	// Arrange
	private AppointmentServlet appointmentServlet;
	private ArrayList<Appointment> appointments = new ArrayList<>();
	private String testing_id;
	private String testing_clinic_id;
	// SQL statement to get all the appointments
	private static final String SELECT_ALL_APPOINTMENT = "SELECT * FROM appointment";
	private String jdbcURL = "jdbc:mysql://localhost:3306/clinic_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

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
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Step 2: Mock the Servlet Class here!
		appointmentServlet = new AppointmentServlet();
		// This will be used to call the servlet function in order to ensure converage
		// across each functions

		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_APPOINTMENT);) {

			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("EXECUTED QUERY");
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				int appt_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int clinic_id = rs.getInt("clinic_id");
				String date_time = rs.getString("date_time");
				String status = rs.getString("status");
				String appointment_type = rs.getString("appointment_type");
				appointments.add(new Appointment(appt_id, user_id, clinic_id, date_time, status, appointment_type));
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		// Get the values and assign to testing IDs!
		System.out.println(appointments);
		if (appointments.isEmpty() == false) {
			testing_id = Integer.toString(appointments.get(0).id);
			testing_clinic_id = Integer.toString(appointments.get(0).id);
		} else {
			// set default testing id
			testing_id = "50";
			testing_clinic_id = "2";
		}
	}

	// Step 3: Write your Test Cases!
	// Make sure you cover all functions!
	// Note that our CRUD functions are through private function
	// So we can only access it via a call on the Initiated Servlet above! (See the
	// void setUp())

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link AppointmentServlet#getConnection()}.
	 */
	@Test
	void testGetConnection() {
		Connection connection = null;
		Connection testConnection = appointmentServlet.getConnection();
		connection = testConnection;
		assertNotNull(connection);
	}

	@Test
	void testBookAppointment() throws ServletException, IOException {
		// Test for Booking Appointment
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("id")).thenReturn(3);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/BookAppointment");

		// the form values for SQL set string
		when(request.getParameter("appointment_type")).thenReturn("consultation");
		when(request.getParameter("date")).thenReturn("2022-03-02");
		when(request.getParameter("time")).thenReturn("16:00");
		when(request.getParameter("clinicid")).thenReturn("3");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertEquals("/ClinicJavaWebEE/BookSuccess.jsp", captor.getValue());
	}

	@Test
	void testBookAppointmentasGuest() throws ServletException, IOException {

		// Test for Booking Appointment as Guest
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/BookAppointment");
		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println("Guest attempt Book:" + captor.getValue());

		// should not fall into the success page
		assertFalse(captor.getValue().equals("/ClinicJavaWebEE/BookSuccess.jsp"));

	}

	@Test
	void testGetAppointmentClinic() throws ServletException, IOException {

		// Test for get selected appointment clinic
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/AppointmentClinic");

		// Parameter of the selected clinic id
		when(request.getParameter("id")).thenReturn("3");

		when(request.getRequestDispatcher("/BookAppointment.jsp")).thenReturn(requestDispatcher);

		appointmentServlet.doPost(request, response);

		// Get the request get dispatcher
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());
		System.out.println(requestDispatcherString.getValue());

		// assert results
		assertEquals("/BookAppointment.jsp", requestDispatcherString.getValue());

	}

	@Test
	void testGetAppointmentClinicAsGuest() throws ServletException, IOException {
		// Test for Booking Appointment as Guest
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/AppointmentClinic");

		when(request.getRequestDispatcher("/BookAppointment.jsp")).thenReturn(requestDispatcher);
		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println("Guest attempt Book:" + captor.getValue());

		// should not fall into the booking page
		assertEquals("/ClinicJavaWebEE/login.jsp", captor.getValue());
	}

	@Test
	void testGetPatientAppointments() throws ServletException, IOException {

		// Test for get patient appointments
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		when(request.getSession().getAttribute("id")).thenReturn("3");

		// expecting parameters
		when(request.getParameter("userid")).thenReturn("3");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/PatientAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/PatientAppointments.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the request get dispatcher
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());
		System.out.println(requestDispatcherString.getValue());

		// assert results
		assertEquals("/PatientAppointments.jsp", requestDispatcherString.getValue());

	}

	@Test
	void testGetPatientAppointmentsAsGuest() throws ServletException, IOException {

		// Test for get patient appointments as a guest user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);
		when(request.getSession().getAttribute("role")).thenReturn(null);
		when(request.getSession().getAttribute("id")).thenReturn(null);

		// expecting parameters
		when(request.getParameter("userid")).thenReturn("");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/PatientAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/PatientAppointments.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());

		// assert that servlet should send a redirect
		assertEquals("/ClinicJavaWebEE/login.jsp", captor.getValue());
	}

	@Test
	void testGetPatientAppointmentsAsDoctor() throws ServletException, IOException {

		// Test for get patient appointments by user id as a doctor
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");
		when(request.getSession().getAttribute("id")).thenReturn("2");

		// expecting parameters
		when(request.getParameter("userid")).thenReturn("2");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/PatientAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/PatientAppointments.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());

		// assert that servlet should send a redirect to logout
		assertEquals("/ClinicJavaWebEE/UserServlet/logout", captor.getValue());
	}

	@Test
	void testGetOtherPatientAppointments() throws ServletException, IOException {

		// Test for patient trying to get another user's appointments listing
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		when(request.getSession().getAttribute("id")).thenReturn("3");

		// expecting parameters - so user is trying to get another user's appointments
		// lists
		when(request.getParameter("userid")).thenReturn("1");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/PatientAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/PatientAppointments.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());

		// assert that servlet should send a redirect
		assertEquals("/ClinicJavaWebEE/PatientHome.jsp", captor.getValue());
	}

	@Test
	void testGetClinicAppointmentsAsDoctor() throws ServletException, IOException {
		// Test for get Clinic Appointments as doctor
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// expecting parameter of the clinic id
		when(request.getParameter("clinicid")).thenReturn("3");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/ClinicAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/DoctorClinicAppt.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the request get dispatcher
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());
		System.out.println(requestDispatcherString.getValue());

		// assert results
		assertEquals("/DoctorClinicAppt.jsp", requestDispatcherString.getValue());

	}

	@Test
	void testGetClinicAppointmentsAsPatient() throws ServletException, IOException {
		// Test for get clinic appointments as the patient
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// expecting parameter of the clinic id
		when(request.getParameter("clinicid")).thenReturn("3");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/ClinicAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/DoctorClinicAppt.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());

		// assert that servlet should send a redirect
		assertEquals("/ClinicJavaWebEE/UserServlet/logout", captor.getValue());

	}

	@Test
	void testGetClinicAppointmentsAsGuest() throws ServletException, IOException {
		// Test for get selected appointment clinic
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);
		when(request.getSession().getAttribute("role")).thenReturn("");

		// expecting parameter of the clinic id
		when(request.getParameter("clinicid")).thenReturn("3");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/ClinicAppointments");

		// set the request dispatcher
		when(request.getRequestDispatcher("/DoctorClinicAppt.jsp")).thenReturn(requestDispatcher);
		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());

		// assert that servlet should send a redirect
		assertEquals("/ClinicJavaWebEE/login.jsp", captor.getValue());

	}

	@Test
	void testShowAppointmentsDetailsAsGuest() throws ServletException, IOException {
		// Test for show appointment details as a guest user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);
		when(request.getSession().getAttribute("role")).thenReturn("");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/ShowAppointmentDetails");

		// set the request dispatcher
		when(request.getRequestDispatcher("/PatientUpdateAppt.jsp")).thenReturn(requestDispatcher);

		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());

		// assert that servlet should send a redirect
		assertEquals("/ClinicJavaWebEE/login.jsp", captor.getValue());

	}

	@Test
	void testShowAppointmentsDetailsAsPatient() throws ServletException, IOException {
		// Test for show appointment details as a patient user
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/ShowAppointmentDetails");

		// set the request dispatcher
		when(request.getRequestDispatcher("/PatientUpdateAppt.jsp")).thenReturn(requestDispatcher);

		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the request get dispatcher
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());
		System.out.println(requestDispatcherString.getValue());

		// assert results
		assertEquals("/PatientUpdateAppt.jsp", requestDispatcherString.getValue());

	}

	@Test
	void testShowAppointmentsDetailsAsDoctor() throws ServletException, IOException {
		// Test for show appointment details as a doctor user
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/ShowAppointmentDetails");

		// set the request dispatcher
		when(request.getRequestDispatcher("/DoctorUpdateAppointment.jsp")).thenReturn(requestDispatcher);

		// run the function from servlet
		appointmentServlet.doPost(request, response);

		// Get the request get dispatcher
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());
		System.out.println(requestDispatcherString.getValue());

		// assert results
		assertEquals("/DoctorUpdateAppointment.jsp", requestDispatcherString.getValue());
	}

	@Test
	void testUpdateAppointmentsDetailsAsPatient() throws ServletException, IOException {
		// Test for update appointment as patient user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		String empty = "";

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);
		when(request.getParameter("date_time")).thenReturn("2022-06-15 18:00:00");
		when(request.getParameter("date")).thenReturn(empty);
		when(request.getParameter("time")).thenReturn(empty);
		when(request.getParameter("status")).thenReturn("completed");
		when(request.getParameter("appointment_type")).thenReturn("vaccination");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/UpdateAppointmentDetails");

		// call the function
		appointmentServlet.doPost(request, response);

		// set the patient role for the final if else
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString()
				.contains("/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="));
	}

	@Test
	void testUpdateAppointmentsDetailsAsDoctor() throws ServletException, IOException {

		// Test for update appointment as doctor user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);
		when(request.getParameter("date_time")).thenReturn("2022-06-15 18:00:00");
		when(request.getParameter("date")).thenReturn("2022-06-17");
		when(request.getParameter("time")).thenReturn("19:00");
		when(request.getParameter("status")).thenReturn("completed");
		when(request.getParameter("appointment_type")).thenReturn("vaccination");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/UpdateAppointmentDetails");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString()
				.contains("/ClinicJavaWebEE/AppointmentServlet/ClinicAppointments?clinicid="));

	}

	@Test
	void testUpdateDateTimeAppointmentsAsPatient() throws ServletException, IOException {
		// Test for update Appointment with date and time null
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		when(request.getSession().getAttribute("update_datetime_error")).thenReturn(true);

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);
		when(request.getParameter("date_time")).thenReturn("2022-06-15 18:00:00");
		// when Date & Time case is null
		when(request.getParameter("date")).thenReturn("2022-07-15");
		when(request.getParameter("time")).thenReturn("08:00");
		when(request.getParameter("status")).thenReturn("completed");
		when(request.getParameter("appointment_type")).thenReturn("vaccination");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/UpdateAppointmentDetails");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println("Update date and time by: " + captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString()
				.contains("/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="));
	}

	@Test
	void testUpdateDateTimeAppointmentAsDoctor() throws ServletException, IOException {
		// Test for update Appointment with date and time null as doctor user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");
		when(request.getSession().getAttribute("update_datetime_error")).thenReturn(true);

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn(testing_id);

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);
		when(request.getParameter("date_time")).thenReturn("2022-06-15 18:00:00");
		// when Date & Time case is null
		when(request.getParameter("date")).thenReturn("2022-07-15");
		when(request.getParameter("time")).thenReturn("");
		when(request.getParameter("status")).thenReturn("completed");
		when(request.getParameter("appointment_type")).thenReturn("vaccination");

		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/UpdateAppointmentDetails");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString()
				.contains("/ClinicJavaWebEE/AppointmentServlet/ShowAppointmentDetails?id="));
	}

	@Test
	void testDeleteAppointmentsDetailsAsPatient() throws ServletException, IOException {
		// Test for update Appointment with date and time null as patient user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// set request parameter
		when(request.getParameter("id")).thenReturn(testing_id);
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);

		// set servlet path
		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/DeleteAppointment");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString()
				.contains("/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="));

	}

	@Test
	void testDeleteAppointmentsDetailsAsDoctor() throws ServletException, IOException {
		// Test for Delete Appointment as a doctor user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// set request parameter
		when(request.getParameter("id")).thenReturn(testing_id);
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);

		// set servlet path
		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/DeleteAppointment");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString()
				.contains("/ClinicJavaWebEE/AppointmentServlet/ClinicAppointments?clinicid="));

	}

	@Test
	void testDeleteAppointmentAsGuest() throws ServletException, IOException {
		// Test for delete appointment as a guest user
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);

		// set request parameter
		when(request.getParameter("id")).thenReturn(testing_id);
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);

		// set servlet path
		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/DeleteAppointment");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString().contains("/ClinicJavaWebEE/login.jsp"));
	}

}
