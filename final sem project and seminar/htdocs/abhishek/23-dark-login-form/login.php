<?php

include("config.php");
if($_SERVER["REQUEST_METHOD"] == "POST")
{
$myusername=$_POST['userid']; 
$mypassword=$_POST['password']; 
echo "welcome $myusername and $mypassword";

$sql="SELECT usn FROM usn WHERE usn='$myusername' and bss='$mypassword'";
$result=mysql_query($sql);
$row=mysql_fetch_array($result);


$count=mysql_num_rows($result);

echo "count is $count";
// If result matched $myusername and $mypassword, table row must be 1 row
if($count==1)
{
echo 'hi';
}
else 
{
echo "Your Login Name or Password is invalid";
sleep(5);
header("location: index.html");
}


?>