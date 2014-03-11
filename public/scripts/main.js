$(function(){
	setInterval(function(){
		$('#messages').load('/getMessages');
	}, 1000);
	
	$('#message').keydown(function(event){
		if(event.keyCode == 13) { // enter key was pressed inside text box
			sendMessage();
		}
	});
	
	$('#send-message').click(sendMessage);
	
	function sendMessage(){
		$.post('/sendMessage', { message: $('#message').val() });
		$('#message').val('');
	}
});