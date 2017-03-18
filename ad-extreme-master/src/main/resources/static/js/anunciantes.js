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
				"<td>"+user.email+"</td>"+
				"<td>&#10008;</td>" 
				/* 
				if(logged.favoritos contain user.email){
					+"<td onClick(function(user.email){
						$.post("/user/favoritos"+user.id), function(){
							//atualizar a pagina
						}
					}>&#10008;</td>"
				} else {
					+"<td onClick(function(user.email){
						$.delete("/user/favoritos"+user.id), function(){
							//atualizar a pagina
						}
					}>&#10004;</td>"
				}
				*/
		);
	}
})