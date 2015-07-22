<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>

$('#page_content').find('a').on('click', function(e) {
    	console.log('executing url ... ' + $(this).attr('href'));

    	e.preventDefault();
    	
    	if ($(this).attr('href') == '#')
    		return;

    	$.ajax({
    		url: $(this).attr('href'),
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

	$('#addAccount').click(function(e) {
			e.preventDefault();
	});

</script>

<header id="topbar" class="ph10">
	<div class="topbar-left">
		<ol class="breadcrumb">
			<c:choose> 
			  <c:when test="${solo == 'solo/'}">
				<li class="crumb-active"><a href="${rootURL}${solo}">Accounts</a></li>
			  </c:when>
			  <c:otherwise>
				<li class="crumb-active"><a href="${rootURL}maintenance/accounts">Accounts</a></li>
			  </c:otherwise>
			</c:choose>	
		</ol>
	</div>

	<div class="topbar-right">
		<a href="${rootURL}maintenance/accounts/create.html" id="addAccount"
			class="btn btn-primary btn-sm light fw600 ml10"> <span
			class="fa fa-plus pr5"></span> Add Account
		</a>
	</div>
</header>
<section id="content" class="table-layout">
	<!-- begin: .tray-center -->
	<div class="tray tray-center tray-top">
 		<div class="panel" id="accounts-table">
			<div class="panel-heading">
				<span class="panel-title"> <span class="fa fa-table"></span>Accounts
				</span>
				<div class="pull-right"></div>
			</div>
			<div class="panel-body pn">
				<div class="table-responsive">
					<div class="bs-component">
						<table id="accounts" class="table table-bordered mbn">
							<thead>
								<tr>
									<th>Partner Name</th>
									<th>Partner Type</th>
									<th>Channel</th>
									<th>zip</th>
									<th>Salesforce ID</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="account" items="${Accounts}">
									<tr>
										<td>
										<a id="${account.sfid}" href="${rootURL}${solo}accounts/devices/${account.sfid}.html">${account.name}</a>
										</td>
										<td>${account.partnerTypeC}</td>
										<td>${account.buyingRelationshipC}</td>
										<td>${account.billingpostalcode}</td>
										<td>${account.sfid}</td>
										<td class="text-center">
										<a id="detail-${account.sfid}" class="fa fa-trash" href="${rootURL}${solo}accounts/devices/${account.sfid}.html"></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end: .tray-center -->
<%--
 	<%@ include file="filters.jsp" %> 	
 --%>
 </section>
 