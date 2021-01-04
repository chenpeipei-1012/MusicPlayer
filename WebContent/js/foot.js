// 把audio当成全局变量
var rem = [];
// 歌曲列表
var musicList = [];
// 当前播放音乐的下标
var curMusic;

// 歌词数组
var lyricArray = new Array()
// 歌词行下标
var lineNo = 0;

// audio的初始化工作
rem.audio = $("#audio")[0];
	
// 给锁添加点击事件
$(".lock").click(function(){
	// 点击更换背景图片。先判断
	if($(".lock").hasClass("lockbar")){
		$(".lock").removeClass("lockbar")
		$(".lock").addClass("unlockbar");
		
		// 绑定播放栏下移隐藏和上移展示的动画效果
		$(".m-playbar").mouseover(function(){
			// 实现动态效果: 仅运动一次， !$("#box").is(':animated')
			$(".m-playbar").stop().animate({top:"0"},100);
		});
		
		$(".m-playbar").mouseout(function(){
			$(".m-playbar").stop().animate({top:"46px"},1000);
		});
	}else{
		$(".lock").removeClass("unlockbar")
		$(".lock").addClass("lockbar");
		
		// 移除动画效果，即事件解除绑定
		$(".m-playbar").unbind();
	}
});

	// 给按钮绑定事件，悬浮颜色加深
	$(".pre").mouseover(function(){
		// 只需要移X坐标，Y坐标代表图标
		$(".pre").css({"background-position-x":"-30px"});
	});
	$(".pre").mouseout(function(){
		// 暂停移走
		$(".pre").css({"background-position-x":"0"});
	});
	
	$(".next").mouseover(function(){
		// 只需要移X坐标，Y坐标代表图标
		$(".next").css({"background-position-x":"-110px"});
	});
	$(".next").mouseout(function(){
		// 暂停移走
		$(".next").css({"background-position-x":"-80px"});
	});

	$(".play").mouseover(function(){
		// 只需要移X坐标，Y坐标代表图标
		$(".play").css({"background-position-x":"-40px"});
	});

	$(".play").mouseout(function(){
		// 暂停移走
		$(".play").css({"background-position-x":"0"});
	});

	// 音量
	$(".volumn").mouseover(function(){
		$(".volumn").css({"background-position-x":"-31px"});
	});
	$(".volumn").mouseout(function(){
		$(".volumn").css({"background-position-x":"-2px"});
	});

	$(".volumn").click(function(){
		if($(".m-vol").is(':hidden'))
		   $(".m-vol").show();
		else
		   $(".m-vol").hide();
	});

	// TODO
	// 音量调节
	var flag = false;
	//onmousedown事件+onmousemove
	$(".volbar-bg").mousedown(function(e){
		flag = true;
        // 计算出鼠标落下点与月亮边界的距离
		var curY =  e.clientY;
		var preY =$('#cur-vol').offset().top;
		var x = 100 - e.offsetY;
		
		//rem.audio.volume = (x - 30)/70;
		
		//changeVolumn(rem.audio.volume);
	});

	$(".volbar-bg").mousemove(function(event){
		// 当前音量 + 偏移量所产生的音量
		var curY = event.clientY;
		var preV = $(".change-vol").offset();
		var X = $(".change-vol").position().top;

	});

	function changeVolumn(curVolumn){
		var volBtn = curVolumn * 100 - 22 + "%";
		var volProcess = curVolumn * 100 - 18 + "%";
		// 按钮位置
		$(".change-vol").css({"bottom":volBtn});
		// 进度条位置
		$(".cur-vol").css({"height":volProcess});
	}

	// 给play添加点击事件
	$(".play").click(function(){
		//initMusic();
//		if(musicList.length == 0){
//			return;
//		}
		// 是播放还是暂停
		if($(".play").hasClass("noplay-btn")){
			// 播放
			$(".play").removeClass("noplay-btn");
			$(".play").addClass("play-btn");
			
			audio.play();
		}else{
			// 暂停
			$(".play").removeClass("play-btn");
			$(".play").addClass("noplay-btn");
			
			audio.pause();
		}
	});
	
	
	// 播放/暂停
	
	
	// 播放音乐时，先初始化进度条，时长等信息
	function initMusic(){
		rem.unit = 100 / rem.audio.duration;
		
		// 添加事件
		audio.addEventListener("timeupdate",updateProcess);	
		audio.addEventListener('ended',endProcess);
		// TODO
		lineNo = 0;
		
		// 总时长
		var totaltime = processTime(rem.audio.duration);
		$(".totaltime").empty();
		$(".totaltime").append(totaltime);
		
		// 初始音量：1 
		var firstVolBtn = rem.audio.volume * 100 - 22 + "%";
		var firstVolPro = rem.audio.volume * 100 - 18 + "%";
		
		// 按钮位置
		$(".change-vol").css({"bottom":firstVolBtn});
		// 进度条位置
		$(".cur-vol").css({"height":firstVolPro});
		
		$(".play").removeClass("noplay-btn");
		$(".play").addClass("play-btn");
		
		// 歌曲图片
		var musicPicPath = musicList[curMusic].musicPic;
		$('#music-pic').attr('src',"/MusicPlayer/" + musicPicPath);
		//$('#music-pic').css({"background-image":"url(/MusicPlayer/" + musicPicPath + ")"});
		
		// 初始化歌词: 获取并填充歌词
		getLyric();
		
	}
	
	
	function getLyric(){
		// 修改当前歌曲名
		$(".music-name").empty();
    	$(".music-name").append(musicList[curMusic].musicName);
    	
		var lyricPath = musicList[curMusic].musicLyricPath;
		// 去服务器读数据
		$.ajax({
		    type : "GET",
		    async : true,         
		    url : "/MusicPlayer/" + lyricPath, 
		    dataType : "text",        //返回数据形式为文本
		    success : function(result) {
		    	// 清空之前的lyricArray
		    	lyricArray.splice(0,lyricArray.length);
		    	
		    	// 重置歌词行数
		    	lineNo = 0;
		    	
		    	var content = "";
		    	var lyricArr = result.split(/[\r\n]+/);
		    	var time = 0;
		    	var word = "";
		    	var timeArr;
		    	
		    	for(var i=0;i<lyricArr.length;i++){
		    		var index = lyricArr[i].indexOf("]");
		    		
		    		// [00:00.30]  分秒毫    
		    		timeArr = lyricArr[i].substring(1,index).split(":");
		    		// 将字符串转为小数
		    		time = parseFloat(timeArr[0] * 60) + parseFloat(timeArr[1]);
		    		word = lyricArr[i].substring(index+1);
		    		lyricArray.push({"time":time,"word":word});
		    		
		    		// 填充歌词
		    		content += '<p class="p-item">' + word +'</p>';
		    	}
		    	
		    	$("#lyric").empty();
		    	$("#lyric").append(content);
		    },
		    error : function(errorMsg) {
		        //请求失败时执行该函数
		        alert("请求数据失败!");
		    }
		});
	}
	
	// 前一首播放完后播放列表中的下一首
	function endProcess(){
		// 结束后自动播放下一首curMusic
		if(curMusic  < musicList.length - 1){
			curMusic++;
		}else{
			curMusic = 0;
		}
		changeMusic();
	}
	
	function changeMusic(){
		var path = '/MusicPlayer/' + musicList[curMusic].musicPath;
		$('#audio').attr('src',path);
		$('#music-pic').css({"background-image":"url(" + path + ")"});
		audio.play();
		
		// 歌词和当前播放刷新
		getLyric();
		
		// 待播放列表刷新
		// TODO
		curToBeMusicShow();
	}

	/*
	 * 处理跟时间有关系的信息，每秒执行一次
	 * 
	 */
	var percentage;
	function updateProcess(){
		// 1：当前时长
		var curTime = processTime(rem.audio.currentTime);

		$(".curtime").empty();
		$(".curtime").append(curTime);
		
		// 2：进度条     cur的宽度设置    百分比布局，不需要知道具体
		// 每秒钟要移动多少百分比     百分比/s
		percentage = rem.audio.currentTime * rem.unit + "%";
		
		$(".cur").css({"width":percentage});
		
		$(".move-btn").css({"left":percentage});
		
		if(lineNo + 1 == lyricArray.length){
			// 如果是最后一句歌词了，只需要满足第一个条件
			if (rem.audio.currentTime >= parseFloat(lyricArray[lineNo].time)){
				processLyric(lineNo);
			}

			return;
		}
		
		// 当前歌词满足条件 已开始播放   ，下一句歌词未开始播放
		var time = rem.audio.currentTime;
		if (time >= parseFloat(lyricArray[lineNo].time)&&
				time <= parseFloat(lyricArray[lineNo + 1].time)) {
			processLyric(lineNo);
		    lineNo++;
		}
	}




	function processTime(time){
		// 总时长：duration得到的单位是s，需转换为分钟:秒
		var minues = parseInt(time / 60);
		var second = parseInt(time - 60 * minues);
		var total = (minues < 10 ? ("0" + minues) : minues) + ":" +(second < 10 ? ("0" + second) : second);
		
		return total;
	}

	// 定时器实现当前播放时长及进度条的控
	
	// 上一首
	$(".pre").click(function(){
		if(musicList.length == 0){
			return;
		}
		
		// 如果是第一首，则播放最后一首
		if(curMusic  != 0){
			curMusic--;
		}else{
			curMusic = musicList.length-1;
		}
		
		changeMusic();
	});
	
	// 下一首
	$(".next").click(function(){
		if(musicList.length == 0){
			return;
		}
		
		// 如果已经是最后一首了，则播放第一首
		if(curMusic  < musicList.length - 1){
			curMusic++;
		}else{
			curMusic = 0;
		}
		
		changeMusic();
	});

	// 展示待播放列表
	$(".plist").click(function(){
		if($(".g_playlist").is(':hidden')){
			// 如果隐藏，则显示
			
			fillMusicLyric();
			// 先加载歌曲列表和歌词数据
			fillToBePlayList();
			$(".g_playlist").show();
		}else{
			$(".g_playlist").hide();
		}
	});
	
	function nonToBeListShow(){
		var nonMusicContent = "";
		nonMusicContent += '<div class="nocnt" id="nocnt">' +
								'<i class="nocnt-icon"></i>' +
								 '你还没有添加任何歌曲' +
								'<br/>' +
								'去首页' +
								'<a href="">发现音乐</a>' +
								'，或在' +
								'<a href="">我的音乐</a>' +
								'收听自己收藏的歌单。' +
							'</div>';
		
		$("#tobeplay").empty();
		$(".listbd-left").append(nonMusicContent);
	}
	
	
	function fillToBePlayList(){
		var content = "";
		
		if(musicList.length == 0){
			if($("#nocnt").length > 0){
				return;
			}
			
			nonToBeListShow();
			
			return;
		}
		
		// 如果有数据
		$("#nocnt").remove();
		
		$(".list-num").empty();
		$(".list-num").append(musicList.length);
		
		$(".music-name").empty();
		$(".music-name").append(musicList[curMusic].musicName);
		
		for(var i=0;i<musicList.length;i++){
		content += '<li class="list-item list-noselected" id="tobeplay_' + musicList[i].musicId + '">' +
						'<div class="list-mplay">' +
						'</div>' +
						'<div class="list-mname"> ' + musicList[i].musicName + '</div>' +
						'<div class="list-opr">' +
							'<div class="icons">' +
								'<i class="icon-save"></i>' +
								'<i class="icon-download"></i>' +
								'<i class="icon-delete"></i>' +
							'</div>' +
						'</div>' +
						'<div class="list-mauthor-name">' + musicList[i].musicAuthor + '</div>' +
						'<div class="list-dur">' + musicList[i].musicDuration + '</div>' +
						'<div class="list-share">' +
							'<span></span>' +
						'</div>' +
					'</li>' ;
		}
		
		$("#tobeplay").empty();
		$("#tobeplay").append(content);
		
		// 当前歌曲标示
//		var curLi = $("#tobeplay_"+musicList[curMusic].musicId);
//		curLi.removeClass("list-noselected");
//		curLi.addClass("list-selected");
//		
//		$("#tobeplay_"+musicList[curMusic].musicId + " .list-mplay").append('<div class="playicn"></div>');
		
		curToBeMusicShow();
		
		
		// 添加点击事件
		$(".icons .icon-save").click(function(){
			// 弹窗
			// TODO_
			var e = this.parentNode.parentNode.parentNode;
			var musicId = e.id.substring(e.id.indexOf("_")+1);
			var choiceMusicIndex = findMusicListIndex(musicId,musicList);
			var musicId = musicList[choiceMusicIndex].musicId;
			queryAndFillSavePop(musicId);
		});
		$(".icons .icon-download").click(function(){
			var e = this.parentNode.parentNode.parentNode;
			var musicId = e.id.substring(e.id.indexOf("_")+1);
			var choiceMusicIndex = findMusicListIndex(musicId,musicList);
			
		    // 下载歌曲
			var url = "/MusicPlayer/" +  musicList[choiceMusicIndex].musicPath + "?musicId=" + musicId;
			
			// 文件下载不能用AJAX
			var fileName = musicList[choiceMusicIndex].musicPath;
	        var form = $("<form></form>").attr("action", "/MusicPlayer/file").attr("method", "post");
	        form.append($("<input></input>").attr("type", "hidden").attr("name", "fileName").attr("value", fileName));
	        form.append($("<input></input>").attr("type", "hidden").attr("name", "musicId").attr("value", musicId));
	        form.appendTo('body').submit().remove();
	        
	        
		});
		$(".icons .icon-delete").click(function(){
			var e = this.parentNode.parentNode.parentNode;
			var musicId = e.id.substring(e.id.indexOf("_")+1);
			
			// 清除
			// 得到对应的下标
			var index = findMusicListIndex(musicId,musicList);
			musicList.splice(index, 1); 
			
			// 从ul中删除
			$("#"+e.id).remove();
			// TODOs
			
			// 如果删除的是当前歌曲
			if(curMusic == index){
				if(musicList.length == 0){
					// 删除之后没有歌曲了
					nonToBeListShow();
					return;
				}
				
				// 如果是最后一首，则选中上第一首，否则选中下一首
				if(index > musicList.length - 1){
					index = 0;
				}
				
				curMusic = index;
				changeMusic();
			}
		});
	}
	
	function curToBeMusicShow(){
		// 清除原来的
		$(".list-selected").addClass("list-noselected");
		$(".list-selected").removeClass("list-selected");
		$(".playicn").remove();
		
		// 当前歌曲标示
		var id = "#tobeplay_"+musicList[curMusic].musicId;
		var curLi = $(id);
		curLi.removeClass("list-noselected");
		curLi.addClass("list-selected");
		
		$(id + " .list-mplay").append('<div class="playicn"></div>');
	}
	
	// 找到musiclist.id对应的下标
	function findMusicListIndex(id,arr){
		for(var i=0;i<arr.length;i++){
			if(arr[i].musicId == id){
				return i;
			}
		}
		
		return -1;
	}
	
function fillMusicLyric(){
	if(musicList.length == 0){
		return;
	}
	
	
}

//歌词的高亮显示
function processLyric(curline){
	// find()   搜寻选中元素中的子元素
	// 获得当前元素
	var curEle = $("#lyric").find("p").get(curline);
	
	// 移除之前高亮的元素
	$(".lyric-seleted").removeClass("lyric-seleted");
	
	// 高亮显示当前元素
	$(curEle).addClass("lyric-seleted");
	
	// 歌词滚动: scrollTop的设置
	var _eul = $(".listbd-right")[0];
	
	var _scrollTop = getScollTop(curEle,_eul);

	// 设置scrollTop
	_eul.scrollTop = _scrollTop;
}

function getScollTop(_ep,_eul){
	var fraction = 3/5;
	
	var _scrollTop;
	if (_ep.offsetTop < _eul.clientHeight*fraction){
		_scrollTop = 0;
	} else if (_ep.offsetTop > (_eul.scrollHeight - _eul.clientHeight*(1-fraction))){
		_scrollTop = _eul.scrollHeight - _eul.clientHeight;
	} else {
		_scrollTop = _ep.offsetTop - _eul.clientHeight*fraction;
	}
	
	return _scrollTop;
}

$(".close-list-img").click(function(){
	$(".g_playlist").hide();
});

function fillPopContent(playlist){
	var content = "";
	content +=  '<div id="saveToMusicList">' +
					'<div id="pop-bg" class="pop-bg"></div>' +
					'<div id="pop" class="pop p-save">' +
						'<div class="pop-head">' +
							'<span>添加到歌单</span>' +
							'<img src="/MusicPlayer/image/close_pop.png" onClick="cancelMList()" />' +
						'</div>' +
						'<div class="pop-content pc-save">' +
							'<div class="new-list">' +
								'<i class="new-limg"></i>新歌单' +
							'</div>' +
							'<div class="my-mlist">' +
								'<ul id="my-mlist-ul">';
	
	var listPicPath = "";
	for(var i=0;i<playlist.length;i++){
		if(playlist[i].musicNum == 0){
			listPicPath = "/MusicPlayer/image/default_list_Pic.jpg";
    	}else{
    		listPicPath = "/MusicPlayer/" + playlist[i].listPic;
    	}
		
		if(playlist[i].listLove == 1){
			listPicPath = "/MusicPlayer/image/love_list_pic.png";
		}else if(playlist[i].listLove == 2){
			listPicPath = "/MusicPlayer/image/default_save_pic.png";
		}
		content += 					'<li class="xtag" id="pop-' + playlist[i].listId + '">' +
										'<div class="s-left">' +
											'<img src="' + listPicPath + '"/>' +
										'</div>' +
										'<div class="s-right">' +
											'<p class="sr-p1">' + playlist[i].listName + '</p>' +
											'<p class="sr-p2">' + playlist[i].musicNum + '首</p>' +
										'</div>' +
									'</li>';
	}
	
	content +=						'</ul>' +
								'</div>' +
							'</div>' +
							'</div>' +
							'</div>';
	
	// 当前播放栏有歌，弹出收藏窗口
	$("#pop-content").empty();
	$("#pop-content").append(content);
}


// 在底部播放栏收藏
$("#like").click(function(){
	// TODO
	// 没有登录，则提示登录

	if(curMusic > -1 && curMusic < musicList.length){
		queryAndFillSavePop(curMusic);
	}
});

// 请求歌单列表
function queryAndFillSavePop(musicId){
	var url = "/MusicPlayer/user/playlist?date=" + new Date().getTime();
	// 请求歌单
	$.ajax({
	    type : "GET",
	    async : true,         
	    url : url,  
	    dataType : "json",        //返回数据形式为json
	    
	    success : function(result) {
	    	fillPopContent(result.playlist);
	    	
	    	// 添加点击事件
	    	$(".xtag").click(function(){
	    		// 得到选中歌单id
	    		var listId = this.id.substring(4);
	    		// 得到当前歌曲ID
	    		// TODO
	    		// var musicId = musicList[choiceMusicIndex].musicId;
	    		
	    		// 请求添加
	    		addMusicToList(listId,musicId);
	    	});
	    	
	    	// 
	    	$(".new-list").click(function(){
	    		// 关闭弹窗
	        	$("#pop-content").empty();
	        	
	        	// 加载新增弹窗
	    		$("#pop-content").load("/MusicPlayer/popWindows.html #createNewMusicListPop",function(){
	    			
	    		});
	    		
	    	});
	    	
	    	// 悬浮事件
	    	$(".xtag").mouseover(function(){
	    		// #e6e6e6
	    		this.style.backgroundColor = "#f7f7f7";
	    	});
	    	$(".xtag").mouseout(function(){
	    		this.style.backgroundColor = "white";
	    	});
	    },
	    error : function(errorMsg) {
	        alert("请求数据失败!");
	    }
	});
}

// 请求添加新歌曲到歌单中
function addMusicToList(listId,musicId){
	var url = "/MusicPlayer/user/saveMusic?listId="+listId+"&musicId="+musicId+"&date=" + +new Date().getTime();

	$.ajax({
        type : "GET",
        async : true,         
        url : url,    
        dataType : "json",
        success : function(result) {
        	var tipMsg = "";
        	if(result.isSuccess){
        		// 提示: 
        		tipMsg = "收藏成功";
        	}else{
        		// 提示：
        		tipMsg = result.errMsg;
        	}
        	
        	popTips(result.isSuccess,tipMsg);
        
        },error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});
}


function addAndPlayMusic(music){
	var cruIndex = findMusicListIndex(music.musicId,musicList);
	
	// 查看待播放列表是否已经存在该歌曲
	if(cruIndex == -1){
		// 不存在，则往里面放
		cruIndex = musicList.push(music);
		cruIndex--;
	}
	
	// 播放选中的歌曲
	curMusic = cruIndex;
	playMusic();
}

function addMusicToBeList(music){
	var cruIndex = findMusicListIndex(music.musicId,musicList);
	
	// 查看待播放列表是否已经存在该歌曲
	if(cruIndex == -1){
		// 不存在，则往里面放
		cruIndex = musicList.push(music);
		cruIndex--;
	}
}

function playMusic(){
	// 改变带播放列表的歌曲数目
	$("#curlistnum").empty();
	$("#curlistnum").append(musicList.length);

	// 选中列表中的第一首音乐播放$('#fry_audio').attr('src',nameAttrValue);
	var path = '/MusicPlayer/' + musicList[curMusic].musicPath;
	$('#audio').attr('src',path);
	
	// 改变路径后，需重新加载，重新赋值
	$('#audio').load();
	
	// 等到资源加载完毕后再初始化
	$("#audio")[0].oncanplay = function () {
		rem.audio = $("#audio")[0];
		initMusic();
		rem.audio.play();
	}
}