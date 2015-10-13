<?php

include("config.php");
session_start();
$myusername=$_POST['userid']; 
$mypassword=$_POST['password']; 
echo "welcome $myusername and $mypassword";
$sql="SELECT usn from usn WHERE usn='$myusername' and bss='$mypassword'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);
$count=mysql_num_rows($result);
echo "count is $count";
if($count==1)
{
@$SESSION['userid']=$myusername;
echo 'hi';
}
else 
{
echo "Your Login Name or Password is invalid";
sleep(5);
header("location: index.html");
}

?>