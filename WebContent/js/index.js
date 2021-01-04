// 全局变量
var hotTopMusics = [];

var newTopMusics = [];
var saveTopMusics = [];
var downloadTopMusics = [];

// 加载数据，初始化页面
loadPageData();

// 添加导航栏背景色
initPage();

// 设置轮播图滚动
self.setInterval('fun1()', 3000); //作为全局变量理解，事件为：timerout(),事件处理函数为:fun1().


function fun1() {
        var now_jpeg = document.getElementById("imginfo").getAttribute("src");
        //document.writeln(now_jpeg);
        switch (now_jpeg) {
            case './image/1.jpg':
                document.getElementById('imginfo').setAttribute("src", './image/2.jpg');
                document.getElementById("banner").style.backgroundColor="#CC9E71";

                break;
            case './image/2.jpg':
                document.getElementById('imginfo').setAttribute("src", './image/3.jpg');
                document.getElementById("banner").style.backgroundColor="#D5D4DA";

                break;
            case './image/3.jpg':
                document.getElementById('imginfo').setAttribute("src", './image/4.jpg');
                document.getElementById("banner").style.backgroundColor="#B36F45";

                break;
            case './image/4.jpg':
                document.getElementById('imginfo').setAttribute("src", './image/5.jpg');
                document.getElementById("banner").style.backgroundColor="#D97457";

                break;
            case './image/5.jpg':
                document.getElementById('imginfo').setAttribute("src", './image/6.jpg');
                document.getElementById("banner").style.backgroundColor="#AE9090";

                break;
            case './image/6.jpg':
                document.getElementById('imginfo').setAttribute("src", './image/1.jpg');
                document.getElementById("banner").style.backgroundColor="#ACC9E7";
                break;
            default:
                //document.writeln("未获取src");
                break;
        }
    }



function initPage(){
	$("#mymusic").css("background","#242424");
	$("#find").css("background","#000");
	// 去掉发现音乐的悬浮事件
	$("#find").unbind("mouseout");
	
	// 给我的音乐加上悬浮事件
	$("#mymusic").mouseout(function(){
		this.style.backgroundColor = "#242424";
	});
}

function loadPageData(){
	// 加载 类型及第一个类型下的歌曲 
	loadCategoryMusics("");
	// 加载三个榜单
	loadBandOrder();
	
	
	
}
function loadCategoryMusics(categoryId){
	$.ajax({
        type : "GET",
        async : true,
        url : "/MusicPlayer/hotCategory?categoryId="+categoryId,
        dataType : "json",
        success : function(result) {
        	// 填充数据
        	if(result.isSuccess){
        		var categoryList = result.categoryList;
        		var hotMusics = result.hotMusics;
        		
        		if(categoryList != null){
        			var content = "";
            		
            		for(var i=0;i<categoryList.length;i++){
            			content += '<li id="category-' + categoryList[i].category_id + '"> ' +
    								    '<a onclick="choiceCategory(this)" href="javascript:void(0);" class="everydayRcom">' +  categoryList[i].category_type +'</a>' +
    							   '</li>';
            		}
            		$("#category").empty();
            		$("#category").append(content);
        		}
        		
        		if(hotMusics != null){
        			hotTopMusics = new Array();
        			var content = "";
        			var music = null;
        			var saveCount = 0;
        			var musicPic = "";
        			
        			for(var i=0;i<hotMusics.length;i++){
        				
        				music = hotMusics[i][0];
        				saveCount = hotMusics[i][1];
        				
        				hotTopMusics.push(music);
        				musicPic = "/MusicPlayer/" + music.musicPic;
        				content += '<div class="hotm-' + (i+1) +' h" id="hot-' + music.musicId + '"> ' +
					                    '<img src="' + musicPic + '" alt="" class="x">' +
										'<br>' +
										'<a onclick="viewIndexMusicDetails(this)" href="javascript:void(0);" class="hotM">' + music.musicName + '</a>' +
										'<div class="u-bottom">' +
											'<div class="u-bottom-1">' +
												'<a onclick="playTopMusic(this)" href="javascript:void(0);" class="" title="播放" >' +
													'<img src="./image/Play_Music_32px_1178409_easyicon.net.png" alt="" class="player">' +
												'</a>' +
											'</div>' +
											'<img src="./image/blue_cd_music_note_16px_40209_easyicon.net.png" alt="" class="icon-headset">' +
											'<span class="nb">' + saveCount + '</span>' +
										'</div>' +
					               ' </div>';
        			}
        		
        			$("#recommendHot").empty();
            		$("#recommendHot").append(content);
        		
        		}
        	}
        },error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }

	});
}

function loadBandOrder(){
	// 请求数据
	$.ajax({
        type : "GET",
        async : true,
        url : "/MusicPlayer/bandOrder",
        dataType : "json",
        success : function(result) {
        	// 填充数据
        	newTopMusics = result.newMusicList;
        	fillTopMusics(newTopMusics,"#newMusic","new-");
        	
        	saveTopMusics = result.saveMusicList;
        	fillTopMusics(saveTopMusics,"#saveMusic","save-");
        	
        	downloadTopMusics = result.downloadMusicList;
        	fillTopMusics(downloadTopMusics,"#downloadMusic","dn-");
        },error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }

	});
}

function fillTopMusics(musicList,id,identy){
	var content = "";
	
	for(var i=0;i<musicList.length;i++){
		if(i%2 == 0){
			content += '<div class="x'+(i+1) + ' xg" id="' + identy + musicList[i].musicId + '">';
		}else{
			content += '<div class="x'+(i+1) + ' xgg" id="' + identy + musicList[i].musicId + '">';
		}
		content += 		'<a onclick="viewIndexMusicDetails(this)" href="javascript:void(0);" class="x">'+musicList[i].musicName + '</a>'+
						'<div class="open">'+
							'<a href="javascript:void(0);" class="play1" hidefouse="true">'+
								'<img onclick="playTopMusic(this)" src="./image/Play_Music_32px_1178409_easyicon.net.png" height="12px">'+
							'</a> '+
						'</div>'+
		            '</div>';
	}
	$(id).empty();
	$(id).append(content);
}

// 列表循环播放
$("#newMusicPlay").click(function(){
	addListToBePlay(newTopMusics);
	playMusic();
});

$("#saveMusicPlay").click(function(){
	addListToBePlay(saveTopMusics);
	playMusic();
});

$("#downloadMusicPlay").click(function(){
	addListToBePlay(downloadTopMusics);
	playMusic();
});

function playTopMusic(e){
	// 获取musicId
	var musicId = e.parentNode.parentNode.parentNode.id;
	var prefix = musicId.substring(0,musicId.indexOf("-"));
	
	musicId = musicId.substring(musicId.indexOf("-")+1);
	
	var musicList = null;
	if(prefix == "new"){
		musicList = newTopMusics;
	}else if(prefix == "save"){
		musicList = saveTopMusics;
	}else if(prefix == "dn"){
		musicList = downloadTopMusics;
	}else if(prefix == "hot"){
		musicList = hotTopMusics;
	}
	
	var index = findMusicListIndex(musicId,musicList);
	if(index != -1){
		var music = musicList[index];
		// 通过musicId找到music对象
		addAndPlayMusic(music);
	}
	
}

function viewIndexMusicDetails(e){
	var musicId = e.parentNode.id;
	var prefix = musicId.substring(0,musicId.indexOf("-"));
	
	musicId = musicId.substring(musicId.indexOf("-")+1);
	
	var musicList = null;
	if(prefix == "new"){
		musicList = newTopMusics;
	}else if(prefix == "save"){
		musicList = saveTopMusics;
	}else if(prefix == "dn"){
		musicList = downloadTopMusics;
	}else if(prefix == "hot"){
		musicList = hotTopMusics;
	}
	
	var index = findMusicListIndex(musicId,musicList);
	if(index != -1){
		var music = musicList[index];
		// 加载详情页面
		loadMusicDetail(musicId,musicList); 
	}
	
}

// 切换类别
function choiceCategory(e){
	var categoryId = e.parentNode.id;
	categoryId = categoryId.substring(categoryId.indexOf("-")+1);
	
	loadCategoryMusics(categoryId);
}
