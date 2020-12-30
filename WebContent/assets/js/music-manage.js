// 变量，记录当前被选中的行数
var checkedRowNum = 0;

requestData();

function requestData(){
	// GET请求加载页面数据
	$.ajax({
	    type : "GET",
	    async : true,         
	    url : "/MusicPlayer/admin/music-manage?curPage=1&date=" + new Date().getTime(),    
	    dataType : "json",
	    
	    success : function(result) {
	    	var musicList = result.musicList;
	    	if(musicList != null){
	    		var content = "";
	    		for(var i=0;i<musicList.length;i++){
	    			var date = new Date(musicList[i].musicCreatedTime.time);
	    			time = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();;
	    			content += '<tr id="' + musicList[i].musicId+ '">' + 
								  '<td>' + 
									'<i class="mdui-icon material-icons c-icon" onclick="checkMusicBox(this)">check_box_outline_blank</i>' + 
								  '</td>' + 
								  '<td>' + musicList[i].musicName + '</td>' +
					                '<td>' + musicList[i].musicAuthor + '</td>' +
									  '<td>' + musicList[i].musicAlbum + '</td>' +
									  '<td>' + time + '</td>' +
									  '<td class="td-actions">' +
										'<button type="button" rel="tooltip" class="btn btn-primary btn-link btn-sm">' +
											'<i class="material-icons">edit</i>' +
										'</button>' +
										'<button type="button" rel="tooltip" class="btn btn-danger btn-link btn-sm">' +
											'<i class="material-icons">close</i>' +
										'</button>' +
									  '</td>' +
					              '</tr>';
	    		}
                
	    		$("#music-manage-table").empty();
	    		$("#music-manage-table").append(content);
	    	}
	    },
	    error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});
}

function checkMusicBox(event){
	var trEle = event.parentNode.parentNode;
	var musicId = trEle.id;
	
	// 勾选状态
	if($(event).text() == "check_box"){
		$(event).empty();
		$(event).append("check_box_outline_blank");
		checkedRowNum -= 1;
	}else{
		$(event).empty();
		$(event).append("check_box");
		checkedRowNum += 1;
	}
	
	// checkedRowNum与行总数作比较
	var count = $("#music-manage-table tr").length;
	
	if(checkedRowNum == count){
		// 全部勾选       check_box
		$("#checkAllIcon").empty();
		$("#checkAllIcon").append("check_box");
	}else if(checkedRowNum = 0){
		// 1：无勾选         空      
		$("#checkAllIcon").empty();
		$("#checkAllIcon").append("check_box_outline_blank");
	}else{
        // 2：部分勾选        -      indeterminate_check_box
		if($("#checkAllIcon").text() == "indeterminate_check_box"){
			return;
		}
		
		$("#checkAllIcon").empty();
		$("#checkAllIcon").append("indeterminate_check_box");
	}
}

function changeAllCheckBoxStatus(){
	// 循环tbody下的所有<i>
	$("#music-manage-table").find("tr .c-icon").each(function(){
		// 
	});
	
}

function checkAllMusic(){
	
}