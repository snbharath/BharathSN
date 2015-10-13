<?php

$con=mysql_connect("http://192.168.133.1","root","");
if(!$con)
{
	die("error");
}
mysql_select_db("handycampusapp",$con);

$result = mysql_query("SELECT * FROM usn LIMIT 0, 30 ");

while($row = mysql_fetch_assoc($result))
{
	$output[]=$row;
}

print(json_encode($output));

mysql_close($con);

?>