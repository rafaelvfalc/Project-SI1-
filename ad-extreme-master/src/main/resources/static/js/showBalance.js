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
	
	//Terminar de implementar
	
	$("#results").html("")
	$("#results").append("<h2> Resultados </h2>");
	
	$.get( "/anuncios", function( allAds ) {
		$( ".result" ).html( allAds );
		
		console.log(allAds)
		
		for (var i = 0; i < allAds.length; i++) {
			
			if($("#nome").val() != "" && allAds[i].titulo.includes($("#nome").val())) {
				$("#results").append(allAds[i].titulo)
			}
			
			if($('#emprego').is(':checked')) {
				if(allAds[i].tipo == "emprego") {
					$("#results").append(allAds[i].titulo)
				}
			}
			
			if($('#imovel').is(':checked')) {
				if(allAds[i].tipo == "imovel") {
					$("#results").append(allAds[i].titulo)
				}
			}
			
			if($('#movel').is(':checked')) {
				if(allAds[i].tipo == "movel") {
					$("#results").append(allAds[i].titulo)
				}
			}
			
			if($('#servico').is(':checked')) {
				if(allAds[i].tipo == "servico") {
					$("#results").append(allAds[i].titulo)
				}
			}			
		}
	});
}

$(document).ready(function(){
	
getBalanceLoggedIn()
	
});
