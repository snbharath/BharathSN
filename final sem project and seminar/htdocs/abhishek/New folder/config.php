<?php
$mysql_hostname = "mysql1.000webhost.com";
$mysql_user = "a3311905_project";
$mysql_password = "mobile9972680173";
$mysql_database = "a3311905_project";

$bd = mysql_connect($mysql_hostname, $mysql_user, $mysql_password) or die("Opps some thing went wrong");
mysql_select_db($mysql_database, $bd) or die("Opps some thing went wrong");

?>