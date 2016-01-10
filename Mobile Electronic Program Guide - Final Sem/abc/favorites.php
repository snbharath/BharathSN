<?php
$con=mysql_connect("localhost","root","");
if(!$con)
{
	die('Could not connect :' . mysql_error());
}

mysql_select_db("mepg",$con);

$result=mysql_query("SELECT pid,pname,pdetails,pdate,pstime,petime FROM programtab LIMIT 0, 30 ");
while($row=mysql_fetch_assoc($result))
{
	$output[]=$row;
}

print(json_encode($output));
mysql_close($con);

?>