$(function(){

	$.ajax({
        method: "POST",
        url: "/home",
        data: {
            action: "authenticate"
        },
        success: function(resp){
			resp = JSON.parse(resp);
			if(resp.droits === "ETUDIANT"){
				window.location.pathname = "/studentHome.html";
			}else{
				window.location.pathname = "/teacherHome.html";
			}
            console.log("Authentification réussie");
        },
        error: function(error){
            console.log("Authentification echouée");
        }
    });


	$("#connectButton").click(function(){
		 $.ajax({
	        method: "POST",
	        url: "/home",
	        data: {
	            action: "login",
	            username : $("input[name='login']").val(),
	            password : $("input[name='password']").val()
	        },
	        success: function(resp){
			resp = JSON.parse(resp);
				if(resp.droits === "ETUDIANT"){
					window.location.pathname = "/studentHome.html";
				}else{
					window.location.pathname = "/teacherHome.html";
				}
	            console.log("Connexion réussie");
	        },
	        error: function(error){
	            console.log("Connexion echouée");
	        }
	    });
		 return false;
	});

	$("#profileButton").click(function () {
		$.ajax({
			method: "POST",
			url: "/profile",
			data: {
				action: "editProfile",
				name: $("input[name='name']").val(),
				firstname: $("input[name='firstname']").val(),
				gender: $("select[name='gender']").val(),
				// Birthdate on doit toujours se décider
				citizenship: $("input[name='citizenship']").val(),
				street: $("input[name='street']").val(),
				zipcode: $("input[name='zipcode']").val(),
				tel: $("input[name='tel']").val(),
				email: $("input[name='email']").val(),
				successfullYearsInCollege: $("input[name='successfullYearsInCollege']").val(),
				iban: $("input[name='iban']").val(),
				accountHolder: $("input[name='accountHolder']").val(),
				bankName: $("input[name='bankName']").val(),
				bic: $("input[name='bic']").val()
			},
			success: function (resp) {

			},
			error: function (resp) {

			}
		})
	});
});

// Managing of the confirmed table

$(function(){
	/*
	$.ajax({
        method: "POST",
        url: "/home",
        data: {
            action: "confirmedMobility",
        },
        success: function(resp){
        	resp=JSON.parse(resp);
        	
        },
        error: function(error){
            console.log("Connexion echouée");
        }
    });
	*/
	
	$("#tableConfirmed tr td:last-child").each(function(){
		if ($(this).html() === "Annulée"){
			$(this).parent().addClass("danger");
		}
	});
	
});
