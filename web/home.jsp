<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rekrek Music Player</title>
    <link rel="stylesheet" href="homep.css">
</head>
<body>
    <!-- Navigation Bar -->
    <div class="navbar">
        <div class="logo">
            <a href="home.jsp">Rekrek Music Player</a>
        </div>
        <div class="nav-links">
            <a href="upload.jsp">Upload Music</a>
            <a href="profile.jsp">profile</a>
            <a href="login.jsp">Logout</a> <!-- 'logout' is handled by a servlet -->
        </div>
    </div>

    <!-- Music Player Section -->
    <div class="music-player">
        <div class="song-details">
            <img id="songImage" src="./uploads/music.jpg" alt="Song Image" class="song-image">
            <div class="song-info">
                <h2 id="songTitle">Welcome</h2>
                <p><b>Artist:</b> <span id="artistName">unknown</span></p>
                <p><b>Uploaded on:</b> <span id="uploadDate">2024-12-31</span></p>
                <audio id="audioPlayer" controls>
                   <source id="audioSource" src="./musics/uploads/vlog-music-beat-trailer-showreel-promo-background-intro-theme-274290.mp3" type="audio/mp3">
               </audio>

                
            </div>
        </div>
    </div>
    

    <!-- Music Library Section -->
    <div class="music-library">
        <h3>Music Library</h3>
        <div id="songList" class="song-list">
            <!-- Songs will be dynamically loaded here -->
        </div>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>&copy; 2024 Rekrek Music Player | All rights reserved</p>
    </div>

    <script>
        // Fetch song data from the server
     function handleSongs() {
    console.log("Fetching songs...");
    fetch("GetSongsServlet") // Correct URL for the servlet
        .then(response => response.json())
        .then(data => {
            console.log(data); // Check the data object
           
            const songList = document.getElementById('songList');
            songList.innerHTML = ''; // Clear the song list before appending new items

            // Iterate through each song in the data
            data.forEach(song => {
                console.log(song.artist); // Check if artist value is correct

                // Create the container div for the song
                const songItem = document.createElement('div');
                songItem.className = 'song-item';

                // Create image element for the song
                const songImage = document.createElement('img');
                songImage.src = song.imagePath ; // Default image if missing
                songImage.alt = 'Song Image';

                // Create div for song info
                const songInfo = document.createElement('div');
                songInfo.className = 'song-item-info';

                // Create song title element
                const songTitle = document.createElement('h4');
                songTitle.textContent = song.title; // Set song title

                // Create artist paragraph element
                const songArtist = document.createElement('p');
                songArtist.innerHTML = `<b>Artist:</b> ${song.artist}`; // Set artist name

                // Create upload date paragraph element
                const uploadDate = document.createElement('p');
                uploadDate.innerHTML = `<b>Uploaded on:</b> ${song.uploadDate}`; // Set upload date

                // Create Play button
                const playButton = document.createElement('button');
                playButton.className = 'play-btn';
                playButton.textContent = 'Play';
                playButton.onclick = function() {
                    playSong("./musics/"+song.songPath, song.imagePath, song.title, song.artist, song.uploadDate);
                };

                // Create Download button with a link
                const downloadLink = document.createElement('a');
                downloadLink.href = "./musics/"+song.songPath;
                downloadLink.download = song.title; // Optional: Set a download name

                const downloadButton = document.createElement('button');
                downloadButton.className = 'download-btn';
                downloadButton.textContent = 'Download';

                // Append everything together
                downloadLink.appendChild(downloadButton); // Append the download button inside the link
                songInfo.appendChild(songTitle); // Append the title to song info
                songInfo.appendChild(songArtist); // Append the artist info to song info
                songInfo.appendChild(uploadDate); // Append upload date to song info
                songInfo.appendChild(playButton); // Append play button to song info

                songItem.appendChild(songImage); // Append image to song item
                songItem.appendChild(songInfo); // Append song info to song item

                songList.appendChild(songItem); // Append the full song item to the song list
            });
        })
        .catch(error => {
            console.error('Error fetching songs:', error);
        });
}


        // Play the selected song
        function playSong(songPath, imagePath, title, artist, uploadDate) {
            console.log(songPath);
            document.getElementById('audioPlayer').src = songPath;
            document.getElementById('songImage').src = imagePath;
            document.getElementById('songTitle').textContent = title;
            document.getElementById('artistName').textContent = artist;
            document.getElementById('uploadDate').textContent = uploadDate;
            document.getElementById('audioPlayer').play();
            isPlaying = true;
            document.getElementById('playPauseBtn').textContent = 'Pause'; // Change button text
        }


        window.onload = handleSongs; // Automatically load songs when the page loads
    </script>
</body>
</html>
