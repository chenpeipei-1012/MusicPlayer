//$(".head").load("/MusicPlayer/header.html",function(){
	// 绑定事件: 鼠标放置改变导航栏颜色
	$(".nav-bg").mouseover(function(){
		this.style.backgroundColor = "#000";
	});

	$(".nav-bg").mouseout(function(){
		this.style.backgroundColor = "#242424";
	});
	
	// 页面加载时，根据URL将导航栏中颜色改变将导航栏的"发现音乐"改变背景颜色
	$("#find").css("background","#000");
	
	// 动态添加css文件
	function addCss(filename){
		var creatHead = $('head');
		creatHead.append('<link rel="stylesheet" href="'+filename+'">');
	}
	
	// 动态添加js文件
	function addJS(filename){
		var creatBody = $('body');
		var str = '<script src="' + filename + '"></script>';
		creatBody.append(str);
	}
	
	$.ajaxSetup({
		cache:true
	});
	
	
	$("#myMusic").click(function(){
		$.ajax({
		    type : "POST",
		    async : true,         
		    url : "/MusicPlayer/playlist",    
		    dataType : "json",        //返回数据形式为json
		    success : function(result){
		    	// 移除原有的信息
		    	$(".content").empty();
		    	
		    	// 把整个content都删除
		    	// $(".content").remove();
		    	
		    	removejscssfile("setting.css","css");
		    	addCss('/MusicPlayer/css/pop.css');
		    	addCss('/MusicPlayer/css/playlist.css');
		    	$(".content").load("/MusicPlayer/user/playlist.jsp #container",function(){
		    		 // 修改url
		    		 history.pushState(null,null,"/MusicPlayer/user/playlist.jsp");
		    		 $.getScript("/MusicPlayer/js/playlist.js");
		    	});
		    },
		    error : function(errorMsg) {
		        //请求失败时执行该函数
		        alert("请求数据失败!");
		    }
		});
		
	});
	
	
	// 移除已经加载的JS和CSS文件
	function removejscssfile(filename, filetype){
		var targetelement = (filetype=="js")? "script" : (filetype=="css")? "link" : "none"
		var targetattr = (filetype=="js")? "src" : (filetype=="css")? "href" : "none"
		var allsuspects = document.getElementsByTagName(targetelement)
		
		for (var i=allsuspects.length; i>=0; i--){
			if (allsuspects[i] && allsuspects[i].getAttribute(targetattr)!=null && allsuspects[i].getAttribute(targetattr).indexOf(filename)!=-1)
			   allsuspects[i].parentNode.removeChild(allsuspects[i])
		}
	}
	
//});