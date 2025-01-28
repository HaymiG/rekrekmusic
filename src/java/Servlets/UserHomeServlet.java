package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/UserHomeServlet")
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session object
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if (username == null) {
            // If username is null, redirect to login page
            response.sendRedirect("login.jsp");
        } else {
            // If the user is logged in, show their home page
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Connection conn = null;

            try {
                // Assuming connection.getConnection() is your method to get the database connection
                conn = connection.getConnection();

                String sql = "SELECT * FROM users WHERE username = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    // Store the user's information as request attributes
                    String name = rs.getString("name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");

                    // Set these attributes to display on the home page (e.g., home.jsp)
                    request.setAttribute("name", name);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("email", email);

                    // Forward the request to home.jsp to display user info
                    request.getRequestDispatcher("/home.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().write("Error fetching user profile.");
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
