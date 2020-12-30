
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
function loadMusicInfo(musicId){
	var aa = window.location.href;
	var url = "/MusicPlayer/user/display?musicId="+musicId;
	alert(aa);
	$.ajax({
        type : "GET",
        async : true,         
        url : url,    
        dataType : "json",
        success : function(result) {
        	// TODO
        	// 填充数据
        
        },error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});
}