<?php
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Welcome Page </title>
		<link rel="stylesheet" type="text/css" href="style.css" />
</head>

<body>
	<header id="head" >
		 <p>MOBILE EPG</p>
		 
		</header>
<br>
<br><br><br><br>
<p align="center"><font face="century gothic" color="red" size="6" align="center"><u>WELCOME ,CHOOSE YOUR ACTION</u></font></p>
<br>
<br>
<br><br><br>
<li class="buttons">
			
<input type="button" name="program" value="ADD PROGRAM"onclick="location.href='newprogramwithdate.php'"  />
<input type="button" name="modify" value="MODIFY" />
<input type="button" name="addchannel" value="ADD CHANNEL" onclick="location.href='newchannel.php'" />
					 

</li>

<br><br><br><br><br><br>
 <li class="buttons">
						 <input type="button" name="signout" value="SIGN OUT" onclick="location.href='index11.php'"  /></li>
</body>
</html>
