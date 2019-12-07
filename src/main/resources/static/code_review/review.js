user=localStorage.getItem("username");
if(user==undefined)
	window.location.href = "../login/login.html";

codeId=localStorage.getItem('codeId');

window.onload = function() {
	var usernames={};
	var events;
	if(codeId!=undefined){
		fetchResponse("revieweeCode/"+codeId).done(function(data){
				data = JSON.parse(data);
				document.getElementById("tag").innerHTML=data.tags;
				document.getElementById("title").innerHTML=data.title;
				document.getElementById("author").innerHTML=data.username;
				document.getElementById("code").innerHTML=data.code;
				const list = document.getElementById("comments_list");
				list.innerHTML = data.feedbacks.map(e => `<li id=’${e.comment} >Comment: ${e.comment}</li>

						<li id=’${e.feedbackBy} >Author: ${e.feedbackBy}</li><hr>`).join('\n');
		});
	} else{
		window.location.href = "../codes-list/codes-list.html";
	}
	
	
	 $('.acc-title').click(function(e){
         var currentAttrvalue = $(this).attr('href');
         if($(e.target).is('.active')){
             $(this).removeClass('active');
             $('.accordion-section-content:visible').slideUp(300);
         } else {
             $('.acc-title').removeClass('active').filter(this).addClass('active');
             $('.accordion-section-content').slideUp(300).filter(currentAttrvalue).slideDown(300);
         }
     });
}
function comment_submit() { 
    	username=localStorage.getItem("username");
    	codeId=localStorage.getItem("codeId");
    	const comment = document.getElementById("comment");
    	codeId=localStorage.getItem('codeId');

    	request={"comment":$("#comment").val(), "feedbackBy":username};
    	put("revieweeCode/"+codeId,request).done(function(data){
    		alert("Submitted");
    	});
    }


