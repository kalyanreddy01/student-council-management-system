import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminView")
public class AdminViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM appointments");
            ResultSet rs = ps.executeQuery();

            out.println("<h2>Student Appointments</h2>");
            out.println("<table border='1'><tr><th>Name</th><th>Email</th><th>Type</th><th>Date</th><th>Time</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("student_name") + "</td><td>" + rs.getString("student_email") + "</td><td>" + rs.getString("counseling_type") + "</td><td>" + rs.getString("appointment_date") + "</td><td>" + rs.getString("appointment_time") + "</td></tr>");
            }
            out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error retrieving data.</h3>");
        }
    }
}
