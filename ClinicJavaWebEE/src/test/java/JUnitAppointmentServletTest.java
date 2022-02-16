import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Step 2: Mock the Servlet Class here!
		appointmentServlet = new AppointmentServlet();
		// This will be used to call the servlet function in order to ensure converage
		// across each functions
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
		Connection testConnection = appointmentServlet.getConnection();
		assertNotNull(testConnection);
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
		assertEquals("http://localhost:8090/ClinicJavaWebEE/BookSuccess.jsp", captor.getValue());
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
		assertFalse(captor.getValue().equals("http://localhost:8090/ClinicJavaWebEE/BookSuccess.jsp"));

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
		assertEquals("http://localhost:8090/ClinicJavaWebEE/login.jsp", captor.getValue());
	}

	@Test
	void testdoPostGetPatientAppointments() throws ServletException, IOException {

		// Test for get selected appointment clinic
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
	void testGetClinicAppointmentsAsDoctor() throws ServletException, IOException {
		// Test for get selected appointment clinic
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// expecting parameter of the clinic id
		when(request.getParameter("clinicid")).thenReturn("2");

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
	void testShowAppointmentsDetailsAsPatient() throws ServletException, IOException {
		// Test for get selected appointment clinic
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn("50");

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
		// Test for get selected appointment clinic
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn("50");

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
		// Test for Booking Appointment
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		String empty = "";

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn("50");

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn("2");
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
				.contains("http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="));
	}

	@Test
	void testUpdateAppointmentsDetailsAsDoctor() throws ServletException, IOException {

		// Test for Booking Appointment
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn("50");

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn("2");
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
				.contains("http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ClinicAppointments?clinicid="));

	}

	@Test
	void testUpdateDateTimeAppointmentsAsPatient() throws ServletException, IOException {
		// Test for Booking Appointment with date and time null
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		when(request.getSession().getAttribute("update_datetime_error")).thenReturn(true);

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn("50");

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn("2");
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
				.contains("http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="));
	}

	@Test
	void testUpdateDateTimeAppointmentAsDoctor() throws ServletException, IOException {
		// Test for Booking Appointment with date and time null
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");
		when(request.getSession().getAttribute("update_datetime_error")).thenReturn(true);

		// expecting parameter of the appointment id
		when(request.getParameter("id")).thenReturn("50");

		// form for update
		when(request.getParameter("user_id")).thenReturn("3");
		when(request.getParameter("clinicid")).thenReturn("2");
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
		assertTrue(captor.getValue().toString().contains(
				"http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ShowAppointmentDetails?id="));
	}

	@Test
	void testDeleteAppointmentsDetailsAsPatient() throws ServletException, IOException {
		// Test for Booking Appointment with date and time null
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		// set request parameter
		when(request.getParameter("id")).thenReturn("37");
		when(request.getParameter("clinicid")).thenReturn("2");

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
				.contains("http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/PatientAppointments?userid="));

	}

	@Test
	void testDeleteAppointmentsDetailsAsDoctor() throws ServletException, IOException {
		// Test for Booking Appointment with date and time null
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		// set request parameter
		when(request.getParameter("id")).thenReturn("37");
		when(request.getParameter("clinicid")).thenReturn("2");

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
				.contains("http://localhost:8090/ClinicJavaWebEE/AppointmentServlet/ClinicAppointments?clinicid="));

	}

	@Test
	void testDeleteAppointmentAsGuest() throws ServletException, IOException {
		// Test for Booking Appointment with date and time null
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		// setting the session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(null);

		// set request parameter
		when(request.getParameter("id")).thenReturn("37");
		when(request.getParameter("clinicid")).thenReturn("2");

		// set servlet path
		// setting the servlet path
		when(request.getServletPath()).thenReturn("/AppointmentServlet/DeleteAppointment");

		// call the function
		appointmentServlet.doPost(request, response);

		// Get the response and verify
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		// assert results
		assertTrue(captor.getValue().toString().contains("http://localhost:8090/ClinicJavaWebEE/login.jsp"));
	}

}
