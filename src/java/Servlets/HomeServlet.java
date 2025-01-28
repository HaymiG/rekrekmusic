
package Servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author lule
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch the list of songs from the database
        List<Song> songList = SongDAO.getAllSongs(); // Replace with actual DAO method

        // Pass the list to the JSP
        request.setAttribute("songList", songList);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}


   