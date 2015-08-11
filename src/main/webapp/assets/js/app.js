
var sections;
var debug = false;
var patient = '0031a000003RTbLAAW';
var q;
var serverUrl = 'https://demo-hls.herokuapp.com';
	
function sendRequest(url, json, request_type){
	  $.ajax({
	  	url: url,
	  	data: JSON.stringify(json),
	  	type: "POST",
	  	
	  	beforeSend: function(xhr) {
	  		xhr.setRequestHeader("Accept", "application/json");
	  		xhr.setRequestHeader("Content-Type", "application/json");
	  	},
	  	success: function(item) {
	  		console.log(JSON.stringify(item));

	  		if (item.success == false){
	  			loadPage('tiles.html');
	  			swal('Error!',item.message,'error');
	  		}else{
	  			callMessagesService();

	  			swal({
	  				title: request_type,
	  				text: "Request was successfully registered!",
	  				type: "success",
	  				showCancelButton: false,
	  				confirmButtonClass: 'btn-success',
	  				confirmButtonText: 'Ok!',
	  				closeOnConfirm: true,
	  		          //closeOnCancel: false
	  		      },
	  		      function(){
	  		      	//swal("Thanks!", "We are glad you clicked welcome!", "success");
	  		      	loadPage('tiles.html');
	  		      });

//		  		swal('Your ' + item.data.type + ' request was successfully registered!','',"success");
	  		}
	  	},
	  	error: function(jq, status, message){
	  		loadPage('titles.html');
	  		swal('Error!',jq.status + " - " + message,'error');
	  	}
	  });

}

function getServiceData(url){
	  $.ajax({
	  	url: url,
	  	type: "GET",
	  	
	  	beforeSend: function(xhr) {
	  		xhr.setRequestHeader("Accept", "application/json");
	  		xhr.setRequestHeader("Content-Type", "application/json");
	  	},
	  	success: function(item) {
	  		if (debug)
	  			console.log(JSON.stringify(item));

	  		sections = item;
	  		console.log('Data loaded!');
	        $('.piluku-preloader').addClass('hidden');

	  	},
	  	error: function(jq, status, message){
	  		swal('Error!',jq.status + " - " + message,'error');
	  	}
	  });

}

function getMessages(url){
	console.log('Loading messages .... ');
	  $.ajax({
	  	url: url,
	  	type: "GET",
	  	
	  	beforeSend: function(xhr) {
	  		xhr.setRequestHeader("Accept", "application/json");
	  		xhr.setRequestHeader("Content-Type", "application/json");
	  	},
	  	success: function(item) {
	  		if (debug)
	  			console.log(JSON.stringify(item));

	  		addAllMessages(item);
	  	},
	  	error: function(jq, status, message){
	  		swal('Error!',jq.status + " - " + message,'error');
	  	}
	  });

}


function prepareRequest(id, request_type, comment) {
	var sub_category = 'SUB';

	var json = {
		"type" : request_type,
		"comment" : comment,
		"originId" : id,
		"patient" : patient,
		"subCategory" : sub_category
	}

	if (debug)
		console.log(JSON.stringify(json));

	var url = serverUrl + '/api/v1/pr/request';

	sendRequest(url, json, request_type);
}

function loadPage(page) {

    "use strict";
    	
    	$.ajax({
    		url: page,
    		dataType: 'html'
    	}).done(function (result) {
		    var $result = $(result);
		    $('#main-content').html($result) ;
		    if (debug)
		    	console.log('Content [' + page + '] loaded!');
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
}

function initApp(){
	loadPage('tiles.html');
	getServiceData(serverUrl + '/api/v1/section/all');
	callMessagesService();
}

function callMessagesService(){
	getMessages(serverUrl + '/api/v1/pr/chat?patient=' + patient);
//	getMessages('http://localhost:8080/demo-hls' + '/api/v1/pr/chat?patient=' + patient);

}

function getSection(id){
	
	for (s in sections.data){
		if (sections.data[s].name == id){
			return sections.data[s];
		}
	}
}

function getQuestionFooter() {
	var s = '';

	s += '	<div class="row grid">';
	s += '	<div class="col-md-12 col-s-12 col-xs-12">';
	s += '		<div class="panel panel-piluku">';
	s += '			<div class="panel-body">';
	s += '				<ul class="pager wizard">';
	s += '					<li class="previous first disabled" style="display:none;"><a href="#">First</a>';
	s += '					</li>';
	s += '					<li class="previous disabled"><input type="submit" id="back-to-tiles" class="btn btn-lg" value="Back">';
	s += '					</li>';
	s += '					<li class="next last">';
	s += '					</li>';
	s += '					<li class="next"><input type="submit" id="submit-questions" class="btn btn-lg btn-success" value="Send request">';
	s += '					</li>';
	s += '				</ul>		';
	s += '			</div>';
	s += '		</div>';
	s += '	</div>';
	s += '</div>';

	return s;

}

function loadQuestions(id, request_type){
	
	var section = getSection(id);
	var questions = '';
	
	$('#main-content').empty();

	for(q in section.questions)
	{
		
		if (section.questions[q].type == 'SINGLE' || section.questions[q].type == 'MULTIPLE')
			addQuestionSingleOrMultiple(section.questions[q]);
		
		if (section.questions[q].type == 'TEXT')
			addQuestionText(section.questions[q]);
		
		if (section.questions[q].type == 'CALL')
			addQuestionCall(section.questions[q]);
		
	}
	
	$('#main-content').append(getQuestionFooter());
	
	$("#back-to-tiles").unbind('click',function(e){});
	$("#back-to-tiles").click(function(e){
		loadPage('tiles.html');
	});

	
	$("#submit-questions").unbind('click',function(e){});
	$("#submit-questions").click(function(e){
		var s='';
		$("div[data-role='question']").each(function(){
			var type = $(this).attr('data-type');
			var q = $(this).find('h3').text().trim();
			var text = '';

			if (type == 'SINGLE' || type == 'MULTIPLE'){
				console.log('type : ' + type);
				$(this).find('.active').each(function(){
					console.log('answer !!  ' + $(this).text());
					text+= $(this).text() + ',';
				});
			}
			
			if (type == 'TEXT')
				text = $(this).find('textarea').val();
				
			s += "question:"  + q +  ", id:" + $(this).attr('id') + ',type: ' + type + ',answer:' + text + '\n';
			
		});
		
		if (debug)
			console.log('Sent!!!\n' + s);
		
		prepareRequest(id, request_type, s);
		
		return s;
		
	});
	
}

function addQuestionCall(q) {
	var qid = '#question-' + q.id;
	return qid;
}

function addQuestionText(q) {
	
	var qid = '#question-' + q.id;
	var s =  '<div class="row grid">\n';
		s += '	<div class="col-md-12 col-s-12 col-xs-12">\n';
		s += '		<div class="panel panel-piluku" id="question-' + q.id + '" data-type="' + q.type + '" data-role="question" data-comment="comment-' + q.id + '">\n';
		s += '			<div class="panel-heading">\n';
		s += '				<h3 class="panel-title">' + q.question + '\n';		
		s += '					<span class="panel-options">\n';
		s += '						<a href="#" class="panel-refresh"></a>\n';
		s += '					</span>\n';
		s += '				</h3>\n';
		s += '			</div>\n';
		s += '			<div class="panel-body">\n';
		s += '				<div class="col-md-12 col-s-12 col-xs-12">\n';
		s += '					<div class="responsive-bottom">\n';
		s += '						<textarea name="question" class="form-control text-area" rows="20" placeholder="Please type your question ..." id="comment-' + q.id + '"></textarea>\n';
		s += '					</div>\n';
		s += '				</div>\n';
		s += '			</div>\n';
		s += '		</div>\n';
		s += '	</div>\n';
		s += '</div>\n';
		
	if (debug)
		console.log(s);
		
	$('#main-content').append(s);

		return qid;
}

function addMessage(record){
	
	var s = '<a href="#" class="list-group-item" id="message-' + record.id + '" data-sfid="' + record.sfid +  '">';
		s+=	'	<p class="list-group-item-text">' + record.commentsC.replace('COMMENT:','') + '</p>';
		s+=	'</a>';
		
		return s;
}

function addAllMessages(response){

var s = '<div class="list-group message-list"> ';
	
	for (m in response.data)
		s += addMessage(response.data[m]);

	s += '</div>	';
	
	console.log(s);
	
	$('.message-list').empty();
	$('.message-list').html(s);
	
	$('#messages').find('.ion-android-refresh').on('click', function(e){
		callMessagesService();
	});
	
}




function addQuestionSingleOrMultiple(q) {
var s =  '<div class="row grid">\n';
	s += '	<div class="col-md-12 col-s-12 col-xs-12">\n';
	s += '		<div class="panel panel-piluku" id="question-' + q.id + '" data-role="question" data-type="' + q.type + '">\n';
	s += '			<div class="panel-heading">\n';
	s += '				<h3 class="panel-title">' + q.question + '\n';
	s += '					<span class="panel-options">\n';
	s += '						<a href="#" class="panel-refresh"></a>\n';
	s += '					</span>\n';
	s += '				</h3>\n';
	s += '			</div>\n';
	s += '			<div class="panel-body">\n';
	s += '				<div class="list-group demo-list-group group-answer">\n';

	for (a in q.answers)
		s += '				<a href="#" class="list-group-item" id="question-' + q.id + '-answer-' + q.answers[a].id  + '">' + q.answers[a].answer  +  '</a>\n';

	s += '				</div>\n';
	s += '			</div>\n';
	s += '		</div>\n';
	s += '	</div>\n';
	s += '</div>\n';
	
	if (debug)
		console.log(s);
	
	$('#main-content').append(s);

	var qid = '#question-' + q.id;
	
	$(qid).find('a.list-group-item').click(function(e){
		var thisId = $(this).attr('id');
		
		if (q.type == 'SINGLE'){
			$(this).parent().find('a.list-group-item').each(function(){
				$(this).removeClass('active');
			});
		}
		
		$(this).toggleClass('active');
	});
	
	return qid;
}



$(document).ready(function(){
  
	window.setTimeout(initApp(), 50);
	
	$(".submit").click(function(e){

		var request_type = $(this).closest('div[role="dialog"]').attr('id').replace('modal-','');
		var id = $(this).attr('data-request');
		var comment = 'COMMENT:' + $('#comment-' + request_type).val();
		
		if (debug){
			console.log(request_type);
			console.log(id);
			console.log(comment);
		}
		
		prepareRequest(id, request_type, comment);
		$('#comment-' + request_type).val('');
		
	});
	
	
});



