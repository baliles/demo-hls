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

<title>Forgot Password</title>

<!-- Font CSS (Via CDN) -->
<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800'>
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Roboto:400,500,700,300">

<!-- Vendor CSS -->
<link rel="stylesheet" type="text/css" href="vendor/plugins/magnific/magnific-popup.css">

<!-- Theme CSS -->
<link rel="stylesheet" type="text/css" href="${rootURL}assets/skin/default_skin/css/theme.css">

<!-- Admin Forms CSS -->
<link rel="stylesheet" type="text/css" href="${rootURL}assets/admin-tools/admin-forms/css/admin-forms.css">

<!-- Admin Modals CSS -->
<link rel="stylesheet" type="text/css" href="assets/admin-tools/admin-plugins/admin-modal/adminmodal.css">


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
   <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
   <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
   <![endif]-->
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


    <!-- Start: Main -->
    <div id="main" class="animated fadeIn">

        <!-- Start: Content -->
        <section id="content_wrapper">

            <!-- begin canvas animation bg -->
            <div id="canvas-wrapper">
                <canvas id="demo-canvas"></canvas>
            </div>

            <!-- Begin: Content -->
            <section id="content">

                <div class="admin-form theme-info mw500" style="margin-top: 10%;" id="login1">
                    <div class="row mb15 table-layout">

                        <div class="col-xs-6 center-block text-center va-m pln">
                            <a href="${rootURL}main" title="">
                                <img src="${rootURL}assets/img/logos/logo_white.png" title="Logo" class="img-responsive w250">
                            </a>
                        </div>

                        <div class="col-xs-6 text-right va-b pr5">
                            <div class="login-links">
<!--                                 <a href="#" class="" title="False Credentials">Password Reset</a>
 -->                            </div>
                        </div>
                    </div>

                    <div class="panel panel-info mv10 heading-border br-n">

                        <!-- end .form-header section -->
                        <form:form method="post" action="${rootURL}credentials/forgot/send" id="resetForm">
                            <div class="panel-body bg-white p15 pt25">

                                <div class="alert alert-micro alert-border-left alert-info pastel alert-dismissable mn">
                                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                    <i class="fa fa-info pr10"></i> Enter your <b>Email</b> and instructions will be sent to you!
                                </div>

                            </div>
                            <!-- end .form-body section -->
                            <div class="panel-footer p25 pv15">

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="section mn">
                                            <label for="email" class="field-label text-muted fs18 mb10 hidden">Password Reset</label>
                                            <div class="smart-widget sm-right smr-80">
                                                <label for="email" class="field prepend-icon">
                                                    <input type="text" name="email" id="email" class="gui-input" placeholder="Your Email Address">
                                                    <label for="email" class="field-icon"><i class="fa fa-envelope-o"></i>
                                                    </label>
                                                </label>
                                                <label for="email" class="button" id="reset">Reset</label>
                                            </div>
                                            <!-- end .smart-widget section -->
                                        </div>
                                        <!-- end section -->
                                    </div>

                                </div>

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
    <script type="text/javascript" src="${rootURL}vendor/plugins/magnific/jquery.magnific-popup.js"></script>

    <!-- Admin Forms Javascript -->
    <script type="text/javascript" src="${rootURL}assets/admin-tools/admin-forms/js/jquery-ui-monthpicker.min.js"></script>
    <script type="text/javascript" src="${rootURL}assets/admin-tools/admin-forms/js/jquery-ui-timepicker.min.js"></script>
    <script type="text/javascript" src="${rootURL}assets/admin-tools/admin-forms/js/jquery-ui-touch-punch.min.js"></script>
    <script type="text/javascript" src="${rootURL}assets/admin-tools/admin-forms/js/jquery.spectrum.min.js"></script>
    <script type="text/javascript" src="${rootURL}assets/admin-tools/admin-forms/js/jquery.stepper.min.js"></script>

	<!-- Theme Javascript -->
	<script type="text/javascript" src="${rootURL}assets/js/utility/utility.js"></script>
	<script type="text/javascript" src="${rootURL}assets/js/main.js"></script>

	<!-- Page Javascript -->
	<script type="text/javascript">
        jQuery(document).ready(function() {

            "use strict";

            // Init Theme Core      
            Core.init();
            
            $( "#resetForm" ).validate({
                
                /* @validation states + elements 
                ------------------------------------------- */
                
                errorClass: "state-error",
                validClass: "state-success",
                errorElement: "em",
                
                /* @validation rules 
                ------------------------------------------ */
                    
                rules: {
                		email: {
                                required: true,
                                email: true
                        }                                                                                           
                    
                },
                
                /* @validation error messages 
                ---------------------------------------------- */
                    
                messages:{
                		email: {
                                required: 'Enter the email associated to your username',
                                email: 'Enter a VALID email address'
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

            $('#reset').click(function(e){
            	console.log	('Sending reset ');
            	$('form#resetForm').submit();
            });
            
           	$('#resetForm').submit(function() {
            	console.log('Reset sent!');
        	    var resetForm = $(this);
        	    // send xhr request
        	    $.ajax({
        	        type: resetForm.attr('method'),
        	        url: resetForm.attr('action'),
        	        data: resetForm.serialize(),
        	        success: function(data) {
        	        	console.dir(data);
        	        	alert(data.data);
        	        },
        	        error: function (jqXHR,  s,  e ){
        	        	alert('Error while trying to send message: ' + e + '\n' + jqXHR.responseText)
        	        }
        	    });
        	    return false;
        	});

        });
    </script>

	<!-- END: PAGE SCRIPTS -->


</body>
</html>