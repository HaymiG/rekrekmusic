<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="home.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Sign in to Rekrek</h2>
            <form action="LoginServlet" method="post">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required><br>
                
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required><br>
                
                <button type="submit">Login</button>
            </form>
            <p>Don't have an account? <a href="signup.jsp" class="signup-link">Signup</a></p>
        </div>
    </body>
</html>
