<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="data1" method="POST" action="logindetails.html" onclick="validatelogindetails()" >
<table><tr>
<td>Email</td><td><input type="text" name="email"></td>
</tr>
<tr>
<td>Password </td><td><input type="password" name="password"></td>
</tr>
<tr>
<td><center><input type="submit" name="submit"></center></td>
</tr>
</table>
</form>
<script type="text/javascript">
function validatelogindetails()
			 {
			   var retvalue1;
			   retvalue1=validateemail();
			   if(retvalue1==false) { return retvalue1;
			   }
           retvalue1=validatePassword();
			   if(retvalue1==false) { return retvalue1;}
			  }			   
function validateemail()
{
var x1=data1.email.value
var atpos1=x1.indexOf("@");
var dotpos1=x1.lastIndexOf(".");
if (atpos1<1 || dotpos1< atpos1+2 || dotpos1 +2>=x1.length)
  {
  alert("Not a valid e-mail address");
  return false;
  }
}
function validatePassword()
{
var password=data1.password.value;
if(password=="")
{
alert("Enter the correct password!!");
return false;
}
}
</script>
</body>
</html>