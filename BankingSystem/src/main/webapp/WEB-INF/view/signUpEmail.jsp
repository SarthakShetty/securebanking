<%@ page isELIgnored="false"%>
<html>
	<head>
		<title>Sign Up</title>
		<style>
			body{
				font-family: "Sqauda One" !important;
				background-image: linear-gradient(to right, #0F2027, #203A43, #2C5364);
			}
			
			#container{
				width: 40%;
				margin: 100px auto;
			}
			
			#email{
				margin: 10px auto 50px auto;
			}
			
			#confirmEmail{
				margin: 10px auto 20px auto;
			}
			
			h1, label{
				color: white !important;
			}
			
			.divider{
				border-top: 1px solid #8c8b8b;
				
			}
			
			#form{
				text-align: center;
			}
			
			input[type="text"]{
				width: 60%;
			}
		</style>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
		<link href="https://fonts.googleapis.com/css?family=Squada+One&display=swap" rel="stylesheet">
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	<body>
		<div id="container">
			<div>
				<h1>Sign Up</h1>
			</div>
			<hr class="divider">
			<div id="form">
				<form action="/EmailSent" method="POST">
					<div class="md-form">
						<input type="text" name="firstEmail" placeholder="Email"  id="email" class="form-control"/> 
						<input name="verifiedEmail" type="text" placeholder="Confirm Email"  id="confirmEmail" class="form-control"/>
					</div>
					<hr class="divider">
					<div>
						<input type="submit" class="btn btn-primary btn-md" value="Sign Up" style="margin: 20px auto 0 auto;"/>
					</div>
				</form>
			</div>
		</div>
		
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>