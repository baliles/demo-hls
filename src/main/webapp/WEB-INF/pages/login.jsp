<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:url var="rootURL" value="/" />

<html>
<head>
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Login</title>

<link rel="shortcut icon" href="${rootURL}resources/favicon.ico" type="image/x-icon">
<link rel="icon" href="${rootURL}favicon.ico" type="image/x-icon">	

<!-- Font CSS (Via CDN) -->
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800'>
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css?family=Roboto:400,500,700,300">

<!-- Theme CSS -->
<link rel="stylesheet" type="text/css"
	href="${rootURL}assets/skin/default_skin/css/theme.css">

<!-- Admin Forms CSS -->
<link rel="stylesheet" type="text/css"
	href="${rootURL}assets/admin-tools/admin-forms/css/admin-forms.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
   <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
   <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
   <![endif]-->
   
   <style>
   
   body.external-page #main {
	  overflow: hidden;
	  background: white !important;
	}
   </style>
   
</head>
<body class="external-page sb-l-c sb-r-c">

	<!-- Start: Settings Scripts -->
	<script>
        var boxtest = localStorage.getItem('boxed');

        if (boxtest === 'true') {
            document.body.className += ' boxed-layout';
        }
        
        
    </script>
	<!-- End: Settings Scripts -->


	<div id="main" class="animated fadeIn">

		<!-- Start: Content -->
		<section id="content_wrapper">

			<!-- begin canvas animation bg -->
			<div id="canvas-wrapper">
				<canvas id="demo-canvas"></canvas>
			</div>

			<!-- Begin: Content -->
			<section id="content" class="login_page">

				<div class="admin-form theme-info" id="login1"
					style="max-width: 400px;">

					<div class="row mb15 table-layout">

						<div class="col-xs-6 center-block text-center va-m pln">
							<div
								style="color: white; font-size: 40px; font-family: sans-serif; font-weight: normal;">
								<img src="${rootURL}resources/logo.png" style="height: 60px;">
							</div>
						</div>
						<div class="col-xs-6 text-right va-b pr5"></div>

					</div>
					<div class="panel panel-info mt10 br-n">

						<form:form method="post" action="${rootURL}login" id="login"
							modelAttribute="user">
							<div class="panel-body bg-light p30">
								<div class="row">
									<div class="col-sm-12 pr30">
										<div class="section">
											<label for="username"
												class="field-label text-muted fs18 mb10">Username</label> <label
												for="username" class="field prepend-icon"> <input
												type="text" name="username" id="username" class="gui-input"
												placeholder="Enter username" value="${sessionScope.LAST_USERNAME}"> <label for="username"
												class="field-icon"><i class="fa fa-user"></i> </label>
											</label>
										</div>
										<!-- end section -->

										<div class="section">
											<label for="username"
												class="field-label text-muted fs18 mb10">Password</label> <label
												for="password" class="field prepend-icon"> <input
												type="password" name="password" id="password"
												class="gui-input" placeholder="Enter password"> <label
												for="password" class="field-icon"><i
													class="fa fa-lock"></i> </label>
											</label>
										</div>
										<div class="section">
											<div class="col-sm-12 text-right va-b pr5">
												<div class="login-links">
													<a href="${rootURL}credentials/forgot" class="" title="False Credentials">Password Reset</a>
												</div>
											</div>
										</div>
										<!-- end section -->
									</div>
								</div>
							</div>
							<!-- end .form-body section -->
							<div class="panel-footer clearfix p10 ph15">
								<button type="submit" class="button btn-primary mr10 pull-right">Login</button>
								<!--
 								<label
									class="switch block switch-primary pull-left input-align mt10">
									<input type="checkbox" name="remember" id="remember" checked>
									<label for="remember" data-on="YES" data-off="NO"></label> <span>Remember
										me</span>
								</label>
-->
							</div>
							<!-- end .form-footer section -->
						</form:form>
					</div>
				</div>

			</section>
			<!-- End: Content -->

		</section>
		<!-- End: Content-Wrapper -->

	</div>
	<!-- End: Main -->

	<!-- BEGIN: PAGE SCRIPTS -->

	<!-- jQuery -->
	<script type="text/javascript" src="${rootURL}vendor/jquery/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${rootURL}vendor/jquery/jquery_ui/jquery-ui.min.js"></script>

	<!-- Bootstrap -->
	<script type="text/javascript" src="${rootURL}assets/js/bootstrap/bootstrap.min.js"></script>

	<!-- Page Plugins -->
	<script type="text/javascript" src="${rootURL}assets/js/pages/login/EasePack.min.js"></script>
	<script type="text/javascript" src="${rootURL}assets/js/pages/login/rAF.js"></script>
	<script type="text/javascript" src="${rootURL}assets/js/pages/login/TweenLite.min.js"></script>
	<script type="text/javascript" src="${rootURL}assets/js/pages/login/login.js"></script>
	<script type="text/javascript" src="${rootURL}assets/admin-tools/admin-forms/js/jquery.validate.min.js"></script>

	<!-- Theme Javascript -->
	<script type="text/javascript" src="${rootURL}assets/js/utility/utility.js"></script>
	<script type="text/javascript" src="${rootURL}assets/js/main.js"></script>

	<!-- Page Javascript -->
	<script type="text/javascript">
        jQuery(document).ready(function() {

            "use strict";

            // Init Theme Core      
            Core.init();

            $( "#login" ).validate({
                
                /* @validation states + elements 
                ------------------------------------------- */
                
                errorClass: "state-error",
                validClass: "state-success",
                errorElement: "em",
                
                /* @validation rules 
                ------------------------------------------ */
                    
                rules: {
                        username: {
                                required: true,
                                email: true
                        },
                        password:{
                                required: true,
                                minlength: 8,
                                maxlength: 16                       
                        }                                                                                           
                    
                },
                
                /* @validation error messages 
                ---------------------------------------------- */
                    
                messages:{
                        username: {
                                required: 'Enter username',
                                email: 'Enter a VALID email address'
                        },
                        password:{
                                required: 'Please enter a password'
                        }                                                                          
                },

                /* @validation highlighting + error placement  
                ---------------------------------------------------- */ 
                
                highlight: function(element, errorClass, validClass) {
                        $(element).closest('.field').addClass(errorClass).removeClass(validClass);
                },
                unhighlight: function(element, errorClass, validClass) {
                        $(element).closest('.field').removeClass(errorClass).addClass(validClass);
                },
                errorPlacement: function(error, element) {
                   if (element.is(":radio") || element.is(":checkbox")) {
                            element.closest('.option-group').after(error);
                   } else {
                            error.insertAfter(element.parent());
                   }
                }
                        
        });     

            
/*           	$('#login').submit(function() {
        	    $theForm = $(this);
        	    // send xhr request
        	    $.ajax({
        	        type: $theForm.attr('method'),
        	        url: $theForm.attr('action'),
        	        data: $theForm.serialize(),
        	        success: function(data) {
        	        	console.dir(data);
        	        	alert('Welcome ' + data);
        	        },
        	        error: function (jqXHR,  s,  e ){
        	        	alert('Error while trying to send message: ' + e + '\n' + jqXHR.responseText)
        	        }
        	    });
        	    return false;
        	});
*/
           	$('#username').focus();
           	
        });
    </script>
	<c:if test="${param.error != null}">
        <script>

		setTimeout(
				function() {
					alert("Invalid username and password, try again!");
					$('#username').focus();
				}, 500);
		</script>
    </c:if>

	<!-- END: PAGE SCRIPTS -->


</body>
</html>