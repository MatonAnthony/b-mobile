$(function () {

	$.ajax({
		method: "POST",
		url: "/home",
		data: {
			action: "authenticate"
		},
		success: function (resp) {
			resp = JSON.parse(resp);
             if(null != history.state){
            	changePage();
            }else if(resp['permissions'] === "STUDENT"){
                 $('#permissionHideFilds').val('');
                 $('#permissionHideFilds').val('STUDENT');
                 authStudent();
            }else{
                 $('#permissionHideFilds').val('');
                 $('#permissionHideFilds').val('TEACHER');
                 authTeacher();
            }

            console.log("Authentification OK");
        },
        error: function (error) {
            console.log("Authentification echouée");
        }
    });

    $("#logo").click(function(){
       document.location.href="home";
    });

    // HTML Based form validation :
    window.onload = function(){
        // Validation for "Edit profile"
        $("#profile_name").change(function(){
            var name = document.getElementById("profile_name")
            if(name.validity.patternMismatch){
                console.log("event fired");
                printToaster("info", "Le nom peut uniquement contenir des lettres et des tirets");
            }else{
                $("#profile_name").removeClass(":invalid");
            }
        });

        $("#profile_firstname").change(function(){
            var firstname = document.getElementById("profile_firstname")
            if(firstname.validity.patternMismatch){
                console.log("event fired");
                printToaster("info", "Le nom doit uniquement contenir des lettres et des tirets");
            }else{
                $("#profile_name").removeClass(":invalid");
            }
        });

        $("#profile_citizenship").change(function(){
            var citizenship = document.getElementById("profile_citizenship");
            if(citizenship.validity.patternMismatch){
                printToaster("info", "La nationalité ne peut contenir que des lettres, tirets et espaces");
            }
        });

        $("#profile_street").change(function(){
            var street = document.getElementById("profile_street");
            if(street.validity.patternMismatch){
                printToaster("info", "La rue ne peut contenir que des lettres, tirets et espaces");
            }
        });

        $("#profile_city").change(function(){
            var city = document.getElementById("profile_city");
            if(city.validity.patternMismatch){
                printToaster("info", "La ville ne peut contenir que des lettres, tirets et espaces");
            }
        });

        $("#profile_tel").change(function(){
            var tel = document.getElementById("profile_tel");
            if(tel.validity.patternMismatch){
                printToaster("info", "Le numéro de téléphone doit être valide et de la forme : " +
                    "0032 470 00 00 00");
            }
        });

        $("#profile_email").change(function(){
           var email = document.getElementById("profile_email");
            if(email.validity.typeMismatch){
                printToaster("info", "E-mail invalide");
            }
        });

        $("#profile_successfullYearsInCollege").change(function(){
            var sucessfullYearsInCollege = document.getElementById("profile_successfullYearsInCollege");
            if(sucessfullYearsInCollege.validity.typeMismatch){
                printToaster("info", "Veuillez entrer un nombre d'années !");
            }
        });

        $("#profile_accountHolder").change(function(){
            var accountHolder = document.getElementById("profile_accountHolder");
            if(accountHolder.validity.patternMismatch){
                printToaster("info", "Veuillez entrer un prénom et un nom cohérent !");
            }
        });

        $("#profile_bankName").change(function(){
            var bankName = document.getElementById("profile_bankName");
            if(bankName.validity.patternMismatch){
                printToaster("info", "Veuillez entrer un nom de banque cohérent !");
            }
        });

        // Validation for "Register.html"
        $("#loginRegister").change(function(){
            var login = document.getElementById("loginRegister");
            if(login.validity.patternMismatch) {
                printToaster("info", "Le pseudo doit contenir au moins 6 caractères");
            }
        });

        $("#passwordRegister").change(function(){
            var password = document.getElementById("passwordRegister");
            if(password.validity.patternMismatch) {
                printToaster("info", "Le mot de passe doit contenir au moins 6 caractères");
            }
        });

        $("#confirmRegister").change(function(){
           if($("#confirmRigster").val() != $("#passwordRegister").val()) {
               printToaster("warning", "Les deux mots de passes doivent être identiques");
           }
        });

        $("#nameRegister").change(function(){
            var name = document.getElementById("nameRegister");
            if(name.validity.patternMismatch) {
                printToaster("info", "Le nom ne peut pas contenir de ponctuation ou de chiffres");
            }
        });

        $("#firstnameRegister").change(function(){
            var firstname = document.getElementById("firstnameRegister");
            if(firstname.validity.patternMismatch) {
                printToaster("info", "Le prénom ne peut pas contenir de ponctuation ou de chiffres");
            }
        });

        $("#emailRegister").change(function(){
           var email = document.getElementById("emailRegister");
            if(email.validity.typeMismatch) {
                printToaster("info", "E-mail invalide");
            }
        });

        // Add Partner validation
        $("#add_partner_legal_name").change(function(){
            var partner_legal_name = document.getElementById("add_partner_legal_name");
            if(partner_legal_name.validity.patternMismatch) {
                printToaster("info", "Le nom du partenaire ne peut pas contenir de caractères spéciaux");
            }
        });

        $("#add_partner_business_name").change(function(){
            var partner_business_name = document.getElementById("add_partner_bussiness_name");
            if(partner_business_name.validity.patternMismatch) {
                printToaster("info", "Le nom d'affaire ne peut pas contenir de caractères spéciaux");
            }
        });

        $("#add_partner_full_name").change(function(){
            var partner_full_name = document.getElementByID("add_partner_full_name");
            if(partner_full_name.validity.patternMismatch) {
                printToaster("info", "Le nom du partenaire ne peut pas contenir de caractères spéciaux");
            }
        });

        $("#add_partner_city").change(function(){
            var partner_city = document.getElementById("add_partner_city");
            if(partner_city.validity.patternMismatch) {
                printToaster("info", "Le nom de la ville ne peut pas contenir de caractères spéciaux");
            }
        });

        $("#add_partner_state").change(function(){
            var partner_state = document.getElementById("add_partner_state");
            if(partner_state.validity.patternMismatch) {
                printToaster("info", "Le nom de la région ne peut contenir de caractères spéciaux");
            }
        });

        $("#add_partner_email").change(function(){
            var partner_email = document.getElementById("add_partner_email");
            if(partner_email.validity.typeMismatch) {
                printToaster("info", "Email invalide !");
            }
        });

        $("#add_partner_website").change(function(){
            var partner_website = document.getElementById("add_partner_website");
            if(partner_website.validity.typeMismatch) {
                printToaster("info", "URL Invalide !");
            }
        });

    };
    
    window.onpopstate = function(event) {

        changePage();
    };
    
    function changePage() {
        var state = history.state;

        if (null === state){
            //disconnect();
            return true;
        }
        switch (state['page']) {
            case "userList" :
                loadUserList();
                break;
            case "list" :
                loadList();
                break;
            case "confirmedMobility" :
                authTeacher();
                break;
            case "myMobility" :
                authStudent();
                break;
            case "addMobility" :
                loadAddMobility();
                break;
            case "addPartner" :
                loadAddPartner();
                break;
            case "myInformations" :
                loadProfilePage();
                break;
			case "payment" :
				loadPaymentPage();
				break;
            default:
                disconnect();
                break;
        }
    }

    // Register
    $("#registerButton").click(function () {
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
                    email: $("#emailRegister").val(),
                    department: $('#departmentRegister').val()
                },
                success: function (resp) {
                    resp = JSON.parse(resp);
                    if (resp.permissions === "STUDENT") {
                        authStudent();
                    } else {
                        authTeacher();
                    }
                },
                error: function (error) {
					error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
                }
            });
        } else {
            printToaster("error", "Les deux mots de passes introduits ne correspondent pas");
        }
        return false;
    });

    $("#backToLogin").click(function(){
    	$(".page").css("display", "none");
        $("#loginPage").css("display", "block");
    });

    $("#registerLink").click(function() {
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
					history.pushState({page:"myMobility"}, "Mes mobilités", "/home#myMobility");
				}else{
					authTeacher();
					history.pushState({page:"confirmedMobility"}, "Mobilites Confirmées", "/home#confirmedMobility");
				}
	        },
	        error: function(error){
	            error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
	        }
	    });
		return false;
	});

    //Disconnect
    function disconnect() {
        $('#permissionHideFilds').val('');
        $.ajax({
            url: "/home",
            type: 'POST',
            data: {
                action: "disconnect"
            },
            success: function (reponse) {
                $(".page").css("display", "none");
                $("#loginPage").css("display", "block");
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
        $(".navButton[href='#confirmedMobility']").parent().addClass("active");
        $(".navButton[href='#myMobility']").parent().addClass("active");
    }

    //MyProfile
    $("#formProfile").on("click.profileButton", "#profileButton", function () {
    	var idUser = $("#formProfile").attr("idUser");
    	if(idUser === undefined) {
    		idUser =-1;
    	}
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "updateUser",
                name: $("input[name='name']").val(),
                firstname: $("input[name='firstname']").val(),
                gender: $("select[name='gender']").val(),
                birthdate: $("input[name='birthdate']").val(),
                citizenship: $("input[name='citizenship']").val(),
                street: $("input[name='street']").val(),
                houseNumber: $("input[name='houseNumber']").val(),
                mailbox: $("input[name='mailbox']").val(),
                zipcode: $("input[name='zipcode']").val(),
                city: $("input[name='city']").val(),
                country: $("select[name='country']").val(),
                tel: $("input[name='tel']").val(),
                email: $("input[name='email']").val(),
                successfullYearsInCollege: $("input[name='successfullYearsInCollege']").val(),
                iban: $("input[name='iban']").val(),
                accountHolder: $("input[name='accountHolder']").val(),
                bankName: $("input[name='bankName']").val(),
                bic: $("input[name='bic']").val(),
                idUser: idUser,
                verNr: $("#formProfile").attr("verNb")
            },
            success: function (resp) {
                printToaster("success", "Vos informations ont bien été modifiées.");
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
        $("#formProfile").off("click.profileButton").on("click.profileButton", "#profileButton");
        return false;
    });

    //AddMobility
    $("#addMobilityRow").click(function () {

        var nbRow = $("#addMobilityTable").attr("numberOfRows");
        nbRow++;
        $("#addMobilityTable").attr("numberOfRows", nbRow);
        var value =
            "<tr> " +
            "<form>" +
            '<td>' + nbRow + '</td>' +
            '<td>' +
            '<select id="selectProgram' + nbRow + '" class="form-control programSelector">' +
            '</select>' +
            '</td>' +
            '<td><input type="radio" name="optionsRadios' + nbRow + '" value="SMS" checked /></td>' +
            '<td><input type="radio" name="optionsRadios' + nbRow + '" value="SMP"/></td>' +
            '<td><select id="selectQuadri' + nbRow + '" class="form-control">' +
            '<option value="1">1</option>' +
            '<option value="2">2</option>' +
            '</select>' +
            '</td>' +
            '<td><select id="selectAccademicYear' + nbRow + '" class="form-control">' +
            '</select>' +
            '</td>' +
            '<td><select id="selectDep' + nbRow + '" class="form-control">' +
            '</select>' +
            '</td>' +
            '<td><select id="selectCountry' + nbRow + '" class="form-control countrySelector">' +
            '</select>' +
            '</td>' +
            '<td><select id="selectPartner' + nbRow + '" class="form-control">' +
            '</select>' +
            '</td>' +
            '</form>' +
            '</tr>';

        $("#addMobilityTableBody").append(value);
        addAccademicYearsToSelector(nbRow);
        addDepartmentsToSelector(nbRow);
        addCountriesToSelector(nbRow);
        addProgramsToSelector(nbRow);
        addPartnersToSelector(nbRow);
        showCountriesByProgram(nbRow, 'Erasmus+');
        showPartnerByCountry(nbRow, 'DE');
        return true;
    });

    $("#delMobilityRow").click(function(){
        var nbRow = $("#addMobilityTable").attr("numberOfRows");
        if(nbRow==1) return false;
        $("#addMobilityTableBody > tr:nth-child("+nbRow+")").remove();
        nbRow--;
        $("#addMobilityTable").attr("numberOfRows", nbRow);
    });

    $("#addMobilityBtn").click(function () {
        var nbRow = $("#addMobilityTable").attr("numberOfRows");
        for (var i = 1; i <= nbRow; i++) {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "addMobility",
                    preferenceOrder: i,
                    program: $("#selectProgram" + i).val(),
                    type: $("input[name='optionsRadios" + i + "']:checked").val(),
                    quadrimestre: $("#selectQuadri" + i).val(),
                    department: $("#selectDep" + i).val(),
                    country: $("#selectCountry" + i).val(),
                    year: $("#selectAccademicYear" + i).val(),
                    partner:$("#selectPartner" + i +" >option:selected").attr("data-partnerId")
                },
                success: function (resp) {
                    printToaster("success", "La/Les demande(s) ont bien été ajoutée(s).");
                },
                error: function (error) {
                	error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
                }
            });
        }
        return true;
    });

    $(document).on("change", ".programSelector", function(){
        var row = $(this).context['id'].charAt(13);
    	showCountriesByProgram(row, this.value);
        $("#selectCountry" + row).trigger("change");
    });

    $(document).on("change", ".countrySelector", function(){
        var row = $(this).context['id'].charAt(13);
        showPartnerByCountry(row , $("#selectCountry"+ row + " option:selected").attr("data-countryiso"));
    });

    function showCountriesByProgram(row, programSelected){
    	$("#selectCountry" + row + ">option").each(function(){
    		var program = $(this).attr("data-program");
    		switch(programSelected){
    			case 'Erabel' :
    				if(program != 2){
    					$(this).css("display", "none");
    				}else{
						$(this).css("display", "block");
    				}
    				$(this).parent().val("Belgique");
    			break;
    			case 'FAME' :
    				if(program != 3){
    					$(this).css("display", "none");
    				}else{
						$(this).css("display", "block");
    				}
    				$(this).parent().val("Afghanistan");
    			break;
    			case 'Erasmus+' :
    				if(program != 1){
    					$(this).css("display", "none");
    				}else{
						$(this).css("display", "block");
    				}
    				$(this).parent().val("Allemagne");
    			break;
    		}

    	});
    }

    function showPartnerByCountry(row, countrySelected){

        $("#selectPartner" + row + ">option").each(function(){
            var countryIso = $(this).attr("data-country");
            if(countryIso === countrySelected){
                $(this).css("display", "block");
            }else{
                $(this).css("display", "none");
            }
            $(this).parent().val("Aucun partenaire");
        });
        $("#selectPartner"+row+" option:first-child").css("display", "block");

    }

    function addDepartmentsToSelector(id) {
        var departments = $("#selectDep1").html();
        var selectName = "#selectDep" + id;
        $(selectName).html(departments);
    }

    function addCountriesToSelector(id) {
        var countries = $("#selectCountry1").html();
        var selectName = "#selectCountry" + id;
        $(selectName).html(countries);
    }

    function addProgramsToSelector(id) {
        var programs = $("#selectProgram1").html();
        var selectName = "#selectProgram" + id;
        $(selectName).html(programs);
    }

    function addPartnersToSelector(id) {
        var partners = $("#selectPartner1").html();
        var selectName = "#selectPartner" + id;
        $(selectName).html(partners);
    }

    function addAccademicYearsToSelector(id){
    	var academicYears = $("#selectAccademicYear1").html();
    	var selectName = "#selectAccademicYear" + id;
    	$(selectName).html(academicYears);
    }

    //userList

    $("#userListTableBody").on("click.btnNommer", ".btnNommer", function () {
        var id = $(this).attr("value");
        var verNr = $(this).attr("verNr");
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "changePermissions",
                id: id,
                vrNr: verNr
            },
            success: function (resp) {
                $("#tdPermissions" + id).html("TEACHER");
                $("#tdButtonNommer" + id).html("");
                $("#tdButtonGererInfos" + id).html("");
				printToaster("success","L'utilisateur a bien été promu.");
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
        return true;
    });

    $("#userListTableBody").on("click", ".btnGererInfos", function () {
        var id = $(this).attr("value");
        if ($("#profile_country").html() == "") {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectCountries"
                },
                success: function (resp) {
                    resp = JSON.parse(resp);
                    var key;
                    for (key in resp) {
                        $("#profile_country").append('<option value='+resp[key]['nameFr']+'>' + resp[key]['nameFr'] + '</option>');
                    }
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
                }
            });
        }

        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "selectUserInformationsById",
                id: id
            },
            success: function (resp) {
                resp = JSON.parse(resp);
                try{
                    var b = new Date(""+resp['birthDate']['year']+"-"+resp['birthDate']['month']
                        +"-"+resp['birthDate']['day']);
                    var birthdate = b.getFullYear() + "-" +
                        (b.getMonth().toString().length == 1 ? "0" + parseInt(b.getMonth() + 1) : parseInt(b.getMonth() + 1)) + "-" +
                        (b.getDate().toString().length == 1 ? "0" + b.getDate() : b.getDate());
                    $("input[name='birthdate']").val(birthdate);
                }catch(err){
                    console.log("La date de naissance est nulle");
                }

                $("input[name='name']").val(resp['name']);
                $("input[name='firstname']").val(resp['firstname']);
                $("input[name='gender']").val(resp['gender']);
                $("input[name='citizenship']").val(resp['citizenship']);
                $("input[name='street']").val(resp['street']);
                $("input[name='houseNumber']").val(resp['houseNumber']);

                $("input[name='city']").val(resp['city']);
                var country = resp['country'];
                $("#profile_country").val(country);
                $("input[name='mailbox']").val(resp['mailBox']);
                $("input[name='zipcode']").val(resp['zip']);
                $("input[name='tel']").val(resp['tel']);
                $("input[name='email']").val(resp['email']);
                $("input[name='successfullYearsInCollege']").val(resp['successfullYearInCollege']);
                try {
                    $("input[name='iban']").val(resp["iban"]["value"]);
                }catch(err){

                }
                $("input[name='accountHolder']").val(resp['accountHolder']);
                $("input[name='bankName']").val(resp['bankName']);
                $("input[name='bic']").val(resp['bic']);
                
                $("#formProfile").attr("idUser", id);
                $("#formProfile").attr("verNb", resp['verNr']);

                $(".page").css("display", "none");
                $("#navBarTeacher").css("display","block");
                $("#profilePage").css("display", "block");
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
        return true;
    });

    //addPartner
    $("#addPartnerBtn").click(function () {

        if ($("#add_partner_legal_name").val() === "" || $("#add_partner_country").val() === ""){
            if ($("#add_partner_legal_name").val() === ""){
                printToaster("warning","Le champ \"Nom légale\" est requis");
            }
            if ($("#add_partner_country").val() === ""){
                printToaster("warning","Le champ \"Pays\" est requis");
            }
        }
        else {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "addPartner",
                    legal_name: $("#add_partner_legal_name").val(),
                    business_name: $("#add_partner_business_name").val(),
                    full_name: $("#add_partner_full_name").val(),
                    department: $("#add_partner_department").val(),
                    type: $("#add_partner_type").val(),
                    nb_employees: $("#add_partner_nb_employees").val(),
                    street: $("#add_partner_street").val(),
                    number: $("#add_partner_number").val(),
                    mailbox: $("#add_partner_mailbox").val(),
                    zip: $("#add_partner_zip").val(),
                    city: $("#add_partner_city").val(),
                    state: $("#add_partner_state").val(),
                    country: $("#add_partner_country").val(),
                    tel: $("#add_partner_tel").val(),
                    email: $("#add_partner_email").val(),
                    website: $("#add_partner_website").val()

                },
                success: function (resp) {
                    printToaster("success", "Le partenaire a bien été ajouté.");
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
        }
        return false;
    });


    //navBar
    $(".navButton").click(function () {

        switch ($(this).attr("href")) {
            case "#myMobility":
                  authStudent();
                history.pushState({page: "myMobility"}, "Mes mobilités", "/home#myMobility");
                break;
            case "#confirmedMobility":
                authTeacher();
                history.pushState({page: "confirmedMobility"}, "Mobilites Confirmées", "/home#confirmedMobility");
                break;
            case "#addMobility" :
                loadAddMobility();
                history.pushState({page:"addMobility"}, "Ajouter une mobilité", "/home#addMobility");
                break;
            case "#disconnect" :
                disconnect();
                history.pushState({page: "index"}, "Page d'accueil", "/home#disconnect");
                break;
            case "#list":
                loadList();
                history.pushState({page: "list"}, "Liste des demandes", "/home#list");
                break;
            case "#userList":
                loadUserList();
                history.pushState({page: "userList"}, "Liste des utilisateurs", "/home#userList");
                break;
            case "#addPartner":
                loadAddPartner();
                history.pushState({page: "addPartner"}, "Ajouter un partenaire", "/home#addPartner");
                break;
            case "#myInformations":
                loadProfilePage();
                history.pushState({page: "myInformations"}, "Modifier mes informations", "/home#myInformations");
                break;
			case "#payment" :
                loadPaymentPage();
				history.pushState({page: "payment"}, "Liste des paiements", "/home#payment");
				break;
        }
        return false;
    });

    //Chargement des pages.
    function authStudent() {
        checkPermission();
        $(".page").css("display", "none");
        $("#navBarStudent").css("display", "block");
        $("#studentHomePage").css("display", "block");
        loadMyMobility();
        $(".active").removeClass("active");
        $(".navButton[href='#myMobility']").parent().addClass("active");
    }

    function loadAddMobility() {
        $(".page").css("display", "none");
        $("#navBarStudent").css("display", "block");
        $("#addMobilityPage").css("display", "block");
		var currentTime = new Date();
        var startYear = currentTime.getFullYear()-1;

        for(var i = 0; i<4; i++){
        	var temp = "" + startYear + "-" + (startYear+1);
        	$("#selectAccademicYear1").append("<option>" + temp +"</option>");
        	startYear++;
        }


        if ($("#selectProgram1").html() == "" || $("#selectCountry1").html() == "" || $("#selectDep").html() == "") {
	        $.ajax({
	            method: "POST",
	            url: "/home",
	            data: {
	                action: "selectAddMobilityInformations"
	            },
	            success: function (resp) {
	                resp = JSON.parse(resp);
	                var key;
	                for (key in resp['programs']) {
	                    $("#selectProgram1").append("<option>" + resp['programs'][key]['name'] + "</option>");
	                }
                    for (key in resp['countries']) {
                        $("#selectCountry1").append("<option data-countryIso=\"" + resp['countries'][key]['iso'] + "\" data-program=\"" + resp['countries'][key]['idProgram'] + "\">" + resp['countries'][key]['nameFr'] + "</option>");
                    }
                    showCountriesByProgram(1, "Erasmus+");
                    for (key in resp['departments']) {
                        $("#selectDep1").append("<option>" + resp['departments'][key]['label'] + "</option>");
                    }
                    for (key in resp['partners']) {
                        $("#selectPartner1").append("<option data-country=\"" + resp['partners'][key]['country'] + "\" data-partnerId=\"" + resp['partners'][key]['id'] + "\">" + resp['partners'][key]['legalName'] + "</option>");
                    }
                    showPartnerByCountry(1, "DE");
	            },
	            error: function (error) {
	                error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
	            }
	        });
	    }
	    $(".active").removeClass("active");
	    $(".navButton[href='#addMobility']").parent().addClass("active");
    }

    function authTeacher() {
        checkPermission();
        $(".page").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#teacherHomePage").css("display", "block");
        loadConfirmedMobility();
        $(".active").removeClass("active");
        $(".navButton[href='#confirmedMobility']").parent().addClass("active");
    }

    function loadList() {

        $(".page").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#listPage").css("display", "block");
        $(".active").removeClass("active");
        $(".navButton[href='#2lists']").parent().addClass("active");
        $("#tableConfirmed tbody").empty();
        loadMobility();
    }

	function loadPaymentPage(){
		$(".page").css("display", "none");
		$("#navBarTeacher").css("display", "block");
		$("#paymentPage").css("display", "block");

		$(".active").removeClass("active");
		$(".navButton[href='#2lists']").parent().addClass("active");
		//$(".navButton[href='#payment']").parent().addClass("active");
		$("#tablePayments tbody").empty();
		loadPayment();
	}

    function loadUserList() {
        $(".page").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#userListPage").css("display", "block");
		$("#searchBar").val("");
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "selectUsers"
            },
            success: function (resp) {
                resp = JSON.parse(resp);
                $("#userListTableBody").empty();
                for (key in resp) {
                    var value;
                    if (resp[key]['permissions'] === "STUDENT") {
                        value = "<tr class='studentTR'>"
                            + "<td>" + resp[key]['id'] + "</td>"
                            + "<td class='tdNameStudent'>" + resp[key]['name'] + "</td>"
                            + "<td class='tdFirstnameStudent'>" + resp[key]['firstname'] + "</td>"
                            + '<td id="tdPermissions' + resp[key]['id'] + '">' + resp[key]['permissions'] + '</td>'
                            + '<td id="tdButtonNommer' + resp[key]['id'] + '"><button value="' + resp[key]['id'] 
                            + '"verNr="' + resp[key]['verNr']
                        	+ '" class="btnNommer btn btn-sm btn-info">Nommer</button></td>'
                            + '<td id="tdButtonGererInfos' + resp[key]['id'] + '"><button value="' + resp[key]['id'] + '" class="btnGererInfos btn btn-sm btn-info">Gérer les informations</button></td>'
                            + "</tr>";
                    } else {
                        value = "<tr class='teacherTR'>"
                            + "<td>" + resp[key]['id'] + "</td>"
                            + "<td>" + resp[key]['name'] + "</td>"
                            + "<td>" + resp[key]['firstname'] + "</td>"
                            + "<td>" + resp[key]['permissions'] + "</td>"
                            + '<td></td>'
                            + '<td></td>'
                            + "</tr>";
                    }

                    $("#userListTableBody").append(value);
                }
            },
            error: function (error) {
					error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
            }
        });
        $(".active").removeClass("active");
        $(".navButton[href='#userList']").parent().addClass("active");
		
		$("#searchBar").off('input.searchBar').on('input.searchBar', function (){
			var inputSearch = $("#searchBar").val();
			var regEx = new RegExp(inputSearch,"i");
			if (inputSearch !== ""){
				$(".teacherTR").css("display", "none");
			}else{
				$(".teacherTR").css("display", "table-row");
			}
			$(".studentTR").each(function(){
				if ($(this).children(".tdFirstnameStudent").html().match(regEx) || $(this).children(".tdNameStudent").html().match(regEx)  ){
					$(this).css("display","table-row");
				}else{
					$(this).css("display","none");
				}
			});
		});
    }

	function loadAddPartner() {

        $(".page").css("display", "none");
        $("#navBarStudent").css("display", "block");
        $("#addPartnerPage").css("display", "block");
        $("#setPartnerBtn").css("display","none");
 		$("#addPartnerBtn").css("display","block");
        $(".active").removeClass("active");
        $(".navButton[href='#addPartner']").parent().addClass("active");
        addParnter();

    }

    function loadAddPartnerTeacher() {

        $(".page").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#addPartnerPage").css("display", "block");
        $("#setPartnerBtn").css("display","none");
 		$("#addPartnerBtn").css("display","block");
        $(".active").removeClass("active");
        addParnter();

    }

    function addParnter(){
        if ($("#add_partner_country").html() == "") {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectCountries"
                },
                success: function (resp) {
                    resp = JSON.parse(resp);
                    var key;
                    $("#add_partner_country").append("<option></option>");
                    for (key in resp) {
                        $("#add_partner_country").append("<option>" + resp[key]['nameFr'] + "</option>");
                    }
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
        }
    }

	function loadRegisterPage() {
	    $(".page").css("display", "none");
	    $("#registerPage").css("display", "block");
		$(".form-control").val("");

        if ($("#departmentRegister").html() == "") {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectDepartments"
                },
                success: function (resp) {
                    resp = JSON.parse(resp);
                    var key;
                    for (key in resp) {
                        $("#departmentRegister").append("<option>" + resp[key]['label'] + "</option>");
                    }
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
        }
	}


	function loadProfilePage() {
	    $(".page").css("display", "none");
	    $("#navBarStudent").css("display", "block");
	    $("#profilePage").css("display", "block");

		$(".active").removeClass("active");
		$(".navButton[href='#myInformations']").parent().addClass("active");

        if ($("#profile_country").html() == "") {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectCountries"
                },
                success: function (resp) {
                    resp = JSON.parse(resp);
                    var key;
                    for (key in resp) {
                        $("#profile_country").append('<option value='+resp[key]['iso']+'>' + resp[key]['nameFr'] + '</option>');
                    }
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
                }
            });
        }

		$.ajax({
			method: 'POST',
			url: '/home',
			data: {
				action: 'selectProfile'
			},
			success: function(resp){
				resp = JSON.parse(resp);
                try{
                    var b = new Date(""+resp['birthDate']['year']+"-"+resp['birthDate']['month']
                        +"-"+resp['birthDate']['day']);
                    var birthdate = b.getFullYear() + "-" +
                        (b.getMonth().toString().length == 1 ? "0" + parseInt(b.getMonth() + 1) : parseInt(b.getMonth() + 1)) + "-" +
                        (b.getDate().toString().length == 1 ? "0" + b.getDate() : b.getDate());
                    $("input[name='birthdate']").val(birthdate);
                }catch(err){
                    console.log("La date de naissance est nulle");
                }
                //console.log(resp);
                $("input[name='name']").val(resp['name']);
                $("input[name='firstname']").val(resp['firstname']);
                $("input[name='gender']").val(resp['gender']);
                $("input[name='citizenship']").val(resp['citizenship']);
                $("input[name='street']").val(resp['street']);
                $("input[name='houseNumber']").val(resp['houseNumber']);

                $("input[name='city']").val(resp['city']);
                var country = resp['country'];
                $("#profile_country").val(country);
                $("input[name='mailbox']").val(resp['mailBox']);
                $("input[name='zipcode']").val(resp['zip']);
                $("input[name='tel']").val(resp['tel']);
                $("input[name='email']").val(resp['email']);
                $("input[name='successfullYearsInCollege']").val(resp['successfullYearInCollege']);
                try {
                    $("input[name='iban']").val(resp["iban"]["value"]);
                }catch(err){

                }
                $("input[name='accountHolder']").val(resp['accountHolder']);
                $("input[name='bankName']").val(resp['bankName']);
                $("input[name='bic']").val(resp['bic']);
			},
			error: function(error){
				error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
			}
		});
	}

	function loadMobility() {
	    $(function () {
	        $.ajax({
	            method: "POST",
	            url: "/home",
	            data: {
	                action: "selectAllMobility",
	            },
	            success: function (resp) {
	                if (resp === ""){
						$("#list").empty();
						$("#empty").empty();
						$("#list").after("<p id=\"empty\" class=\"text-center\"><strong> Il n'y aucune demande de mobilité actuellement. </strong></p>");
					}else{
						clearButtons();
						resp = JSON.parse(resp);
						$("#list tbody").empty();
						$("#empty").empty();

						for (key in resp) {
							var data =
								"<tr value='"+ resp[key]['id'] +"'>"+
								"<td>" + resp[key]['id'] + "</td>" +
									"<td>" + resp[key]['studentDto']['name'] + "</td>" +
									"<td>" + resp[key]['studentDto']['firstname'] + "</td>" +
									"<td>" + resp[key]['departmentDto']['label'] + "</td>" +
									"<td>" + resp[key]['preferenceOrder'] + "</td>" +
									"<td>" + resp[key]['programDto']['name'] + "</td>" +
									"<td>" + resp[key]['type'] + "</td>" +
									"<td>" + resp[key]['quadrimester'] + "</td>";
									if (resp[key]['partnerDto']['legalName'] !== null)
										data += "<td>"+resp[key]['partnerDto']['legalName']+"</td>";
									else data += "<td></td>";
									data +=	"<td>" + resp[key]['status'] +
									"</td><td value='"+ resp[key]['id'] +"'></td>"+
									"<td></td><td></td>" +
								"</tr>";
							$("#list tbody").append(data);
						}
						
						var textBtn = "Détails";
						$("#list tr td:nth-child(10)").each(function(){
							textBtn = "Détails";
							if ($(this).html() !== "Annulee") {
								textBtn="Modifier";
                                $(this).next().append("<button type=\"button\" class=\"btnCancel btn btn-sm btn-danger\" data-toggle=\"modal\" data-target=\"#modalCancelMobility\">Annuler</button>");
							} else {
								$(this).parent().addClass("danger");
							}
							if ($(this).html() === "En attente"){
								$(this).next().next().append("<button type=\"button\" class=\"btn btn-sm btn-success btnConfirm\" data-toggle=\"modal\" data-target=\"#modalConfirmMobility\">Confirmer</button>");
							}else{	
								$(this).next().next().next().append("<button id=\"profBtnModif\" class=\"btnModif btn btn-sm btn-info\" >"+textBtn+"</button>");
							}
						});
						
					}
	            },
	            error: function (error) {
	                error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
	            }
	        });
	    });
	}

        $("#list").on("click", ".btnModif", function (evt){
            var id = $(evt.currentTarget).parent().parent().attr("value");
            loadDetailsMobility(id);
        });
        $("#list").on("click", ".btnCancel", function (evt) {
            var id = $(evt.currentTarget).parent().parent().attr("value");
            loadCancelMobility(id);
        });
        $("#list").on("click", ".btnConfirm", function (evt) {
            var id = $(evt.currentTarget).parent().parent().attr("value");
            loadConfirmMobility(id);
        });

	// Managing of filter buttons

	$("#info").on("click", function(){
		if($(this).hasClass('btn-primary')){
			resetAllDemandsDisplay();
		}else{
			clearButtons();
			$(this).addClass("btn-primary").removeClass("btn-default");
			demandsDisplayManagement("Informatique de gestion");
		}
	});

	$("#chim").on("click", function(){
		if($(this).hasClass('btn-primary')){
			resetAllDemandsDisplay();
		}else{
			clearButtons();
			$(this).addClass("btn-primary").removeClass("btn-default");
			demandsDisplayManagement("Chimie");
		}
	});

	$("#biomed").on("click", function(){
		if($(this).hasClass('btn-primary')){
			resetAllDemandsDisplay();
		}else{
			clearButtons();
			$(this).addClass("btn-primary").removeClass("btn-default");
			demandsDisplayManagement("Biologie médicale");
		}
	});

	$("#imamed").on("click", function(){
		if($(this).hasClass('btn-primary')){
			resetAllDemandsDisplay();
		}else{
			clearButtons();
			$(this).addClass("btn-primary").removeClass("btn-default");
			demandsDisplayManagement("Imagerie médicale");
		}
	});

	$("#diet").on("click", function(){
		if($(this).hasClass('btn-primary')){
			resetAllDemandsDisplay();
		}else{
			clearButtons();
			$(this).addClass("btn-primary").removeClass("btn-default");
			demandsDisplayManagement("Diététique");
		}
	});

	function resetAllDemandsDisplay(){
		$("#list tr").each(function(){
			$(this).css("display", "table-row"); 
		});
		clearButtons();
	}

	function clearButtons(){
		$("#choice button").each(function(){
			$(this).addClass('btn-default').removeClass("btn-primary");
		});
	}

	function demandsDisplayManagement(department){
		$("#list tr td:nth-child(4)").each(function(){
			if($(this).html() === department){
				if ($(this).parent().css("display") === "none"){
					$(this).parent().css("display", "table-row");
				}
			}else{
				$(this).parent().css("display", "none");
			}
		});
	}

    // Managing of the payment table
	function loadPayment(){
		$(function (){
			$.ajax({
				method: "POST",
				url: "/home",
				data: {
					action: "academicYears"
				},
				success: function (resp) {
					resp = JSON.parse(resp);
					$('#selectYear').empty();
					for(var i= 0; i < resp.length; i++){
						var option;
						if (i===0){
							option = $('<option selected="selected">');
						}else{
							option = $('<option>');
						}
						$(option).val(resp[i]).text(resp[i]);
						$('#selectYear').append(option);
					}
					$('#selectYear').trigger("change");
				},
				error: function (error) {
					error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
				}
			});
			$("#selectYear").on("change", function(){ loadPaymentTable($("#selectYear").val()) });
		});
		
		function loadPaymentTable(year) {
	        $.ajax({
	            method: "POST",
	            url: "/home",
	            data: {
					academicYear : year,
	                action: "selectPayments"
	            },
	            success: function (resp) {
					$("#empty").empty();
					if (resp === ""){
						$("#tablePayments").empty();
						$("#tablePayments").after("<p id=\"empty\" class=\"text-center\"><strong> Il n'y a aucun paiement actuellement. </strong></p>");
					}else{
						resp = JSON.parse(resp);
						$("#tablePayments tbody").empty();
						var danger = "";
						var textBtn = "Modifier";
						for (key in resp) {
							textBtn = "Modifier";
							danger="";
							if (resp[key]['status'] === "Annulee"){
								danger = "class='danger'";
								textBtn = "Détails";
							}
							var data = "<tr value='"+ resp[key]['id'] + "'"+danger+">" +
											"<td>" + resp[key]['id'] + "</td>"+
											"<td>" + resp[key]['studentDto']['name'] + "</td>" +
											"<td>" + resp[key]['studentDto']['firstname'] + "</td>" +
											"<td>" + resp[key]['departmentDto']['label'] + "</td>" +
											"<td>" + resp[key]['programDto']['name'] + "</td>" +
											"<td>" + resp[key]['type'] + "</td>";
							if (resp[key]['partnerDto']['legalName'] !== null)
								data += "<td>"+resp[key]['partnerDto']['legalName']+"</td>";
							else data += "<td></td>";
								data += "<td>"+resp[key]['amount']+"</td>";

							if (resp[key]['paymentDate1']){
								data += "<td>Demandé</td>"
							}else data += "<td>Non demandé</td>";

							if (resp[key]['paymentDate2']){
								data += "<td>Demandé</td>"
							}else data += "<td>Non demandé</td>";
								
							if (resp[key]['status'] !== "En attente"){
								data+="<td><button class=\"btnModif btn btn-sm btn-info\">"+textBtn+"</button></td>";
							}else{
								data+="<td></td>";
							}
							data += "</tr>"

							$("#tablePayments tbody").append(data);
						}
					}
					
	            },
	            error: function (error) {
	               	error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
	            }
	        });
		}
	}

    $("#tablePayments").on("click", ".btnModif", function (evt){
        var id = $(evt.currentTarget).parent().parent().attr("value");
        loadDetailsMobility(id);
    });

	// Managing of the confirmed table

	function loadConfirmedMobility() {
	    $(function () {
	        $.ajax({
	            method: "POST",
	            url: "/home",
	            data: {
	                action: "selectConfirmedMobility",
	            },
	            success: function (resp) {
					$("#empty").empty();
					if (resp === ""){
						$("#tableConfirmed").empty();
						$("#tableConfirmed").after("<p id=\"empty\" class=\"text-center\"><strong> Il n'y aucune demande confirmée actuellement. </strong></p>");
					}else{
						resp = JSON.parse(resp);
						$("#tableConfirmed tbody").empty();
						$("#empty").empty();

						for (key in resp) {

							$("#tableConfirmed tbody").append(
								"<tr value='"+ resp[key]['id'] + "'>" +
								"<td>" + resp[key]['departmentDto']['label'] + "</td>" +
								"<td>" + resp[key]['programDto']['name'] + "</td>" +
								"<td>" + resp[key]['type'] + "</td>" +
								"<td>" + resp[key]['countryDto']['nameFr'] + "</td>" +
								"<td>" + resp[key]['studentDto']['name'] + "</td>" +
								"<td>" + resp[key]['studentDto']['firstname'] + "</td>" +
								"<td>" + resp[key]['status'] + "</td><td></td>"
								+ "</tr>");

						}
						var textBtn = "Modifier";
						$("#tableConfirmed tr td:last-child").each(function () {
							textBtn = "Modifier";
							if ($(this).html() === "Annulee") {
								$(this).parent().addClass("danger");
								textBtn = "Détails";
							}
							$(this).append("<button class=\"btnModif btn btn-sm btn-info\">"+textBtn+"</button>");								
						});
					}

	            },
	            error: function (error) {
	                error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
	            }
	        });
	    });
		
	}

    $("#tableConfirmed").on("click", ".btnModif", function (evt){
        var id = $(evt.currentTarget).parent().parent().attr("value");
        loadDetailsMobility(id);
    });

	// Managing of the "myMobility" table
	function loadMyMobility() {
	    $(function () {
	        $.ajax({
	            method: "POST",
	            url: "/home",
	            data: {
	                action: "selectMyMobility",
	            },
	            success: function (resp) {
					$("#empty").empty();
					if (resp === ""){
						$("#myMobility").empty();
						$("#myMobility").after("<p id=\"empty\" class=\"text-center\"><strong> Vous n'avez aucune mobilité actuellement. </strong></p>");
					}else{
						$("#myMobility tbody").empty();
						resp = JSON.parse(resp);
                        //console.log(resp)
                        for (key in resp) {
							$("#myMobility tbody").append(
								"<tr>" +
								"<td>" + resp[key]['preferenceOrder'] + "</td>" +
								"<td>" + resp[key]['programDto']['name'] + "</td>" +
								"<td>" + resp[key]['type'] + "</td>" +
								"<td>" + resp[key]['countryDto']['nameFr'] + "</td>" +
								"<td>" + resp[key]['quadrimester'] + "</td>" +
								"<td>" + resp[key]['status'] + "</td>"+
								"<td value='"+ resp[key]['id'] +"'></td>"+
								"<td value='"+ resp[key]['partnerDto']['id'] +"' id='"+ resp[key]['id'] +"'></td>"+
								+ "</tr>");

						}	
						$("#myMobility tr td:nth-child(6)").each(function () {
							if ($(this).html() !== "Annulee") {
								$(this).next().append("<button type=\"button\" class=\"btn btn-sm btn-danger btnCancelStudent\" data-toggle=\"modal\" data-target=\"#modalCancelMobility\">Annuler</button>");
							} else {
								$(this).parent().addClass("danger");
							}
							if ($(this).html() === "En attente" && $(this).next().next().attr("value") == 0) {

								$(this).next().next().append("<button type=\"button\" class=\"btn btn-sm btn-success btnConfirm\" data-toggle=\"modal\" data-target=\"#modalConfirmMobility\">Confirmer</button>");
							}
						});
					
					}
					
	            },
	            error: function (error) {
	               	error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
	            }
	        });
	    });
		
	}

    $("#myMobility").off("click.btnCancelStudent").on("click.btnCancelStudent", ".btnCancelStudent", function (evt) {
        var id = $(evt.currentTarget).parent().attr("value");
        loadCancelMobility(id);

    });
    $("#myMobility").off("click.btnConfirm").on("click.btnConfirm", ".btnConfirm", function (evt) {
        var id = $(evt.currentTarget).parent().attr("id");
        loadConfirmMobility(id);
    });


	function loadCancelMobility(id){
		$("#textReason").val("");
		var teacher=false;
		
         if ($('#permissionHideFilds').val() === "TEACHER"){
			teacher=true;
			$(function (){
				$.ajax({
					method: "POST",
					url: "/home",
					data: {
						action: "loadCancelationsReasons"
					},
					success: function (resp) {
						resp = JSON.parse(resp);
						$('#listReasons').empty();
						$('#listReasons').append("<option value='other' selected=\"selected\">--Autre--</option>");
						for(var i= 0; i < resp.length; i++){
							var option = $('<option>');
							$(option).val(resp[i]['id']).text(resp[i]['reason']);
							$('#listReasons').append(option);
						}
						$('#listReasons').trigger("change");
					},
					error: function (error) {
						error = JSON.parse(error.responseText);
						printToaster(error.type, error.message);
					}
				});
			});
			$("#onlyTeacherReasons").css("display","block");
		} else if ($('#permissionHideFilds').val() === "STUDENT"){
			$("#onlyTeacherReasons").css("display","none");
		}
		
        $("#sendCancelation").attr("data-id", id);

		$(function(){
			$.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectMobility",
                    id: id
                },
                success: function (resp) {
                	resp = JSON.parse(resp);
					//console.log(resp);
					$("#destinationP").html(resp['programDto']['name'] + " " + resp['type'] +
											" (" + resp['countryDto']['nameFr'] + ") durant le " +
											resp['quadrimester'] + "e quadrimestre.");
					if (resp['partnerDto']['legalName'] !== null){
						$("#partnerP").html("Partenaire : "+resp['partnerDto']['legalName']);
					}else{
						$("#partnerP").html("Aucun partenaire actuellement.")
					}
					$("#stateP").html("Etat de la mobilité : "+resp['status']);
					$("#sendCancelation").val(resp['verNr']);
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
		});

        
	}

    $("#sendCancelation").on("click", function (){
        var id = $(this).attr("data-id");
        var reasonValue;
        var idReason=0;
        var teacher=false;
        if($("#navBarTeacher").css("display") === "block"){
            teacher=true;
        }
        
        if (teacher && $('#listReasons').val() === "other"){
            if ($("#textReason").val() === ""){
                printToaster("error","Vous devez entrez une raison pour l'annulation de la mobilité !");
                return;
            }
            reasonValue = $("#textReason").val();
        }else if (teacher){
            if ($("#listReasons").val() !== "other" && $("#textReason").val() !== ""){
                printToaster("error","Vous devez soit sélectionner une raison prédéfinie soit entrer un texte!");
                return;
            }
            idReason = $('#listReasons').val();
        }else{ // Student
            if($("#textReason").val() === ""){
                printToaster("error","Vous devez entrez une raison pour l'annulation de la mobilité !");
                return;
            }
            reasonValue = $("#textReason").val();
        }
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "cancelMobility",
                idMobility: id,
                reasonValue: reasonValue,
                idReason: idReason,
                verNr:$(this).val()
            },
            success: function (resp) {
                $(".page").css("display", "none");
                $("#modalCancelMobility").modal("hide");
                changePage();
                printToaster("success","La mobilité a bien été annulée.")
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
                printToaster(error.type, error.message);
            }
        });
    });
    
	function loadConfirmMobility(id){
        $(function () {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectPartnersForConfirm",
                    idMobility: id,
                },
                success: function (resp) {
                    resp = JSON.parse(resp);

                    checkPermission();
                    $(".confirmMobilityPartnerDetail").empty();
                    if (jQuery.isEmptyObject(resp['partners'])){
                        $("#confirmMobility ").hide();
                        $(".confirmMobilityPartnerDetail").empty();
                        $(".confirmMobilityPartnerDetail").append("<h5>PAS DE PARTENAIRE DISPONIBLE</h5>");

                    }else {
                        $(".confirmMobilityPartnerDetail").append("<select>");
                        var cmp = 0;
                        for (key in resp['partners']) {
                            if (resp['mobility']['countryDto']['iso'] === resp['partners'][key]['country']) {
                                $("#confirmMobility ").show();
                                $("#confirmMobilityPartner select").append(
                                    "<option class=\"confirmClass\"id=" + resp['partners'][key]['id'] + ">" +
                                    resp['partners'][key]['legalName'] + "</option>"
                                );
                                cmp++;
                            }

                        }
                        $(".confirmMobilityPartnerDetail").append("</select>");
                        if (cmp == 0) {
                            $("#confirmMobility ").hide();
                            $(".confirmMobilityPartnerDetail").empty();
                            $(".confirmMobilityPartnerDetail").append("<h5>PAS DE PARTENAIRE DISPONIBLE</h5>");
                        }
                    }

                    $('#confirmMobility').attr("nbVr",resp['mobility']['verNr']);
                    
                    $('#confirmMbolityInfo').empty();
                    $('#confirmMbolityInfo').append(
                        "<b>Etudiant: </b>" + resp['mobility']['studentDto']['name'] + " " +
                        resp['mobility']['studentDto']['firstname'] + " <i>("+ resp['mobility']['idDepartment'] +")</i></br></br>" +
                        "<b>Département: </b>" + resp['mobility']['departmentDto']['label'] + "</br>" +
                        "<b>Type: </b> " + resp['mobility']['programDto']['name'] +
                        " <i>(" + resp['mobility']['type'] +")</i></br>" +
                        "<b>Lieu: </b>" + resp['mobility']['countryDto']['nameFr'] + "</br>" +
                        "<b>Quadrimestre: </b>" + resp['mobility']['quadrimester']

                    );

                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                    console.log("err");
                }
            });

        });

        $("#modalConfirmMobility").off('click.confirmMobility').on("click.confirmMobility", "#confirmMobility", function () {

            var idMobility = id;
            var idPartner = $('#confirmMobilityPartner option:selected').attr('id');
            var nbVer = $('#confirmMobility').attr('nbVr');
            $(function () {
                $.ajax({
                    method: "POST",
                    url: "/home",
                    data: {
                        action: "confirmPartnerInMobility",
                        idMobility: idMobility,
                        idPartner: idPartner,
                        verNr: nbVer
                    },
                    success: function (resp) {
                        $("#modalConfirmMobility").modal("hide");
                        if ($('#permissionHideFilds').val() === 'STUDENT'){
                            authStudent();
                        }else if ($('#permissionHideFilds').val() === 'TEACHER'){
                            loadList();
                        }
                        printToaster("success", "Le partenaire à bien été confirmé");

                    },
                    error: function (error) {
                        error = JSON.parse(error.responseText);
                        printToaster(error.type, error.message);
                        console.log("err");
                    }
                });

            });
        });
        $("#confirmMobilityAddPartnerBtn").on("click", function(){
            $("#modalConfirmMobility").modal("hide");
            checkPermission();

            if ($('#permissionHideFilds').val() === "TEACHER"){
                loadAddPartnerTeacher()
            } else if ($('#permissionHideFilds').val() === "STUDENT"){
                loadAddPartner();
            }

    });
    }


    function loadInfoPartner(id){
    	$(".page").css("display", "none");
        $("#partnerPage").css("display","block");
        $("#navBarTeacher").css("display","block");
        $(function (){
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "selectInfoPartner",
                    id: id
                },
                success: function (resp) {
                    resp = JSON.parse(resp);

                    $('#legalName').html(resp['legalName']);
                    $('#BusinesName').html(resp['business']);
                    $('#FullName').html(resp['fullName']);
                    $('#departmentPartner').html(resp['department']);
                    $('#typePartner').html(resp['type']);
                    $('#employee').html(resp['nbEmployees']);
                    $('#streetPartner').html(resp['street']);
                    $('#numberPartner').html(resp['number']);
                    $('#mailboxPartner').html(resp['mailbox']);
                    $('#countryPartner').html(resp['countryDto']['nameFr']);                  
                    $('#areaPartner').html(resp['state']);
                    $('#zipPartner').html(resp['zip']);
                    $('#cityPartner').html(resp['city']);
                    $('#PhonePartner').html(resp['tel']);
                    $('#mailPartner').html(resp['email']);
                    $('#webPartner').html(resp['website']);
                    $('#userPartner').html(resp['userDto']['name'] + " " + resp['userDto']['firstname']);
                    $('#setPartner').attr('nbVr',resp['verNr']);
                    $('#setPartner').attr('idPart',resp['id']);
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
        });

        if($("#navBarTeacher").css("display") === "block"){
         $("#setPartner").css("display","block");
         }else{
         $("#setPartner").css("display","none");
         }
    }

         $("#setPartner").on("click",function(){
        	$(".page").css("display", "none");
     		$("#addPartnerPage").css("display","block");
     		$("#navBarTeacher").css("display","block");
     		$("#setPartnerBtn").css("display","block");
     		$("#addPartnerBtn").css("display","none");
     		addParnter();
     		$('#add_partner_legal_name').val($('#legalName').html());
     		$('#add_partner_business_name').val($('#BusinesName').html());
     		$('#add_partner_full_name').val($('#FullName').html());
     		$('#add_partner_department').val($('#departmentPartner').html());
     		$('#add_partner_type').val($('#typePartner').html());
     		$('#add_partner_nb_employees').val($('#employee').html());
     		$('#add_partner_street').val($('#streetPartner').html());
     		$('#add_partner_number').val($('#numberPartner').html());
     		$('#add_partner_mailbox').val($('#mailboxPartner').html());
     		$('#add_partner_zip').val($('#zipPartner').html());
     		$('#add_partner_city').val($('#cityPartner').html());
     		$('#add_partner_state').val($('#areaPartner').html());
     		$('#add_partner_country').val($('#countryPartner').html());    		
     		$('#add_partner_tel').val($('#PhonePartner').html());
     		$('#add_partner_email').val($('#mailPartner').html());
     		$('#add_partner_website').val($('#webPartner').html());
         });

         $("#setPartnerBtn").on("click",function(){
        	 $.ajax({
                 method: "POST",
                 url: "/home",
                 data: {
                     action: "updatePartner",
                     idP: $('#setPartner').attr('idPart'),
                     nrVersion: $('#setPartner').attr('nbVr'),
                     legalName: $('#add_partner_legal_name').val(),
                     businnes: $('#add_partner_business_name').val(),
                     fullName: $('#add_partner_full_name').val(),
                     department: $('#add_partner_department').val(),
                     type: $('#add_partner_type').val(),
                     nbEmployees: $('#add_partner_nb_employees').val(),
                     street: $('#add_partner_street').val(),
                     number: $('#add_partner_number').val(),
                     zip: $('#add_partner_zip').val(),
                     mailbox: $('#add_partner_mailbox').val(),
                     country: $('#add_partner_country').val(),
                     state: $('#add_partner_state').val(),
                     city: $('#add_partner_city').val(),
                     tel: $('#add_partner_tel').val(),
                     email: $('#add_partner_email').val(),
                     website:$('#add_partner_website').val()
                 },
                 success: function (resp) {
                     printToaster("success", "Le partenaire à bien été modifiée.");
                 },
                 error: function (error) {
                     error = JSON.parse(error.responseText);
                     printToaster(error.type, error.message);
                 }
             });
         });


	function loadDetailsMobility(idStudent){
		$(".page").css("display", "none");
		$("#mobilityDetail").css("display","block");
		$("#navBarTeacher").css("display","block");


		$.ajax({
            url: "/home",
            type: 'POST',
            data: {
                action: "selectMobility",
                id:idStudent
            },
            success: function (resp) {
            	resp = JSON.parse(resp);
                //console.log(resp);
            	var city;
            	if(resp['partnerDto']['city'] === null){
            		city = "Non enregistré";
            	}else{
            		city = resp['partnerDto']['city'];
            	}
                $("#detailMobiliteIdMobilite").attr("data-id", resp['id']);
                $("#detailMobiliteNrVersion").attr("data-nrVer",resp['verNr']);
            	var intitule = "" +resp['programDto']['name'] + " " + resp['type'] 
            		+  " à " + city + " durant le quadri " + resp['quadrimester'];
            	$("#detailMobiliteIntitule").html(intitule);
            	$("#detailMobilitePartenaire").html("Partenaire : " + resp['partnerDto']['legalName']);
            	$("#detailMobiliteEtat").html(resp['status']);
            	$("#detailMobilitePartenaire").attr("id-partner",resp['partnerDto']['id']);	
            	
                //infos des checkboxs
            	if(resp['paymentDate1']){
            		$("#envoiPaiement1").prop("checked", true);
            	}else{
            		$("#envoiPaiement1").prop("checked", false);
            	}

            	if(resp['paymentDate2']){
            		$("#envoiPaiement2").prop("checked", true);
            	}else{
            		$("#envoiPaiement2").prop("checked", false);
            	}

                $("#detailMobiliteMontant").val(resp['amount']);

            	if(resp['softwareProeco']){
            		$("#encodageProEco").prop("checked", true);
            	}else{
            		$("#encodageProEco").prop("checked", false);
            	}

            	if(resp['softwareMobilityTools']){
            		$("#encodageMobilityTool").prop("checked", true);
            	}else{
            		$("#encodageMobilityTool").prop("checked", false);
            	}

            	if(resp['softwareMobi']){
            		$("#encodageMobi").prop("checked", true);
            	}else{
            		$("#encodageMobi").prop("checked", false);
            	}

                //Modal documents
                //depart
                var string = "";
                string +="<tr><td> Convention de stage/études : </td>";
                if(resp['departureConventionInternshipSchoolarship']){
                    $("#detailMobiliteConventionStage").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteConventionStage").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }

                string +="<tr><td> Document haute école envoyé : </td>";
                if(resp['departDocSentHighschool']){
                    $("#detailMobiliteDocumentHauteEcoleDepart").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteDocumentHauteEcoleDepart").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }

                string +="<tr><td> Contrat de bourse : </td>";
                if(resp['departureDocAggreement']){
                    $("#detailMobiliteEngagement").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteEngagement").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Preuve du passage des tests linguistiques: </td>";
                if(resp['departureErasmusLanguageTest']){
                    $("#detailMobiliteTestLangueDepart").prop("checked", true);
                    string +="<td> Remis </td> </tr>";

                }else{
                    $("#detailMobiliteTestLangueDepart").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Contrat de bourse </td>";
                if(resp['departureGrantContract']){
                    $("#detailMobiliteContratBourse").prop("checked", true);
                    string +="<td> Remis </td> </tr>";

                }else{
                    $("#detailMobiliteContratBourse").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Charte de l'étudiant </td>";
                if(resp['departureStudentConvention']){
                    $("#detailMobiliteCharteEtudiant").prop("checked", true);
                    string +="<td> Remis </td> </tr>";

                }else{
                    $("#detailMobiliteCharteEtudiant").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                //retour

                    string +="<tr><td> Document HE Envoyé </td>";
                if(resp['returnDocSentHighschool']){
                    $("#detailMobiliteDocumentHauteEcoleRetour").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteDocumentHauteEcoleRetour").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Test de langues : Retour </td>";
                if(resp['returnErasmusLanguagetest']){
                    $("#detailMobiliteTestLangueRetour").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteTestLangueRetour").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Rapport final </td>";
                if(resp['returnFinalReport']){
                    $("#detailMobiliteRapportFinal").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteRapportFinal").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Certificat de stage </td>";
                if(resp['returnInternshipCert']){
                    $("#detailMobiliteCertificatStage").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteCertificatStage").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Attestation de sejour </td>";
                if(resp['returnResidenceCert']){
                    $("#detailMobiliteAttestationSejour").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteAttestationSejour").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }


                    string +="<tr><td> Relevé de notes</td>";
                if(resp['returnTranscript']){
                    $("#detailMobiliteReleveNote").prop("checked", true);
                    string +="<td> Remis </td> </tr>";
                }else{
                    $("#detailMobiliteReleveNote").prop("checked", false);
                    string +="<td> Non Remis </td> </tr>";
                }

                $("#document_csv_table tbody").append(string);

                //Infos de l'étudiant
            	$("#detailMobiliteNom").html(resp['studentDto']['name']);
            	$("#detailMobilitePrenom").html(resp['studentDto']['firstname']);
                
                if(resp['studentDto']['gender'] != null){
            	   $("#detailMobiliteSexe").html(resp['studentDto']['gender']);
                }else{
                    $("#detailMobiliteSexe").html("Non enregistré");
                }

                if(resp['studentDto']['birthDate'] != null){
            	   $("#detailMobiliteDateNaissance").html(resp['studentDto']['birthDate']['dayOfMonth'] + "/" 
            		  + resp['studentDto']['birthDate']['monthValue'] + "/" + resp['studentDto']['birthDate']['year']);
                }else{
                    $("#detailMobiliteDateNaissance").html("Non enregistré");
                }
                
                if(resp['studentDto']['citizenship'] != null){
                    $("#detailMobiliteNationalite").html(resp['studentDto']['citizenship']);
                }else{
                   $("#detailMobiliteNationalite").html("Non enregistré"); 
                }
            	
                if (resp['studentDto']['street'] == null || resp['studentDto']['houseNumber']==null || resp['studentDto']['zip']==null || resp['studentDto']['city']==null){
                    $("#detailMobiliteAdresse").html("Non enregistré");
                }else{
            	$("#detailMobiliteAdresse").html(resp['studentDto']['street'] + " " + resp['studentDto']['houseNumber']
            		+ ", " + resp['studentDto']['zip'] + " " + resp['studentDto']['city']);
                }

                if(resp['studentDto']['tel'] != null){
            	   $("#detailMobiliteTel").html(resp['studentDto']['tel']);
                }else{
                    $("#detailMobiliteTel").html("Non enregistré");
                }

                if(resp['departmentDto'] != null ){
                    $("#detailMobiliteDepartement").html(resp['departmentDto']['label']);
                }else{
                    $("#detailMobiliteDepartement").html("Non enregistré");
                }
            	
                if(resp['studentDto']['email']!=null){
            	   $("#detailMobiliteMail").html(resp['studentDto']['email']);
                }else{
                    $("#detailMobiliteMail").html("Non enregistré");
                }

                if(resp['status'] === "Annulee" || resp['status'] === "En attente"){

                    //paiements
                    $("#envoiPaiement1").prop("disabled", true);
                    $("#envoiPaiement2").prop("disabled", true);

                    //softwares
                    $("#encodageProEco").prop("disabled", true);
                    $("#encodageMobilityTool").prop("disabled", true);
                    $("#encodageMobi").prop("disabled", true);

                    //montant
                    $("#detailMobiliteMontant").prop("disabled", true);

                    //documents départ
                    $("#detailMobiliteContratBourse").prop("disabled", true);
                    $("#detailMobiliteConventionStage").prop("disabled", true);
                    $("#detailMobiliteCharteEtudiant").prop("disabled", true);
                    $("#detailMobiliteEngagement").prop("disabled", true);
                    $("#detailMobiliteTestLangueDepart").prop("disabled", true);
                    $("#detailMobiliteDocumentHauteEcoleDepart").prop("disabled", true);

                    //documents retour
                    $("#detailMobiliteAttestationSejour").prop("disabled", true);
                    $("#detailMobiliteReleveNote").prop("disabled", true);
                    $("#detailMobiliteCertificatStage").prop("disabled", true);
                    $("#detailMobiliteRapportFinal").prop("disabled", true);
                    $("#detailMobiliteTestLangueRetour").prop("disabled", true);
                    $("#detailMobiliteDocumentHauteEcoleRetour").prop("disabled", true);

                    //cacher boutons
                    $("#detailMobiliteCancel").css("display", "none");
                    $("#detailMobiliteModify").css("display", "none");
                }else{
                    //paiements
                    $("#envoiPaiement1").prop("disabled", false);
                    $("#envoiPaiement2").prop("disabled", false);

                    //softwares
                    $("#encodageProEco").prop("disabled", false);
                    $("#encodageMobilityTool").prop("disabled", false);
                    $("#encodageMobi").prop("disabled", false);

                    //montant
                    $("#detailMobiliteMontant").prop("disabled", false);

                    //documents départ
                    $("#detailMobiliteContratBourse").prop("disabled", false);
                    $("#detailMobiliteConventionStage").prop("disabled", false);
                    $("#detailMobiliteCharteEtudiant").prop("disabled", false);
                    $("#detailMobiliteEngagement").prop("disabled", false);
                    $("#detailMobiliteTestLangueDepart").prop("disabled", false);
                    $("#detailMobiliteDocumentHauteEcoleDepart").prop("disabled", false);

                    //documents retour
                    $("#detailMobiliteAttestationSejour").prop("disabled", false);
                    $("#detailMobiliteReleveNote").prop("disabled", false);
                    $("#detailMobiliteCertificatStage").prop("disabled", false);
                    $("#detailMobiliteRapportFinal").prop("disabled", false);
                    $("#detailMobiliteTestLangueRetour").prop("disabled", false);
                    $("#detailMobiliteDocumentHauteEcoleRetour").prop("disabled", false);

                    //cacher boutons
                    $("#detailMobiliteCancel").css("display", "inline-block");
                    $("#detailMobiliteModify").css("display", "inline-block");
                }


            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
		
		//link info partner
		$("#detailMobilitePartenaire").on("click",function(){
			loadInfoPartner($(this).attr("id-partner"));
		});
		
	}

    $("#detailMobiliteCancel").click(function(){
        console.log("click clack");
        var id = $("#detailMobiliteIdMobilite").attr("data-id");
        loadCancelMobility(id);
    });

    $("#detailMobiliteModify").click(function(){
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "updateMobilityDetail",
                id: parseInt($("#detailMobiliteIdMobilite").attr("data-id")),
                nrVersion:parseInt($("#detailMobiliteNrVersion").attr("data-nrVer")),
                paiement1:$("#envoiPaiement1").prop("checked"),
                paiement2:$("#envoiPaiement2").prop("checked"),
                proEco:$("#encodageProEco").prop("checked"),
                mobilitytool:$("#encodageMobilityTool").prop("checked"),
                mobi:$("#encodageMobi").prop("checked"),
                departContratBourse:$("#detailMobiliteContratBourse").prop("checked"),
                departConventionStageEtudes:$("#detailMobiliteConventionStage").prop("checked"),
                departCharteEtudiant:$("#detailMobiliteCharteEtudiant").prop("checked"),
                departEngagement:$("#detailMobiliteEngagement").prop("checked"),
                departTestLangue:$("#detailMobiliteTestLangueDepart").prop("checked"),
                departDocumentHauteEcole:$("#detailMobiliteDocumentHauteEcoleDepart").prop("checked"),
                retourAttestationSejour:$("#detailMobiliteAttestationSejour").prop("checked"),
                retourReleveNotes:$("#detailMobiliteReleveNote").prop("checked"),
                retourCertificatStage:$("#detailMobiliteCertificatStage").prop("checked"),
                retourRapportFinal:$("#detailMobiliteRapportFinal").prop("checked"),
                retourTestLangue:$("#detailMobiliteTestLangueRetour").prop("checked"),
                retourDocumentHauteEcole:$("#detailMobiliteDocumentHauteEcoleRetour").prop("checked"),
                etat:$("#detailMobiliteEtat").html(),
                montant:$("#detailMobiliteMontant").val()
            },
            success: function (resp) {
                printToaster("success", "La mobilité à bien été modifiée.");
                changePage();
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
                printToaster(error.type, error.message);
            }
        });
    });

	function printToaster(type, message){
		switch(type){
			case "warning":
				toastr.warning(message);
				break;
			case "error":
				toastr.error(message);
				break;
			case "success":
				toastr.success(message);
				break;
			case "info":
				toastr.info(message);
				break;
		}

	}

    function checkPermission(){

        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "checkPermission"
            },
            success: function (resp) {
                resp = JSON.parse(resp);

                if (resp === 'TEACHER'){
                    $('#permissionHideFilds').val('');
                    $('#permissionHideFilds').val('TEACHER');
                }else if (resp === 'STUDENT') {
                    $('#permissionHideFilds').val('');
                    $('#permissionHideFilds').val('STUDENT');
                }
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
                printToaster(error.type, error.message);
            }
        });



    }

	// Export to CSV
	$("#CSV").click(function(){
		$("#list").tableToCSV();
	})


    $("#CSV_paiements").click(function(){
        $("#tablePayments").tableToCSV();
    })

    $("#CSV_mobility").click(function(){
        $("#document_csv_table").tableToCSV();
    })
});

