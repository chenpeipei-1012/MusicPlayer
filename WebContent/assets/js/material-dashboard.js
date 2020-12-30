$(document).ready(function() {
	requestPage();
});

function requestPage(){
	// GET请求加载页面数据
	$.ajax({
	    type : "GET",
	    async : true,         
	    url : "/MusicPlayer/admin/dashboard?date=" + new Date().getTime(),    
	    dataType : "json",        //返回数据形式为json
	    
	    success : function(result) {
	    	if(result.numMap != null){
	    		$("#yestodyNewUserNum").empty();
		    	$("#yestodyNewUserNum").append(result.numMap.yestodayNewUserNum);
		    	
		    	$("#curNewMusicNum").empty();
		    	$("#curNewMusicNum").append(result.numMap.curNewMusicNum);
		    	
		    	$("#curMusicDownloadNum").empty();
		    	$("#curMusicDownloadNum").append(result.numMap.curMusicDownloadNum);
		    	
		    	$("#activeSessionsNum").empty();
		    	$("#activeSessionsNum").append(result.numMap.activeSessionsNum);
		    	
		    	// 当天日期
		    	var date = new Date();
		    	var curTime = date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
		    	
		    	// 昨天日期
		    	var yestodayDate = new Date();
		    	yestodayDate.setTime(yestodayDate.getTime()-24*60*60*1000);
		    	var yestodayTime = yestodayDate.getFullYear()+"-" + (yestodayDate.getMonth()) + "-" + yestodayDate.getDate();
		    	
		    	$("#urc-time").append(yestodayTime);
		    	$("#cnm-time").append(curTime);
		    	
		    	
	    	}
	    	
	    	if(result.musicDownloadNum != null && result.musicDownloadNum.length == 7){
	    		// 初始化图表
	    		var data = result.musicDownloadNum;
	    	    md.initDashboardPageCharts(data);
	    	    
	    	    
	    	    if(data[6] > data[5]){
		    		
		    	}else{
		    		
		    	}
	    	}
	    	
	    },
	    error : function(errorMsg) {
	        //请求失败时执行该函数
	        alert("请求数据失败!");
	    }
	});
}

// md的定义
md = {
  initDashboardPageCharts: function(data) {

	// #dailySalesChart
    if ($('#dailySalesChart').length != 0 || $('#completedTasksChart').length != 0 || $('#websiteViewsChart').length != 0) {
      /* ----------==========     歌曲下载线性图 初始化    ==========---------- */
      
      var labels = new Array();
      var series = new Array();
      var userSeries = new Array();
      var weekDays = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
      var week ;
      
      for(var i=data.length-1;i>=0;i--){
    	  week = new Date(data[i].downloadDate).getDay();
    	  
    	  labels.push(weekDays[week]);
    	  series.push(data[i].downloadNum);
    	  userSeries.push(data[i].userNum);
      }
	  // 将其分为横坐标和纵坐标，横坐标对应日期，纵坐标对应下载量
      dataDailySalesChart = {
        labels: labels,
        series: [
                series
        ]
      };
      
      // 前一天下载量为0
      var percentage = 0;
      var desc = "";
      if(series[5] == 0 || series[6]){
    	  percentage = series[6] - series[5];
    	  desc = percentage < 0 ? "昨天减少 了"+ (-percentage) + "次下载量" : "昨天增加了"+ percentage + "次下载量";
      }else{
    	  percentage = (series[6] - series[5])/series[5] * 100;
    	  desc = percentage < 0 ? "昨天减少 了"+ (-percentage) + "%下载量" : "昨天增加 了"+ percentage + "%下载量" 
      }
      
      
      var iconContent = percentage < 0 ? "arrow_downward" : "arrow_upward";
      $("#md-value").append(desc);
      $("#md-icon").empty();
      $("#md-icon").append(iconContent);
      
      var maxValue = Math.max.apply(null, series);
	  // 选项
      optionsDailySalesChart = {
		// 是否平滑线条：否   ---->   直线
        lineSmooth: false,
		// 指定图表最低点
        low: 0,   
		// 指定图表最高点：需根据最大值设定
        high: maxValue + 10, 
        chartPadding: {
          top: 0,
          right: 0,
          bottom: 0,
          left: 0
        },
      }

      new Chartist.Line('#dailySalesChart', dataDailySalesChart, optionsDailySalesChart);
	  
	  /* ----------==========     每日增加用户数量 初始化    ==========---------- */
      
	  userRegisterDailyChart = {
        labels: labels,
        series: [
               userSeries
        ]
      };
	  
	  maxValue = Math.max.apply(null, userSeries);
	  var optionsWebsiteViewsChart = {
        axisX: {
          showGrid: false
        },
        low: 0,
        high: maxValue + 10,
        chartPadding: {
          top: 0,
          right: 5,
          bottom: 0,
          left: 0
        }
      };
	  
	  dataCompletedTasksChart = {
        labels: ['12p', '3p', '6p', '9p', '12p', '3a', '6a', '9a'],
        series: [
          [230, 750, 450, 300, 280, 240, 200, 190]
        ]
      };

      optionsCompletedTasksChart = {
        lineSmooth: Chartist.Interpolation.cardinal({
          tension: 0
        }),
        low: 0,
        high: 1000, // creative tim: we recommend you to set the high sa the biggest value + something for a better look
        chartPadding: {
          top: 0,
          right: 0,
          bottom: 0,
          left: 0
        }
      }

      var completedTasksChart = new Chartist.Line('#completedTasksChart', dataCompletedTasksChart, optionsCompletedTasksChart);

	  
	  Chartist.Bar('#websiteViewsChart', userRegisterDailyChart, optionsWebsiteViewsChart);
	  
	  
	}
  }
  

}