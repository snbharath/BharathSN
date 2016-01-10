<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
function showHint()
{
var str=document.getElementById("out").value;
var xmlhttp;

if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("chat").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","insert.jsp?q="+str,true);
xmlhttp.send();
}
</script>
</head>
<body>
<form>
<textarea id="out" rows="5" cols="20"></textarea>
<br/>

<input type="button" value="submit" onclick="showHint();">
</form>
<br/>
<form>
<div id="chat"><textarea rows="10" cols="30"></textarea></div>
</form>
</body>
</html>
