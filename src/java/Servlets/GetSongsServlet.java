package Servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;


@WebServlet("/GetSongsServlet")
public class GetSongsServlet extends HttpServlet {
 
    // Database credentials
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           System.out.println("abennniii");
        // Set the response type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Create a connection to the database
         
        Connection conn = null;  
        try {
            conn = connection.getConnection();
            
            // SQL query to fetch song data
            String sql = "SELECT title, artist, imagePath, songPath, uploadDate FROM songs";
            try (Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql)) {
               
                // Use StringBuilder to build JSON manually
                StringBuilder jsonBuilder = new StringBuilder();
                jsonBuilder.append("[");

                boolean first = true; // To handle commas between JSON objects
                while (rs.next()) {
                    if (!first) {
                        jsonBuilder.append(",");
                    } else {
                        first = false;
                    }

                    jsonBuilder.append("{")
                               .append("\"title\":\"").append(escapeJson(rs.getString("title"))).append("\",")
                               .append("\"artist\":\"").append(escapeJson(rs.getString("artist"))).append("\",")
                               .append("\"imagePath\":\"").append(escapeJson(rs.getString("imagePath"))).append("\",")
                               .append("\"songPath\":\"").append(escapeJson(rs.getString("songPath"))).append("\",")
                               .append("\"uploadDate\":\"").append(escapeJson(rs.getString("uploadDate"))).append("\"")
                               .append("}");
                }

                jsonBuilder.append("]");
                System.out.println(response);
                // Write the JSON string to the response output stream
                PrintWriter out = response.getWriter();
                out.print(jsonBuilder.toString());
                out.flush();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Databaseconnection failed.\"}");
        }
    }

    // Helper method to escape special characters in JSON
    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\b", "\\b")
                    .replace("\f", "\\f")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
}
