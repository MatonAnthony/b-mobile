$(function(){

	$.ajax({
        method: "POST",
        url: "/home",
        data: {
            action: "authenticate"
        },
        success: function(resp){
			resp = JSON.parse(resp);
			if(resp.permissions === "STUDENT"){
				authStudent();
				console.log("Authentification réussie etudiant");
			}else{
				authTeacher();
				console.log("Authentification réussie professeur");
			}

        },
        error: function(error){
            console.log("Authentification echouée");
        }
    });
	// Register
	$("#registerButton").click(function(){
		password = $("input[name='password']").val();
		if (password === $("input[name='confirm']").val()) {
			$.ajax({
				method: "POST",
				url: "/home",
				data: {
					action: "register",
					username: $("input[name='login']").val(),
					password: password,
					name: $("input[name='name']").val(),
					firstname: $("input[name='firstname']").val(),
					email: $("input[name='email']").val()
				},
				success: function (resp) {
					resp = JSON.parse(resp);
					if (resp.permissions === "ETUDIANT") {
						authStudent();
					} else {
						authTeacher();
					}
				},
			});
		} else {
			$("#password_matching").show();
		}

	});
	//Connect
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
				if(resp.permissions === "STUDENT"){
					authStudent();
				}else{
					authTeacher();
				}
	        },
	        error: function(error){
	            console.log("Connexion echouée");
	        }
	    });
		return false;
	});
	//MyProfile
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
				console.log("profileButton retour OK")
			},
			error: function (resp) {
				console.log("profileButton retour not ok")
			}
		})
		return false;
	});

	//AddMobility
	$("#addMobilityRow").click(function(){

		var nbRow = $("#addMobilityTable").attr("numberOfRows");
		if(nbRow == 3)return false;
		nbRow++;
		$("#addMobilityTable").attr("numberOfRows", nbRow);
		var value = 
		"<tr> " +
			"<form>"+
				'<td>' + nbRow + '</td>'+
				'<td>'+
					'<select id="selectProgram' + nbRow + '" class="form-control">'+
						'<option >FAME</option>'+
						'<option>Erasmus</option>'+
						'<option>Erabel</option>'+
					'</select>'+
				'</td>'+
				'<td><input type="radio" name="optionsRadios' + nbRow + '" value="SMS" checked></input></td>'+
				'<td><input type="radio" name="optionsRadios' + nbRow + '" value="SMP"></input></td>'+
				'<td><select id="selectQuadri' + nbRow + '" class="form-control">'+
						'<option value="1">1</option>'+
						'<option value="2">2</option>'+
					'</select>'+
				'</td>'+
				'<td><select id="selectDep' + nbRow + '" class="form-control">'+
						'<option>Informatique</option>'+
						'<option>Dietetique</option>'+
						'<option>Imagerie Medicale</option>'+
						'<option>Chimie</option>'+
					'</select>'+
				'</td>'+
				'<td><select id="selectCountry' + nbRow + '" class="form-control">'+
					'</select>'+
				'</td>'+
			'</form>'+
		'</tr>';

		$("#addMobilityTableBody").append(value);
		addCountriesToSelector(nbRow);
		return true;
	});

	function addCountriesToSelector(id){
		var countries = $("#selectCountry1").html();
		var selectName = "#selectCountry"+id;
		$(selectName).html(countries);
	}


	//navBar
	$(".navButton").click(function(){
		switch($(this).attr("href")){
			case "#addMobility" :
			loadAddMobility();
			break;

		}

	});


	function authStudent(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "block");
		$("#navBarTeacher").css("display", "none");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "block");
		$("#teacherHomePage").css("display", "none");
	}

	function authTeacher(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "none");
		$("#navBarTeacher").css("display", "block");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "none");
		$("#teacherHomePage").css("display", "block");
	}

	function loadAddMobility(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "block");
		$("#navBarTeacher").css("display", "none");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "none");
		$("#teacherHomePage").css("display", "none");
		$("#addMobilityPage").css("display", "block");
		$.ajax({
	        method: "POST",
	        url: "/home",
	        data: {
	            action: "selectCountries"
	        },
	        success: function(resp){
	        	resp = JSON.parse(resp);
	        	var key;
	        	for(key in resp){
	        		$("#selectCountry1").append("<option>" + resp[key]['nameFr'] + "</option>"); 
	        	}
	        },
	        error: function(error){
	            console.log("Problème lors de la récuperation de la liste des pays");
	        }
	    });
	}

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

// Managing of the "myMobility" table

$(function(){

	$("#myMobility tr td:nth-child(6)").each(function(){
		console.log($(this).html());
		if ($(this).html() !== "Annulée"){
			$(this).next().append("<button>Annuler</button>");
		}else{
			$(this).parent().addClass("danger");
		}
		if ($(this).html() === "Non confirmée"){
			$(this).next().next().append("<button>Confirmer</button>");
		}
		//else{
		//	$(this).parent().addClass("success");
		//}
		// On garde ou pas ?
		
	});

});
