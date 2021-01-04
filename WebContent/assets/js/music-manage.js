// 变量，记录当前被选中的行数
var checkedRowNum = 0;

requestData(1,"");

function requestData(curPage,condition){
	// GET请求加载页面数据
	$.ajax({
	    type : "GET",
	    async : true,         
	    url : "/MusicPlayer/admin/music-manage?curPage=" + curPage + "&condition=" + condition + "&date=" + new Date().getTime(),    
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
	    	var page= result.page;
	    	if(page != null){
	    		// 填充下方的分页控件
	    		var pageInfo = "第 " + page.curPage + " 页，共 " + page.totalPage+ " 页 ";
	    		$(".dataTables_info").empty();
	    		$(".dataTables_info").append(pageInfo);
	    		
	    		// 右侧
	    		var pageController = "";
	    		pageController += '<li class="paginate_btn page-item pre">'+
									  '<a href="javascript:void(0);" class="p-link" onclick="prePage()">上一页</a>'+
								  '</li>';
	    		if(page.totalPage <=5){
	    			for(var i=1;i<=page.totalPage;i++){
	    				pageController += '<li class="paginate_btn page-item" id="page-' + i + '">'+
											  '<a href="javascript:void(0);" class="p-link" onclick="jumpToPage(this)">' + i + '</a>'+
										  '</li>';
	    			}
	    			
	    		}else{
	    			// 超过5页  1 2 3 ... 6
	    			var pageNum = null;
	    			if(page.curPage <= 3 ){
	    				pageNum = [1,2,3,"...",page.totalPage];
	    			}else if(page.curPage  >= page.totalPage - 2){
	    				pageNum = [1,"...",page.totalPage - 2,page.totalPage - 1,page.totalPage];
	    			}else{
	    				pageNum = [1,"...",page.curPage,"...",page.totalPage];
	    			}
	    			
	    			// 
	    			for(var i=0;i<pageNum.length;i++){
	    				if(pageNum[i] != "..."){
	    					pageController += '<li class="paginate_btn page-item" id="page-' + pageNum[i] + '">'+
												  '<a href="javascript:void(0);" class="p-link" onclick="jumpToPage(this)">' + pageNum[i] + '</a>'+
											  '</li>';
	    				}else{
	    					pageController += '<li class="paginate_btn page-item disabled">'+
												  '<a href="javascript:void(0);" class="p-link" onclick="prePage()">...</a>'+
											  '</li>';
	    				}
	    			}
	    			
	    		}
	    		
	    		pageController += '<li class="paginate_btn page-item next">'+
									  '<a href="javascript:void(0);" class="p-link" onclick="nextPage()">下一页</a>'+
								  '</li>';
	    		$("#pagination").empty();
	    		$("#pagination").append(pageController);
	    		
	    		// 给当前页添加背景颜色
	    		$("#page-"+page.curPage + " a").addClass("active");
	    	}
	    },
	    error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});
}

function prePage(){
	
}

function nextPage(){
	
}

function jumpToPage(e){
	var id = e.parentNode.id;
	// page-
	var curPage = id.substring(id.indexOf("-")+1);
	
	requestData(curPage,"");
}

$(".p-link").click(function(){
	var id = this.parentNode.id;
});

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