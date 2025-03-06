import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentAppointment")
public class StudentAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String studentName = request.getParameter("student_name");
        String studentEmail = request.getParameter("student_email");
        String counselingType = request.getParameter("counseling_type");
        String appointmentDate = request.getParameter("appointment_date");
        String appointmentTime = request.getParameter("appointment_time");

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO appointments (student_name, student_email, counseling_type, appointment_date, appointment_time) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, studentName);
            ps.setString(2, studentEmail);
            ps.setString(3, counselingType);
            ps.setString(4, appointmentDate);
            ps.setString(5, appointmentTime);

            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("<h3>Appointment booked successfully!</h3>");
            } else {
                out.println("<h3>Error booking appointment.</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Database connection error.</h3>");
        }
    }
}
