$(document).ready(function(){
	var loc = window.location.href;
	var id = loc.split("=")[1];

	$.get("/users/"+id, function( user ){
		$.get("/loggedUser", function( logged ){
			$.get("/anuncios", function( allAds ){
				$.get("/users", function( allUsers) {
					$("#username").html ("");
					$("#perfil").html ("");
					$("#body").html ("");
					$("#footer").html ("");


					$("#perfil").append("<li>Contato: "+user.email+"</li></ul");

					var respBody ="";
					if(user.anuncios.isEmpty || user.anuncios[0] == ""){
						respBody += "<h2>Nenhum anuncio cadastrado ainda!</h2>";
					} else {
						respBody += "<h2 style='text-align:center;'>Anuncios</h2>"
							respBody += "<table style='width: 90%; text-align: center;' class='table table-bordered'>" +
							"<tr><th style='text-align: center;'>Nome</th><th style='text-align: center;'>Tipo</th>" +
							"<th style='text-align: center;'>Data de criacao</th><th style='text-align: center;'>Preco</th></tr>"
							for(var i=0; i < user.anuncios.length; i++){
								var anuncio = user.anuncios[i];
								var	idAnuncio = parseInt(anuncio);
								for(var j=0; j < allAds.length; j++){
									if(allAds[j]._id == idAnuncio){
										respBody += "<tr><td><a style='cursor:pointer' onclick='anuncio("+allAds[j]._id+")'>"
										+allAds[j].titulo+"</a></td><td>"+allAds[j].tipo+
										"</td><td>"+allAds[j].dataDeCriacao+"</td><td>R$ "+allAds[j].preco+"</td></tr>";
									}
								}
							}
						respBody += "</table>"
					}
					var respFooter = ""
						if(logged.email != user.email){
							$("#username").append("Page de "+user.nome);
							respBody +="";
							if((logged.favoritos).indexOf(user.email) > -1){
								// add notas
								respFooter += "<button type='button' onclick='removerFavorito("
									+user.id+")'>Desfavoritar</button>"
							} else {
								respFooter += "<button type='button' onclick='addFavorito("
									+user.id+")'>Favoritar</button>"
							}
						} else {
							$("#username").append("Minha Pagina");
							if(logged.favoritos.isEmpty || logged.favoritos[0] == ""){
								respFooter += "<h2>Nenhum usuario favoritado!</h2>"
							} else {
								respFooter += "<h2 style='text-align:center;'>Meus Favoritos</h2><br /><br />";
								respFooter += "<table style='width: 90%; text-align: center;' class='table table-bordered'>" +
								"<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
								"<th style='width: 20%; text-align: center'>Contato</th>" +
								"<th style='width: 5%; text-align: center'></th></tr>"
								for(var i=0; i<user.favoritos.length; i++){
									var emailFavorito = user.favoritos[i];
									for(var j=0; j < allUsers.length; j++){
										if(allUsers[j].email == emailFavorito){
											respFooter += "<tr><td><a style='cursor:pointer' onclick='perfilAnunciante("+allUsers[j].id+")'>"
											+allUsers[j].nome+"</a></td><td>"+allUsers[j].email+"</td><td><span onclick= removerFavorito("
											+allUsers[j].id+") style='cursor:pointer' title='retirar favorito'>&#10008";
										}
									}
								}
								respFooter += "</table>"
							}
						}
					$("#body").append(respBody);
					$("#footer").append(respFooter);
				})
			})
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

function perfilAnunciante( id ){
	window.location = "perfil?id="+id;
}

function anuncio( id ){
	window.location = "anuncio?id="+id;
}