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
			console.log(resp);
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
});