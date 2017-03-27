$(document).ready(function(){
	$.get ("/users", function( allUsers ){
		$("#anunciante").html ("");

		$.get ("/loggedUser", function( user ){
			var resp =	"<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
			"<th style='width: 20%; text-align: center'>Contato</th>" +
			"<th style='width: 5%; text-align: center'></th></tr>";

			for (var i = 0; i < allUsers.length; i++) {
				resp += "<tr><td>"+allUsers[i].nome+"</td>" +
				"<td>"+allUsers[i].email+"</td>";
				if(user.email != allUsers[i].email){
					if((user.favoritos).indexOf(allUsers[i].email) > -1){
						resp += "<td><span onclick= removerFavorito("+allUsers[i].id+") "
						+ "style='cursor:pointer' title='retirar favorito'>&#10008";
					} else {
						resp += "<td><span onclick= addFavorito("+allUsers[i].id+") "
						+ "style='cursor:pointer' title='adicionar favorito'>&#10004";
					}
					resp += "</span>";
				} else {
					resp += "<td>You";
				}
				resp += "</td></tr>";
			}
			$("#anunciante").append(resp);
		})		
	})
})

function addFavorito( id ){
	$.get("/favoritos/add/"+id, function(user){
		location.reload();
	})
}

function removerFavorito( id ){
	$.get("/favoritos/remove/"+id, function(user){
		location.reload();
	})
}
