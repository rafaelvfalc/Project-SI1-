$(document).ready(function(){
	welcome();
	showNewestAd();
	recommendation();
})

function welcome() {
	
	var userLoggedIn = ""
	
	$.get( "/logged", function( data ) {
		$( ".result" ).html( data );
		userLoggedIn = data
		
		$.get( "/users", function( allUsers ) {
			$( ".result" ).html( allUsers );
			for (var i = 0; i < allUsers.length; i++) {
				if(allUsers[i].email === userLoggedIn) {
					var $welcome = $("#welcomeDiv");
					$welcome.append("<h1>Bem vindo " + allUsers[i].nome + "!</h1>")
				}
			}
		});
		
	});

}

function showNewestAd() {
	$.get( "/anuncios", function( allAds ) {
		
		newestAd = allAds[0]
		for (var i = 0; i < allAds.length; i++) {
			if(newestAd.dataDeCriacao < allAds[i].dataDeCriacao){
				newestAd = allAds[i]
			}
		}
		
		if(newestAd != undefined){
			
			precoFormatado = ""
				if(parseInt(newestAd.preco) != parseFloat(newestAd.preco)) {
					precoFormatado = newestAd.preco
				} else {
					precoFormatado = newestAd.preco  + ".00"
				}
				
				titulo = "<td>" + newestAd.titulo + "</td>"
				tipo = "<td>" + newestAd.tipo + "</td>"
				data = "<td>" + newestAd.dataDeCriacao + "</td>"
				preco = "<td> R$ " + precoFormatado + "</td>"
				$("#newestAd").append("<tr>" + titulo + tipo + data + preco + "</tr>")
			
		}
		
	});
}

function recommendation() {
	
	$.get( "/anuncios", function( allAds ) {
		
		$.get( "/logged", function( userLogged ) {
			recommendation = allAds[0]
			for (var i = 0; i < allAds.length; i++) {
				if(allAds[i].dono != userLogged) {
					while(true) {
						recommendation = allAds[getRandomArbitrary(0, allAds.length)]
						if(recommendation.dono != userLogged){
							break
						}
					}
					break;
				}
			}
			
			precoFormatado = ""
			if(parseInt(recommendation.preco) != parseFloat(recommendation.preco)) {
				precoFormatado = recommendation.preco
			} else {
				precoFormatado = recommendation.preco  + ".00"
			}
				
			titulo = "<td>" + recommendation.titulo + "</td>"
			tipo = "<td>" + recommendation.tipo + "</td>"
			data = "<td>" + recommendation.dataDeCriacao + "</td>"
			preco = "<td> R$ " + precoFormatado + "</td>"
			$("#recommendation").append("<tr>" + titulo + tipo + data + preco + "</tr>")
			
		});
		
	});	
	
	function getRandomArbitrary(min, max) {
	    return Math.floor(Math.random() * (max - min) + min);
	}
	
}
