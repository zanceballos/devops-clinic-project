
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

import java.util.List;
import java.util.ArrayList;

/**
 * Servlet implementation class ClinicServlet
 */
@WebServlet("/ClinicServlet")
public class ClinicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/clinic_db";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	
	private static final String INSERT_CLINIC_SQL = "INSERT INTO clinic"
			+ " (clinic_name,address,location_name,image,description,opening_hours,opening_days,contact_number) VALUES " + " (?, ?, ?,?, ?, ?,?,?);";
	private static final String SELECT_CLINIC_BY_ID = "select clinic_name,address,location_name,image,description,opening_hours,opening_days,contact_number from clinic where id = ?";
	private static final String SELECT_CLINIC_BY_CLINIC_NAME = "select id,clinic_name,address,location_name,image,description,opening_hours,opening_days,contact_number from clinic where clinic_name = ?";
	private static final String SELECT_ALL_CLINIC = "select * from clinic ";
	private static final String DELETE_CLINIC_SQL = "delete from clinic where id = ?;";
	private static final String UPDATE_CLINIC_SQL = "update clinic set clinic_name = ?, address = ? , location_name = ? , image = ?, description= ?, opening_hours= ?, opening_days, contact_number= ? where id = ?;";
       
	
	
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
    public ClinicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/ClinicServlet/delete":
				// deleteUser(request, response);
				break;
			case "/ClinicServlet/edit":
				// showEditForm(request, response);
				break;
			case "/ClinicServlet/update":
				// updateUser(request, response);
				break;
			case"/ClinicServlet/dashboard":
				listClinic(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listClinic(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			List <Clinic> clinic = new ArrayList <>();
			 try (Connection connection = getConnection();
			
			 PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_ALL_CLINIC);) {
			 
			 ResultSet rs = preparedStatement.executeQuery();
			 
			 while (rs.next()) {
			 int id = rs.getInt("id");
			 String clinic_name = rs.getString("clinic_name");
			 String address = rs.getString("address");
			 String location_name = rs.getString("location_name");
			 String image = rs.getString("image");
			 String description = rs.getString("description");
			 String opening_hours = rs.getString("opening_hours");
			 String opening_days = rs.getString("opening_days");
			 String contact_number = rs.getString("contact_number");
			 clinic.add(new Clinic(id,clinic_name,address,location_name,image,description,opening_hours,opening_days,contact_number));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
			// Step 5.4: Set the users list into the listUsers attribute to be pass to the
			request.setAttribute("listClinic", clinic);
			request.getRequestDispatcher("/clinic.jsp").forward(request, response);
			}
	

	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
