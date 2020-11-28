$(".head").load("/MusicPlayer/header.html",function(){
	// 绑定事件: 鼠标放置改变导航栏颜色
	$(".nav-bg").mouseover(function(){
		this.style.backgroundColor = "#000";
	});

	$(".nav-bg").mouseout(function(){
		this.style.backgroundColor = "#242424";
	});
	
	$("#myMusic").click(function(){
		$.ajax({
		    type : "POST",
		    async : true,         
		    url : "/MusicPlayer/playlist",    
		    dataType : "json",        //返回数据形式为json
		    
		    success : function(result) {
		        //请求成功时执行该函数内容，result即为服务器返回的json对象
		    	window.history.pushState(null,'','playlist?id=1');
		        $(".content").remove(".content-wrap");
		        $(".content").load("user/playlist.html #container",function(){
		        	//填充数据
			        // 先填充左侧歌单
			        var content = "";
			        var playlistArr = result.playlist;
			        for(var i=0;i<playlistArr.length;i++){
			        	content += '<li class="selected" onClick="choiceMusicList(this)" id="';
			        	if(i == 0){
			        		content += 'love"';
			        	}else{
			        		content += playlistArr[i].listId + '"';
			        	}
			        	
			        	content += '<div class="left-item">' +
			        	              '<div class="left-item-image">' +
			        	                  '<img src="image/music_list.jpg">' + 
			      					  '</div>' + 
			      					  '<div class="left-item-text">' + 
			      						  '<div class="desc-item">' + playlistArr[i].listName +'</div>' + 
			      						  '<div class="num-item">3首</div>' +
			      					  '</div>' + 
			      				   '</div>'
			      			   '</li>';
			        }
			        
			        $("#music-file-list").append(content);
			        
			        // 再填充右侧歌曲列表
			        var musiclist = "";
			        var musiclistArr = result.musiclist;
			        for(var i=0;i<musiclistArr.length;i++){
			        	if(i/2==1)
			        		musiclist +='<tr id="table-rows-1 bg">';
			        	else
			        		musiclist +='<tr id="table-rows-1">';
			        	musiclist +=      '<td class="w1">' + (i+1) + '</td>' + 
										  '<td class="w2">' + 
											  '<img src="image/play.png"/>' + 
										  '</td>' + 
										  '<td class="w3">' + musiclistArr[i].musicName + '</td>' + 
										  '<td class="w4">03:36</td>' + 
										  '<td class="w5">' + musiclistArr[i].musicAuthor + '</td>' + 
										  '<td class="w6">' + musiclistArr[i].musicAlbum + '</td>' + 
									'</tr>'
			        }
			        
			        $("#mymusiclist").append(musiclist);
		        });
		        
		    },
		    error : function(errorMsg) {
		        //请求失败时执行该函数
		        alert("请求数据失败!");
		    }
		});
		
	});
	
});