package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import static org.apache.tomcat.jakartaee.commons.lang3.StringEscapeUtils.escapeJson;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get the logged-in username from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // If the user is not logged in, return an error message in JSON
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"User not logged in.\"}");
            return;
        }

        // Create a connection to the database
        Connection conn = null;
        try {
            conn = connection.getConnection();
            // SQL query to fetch user data by username
            String sql = "SELECT name, email,username FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Build JSON response manually
                        StringBuilder jsonBuilder = new StringBuilder();
                        jsonBuilder.append("{")
                                   .append("\"name\":\"").append(escapeJson(rs.getString("name"))).append("\",")
                                   .append("\"email\":\"").append(escapeJson(rs.getString("email"))).append("\",")
                                   .append("\"username\":\"").append(escapeJson(rs.getString("username"))).append("\"")
                                   .append("}");

                        // Write the JSON string to the response output stream
                        PrintWriter out = response.getWriter();
                        out.print(jsonBuilder.toString());
                        out.flush();
                    } else {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        response.getWriter().write("{\"error\":\"User not found.\"}");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Database connection failed.\"}");
        }
    }
}
