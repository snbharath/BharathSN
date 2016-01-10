<?php

include("config.php");
session_start();
$myusername=$_POST['usn']; 
$mypassword=$_POST['passwd']; 
echo "welcome $myusername and $mypassword";
$sql="SELECT username from admin WHERE username='$myusername' and password='$mypassword'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
$count=mysql_num_rows($result);
echo "count is $count";
if($count==1)
{
header("location: welcome.php");
}
else 
{
echo "Your Login Name or Password is invalid";
sleep(10);
header("location: index11.php");
}

?>
