<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${songTitle} - Rekrek Music Player</title>
</head>
<body>
    <h1>${songTitle}</h1>
    <p><b>Artist:</b> ${artistName}</p>
    <p><b>Uploaded on:</b> ${uploadDate}</p>
    <audio controls autoplay>
        <source src="${songPath}" type="audio/mp3">
        Your browser does not support the audio element.
    </audio>
</body>
</html>
