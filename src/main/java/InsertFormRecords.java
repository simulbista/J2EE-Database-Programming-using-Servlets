
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertFormRecords
 */
@WebServlet("/InsertFormRecords")
public class InsertFormRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		try {
			// step1 for db - load driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// step2 for db - connect to the driver
			String dburl = "jdbc:mysql://localhost:3306/as2_simul";
			String userName = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(dburl, userName, password);

			// getting the submitted data from the form (Register-Form.html)
			String empNo = request.getParameter("empNo");
			String empName = request.getParameter("empName");
			String department = request.getParameter("department");
			String empJob = request.getParameter("empJob");
			
			
//			Step3: Create query statement
				//data validation (not allow empty values)
			if(!empNo.isEmpty() && !empName.isEmpty() && !department.isEmpty() && !empJob.isEmpty()) {
				PreparedStatement pst = con.prepareStatement("INSERT INTO emp VALUES(?,?,?,?)");
				pst.setString(1, empNo);
				pst.setString(2, empName);
				pst.setString(3, department);
				pst.setString(4, empJob);

				pst.executeUpdate();
				out.println("Records inserted successfully!");	
			}else {
				out.println("Either of the fields must not have an empty value!");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
