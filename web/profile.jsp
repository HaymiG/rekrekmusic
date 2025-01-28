<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <script>
        // Function to fetch profile data from the servlet
        function loadUserProfile() {
            console.log("Fetching profile...");
            fetch("ProfileServlet")
                .then(response => response.json())
                .then(data => {
                    console.log(data);
                    const profileInfo = document.getElementById('profile-info');
                    profileInfo.innerHTML = ''; // Clear profile info before appending new items

                    if (data.error) {
                        profileInfo.innerHTML = "<p>" + data.error + "</p>";
                    } else {
                        const name = data.name || "Name not available";
                        const email = data.email || "Email not available";
                        const username = data.username || "Username not available";

                        const profileContent = `
                            <h2>Profile</h2>
                            <p><b>Name:</b> ${name} </p>
                            <p><b>Email:</b> ${email}</p>
                            <p><b>Username:</b> ${username}</p>
                        `;

                        profileInfo.innerHTML = profileContent;
                    }
                })
                .catch(error => {
                    console.error("Error fetching profile data: ", error);
                    document.getElementById("profile-info").innerHTML = "<p>There was an error loading your profile.</p>";
                });
        }

        window.onload = loadUserProfile;  // Automatically load the user profile when the page loads
    </script>
</head>
<body>
    <div id="profile-info">
        <!-- Initially empty, will be populated by the JavaScript after fetching data -->
    </div>
</body>
</html>
