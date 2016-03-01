function login(){
    $.ajax({
        method: "POST",
        url: "/home",
        data: {
            action: "login",
            username : $(input[name='username']).val(),
            password : $(input[name='password']).val()
        },
        success: function(html_code, status){
            console.log("Connexion réussie");
        },
        error: function(html_code, status){
            console.log("Connexion echouée");
        }
    });
}