function userLogin(){
	var isSubmit = true;
	var errMsg = "";
	// 输入框合法性验证
	var userName = $("#username").val();
	var password = $("#password").val();
	
	if(userName == ""){
		// 如果没有则红框显示
		if(!$("#username").hasClass("error-input")){
			$("#username").addClass("error-input");
			$("#u-img").addClass("error-img");
		}
	}else{
		// 如果有去除红框
		if($("#username").hasClass("error-input")){
			$("#username").removeClass("error-input");
			$("#u-img").removeClass("error-img");
		}
	}
	
	if(password == ""){
		// 如果没有则红框显示
		if(!$("#password").hasClass("error-input")){
			$("#password").addClass("error-input");
			$("#p-img").addClass("error-img");
		}
		
	}else{
		// 如果有去除红框
		if($("#password").hasClass("error-input")){
			$("#password").removeClass("error-input");
			$("#p-img").removeClass("error-img");
		}
	}
	
	if(userName == "" || password == ""){
		isSubmit = false;
		errMsg = "用户名或密码不能为空!";
	}
	
	if(!isSubmit){
		// 错误信息显示
		$("#login-err-msg").empty();
		$("#login-err-msg").append(errMsg);
		$("#login-error-wrap").show();
		
		return false;
	}
	
	$("#login-error-wrap").hide();
	
	
	
	return true;
}

function getQueryString(name) {
	// 正则表达式
	var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	var r = window.location.search.substr(1).match(reg);
	
	if (r != null) {
		return unescape(r[2]);
	}
	
	return null;
}

modifyUrl();

function modifyUrl(){
	// 得到URL中的参数which=Login
	var paramerValue = getQueryString("which");
	
	if(paramerValue == "Login"){
		$("#flogin").attr('action', "login?which=Login");
	}
	
}