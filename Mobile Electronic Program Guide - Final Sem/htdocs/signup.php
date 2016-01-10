<?php 
session_start();

if(!isset($_POST['fname']))
die("Access Denied!!");

$fname=$_POST['fname'];
$lname=$_POST['lname'];
$email=$_POST['email'];
$pass=$_POST['pass'];
$pass2=$_POST['pass2'];
if($pass!=$pass2)
	{  echo "<script type=\"text/javascript\">alert('Password miss match');".
	"window.location='register.php'</script>";
	exit(0);

        } 
$col=$_POST['college'];
$city=$_POST['city'];
$contact=$_POST['contact'];
$f=0;
include("dbconfig.php");
$getqry=mysql_query("select email from reg");
if(mysql_num_rows($getqry)>0)
{
 while($row=mysql_fetch_array($getqry))
	{
		if($row['email']==$email)
			{$f=1;break;}
		
	}

}
if($f==1)
   {
	echo "<script type=\"text/javascript\">alert('Account with this email id is already present.');".
	"window.location='register.php'</script>";
	exit(0);

   } 
$acc=0;$points=0;$level=0;
$uid=generate_uid(15);
$qry="insert into registration(username,password) values('$email','$pass')";
$res=mysql_query($qry);
if($res >0 )
	{
		$sub="IEEESJCE El-Dorado'13 online registration";
		$msg="Hi $email , You have been successfully registered for El-Dorado,as part of IEEE-SJCE Cyberia'13 online events . Click on the link to activate your account www.ieeesjce.com/cyberia13/eldd/act.php?uid=$uid. Thank you,IEEE-SJCE team";
	$mailed=mail($email,$sub,"".$msg,"Cyberia'12");
		
if($mailed>0)
		
{ 
echo "<script type=\"text/javascript\">alert('Registration successful.An activation link has been sent to your email account.Click on the link to activate the account.');".
	"window.location='index.html'</script>";
	exit(0);

//$_SESSION['REG_MSG']="Thank you for registering for online events.An activation mail has been sent to your mail ID.Kindly activate your account . Please check your Spam box also.";
		//header("Location: register.php");
	}

}
function generate_uid($length)
{
  $random= "";
  srand((double)microtime()*1000000);
  $char_list = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  $char_list .= "abcdefghijklmnopqrstuvwxyz";
  $char_list .= "1234567890";
  
  for($i = 0; $i < $length; $i++)  
  {    
     $random .= substr($char_list,(rand()%(strlen($char_list))), 1);  
  }  
  return $random;
} 


?>
