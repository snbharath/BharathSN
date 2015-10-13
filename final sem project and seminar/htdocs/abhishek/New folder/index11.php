<!DOCTYPE html>
<html>
	<head>
		<title>MOBILE EPG- Secured Login with php5</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
	
		<header id="head" >
		 <p>MOBILE EPG admin Login</p>
		 
		</header>
		
		<div id="main-wrapper">
		 <div id="login-wrapper">
			 <form method="post" action="login.php">
				 <ul>
					 <li>
						 <label for="usn">Username : </label>
						 <input type="text" name="usn" id="usn" maxlength="30" required autofocus name="username" />
					 </li>
					
					 <li>
						 <label for="passwd">Password : </label>
						 <input type="password" name="passwd" id="passwd" maxlength="30" required name="password" />
					 </li>
					 <li class="buttons">
						 <input type="submit" name="login" value="Log me in" />
							<input type="button" name="register" value="Register" onclick="location.href='register.php'" />
					 </li>
					
				 </ul>
			 </form>
				
			</div>
		</div>
	
	</body>
</html>