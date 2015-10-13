<!DOCTYPE html>
<html>
	<head>
		<title>MOBILE EPG- Secured Login with php5</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
		
		<script>
			function validate()
			{
				var val=document.getElementById("channel_list");
				var selected = val.options[val.selectedIndex].value;
				
				if(selected=="selectone")
				{
					alert("select one channel name");
					return false;
				}
			}
		</script>
		
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
			 <form name="f1" method="post" action="programinputwithtime.php" onsubmit="return validate()">
				 <ul>
					 <li>
						 <label for="channel">Channel name : </label>
						 <?php
						 
							$con=mysql_connect("localhost","root","");
							if(!$con)
							{
								die('Could not connect :' . mysql_error());
							}
							$options="<option value=\"selectone\">Select one</option>";
							mysql_select_db("mepg",$con);
							
							$result=mysql_query("SELECT distinct cname FROM CHANNEL LIMIT 0, 30 ");
							while($row=mysql_fetch_assoc($result))
							{
								$options .="<option value=\"".$row['cname']."\">".$row['cname']."</option>";
								//$output[]=$row;
							}
							$select = "<select name='channel_list' id='channel_list'>".$options."</select>";
							echo $select;
							//print(json_encode($output));
							mysql_close($con);
							
						 ?>
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
						 <label for="Program Date">Program Date : </label>
						 <input type="date" name="progdate" id="progdate" maxlength="30" required name="progdate"  />
					 </li>
					 			 <li>
						 <label for="Program Time">Program Start Time : </label>
						 <input type="time" name="progstime" id="progstime" maxlength="30" required name="progstime"  />
					 </li>
					 			 <li>
						 <label for="Program End Time">Program End Time : </label>
						 <input type="time" name="progetime" id="progetime" maxlength="30" required name="progetime"  />
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
