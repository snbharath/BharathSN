<?php
//the example of inserting data with variable from HTML form
//input.php
mysql_connect("localhost","root","");//database connection
mysql_select_db("mepg");

$cname=$_POST['channel']; 
$lang=$_POST['language']; 
//inserting data order
$order = "INSERT INTO channel
			(cname, language)
			VALUES
			('$cname',
			'$lang')";

//declare in the order variable
$result = mysql_query($order);	//order executes
if($result){
	header("location: success.php");
} else{
	header("location: fail.php");
}
?>