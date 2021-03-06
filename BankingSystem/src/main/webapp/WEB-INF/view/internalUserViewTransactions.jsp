<%@ page isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Group 12 Bank</title>
		<style>
			#container{
				width: 60%;
				margin: 100px auto;
			}
			
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			body{
				font-family: "Sqauda One" !important;
				background-image: linear-gradient(to right, #0F2027, #203A43, #2C5364);
				
			}
			
			
			h1, h3,p, label{
				color: white !important;
			}



			table{
				color: white !important;
			}
			
			#listDiv, #authSide{
				display: inline-block;
			}
			
			#listDiv{
				width: 70%;
				display: inline;
			}
			
			#authSide{
				width: 100%;
				text-align: center;
				margin: 20px 0 0 0;
			}
			
		
			.my-custom-scrollbar {
				position: relative;
				height: 300px;
				overflow: auto;
			}
			.table-wrapper-scroll-y {
				display: block;
			}
			
		
			
		</style>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
		<link href="https://fonts.googleapis.com/css?family=Squada+One&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link rel="stylesheet" href="//cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="//cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
		
	</head>
	<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<label class="navbar-brand" style="margin: 0 !important;">Group 12 Bank</label>
			  <ul class="navbar-nav">
				<li class=" nav-item"  ><a class="nav-link" href="/internalUser/profile">Profile</a></li>
				  <li class =" active nav-item" ><a class="nav-link" href="/internalUser/transactions">View/Authorize Transactions</a></li>
				  	<li class ="nav-item" ><a class="nav-link" href="/internalUser/Requests">Requests</a></li>
				  <c:if test="${role == 'tier2' || role == 'tier1' }">
				  	<li class =" nav-item"  ><a class="nav-link" href="/internalUser/accountManagement">Account Management</a></li>
				  </c:if>
				  <c:if test="${role == 'admin'}">
				  	<li class =" nav-item"  ><a class="nav-link" href="/internalUser/accountManagement/admin">Account Management</a></li>
				  	<li class ="nav-item" ><a class="nav-link" href="/admin/systemLogs">System Log</a></li>
				  </c:if>
			  </ul>
			    <ul class="navbar-nav ml-auto">
			  <li  class ="nav-item" ><a class="nav-link" href="/employee/logout">Logout</a></li>
		  		</ul>
			</nav>
		<div id="container">
			<h1>Transactions</h1>
			<hr class="divider"/>
			<div id="transactions">
				
				<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<div id="listDiv" class="table-wrapper-scroll-y my-custom-scrollbar">
					<input class="form-control" id="myInput" type="text" placeholder="Search.." style="margin: 0 0 10px 0;">
					<table class="table table-bordered mb-0">
						<thead>
							<tr>
						 <td> <b>Username</b></td>
						  <td><b>From Account</b></td>
						  <td><b>To Account</b></td>
						  <td><b>Request Type</b></td>
						  <td><b>Amount</b></td>
						  <td>Accept/Decline</td>
						  <td>Authorize</td>
						  </tr>
						</thead>
						<tbody id="myTable">
						
						  <c:forEach items="${list}" var="tList">
						  	<tr>
						  	   <td>
						  			${username}
						  		</td>
						  		<td>
						  			${fromAccount}
						  		</td>
						  		<td>
						  		  	${toAcc}
						  	    </td>
						  	    <td>${requestType }</td>
						  	    <td>${amount }</td>
						  		<td>
						  			<label>
							    		<input type="radio" name="${transaction_id}" id="treq" autocomplete="off" value="accept" checked> Accept
								  	</label>
								  	<label>
								    	<input type="radio" name="${transaction_id }" id="treq" autocomplete="off" value="decline"> Decline
								  	</label>
						  		</td>
						  		<td>
						  			<input style="display: block; margin: auto;" type="submit" class="btn btn-info btn-md" value="Authorize"/>
						  		</td>
						  	</tr>
						  </c:forEach>
						</tbody>
					</table>
					
				</div>
				</div>
			</div>
			 
		</div>
	<script>
	$(document).ready(function(){
		  $("#myInput").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#myTable tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
	</body>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>