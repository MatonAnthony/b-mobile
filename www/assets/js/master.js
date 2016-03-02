$(function(){


	$("#connectButton").click(function(){
		 $.ajax({
	        method: "POST",
	        url: "/home",
	        data: {
	            action: "login",
	            username : $("input[name='username']").val(),
	            password : $("input[name='password']").val()
	        },
	        success: function(resp){
	            console.log("Connexion réussie");
	        },
	        error: function(error){
	            console.log("Connexion echouée");
	        }
	    });
		 return false;
	});
});