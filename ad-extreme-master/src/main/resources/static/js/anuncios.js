$(document).ready(function(){
	var loc = window.location.href;
	var id = loc.split("=")[1];

	$.get("/anuncios/"+id, function( ads ){
		$.get("/users", function( allUsers ){
			$("#titulo").html("");
			$("#anuncio").html("");
			$("#valor").html("");
			$("#titulo").append(ads.titulo);
			$("#valor").append("R$ "+ads.preco);

			var respAnuncio = "";

			for(var i = 0; i < allUsers.length; i++){
				if(allUsers[i].email == ads.dono){
					respAnuncio = "<ul style='list-style-type: none'><li>Data: "+ads.dataDeCriacao+"</li><li><a style='cursor:pointer'" +
					" onclick='perfilAnunciante("+allUsers[i].id+")'>Proprietario do anuncio: "
					+allUsers[i].nome+"</a></li></ul>";
				}
			}

			$("#anuncio").append(respAnuncio);
			$.get("/loggedUser", function(logged){
				if(logged.anuncios.indexOf(ads._id.toString()) > -1  ){
					$("#comprar").click(function(){
						window.alert("Nao pode comprar seu proprio anuncio!")
					})
				} else {
					$("#comprar").click(function(){
						$.get("/anuncio/"+ads._id+"/buy", function(){
							perfilAnunciante(logged.id);
						})
						window.alert("Parabens pela compra de "+ads.titulo);
					})
				}

			})
		})
	})
})


function perfilAnunciante( id ){
	window.location = "perfil?id="+id;
}

