
var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function(){
	if(this.readyState == 4 && this.status == 200){
		var 
		console.log("response: "+this.responseText);
		preencherTabela(this.responseText);
	}
};
xhttp.open("GET", "/users", true);
xhttp.send();

function preencherTabela(users){
	var listaUsers = users;
	var resp = "";
	console.log("1 - Usuarios: "+listaUsers);
	console.log("2 - quant: "+listaUsers.length);
	console.log("3 - first user: "+listaUsers[0]);
	if(quant > 1){
		resp = "<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
		"<th style='width: 20%; text-align: center'>Contato</th>" +
		"<th style='width: 5%; text-align: center'></th></tr>";
		for(i=0; i< listaUsers.length; i++){
			resp += "<tr><td>" + listaUsers[i].nome + "</td>";
			resp += "<td>" + listaUsers[i].email + "</td>";
			/*
			 resp += "<td onClick(function(listaUsers[i].email){
						$.post("/user/favoritos"+listaUsers[i].id), function(){
							// atualizar pagina
						}>";
			if(logged.favoritos:contains(listaUsers[i].email){
				resp += "&#10008";
			
			} else {
				resp += "&#10004;"
			}
			*/
			resp += "<td>" + '&#10008'+ "</td></tr>";
		}
	} else {
		resp += "<h2>Nenhum outro usuario cadastrado!</h2>";
	}
	document.getElementById("anunciante").innerHTML = resp;		
}
