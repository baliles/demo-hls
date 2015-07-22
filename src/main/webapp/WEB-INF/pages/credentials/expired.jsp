<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
 
<body>
	<h2>Your credentials has been expired, please change them</h2>
	<form method="POST" action="/credentials/change">
		<table>
			<tr>
				<td>Username :</td>
				<td><input disabled="disabled" id="username" value="${sessionScope.LAST_USERNAME}">
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>Old Password :</td>
				<td><input type="password" id="oldPassword" >
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>New Password :</td>
				<td><input type="password" id="newPassword" />
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>Confirm Password :</td>
				<td><input type="password" id="confirmPassword" />
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" />
				</td>
			</tr>
		</table>
	</form>
 
</body>
</html>