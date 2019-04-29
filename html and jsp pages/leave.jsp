<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Application</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%
response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); //HTTP 1.1
//response.setHeader("Pragma","no-cache"); //HTTP 1.0
//response.setHeader("Expires","0"); //proxy server
  if(session.getAttribute("username")==null)
  {
	  response.sendRedirect("employee_login.html");
  }
%>
<div class="alert alert-success" role="alert">
Welcome <label id="nam" style="font-weight:bold"><%=request.getParameter("name")%></label>
&nbsp;&nbsp;&nbsp;Department <label id="dpt" style="font-weight:bold"><%=request.getParameter("dept")%></label>
&nbsp;&nbsp;&nbsp;Designation <label id="dsg" style="font-weight:bold"><%=request.getParameter("desig")%></label>
&nbsp;&nbsp;&nbsp;EmailId <label id="mail" style="font-weight:bold"><%=request.getParameter("email")%></label>
<form action="logout" method="post" style="float:right"><button type="submit" class="btn btn-light">LogOut</button></form>
</div>
<form action="status.jsp" method="post"><button type="submit" class="btn btn-outline-primary">
See Status of Your Applications
</button>
<!--<input type="hidden" name="gmail" value="${email}">-->
<input type="hidden" name="gmail" value="<%=request.getParameter("email")%>">
</form>
<h3>Leave Form</h3>
<form action="leavedata" method="post">
<input type="hidden" name="name" id="name">
<input type="hidden" name="dept" id="dept">
<input type="hidden" name="desig" id="desig">
<input type="hidden" name="email" id="email">
<b>Days</b><br>
<input type="number" name="days" required><br><br>
<b>from</b><br>
<input type="date" name="frm" placeholder="yyyy-mm-dd" required><br><br>
<b>To</b><br>
<input type="date" name="too" placeholder="yyyy-mm-dd" required><br><br>
<b>Type of Leave</b><br>
<select name="leave">
<option value="Study Leave">Study Leave</option>
<option value="Sick Leave">Sick Leave</option>
<option value="Casual Leave">Casual Leave</option>
<option value="Maternity/paternity Leave">Maternity/paternity Leave</option>
<option value="Earned Leave">Earned Leave</option>
</select><br><br>
<b>Reason for leave</b><br>
<textarea name="text" placeholder="enter your text" rows="3" cols="50" required></textarea><br><br>
<button type="submit" class="btn btn-primary">Submit</button>
</form>
</body>

<script>
//alert("Login Successful");
var name=document.getElementById("nam").innerHTML;
var dept=document.getElementById("dpt").innerHTML;
var desig=document.getElementById("dsg").innerHTML;
var email=document.getElementById("mail").innerHTML;
document.getElementById("name").value=name;
document.getElementById("dept").value=dept;
document.getElementById("desig").value=desig;
document.getElementById("email").value=email;
</script>
</html>