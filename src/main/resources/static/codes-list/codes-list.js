function viewCode(codeId){
	localStorage.setItem('codeId',codeId);

	window.location.href = "../code_review/review.html";
}


function onLoad(){
	fetchResponse('revieweeCode').done(function(data){
			if(data){
				data = JSON.parse(data);
				const list = document.getElementById("code_snippets");
				list.innerHTML = data.map(e => `<li id=’${e.tags} >Programming Language: ${e.tags}</li>

						<li id=’${e.username} >Author: ${e.username}</li><button onclick='viewCode("${e.id}");'>View Code</button><hr>`).join('\n');
			}
	});
}
function searchCode(){
	search=$("#searchText").val();
	fetchResponse('revieweeCode?tag='+search).done(function(data){
		if(data){
			data = JSON.parse(data);
			const list = document.getElementById("code_snippets");
			list.innerHTML = data.map(e => `<li id=’${e.tags} >Programming Language: ${e.tags}</li>

					<li id=’${e.username} >Author: ${e.username}</li><button onclick='viewCode("${e.id}");'>View Code</button><hr>`).join('\n');
		}
	});
}

window.onload = function() {
	onLoad();
}
