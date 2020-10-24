// 全局变量定义

// 年月日选择器
var year = document.getElementById("scroll-year");
var month = document.getElementById("scroll-month");
var day = document.getElementById("scroll-day");

// 地区城市选择器
var province = document.getElementById("scroll-pro");
var city = document.getElementById("scroll-city");

/* 鼠标悬浮 */
var unit = document.getElementsByClassName("f-thide");


var yearSpan = document.getElementById("year");
var monthSpan = document.getElementById("month");
var daySpan = document.getElementById("day");
var provinceSpan = document.getElementById("province");
var citySpan = document.getElementById("city");

var pselect = document.getElementById("pselect");
var yselect = document.getElementById("yselect");
var mselect = document.getElementById("mselect");
var dselect = document.getElementById("dselect");

var set = document.getElementById("set");
var pic = document.getElementById("pic");

bindEvent();
initYearSelect();
initMonthSelect();
initProSelect();
	
// 阻止事件冒泡
$(".f-thide a").click(function(event){
	event.stopPropagation();
});
	
// 绑定事件
function bindEvent(){
	
	/* 为日期控件添加点击事件：改变样式 */
	year.addEventListener('click',function(){
		document.getElementById("yselect").style.display = "block";
	})
	
	month.addEventListener('click',function(){
		document.getElementById("mselect").style.display = "block";
	})
	
	province.addEventListener('click',function() {
		document.getElementById("pselect").style.display = "block";
	})
	
	// 如果年份不为空，则为day添加点击事件
	if(daySpan.innerHTML != "-"){
		day.addEventListener('click',function(){
			bindClickForDay();
		})
	}

	// 如果省份不为空，则为城市选择器添加点击事件
	if(provinceSpan.innerHTML != "---"){
		city.addEventListener('click',function(){
			bindClickForCity();
		})
	}
}

function initYearSelect(){
	// 得到当前年份
	var currentYear = new Date().getFullYear();
	var str="";
    
	// 从当前年份往前数共100年
	for(var i = 0;i<=100;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceYear(this)" onMouseOver="pluginOver(this.id)" onMouseOut="pluginOut(this.id)">'+ currentYear
			 +'</a></li>';
		currentYear--;
	}
	
	yselect.innerHTML = str;
}

function initMonthSelect(){
	var str="";
    
	for(var i = 1;i<=12;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceMonth(this)">'+ i
			 +'</a></li>';
	}
	
	mselect.innerHTML = str;
}

function choiceYear(year){
	// 填充span标签中的年份内容
	yearSpan.innerHTML = year.innerHTML;
	
	// 每次切换年份，都要清空原有的月份和日份，默认为1月1日
	monthSpan.innerHTML = "1";
	daySpan.innerHTML = "1";

	// 选中了之后下拉框要消失
	document.getElementById("yselect").style.display = "none";
	// 且要给day添加点击事件
	day.addEventListener('click',function(){	
		bindClickForDay();
	})
	
}

// 选择月份
function choiceMonth(month){
	// 填充span标签中的月份内容
	monthSpan.innerHTML = month.innerHTML;
	
	// 每次切换年份，都要清空原有的日份，默认为1号
	daySpan.innerHTML = "1";

	// 选中了之后下拉框要消失
	document.getElementById("mselect").style.display = "none";
	// 且要给day添加点击事件
//	day.addEventListener('click',function(){	
//		bindClickForDay();
//	})
	
}

// 选择天数
function choiceDay(day){
	// 填充span标签中的月份内容
	daySpan.innerHTML = day.innerHTML;

	// 选中了之后下拉框要消失
	dselect.style.display = "none";
}

function pluginOver(id){
//	var li = document.getElementById(id);
//	li.style.background = "#ccc";

}
function pluginOut(id){
//	var li = document.getElementById(id);
//	li.style.background = "white";
}

function initProSelect(){
	var str="";
    
	// 根据areaLocaltion.js初始化省份选择器
	for(var i = 0;i<provinceArray.length;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choicePro(this)">'+
			provinceArray[i].province +'</a></li>';
	}
	
	pselect.innerHTML = str;
}

function choicePro(province){
	// 填充span标签中的省份内容
	provinceSpan.innerHTML = province.innerHTML;
	
	// 每次切换省份，都要清空原有的城市
	var proValue = provinceSpan.textContent;
	var index = getIndexOfProvince(proValue);
	var contry = provinceArray[index].country[0];
     
	citySpan.innerHTML = contry;

	// 选中了之后下拉框要消失
	document.getElementById("pselect").style.display = "none";
	// 且要给城市添加点击事件
	city.addEventListener('click',function(){	
		bindClickForCity();
	})
	
}

function bindClickForCity(){
	var proValue = provinceSpan.textContent;
	var index = getIndexOfProvince(proValue);
	var length = provinceArray[index].country.length
	var contry = provinceArray[index].country
	var str = "";
	
	// 为city选择器循环添加<li>标签
	for(var i = 0;i<length;i++){
		str+='<li class="f-thide"><a href="javascript:void(0)" onclick="choiceCity(this)">'+contry[i] +'</a></li>';
	}
	cselect.innerHTML = str;
	document.getElementById("cselect").style.display = "block";
	
	// 阻止事件冒泡
	$(".f-thide a").click(function(event){
		event.stopPropagation();
	});
}

function bindClickForDay(){
	// 获得年和月
	var yearValue = parseInt(yearSpan.textContent);
	var monthValue = parseInt(monthSpan.textContent);
	
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
	dselect.innerHTML = str;
	dselect.style.display = "block";
	
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

// 选择城市
function choiceCity(city){
	citySpan.innerHTML = city.innerHTML;
	document.getElementById("cselect").style.display = "none";
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
//
function modifyUserInfo(){
	var isSubmit = true;
	
	var username = document.getElementById("username");
	var introduce = document.getElementById("introduce");
	var gender = document.getElementsByName("gender");
	
	var yearValue = parseInt(yearSpan.textContent);
	var monthValue = parseInt(monthSpan.textContent);
	var dayValue = parseInt(daySpan.textContent);
	
	var proValue = provinceSpan.textContent;
	var cityValue = citySpan.textContent;
	
	// 昵称不能为空，其余信息可为空
	if(username.value == ""){
	    isSubmit = false;
	    username.style.border = "1px solid red";
		document.getElementById("error").style.display = "block";
	}
	
    if(isSubmit){
	    
	}
	
}

// 更换头像
function changePic(){
	set.style.display = "none";
	pic.style.display = "block";
}


function backSet(){
	set.style.display = "block";
	pic.style.display = "none";
}
