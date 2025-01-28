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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the username and password from the login form
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
                        

      
        try {
            // Validate user credentials against the database
            if (validateUser(username, password)) {
                // Create a session and store user data (e.g., username)
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                System.out.println("redirecting to the homepage");
                // Redirect to the home page (e.g., home.jsp)
                response.sendRedirect("home.jsp");
                
            } else {
                // If invalid credentials, send error message and stay on login page
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                 
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserHomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validateUser(String username, String password) throws ClassNotFoundException {
        boolean isValidUser = false;
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            // Get the connection
            conn = connection.getConnection();

            //AND password = ? SQL query to check if the user exists with the given username and password
            String query = "SELECT * FROM users WHERE username = ? and password = ? ";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);  // In real applications, use hashed passwords for security

            // Execute the query
            rs = stmt.executeQuery();

            // If a record is found, the user is valid
            if (rs.next()) {
                isValidUser = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isValidUser;
    }
}
