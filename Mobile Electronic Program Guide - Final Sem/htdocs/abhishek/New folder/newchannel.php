<!DOCTYPE html>
<html>
	<head>
		<title>MOBILE EPG- Secured Login with php5</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
	
		<header id="head" >
		 <p>MOBILE EPG CHANNEL PAGE</p>
		 
		</header>

<br><br><br>
<p align="center">
<font size="6" color="red" face="century gothic">PLEASE ADD THE NEW CHANNEL</font></p>
		
		<div id="main-wrapper">
		 <div id="login-wrapper">
			 <form method="post" action="channelinput.php">
				 <ul>
					 <li>
						 <label for="channel">Channel : </label>
						 <input type="text" name="channel" id="usn" maxlength="30" required autofocus name="channel" />
					 </li>
					
					 <li>
						 <label for="language">Language : </label>
						 <input type="text" name="language" id="language" maxlength="30" required name="language" />
					 </li>
					 <li class="buttons">
						 <input type="submit" name="submit" value="submit" />

					 </li>
					
				 </ul>
			 </form>
				
			</div>
		</div>
	
	</body>
</html>