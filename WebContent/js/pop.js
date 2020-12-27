// 关闭弹窗
function popTips(isSuccess,tipMsg){
	$("#pop-content").empty();
	
	var className = "";
	if(isSuccess){
		className = "tip-icon-success";
	}else{
		className = "tip-icon-error";
	}
	
	var tips = "";
	tips += '<div class="tips" id="tips">'+
		     ' <i class="' + className + '"></i>' +
					'<span class="tip-info">' + tipMsg + '</span>' +
			'</div>' ;

	$("#pop-content").append(tips);
	setTimeout(function(){
		$("#tips").remove();
	},3000);
}