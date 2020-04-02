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
				margin: 20px auto;
				text-align: center;
			}
			
			
			#OTPKey{
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
				width: 50%;
				margin: auto;
			}
			
			input[type="text"], input[type='password']{
				width: 60%;
			}
			
			#bottom{
				margin: 50px auto 0 auto;
			}
		</style>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/themes/smoothness/jquery-ui.css" />
		<link href="https://fonts.googleapis.com/css?family=Squada+One&display=swap" rel="stylesheet">
		<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <label class="navbar-brand" style="margin: 0 !important;">Group 12 Bank</label>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="nav navbar-nav ml-auto">
		      <li class="nav-item"><a  class="nav-link" href="/"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		    </ul>
		   </div>
    	</nav>
		<div id="container">
			<div style="width: 50%; margin: auto;">
				<h1>Create New Account - Information</h1>
			</div>
			
			<div id="form">
				<hr class="divider">
		<form action="/confirmationAccount" method="POST">

				  <div class="form-group row">
				    <label for="firstName" class="col-sm-5 col-form-label">First Name:</label>
				    <div class="col-sm-7">
				      <input type="text" name ="firstName"  class="form-control" id="firstName"  placeholder="First Name" >
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="lastName" class="col-sm-5 col-form-label">Last Name:</label>
				    <div class="col-sm-7">
				      <input type="text" name = "lastName" class="form-control" id="lastName" placeholder="Last Name">

				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="username" class="col-sm-5 col-form-label">Username:</label>
				    <div class="col-sm-7">

				      <input type="text" name = "username" class="form-control" id="username" placeholder="Username">

				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="password" class="col-sm-5 col-form-label">Password:</label>
				    <div class="col-sm-7">

				      <input type="password" name="password" class="form-control" id="password" placeholder="Password">

				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="cPassword" class="col-sm-5 col-form-label">Confirm Password:</label>
				    <div class="col-sm-7">
				      <input type="password" name="cPassword" class="form-control" id="cPassword" placeholder="Confirm Password">

				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="address" class="col-sm-5 col-form-label">Address:</label>
				    <div class="col-sm-7">
				      <input type="text" name="address" class="form-control" id="address" placeholder="Address">

				    </div>
				    </div>
				   <div class="form-group row">
				    <label for="email" class="col-sm-5 col-form-label">Email:</label>
				    <div class="col-sm-7">

				      <input type="text" name="email" class="form-control" id="email" placeholder="Email">

				    </div>
				    </div>
				   <div class="form-group row">
				    <label for="mobile" class="col-sm-5 col-form-label">Phone Number:</label>
				    <div class="col-sm-7">

				      <input type="text" name="mobile" class="form-control" id="mobile" placeholder="Phone Number">

				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="mobile" class="col-sm-5 col-form-label">Age:</label>
				    <div class="col-sm-7">

				      <input type="text" name="age" class="form-control" id="age" placeholder="Age">

				    </div>
				  </div>
				  <div id="bottom">
				  	<label>City:</label>
				  	<input type="text" name="city" placeholder="City" class='form-control' id="city" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
				  	<label>Zip:</label>
				  	<input type="text" name="zip" placeholder="Zip" class='form-control' id="zip" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
				  	<label>State:</label>
				  	<select class="form-control" name="state" id="state" style="display: inline-block; width: 100px; margin: 0 30px 0 0;">
				        <option value="AL">AL</option>
				        <option value="AK">AK</option>
				        <option value="AZ">AZ</option>
				        <option value="AR">AR</option>
				        <option value="CA">CA</option>
				        <option value="CO">CO</option>
				        <option value="CT">CT</option>
				        <option value="DE">DE</option>
				        <option value="FL">FL</option>
				        <option value="GA">GA</option>
				        <option value="HI">HI</option>
				        <option value="ID">ID</option>
				        <option value="IL">IL</option>
				        <option value="IN">IN</option>
				        <option value="IA">IA</option>
				        <option value="KS">KS</option>
				        <option value="KY">KY</option>
				        <option value="LA">LA</option>
				        <option value="ME">ME</option>
				        <option value="MD">MD</option>
				        <option value="MA">MA</option>
				        <option value="MI">MI</option>
				        <option value="MN">MN</option>
				        <option value="MS">MS</option>
				        <option value="MO">MO</option>
				        <option value="MT">MT</option>
				        <option value="NE">NE</option>
				        <option value="NV">NV</option>
				        <option value="NH">NH</option>
				        <option value="NJ">NJ</option>
				        <option value="NM">NM</option>
				        <option value="NY">NY</option>
				        <option value="NC">NC</option>
				        <option value="ND">ND</option>
				        <option value="OH">OH</option>
				        <option value="OK">OK</option>
				        <option value="OR">OR</option>
				        <option value="PA">PA</option>
				        <option value="RI">RI</option>
				        <option value="SC">SC</option>
				        <option value="SD">SD</option>
				        <option value="TN">TN</option>
				        <option value="TX">TX</option>
				        <option value="UT">UT</option>
				        <option value="VT">VT</option>
				        <option value="VA">VA</option>
				        <option value="WK">WA</option>
				        <option value="WV">WV</option>
				        <option value="WI">WI</option>
				        <option value="WY">WY</option>
			      	</select>
			
			  
			      <div>
			     
							<h3 style="margin: 20px 0 20px 0; color: white;">Type of User</h3>
							<label>
						    	<input type="radio" name="type_user" id="treq" autocomplete="off" checked value="customer"> Customer
						  	</label>
						  	<label>
						    	<input type="radio" name="type_user" id="preq" autocomplete="off" value="employee"> Employee
						  	</label>
						</div>
			      <div>
			      	<input type="submit" value="Next" class="btn btn-primary btn-md" style="margin: 30px auto 0 auto;">
			      	<p style="margin: 20px 0 0 0;"><font color="red">${error_msg}</font></p>

			      </div>
				  </div>
				</form>
			</div>
		</div>
		
	</body>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</html>