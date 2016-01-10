<!DOCTYPE html>
<html>
	<head>
		<title>MOBILE EPG- Secured Login with php5</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
	
		<header id="head" >
		 <p>MOBILE EPG PROGRAM PAGE</p>
		 
		</header>

<br><br><br>
<p align="center">
<font size="6" color="red" face="century gothic">PLEASE ADD THE NEW PROGRAM</font></p>
		<div id="main-wrapper">
		 <div id="login-wrapper1">
			 <form method="post" action="programinput.php">
				 <ul>
					 <li>
						 <label for="channel">Channel name : </label>
						 <input type="text" name="channel" id="channel" maxlength="30" required name="channel"  />
					 </li>
					
					 <li>
						 <label for="programname">Program name : </label>
						 <input type="text" name="program" id="program" maxlength="30" required name="program"  />
					 </li>
			 <li>
						 <label for="program id">Program id : </label>
						 <input type="text" name="progid" id="progid" maxlength="30" required name="progid" />
					 </li>
			 <li>
						 <label for="program details">Program details : </label>
						 <input type="text" name="progdet" id="progdet" maxlength="100" required name="progdet"  />
					 </li>
			 <li>
						 <label for="Program schedule">Program schedule : </label>
						 <input type="text" name="progtime" id="progtime" maxlength="30" required name="progtime"  />
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
