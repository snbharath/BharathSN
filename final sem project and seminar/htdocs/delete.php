<!DOCTYPE html>
<html>
	<head>
		<title>DELETE</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
		
		<script>
			function validate()
			{
				var val=document.getElementById("channel_list");
				var selected = val.options[val.selectedIndex].value;
				
				if(selected=="selectone")
				{
					alert("select a channel to be deleted");
					return false;
				}
			}
		</script>
	</head>
	
	<body>
	
		<header id="head" >
		 <p>DELETE PAGE</p>
		 
		</header>

<br><br><br>
<p align="center">
<font size="6" color="red" face="century gothic">DELETE CHANNEL</font></p>
		
		<div id="main-wrapper">
		 <div id="login-wrapper">
			 <form method="post" action="delete_verify.php" onsubmit="return validate()">
				 <table>
					<tr>
						<td>
						 <?php
						 
							$con=mysql_connect("localhost","root","");
							if(!$con)
							{
								die('Could not connect :' . mysql_error());
							}
							$options="<option value=\"selectone\">Select one</option>";
							mysql_select_db("mepg",$con);
							
							$result=mysql_query("SELECT cname FROM CHANNEL LIMIT 0, 30 ");
							while($row=mysql_fetch_assoc($result))
							{
								$options .="<option value=\"".$row['cname']."\">".$row['cname']."</option>";
								$output[]=$row;
							}
							$select = "<select name='channel_list' id='channel_list'>".$options."</select>";
							echo $select;
							//print(json_encode($output));
							mysql_close($con);
							
						 ?> </td>
						 <td>
						 <input type="submit" name="delete_channel" value="Delete Channel" />
						 </td>
					</tr>
					</table>

			 </form>
				
			</div>
		</div>
	
	</body>
</html>