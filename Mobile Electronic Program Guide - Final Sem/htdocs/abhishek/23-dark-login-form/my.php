<?php
$usn = $_GET['argument1'];

$con=mysql_connect("localhost","root","");
if(!$con)
{
	die("error");
}
$try=mysql_select_db("campusapp",$con);
if(!$try)
{
	die("try error");
}

$result = mysql_query("INSERT INTO `campusapp`.`usn` (`usn`,`bss`) VALUES ('".$_GET['argument1']."','".$_GET['argument2']."')");
if(!$result)
{
	die("error");
}
if($result)
{
	echo("SUCCESS MACHI!!!!!!!");
}
?>