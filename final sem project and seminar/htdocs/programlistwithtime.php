<?php
$con=mysql_connect("localhost","root","");
if(!$con)
{
	die('Could not connect :' . mysql_error());
}
$lang = $_GET['prog'];
$date = $_GET['date'];
$time = $_GET['time'];
mysql_select_db("mepg",$con);

$result=mysql_query("SELECT * FROM programtab WHERE cname= '$lang' && pdate>='$date' && petime>='$time' ORDER BY pdate,pstime LIMIT 0, 30 ");
while($row=mysql_fetch_assoc($result))
{
	$output[]=$row;
}

print(json_encode($output));
mysql_close($con);

?>