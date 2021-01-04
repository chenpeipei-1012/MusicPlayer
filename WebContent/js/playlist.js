var popBg = document.getElementById("pop-bg");
var pop = document.getElementById("pop");
var errorSpan = document.getElementById("error-msg");

// 当前歌单
var musiclistArr;
var playlistArr;


initMyMusicPage();

function initMyMusicPage(){
	// 其他的颜色
	$("#find").css("background","#242424");
	$("#mymusic").css("background","#000");
	
	// 去掉我的音乐的悬浮事件
	$("#mymusic").unbind("mouseout");
	$("#find").mouseout(function(){
		this.style.backgroundColor = "#242424";
	});
}


function playList(result) {
    // 请求成功时执行该函数内容，result即为服务器返回的json对象
    // 填充数据: 先填充左侧歌单
    playlistArr = result.playlist;
    musiclistArr = result.musiclist;
    
    var curList = fillLeftListData(playlistArr);
    fillMusicListInfo(curList);
    fillMusicListData(musiclistArr);
}

// 填充右侧上部分列表信息
function fillMusicListInfo(curList){
	$("#created-time,#music-num").empty();
	var time = curList.listTime.time;
	var date = new Date(time);
	
	$("#r-mlistname").empty();
	$("#r-mlistname").append(curList.listName);
	
	// 头像
	
	
	// 用户名
//	$("#ml-username").empty();
//	$("#ml-username").append();
	
	// 日期输出格式：2020-10-12
	var day = date.getDay();
	var createdTime = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
	$("#created-time").append(createdTime);
	$("#music-num").append(curList.musicNum + "首歌");
	
	var button = "";
	// 当前歌单是否有歌
	$("#list-play").remove();
	
	if(curList.musicNum == 0){
		
		button += '<a class="list-play-non" id="list-play">' +
						'<i class="">' +
						'	<span class="non-play-font">播放</span>' +
						'</i>' +
					'</a>';
	}else{
		button += '<a class="list-play" id="list-play">' +
						'<i class="list-play-bg">' +
							'<em class="list-play-icon"></em>' +
							'<span class="icon-font">播放</span>' +
						'</i>' +
					'</a>';
	}
	
	$(".t_operator").append(button);
	
	// 添加点击事件
	if(curList.musicNum != 0){
		// 点击歌单列表中的播放音乐
		$('#list-play').click(function(){
			// 将当前歌单数据加到带播放列表中 concat连接    push：放元素
			
			// 循环加入
			var cruIndex;
			for(var i=0;i<musiclistArr.length;i++){
				cruIndex = findMusicListIndex(musiclistArr[i].musicId,musicList);

				if(cruIndex == -1){
					// 没有则加入
					cruIndex = musicList.push(musiclistArr[i]);
					cruIndex--;
				}
				
				if(i == 0){
					curMusic = cruIndex;
				}
			}

			
			playMusic();
			
		});
	}
}



// 填充左侧歌单
function fillLeftListData(playlistArr){
	var content = "";
	var listType;
	var curList;
	var picPath = "";
	
    for(var i=0;i<playlistArr.length;i++){
    	listType = playlistArr[i].listLove;
    	if(listType == 1){
    		// 我喜欢的音乐：获取歌曲总数和创建时间musicNum
    		$(".love .num-item").empty();
    		$(".love .num-item").append(musiclistArr.length + "首");
    		
    		// 给元素设置ID
    		$(".love").attr("id",playlistArr[i].listId);
    		curList = playlistArr[i];
    		continue;
    	}
    	if(listType == 2){
    		// 默认收藏
    		$(".default .num-item").empty();
    		$(".default .num-item").append(playlistArr[i].musicNum + "首");
    		
    		$(".default").attr("id",playlistArr[i].listId);
    		continue;
    	}
    	
    	content += '<li class="no-selected" onClick="choiceMusicList(this)" id="';
    	content += playlistArr[i].listId + '">';
    	
    	content += '<div class="left-item">' +
    	              '<div class="left-item-image">' ;
    	// 如果该歌单中没有歌曲，则采用默认的头像
    	if(playlistArr[i].musicNum == 0){
    		content += '<img src="/MusicPlayer/image/default_list_Pic.jpg">';
    	}else{
    		// 
    		if(listType == 0){
    			picPath = "/MusicPlayer/" + playlistArr[i].listPic;
    			content += '<img src="' + picPath + '">';
    		}
    	}
    	
    	
    	                 // '<img src="/MusicPlayer/image/music_list.jpg">' + 
    	content +='</div>' + 
  					  '<div class="left-item-text">' + 
  						  '<div class="desc-item">' + playlistArr[i].listName +'</div>' + 
  						  '<div class="num-item">' + playlistArr[i].musicNum + 
		  						'<div class="mlist-opr" id="op-' + playlistArr[i].listId +'">' +
									'<i class="mlist-modify mlist-icon" onclick="popModifyWindows(this)"></i>' +
									'<i class="mlist-del mlist-icon" onclick="popDeleteWindows(this)"></i>' +
								'</div>' +
  						  '</div>' +
  					  '</div>' + 
  				   '</div>'
  			   '</li>';
    }
    
    $("#music-file-list").append(content);
   
    
    return curList;
}

// 填充右侧歌曲
function fillMusicListData(musiclistArr){
    var musiclist = "";
    
    // 清空原有数据
    $("#mymusiclist").empty();
    $("#n-nmusic").remove();
    
    if(musiclistArr.length == 0){
    	var tips = "";
    	
    	tips += "<div class='n-nmusic' id='n-nmusic'>" + 
					"<h3 class='nonm-top'>" +
						"<i class='icon-listen'></i>" +
						"<span class='non-listen-font'>暂无音乐！</span>" +
					"</h3>" +
					"<p class='nonm-bottom'>" +
						"点击" +
						"<i class='icon-save'></i>" +
						"即可将你喜欢的音乐收藏到“我的音乐”    马上去" +
						"<a class='link-font' href=''>发现音乐</a>" +
					"</p>" +
				"</div>";
    	
    	$("#table-warp").append(tips);
    	return;
    }
    
    for(var i=0;i<musiclistArr.length;i++){
    	if((i+1)%2==1)
    		musiclist +='<tr id="table-rows-' + musiclistArr[i].musicId + '" class="tbg">';
    	else
    		musiclist +='<tr id="table-rows-' + musiclistArr[i].musicId +'">';
    	musiclist +=      '<td class="w1">' + (i+1) + '</td>' + 
						  '<td class="w2">' + 
							  '<img src="/MusicPlayer/image/play.png"/>' + 
						  '</td>' + 
						  '<td class="w3" onclick="viewMusicDetail(this)">' + musiclistArr[i].musicName + '</td>' + 
						  '<td class="w4">' + musiclistArr[i].musicDuration + '</td>' + 
						  '<td class="w5">' + musiclistArr[i].musicAuthor + '</td>' + 
						  '<td class="w6">' + musiclistArr[i].musicAlbum + '</td>' + 
					'</tr>'
    }
    
    $("#mymusiclist").append(musiclist);
    
    // 为音乐列表添加点击事件
    $("#mymusiclist .w2").click(function(event){
    	// 将选中的音乐加到待播放列表
    	var id = $(this).parent().attr("id");
    	var musicId = id.substring(id.lastIndexOf("-")+1);
    	
    	var index = findMusicListIndex(musicId,musiclistArr); // 找到这首歌在当前歌单中的下标
    	var music = musiclistArr[index];
    	
    	addAndPlayMusic(music);
    });
}




// GET请求加载页面数据
$.ajax({
    type : "GET",
    async : true,         
    url : "/MusicPlayer/user/playlist",    
    dataType : "json",        //返回数据形式为json
    
    success : function(result) {
    	$("#mymusic").css("background","#000");
    	$("#find").css("background","rgb(36, 36, 36)");
    	playList(result);
    },
    error : function(errorMsg) {
        //请求失败时执行该函数
        alert("请求数据失败!");
    }
});

// 展开或关闭左侧歌单列表
function operMusicList(){
	var img = document.getElementById("file-img");
	var ul = document.getElementById("music-file-list");

	// 判断菜单时展开的还是关闭的
	if(ul.style.display == "none"){
		// 关闭，则打开；且显示ul
		img.src = "/MusicPlayer/image/icon_open.png";
		ul.style.display = "block";
	}else{
		img.src = "/MusicPlayer/image/icon_close.png";
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
	
	preSelected.classList.remove("selected");
	preSelected.classList.add("no-selected");
	// 选中: 灰色   之前的: 白色
	curSelected.classList.remove("no-selected");
	curSelected.classList.add("selected");
	var listLove = 0;
	
	var className = $("#"+musiclist.id).attr("class");
	if(className.indexOf("default") != -1){
		listLove = 2;
	}else if(className.indexOf("love") != -1){
		listLove = 1;
	}
	
	var index = findListIndex(musiclist.id);
	
	// 刷新右边的歌曲列表  --> 部分刷新
	refreshMusicList(playlistArr[index]);
}

function findListIndex(id){
	for(var i=0;i<playlistArr.length;i++){
		if(playlistArr[i].listId == id){
			return i;
		}
	}
	
	return -1;
}

// 刷新右侧歌曲列表
function refreshMusicList(curList){
	var picPath = "/MusicPlayer/image/default_list_BPic.jpg"
    	if(curList.listLove == 1){// /MusicPlayer/image/love_list_pic.png
    		picPath = "/MusicPlayer/image/love_list_pic.png"
    	}else if(curList.listLove == 2){
    		picPath = "/MusicPlayer/image/default_save_pic.png"
    	}else{
    		// 根据该歌单是否有歌曲，决定头像
    		if(curList.musicNum != 0){
    			// 有歌
    			picPath = "/MusicPlayer/" + curList.listPic;
    		}
    	}
	
	$("#musiclist-pic").attr("src", picPath);
	
	// TODO
	// 如果num == 0    不要去查数据库了
	if(curList.musicNum == 0){
		fillMusicListData([]);
    	fillMusicListInfo(curList);
    	return;
	}
	
	
	$.ajax({
	    type : "GET",
	    async : true,         
	    url : "/MusicPlayer/user/playlist?id=" + curList.listId,    
	    dataType : "json",        //返回数据形式为json
	    
	    success : function(result) {
	    	// 填充content-right中的数据
	        musiclistArr = result.musiclist;
	    	
	    	fillMusicListData(musiclistArr);
	    	fillMusicListInfo(curList);
	    },
	    error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});

}

// 弹出新建歌单窗口
function createNewMList(){
	
	// 加载弹窗
	$("#pop-content").load("/MusicPlayer/popWindows.html #createNewMusicListPop",function(){
		
	});
}

// 添加新歌单后的页面
function addNewMusicList(curList){
	var listUl = document.getElementById("music-file-list");
	var preSelected = document.getElementsByClassName("selected");
	preSelected = preSelected[0];
	var str = "";
	
	var element = document.createElement("li");
	
//	 '<li class="selected" onClick="choiceMusicList(this)" id="list">' + 
		str +=	'<div class="left-item">' + 
					'<div class="left-item-image">' +
						'<img src="/MusicPlayer/image/default_list_Pic.jpg">' +
					'</div>' +
					'<div class="left-item-text">' +
						'<div class="desc-item">' + curList.listName + '</div>' +
						'<div class="num-item">0首' +
							'<div class="mlist-opr" id="op-' + curList.listId +'">' +
								'<i class="mlist-modify mlist-icon" onclick="popModifyWindows(this)"></i>' +
								'<i class="mlist-del mlist-icon" onclick="popDeleteWindows(this)"></i>' +
							'</div>' +
						'</div>' +
					'</div>' +
				'</div>' ;
//			'</li>' ;
	
	// 默认选中新建的文件夹
	preSelected.classList.remove("selected");
    preSelected.classList.add("no-selected");
	
	// 把li标签加在最前面
	element.innerHTML = str;
	element.setAttribute("class","selected");
	element.setAttribute("id",curList.listId);
	element.setAttribute("onClick","choiceMusicList(this)");
	
	var node = document.getElementsByClassName("default")[0];
	node.after(element);
	// 取消弹窗
	cancelMList();
}


// 播放歌单音乐   只有当列表音乐不为空时，才能添加点击事件
//取消新建歌单窗口
function cancelMList(){
	// 清除弹窗信息，并关闭弹窗
	
	$("#pop-content").empty();
//	popBg.style.display = "none";
//	pop.style.display = "none";
//	
//	errorSpan.innerHTML = "";
//	error.style.display = "none";
//	
//	$("#listname").val("");
}

// 新建歌单
function createMList(){
	// 非空检查
	var listname = document.getElementById("listname");
	var error = document.getElementById("error");
	
	var errorMsg = ""
	var isSubmit = true;
	
	// 清除上次的错误信息
	var errorSpan = document.getElementById("error-msg");
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
		// AJAX提交数据
	    $.ajax({
	        type : "POST",
	        async : true,         
	        url : "/MusicPlayer/user/playlist",    
	        dataType : "json",        //返回数据形式为json
	        data:{
	        	  "listname":$("#listname").val()
                 },
	        success : function(result) {
	        	var tipMsg = ""
	        	if(result.isSuccess){
	        		
		        	var curPage = window.location.href;
		        	if(curPage.indexOf("playlist") != -1 ){
		        		// playlist页面
		        		// 加入playlistArr
		        		playlistArr.push(result.curList);
		        		
		        		// 左侧添加新的歌单
		        		addNewMusicList(result.curList);

		        		// 刷新右侧数据   
		        		refreshMusicList(result.curList);
		        		history.pushState(null,null,"/MusicPlayer/user/playlist?id=" + result.curList.listId);
		        	}
		        	
		        	// 提示信息
		        	tipMsg = "新增歌单成功";
	        	}else{
	        		tipMsg = "新增歌单失败";
	        	}
	        	
	        	popTips(result.isSuccess,tipMsg);
	        },
	        error : function(errorMsg) {
	            // 添加失败
	            alert("请求数据失败!");
	        }
	    });

	}else{
		errorSpan.innerHTML = errorMsg;
		error.style.display = "block";
	}
	
}



//页面加载时，根据URL将导航栏中颜色改变将导航栏的"发现音乐"改变背景颜色
$("#myMusic").css("background","#000");

// 播放音乐时，先初始化进度条，时长等信息

// 删除歌单
function deleteMList(id){
	$.ajax({
	    type : "DELETE",
	    async : true,         
	    url : "/MusicPlayer/user/playlist?id=" + id,    
	    dataType : "json",        //返回数据形式为json
	    success : function(result) {
	    	if(result.isSuccess){
	    		
	    		// 查看当前选中的元素是否是要删除的元素
	    		var selectedId = $(".selected:eq(0)").attr("id");
	    		// .attr("id");
	    		if(selectedId == id){
	    			
	    			// 刷新右侧列表: 展示相邻的后一个歌单
	    			var index = findListIndex(id);
	    			// 移除该歌单array.splice(start,delCount);
	    			playlistArr.splice(index,1)
	    			index = index > (playlistArr.length -1) ? 1 : index;
	    			refreshMusicList(playlistArr[index]);
	    			
	    			// 选中新歌单
	    			var curSelected = document.getElementById(playlistArr[index].listId);
	    			// 选中: 灰色   之前的: 白色
	    			curSelected.classList.remove("no-selected");
	    			curSelected.classList.add("selected");
	    			
	    			// 清除数据
		    		$("#"+id).remove();
	    		}
	    		
	    		// 关闭弹窗
	    		$("#pop-content").empty();
	    	}else{
	    		
	    	}
	    },
	    error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});
	//.attr("id");
}


function popDeleteWindows(curEle){
	// 不要触发父元素的点击事件
//	$(".f-thide a").click(function(event){
//		event.stopPropagation();
//	});
	
	var event = this.event;
	event.stopPropagation();
	
	var id = curEle.parentNode.id;
	id = id.substring(id.lastIndexOf("-")+1);
	
	
	// 显示弹窗
	// 加载弹窗
	$("#pop-content").load("/MusicPlayer/popWindows.html #deleteMusicListPop",function(){

		$("#delmlist").click(function(){
			deleteMList(id);
		});
	});
	
}

// 修改歌单名
function popModifyWindows(curEle){
	var event = this.event;
	event.stopPropagation();
	
	var id = curEle.parentNode.id;
	id = id.substring(id.lastIndexOf("-")+1);
	
	var index = findListIndex(id);
	var list = playlistArr[index]
	// 显示弹窗
	$("#pop-content").load("/MusicPlayer/popWindows.html #updateMusicListPop",function(){
		// 回显歌单名
		$("#listname").val(list.listName);
		
		$("#updateml").click(function(){
			// 合法性检查
//			if(){
//				
//			}
			var listName = $("#listname").val();
			var url =  "/MusicPlayer/user/playlist?id="+id+"&listName="+listName;
			
			// 保存
			$.ajax({
			    type : "PUT",  // 更新数据
			    async : true,         
			    url : url,  
			    dataType : "json",        //返回数据形式为json
			    success : function(result) {
			    	
			    	var tipMsg = "";
		        	if(result.isSuccess){
		        		// 提示: 
		        		tipMsg = "修改成功";
		        		// 更新右侧歌单名称
		        		$("#"+id + " .desc-item").empty();
		        		$("#"+id + " .desc-item").append(listName);
		        		
		        		
		        		// 更新数组
		        		var index = findListIndex(id);
		        		playlistArr[index].listName = listName;
		        	}else{
		        		// 提示：
		        		tipMsg = "修改失败";
		        	}
			    	
			    	popTips(result.isSuccess,tipMsg);
			    },
			    error : function(errorMsg) {
			        alert("请求数据失败!");
			    }
			});
		});
	});
	
}

function viewMusicDetail(e){
	var id = e.parentNode.id;
	// 截取table-rows-1
	var musicId = id.substring(id.lastIndexOf("-")+1);
	
	// 加载详情页面
	// 移除原有的信息
	$(".content").empty();
	
	// 移除和添加css文件信息css/footer.css"/>
	removejscssfile("setting.css","css");
	removejscssfile("playlist.css","css");
	addCss('/MusicPlayer/css/display.css');

	// 加载content内容
	$(".content").load("/MusicPlayer/display.jsp #container",function(){
		 // 修改url
		 history.pushState(null,null,"/MusicPlayer/display?musicId="+musicId);
		 // 定义变量
		 
		 
		 $.getScript("/MusicPlayer/js/display.js",function(){
			 // 通过musicId得到music
			 var index = findMusicListIndex(musicId, musiclistArr);
			 var music = musiclistArr[index];
			 initDisplayPage(music);
		 });
	});
        
        
}
