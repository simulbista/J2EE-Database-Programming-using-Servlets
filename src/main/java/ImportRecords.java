//Servlet that inserts 10 records into mysql database using jdbc

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImportRecords
 */
@WebServlet("/ImportRecords")
public class ImportRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
//			Step1: Load driver
			Class.forName("com.mysql.cj.jdbc.Driver");

//			Step2: Connect the driver
			String dburl = "jdbc:mysql://localhost:3306/as2_simul";
			String username = "root";
			String password = "root";
			Connection con = DriverManager.getConnection(dburl, username, password);

//			Step3: Create query statement
			String records[][] = { { "MC001", "Sim", "CS", "JAVA dev" }, { "MC002", "Josh", "Mechanical", "Fixer" },
					{ "MC003", "Abhi", "Culinary", "Chef" }, { "MC004", "Carmen", "Insurance", "Manager" },
					{ "MC005", "Bert", "Construction", "Manager" }, { "MC006", "Carlos", "Construction", "Fixer" },
					{ "MC007", "Paula", "SEO", "Specialist" }, { "MC008", "Ellie", "Fashion", "Designer" },
					{ "MC009", "Cory", "Management", "Superintendent" },
					{ "MC010", "John", "IT", "Field Technician" } };

			PreparedStatement pst = con.prepareStatement("INSERT INTO emp VALUES(?,?,?,?)");

			for (int i = 0; i < records.length; i++) {
				pst.setString(1, records[i][0]);
				pst.setString(2, records[i][1]);
				pst.setString(3, records[i][2]);
				pst.setString(4, records[i][3]);
				pst.executeUpdate();
			}
			System.out.println("Records inserted successfully!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
