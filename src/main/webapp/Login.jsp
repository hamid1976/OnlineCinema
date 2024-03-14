<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        <!-- .container { -->
            <!-- background-color: #ffffff; -->
            <!-- border-radius: 4px; -->
            <!-- padding: 40px; -->
            <!-- box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); -->
            <!-- text-align: center; -->
        <!-- } -->
			.container {
			background-color: #f9f9f9;
			border-radius: 8px;
			padding: 40px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
			text-align: center;
			}

			.container h1 {
			margin-bottom: 30px;
			color: #333333;
			}

			.container input[type="text"],
			.container input[type="password"],
			.container input[type="email"],
			.container input[type="tel"] {
			width: 100%;
			padding: 10px;
			font-size: 16px;
			border: 1px solid #cccccc;
			border-radius: 4px;
			box-sizing: border-box;
			color: #333333;
			}

			.container button {
			padding: 10px 20px;
			font-size: 16px;
			background-color: #4CAF50;
			color: #ffffff;
			border: none;
			border-radius: 4px;
			cursor: pointer;
			}

			.container a {
			color: #4CAF50;
			text-decoration: none;
			}
        .container h1 {
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 10px;
        }

        .form-group input[type="text"],
        .form-group input[type="password"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .form-group button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-group {
			color: #4CAF50;
			text-decoration: none;
			font-weight: bold;
		}
		
		.colorful-button {
		  background-color: #FFC107; 
		  color: #FFFFFF; 
		  border: none; 
		  padding: 10px 20px; 
		  font-size: 16px;
		  border-radius: 5px; 
		}

    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
		<!-- <form action="http://localhost:8080/LoginServlet"> -->
		
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
           <div class="form-group">
			  <button type="submit" class="colorful-button">Login</button>
			</div>
			
        </form>
      <p>Don't have an account?</p>
    
        <a href="RegistrationForm.jsp">Create New Account</a>
       
    
    </div>
</body>
</html>
