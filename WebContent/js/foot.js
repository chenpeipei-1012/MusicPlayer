// 把audio当成全局变量
var rem = [];
// 歌曲列表
var musicList = [];
// 当前播放音乐的下标
var curMusic;

//$(".foot").load("/MusicPlayer/foot.html",function(){

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

	//onmousedown事件+onmousemove
	$(".volbar-bg").mousedown(function(event){
		
	});

	$(".volbar-bg").mousemove(function(event){
		// 当前音量 + 偏移量所产生的音量
		var curY = event.clientY;
		var preV = $(".change-vol").offset();
		var X = $(".change-vol").position().top;

		if(curY < preV){
			// 下移，减小音量
			rem.audio.volume -= event.offsetY/100;
		}else{
			// 上移，增加音量
			rem.audio.volume += event.offsetY/100;
		}
		
		changeVolumn(rem.audio.volume);
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
		if(musicList.length == 0){
			return;
		}
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
	
	
	function fillToBePlayList(){
		var content = "";
		
		if(musicList.length == 0){
			if($("#nocnt").length > 0){
				return;
			}
			
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
						'<div class="list-dur">03:47</div>' +
						'<div class="list-share">' +
							'<span></span>' +
						'</div>' +
					'</li>' ;
		}
		
		$("#tobeplay").empty();
		$("#tobeplay").append(content);
		
		// 当前歌曲标示
		var curLi = $("#tobeplay_"+musicList[curMusic].musicId);
		curLi.removeClass("list-noselected");
		curLi.addClass("list-selected");
		
		$("#tobeplay_"+musicList[curMusic].musicId + " .list-mplay").append('<div class="playicn"></div>')
	}
	
function fillMusicLyric(){
	if(musicList.length == 0){
		return;
	}
	
	var lyricPath = musicList[curMusic].musicLyricPath;

	// 去服务器读数据
	$.ajax({
	    type : "GET",
	    async : true,         
	    url : "/MusicPlayer/" + lyricPath, 
	    dataType : "text",        //返回数据形式为文本
	    success : function(result) {
	    	var content = "";
	    	var lyricArr = result.split(/[(\r\n)\r\n]+/);
	    	
	    	for(var i=0;i<lyricArr.length;i++){
	    		var index = lyricArr[i].indexOf("]");
	    		content += '<p class="p-item">' + lyricArr[i].substring(index+1) +'</p>';
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

$(".close-list-img").click(function(){
	$(".g_playlist").hide();
});