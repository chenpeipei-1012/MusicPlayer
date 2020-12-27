initPage();

function initPage(){
	$("#mymusic").css("background","#242424");
	$("#find").css("background","#000");
	// 去掉发现音乐的悬浮事件
	$("#find").unbind("mouseout");
	
	// 给我的音乐加上悬浮事件
	$("#mymusic").mouseout(function(){
		this.style.backgroundColor = "#242424";
	});
}
