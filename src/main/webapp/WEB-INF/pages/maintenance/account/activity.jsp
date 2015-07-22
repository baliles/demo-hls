<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script>
	$('#page_content')
			.find('a')
			.on(
					'click',
					function(e) {
						console
								.log('executing url ... '
										+ $(this).attr('href'));

						e.preventDefault();

						if ($(this).attr('href') == '#')
							return;

						$
								.ajax({
									url : $(this).attr('href'),
									dataType : 'html'
								})
								.done(
										function(result) {
											var $result = $(result);
											var $checklogin = $result
													.find('.login_page')
											if ((typeof $checklogin == 'undefined')
													|| ($checklogin
															.hasClass("login_page"))) {
												console
														.log('redirecting to login!');
												window.location = "${rootURL}login/form";
												return;
											}

											$('#page_content').html($result);
											console.log('Content loaded!');
											console.log('Scripts loaded');
										})
								.fail(
										function(jqXHR, exception) {
											console.dir(jqXHR);
											console.dir(exception);
											if (jqXHR.status == 0) {
												alert('Could not connect.\nPlease check your connection settings.');
											} else if (jqXHR.status == 404) {
												alert('Requested page not found. [404]');
											} else if (jqXHR.status == 500) {
												alert('Internal Server Error [500].'
														+ '\n'
														+ jqXHR.responseText);
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
												alert('Uncaught Error.\n'
														+ jqXHR.responseText);
											}
										});
					});
</script>

<header id="topbar" class="ph10">
	<div class="topbar-left">
		<ol class="breadcrumb">
			<c:choose>
				<c:when test="${solo == 'solo/'}">
					<li class="crumb-active"><a
						href="${rootURL}solo/accounts/devices/${account.sfid}">Back</a></li>
				</c:when>
				<c:otherwise>
					<li class="crumb-active"><a
						href="${rootURL}maintenance/accounts/devices/${account.sfid}">Back</a></li>
				</c:otherwise>
			</c:choose>
		</ol>
	</div>
</header>
<section id="content" class="table-layout">
	<!-- begin: .tray-center -->




	<div class="tray tray-center tray-top">
		<div class="panel" id="device-table">
			<div class="panel-heading">
				<span class="panel-title"> <span class="fa fa-table"></span>
					Device of account: ${account.name}<BR />
				</span>
				<div class="pull-right"></div>
			</div>
			<div class="panel-body pn">
				<div class="table-responsive">
					<div class="bs-component">

						<table id="deviceInfo" class="table table-bordered mbn">
							<tbody>
								<tr>
									<td>Device name:</td>
									<td>${device.name}</td>
								</tr>
								<tr>
									<td>Device Type</td>
									<td>${device.deviceTypeC}</td>
								</tr>
								<tr>
									<td>Device Status</td>
									<td>${device.statusC}</td>
								</tr>
								<tr>
									<td>Device Address</td>
									<td>${device.macAddressC}</td>
								</tr>
								<tr>
									<td>IP Address</td>
									<td>${device.ipAddressC}</td>
								</tr>
								<tr>
									<td>Data Plan</td>
									<td>${device.currentMonthlyDataMbC}</td>
								</tr>
							</tbody>
						</table>

					</div>
				</div>
			</div>

			<div class="panel" id="activity-table">
				<div class="panel-heading">
					<span class="panel-title"> <span class="fa fa-table"></span>
						Activity<BR />
					</span>
					<div class="pull-right"></div>
				</div>
				<div class="panel-body pn">
					<div class="table-responsive">
						<div class="bs-component">
							<table id="accounts" class="table table-bordered mbn">
								<thead>
									<tr>
										<th>Name</th>
										<th>Timestamp</th>
										<th>Transferred MB</th>
										<th>Duration</th>
										<th>IP Address</th>
										<th>Deleted</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="a" items="${activity}">
										<tr>
											<td>${a.name}</td>
											<td>${a.activityTimestampC}</td>
											<td>${a.dataTransferredMbC}</td>
											<td>${a.activityDurationMsC}</td>
											<td>${a.originatingIpAddressC}</td>
											<td>${a.isdeleted}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end: .tray-center -->
</section>
