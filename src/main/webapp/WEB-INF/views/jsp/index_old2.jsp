<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!--http://adarshspatel.in/simple-calculator-using-servlet/
https://github.com/emalock3/spring-batch-example/blob/master/src/main/resources/schema-h2.sql

http://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_tabs


https://ultimatechange.wordpress.com/2012/11/25/html-servlet-program-using-eclipse/

-->

<!DOCTYPE html>
<html lang="en">
<head>
<title>Web App Demo</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
	<div class="navbar-header">
		<a class="navbar-brand" href="http://localhost:8080/webapp">Webapp demo</a>
	</div>
  </div>
</nav>

<div class="jumbotron">
  <div class="container">
	<h1>${title}</h1>
	<p>
		<c:if test="${not empty msg}">
			Hello ${msg}
		</c:if>

		<c:if test="${empty msg}">
			Welcome Welcome!
		</c:if>
        </p>
        
	</div>
</div>


<ul class="tab">

  <li><a href="#" class="tablinks" onclick="openTab(event, 'Employee')">Employee</a></li>
  <li><a href="#" class="tablinks" onclick="openTab(event, 'Calculator')">Calculator</a></li>
  
</ul>

<div id="Employee" class="tabcontent">
 <div class="container">

  <div class="row">
	<div class="col-md-4">
		<h2>Employee</h2>
	
		
		<table style="width:70%;" border="1">
        <tr>
            <th width="70" height="100" >Client Security code</th>
            <th width="70" height="100" >First Name</th>
            <th width="70" height="100" >Last Name</th>
            <th width="70" height="100" >Phone</th>
            <th width="70" height="100" >Country</th>
            <th width="50" height="100" >Address</th>
            <th width="70" height="100" >Edit</th>
            
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
            
                <td>${employee.securitycode}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.phone}</td>
                <td>${employee.country}</td>
                <td>${employee.address}</td>
                <td>
             
                <a class="Edit" href="http://localhost:8080/webapp/editEmployee?securitycode=${employee.securitycode}">Edit</a>
             </td>
             
             
            </tr>
        </c:forEach>
    </table>
    <br><br>
        <p>
			<a class="btn btn-default" href="http://localhost:8080/webapp/employee" role="button">View details</a>
		</p>
		
	</div> <!--col-md-4-->
   </div> <!--row-->
	<div class="col-md-4">
				<h3>Edit employee form</h3>
		<br><br>
		  <div class="form">	
              <form id = "changeEmployee"  method="POST" action="doEditEmployee">
          <input type="hidden" name="securitycode" value="${employee.securitycode}" />
          <table border="0">
             <tr>
                <td>Code</td>
                <td style="color:red;">${employee.securitycode}</td>
             </tr>
             <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${employee.firstName}" /></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${employee.lastName}" /></td>
             </tr>
             <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" value="${employee.phone}" /></td>
             </tr>
             <tr>
                <td>Country</td>
                <td><input type="text" name="country" value="${employee.country}" /></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${employee.address}" /></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Change" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
          </div> <!--form-->
 
	</div> <!--col-md-4-->
	
	
	<div class="col-md-4">
		<h3>Add Employee form</h3>
		<br><br>
    <div class="form">	
       <form id = "createEmployee" method="POST" action="doCreateEmployee">
          
          <table border="0">
              <tr>
                <td>Code</td>
                <td><input type="text" name="securitycode" value="${employee.securitycode}" required/></td>
             </tr>
             <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${employee.firstName}" /></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${employee.lastName}" /></td>
             </tr>
             <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" value="${employee.phone}" /></td>
             </tr>
             <tr>
                <td>Country</td>
                <td><input type="text" name="country" value="${employee.country}" required/></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${employee.address}" /></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Create" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
       </form>
  </div> <!--form-->
	</div>	 <!--col-md-4-->
		
	<hr>	
 </div>	<!--contaner-->
</div> <!--Employee-->

<div id="calculate" class="tabcontent">
 <div class="form">
  <form action="CalculatorServlet" method="get">
Enter 1st Number <input type="text" name="n1"><br>
Enter 2nd Number <input type="text" name="n2"><br>

<input type="radio" name="r1" value="Add">Add<br>
<input type="radio" name="r2" value="Subtract">Subtract<br>
<input type="radio" name="r3" value="Multiply">Multiply<br>
<input type="radio" name="r4" value="Divide">Divide<br>
<input type="Submit"  id="calculate" value="Submit">
  </form>
 </div> <!--form-->
 
 
  <div class="form">
  <form action="calculate" method="post">
Enter 1st Number <input type="text" name="n1"><br>
Enter 2nd Number <input type="text" name="n2"><br>

<input type="radio" name="r1" value="Add">Add<br>
<input type="radio" name="r2" value="Subtract">Subtract<br>
<input type="radio" name="r3" value="Multiply">Multiply<br>
<input type="radio" name="r4" value="Divide">Divide<br>
<input type="Submit"  id="calculate" value="Submit">
  </form>
 </div> <!--form-->
 
</div> <!--Calculator-->

<footer>
	<p>© Anastassia 2016</p>
</footer>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
<script src="index.js"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

<script type="text/javascript">
    $(document).ready(function(){ 
            $('#calculate').click(function(){
                $.ajax({
                    url:'JsonServlet',
                    type:'post',
                    dataType: 'json',
                    success: function(data) {
                        $('#n1').val(data.n1);
                        $('#n2').val(data.n2);
                    }
                });
            });
    });
</script>

</body>
</html>