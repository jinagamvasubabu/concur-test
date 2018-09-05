
var rootURL = "http://localhost:8081/billing/api";
var webURL = "http://localhost:8081/billing"

$(document).ready(function(){
    console.log("All set!!!");
    $("#createMe").click(function(){
      console.log("Going to create User!!!");
      createUser();
    });

     $("#loginMe").click(function(){
          console.log("Login user!!!");
          loginMe();
    });

});

$("#createMe").click(function(){
  console.log("Going to create User!!!");
  createUser();
});

function createUser() {
console.log($('#username').val());
if ($('#username').val()!="" && $('#password').val() != "") {
        console.log('Onboarding user!!!');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL+"/user/signup",
		data: userToJson(),
		success: function(data, textStatus, jqXHR){
        			alert(data);
        			if (data === "You have signedup successfully!!!!")
        			    window.location.href = webURL+"/index.html";
        		},
        error: function(jqXHR, textStatus, errorThrown){
            alert('Error: ' + textStatus);
        }
	});
	} else {
	    alert("Please fill all fields!!!");
	}
}

function loginMe() {
    console.log('Login the user!!!');
    if ($('#username').val() != "" && $('#password').val() != "") {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: rootURL+"/user/login",
            data: userToJson(),
            success: function(data, textStatus, jqXHR){
                        console.log(data);
                        if (data === "User Logged in!!!")
                            window.location.href = webURL+"/html/welcome.html";
                            else
                          alert("Invalid Credentials!!!");
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert('Error: ' + textStatus);
            }
        });
	} else {
	     alert("Please fill all fields!!!");
	}
}

function userToJson() {
 if ($('#username').val() && $('#password').val())
	return JSON.stringify({
		"userName": $('#username').val(),
		"password": $('#password').val()
		});
}



