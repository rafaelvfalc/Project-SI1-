/**
 *
 */

function getBalanceLoggedIn() {
	
	var userLoggedIn = ""
	
	$.get( "/logged", function( data ) {
		$( ".result" ).html( data );
		userLoggedIn = data
		
		$.get( "/users", function( allUsers ) {
			$( ".result" ).html( allUsers );
			for (var i = 0; i < allUsers.length; i++) {
				if(allUsers[i].email === userLoggedIn) {
					var $spanSaldo = $("#saldoUser");
					
					if(parseInt(allUsers[i].saldo) != parseFloat(allUsers[i].saldo)) {
						$spanSaldo.append(allUsers[i].saldo)
					} else {
						$spanSaldo.append(allUsers[i].saldo  + ".00")
					}
				}
			}
		});
		
	});

}

function searchAd() {
	
	$("#results").html("")
	
	var resultsName = []
	
	$.get( "/anuncios", function( allAds ) {
		
		for (var i = 0; i < allAds.length; i++) {
			
			if($("#nome").val() == "") {
				resultsName = allAds;
				break;
			}
			
			if(allAds[i].titulo.includes($("#nome").val())) {
				resultsName.push(allAds[i])
			}
					
		}
		
		resultsType = []
		for (var i = 0; i < resultsName.length; i++) {
			
			if($('#emprego').is(':checked')) {
				if(resultsName[i].tipo == "emprego") {
					resultsType.push(resultsName[i])
				}
			}
			
			if($('#imovel').is(':checked')) {
				if(resultsName[i].tipo == "imovel") {
					resultsType.push(resultsName[i])
				}
			}
			
			if($('#movel').is(':checked')) {
				if(resultsName[i].tipo == "movel") {
					resultsType.push(resultsName[i])
				}
			}
			
			if($('#servico').is(':checked')) {
				if(resultsName[i].tipo == "servico") {
					resultsType.push(resultsName[i])
				}
			}
			
			if (!$('#emprego').is(':checked') && !$('#imovel').is(':checked') && !$('#movel').is(':checked') && !$('#servico').is(':checked')) {
				resultsType = resultsName
				break;
			}
		}
		
		resultsDate = []
		monthsName = ["jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"]
		yearToSearch = $("#date").val().split("-")[0]
		monthToSearch = monthsName[Number($("#date").val().split("-")[1]) - 1]
		dayToSearch = $("#date").val().split("-")[2]
		
		for (var i = 0; i < resultsType.length; i++) {
			
			if($("#date").val() == "") {
				resultsDate = resultsType;
				break;
			}

			day = resultsType[i].dataDeCriacao.split("-")[0]
			month = resultsType[i].dataDeCriacao.split("-")[1]
			year = resultsType[i].dataDeCriacao.split("-")[2].split(" ")[0]
			
			if(day == dayToSearch && month == monthToSearch && year == yearToSearch) {
				resultsDate.push(resultsType[i])
			}
			
		}
		
		firstRow = "<tr><th>Titulo</th><th>Tipo</th><th>Postado</th><th align=\"right\">Preço</th></tr>"
		
		$("#results").append(firstRow)
		
		for (var i = 0; i < resultsDate.length; i++) {

			precoFormatado = ""
			if(parseInt(resultsDate[i].preco) != parseFloat(resultsDate[i].preco)) {
				precoFormatado = resultsDate[i].preco
			} else {
				precoFormatado = resultsDate[i].preco  + ".00"
			}
			titulo = "<td><a style='cursor:pointer' onclick='anuncio("+resultsDate[i]._id+")'>" + resultsDate[i].titulo + "</a></td>"
			tipo = "<td>" + resultsDate[i].tipo + "</td>"
			data = "<td>" + resultsDate[i].dataDeCriacao + "</td>"
			preco = "<td> R$ " + precoFormatado + "</td>"
			$("#results").append("<tr>" + titulo + tipo + data + preco + "</tr>")
			
		}
	});
}

function anuncio( id ){
	window.location = "anuncio?id="+id;
}

$(document).ready(function(){
	
getBalanceLoggedIn()
	
});
