// 全局变量定义
var popBg = document.getElementById("pop-bg");
var pop = document.getElementById("pop");

var love = document.getElementById("love");

var listname = document.getElementById("listname");
var errorSpan = document.getElementById("error-msg");
var error = document.getElementById("error");

// 展开 or 收起  歌单列表
function operMusicList(){
	var img = document.getElementById("file-img");
	var ul = document.getElementById("music-file-list");

	// 判断菜单时展开的还是关闭的
	if(ul.style.display == "none"){
		// 关闭，则打开；且显示ul
		img.src = "image/icon_open.png";
		ul.style.display = "block";
	}else{
		img.src = "image/icon_close.png";
		ul.style.display = "none";
	}
}

// 选择某个歌单
function choiceMusicList(musiclist){
	// 得到当前选择的歌单 和 之前选择的
	var curSelected = document.getElementById(musiclist.id);
	var preSelected = document.getElementsByClassName("selected");
	preSelected = preSelected[0];
	
	preSelected.removeAttribute("class");
	preSelected.setAttribute("class","no-selected");
	
	// 选中: 背景置灰   之前的: 白色
	curSelected.removeAttribute("class");
	curSelected.setAttribute("class","selected");
	
	// 刷新右边的歌曲列表  --> 部分刷新
	refreshMusicList();
}

// 刷新歌曲列表
function refreshMusicList(){
	var iframe = document.getElementById("rightiframe");
	iframe.contentWindow.location.reload(true);
}

// 弹出新建窗口
function createNewMList(){
	popBg.style.display = "block";
	pop.style.display = "block";
}

// 取消新建窗口
function cancelMList(){
	// 清除弹窗信息:错误信息、输入框的值，并关闭弹窗
	popBg.style.display = "none";
	pop.style.display = "none";
	
	errorSpan.innerHTML = "";
	error.style.display = "none";
	
	listname.value = "";
}

// 新建歌单
function createMList(){
	var errorMsg = ""
	var isSubmit = true;
	
	// 清除上次的错误信息
	errorSpan.innerHTML = "";
	error.style.display = "none";
	
	// 数据合法性校验
	if(listname.value == ""){
		errorMsg = "歌单名不能为空";
		isSubmit = false;
	}else if(listname.value.length > 20){
		errorMsg = "歌单名不能超过20个字符！";
		isSubmit = false;
	}
	
	if(isSubmit){
		// 提交表单，成功后更新右边的数据：加在我喜欢的音乐后面    即按时间倒序排序
		// TO DO   传给后台

		var listUl = document.getElementById("music-file-list");
		var preSelected = document.getElementsByClassName("selected");
		preSelected = preSelected[0];
		var str = "";
		
		var element = document.createElement("li");
		
		str = '<div class="left-item">' + 
					'<div class="left-item-image">' +
						'<img src="image/music_list.jpg">' +
					'</div>' +
					'<div class="left-item-text">' +
						'<div class="desc-item">test</div>' +
						'<div class="num-item">0首</div>' +
					'</div>' +
			  '</div>' ;
		
		// 默认选中新建的文件夹
		preSelected.removeAttribute("class");
	    preSelected.setAttribute("class","no-selected");
		
		element.innerHTML = str;
		element.setAttribute("class","selected");
		element.setAttribute("id","list");
		element.setAttribute("onClick","choiceMusicList(this)");
		
		// 把li标签加在love后面
		love.after(element);
		
		// 取消弹窗
		cancelMList();
	}else{
		errorSpan.innerHTML = errorMsg;
		error.style.display = "block";
	}
}