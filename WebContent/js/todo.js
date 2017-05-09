$(document).ready(function () {
	getTasks();
});

function getTasks(){
	$.get("./TaskManager", "asd", function (response) {
		console.log(response);
		$("#toDoList").html("");
		for(task of response){
			$("#toDoList").append('<li class="list-group-item">' + task.name + '<button id=' + task.id + ' type="button" class="btn btn-xs btn-danger" onClick="removeTask('+ task.id + ')">Remove</button> <button id=' + task.id + ' type="button" class="btn btn-xs btn-success">Done</button></li>');
		}			
	});
}

function removeTask(id){
	$.ajax({
	    url: './TaskManager?removeTask='+ id,
	    type: 'DELETE',
	    success: function(result) {	        
	    }
	});
	getTasks();
}