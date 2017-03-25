
$(document).ready(function(){
	var users = usuarios();

	for(user in users){
		$("#anunciante")
		.append("<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
				"<th style='width: 20%; text-align: center'>Contato</th>" +
				"<th style='width: 5%; text-align: center'></th></tr><tr>"+
				"<td>"+user.nome+"</td>" +
				"<td>"+user.email+"</td>"+
				"<td>&#10008;</td>" 
				 
				/*if(logged.favoritos:contains(user.email)){
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
				}*/
				 
				+"</tr>"
		);
	}
})

function usuarios(){
	var users = [];
	
	function usuarios(){
		$.get("/users", function(data){
			return data;
		})
	};
	//usuarios com o logged
	users = usuarios();
	
	/*	
	
	function usuarioLogado(){
		$.get("/logged"), function(data){
			return data;
		}
	};

	console.log("1 - Users: "+users);
	// usuario logged
	logged = usuarioLogado();
	
	console.log("2 - email Logged: "+logged);
	// retira o logged da lista
	for(i = 0; i < users.lenght; i++){
		if(users[i].email == logged){
			users.splice(i, 1);
			break;
		}
	}
	console.log("3 - Users(Sem Logged): "+users);
	return users;*/
}
