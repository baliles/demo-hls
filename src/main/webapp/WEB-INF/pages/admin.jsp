<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<c:url var="rootURL" value="/"/>

<html>
<head>
<title>Administrator</title>
<link rel="stylesheet" href='<spring:url value="resources/css/styles.css"/>' />
<script type="text/javascript" src='<spring:url value="resources/jquery/jquery-1.10.2.js"/>'></script>
<script type="text/javascript" src='<spring:url value="resources/js/app.js"/>'></script>
<script type="text/javascript">

</script>
</head>
<body>
	<h2>Administrator Home Page</h2>
	<p>	<a href="${rootURL}welcome">Home</a></p>
	<p>	<a href="${rootURL}logout">Logout</a></p>
	
</body>
</html>