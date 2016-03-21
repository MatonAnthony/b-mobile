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
			}else{
				authTeacher();
			}

        },
        error: function(error){
            console.log("Authentification echouée");
        }
    });
	// Register
	$("#registerButton").click(function(){
		password = $("#passwordRegister").val();
		if (password === $("#confirmRegister").val()) {
			$.ajax({
				method: "POST",
				url: "/home",
				data: {
					action: "register",
					username: $("#loginRegister").val(),
					password: password,
					name: $("#nameRegister").val(),
					firstname: $("#firstnameRegister").val(),
					email: $("#emailRegister").val()
				},
				success: function (resp) {
					resp = JSON.parse(resp);
					console.log(resp);
					if (resp.permissions === "STUDENT") {
						authStudent();
					} else {
						authTeacher();
					}
				},
				error: function(error){
					console.log("erreur lors de l'enregistrement");
				}
			});
		} else {
			$("#password_matching").show();
		}
		return false;
	});

	$("#registerLink").click(function(){
		loadRegisterPage();
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
	//Disconnect
	
	function disconnect(){
			$.ajax({
				url: "/home",
				type: 'POST',
				data: {
					action:"disconnect"
				},
				success: function(reponse) {
					$("#loginPage").css("display", "block");
					$("#navBarStudent").css("display", "none");
					$("#navBarTeacher").css("display", "none");
					$("#profilePage").css("display", "none");
					$("#studentHomePage").css("display", "none");
					$("#teacherHomePage").css("display", "none");
					$("#addMobilityPage").css("display", "none");
				},
				error: function(e) {
					console.log(e.message);
				}
			});
	}
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
					'</select>'+
				'</td>'+
				'<td><select id="selectCountry' + nbRow + '" class="form-control">'+
					'</select>'+
				'</td>'+
			'</form>'+
		'</tr>';

		$("#addMobilityTableBody").append(value);
		addDepartmentsToSelector(nbRow);
		addCountriesToSelector(nbRow);
		addProgramsToSelector(nbRow);
		return true;
	});

	$("#addMobilityBtn").click(function(){
		var nbRow = $("#addMobilityTable").attr("numberOfRows");
		for (var i = 1; i < nbRow; i++){
			$.ajax({
		        method: "POST",
		        url: "/home",
		        data: {
		            action: "addMobility",
		            numPreference: i,
		            program: $("#selectProgram"+i).val()
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
		


		return true;
	});

	function addDepartmentsToSelector(id){
		var departments = $("#selectDep1").html();
		var selectName = "#selectDep"+id;
		$(selectName).html(departments);	
	}

	function addCountriesToSelector(id){
		var countries = $("#selectCountry1").html();
		var selectName = "#selectCountry"+id;
		$(selectName).html(countries);
	}
	
	function addProgramsToSelector(id){
		var programs = $("#selectProgram1").html();
		var selectName = "#selectProgram"+id;
		$(selectName).html(programs);
	}


	//navBar
	$(".navButton").click(function(){
		$(".active").removeClass("active");
		switch($(this).attr("href")){
			case "#myMobility":
				$(".navButton[href='#myMobility']").parent().addClass("active");
				authStudent();
				break;
			case "#confirmedMobility":
				$(".navButton[href='#confirmedMobility']").parent().addClass("active");
				authTeacher();
				break;
			case "#addMobility" :
				loadAddMobility();
				$(".navButton[href='#addMobility']").parent().addClass("active");
				break;
			case "#disconnect" :
				disconnect();
				$(".navButton[href='#confirmedMobility']").parent().addClass("active");
				$(".navButton[href='#myMobility']").parent().addClass("active");
				break;
			case "#list":
				$(".navButton[href='#list']").parent().addClass("active");
				loadList();
				break;
		}

	});
	
	//Chargement des pages.
	function authStudent(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "block");
		$("#navBarTeacher").css("display", "none");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "block");
		$("#teacherHomePage").css("display", "none");
		$("#registerPage").css("display", "none");
		$("#listPage").css("display", "none");
	}

	function authTeacher(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "none");
		$("#navBarTeacher").css("display", "block");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "none");
		$("#teacherHomePage").css("display", "block");
		$("#registerPage").css("display", "none");
		$("#listPage").css("display", "none");
		loadConfirmedMobility();
	}
	
	function loadList(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "none");
		$("#navBarTeacher").css("display", "block");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "none");
		$("#teacherHomePage").css("display", "none");
		$("#registerPage").css("display", "none");
		$("#listPage").css("display", "block");
	}

	function loadAddMobility(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "block");
		$("#navBarTeacher").css("display", "none");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "none");
		$("#teacherHomePage").css("display", "none");
		$("#addMobilityPage").css("display", "block");
		$("#listPage").css("display", "none");
		
		if($("#selectCountry1").html()== ""){
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
		if($("#selectDep1").html()== ""){
		    $.ajax({
		        method: "POST",
		        url: "/home",
		        data: {
		            action: "selectDepartments"
		        },
		        success: function(resp){
		        	resp = JSON.parse(resp);
		        	var key;
		        	for(key in resp){
		        		$("#selectDep1").append("<option>" + resp[key]['label'] + "</option>"); 
		        	}
		        },
		        error: function(error){
		            console.log("Problème lors de la récuperation de la liste des departements");
		        }
		    });
		}
		if($("#selectProgram1").html()== ""){
		    $.ajax({
		        method: "POST",
		        url: "/home",
		        data: {
		            action: "selectPrograms"
		        },
		        success: function(resp){
		        	resp = JSON.parse(resp);
		        	var key;
		        	for(key in resp){
		        		$("#selectProgram1").append("<option>" + resp[key]['name'] + "</option>"); 
		        	}
		        },
		        error: function(error){
		            console.log("Problème lors de la récuperation de la liste des programmes");
		        }
		    });
		}
	}

	function loadRegisterPage(){
		$("#loginPage").css("display", "none");
		$("#navBarStudent").css("display", "none");
		$("#navBarTeacher").css("display", "none");
		$("#profilePage").css("display", "none");
		$("#studentHomePage").css("display", "none");
		$("#teacherHomePage").css("display", "none");
		$("#registerPage").css("display", "block");
		$("#listPage").css("display", "none");
	}

});

// Managing of the confirmed table

function loadConfirmedMobility(){
	$(function(){
		$.ajax({
			method: "POST",
			url: "/home",
			data: {
				action: "selectConfirmedMobility",
			},
			success: function(resp){
				resp=JSON.parse(resp);
				console.log(resp);
			},
			error: function(error){
				console.log("Connexion echouée");
			}
		});
		
		$("#tableConfirmed tr td:last-child").each(function(){
			if ($(this).html() === "Annulée"){
				$(this).parent().addClass("danger");
			}
		});
	
	});
}

// Managing of the "myMobility" table

$(function(){

	$("#myMobility tr td:nth-child(6)").each(function(){
		if ($(this).html() !== "Annulée"){
			$(this).next().append("<button>Annuler</button>");
		}else{
			$(this).parent().addClass("danger");
		}
		if ($(this).html() === "Non confirmée"){
			$(this).next().next().append("<button>Confirmer</button>");
		}
	});

});
