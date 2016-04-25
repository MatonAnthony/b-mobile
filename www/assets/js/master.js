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
                authStudent();
            }else{
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

        $("#profile_bic").change(function(){
            var bic = document.getElementById("profile_bic");
            if(bic.validity.patternMismatch) {
                printToaster("info", "Le BIC ne peut être composé que de lettres.");
            }
        });
    };
    
    window.onpopstate = function (event) {
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
			case "partner" :
				loadInfoPartner("1");
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
                    email: $("#emailRegister").val()
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

    $("#registerLink").click(function () {
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
    $("#profileButton").click(function () {
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
                idUser: idUser
            },
            success: function (resp) {
                printToaster("success", "Vos informations ont bien été modifiées.");
            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
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
            '<td><select id="selectCountry' + nbRow + '" class="form-control">' +
            '</select>' +
            '</td>' +
            '</form>' +
            '</tr>';

        $("#addMobilityTableBody").append(value);
        addAccademicYearsToSelector(nbRow);
        addDepartmentsToSelector(nbRow);
        addCountriesToSelector(nbRow);
        addProgramsToSelector(nbRow);
        showCountriesByProgram(nbRow, 'Erasmus+');
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
                    year: $("#selectAccademicYear" + i).val()
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
    	showCountriesByProgram($(this).context['id'].charAt(13), this.value);
    });

    function showCountriesByProgram(row, programSelected){

    	$("#selectCountry" + row + ">option").each(function(){
    		var program = $(this).attr("program");
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

    function addAccademicYearsToSelector(id){
    	var academicYears = $("#selectAccademicYear1").html();
    	var selectName = "#selectAccademicYear" + id;
    	$(selectName).html(academicYears);
    }

    //userList

    $("#userListTableBody").on("click", ".btnNommer", function () {
        var id = $(this).attr("value");
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "changePermissions",
                id: id
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

                console.log(resp);
                $("input[name='name']").val(resp['name']);
                $("input[name='firstname']").val(resp['firstname']);
                $("input[name='gender']").val(resp['gender']);
                $("input[name='citizenship']").val(resp['citizenship']);
                $("input[name='street']").val(resp['street']);
                $("input[name='houseNumber']").val(resp['houseNumber']);

                $("input[name='city']").val(resp['city']);
                console.log(resp['country']);
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
                        $("#selectCountry1").append("<option program=\"" + resp['countries'][key]['idProgram'] + "\">" + resp['countries'][key]['nameFr'] + "</option>");
                    }
                     showCountriesByProgram(1, "Erasmus+");
                     for (key in resp['departments']) {
                        $("#selectDep1").append("<option>" + resp['departments'][key]['label'] + "</option>");
                    }
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
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "selectUsers"
            },
            success: function (resp) {
                resp = JSON.parse(resp);
                var key;
                $("#userListTableBody").html("");
                for (key in resp) {
                    var value;
                    if (resp[key]['permissions'] === "STUDENT") {
                        value = "<tr>"
                            + "<td>" + resp[key]['id'] + "</td>"
                            + "<td>" + resp[key]['name'] + "</td>"
                            + "<td>" + resp[key]['firstname'] + "</td>"
                            + '<td id="tdPermissions' + resp[key]['id'] + '">' + resp[key]['permissions'] + '</td>'
                            + '<td id="tdButtonNommer' + resp[key]['id'] + '"><button value="' + resp[key]['id'] + '" class="btnNommer btn btn-info">Nommer</button></td>'
                            + '<td id="tdButtonGererInfos' + resp[key]['id'] + '"><button value="' + resp[key]['id'] + '" class="btnGererInfos btn btn-info">Gérer les informations</button></td>'
                            + "</tr>";
                    } else {
                        value = "<tr>"
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

    }

	function loadAddPartner() {
	    $(".page").css("display", "none");
	    $("#navBarStudent").css("display", "block");
	    $("#addPartnerPage").css("display", "block");
	    $(".active").removeClass("active");
	    $(".navButton[href='#addPartner']").parent().addClass("active");

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

                console.log(resp);
                $("input[name='name']").val(resp['name']);
                $("input[name='firstname']").val(resp['firstname']);
                $("input[name='gender']").val(resp['gender']);
                $("input[name='citizenship']").val(resp['citizenship']);
                $("input[name='street']").val(resp['street']);
                $("input[name='houseNumber']").val(resp['houseNumber']);

                $("input[name='city']").val(resp['city']);
                console.log(resp['country']);
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
						$("#empty").empty();
						$("#list").after("<p id=\"empty\" class=\"text-center\"><strong> Il n'y aucune demande actuellement. </strong></p>");
					}else{
						clearButtons();
						resp = JSON.parse(resp);
						$("#list tbody").empty();
						$("#empty").empty();

						for (key in resp) {
							var data =
								"<tr class=\"clickable\" value='"+ resp[key]['id'] + "'>"+
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
									"<td></td></tr>";
							$("#list tbody").append(data);
						}
						
						$("#list tr td:nth-child(10)").each(function(){
							if ($(this).html() !== "Annulee") {
								$(this).next().append("<button class=\"btnCancel btn btn-info\">Annuler</button>");
							} else {
								$(this).parent().addClass("danger");
							}
							if ($(this).html() === "En attente"){
								$(this).next().next().append("<button class=\"btnConfirm btn btn-info\">Confirmer</button>");
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
		$("#list").on("click", "tr", function (){
			var id = $(this).attr("value");
			loadDetailsMobility(id);
		});
		$("#list").on("click", ".btnCancel", function (e) {
			e.stopPropagation();
			var id = $(this).parent().attr("value");
			loadCancelMobility(id);
		});

	}

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
					if (resp === ""){
						$("#empty").empty();
						$("#tablePayments").after("<p id=\"empty\" class=\"text-center\"><strong> Il n'y a aucun paiement actuellement. </strong></p>");
					}else{
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
					}
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
					resp = JSON.parse(resp);
					$("#tablePayments tbody").empty();
					$("#empty").empty();

					for (key in resp) {
						var data = "<tr class='clickable' value='"+ resp[key]['id'] + "'>" +
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

						if (resp[key]['paymentDate1'] !== null){
							var day = resp[key]['paymentDate1']['dayOfMonth'];
							var month = resp[key]['paymentDate1']['monthValue'];
							if (month>=1 && month <=9)
								month = "0"+month;
							if (day >= 1 && day <= 9)
								day = "0"+day;
							data += "<td>"+day+"/"+month+"/"+resp[key]['paymentDate1']['year']+"</td>";
						}
						else data += "<td></td>";

						if (resp[key]['paymentDate2'] !== null){
							var day = resp[key]['paymentDate2']['dayOfMonth'];
							var month = resp[key]['paymentDate2']['monthValue'];
							if (month>=1 && month <=9)
								month = "0"+month;
							if (day >= 1 && day <= 9)
								day = "0"+day;
							data += "<td>"+day+"/"+month+"/"+resp[key]['paymentDate2']['year']+"</td>";
						}
						else data +="<td></td>";

						data += "</tr>"

						$("#tablePayments tbody").append(data);
					}
	            },
	            error: function (error) {
	               	error = JSON.parse(error.responseText);
					printToaster(error.type, error.message);
	            }
	        });
		}
		$("#tablePayments").on("click", "tr", function (){
			var id = $(this).attr("value");
			loadDetailsMobility(id);
		});
	}

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
					if (resp === ""){
						$("#empty").empty();
						$("#tableConfirmed").after("<p id=\"empty\" class=\"text-center\"><strong> Il n'y aucune demande confirmée actuellement. </strong></p>");
					}else{
						resp = JSON.parse(resp);
						$("#tableConfirmed tbody").empty();
						$("#empty").empty();

						for (key in resp) {

							$("#tableConfirmed tbody").append(
								"<tr class='clickable' value='"+ resp[key]['id'] + "'>" +
								"<td>" + resp[key]['departmentDto']['label'] + "</td>" +
								"<td>" + resp[key]['programDto']['name'] + "</td>" +
								"<td>" + resp[key]['type'] + "</td>" +
								"<td>" + resp[key]['countryDto']['nameFr'] + "</td>" +
								"<td>" + resp[key]['studentDto']['name'] + "</td>" +
								"<td>" + resp[key]['studentDto']['firstname'] + "</td>" +
								"<td>" + resp[key]['status'] + "</td>"
								+ "</tr>");

						}
						$("#tableConfirmed tr td:last-child").each(function () {
							if ($(this).html() === "Annulee") {
								$(this).parent().addClass("danger");
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
		$("#tableConfirmed").on("click", "tr", function (){
			var id = $(this).attr("value");
			loadDetailsMobility(id);
		});
	}

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
					$("#myMobility tbody").empty();
					$("#empty").empty();
					if (resp === ""){
						$("#myMobility").after("<p id=\"empty\" class=\"text-center\"><strong> Vous n'avez aucune mobilité actuellement. </strong></p>");
					}else{
						resp = JSON.parse(resp);
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
								"<td value='"+ resp[key]['partnerDto'] +"' id='"+ resp[key]['id'] +"'></td>"+
								+ "</tr>");

						}	
						$("#myMobility tr td:nth-child(6)").each(function () {
							if ($(this).html() !== "Annulee") {
								$(this).next().append("<button class=\"btnCancel btn btn-info\">Annuler</button>");
							} else {
								$(this).parent().addClass("danger");
							}
							if ($(this).html() === "En attente"&& $(this).parent().attr("value") === undefined) {
								$(this).next().next().append("<button type=\"button\" class=\"btn btn-info\" id=\"btnConfirm\" data-toggle=\"modal\" data-target=\"#modalConfirmMobility\">Confirmer</button>");
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
		$("#myMobility").on("click", ".btnCancel", function () {
			var id = $(this).parent().attr("value");
			loadCancelMobility(id);

		});
		$("#myMobility").on("click", "#btnConfirm", function () {
            var id = $(this).parent().attr("id");
			loadConfirmMobility(id);

		});
	}


	function loadCancelMobility(id){
		$("#textReason").val("");
		var oldNavBar;
		var teacher=false;
		if($("#navBarTeacher").css("display") === "block"){
			teacher=true;
			oldNavBar = $("#navBarTeacher");
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
		}else{
			oldNavBar = $("#navBarStudent");
			$("#onlyTeacherReasons").css("display","none");
		}
		//Managing of the display
		$(".page").css("display", "none");
		$("#cancelMobilityPage").css("display","block");
		oldNavBar.css("display","block");

		$("#sendCancelation").on("click", function (){
			var reasonValue;
			var idReason=0;
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
					idReason: idReason
                },
                success: function (resp) {
					$(".page").css("display", "none");
					if (teacher){
						loadList();
					}else{
						authStudent();
					}
					printToaster("success","La mobilité a bien été annulée.")
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
		});

		
	}

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

                    $("#confirmMobilityPartner select").empty();
                    if (jQuery.isEmptyObject(resp['partners'])){
                        $("#confirmMobility ").hide();
                        $("#confirmMobilityPartner").empty();
                        $("#confirmMobilityPartner").append("<h5>PAS DE PARTENAIRE DISPONIBLE</h5>");

                    }
                    for (key in resp['partners']) {
                        $("#confirmMobilityPartner select").append(
                            "<option class=\"confirmClass\"id=" + resp['partners'][key]['id'] + ">" +
                            resp['partners'][key]['legalName'] + "</option>"
                        );
                    }

                    $('#confirmMbolityInfo').empty();
                    $('#confirmMbolityInfo').append(
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
        $("#modalConfirmMobility").on("click", "#confirmMobility", function () {

            var idMobility = id;
            var idPartner = $('#confirmMobilityPartner option:selected').attr('id');
            confirmMobility(idMobility, idPartner);
            $("#modalConfirmMobility").off();
        });
	}

    function confirmMobility(idMobility, idPartner) {
        $(function () {
            $.ajax({
                method: "POST",
                url: "/home",
                data: {
                    action: "confirmPartnerInMobility",
                    idMobility: idMobility,
                    idPartner: idPartner,
                },
                success: function (resp) {
                    $("#modalConfirmMobility").modal("hide");
                    printToaster("success", "Le partenaire à bien été confirmé");
                    //TODO (Kamil) Faire en sorte de recharger la page pour que le bouton Confirmer disparait.

                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                    console.log("err");
                }
            });

        });
    }

    function loadInfoPartner(id){
        (".page").css("display", "none");
        $("#partnerPage").css("display","block");
        $("#navBarTeacher").css("display","block");
        console.log("je passe");
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
                    console.log(resp);
                    $('#legalName').val(resp['legalName']);
                },
                error: function (error) {
                    error = JSON.parse(error.responseText);
                    printToaster(error.type, error.message);
                }
            });
        });

        /*if(){
         $("#setPartner").css("display","none");
         }else{
         $("#setPartner").css("display","block");
         }

         $("#setPartner").on("click",function(){

         });*/

    }

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
            	console.log(resp);
            	var city;
            	if(resp['partnerDto']['city'] === null){
            		city = undefined;
            	}else{
            		city = resp['partnerDto']['city'];
            	}
            	var intitule = "" +resp['programDto']['name'] + " " + resp['type'] 
            		+  " à " + city + " durant le quadri " + resp['quadrimester'];
            	$("#detailMobiliteIntitule").html(intitule);
            	$("#detailMobilitePartenaire").html("Partenaire : " + resp['partnerDto']['fullName']);
            	$("#detailMobiliteEtat").html("Etat : " + resp['status']);
            	
            	if(resp['paymentDate1'] != null){
            		$("#envoiPaiement1").prop("checked", true);
            	}else{
            		$("#envoiPaiement1").prop("checked", false);
            	}

            	if(resp['paymentDate2'] != null){
            		$("#envoiPaiement2").prop("checked", true);
            	}else{
            		$("#envoiPaiement2").prop("checked", false);
            	}

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

            	$("#detailMobiliteNom").html(resp['studentDto']['name']);
            	$("#detailMobilitePrenom").html(resp['studentDto']['firstname']);
            	$("#detailMobiliteSexe").html(resp['studentDto']['gender']);
            	$("#detailMobiliteDateNaissance").html(resp['studentDto']['birthDate']['dayOfMonth'] + "/" 
            		+ resp['studentDto']['birthDate']['monthValue'] + "/" + resp['studentDto']['birthDate']['year']);
            	$("#detailMobiliteNationalite").html(resp['studentDto']['citizenship']);
            	$("#detailMobiliteAdresse").html(resp['studentDto']['street'] + " " + resp['studentDto']['houseNumber']
            		+ ", " + resp['studentDto']['zip'] + " " + resp['studentDto']['city']);
            	$("#detailMobiliteTel").html(resp['studentDto']['tel']);
            	$("#detailMobiliteDepartement").html(resp['departmentDto']['label']);
            	$("#detailMobiliteMail").html(resp['studentDto']['email']);

            },
            error: function (error) {
                error = JSON.parse(error.responseText);
				printToaster(error.type, error.message);
            }
        });
	}

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

	// Export to CSV
	$("#CSV").click(function(){
		$("#list").tableToCSV();
	})
});

