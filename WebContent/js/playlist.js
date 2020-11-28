var popBg = document.getElementById("pop-bg");
var pop = document.getElementById("pop");

var love = document.getElementById("love");

$.ajax({
    type : "GET",
    async : true,         
    url : "/MusicPlayer/playlist",    
    dataType : "json",        //返回数据形式为json
    
    success : function(result) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
        //填充数据
	     // 先填充左侧歌单
	        var content = "";
	        var playlistArr = result.playlist;
	        for(var i=0;i<playlistArr.length;i++){
	        	content += '<li class="selected" onClick="choiceMusicList(this)" id="';
	        	if(i == 0){
	        		content += 'love"';
	        	}else{
	        		content += playlistArr[i].listId + '"';
	        	}
	        	
	        	content += '<div class="left-item">' +
	        	              '<div class="left-item-image">' +
	        	                  '<img src="image/music_list.jpg">' + 
	      					  '</div>' + 
	      					  '<div class="left-item-text">' + 
	      						  '<div class="desc-item">' + playlistArr[i].listName +'</div>' + 
	      						  '<div class="num-item">3首</div>' +
	      					  '</div>' + 
	      				   '</div>'
	      			   '</li>';
	        }
	        
	        $("#music-file-list").append(content);
	        
	        // 再填充右侧歌曲列表
	        var musiclist = "";
	        var musiclistArr = result.musiclist;
	        for(var i=0;i<musiclistArr.length;i++){
	        	if((i+1)%2==1)
	        		musiclist +='<tr id="table-rows-1" class="tbg">';
	        	else
	        		musiclist +='<tr id="table-rows-1">';
	        	musiclist +=      '<td class="w1">' + (i+1) + '</td>' + 
								  '<td class="w2">' + 
									  '<img src="/MusicPlayer/image/play.png"/>' + 
								  '</td>' + 
								  '<td class="w3">' + musiclistArr[i].musicName + '</td>' + 
								  '<td class="w4">03:36</td>' + 
								  '<td class="w5">' + musiclistArr[i].musicAuthor + '</td>' + 
								  '<td class="w6">' + musiclistArr[i].musicAlbum + '</td>' + 
							'</tr>'
	        }
	        
	        $("#mymusiclist").append(musiclist);
        
    },
    error : function(errorMsg) {
        //请求失败时执行该函数
        alert("请求数据失败!");
    }
});


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

function tableRowsOver(dd){
	var trTable = document.getElementById("table-rows-1");
	//trTable.style.
}
function tableRowsOut(aa){
	
}

// 选择歌单
function choiceMusicList(musiclist){
	// 得到当前选择的歌单 和 之前选择的
	var curSelected = document.getElementById(musiclist.id);
	var preSelected = document.getElementsByClassName("selected");
	preSelected = preSelected[0];
	
	preSelected.removeAttribute("class");
	preSelected.setAttribute("class","no-selected");
	// 选中: 灰色   之前的: 白色
	curSelected.removeAttribute("class");
	curSelected.setAttribute("class","selected");
	
	
	// 刷新右边的歌曲列表  --> 部分刷新
	refreshMusicList();
	
}

function refreshMusicList(){
	// TO DO 
	// 刷新iframe  
	var iframe = document.getElementById("rightiframe");
	iframe.contentWindow.location.reload(true);
}

// 弹出新建窗口
function createNewMList(){
	popBg.style.display = "block";
	pop.style.display = "block";
}


function cancelMList(){
	// 清除弹窗信息，并关闭弹窗
	popBg.style.display = "none";
	pop.style.display = "none";
	
	errorSpan.innerHTML = "";
	error.style.display = "none";
}

// 新建歌单
function createMList(){
	// 非空检查
	var listname = document.getElementById("listname");
	var errorSpan = document.getElementById("error-msg");
	var error = document.getElementById("error");
	
	var errorMsg = ""
	var isSubmit = true;
	
	// 清除上次的错误信息
	errorSpan.innerHTML = "";
	error.style.display = "none";
	
	if(listname.value == ""){
		errorMsg = "歌单名不能为空";
		isSubmit = false;
	}else if(listname.value.length > 20){
		errorMsg = "歌单名不能超过20个字符！";
		isSubmit = false;
	}
	
	if(isSubmit){
		// 提交表单，成功后更新右边的数据：加在我喜欢的音乐后面    即按时间倒序排序
		
		var listUl = document.getElementById("music-file-list");
		var preSelected = document.getElementsByClassName("selected");
		preSelected = preSelected[0];
		var str = "";
		
		var element = document.createElement("li");
		
//		 '<li class="selected" onClick="choiceMusicList(this)" id="list">' + 
			str =	'<div class="left-item">' + 
						'<div class="left-item-image">' +
							'<img src="image/music_list.jpg">' +
						'</div>' +
						'<div class="left-item-text">' +
							'<div class="desc-item">test</div>' +
							'<div class="num-item">0首</div>' +
						'</div>' +
					'</div>' ;
//				'</li>' ;
		
		// 默认选中新建的文件夹
		preSelected.removeAttribute("class");
	    preSelected.setAttribute("class","no-selected");
		
		// 把li标签加在最前面
		element.innerHTML = str;
		element.setAttribute("class","selected");
		element.setAttribute("id","list");
		element.setAttribute("onClick","choiceMusicList(this)");
		
		love.after(element);
		// 取消弹窗
		cancelMList();
	}else{
		errorSpan.innerHTML = errorMsg;
		error.style.display = "block";
	}
		
	
}