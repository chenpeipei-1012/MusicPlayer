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

	// 为DOM元素添加点击事件
//	var imageWrap = document.getElementById("image-wrap");
	var userinfoList = document.getElementById("userinfo-list-wrap");
//	imageWrap.addEventListener("mouseover", mouseOverUserPic());
//	imageWrap.addEventListener("mouseout", mouseOutUserPic());

	function mouseOverUserPic() {
		//userinfoList.style.display = "block";
	}

	function mouseOutUserPic() {
	   userinfoList.style.display = "none";
	}
	
    
	