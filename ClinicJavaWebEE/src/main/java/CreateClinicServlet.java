
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class CreateClinicServlet
 */
@WebServlet("/CreateClinicServlet")
public class CreateClinicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClinicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String clinic_name = request.getParameter("clinic_name");
		String address = request.getParameter("address");
		String location_name = request.getParameter("location_name");
		String image = request.getParameter("image");
		String description = request.getParameter("description");
		String opening_hours = request.getParameter("opening_hours");
		String opening_days = request.getParameter("opening_days");
		String contact_number = request.getParameter("contact_number");
		System.out.println("Clinic post called");
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con = DriverManager.getConnection(
			 "jdbc:mysql://localhost:3306/clinic_db", "root", "password");
			 PreparedStatement ps = con.prepareStatement("INSERT INTO clinic VALUES (?,?,?,?,?,?,?,?,?)");
			 ps.setInt(1, 0);
			 ps.setString(2, clinic_name);
			 ps.setString(3, address);
			 ps.setString(4, location_name);
			 ps.setString(5, image);
			 ps.setString(6, description);
			 ps.setString(7, opening_hours);
			 ps.setString(8, opening_days);
			 ps.setString(9, contact_number);
			 int i = ps.executeUpdate();
				System.out.println("SQL query executed");
			 if (i > 0){
					System.out.println("Successfully inserted");
				response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
				 }
		}
		catch (Exception exception) {
			 System.out.println(exception);
			 out.close();
			}
			doGet(request, response);
	}


}
