<?php
//the example of inserting data with variable from HTML form
//input.php
mysql_connect("mysql1.000webhost.com","a3311905_project","mobile9972680173");//database connection
mysql_select_db("a3311905_project");

$cname=$_POST['channel']; 
$prog=$_POST['program']; 
$pid=$_POST['progid']; 
$pdetails=$_POST['progdet']; 
$pdate=$_POST['progdate']; 
$pstime=$_POST['progstime'];
$petime=$_POST['progetime'];
//ho "$pdate";
//ho "$pstime";
if( isset($_POST['program']))
{ 
//inserting data order
$order = "INSERT INTO programtab
			(pid, pname, cname, pdetails, pdate,pstime,petime)
			VALUES
			('$pid','$prog','$cname','$pdetails',
			'$pdate','$pstime','$petime')";

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