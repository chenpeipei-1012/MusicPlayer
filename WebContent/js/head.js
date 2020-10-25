// JavaScript Document
var myMusicMenu = document.getElementById("myMusic");
var myMusicMenu = document.getElementById("findMusic");

function blueMenu(id){
	document.getElementById(id).style.backgroundColor = "#000";
}
function moveMenu(id){
	document.getElementById(id).style.backgroundColor = "#242424";
}

function setUserInfo(){
	location.href="setting.html";
}

var userinfoList = document.getElementById("userinfo-list-wrap");


function mouseOutUserPic() {
   userinfoList.style.display = "none";
}