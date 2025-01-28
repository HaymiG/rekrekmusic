<!DOCTYPE html>
<html>
    <head>
        <title>Signup-to-Rekrek</title>
        <link rel="stylesheet" href="home.css">
    </head>
    <body>
        <div class="signup-container">
            <h3>Signup to Rekrek</h3>
            <form action="SignupServlet" method="post">
                <!-- Name Field -->
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" placeholder="Enter your full name" required><br>
                
                <!-- Email Field -->
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required><br>
                
                <!-- Username Field -->
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Choose a username" required><br>
                
                <!-- Password Field -->
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Create a password" required><br>
                
                <!-- Signup Button -->
                <button class="submit" type="submit">Signup</button>
            </form>
            <p>Already have an account? <a href="login.jsp" class="login-link">Login</a></p>
        </div>
    </body>
</html>
