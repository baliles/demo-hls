<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:url var="rootURL" value="/" />

<!DOCTYPE html>
<html>

<head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <title>IoT</title>
    <meta name="keywords" content="PCS" />
    <meta name="author" content="rodrigo@rtapps.co">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${rootURL}favicon.ico" type="image/x-icon">
	<link rel="icon" href="${rootURL}favicon.ico" type="image/x-icon">	
	<%@ include file = "main/css.jsp" %>
<style>
#content_wrapper {
  margin-left: 0px !important;
}
</style>
</head>

<body class="blank-page">
    <!-- Start: Main -->
    <div id="main">
<%-- 
		<!-- Breadcrumbs -->
		<%@ include file="main/viewsettings.jsp" %> 
	
		<!-- header -->
		<%@ include file="main/header.jsp" %> 
	
		<!-- Side Bar (Menu) -->
		<%@ include file="main/sidebar.jsp" %>
	
 --%>
 		<!-- Start: Content -->
		<section id="content_wrapper">
			
<%-- 			<!-- Top Bar (Shortcuts) -->
			<%@ include file="main/topbar.jsp" %> 
 --%>			
			<!-- Breadcrumbs -->
<%-- 			<%@ include file = "main/breadcrumbs.jsp" %>
 --%>
			<!-- Begin: Content -->
			<section id="page_content">

			</section>
			<!-- End: Content -->
			
		</section>
	</div>
    <!-- End: Main -->

    <!-- BEGIN: PAGE SCRIPTS -->
	<%@ include file="main/bodyscripts.jsp" %>

    <script type="text/javascript">
        jQuery(document).ready(function() {

            "use strict";

            // Init Theme Core    
            Core.init();
        
	        	$.ajax({
	        		url: '${rootURL}solo/accounts.html',
	        		dataType: 'html'
	        	}).done(function (result) {
        		    var $result = $(result);
					var $checklogin = $result.find('.login_page')
        		    if ((typeof $checklogin == 'undefined') || ($checklogin.hasClass("login_page"))){
        		    	console.log('redirecting to login!');
        		    	window.location = "${rootURL}login/form";
        		    	return;
        		    }
        		    
        		    $('#page_content').html($result) ;
        		    console.log('Content loaded!');
        		    console.log('Scripts loaded');
	        	}).fail(function (jqXHR, exception) {
	        		console.dir(jqXHR);
	        		console.dir(exception);
	        		if (jqXHR.status == 0) {
	        			alert('Could not connect.\nPlease check your connection settings.');
	        		} else if (jqXHR.status == 404) {
	        			alert('Requested page not found. [404]');
	        		} else if (jqXHR.status == 500) {
	        			alert('Internal Server Error [500].' + '\n' + jqXHR.responseText);
	        		} else if (jqXHR.status == 12007) {
	        			alert('The server name cannot be resolved.');
	        		} else if (jqXHR.status == 12029) {
	        			alert('Connection to the server failed.');
	        		} else if (exception == 'parsererror') {
	        			alert('JSON Parse failure.');
	        		} else if (exception == 'timeout') {
	        			alert('Time out error.');
	        		} else if (exception == 'abort') {
	        			alert('Request aborted.');
	        		} else {
	        			alert('Uncaught Error.\n' + jqXHR.responseText);
	        		}
	        	});
        });
    </script>
    <!-- END: PAGE SCRIPTS -->

</body>

</html>
