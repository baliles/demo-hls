<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>

$('#newEvent').submit(function(event) {
	  
	  event.preventDefault();
	  $(this).unbind('submit');
	  
	  var device = $('#device').val();
	  var type = $('#type').val();
	  var event = $('#event').val();
	  var metric = $('#metric').val();
	  var comment = $('#comment').val();
	  
	  var json = {
  		  "device":device,
  		  "type":type,
  		  "event":event,
  		  "metric":metric,
  		  "comment":comment
  		  }
	  
	  console.log(JSON.stringify(json));

	  $.ajax({
	  	url: $("#newEvent").attr( "action"),
	  	data: JSON.stringify(json),
	  	type: "POST",
	  	
	  	beforeSend: function(xhr) {
	  		xhr.setRequestHeader("Accept", "application/json");
	  		xhr.setRequestHeader("Content-Type", "application/json");
	  	},
	  	success: function(item) {
	  		bootbox.alert('Event [' + item.data.type + ' ' + item.data.event + '] was registerd at ' + item.data.created + ' by ' +  item.data.createdby, function() {
	  			navigateTo('${rootURL}maintenance/events.html');
	  		});
	  	},
	  	error: function(jq, status, message){
	  		bootbox.alert(jq.status + " - " + message, function(){});
	  	}
	  });
   
});

$('#back').click(function(e){
	navigateTo('${rootURL}maintenance/events.html');
	e.preventDefault();
});
</script>

<section id="content" class="table-layout">

	<!-- begin: .tray-center -->
	<div class="tray tray-center">


		<div class="panel">
			<div class="panel-heading">
				<span class="panel-title">Add Event</span>
			</div>
			<div class="panel-body">

				<form:form class="form-horizontal" role="form" id="newEvent" action="${rootURL}api/v1/event/create" commandName="Event">
					<div class="form-group">
						<label for="device" class="col-lg-3 control-label">Device</label>
						<div class="col-lg-8">
							<div class="bs-component">
								<form:input type="text" id="device" path="device" class="form-control"
									placeholder="Enter Device UUID..."/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="type" class="col-lg-3 control-label">Type</label>
						<div class="col-lg-8">
							<div class="bs-component">
								<form:input type="text" id="type" path="type" class="form-control"
									placeholder="type ..."/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="event" class="col-lg-3 control-label">Event</label>
						<div class="col-lg-8">
							<div class="bs-component">
								<form:input type="text" id="event" path="event" class="form-control"
									placeholder="event ..."/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="metric" class="col-lg-3 control-label">Metric</label>
						<div class="col-lg-8">
							<div class="bs-component">
								<form:input type="number" id="metric" path="metric" class="form-control"
									placeholder="metric ..."/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label" for="comment">Comment</label>
						<div class="col-lg-8">
							<div class="bs-component">
								<form:textarea class="form-control" id="comment" path="comment" rows="3"></form:textarea>
							</div>
						</div>
					</div>
					<hr class="short alt">
					<div class="form-group text-right">
					<div class="col-lg-1">
						<button type="button" id="back"
							class="btn btn-default btn-gradient dark btn-block">Back</button>
					</div>
					<div class="col-lg-1">
						<button type="submit"
							class="btn btn-primary btn-gradient dark btn-block">Save</button>
					</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- end: .tray-center -->

</section>