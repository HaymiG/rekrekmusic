package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/uploadSong")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50,      // 50MB
        maxRequestSize = 1024 * 1024 * 100   // 100MB
)
public class UploadMusicServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        Part imagePart = request.getPart("image");
        Part audioPart = request.getPart("audio");
        
        

        // Validate form inputs
        if (title == null || title.isEmpty() ||
            artist == null || artist.isEmpty() ||
           
            imagePart == null || imagePart.getSize() == 0 ||
            audioPart == null || audioPart.getSize() == 0) {
            response.getWriter().write("Missing title, artist, audio, or image file.");
            return;
        }

        // Save uploaded files
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        String imageFileName = extractFileName(imagePart);
        String audioFileName = extractFileName(audioPart);

        imagePart.write(uploadPath + File.separator + imageFileName);
        audioPart.write(uploadPath + File.separator + audioFileName);

        // Save song details to the database (assumes a SongDAO exists)
        Song song = new Song(0, title, artist, UPLOAD_DIR + "/" + imageFileName, UPLOAD_DIR + "/" + audioFileName, java.time.LocalDate.now().toString());
        boolean success = SongDAO.saveSong(song);

        if (success) {
            response.getWriter().write("Song uploaded successfully!");
        } else {
            response.getWriter().write("Failed to upload song. Please try again.");
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "default.file";
    }
}
