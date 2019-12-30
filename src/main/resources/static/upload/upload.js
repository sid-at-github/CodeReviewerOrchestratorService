user=localStorage.getItem("username");
if(user==undefined)
	//window.location.href = "../login/login.html";


var answer;
function upload() { 
	var title=$("#title").val();
    var tag = $("#tag").val().trim().split(', '); 
    var code = $("#code").val();
    var username=localStorage.getItem("username");
    request={"tags":tag, "code":code, "username":username,"title":title};
    post("revieweeCode",request).done(function(data){
    	alert("Code Uploaded");
    });
}
