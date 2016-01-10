<!DOCTYPE html>
<html>
	<head>
		<title>mobile epg  - Secured Login with php5</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
		<header id="head" >
		 <p>MOBILE EPG admin Registration</p>
		 <p><a href="register.php"><span id="register">Register</span></a></p>
		</header>
		
		<div id="main-wrapper">
		 <div id="register-wrapper">
			 <form action="input.php" method="post">
				 <ul>
					 <li>
						 <label for="usn">Username : </label>
						 <input type="text" name="usn" id="usn" maxlength="30" required name="usn"  />
					 </li>
					
					 <li>
						 <label for="passwd">Password : </label>
						 <input type="password" name="passwd" id="passwd" maxlength="30" required name="password" />
					 </li>
						
						<li>
						 <label for="conpasswd">Confirm Password : </label>
						 <input type="password" id="conpasswd" maxlength="30" required name="conpassword" />
					 </li>
					 <li class="buttons">
						 <input type="submit" name="register" value="Register" />
							<input type="button" name="cancel" value="Cancel" onclick="location.href='index11.php'" />
					 </li>
					
				 </ul>
			 </form>
			</div>
		</div>
	
	</body>
</html>