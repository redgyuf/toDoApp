$(document).ready(function () {
	$("#btn").click(function (e) {
		e.preventDefault();
		
		$.post("./Login", {inputEmail: $("#inputEmail").val(), inputPassword: $("#inputPassword").val()}, function (response) {
			if(response == 202){
				window.location.replace("./todo.jsp");
				console.log("Login was successful");
			}
			if(response == 403){
				document.getElementById("status").style.display="block";
				$("#status").html("Invalid E-Mail or password!");
				console.log("Login rejected");
			}			
		});
	});
});