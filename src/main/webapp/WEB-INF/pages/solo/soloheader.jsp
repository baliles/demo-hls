<c:url var="rootURL" value="/" />

<!DOCTYPE html>
<html>

<head>
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <title>Vonage</title>
    <meta name="keywords" content="PCS" />
    <meta name="author" content="rodrigo@rtapps.co">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${rootURL}resources/favicon.ico" type="image/x-icon">
	<link rel="icon" href="${rootURL}favicon.ico" type="image/x-icon">	

        <script type="text/javascript" src="${rootURL}resources/sdk/js/canvas-all.js"></script>
        <script type="text/javascript" src="${rootURL}resources/scripts/json2.js"></script>

	<%@ include file = "../main/css.jsp" %>
<style>
#content_wrapper {
  margin-left: 0px !important;
}
</style>

<script>

Sfdc.canvas(function() {
    var sr = JSON.parse('${signedRequestJson}');
    // Save the token
    Sfdc.canvas.oauth.token(sr.oauthToken);
    Sfdc.canvas.byId('username').innerHTML = sr.context.user.fullName;
});

</script>

</head>

<body class="blank-page">
    <!-- Start: Main -->
    <div id="main">
 		<!-- Start: Content -->
		<section id="content_wrapper">
			
			<!-- Begin: Content -->
			<section id="page_content">

