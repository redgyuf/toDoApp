$(document).ready(function () {
//	$("#getList").click(function (e) {
//		e.preventDefault();
//		console.log(this.id);
//		
//		//btn.onClick = (event) => console.log(asd);
		
		$.get("./TaskManager", "asd", function (response) {
			console.log(response);
			for(task of response){
				$("#toDoList").append('<li class="list-group-item">' + task.name + '<button id=' + task.id + ' type="button" class="btn btn-xs btn-danger" onClick="removeTask('+ task.id + ')">Remove</button> <button id=' + task.id + ' type="button" class="btn btn-xs btn-success">Done</button></li>');
			}			
		});
//	});	
});

function removeTask(id){
	$.ajax({
	    url: './TaskManager?removeTask='+ id,
	    type: 'DELETE',
	    success: function(result) {	        
	    }
	});
	console.log(id);
}