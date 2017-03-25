$(document).ready(function(){
	$.get ("/users", function( allUsers ){
		$("#anunciante").html ("");
		
		$.get ("/loggedUser", function( user ){
			if(allUsers.length > 1){
				var resp =	"<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
				"<th style='width: 20%; text-align: center'>Contato</th>" +
				"<th style='width: 5%; text-align: center'></th></tr>";

				for (var i = 0; i < allUsers.length; i++) {
					console.log(allUsers[i]);
					resp += "<tr><td>"+allUsers[i].nome+"</td><td>"+allUsers[i].email+"</td>";
					if(user.email != allUsers[i].email){
						resp += "<td>"
						if( allUsers[i].email in user["favoritos"] ){
							resp += "<p onclick= addFavorito("+allUsers[i].id+") "
									+ "style='cursor:pointer'>&#10008";
						} else {
							resp += "<p onclick= removeFavorito("+allUsers[i].id+") "
								+ "style='cursor:pointer'> &#10004";
						}
						resp += "</p>"
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
})

function addFavorito( id ){
	$.post("/favoritos/add/"+id, function( response ){
		console.log(response);
	});
		location.reload();		
}

function removeFavorito( id ){
	$.post("/favoritos/remove/"+id, function( response ){
		console.log(response);
	});
		location.reload();		
}