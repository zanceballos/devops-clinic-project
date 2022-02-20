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
 * @author zal07
 *
 */
class JUnitClinicServletTest {

	HttpSession session = mock(HttpSession.class);
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

	private ClinicServlet clinicServlet;
	private CreateClinicServlet createClinicServlet;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		clinicServlet = new ClinicServlet();
		createClinicServlet = new CreateClinicServlet();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetConnection() {
		Connection testConnection = clinicServlet.getConnection();
		assertNotNull(testConnection);
	}

	@Test
	void testCreateClinicAsDoctor() throws ServletException, IOException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		when(request.getServletPath()).thenReturn("/CreateClinic");

		when(request.getParameter("clinic_name")).thenReturn("FDC");
		when(request.getParameter("address")).thenReturn(" 85 Marine Parade Central, #01-668, Singapore 440085");
		when(request.getParameter("location_name")).thenReturn("Marine Parade");
		when(request.getParameter("image"))
				.thenReturn("https://www.onecaremedical.com.sg/wp-content/uploads/2019/07/OCTM.png");
		when(request.getParameter("descrption")).thenReturn("It works");
		when(request.getParameter("opening_hours")).thenReturn("9am to 7pm");
		when(request.getParameter("opening_days")).thenReturn("Monday to Saturday");
		when(request.getParameter("contact_number")).thenReturn("85249137");

		createClinicServlet.doPost(request, response);

		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		assertTrue(captor.getValue().contains("/ClinicServlet/dashboard"));
	}

	@Test
	void testShowClinicAsDoctor() throws ServletException, IOException {
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		when(request.getParameter("id")).thenReturn("50");
		when(request.getServletPath()).thenReturn("/ClinicServlet/dashboard");
		when(request.getRequestDispatcher("/clinic.jsp")).thenReturn(requestDispatcher);

		clinicServlet.doPost(request, response);
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());

		assertEquals("/clinic.jsp", requestDispatcherString.getValue());
	}

	@Test
	void testShowClinicAsPatient() throws ServletException, IOException {
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		when(request.getParameter("id")).thenReturn("2");
		when(request.getServletPath()).thenReturn("/ClinicServlet/all-clinics");

		
		when(request.getRequestDispatcher("/ClinicsListing.jsp")).thenReturn(requestDispatcher);

		clinicServlet.doPost(request, response);
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());

		assertEquals("/ClinicsListing.jsp", requestDispatcherString.getValue());
	}

	@Test
	void testShowClinicDetailsAsPatient() throws ServletException, IOException {
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		
		
		when(request.getParameter("id")).thenReturn("2");
		when(request.getServletPath()).thenReturn("/ClinicServlet/clinic-details");
		when(request.getRequestDispatcher("/ClinicDetails.jsp")).thenReturn(requestDispatcher);
		
		clinicServlet.doPost(request, response);
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());
		
		assertEquals("/ClinicDetails.jsp", requestDispatcherString.getValue());
	}
		
	

	@Test
	void testUpdateClinicAsDoctor() throws ServletException, IOException {

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("doctor");

		when(request.getParameter("id")).thenReturn("50");

		when(request.getServletPath()).thenReturn("/ClinicJavaWebEE/CreateClinic.jsp");

		when(request.getParameter("clinic_name")).thenReturn("FDC");
		when(request.getParameter("address")).thenReturn(" 85 Marine Parade Central, #01-668, Singapore 440085");
		when(request.getParameter("location_name")).thenReturn("Marine Parade");
		when(request.getParameter("image"))
				.thenReturn("https://www.onecaremedical.com.sg/wp-content/uploads/2019/07/OCTM.png");
		when(request.getParameter("descrption")).thenReturn("It works");
		when(request.getParameter("opening_hours")).thenReturn("9am to 7pm");
		when(request.getParameter("opening_days")).thenReturn("Monday to Saturday");
		when(request.getParameter("contact_number")).thenReturn("85249137");

		when(request.getServletPath()).thenReturn("/ClinicServlet/update");
		clinicServlet.doPost(request, response);

		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());

		assertTrue(
				captor.getValue().toString().contains("http://localhost:8090/ClinicJavaWebEE/ClinicServlet/dashboard"));
	}

}
