$(function(){
	//setInterval(function(){
		$.ajax({
			url: '/getMessages',
			success: function(response){
				$.each(response, function(i, id){
					var element = $('<div>').append('<i class="fa fa-refresh fa-spin"></i>').appendTo('#messages');
					
					$.get('/assets/messages/' + id, function(response){
						element.empty();
						
						var message = response.split('\n');
						
						element.append(message[1] + ' (' + message[0] + ') says: ' + message[2]);
					});
				});
			},
			dataType: 'json'
		});
	//}, 1000);
	
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