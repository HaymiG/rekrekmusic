<!DOCTYPE html>
<html lang="en">
<head>
    <title>Rekrek Music Player</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="welcome.css">
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="bar">
        <p class="logo">Rekrek</p>
        <div class="search-bar-container">
              <input type="text" class="search-bar" placeholder="search music...">
              <button class="search-bar-submit">
                  <img src="uploads/search-img.png" alt="Search">
              </button>
        </div>

        <div class="nav-links">
            <a href="index.html" class="nav">Home</a>
            <a href="about.jsp" class="nav">About</a>
            <a href="services.jsp" class="nav">Services</a>
            <a href="" class="nav">Contact</a>
            
        </div>
        <div>
            <a href="signup.jsp" class="nav">Signup</a>
            <a href="login.jsp" class="login">Login</a>
        </div>
    </nav>

    <!-- Introduction Text -->
    

    <!-- Interactive Description Section -->
    <section class="interactive-section">
        <h2 class="contact-title">Contact Us</h2>
    <p class="contact-description">
        Have questions or feedback? We'd love to hear from you! Fill out the form below, and we'll get back to you as soon as possible.
    </p>
    <form class="contact-form" action="#" method="post">
        <div class="form-group">
            <label for="name">Your Name</label>
            <input type="text" id="name" name="name" placeholder="Enter your name" required>
        </div>
        <div class="form-group">
            <label for="email">Your Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
        </div>
        <div class="form-group">
            <label for="message">Your Message</label>
            <textarea id="message" name="message" rows="5" placeholder="Enter your message" required></textarea>
        </div>
        <button type="submit" class="submit-button">Send Message</button>
    </form>
        
    </section>

    <!-- Footer -->
    <div class="social-media">
            <a href="https://www.facebook.com" target="_blank">
                <img src="uploads/fb.png" alt="Facebook" title="Follow us on Facebook">
            </a>
            <a href="https://www.twitter.com" target="_blank">
                <img src="uploads/twitter.jpg" alt="Twitter" title="Follow us on Twitter">
            </a>
            <a href="https://www.instagram.com" target="_blank">
                <img src="uploads/insta.png" alt="Instagram" title="Follow us on Instagram">
            </a>
        </div>
    <footer>
        
        <div class="copyright">
            © 2024 Rekrek Music Player
        </div>
    </footer>
</body>
</html>
