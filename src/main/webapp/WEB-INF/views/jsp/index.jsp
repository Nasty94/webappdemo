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
<link rel="stylesheet" type="text/css" href="../resources/core/css/style.css">
</head>

<style>
body {font-family: "Lato", sans-serif;}

ul.tab {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
}

/* Float the list items side by side */
ul.tab li {float: left;}

/* Style the links inside the list items */
ul.tab li a {
    display: inline-block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of links on hover */
ul.tab li a:hover {
    background-color: #ddd;
}

/* Create an active/current tablink class */
ul.tab li a:focus, .active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    display: yes;
    padding: 6px 12px;
    border: 1px solid #ccc;
    border-top: none;
}

.formC { position:relative;left:80px;}
.footer { position:relative;left:80px;}


span {
  position:absolute;
  width: 500px;
}
</style>

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

<div id="Employee" class="tabcontent">
 <div class="container">

  <div class="row">
	<div class="col-md-4">
		<h2>Employee</h2>
	    <p>Sorted by boss id</p>
		
		<table style="width:70%;" border="1">
        <tr>
            <th width="70" height="100" >ID</th>
			<th width="70" height="100" >Security code</th>
            <th width="70" height="100" >First Name</th>
            <th width="70" height="100" >Last Name</th>
            <th width="70" height="100" >Phone</th>
            <th width="70" height="100" >Country</th>
            <th width="50" height="100" >Address</th>
            <th width="50" height="100" >Boss</th>
            <th width="70" height="100" >Edit</th>
            
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
            
                <td>${employee.id}</td>
				<td>${employee.securitycode}</td>
                <td>${employee.firstName}</td>
                <td>${employee.lastName}</td>
                <td>${employee.phone}</td>
                <td>${employee.country}</td>
                <td>${employee.address}</td>
                <td>${employee.boss}</td>
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
<div class="row">
<div class="col-md-4">
				<h3>Edit employee form</h3>
		<br><br>
		  <div class="form">	
              <form id = "changeEmployee"  method="POST" action="doEditEmployee">
          <input type="hidden" name="id" value="${employee.id}" />
          <input type="hidden" name="securitycode" value="${employee.securitycode}" />
          <table border="0">
		    <tr>
                <td>Id</td>
                <td style="color:red;">${employee.id}</td>
             </tr>
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
                <td>Boss</td>
                <td><input type="text" name="boss" value="${employee.boss}" /></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Change" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
        <p>Message: <span id="messageEmployeeEdit">${messageEmployeeEdit}</span></p>
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
                <td><input type="text" name="firstname" value="${employee.firstName}" required/></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${employee.lastName}" required/></td>
             </tr>
             <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" value="${employee.phone}" required/></td>
             </tr>
             <tr>
                <td>Country</td>
                <td><input type="text" name="country" value="${employee.country}" required/></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${employee.address}" required/></td>
             </tr>
             <tr>
                <td>Boss</td>
                <td><input type="text" name="boss" value="${employee.boss}" required/></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Create" />
                    <a href="${pageContext.request.contextPath}/employee">Cancel</a>
                </td>
             </tr>
          </table>
          
        <p>Message: <span id="messageEmployeeAdd">${messageEmployeeAdd}</span></p>
       </form>
  </div> <!--form-->
	</div>	 <!--col-md-4-->
</div>
	
	<!--BOSS-->

  <div class="row">
	<div class="col-md-4">
		<h2>Boss</h2>
	    <p>Sorted by last name</p>
		
		<table style="width:70%;" border="1">
        <tr>
            <th width="70" height="100" >ID</th>
			<th width="70" height="100" >Security code</th>
            <th width="70" height="100" >First Name</th>
            <th width="70" height="100" >Last Name</th>
            <th width="70" height="100" >Phone</th>
            <th width="70" height="100" >Country</th>
            <th width="50" height="100" >Address</th>
            <th width="70" height="100" >Edit</th>
            
        </tr>
        <c:forEach items="${bosses}" var="boss">
            <tr>
            
                <td>${boss.id}</td>
				<td>${boss.securitycode}</td>
                <td>${boss.firstName}</td>
                <td>${boss.lastName}</td>
                <td>${boss.phone}</td>
                <td>${boss.country}</td>
                <td>${boss.address}</td>
                <td>
             
                <a class="Edit" href="http://localhost:8080/webapp/editBoss?securitycode=${boss.securitycode}">Edit</a>
             </td>
             
             
            </tr>
        </c:forEach>
    </table>
    <br><br>
        <p>
			<a class="btn btn-default" href="http://localhost:8080/webapp/boss" role="button">View details</a>
		</p>
		
	</div> <!--col-md-4-->
	
   </div> <!--row-->
<div class="row">
<div class="col-md-4">
				<h3>Edit boss form</h3>
		<br><br>
		  <div class="form">	
              <form id = "changeBoss"  method="POST" action="doEditBoss">
          <input type="hidden" name="id" value="${boss.id}" />
          <input type="hidden" name="securitycode" value="${boss.securitycode}" />
          <table border="0">
		     <tr>
                <td>Id</td>
                <td style="color:red;">${boss.id}</td>
             </tr>
             <tr>
                <td>Code</td>
                <td style="color:red;">${boss.securitycode}</td>
             </tr>
             <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${boss.firstName}" /></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${boss.lastName}" /></td>
             </tr>
             <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" value="${boss.phone}" /></td>
             </tr>
             <tr>
                <td>Country</td>
                <td><input type="text" name="country" value="${boss.country}" /></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${boss.address}" /></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Change" />
                    <a href="${pageContext.request.contextPath}/boss">Cancel</a>
                </td>
             </tr>
          </table>
       <p>Message: <span id="messageBossEdit">${messageBossEdit}</span></p>
       </form>
          </div> <!--form-->
 
	</div> <!--col-md-4-->
	
	
	<div class="col-md-4">
		<h3>Add boss form</h3>
		<br><br>
    <div class="form">	
       <form id = "createBoss" method="POST" action="doCreateBoss">
          
          <table border="0">
		     
              <tr>
                <td>Code</td>
                <td><input type="text" name="securitycode" value="${boss.securitycode}" required/></td>
             </tr>
             <tr>
                <td>First Name</td>
                <td><input type="text" name="firstname" value="${boss.firstName}" required/></td>
             </tr>
             <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastname" value="${boss.lastName}" required/></td>
             </tr>
             <tr>
                <td>Phone</td>
                <td><input type="text" name="phone" value="${boss.phone}" required/></td>
             </tr>
             <tr>
                <td>Country</td>
                <td><input type="text" name="country" value="${boss.country}" required/></td>
             </tr>
             <tr>
                <td>Address</td>
                <td><input type="text" name="address" value="${boss.address}" required/></td>
             </tr>
             <tr>
             <br><br>
                <td colspan = "2">
                    <input type="submit" value="Create" />
                    <a href="${pageContext.request.contextPath}/boss">Cancel</a>
                </td>
             </tr>
          </table>
         <p>Message: <span id="messageBossAdd">${messageBossAdd}</span></p>
       </form>
  </div> <!--form-->
	</div>	 <!--col-md-4-->
</div>
	<hr>	
 </div>	<!--contaner-->
</div> <!--Employee-->

<div id="Calculator" class="tabcontent">
 <div class="row">
 <div class="col-md-4">

	<hr>
 <div class="formC">
  <form style="width: 500px" action="CalculatorServlet" method="get"  target="_self">
  <h3>GET</h3>
  	<hr>
Enter 1st Number <input type="text" name="n1"><br>
Enter 2nd Number <input type="text" name="n2"><br>

<input type="radio" name="r1" value="Add">Add<br>
<input type="radio" name="r2" value="Subtract">Subtract<br>
<input type="radio" name="r3" value="Multiply">Multiply<br>
<input type="radio" name="r4" value="Divide">Divide<br>
<input type="Submit"  id="calculate" value="Submit">

<p>Result: <span id="result">${result}</span></p>

  </form>
 </div> <!--form-->
 </div>
</div>
<div class="row">
<div class="col-md-4">

  <div class="formC">
  <form style="width: 500px" action="CalculatorRESTServiceClient" method="post">
    <h3>POST</h3>
  	<hr>
  <p>Click Submit to get calculation results from file in json format.</p>
      <input type="Submit"  id="calculate" value="Submit">
   <p>Calculation: <span id="calculation">${calculation}</span></p>
   <p>Result: <span id="resultPost">${resultPost}</span></p>
  </form>
 </div> <!--form-->
 </div>
 </div>
</div> <!--Calculator-->

<footer class="footer">
	<p>© Anastassia Ivanova 2016</p>
</footer>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />
<script src="/resources/core/js/index.js"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>


</body>
</html>