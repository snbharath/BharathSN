<?php
$host="localhost";
$username="root";
$password="";
$database="myproject";

mysql_connect($host,$username,$password) or die("Unable to Connect to DB");
mysql_select_db($database) or die("Unable to Select to DB");
?>