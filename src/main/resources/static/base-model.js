function post(url_path, object){
var deferred = $.Deferred();
var url = "/"+url_path;

var json = JSON.stringify(object);

var xhr = new XMLHttpRequest();
xhr.open("POST", url, true);
xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
xhr.onload = function () {
	if (xhr.readyState == 4 && (xhr.status == "201")||(xhr.status == "204")) {
		deferred.resolve(xhr.status);
	} else{
		deferred.reject("HTTP error: " + xhr.status);
	}
}
xhr.send(json);
return deferred.promise();
};



function put(url_path, object){
	var deferred = $.Deferred();
	var url = "/"+url_path;

	var json = JSON.stringify(object);

	var xhr = new XMLHttpRequest();
	xhr.open("PUT", url, true);
	xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
	xhr.onload = function () {
		if (xhr.readyState == 4 && (xhr.status == "201")||(xhr.status == "200")) {
			deferred.resolve(xhr.status);
		} else{
			deferred.reject("HTTP error: " + xhr.status);
		}
	}
	xhr.send(json);
	return deferred.promise();
	};
	
	


function fetchResponse(url_path){
var deferred = $.Deferred();
var url = "/"+url_path;

var xhr = new XMLHttpRequest();
xhr.open("GET", url, true);
xhr.setRequestHeader('Content-type','application/json; charset=utf-8');
xhr.onload = function () {
	var data = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && (xhr.status == "200")||(xhr.status == "201")) {
		deferred.resolve(xhr.response);
	} else{
		deferred.reject("HTTP error: " + xhr.status);
	}
}
xhr.send();
return deferred.promise();
};
