<!DOCTYPE html>
<html>
	<head>
		<title>MOBILE EPG- Secured Login with php5</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
		
		<script>
			function validate()
			{
				var val=document.getElementById("language");
				var selected = val.options[val.selectedIndex].value;
				
				if(selected=="selectone")
				{
					alert("select the language of the channel");
					return false;
				}
			}
		</script>
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
			 <form method="post" action="channelinput.php" onsubmit="return validate()">
				 <ul>
					 <li>
						 <label for="channel">Channel : </label>
						 <input type="text" name="channel" id="usn" maxlength="30" required autofocus name="channel" />
					 </li>
					
					 <li>
						 <label for="language">Language : </label>
						 <?php
						 
							$con=mysql_connect("localhost","root","");
							if(!$con)
							{
								die('Could not connect :' . mysql_error());
							}
							$options="<option value=\"selectone\">Select one</option>";
							mysql_select_db("mepg",$con);
							
							$result=mysql_query("SELECT distinct language FROM CHANNEL LIMIT 0, 30 ");
							while($row=mysql_fetch_assoc($result))
							{
								$options .="<option value=\"".$row['language']."\">".$row['language']."</option>";
								$output[]=$row;
							}
							$select = "<select name='language' id='language'>".$options."</select>";
							echo $select;
							//print(json_encode($output));
							mysql_close($con);
							
						 ?>
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