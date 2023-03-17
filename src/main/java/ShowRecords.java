//Servlet that fetches records from mysql database using jdbc and then displays it in the browser

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowRecords
 */
@WebServlet("/ShowRecords")
public class ShowRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

			// step3 for db - create statement
			Statement stmt = con.createStatement();
			String query = "select * from emp";

			// step4 for db - save resultquery to resultset
			ResultSet rs = stmt.executeQuery(query);

//			display -
			for (int i = 0; i < 93; i++) {
				out.print("-");
				if (i == 92)
					out.print("\n");
			}
			out.println("|" + String.format("%-20s", "EmpNo") + "\t" + String.format("%-20s", "EmpName") + "\t"
					+ String.format("%-20s", "Department") + "\t" + String.format("%-20s", "EmpJob") + "|");

//			display -
			for (int i = 0; i < 93; i++) {
				out.print("-");
				if (i == 92)
					out.print("\n");
			}

			while (rs.next()) {
				out.println("|" + String.format("%-20s", rs.getString("EmpNo")) + "\t"
						+ String.format("%-20s", rs.getString("EmpName")) + "\t"
						+ String.format("%-20s", rs.getString("Department")) + "\t"
						+ String.format("%-20s", rs.getString("EmpJob")) + "|");
			}

//			display -
			for (int i = 0; i < 93; i++) {
				out.print("-");
				if (i == 92)
					out.print("\n");
			}

			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
