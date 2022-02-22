import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */

/**
 * @author carlo
 *
 */
class JUnitReviewServletTest {

	//mock server side classes using Mockito
	HttpSession session = mock(HttpSession.class);
	HttpServletRequest request = mock(HttpServletRequest.class);
	HttpServletResponse response = mock(HttpServletResponse.class);
	RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
	
	private ReviewServlet reviewServlet;
	private String testing_id;
	private String testing_clinic_id;
	private ArrayList<Review> reviews = new ArrayList<>();
	
	// SQL statement to get reviews
	private static final String SELECT_ALL_REVIEWS = "SELECT * FROM review";
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
		reviewServlet = new ReviewServlet();
		
		//establish connection
		try(Connection connection = getConnection()){
			
//			create statement using connection object
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REVIEWS);
			
//			execute query
			ResultSet rs = ps.executeQuery();

//			process ResultSet object
			while(rs.next()) {
				int review_id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int clinic_id = rs.getInt("clinic_id");
				String review = rs.getString("review");
				String review_title = rs.getString("review_title");
				int rating_score = rs.getInt("rating_score");
				
				reviews.add(new Review(review_id, user_id, clinic_id, review, rating_score, review_title));
				
			}
			
			
		} catch(SQLException e){
			System.out.print("Error: " + e.getMessage());
		}
		
//		Get values and set testing IDs
		if(!reviews.isEmpty()) {
			testing_id = Integer.toString(reviews.get(0).id);
			testing_clinic_id = Integer.toString(reviews.get(0).clinic_id);
		} else {
			testing_id = "50";
			testing_clinic_id = "2";
		}
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testGetConnection() {
		Connection testConnection = reviewServlet.getConnection();
		assertNotNull(testConnection);
	}

	@Test
	void testReviewClinic() throws ServletException, IOException{
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		//mock session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("id")).thenReturn("2");
	
		//mock session for servlet path
		when(request.getServletPath()).thenReturn("/ReviewServlet/ReviewClinic");
		
		//set form values for the sql string
		when(request.getParameter("clinicid")).thenReturn("2");
		when(request.getParameter("review_title")).thenReturn("Nice Clinic!");
		when(request.getParameter("review")).thenReturn("Great staff!");
		when(request.getParameter("rating_score")).thenReturn("4");
		
		//call review clinic function
		reviewServlet.doPost(request, response);
		
		//retrieve and verify response
		Mockito.verify(response).sendRedirect(captor.capture());
		
		//assert results
		if(captor.getValue().contains("ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id=")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	@Test
	void testListClinicReviews() throws ServletException, IOException {
		//test to get reviews by clinic id
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		//set user session
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("role")).thenReturn("patient");
		
		//clinic id used
		when(request.getParameter("id")).thenReturn("2");
		
		//set servlet path
		when(request.getServletPath()).thenReturn("/ReviewServlet/ListClinicReviews");
		
		//set dispatcher
		when(request.getRequestDispatcher("/ClinicReviews.jsp")).thenReturn(requestDispatcher);
		
		//run servlet function and get dispatcher
		reviewServlet.doPost(request, response);
		Mockito.verify(request).getRequestDispatcher(captor.capture());
		
		//assert results
		assertEquals("/ClinicReviews.jsp", captor.getValue());
		
	}
	
	@Test
	void testUpdateReview() throws ServletException, IOException{
		//test to update review
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		//mock session of the user
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("id")).thenReturn(2);	
	
		//mock session for servlet path
		when(request.getServletPath()).thenReturn("/ReviewServlet/update");
		
		//set form values for the sql string
		System.out.print(testing_id + " " + testing_clinic_id);
		when(request.getParameter("id")).thenReturn(testing_id);
		when(request.getParameter("user_id")).thenReturn("2");
		when(request.getParameter("clinic_id")).thenReturn(testing_clinic_id);
		when(request.getParameter("review")).thenReturn("Lovely staff!");
		when(request.getParameter("rating_score")).thenReturn("5");
		when(request.getParameter("review_title")).thenReturn("Amazing Clinic!");
		
		//call review clinic function
		reviewServlet.doPost(request, response);
		
		//retrieve and verify response
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());
		
		//assert results
		if(captor.getValue().contains("ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id=")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	@Test
	void testDeleteReview() throws ServletException, IOException {
		//test to delete review
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		
		//set user session
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("logged_in")).thenReturn(true);
		when(request.getSession().getAttribute("id")).thenReturn("2");

		//mock session for servlet path
		when(request.getServletPath()).thenReturn("/ReviewServlet/delete");
	
		//set request params
		when(request.getParameter("id")).thenReturn(testing_id);
		when(request.getParameter("clinicid")).thenReturn(testing_clinic_id);
		
		//call delete function
		reviewServlet.doPost(request, response);
		
		//verify response
		Mockito.verify(response).sendRedirect(captor.capture());
		System.out.println(captor.getValue());
		
		//assert results
		if(captor.getValue().contains("ClinicJavaWebEE/ReviewServlet/ListClinicReviews?id=")) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
		
		
	}

}
