
package Servlets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    public static boolean saveSong(Song song) {
        String query = "INSERT INTO songs (title, artist, imagePath, songPath, uploadDate) VALUES (?, ?, ?, ?, ?)";
        try  (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rekrekdb", "root", "Te16@Ha21");
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters in the prepared statement
            preparedStatement.setString(1, song.getTitle());
            preparedStatement.setString(2, song.getArtist());
            preparedStatement.setString(3, song.getImagePath());
            preparedStatement.setString(4, song.getSongPath());
            preparedStatement.setString(5, song.getUploadDate());

            // Execute the query
            return preparedStatement.executeUpdate() > 0; // Returns true if one or more rows are affected
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Return false if an exception occurs
    }
    public static List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rekrekdb", "root", "Te16@Ha21");
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM songs")) {

            while (rs.next()) {
                Song song = new Song(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("imagePath"),
                    rs.getString("songPath"),
                    rs.getDate("uploadDate").toString()
                );
                songs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return songs;
    }
}
