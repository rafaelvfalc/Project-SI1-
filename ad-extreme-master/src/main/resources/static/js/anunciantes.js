
$(document).ready(function(){
	$.get ("/users", function( allUsers ){
		$("#anunciante").html ("");

		var $userLogged = {};
		$.get("/loggedUser"), function( user ){
			$userLogged = user;
		}

		var $loggedFavoritos = $userLogged.favoritos;

		if(allUsers.length > 0){
			var resp =	"<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
			"<th style='width: 20%; text-align: center'>Contato</th>" +
			"<th style='width: 5%; text-align: center'></th></tr>";

			for (var i = 0; i < allUsers.length; i++) {
				resp += "<tr><td>"+allUsers[i].nome+"</td>" +
				"<td>"+allUsers[i].email+"</td>";
				if($userLogged.email != allUsers[i].email){
					resp += "<td onclick= mudarFavorito(allUsers[i].id) " +
					"style='cursor:pointer'> xxx";
					// verifica se o usuario acessado esta nos favoritos do usuario logado
					// dai adiciona uma string, se estiver, senao adiciona outro
					/*if( allUsers[i].email in $loggedFavoritos ){
						resp += "&#10008";
					} else {
						resp += "&#10004";
					}*/
				} else {
					resp += "<td>You"
				}
				resp += "</td></tr>";
			}

			$("#anunciante").append(resp);
		} else {
			$("#anunciante").append("<h2>Nenhum outro usuario cadastrado!</h2>")
		}
	})
})

function mudarFavorito( id ){
	$.post("/user/favoritos/"+id, function(){
	})
	location.reload();
}