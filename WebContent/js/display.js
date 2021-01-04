var music = null;
var similarMusics = [];

// 初始化歌曲详情页面
function initDisplayPage(curMusic){
	music = curMusic;
	loadMusicInfo(curMusic);
	loadMusicComment(curMusic.musicId,1);
	loadSimilarMusic(curMusic.musicTypeId);
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

// 加载歌曲评论
function loadMusicComment(musicId,curPage){
	var url = "/MusicPlayer/comment?musicId="+musicId+"&curPage="+curPage;
	$.ajax({
		type : "GET",
		async : true,
		url : url,
		dataType : "json",
		success : function(result) {
			// 更新评论区
			var content = "";
			var mcList = result.mcList;
			var user = null;
			var musicComment = null;
			for(var i=0;i<mcList.length;i++){
				musicComment = mcList[i][0];
				user = mcList[i][1];
				content += '<div id="' + musicComment.mcId + '" class="itm">' +
								'<div >' +
									'<a  id="u1" href="javascript:void(0);">' +
									'<img ass="touxiang" src="' + user.userPic + '" style="width:50px;height:50px;"></a>' +
								'</div >' +				
								'<div class="comid fix_texture">' +
									'<a  id="u3nm" class="fix_texture u-name" >' + user.userNick + '</a> ：' +
									'<a id="u3cm"  class="fix_texture">' + musicComment.comment + '</a>' +
								'</div>' +			
							'</div>';
			}
			
			$("#comment-wrap").empty();
			$("#comment-wrap").append(content);
		},error : function(errorMsg) {
			// 请求失败时执行该函数
			alert("请求数据失败!");
		}

	});
}

// 加载推荐的相似歌曲
function loadSimilarMusic(musicTypeId){
	var url = "/MusicPlayer/recommend?musicTypeId="+musicTypeId;
	$.ajax({
		type : "GET",
		async : true,
		url : url,
		dataType : "json",
		success : function(result) {
			// 更新推荐歌曲区域
			var content = "";
			if(result.isSuccess){
				similarMusics = result.recommendMusics
				var recMusics = result.recommendMusics;
				for(var i=0;i<recMusics.length;i++){
					content += '<li class="songbar1" id="rec-' + recMusics[i].musicId +'"> ' +
									'<div class="dis-music">' ;
					if(i < 3){
						content += 		'<span class="lable1" >';
					}else{
						content += 		'<span class="lable2" >';
					}
					
					content += (i+1) + 	'</span> '+ 
										'<a class="songlink" id="s1" href="javascript:void(0);">' + recMusics[i].musicName + '</a> '+ 
									'</div>' +
									'<div class="dis-opr">' +
										'<a href="javascript:void(0);" class="dis-play" onclick="playRecommend(this)"></a>' +
										'<a href="javascript:void(0);" class="dis-add" onclick="addRecommend(this)"></a>' +
									'</div>'  +
								'</li> ';
				}
				
				$("#recommend-song").empty();
			    $("#recommend-song").append(content);
				
			}
			
			
			
			
		},error : function(errorMsg) {
			// 请求失败时执行该函数
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
	if(!isLogin("登录即可评论")){
		return;
	}

	// 非空验证
	var comment = $("#comment").val();
	var musicId = $(".song")[0].id;
	
	if(comment == ""){
		popTips(false,"评论不可为空！");
		return;
	}
	
	
	// 请求后台添加
	$.ajax({
        type : "POST",
        async : true,         
        url : "/MusicPlayer/display",    
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
        		$("#comment").val("");
        		
        		// 刷新下方评论
        		loadMusicComment(musicId,1);
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

// TODO
// 添加到待播放列表
//添加并播放歌曲
function playRecommend(e){
	var id = e.parentNode.parentNode.id;
	
	var musicId = id.substring(id.indexOf("-")+1);
	
	// 推荐的歌曲对象: 在数组中根据ID找到对象
	var index = findMusicListIndex(musicId, similarMusics);
	if(index != -1){
		var choiceMusic = similarMusics[index];
		addAndPlayMusic(choiceMusic);
	}
}

// 添加歌曲
function addRecommend(e){
var id = e.parentNode.parentNode.id;
	
	var musicId = id.substring(id.indexOf("-")+1);
	
	// 推荐的歌曲对象: 在数组中根据ID找到对象
	var index = findMusicListIndex(musicId, similarMusics);
	if(index != -1){
		var choiceMusic = similarMusics[index];
		addMusicToBeList(choiceMusic);
		
		// 刷新总待播放歌曲数量
		$("#curlistnum").empty();
		$("#curlistnum").append(musicList.length);
	}
}

// 播放当前歌曲
$("#one-play").click(function(){
	// 将选中的音乐加到待播放列表,并播放
	var musicId = $(".song").attr("id");
	
	addAndPlayMusic(music);
});

// 收藏当前歌曲
$("#save-icon").click(function(){
	if(!isLogin("登录即可收藏歌曲")){
		return;
	}
	
	var musicId = $(".song").attr("id");
	
	// 保存歌曲到歌单
	queryAndFillSavePop(musicId);
});

function isLogin(tipMsg){
	// 登录验证： 未登录，则不允许评论，提示用户未登录
	var src = $("#user-pic").attr("src");
	if(src.indexOf("default_avatar.jpg") != -1){
		// 未登录
		popTips(false,tipMsg);
		return false;
	}
	
	return true
}