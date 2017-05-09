$(document).ready(function () {
	getTasks("All");
	addTask();
});

function getTasks(filter){
	
	$("li[role='presentation']").removeClass("active");
	$("#Filter"+filter).addClass("active");
	
	$.get("./TaskManager", {"filter": filter}, function (response) {
		console.log(response);
		
		$("#toDoList").html("");
		for(task of response){
			if(task.status == "COMPLETED"){
				$("#toDoList").append('<li class="list-group-item completed" >' + task.name + '<button id=' + task.id + ' type="button" class="btn btn-xs btn-danger" onClick="removeTask('+ task.id + ')">Remove</button></li>');
			}else{
				$("#toDoList").append('<li class="list-group-item">' + task.name + '<button id=' + task.id + ' type="button" class="btn btn-xs btn-danger" onClick="removeTask('+ task.id + ')">Remove</button> <button id=' + task.id + ' type="button" class="btn btn-xs btn-success"  onClick="taskDone('+ task.id + ')">Done</button></li>');
			}
			
		}			
	});
}

function taskDone(id){
	$.ajax({
	    url: './TaskManager?taskDone='+ id,
	    type: 'PUT',
	    success: function(result) {	        
	    }
	});
	getTasks("All");
}

function addTask(){
	$("#addTaskButton").click(function (e) {		
		$.post("./TaskManager", {taskName: $("#inputTaskName").val()}, function (response) {
			console.log("Task sended");
			$("#inputTaskName").val('');
			getTasks("All");			
		});
	});	
	
}

function removeTask(id){
	$.ajax({
	    url: './TaskManager?removeTask='+ id,
	    type: 'DELETE',
	    success: function(result) {	        
	    }
	});
	getTasks("All");
}