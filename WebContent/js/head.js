//$(".head").load("/MusicPlayer/header.html",function(){
	// 绑定事件: 鼠标放置改变导航栏颜色
	$(".nav-bg").mouseover(function(){
		this.style.backgroundColor = "#000";
	});

	$(".nav-bg").mouseout(function(){
		this.style.backgroundColor = "#242424";
	});
	
	// 页面加载时，根据URL将导航栏中颜色改变将导航栏的"发现音乐"改变背景颜色
	// 得到URL
	curTabShow();
	function curTabShow(){
		// alert(window.location.href);
	}
	
	
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
	
	// 发现音乐
	$("#find").click(function(){
		// 移除原有的信息
    	$(".content").empty();
    	
    	// 移除和添加css文件信息css/footer.css"/>
    	removejscssfile("setting.css","css");
    	removejscssfile("playlist.css","css");
    	addCss('/MusicPlayer/css/index.css');
    	addCss('/MusicPlayer/css/footer.css');

    	// 加载content内容
    	$(".content").load("/MusicPlayer/index.jsp #container",function(){
    		 // 修改url
    		 history.pushState(null,null,"/MusicPlayer/index.jsp");
    		 $.getScript("/MusicPlayer/js/index.js");
    	});
	});
	
	$("#myMusic").click(function(){
		
		$.ajax({
		    type : "GET",
		    async : true,         
		    url : "/MusicPlayer/user/playlist",    
		    dataType : "json",        //返回数据形式为json
		    success : function(result){
		    	// 是否登录
		    	if(result.isLogin == "no"){
		    		// 页面跳转
		    		window.location.replace("/MusicPlayer/login.html");
		    		return;
		    	}
		    	
		    	// 移除原有的信息
		    	$(".content").empty();
		    	
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
	
	
	$(".login").click(function(){
		// 打开新窗口
		window.open("/MusicPlayer/login.html?which=Login"); 
		// 弹窗
		// 加载弹窗
//		$("#pop-content").load("/MusicPlayer/popWindows.html #loginPop",function(){
//			
//		});
		
	});
	