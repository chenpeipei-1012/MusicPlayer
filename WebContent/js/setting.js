// 页面加载时绑定事件
bindEvent();
// 初始化页面中的部分元素
init();

// 阻止年月、省份选择器中事件冒泡
$(".f-thide a").click(function(event){
	event.stopPropagation();
});

function bindEvent(){
	// 给年份选择器绑定点击事件
	$("#scroll-year").click(function(){
		// jquery里面没有：元素.style.display。只有隐藏hide()和显示show()
		$("#yselect").show();
	});

	// 给月份选择器绑定点击事件
	$("#scroll-month").click(function(event){
		$("#mselect").show();
	});

	// 给省份选择器绑定点击事件
	$("#scroll-pro").click(function(){
		$("#pselect").show();
	});

	// 如果用户初始年份不为空，则为天数选择器添加点击事件并初始化
	if($("#year").text() != "--"){
		$("#scroll-day").click(function(){
			initDaySelect();
			$("#dselect").show();
		});
	}

	// 如果用户初始省份不为空，则为城市选择器添加点击事件
	if($("#province").text() != "---"){
		$("#scroll-city").click(function(){
			$("#cselect").show();
		});
	}
}

function init(){
	initYearSelect();
	initMonthSelect();
	initProSelect();
}

// 初始化年份选择器
function initYearSelect(){
	// 得到当前年份
	var currentYear = new Date().getFullYear();
	var str = "";
    
	// 从当前年份往前数共100年
	for(var i = 0;i<=100;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceYear(this)">'+ currentYear
			+'</a></li>';
		currentYear--;
	}
	
	// 在元素之间追加元素   = innerHTML()
	$("#yselect").append(str);
}

// 初始化月份选择器
function initMonthSelect(){
	var str = "";
    
	for(var i = 1;i<=12;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceMonth(this)">'+ i
			 +'</a></li>';
	}
	
	$("#mselect").append(str);
}

// 初始化省份选择器
function initProSelect(){
	var str = "";
    
	// 根据areaLocaltion.js初始化省份选择器
	for(var i = 0;i<provinceArray.length;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choicePro(this)">'+
			provinceArray[i].province +'</a></li>';
	}
	
	$("#pselect").append(str);
}

// 选择年份
function choiceYear(year){
	// 先清空，再填充span标签中的年份内容
	$("#year").empty();
	$("#year").append(year.innerText);
	
	// 每次切换年份，都要清空原有的月份和日份，默认为1月1日
	$("#month").empty();
	$("#month").append("1");
	$("#day").empty();
	$("#day").append("1");

	// 选中了之后下拉框要消失
	$("#yselect").hide();
	
	// 且要给day添加点击事件
	$("#scroll-day").click(function(){
		initDaySelect();
		$("#dselect").show();
	});
}

// 选择月份
function choiceMonth(month){
	// 先清空，填充span标签中的月份内容
	$("#month").empty();
	$("#month").append(month.innerText);
	
	// 每次切换月份，都要清空原有的日份，默认为1号
	$("#day").empty();
	$("#day").append("1");

	// 选中了之后下拉框要消失
	$("#mselect").hide();
}

// 选择天数
function choiceDay(day){
	// 填充span标签中的月份内容
	$("#day").empty();
	$("#day").append(day.innerText);

	// 选中了之后下拉框要消失
	$("#dselect").hide();
	
	// 阻止事件冒泡
	$(".f-thide a").click(function(event){
		event.stopPropagation();
	});
}


function choicePro(province){
	// 填充span标签中的省份内容
	$("#province").empty();
	$("#province").append(province.innerText);
	
	// 每次切换省份，都要清空原有的城市: 选择该省份的第一个城市
	var index = getIndexOfProvince(province.innerText);
	var contry = provinceArray[index].country[0];
    
	$("#city").empty();
	$("#city").append(contry);

	// 选中了之后下拉框要消失
	$("#pselect").hide();
	
	// 给城市下拉列表初始化
	initCity();
	
	// 给城市绑定点击事件
	$("#scroll-city").click(function(){
		$("#cselect").show();
		
	});
}

// 选择城市
function choiceCity(city){
	$("#city").empty();
	$("#city").append(city.innerText);

	$("#cselect").hide();
}

function initCity(){
	var proValue = $("#province").text();
	var index = getIndexOfProvince(proValue);
	var length = provinceArray[index].country.length
	var contry = provinceArray[index].country
	var str = "";
	
	// 为city选择器循环添加<li>标签
	for(var i = 0;i<length;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceCity(this)">'+contry[i] +'</a></li>';
	}
	$("#cselect").append(str);
	$("cselect").show();
	
	 //阻止事件冒泡
	$(".f-thide a").click(function(event){
		event.stopPropagation();
	});
}

function initDaySelect(){
	// 获得年和月
	var yearValue = parseInt($("#year").text());
	var monthValue = parseInt($("#month").text());
	
	var dayNum = -1;
	var isCurLeapYear = isLeapYear(yearValue);
	
	var bigArray = [1,3,5,7,8,10,12];
	var samllArray = [4,6,9,11];
	
	// 根据年月计算有多少天
	// 1:31 2:28(平年) 或 29(闰年)    3:31   4:30  5:31   6:30 7:31   8:31   9:30   10:31 11:30 12:31
	if(bigArray.indexOf(monthValue) != -1){
		// 是大月
		dayNum = 31;
	 }else if(samllArray.indexOf(monthValue) != -1){ 
		 // 是小月
		dayNum = 30; 
     }else{
		 // 2月则根据年份来判定平年 or 闰年
		 if(isCurLeapYear){
			 dayNum = 29;
		 }else{
			 dayNum = 28;
		 }
	 }
	
	var str = "";
	
	// 为day选择器循环添加<li>标签
	for(var i = 1;i<=dayNum;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceDay(this)">'+ i +'</a></li>';
	}
	$("#dselect").empty();
	$("#dselect").append(str);
	
	// 阻止事件冒泡
	$(".f-thide a").click(function(event){
		event.stopPropagation();
	});
}

// 一个年月是否是闰年: 1: 能被4整除但不能被100整    2: 能被400整除
function isLeapYear(year){
	var result = false;
	if((year%4==0 && year%100 !=0) || year%400==0){
		result =  true;
	}
	return result;
}

// 得到省份所在的下标
function getIndexOfProvince(province){
	var index = -1;
	var provinceV = province;
	for(var i = 0;i<provinceArray.length;i++){
		if(provinceArray[i].province == province){
			index = i;
			break;
		}
	}

	return index;
}

// 修改用户信息
function modifyUserInfo(){
	// 清空原来的错误信息
	$("#error").hide();
	
	// 是否允许提交表单
	var isSubmit = true;
	
	// 昵称不能为空，其余信息可为空
	if($("#username").val() == ""){
	    isSubmit = false;
	    $("#username").css({
			"border":"1px solid red"
		});
		$("#error").show();
	}
	
    if(!isSubmit){
	    return false;
	}
    
    // 生日
    var bir = "";
    if($("#year").text() != "--"){
    	bir += $("#year").text() + "-" + $("#month").text() + "-" + $("#day").text();
    }
    
    // 地区
    var iddr = "";
    if($("#province").text() != "---"){
    	iddr += $("#province").text() + " " +$("#city").text() ;
    }
    // AJAX提交数据
    $.ajax({
        type : "POST",
        async : true,         
        url : "/MusicPlayer/update",    
        dataType : "json",        //返回数据形式为json
        data:{"nickname":$("#username").val(),
        	  "desc":$("#introduce").val(),
        	  "gender":$("[name='gender']").val(),
        	  "bir":bir,
        	  "iddr":iddr,},
        success : function(result) {
        	if(result.isSuccess){
        		
        	}
        },
        error : function(errorMsg) {
            //请求失败时执行该函数
            alert("请求数据失败!");
        }
    });
    
    return false;
}

// 进入更换头像页面
function changePic(){
	$("#set").hide();
	$("#pic").show();
}

// 返回个人设置页面
function backSet(){
	$("#set").show();
	$("#pic").hide();
}


$.ajax({
    type : "GET",
    async : true,         
    url : "/MusicPlayer/update",    
    dataType : "json",        //返回数据形式为json
    success : function(result) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象
    	var user = result.user;
        $("#username").val(user.userNick);
        $("#introduce").val(user.userDesc);
        
        // 设置radio被选中
        if(user.userGender == "男")
        	$("[name='gender'][value='男']").prop("checked", "checked");
        else
        	$("[name='gender'][value='女']").prop("checked", "checked");
        // $('input[type=radio]').val(user.userGender);
        
        var bir = user.userBirthday;
        if(bir != ""){
        	var birArr = bir.split("-");
            
            $("#year").text(birArr[0]);
            $("#month").text(birArr[1]);
            $("#day").text(birArr[2]);
        }
        
        var iddr = user.userIddr;
        if(iddr != ""){
        	var iddrArr = iddr.split(" ");
            $("#province").text(iddrArr[0]);
            $("#city").text(iddrArr[1]);
        }
    },
    error : function(errorMsg) {
        //请求失败时执行该函数
        alert("请求数据失败!");
    }
});