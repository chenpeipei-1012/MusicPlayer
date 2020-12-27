<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>个人设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="styleSheet"  href="../css/head.css" />
	<link type="text/css" rel="styleSheet"  href="../css/setting.css" />
	<link type="text/css" rel="styleSheet"  href="../css/foot.css"/>
	<style>
		*{
			margin: 0;
			padding: 0;
			text-decoration: none;
			outline: none;
		}
		html,body{
			width:100%;
			height:100%;
			/* 清除body自带的8px的外边距 */
			margin: 0;
		}
		body{
			overflow: hidden;
			box-sizing: border-box;
			padding-top: 75px;
			padding-bottom: 67px;
			background-color: #f2f2f2;
		}
	</style>
</head>
<body>
	<div class="head">
		<%@ include file="../header.jsp"%>
	</div>
	<!-- <div class="set-content"> -->
		<div class="content">
			<div class="content-wrapper" id="set">
				<form class="setting-form" method="post" id="form" onsubmit="return modifyUserInfo();">
					<div class="content-top">
						<h3>个人设置</h3>
						<hr/>
					</div>
					<div class="content-left">
						<div class="itm">
							<label>昵称：</label>
							<input name="username" id="username">
							<div id="error" class="error" style="display: none">
								<img src="../image/error.png"/>
								<span>昵称2-15字符，且不包含除_和-的特殊字符。</span>
							</div>
						</div>
						<div class="itm">
							<label>介绍：</label>
							<textarea name="introduce" id="introduce"></textarea>
						</div>
						<div class="itm">
							<label>性别：</label>
							<label>
								<input name="gender" type="radio" value="男" checked>男
							</label>
							<label>
								<input name="gender" type="radio" value="女">女
							</label>
						</div>
						<div class="itm  itm-selc">
							<label>生日：</label>
							<div class="date" id="scroll-year">
								<span class="year" id="year">--</span>
								<img  src="../image/down-icon.jpg" class="btn"/>
								<ul class="down-select down-year" style="display: none" id="yselect">
	
								</ul>
							</div>
							<span>年</span>
							<div class="date month" id="scroll-month">
								<span class="year" id="month">01</span>
								<img  src="../image/down-icon.jpg" class="btn"/>
								<ul class="down-select" style="display: none" id="mselect">
								</ul>
							</div>
							<span>月</span>
							<div class="date day" id="scroll-day">
								<span class="year" id="day">-</span>
								<img  src="../image/down-icon.jpg" class="btn"/>
								<ul class="down-select" style="display: none" id="dselect">
								</ul>
							</div>
							<span>日</span>
						</div>
						<div class="itm itm-selc">
							<label>地区：</label>
							<div class="date area" id="scroll-pro">
								<span class="year" id="province">---</span>
								<img  src="../image/down-icon.jpg" class="btn"/>
								<ul class="down-select prov-ul" id="pselect" style="display: none">
	
								</ul>
							</div>
							<span>&nbsp;</span>
							<div class="date" id="scroll-city">
								<span class="year" id="city">--</span>
								<img  src="../image/down-icon.jpg" class="btn"/>
								<ul class="down-select city-ul" id="cselect" style="display: none">
								</ul>
							</div>
							<span>&nbsp;</span>
						</div>
						<div class="button">
							<input type="submit" value="保存" id="submit" onclick="modifyUserInfo()"/>
	
						</div>
					</div>
					<div class="content-right">
						<div class="content-right-wrapper">
							<img id="update-upic" src="../image/picture.jpg"/>
							<span class="btm"></span>
							<a href="javascript:void(0);" class="upload" id="change-pic">更改头像</a>
							<input style="display:none" name="picfile" id="picfile" type="file"/>
						</div>
					</div>
				</form>
				
			</div>
		</div>
	 <!--</div>
 	<iframe src="setting-content.html" class="set-content"></iframe>-->
	<div class="foot">
		<%@ include file="../foot.html"%>
	</div>
	
	<div id="pop-content"></div>
	
	<script src="../js/jquery-1.8.3.js"></script>
	<script src="../js/areaLocation.js"></script>
	<script src="../js/head.js"></script>
	<script src="../js/foot.js"></script>
	<script src="../js/setting.js"></script>
	<script src="../js/pop.js"></script>
</body>
</html>