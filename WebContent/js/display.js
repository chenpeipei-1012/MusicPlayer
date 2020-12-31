
// 根据URL中musicId加载音乐信息

// 展开按钮
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

// 加载歌曲信息
function loadMusicInfo(music){
	var aa = window.location.href;
	var url = "/MusicPlayer/user/display?musicId="+music.music_id;
	alert(aa);
	$.ajax({
        type : "GET",
        async : true,
        url : url,
        dataType : "json",
        success : function(result) {
        	// TODO
        	// 填充数据
			//替换歌曲封面
			document.getElementById("songphoto").attributes(src=music_pic);
			//替换歌曲名
			document.getElementById("song").innerHTML=music.music_name;
			//替换歌手
			document.getElementById("singer").innerHTML=music.music_author;
			//替换所属专辑
			document.getElementById("album").innerHTML=albumName;
			//替换歌词 AAAAh 不会

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

			//替换歌曲列表 没有添加每个网站的url
			document.getElementById("s1").innerHTML=RCMusic[0].music_name;
			document.getElementById("s2").innerHTML=RCMusic[1].music_name;
			document.getElementById("s3").innerHTML=RCMusic[2].music_name;
			document.getElementById("s4").innerHTML=RCMusic[3].music_name;
			document.getElementById("s5").innerHTML=RCMusic[4].music_name;
			document.getElementById("s6").innerHTML=RCMusic[5].music_name;
			document.getElementById("s7").innerHTML=RCMusic[6].music_name;
			document.getElementById("s8").innerHTML=RCMusic[7].music_name;
			document.getElementById("s9").innerHTML=RCMusic[8].music_name;
			document.getElementById("s10").innerHTML=RCMusic[9].music_name;
			document.getElementById("s11").innerHTML=RCMusic[10].music_name;

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
			document.getElementById("u1").attributes(src=MusicComment[0].music_user_pic);
			document.getElementById("u1nm").innerHTML=MusicComment[0].user_name;
			document.getElementById("u1cm").innerHTML=MusicComment[0].comment;

			document.getElementById("u1").attributes(src=MusicComment[1].music_user_pic);
			document.getElementById("u1nm").innerHTML=MusicComment[1].user_name;
			document.getElementById("u1cm").innerHTML=MusicComment[1].comment;

			document.getElementById("u1").attributes(src=MusicComment[2].music_user_pic);
			document.getElementById("u1nm").innerHTML=MusicComment[2].user_name;
			document.getElementById("u1cm").innerHTML=MusicComment[2].comment;

		},error : function(errorMsg) {
			//请求失败时执行该函数
			alert("请求数据失败!");
		}

	});
}
