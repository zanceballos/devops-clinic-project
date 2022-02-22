import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

class JUnitUserServlet {

	HttpSession session = mock(HttpSession.class);
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

	private ArrayList<User> users = new ArrayList<>();
	private String testing_user_id;
	private String testing_last_id;
	private UserServlet userServlet;
	private RegisterServlet registerServlet;
	
	private static final String SELECT_ALL_USERS = "select * from users ";

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

	@BeforeEach
	void setUp() throws Exception {
		userServlet = new UserServlet();
		registerServlet = new RegisterServlet();


		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

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
				users.add(new User(user_id, username, role, contact_number, email, password, full_name)) ;
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
		// Get the values and assign to testing IDs!

		if (users.isEmpty() == false) {
			testing_user_id = Integer.toString(users.get(0).id);
			testing_last_id = Integer.toString(users.get(users.size()-1 ).id);
			System.out.println("Last id:" + testing_last_id);

		} else {
			// set default testing id
			testing_user_id = "1";

		}

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetConnection() {
		Connection testConnection = userServlet.getConnection();
		assertNotNull(testConnection);
	}
	
	@Test
	void register_user() throws ServletException, IOException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("someTest");
		when(request.getParameter("role")).thenReturn("patient");
		when(request.getParameter("full_name")).thenReturn("someTest");
		when(request.getParameter("email")).thenReturn("someTest@mail.com");
		when(request.getParameter("contact_number")).thenReturn("999");
		when(request.getParameter("password")).thenReturn("test123");
	
		registerServlet.doPost(request, response);
		Mockito.verify(response).sendRedirect(captor.capture());

		assertEquals("/ClinicJavaWebEE/login.jsp", captor.getValue());
	}
	
	@Test
	void delete_user() throws ServletException, IOException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("id")).thenReturn(testing_last_id);
		when(request.getServletPath()).thenReturn("/UserServlet/delete");
		
		userServlet.doPost(request, response);
		Mockito.verify(response).sendRedirect(captor.capture());

		assertEquals("/ClinicJavaWebEE/UserServlet/logout", captor.getValue());
	}
	
	@Test
	void login_user() throws ServletException, IOException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		
		when(request.getParameter("username")).thenReturn("test");
		when(request.getParameter("password")).thenReturn("test123");
		when(request.getServletPath())
				.thenReturn("/UserServlet/login");
	

		userServlet.doPost(request, response);
		Mockito.verify(response).sendRedirect(captor.capture());

		assertEquals("/ClinicJavaWebEE/UserServlet/home", captor.getValue());
	}

	@Test
	void testShowPatientDetails() throws ServletException, IOException {
		ArgumentCaptor<String> requestDispatcherString = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		when(request.getSession().getAttribute("id")).thenReturn(testing_user_id);
		
		when(request.getParameter("id")).thenReturn(testing_user_id);
		System.out.println("User : " + testing_user_id);
		when(request.getServletPath())
				.thenReturn("/UserServlet/account-details");
		when(request.getRequestDispatcher("/AccountPage.jsp")).thenReturn(requestDispatcher);

		userServlet.doPost(request, response);
		Mockito.verify(request).getRequestDispatcher(requestDispatcherString.capture());

		assertEquals("/AccountPage.jsp", requestDispatcherString.getValue());
	}

	@Test
	void testUpdateAccount() throws ServletException, IOException {

		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");

		when(request.getParameter("id")).thenReturn(testing_user_id);

		when(request.getServletPath()).thenReturn("/UserServlet/updateAccount");

		when(request.getParameter("full_name")).thenReturn("Test Full Name New");
		when(request.getParameter("email")).thenReturn("newtest@gmail.com");
		when(request.getParameter("contact_number")).thenReturn("12345678");
		userServlet.doPost(request, response);

		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println("Update account: " + captor.getValue());

		assertTrue(captor.getValue().contains("/ClinicJavaWebEE/UserServlet/account-details"));
	}
	
	@Test
	void testLogoutAccount() throws ServletException, IOException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		when(request.getSession()).thenReturn(session);
		
		when(request.getServletPath()).thenReturn("/UserServlet/logout");
		
		userServlet.doPost(request, response);
	
		Mockito.verify(response).sendRedirect(captor.capture());
		
		assertTrue(captor.getValue().contains("/ClinicJavaWebEE"));
	}
	
	@Test
	void testHomePage() throws ServletException, IOException {
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		
		when(request.getServletPath()).thenReturn("/UserServlet/home");
		
		when(request.getRequestDispatcher("/PatientHome.jsp")).thenReturn(requestDispatcher);
		
		userServlet.doPost(request, response);
		
		Mockito.verify(request).getRequestDispatcher(captor.capture());
		
		
		assertTrue(captor.getValue().contains("/PatientHome.jsp"));
	}

}
