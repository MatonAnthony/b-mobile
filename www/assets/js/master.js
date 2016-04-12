$(function () {

    $.ajax({
        method: "POST",
        url: "/home",
        data: {
            action: "authenticate"
        },
        success: function (resp) {
            resp = JSON.parse(resp);
            if (resp.permissions === "STUDENT") {
                authStudent();
                history.pushState({page: "myMobility"}, "Mes mobilités", "/home#myMobility");
            } else {
                authTeacher();
                history.pushState({page: "confirmedMobility"}, "Mobilites Confirmées", "/home#confirmedMobility");
            }

        },
        error: function (error) {
            console.log("Authentification echouée");
        }
    });

    $(window).load(function () {
        changePage();
    });

    window.onpopstate = function (event) {
        changePage();
    };

    function changePage() {
        var state = history.state;
        if (null === state)return true;
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
            case "confirmedMobility" :
                loadConfirmedMobility();
                break;
            case "addPartner" :
                loadAddPartner();
                break;
            case "myInformations" :
                loadProfilePage();
                break;
            default:
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
                    console.log("erreur lors de l'enregistrement");
                }
            });
        } else {
            $("#password_matching").show();
        }
        return false;
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
                $("#loginPage").css("display", "block");
                $("#navBarStudent").css("display", "none");
                $("#navBarTeacher").css("display", "none");
                $("#profilePage").css("display", "none");
                $("#studentHomePage").css("display", "none");
                $("#teacherHomePage").css("display", "none");
                $("#addMobilityPage").css("display", "none");
                $("#addPartnerPage").css("display", "none");
                $("#userListPage").css("display", "none");
                $("#listPage").css("display", "none");
            },
            error: function (e) {
                console.log(e.message);
            }
        });
        $(".navButton[href='#confirmedMobility']").parent().addClass("active");
        $(".navButton[href='#myMobility']").parent().addClass("active");
    }

    //MyProfile
    $("#profileButton").click(function () {
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "updateUser",
                name: $("input[name='name']").val(),
                firstname: $("input[name='firstname']").val(),
                gender: $("select[name='gender']").val(),
                // Birthdate on doit toujours se décider
                citizenship: $("input[name='citizenship']").val(),
                street: $("input[name='street']").val(),
                houseNumber: $("input[name='houseNumber']").val(),
                mailbox: $("input[name='mailbox']").val(),
                zipcode: $("input[name='zipcode']").val(),
                city: $("input[name='city']").val(),
                country: $("input[name='country']").val(),
                tel: $("input[name='tel']").val(),
                email: $("input[name='email']").val(),
                successfullYearsInCollege: $("input[name='successfullYearsInCollege']").val(),
                iban: $("input[name='iban']").val(),
                accountHolder: $("input[name='accountHolder']").val(),
                bankName: $("input[name='bankName']").val(),
                bic: $("input[name='bic']").val()
            },
            success: function (resp) {
                console.log("profileButton retour OK");
            },
            error: function (resp) {
                console.log("profileButton retour not ok");
            }
        });
        return false;
    });

    function loadAddMobility() {
        $("#loginPage").css("display", "none");
        $("#navBarStudent").css("display", "block");
        $("#navBarTeacher").css("display", "none");
        $("#profilePage").css("display", "none");
        $("#studentHomePage").css("display", "none");
        $("#teacherHomePage").css("display", "none");
        $("#addMobilityPage").css("display", "block");
        $("#addPartnerPage").css("display", "none");
        $("#listPage").css("display", "none");
        $("#userListPage").css("display", "none");
    }

    //AddMobility
    $("#addMobilityRow").click(function () {

        var nbRow = $("#addMobilityTable").attr("numberOfRows");
        if (nbRow == 3)return false;
        nbRow++;
        $("#addMobilityTable").attr("numberOfRows", nbRow);
        var value =
            "<tr> " +
            "<form>" +
            '<td>' + nbRow + '</td>' +
            '<td>' +
            '<select id="selectProgram' + nbRow + '" class="form-control">' +
            '</select>' +
            '</td>' +
            '<td><input type="radio" name="optionsRadios' + nbRow + '" value="SMS" checked /></td>' +
            '<td><input type="radio" name="optionsRadios' + nbRow + '" value="SMP"/></td>' +
            '<td><select id="selectQuadri' + nbRow + '" class="form-control">' +
            '<option value="1">1</option>' +
            '<option value="2">2</option>' +
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
        addDepartmentsToSelector(nbRow);
        addCountriesToSelector(nbRow);
        addProgramsToSelector(nbRow);
        return true;
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
                    country: $("#selectCountry" + i).val()
                },
                success: function (resp) {
                    console.log("Ajout de la mobilité dans la DB OK");
                },
                error: function (error) {
                    console.log("Problème lors de l'ajout de la mobilité dans la db");
                }
            });
        }
        return true;
    });

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
            },
            error: function (error) {
                console.log("Problème lors du changement des permissions de l'utilisateur");
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
                console.log("Ajout de la mobilité dans la DB OK");
            },
            error: function (error) {
                console.log("Problème lors de l'ajout de la mobilité dans la db");
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
                history.pushState({page: "addMobility"}, "Ajouter une mobilité", "/home#addMobility");
                break;
            case "#disconnect" :
                disconnect();
                history.pushState({page: "index"}, "Page d'accueil", "/home");
                break;
            case "#list":
                loadList();
                history.pushState({page: "list"}, "Liste des demandes de paiement", "/home#list");
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

        }
    });

    //Chargement des pages.
    function authStudent() {
        $("#loginPage").css("display", "none");
        $("#navBarStudent").css("display", "block");
        $("#navBarTeacher").css("display", "none");
        $("#profilePage").css("display", "none");
        $("#studentHomePage").css("display", "block");
        $("#teacherHomePage").css("display", "none");
        $("#registerPage").css("display", "none");
        $("#listPage").css("display", "none");
        $("#addMobilityPage").css("display", "none");
        $("#addPartnerPage").css("display", "none");
        $("#userListPage").css("display", "none");
        loadMyMobility();
        $(".active").removeClass("active");
        $(".navButton[href='#myMobility']").parent().addClass("active");
    }

    function authTeacher() {
        $("#loginPage").css("display", "none");
        $("#navBarStudent").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#profilePage").css("display", "none");
        $("#studentHomePage").css("display", "none");
        $("#teacherHomePage").css("display", "block");
        $("#registerPage").css("display", "none");
        $("#listPage").css("display", "none");
        $("#addMobilityPage").css("display", "none");
        $("#addPartnerPage").css("display", "none");
        $("#userListPage").css("display", "none");
        loadConfirmedMobility();
        $(".active").removeClass("active");
        $(".navButton[href='#confirmedMobility']").parent().addClass("active");
    }

    function loadList() {

        $("#loginPage").css("display", "none");
        $("#navBarStudent").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#profilePage").css("display", "none");
        $("#studentHomePage").css("display", "none");
        $("#teacherHomePage").css("display", "none");
        $("#registerPage").css("display", "none");
        $("#addMobilityPage").css("display", "none");
        $("#addPartnerPage").css("display", "none");
        $("#listPage").css("display", "block");
        $("#userListPage").css("display", "none");
        loadMobility();
        $(".active").removeClass("active");
        $(".navButton[href='#list']").parent().addClass("active");
        $("#tableConfirmed tbody").empty();

    }

    function loadUserList() {
        $("#loginPage").css("display", "none");
        $("#navBarStudent").css("display", "none");
        $("#navBarTeacher").css("display", "block");
        $("#profilePage").css("display", "none");
        $("#studentHomePage").css("display", "none");
        $("#teacherHomePage").css("display", "none");
        $("#registerPage").css("display", "none");
        $("#addMobilityPage").css("display", "none");
        $("#addPartnerPage").css("display", "none");
        $("#listPage").css("display", "none");
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

                console.log("Problème lors de la récuperation de la liste des utilisateurs");
            }
        });
        $(".active").removeClass("active");
        $(".navButton[href='#userList']").parent().addClass("active");
    }

    if ($("#selectCountry1").html() == "") {
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
                    $("#selectCountry1").append("<option>" + resp[key]['nameFr'] + "</option>");
                }
            },
            error: function (error) {
                console.log("Problème lors de la récuperation de la liste des pays");
            }
        });
    }
    if ($("#selectDep1").html() == "") {
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
                    $("#selectDep1").append("<option>" + resp[key]['label'] + "</option>");
                }
            },
            error: function (error) {
                console.log("Problème lors de la récuperation de la liste des departements");
            }
        });
    }
    if ($("#selectProgram1").html() == "") {
        $.ajax({
            method: "POST",
            url: "/home",
            data: {
                action: "selectPrograms"
            },
            success: function (resp) {
                resp = JSON.parse(resp);
                var key;
                for (key in resp) {
                    $("#selectProgram1").append("<option>" + resp[key]['name'] + "</option>");
                }
            },
            error: function (error) {
                console.log("Problème lors de la récuperation de la liste des programmes");
            }
        });
    }
    $(".active").removeClass("active");
    $(".navButton[href='#addMobility']").parent().addClass("active");
});
function loadAddPartner() {
    $("#loginPage").css("display", "none");
    $("#navBarStudent").css("display", "block");
    $("#navBarTeacher").css("display", "none");
    $("#profilePage").css("display", "none");
    $("#studentHomePage").css("display", "none");
    $("#teacherHomePage").css("display", "none");
    $("#addMobilityPage").css("display", "none");
    $("#addPartnerPage").css("display", "block");
    $("#listPage").css("display", "none");
    $("#userListPage").css("display", "none");

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
                console.log("Problème lors de la récuperation de la liste des pays");
            }
        });
    }
}

function loadRegisterPage() {
    $("#loginPage").css("display", "none");
    $("#navBarStudent").css("display", "none");
    $("#navBarTeacher").css("display", "none");
    $("#profilePage").css("display", "none");
    $("#studentHomePage").css("display", "none");
    $("#teacherHomePage").css("display", "none");
    $("#addPartnerPage").css("display", "none");
    $("#registerPage").css("display", "block");
    $("#listPage").css("display", "none");
    $("#userListPage").css("display", "none");
}


function loadProfilePage() {
    $("#loginPage").css("display", "none");
    $("#navBarStudent").css("display", "block");
    $("#navBarTeacher").css("display", "none");
    $("#profilePage").css("display", "block");
    $("#studentHomePage").css("display", "none");
    $("#teacherHomePage").css("display", "none");
    $("#addPartnerPage").css("display", "none");
    $("#registerPage").css("display", "none");
    $("#listPage").css("display", "none");
    $("#userListPage").css("display", "none");

	$(".active").removeClass("active");
	$(".navButton[href='#myInformations']").parent().addClass("active");

	$.ajax({
		method: 'POST',
		url: '/home',
		data: {
			action: 'selectProfile'
		},
		success: function(resp){
			console.log(resp);
			resp = JSON.parse(resp);
			$("input[name='name']").val(resp['name']);
			$("input[name='firstname']").val(resp['firstname']);
			$("input[name='gender']").val(resp['gender']);
			//	TODO : Gérer la date de naissance
			// $("input[name='birthdate']").val(resp['birthdate']);
			$("input[name='citizenship']").val(resp['citizenship']);
			$("input[name='street']").val(resp['street']);
			$("input[name='houseNumber']").val(resp['houseNumber']);
            $("input[name='mailbox']").val(resp['mailbox']);
			$("input[name='zipcode']").val(resp['zip']);
			$("input[name='tel']").val(resp['tel']);
			$("input[name='email']").val(resp['email']);
			$("input[name='successfullYearsInCollege']").val(resp['successfullYearInCollege']);
			//$("input[name='iban ']").val(resp["iban"]["value"]);
			$("input[name='accountHolder']").val(resp['accountHolder']);
			$("input[name='bankName']").val(resp['bankName']);
			$("input[name='bic']").val(resp['bic']);
		},
		error: function(error){
			console.log("Pré-remplissage du profil impossible");
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
                resp = JSON.parse(resp);
                $("#list tbody").empty();

                for (key in resp) {

                    $("#list tbody").append(
                        "<tr>" +
                        "<td>" + resp[key]['id'] + "</td>" +
                        "<td>" + resp[key]['studentDto']['name'] + "</td>" +
                        "<td>" + resp[key]['studentDto']['firstname'] + "</td>" +
                        "<td>" + resp[key]['departmentDto']['label'] + "</td>" +
                        "<td>" + resp[key]['preferenceOrder'] + "</td>" +
                        "<td>" + resp[key]['programDto']['name'] + "</td>" +
                        "<td>" + resp[key]['type'] + "</td>" +
                        "<td>" + resp[key]['quadrimester'] + "</td>" +
                        //+"<td>"+resp[key]['partnerDto']['legal_name']+"</td>"
                        "<td>" + "</td>"
                        + "</tr>");

                }
            },
            error: function (error) {
                console.log("Connexion echouée");
            }
        });
        //TODO (fany) doit on mettre un bouton confirmer?
        /*$("#list tr td:last-child").each(function(){
         if ($(this).html() !== "Annulée"){
         $(this).next().append("<p>Annuler</p>");
         $(this).parent().addClass("danger");
         }else{
         $(this).next().append("<button>Confirmer</button>");
         });*/

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
                resp = JSON.parse(resp);
                $("#tableConfirmed tbody").empty();

                for (key in resp) {

                    $("#tableConfirmed tbody").append(
                        "<tr>" +
                        "<td>" + resp[key]['departmentDto']['label'] + "</td>" +
                        "<td>" + resp[key]['programDto']['name'] + "</td>" +
                        "<td>" + resp[key]['type'] + "</td>" +
                        "<td>" + resp[key]['countryDto']['nameFr'] + "</td>" +
                        "<td>" + resp[key]['studentDto']['name'] + "</td>" +
                        "<td>" + resp[key]['studentDto']['firstname'] + "</td>" +
                        "<td>" + resp[key]['status'] + "</td>"
                        + "</tr>");

                }
            },
            error: function (error) {
                console.log("Connexion echouée");
            }
        });

        $("#tableConfirmed tr td:last-child").each(function () {
            if ($(this).html() === "Annulée") {
                $(this).parent().addClass("danger");
            }
        });

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
                resp = JSON.parse(resp);
                $("#myMobility tbody").empty();

                for (key in resp) {

                    $("#myMobility tbody").append(
                        "<tr>" +
                        "<td>" + resp[key]['preferenceOrder'] + "</td>" +
                        "<td>" + resp[key]['programDto']['name'] + "</td>" +
                        "<td>" + resp[key]['type'] + "</td>" +
                        "<td>" + resp[key]['countryDto']['nameFr'] + "</td>" +
                        "<td>" + resp[key]['quadrimester'] + "</td>" +
                        "<td>" + resp[key]['status'] + "</td>"
                        + "</tr>");

                }
            },
            error: function (error) {
                console.log("Connexion echouée");
            }
        });

        $("#myMobility tr td:nth-child(6)").each(function () {
            if ($(this).html() !== "Annulée") {
                $(this).next().append("<button>Annuler</button>");
            } else {
                $(this).parent().addClass("danger");
            }
            if ($(this).html() === "En_ATTENTE") {
                $(this).next().next().append("<button>Confirmer</button>");
            }
        });
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


