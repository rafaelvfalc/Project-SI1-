
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
	var resp = "";
	var quant = users.length;
	console.log("1 - Usuarios: "+users);
	console.log("2 - quant: "+quant);
	console.log("3 - first user: "+users[0]);
	if(quant > 1){
		resp = "<tr><th style='width: 15%; text-align: center'>Anunciante</th>" +
		"<th style='width: 20%; text-align: center'>Contato</th>" +
		"<th style='width: 5%; text-align: center'></th></tr>";
		for(i=0; i< quant; i++){
			resp += "<tr><td>" + users[i].nome + "</td>";
			resp += "<td>" + users[i].email + "</td>";
			resp += "<td>" + '&#10008'+ "</td></tr>";
		}
	} else {
		resp += "<h2>Nenhum outro usuario cadastrado!</h2>";
	}
	document.getElementById("anunciante").innerHTML = resp;		
}
