initDisplayPage();

// 初始化歌曲详情页面
function initDisplayPage(){
	
}
// 根据URL中musicId加载音乐信息


// 加载歌曲信息
function loadMusicInfo(music){
	// 加载歌曲信息
	// 歌曲名
	$("#mname").empty();
	$("#mname").append(music.musicName);
	
	// 歌手名
	$("#mauthor").empty();
	$("#mauthor").append(music.musicAuthor);
	
	// 专辑名
	$("#malbum").empty();
	$("#malbum").append(music.musicAlbum);
	
	// 图片
	var photoPath = "/MusicPlayer/" + music.musicPic;
	$("#songphoto").attr("src",photoPath);
	
	var aa = window.location.href;
	// var url = "/MusicPlayer/user/display?musicId="+musicId;
	
	// 歌曲ID
	$(".song").attr("id",music.musicId);
	
	// 请求歌词信息
	// 歌词路径
	var lyricPath = music.musicLyricPath;
	$.ajax({
        type : "GET",
        async : true,
        url : "/MusicPlayer/" + lyricPath,
        dataType : "text",
        success : function(result) {
        	// 填充歌词
        	var lyricArr = result.split(/[\r\n]+/);
        	
        	var content = "";
	    	var word = "";
	    	var moreContent = "";
	    	
	    	for(var i=0;i<lyricArr.length;i++){
	    		var index = lyricArr[i].indexOf("]");
	    		word = lyricArr[i].substring(index+1);
	    		
	    		// 填充歌词
	    		if(i >= 15)
	    			moreContent += '<p class="lyric-item">' + word +'</p>';
	    		else
	    			content += '<p class="lyric-item">' + word +'</p>';
	    	}
	    	
	    	// 部分歌词
	    	$("#lyric-part").empty();
	    	$("#lyric-part").append(content);
	    	// 更多歌词
	    	$("#flag_more").empty();
	    	$("#flag_more").append(moreContent);

        },error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }

	});
}


function loadMusiclist (RCMusic){
	var aa = window.location.href;
	var url = "/MusicPlayer/user/display?musicId="+music.music_id;
	alert(aa);
	$.ajax({
		type : "GET",
		async : true,
		url : url,
		dataType : "json",
		success : function(result) {

		

		},error : function(errorMsg) {
			//请求失败时执行该函数
			alert("请求数据失败!");
		}

	});
}
function loadMusiComment (mclist){
	var aa = window.location.href;
	var url = "/MusicPlayer/user/display?musicId="+music.music_id;
	alert(aa);
	$.ajax({
		type : "GET",
		async : true,
		url : url,
		dataType : "json",
		success : function(result) {
			//更新评论区
			

		},error : function(errorMsg) {
			//请求失败时执行该函数
			alert("请求数据失败!");
		}

	});
}


//展开按钮
$(".flag_ctrl").click(function(){
	if($("#flag_more").is(':hidden')){
		// 展开，并更改提示
		$("#flag_more").show();
		$("#crl-tip").empty();
		$("#crl-tip").append("收起");
	}else{
		// 折叠
		$("#flag_more").hide();
		$("#crl-tip").empty();
		$("#crl-tip").append("展开");
	}
});


// 添加评论
$("#addComment").click(function(){
	// TODO
	// 非空验证
	
	// 请求后台添加
	var comment = $("#comment").val();
	var musicId = $(".song")[0].id;
	$.ajax({
        type : "POST",
        async : true,         
        url : "/MusicPlayer/user/display",    
        dataType : "json",        //返回数据形式为json
        data:{
        	  "comment":comment,
        	  "musicId":musicId
             },
        success : function(result) {
        	var tipMsg = "";
        	if(result.isSuccess){
        		// 提示: 
        		tipMsg = "评论成功";
        		
        		// 清空comment
        		$("#comment").empty();
        		
        		// 刷新下方评论
        		// TODO
        	}else{
        		// 提示：
        		tipMsg = "评论失败";
        	}
        	
        	popTips(result.isSuccess,tipMsg);
        },
        error : function(errorMsg) {
	            // 添加失败
	            alert("请求数据失败!");
	        }
	    });
});