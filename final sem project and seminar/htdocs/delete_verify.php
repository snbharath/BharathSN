<?php

include("config.php");
session_start();

$cname=$_POST['channel_list']; 
echo "welcome $myusername and $mypassword";
$sql="DELETE FROM channel where cname='$cname'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
$count=mysql_num_rows($result);


header("location: success.php");

?>
