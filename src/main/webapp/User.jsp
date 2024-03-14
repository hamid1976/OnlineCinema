<!DOCTYPE html>
<html>
<head>
    <title>Welcome (User)</title>
    <style>
        body {
            background-image: url('Images/userpic.jpg'); /* Replace 'your-background-image.jpg' with the actual image file path */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        h1 {
            font-size: 32px;
            text-align: center;
        }

        h2 {
            font-size: 24px;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin: 10px 0;
        }

        a {
            text-decoration: none;
            color: #0077b6;
        }

        form {
            position: absolute;
            top: 20px; /* Adjust as needed to set the distance from the top */
            right: 20px; /* Adjust as needed to set the distance from the right */
        }

        input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #0077b6;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Welcome to Movie Theater (User)</h1>
    <form id="showMoviesForm" action="ShowMoviesUser" method="get">
        <!-- You can add any additional elements you need for this form here -->
    </form>
    <form action="Login.jsp" method="post">
        <input type="submit" value="Logout">
    </form>

    <script>
        // Automatically submit the form when the page loads
        window.onload = function() {
            document.getElementById("showMoviesForm").submit();
        };
    </script>
 
</body>
</html>
