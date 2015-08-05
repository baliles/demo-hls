
var sections;
var debug = false;
var patient = '0031a000003RTbLAAW';
var q;

function sendRequest(url, json){
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
	  			swal('Error!',item.message,'error');
	  		}else{
		  		swal('Your ' + item.data.type + ' request was successfully registered!','',"success");
	  		}
	  	},
	  	error: function(jq, status, message){
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

	var url = 'https://demo-hls.herokuapp.com/api/v1/pr/request';

	sendRequest(url, json);
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
	getServiceData('http://demo-hls.herokuapp.com/api/v1/section/all');
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
	s += '				<ul class="pager wizard">;'
	s += '					<li class="previous first disabled" style="display:none;"><a href="#">First</a>';
	s += '					</li>';
	s += '					<li class="previous disabled"><input type="submit" id="back-to-tiles" class="btn btn-lg" value="Back">';
	s += '					</li>';
	s += '					<li class="next last">';
	s += '					</li>';
	s += '					<li class="next"><input type="submit" class="btn btn-lg btn-success" value="Send request">';
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
		
		if (section.questions[q].type == 'COMMENT')
			addQuestionComment(section.questions[q]);
		
		if (section.questions[q].type == 'CALL')
			addQuestionCall(section.questions[q]);
		
	}
	
	$('#main-content').append(getQuestionFooter());
	
	$("#back-to-tiles").unbind('click',function(e){});
	$("#back-to-tiles").click(function(e){
		loadPage('tiles.html');
	});
	
}
function addQuestionCall(q) {
	var qid = '#question-' + q.id;
	return qid;
}

function addQuestionComment(q) {
	
	var qid = '#question-' + q.id;
	var s =  '<div class="row grid">\n';
		s += '	<div class="col-md-12 col-s-12 col-xs-12">\n';
		s += '		<div class="panel panel-piluku" id="question-' + q.id + '" data-type="' + q.type + '" data-comment="comment-' + q.id + '">\n';
		s += '			<div class="panel-heading">\n';
		s += '				<h3 class="panel-title">' + q.question + '\n';
		
		if (q.type == 'MULTIPLE')
			s += ' (select multiple)';
		
		s += '					<span class="panel-options">\n';
		s += '						<a href="#" class="panel-refresh"></a>\n';
		s += '					</span>\n';
		s += '				</h3>\n';
		s += '			</div>\n';
		s += '			<div class="panel-body">\n';
		s += '				<div class="col-md-12 col-s-12 col-xs-12">\n';
		s += '					<div class="responsive-bottom">\n';
		s += '						<textarea name="question" class="form-control text-area" rows="20" placeholder="Please enter your comment ..." id="comment-' + q.id + '"></textarea>\n';
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

function addQuestionSingleOrMultiple(q) {
var s =  '<div class="row grid">\n';
	s += '	<div class="col-md-12 col-s-12 col-xs-12">\n';
	s += '		<div class="panel panel-piluku" id="question-' + q.id + '" data-type="' + q.type + '">\n';
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
	
	$("#submit-need-my-nurse").click(function(e){

		var request_type = 'Need my Nurse';
		var id = 'need-my-nurse';
		var comment = $('#nurse-question').val();
		
		prepareRequest(id, request_type, comment);
		$('#nurse-question').val('');
		
	});
	
	
});



