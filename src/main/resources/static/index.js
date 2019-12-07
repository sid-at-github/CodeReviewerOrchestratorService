window.onload = function() {
username=localStorage.length>0?localStorage.get("username"):undefined;
if(username==undefined){
	document.getElementById('upload').style.display="none";
	document.getElementById('review').style.display="none";
	document.getElementById('logout').innerHTML="Login";
	document.getElementById("logout").href="./login/login.html";
	document.getElementById("home").href="./index.html";
}
}