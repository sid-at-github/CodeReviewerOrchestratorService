
	function signup() {
		if($("#password").val() != $("#confirmPassword").val()){
			alert("Password and Confirm Password do not match.")
		}
		else{
			userData={"username":$("#username").val(),"email":$("#email").val(),"password":$("#password").val()};
			post("reviewee",userData).done(function(data){
				alert("Sign Up Successful");
			});
		}
}