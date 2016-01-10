<?php
//the example of inserting data with variable from HTML form
//input.php
mysql_connect("localhost","root","");//database connection
mysql_select_db("mepg");

$cname=$_POST['channel']; 
$prog=$_POST['program']; 
$pid=$_POST['progid']; 
$pdetails=$_POST['progdet']; 
$ptime=$_POST['progtime']; 
if( isset($_POST['program']))
{ 
//inserting data order
$order = "INSERT INTO program
			(pid, pname, cname, pdetails, ptime)
			VALUES
			('$pid','$prog','$cname','$pdetails',
			'$ptime')";

//declare in the order variable
$result = mysql_query($order);
	//order executes
}
if($result){
header("location: success.php");
} else{
	header("location: fail.php");
}
?>