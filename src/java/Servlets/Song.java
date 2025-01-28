
package Servlets;

public class Song {
    private int id;
    private String title;
    private String artist;
    private String imagePath;
    private String songPath;
    private String uploadDate;

    // Constructor
    public Song(int id, String title, String artist, String imagePath, String songPath, String uploadDate) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.imagePath = imagePath;
        this.songPath = songPath;
        this.uploadDate = uploadDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }
}

