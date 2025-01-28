package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/getSongs")
public class SongListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("yoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        List<Song> songList = new ArrayList<>();
        String query = "SELECT * FROM songs"; // Adjust this query based on your table structure

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rekrekdb", "root", "Te16@Ha21");
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Loop through the result set to create Song objects
            while (resultSet.next()) {
                Song song = new Song(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("artist"),
                    resultSet.getString("imagePath"),
                    resultSet.getString("songPath"),
                    resultSet.getString("uploadDate")
                );
                songList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert song list to JSON array
        StringBuilder jsonBuilder = new StringBuilder();
        System.out.println(songList);
        jsonBuilder.append("[");

        for (int i = 0; i < songList.size(); i++) {
            Song song = songList.get(i);

            jsonBuilder.append("{")
                        .append("\"id\":").append(song.getId()).append(",")
                        .append("\"title\":\"").append(escapeJson(song.getTitle())).append("\",")
                        .append("\"artist\":\"").append(escapeJson(song.getArtist())).append("\",")
                        .append("\"imagePath\":\"").append(escapeJson(song.getImagePath())).append("\",")
                        .append("\"songPath\":\"").append(escapeJson(song.getSongPath())).append("\",")
                        .append("\"uploadDate\":\"").append(song.getUploadDate()).append("\"")
                        .append("}");

            // Add a comma if it’s not the last element
            if (i < songList.size() - 1) {
                jsonBuilder.append(",");
            }
        }

        jsonBuilder.append("}");

        // Send the JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonBuilder.toString());
    }

    // Helper method to escape JSON special characters
    private String escapeJson(String str) {
        return str.replace("\"", "\\\"");
    }
}
