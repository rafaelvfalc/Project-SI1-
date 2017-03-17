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

$(document).ready(function(){
	
getBalanceLoggedIn()
	
});
