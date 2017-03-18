var users = [];

function getUsers(){
	$.get("/users", function(data, status){
		users = data;
	})
}

function removeLogged(){
	var logged = {};
	$.get("/loggedUser"), function(data){
		logged = data;
	}

	for(i = 0; i < users.lenght; i++){
		if(users[i].email == logged.email){
			users.splice(i, 1);
		}
	}
}

$(document).ready(function(){
	getUsers();
	removeLogged();
	
	for(user in users){
		$("#anunciante")
		.append("<td>"+user.name+"</td>" +
				"<td>"+user.email+"</td>" +
		"<td>&#10008;</td>");
	}
})