function login() {
	if(localStorage.getItem('username')!=undefined){
		window.location.href = "../index.html";
	}
	username=$("#username").val();
	password=$("#password").val();
	cred={"username":username,"password":password};
	if(username=='' || password==''){
		alert("Please enter Username/Password")
	}
	else{
		fetchResponse("reviewee?username="+username+"&password="+password).done(function(data){
			if(data){
				localStorage.setItem('username', username);
				window.location.href = "../home/home.html";
			}
		});
	}
}

function logout(){
	localStorage.clear();
}
logout();
