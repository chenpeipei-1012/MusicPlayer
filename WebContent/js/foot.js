$(".foot").load("/MusicPlayer/foot.html",function(){
	// 把audio当成全局变量
	var rem = [];

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

	// 播放/暂停
	$(".play").click(function(){
		rem.audio = $("#audio")[0];
		rem.unit = 100 / rem.audio.duration;
		
		audio.addEventListener("timeupdate",updateProcess);	
		// 初始化音乐，包括音乐总时长等
		initMusic();
		
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




	function initMusic(){
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
	}

	function processTime(time){
		// 总时长：duration得到的单位是s，需转换为分钟:秒
		var minues = parseInt(time / 60);
		var second = parseInt(time - 60 * minues);
		var total = (minues < 10 ? ("0" + minues) : minues) + ":" +(second < 10 ? ("0" + second) : second);
		
		return total;
	}

	// 定时器实现当前播放时长及进度条的控
});

