// var relativeUrlForPaymentTypes = "/api/payment/types"
// var relativeUrlForPaymentTypes = "/payment/types"
var relativeUrlForPaymentTypes = "/payment-microservice/payment/types"
var relativeUrlForLogin = "/users/login";
	
var currentUserKey = "currentUser";



$(document).ready(function() {

	var numberOfAttempts = 0;
	while(!isLoggedIn()) {
		getTokenFromBackend();
		numberOfAttempts++;
		if (numberOfAttempts == 3) {
			toastr.error("This magazine is not logged on the PaymentConcentrator!");
			return;
		}
	}
	
	
	loadPaymentTypes();
});


function loadPaymentTypes() {
	var paymentTypes = getPaymentTypes();
	
	if(paymentTypes) {
		if(paymentTypes.length > 0) {
			$.each(paymentTypes, function(index, paymentType) {
					$("#id_payment_types").append('<li><a href="' + paymentType.url + '">' + paymentType.name + '</a></li>');
				}
			);
		}
		else {
			toastr.error("Not found payment types!");
		}
	}
	else {
		toastr.error("Not found payment types!");
	}
}


function getPaymentTypes() {
	var paymentTypes = [];
	
	$.ajax({
		async: false,
		type : "GET",
		url : relativeUrlForPaymentTypes,
		//dataType : "json",
		contentType: "application/json",
		cache: false,
		success : function(receivedPaymentTypes) {
			paymentTypes = receivedPaymentTypes;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { 
					toastr.error("Ajax ERROR: " + errorThrown + ", STATUS: " + textStatus); 
					return [];
		}
	});
	
	return paymentTypes;
}



function getTokenFromBackend() {
	$.ajax({
		async: false,
		type : "GET",
		url : relativeUrlForLogin,
		dataType : "json",
		contentType: "application/json",
		cache: false,
		success : function(loginResult) {
			saveToken(loginResult.token);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) { 
					toastr.error("Ajax ERROR: " + errorThrown + ", STATUS: " + textStatus); 
					return [];
		}
	});
}

function initPageForLoggedCompany() {
	$("#id_logout_cell").append('<button id="id_logout_button">Logout</button>');

	loadPayLink();

	$("#id_logout_button").click(function(event) {
			event.preventDefault()

			logout();
			$(this).remove();
		}
	);
}




function isLoggedIn() {
	if (this.getToken() !== '') {
		return true;
	}
	return false;
}

function saveToken(username, password, varToken) {
	localStorage.setItem(currentUserKey, JSON.stringify({roles: getRoles(varToken), token: varToken}));
}

function getToken() {
	const currentUser = JSON.parse(localStorage.getItem(currentUserKey));
	const token = currentUser && currentUser.token;
	return token ? token : '';
}

function getRolesFromToken(token) {
	var jwtData = token.split('.')[1];
	var decodedJwtJsonData = window.atob(jwtData);
	var decodedJwtData = JSON.parse(decodedJwtJsonData);
	return decodedJwtData.roles.map(x => x.authority) || [];
}

function logout() {
	localStorage.removeItem(currentUserKey);
}