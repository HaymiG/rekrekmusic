

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Music</title>
        <link rel="stylesheet" href="homep.css">
    </head>
    <body>
        <div class="navbar">
        <div class="logo">
            <a href="home.jsp">Rekrek Music Player</a>
        </div>
        <div class="nav-links">
            <a href="home.jsp">Home</a>
            <a href="profile.jsp">Profile</a>
            <a href="index.html">Logout</a>
        </div>
    </div>
         <div class="upload-section">
        <h1>Upload Music</h1>
    <form action="uploadSong" method="POST" enctype="multipart/form-data">
        <label for="title">Song Title:</label>
        <input type="text" id="title" name="title" required><br><br>

        <label for="artist">Artist:</label>
        <input type="text" id="artist" name="artist" required><br><br>

        <label for="image">Image File:</label>
        <input type="file" id="image" name="image" accept="image/*" required><br><br>

        <label for="audio">Audio File:</label>
        <input type="file" id="audio" name="audio" accept="audio/*" required><br><br>
        

        <button type="submit">Upload</button>
        </form>
    </div>
    </body>
</html>
