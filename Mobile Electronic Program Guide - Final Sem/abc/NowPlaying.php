<?php
$con=mysql_connect("localhost","root","");
if(!$con)
{
	die('Could not connect :' . mysql_error());
}
$date = $_GET['progdate'];
$time = $_GET['progtime'];
mysql_select_db("mepg",$con);

$result=mysql_query("SELECT pid,pname,cname,pdetails,pdate,pstime,petime FROM programtab WHERE pdate= '$date' && pstime<='$time' && petime>='$time' ORDER BY pdate,pstime LIMIT 0, 30 ");
while($row=mysql_fetch_assoc($result))
{
	$output[]=$row;
}

print(json_encode($output));
mysql_close($con);

?>