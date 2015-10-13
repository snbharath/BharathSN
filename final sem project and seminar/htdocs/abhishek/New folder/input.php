<?php
//the example of inserting data with variable from HTML form
//input.php
mysql_connect("mysql1.000webhost.com","a3311905_project","mobile9972680173");//database connection
mysql_select_db("a3311905_project");

$usn=$_POST['usn']; 
$pwd=$_POST['passwd']; 
//inserting data order
$order = "INSERT INTO admin
			(username, password)
			VALUES
			('$usn',
			'$pwd')";

//declare in the order variable
$result = mysql_query($order);	//order executes
if($result){
header("location: regsuccess.php");

} else{
header("location: index11.php");
}
?>