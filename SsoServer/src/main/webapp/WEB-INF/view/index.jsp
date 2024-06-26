<!DOCTYPE html>
<html lang="en">
  <c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
	<title>Login V4</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${ctx}/index/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${ctx}/index/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${ctx}/index/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${ctx}/index/css/util.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/index/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	<div class="limiter">
		<div class="container-login100" style="background-image: url('${ctx}/index/images/bg-01.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
				<form class="login100-form validate-form" name='loginReqDTO' action="/sso/login" method='POST'>
					<span class="login100-form-title p-b-49">
						Login
					</span>
					
					

					<div class="wrap-input100 validate-input m-b-23" data-validate = "Username is reauired">
						<span class="label-input100">Username</span>
						<input class="input100" type="text" name="username" placeholder="Type your username">
						<span class="focus-input100" data-symbol="&#xf206;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="password" placeholder="Type your password">
						<span class="focus-input100" data-symbol="&#xf190;"></span>
					</div>
					
					<div class="text-right p-t-8 p-b-31">
						<a href="#">
							Forgot password?
						</a>
					</div>
					<%-- <c:set var = credential value = "${credential}"/>
					<c:if test="${ credential eq 'bad'}">
						<div class="alert alert-danger" >
							<p>Bad Credential</p> 
						</div></c:if> --%>
					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Login
							</button>
						</div>
					</div>

					<div class="txt1 text-center p-t-54 p-b-20">
						<span>
							Or Sign in Using
						</span>
					</div>
					
					

					<div class="flex-c-m">
						<a href="/oauth2/authorization/azure" class="login100-social-item bg1">
							<img src="${ctx}/index/images/icons/office365.ico" width="50" height="50">
						</a>
						<a href="/oauth2/authorization/google" class="">
							<img src="${ctx}/index/images/icons/gmail.png" width="50" height="50">
						</a>

					</div>

					
				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${ctx}/index/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${ctx}/index/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${ctx}/index/vendor/bootstrap/js/popper.js"></script>
	<script src="${ctx}/index/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${ctx}/index/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${ctx}/index/vendor/daterangepicker/moment.min.js"></script>
	<script src="${ctx}/index/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${ctx}/index/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${ctx}/index/js/main.js"></script>

</body>
</html>