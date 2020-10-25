<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>设置内容</title>
	<link type="text/css" rel="styleSheet"  href="css/setting.css" />
	<style>
		html{
			height:100%;
			width: 100%;
		}
		body{
			height:100%;
			width: 100%;
		}
	</style>
</head>
<body>
	<div class="content">
		<div class="content-wrapper" id="set">
			<form class="setting-form" method="post" id="form" onsubmit="return false;">
				<div class="content-top">
					<h3>个人设置</h3>
					<hr/>
				</div>
				<div class="content-left">
					<div class="itm">
						<label>昵称：</label>
						<input name="username" id="username">
						<div id="error" class="error" style="display: none">
							<img src="image/error.png"/>
							<span id="error-msg">昵称2-15字符，且不包含除_和-的特殊字符。</span>
						</div>
					</div>
					<div class="itm">
						<label>介绍：</label>
						<textarea name="introduce" id="introduce"></textarea>
					</div>
					<div class="itm">
						<label>性别：</label>
						<label>
							<input name="gender" type="radio" value="1" checked>男
						</label>
						<label>
							<input name="gender" type="radio" value="2">女
						</label>
					</div>
					<div class="itm  itm-selc">
						<label>生日：</label>
						<div class="date" id="scroll-year">
							<span class="year" id="year">--</span>
							<img  src="image/down-icon.jpg" class="btn"/>
							<ul class="down-select down-year" style="display: none" id="yselect">

							</ul>
						</div>
						<span>年</span>
						<div class="date month" id="scroll-month">
							<span class="year" id="month">01</span>
							<img  src="image/down-icon.jpg" class="btn"/>
							<ul class="down-select" style="display: none" id="mselect">
							</ul>
						</div>
						<span>月</span>
						<div class="date day" id="scroll-day">
							<span class="year" id="day">-</span>
							<img  src="image/down-icon.jpg" class="btn"/>
							<ul class="down-select" style="display: none" id="dselect">
							</ul>
						</div>
						<span>日</span>
					</div>
					<div class="itm itm-selc">
						<label>地区：</label>
						<div class="date area" id="scroll-pro">
							<span class="year" id="province">---</span>
							<img  src="image/down-icon.jpg" class="btn"/>
							<ul class="down-select prov-ul" id="pselect" style="display: none">

							</ul>
						</div>
						<span>&nbsp;</span>
						<div class="date" id="scroll-city">
							<span class="year" id="city">--</span>
							<img  src="image/down-icon.jpg" class="btn"/>
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
						<img src="image/picture.jpg"/>
						<span class="btm"></span>
						<a class="upload" onClick="changePic()">更改头像</a>
					</div>
				</div>
			</form>

		</div>
		<div class="content-wrapper" id="pic" style="display:none">
			<div class="cur-pic">
				<a class="font-set" onClick="backSet()">个人设置</a>
				<span>&lt</span>
				<span>更换头像</span>
			</div>
			<div class="upload-pic">
				<input type="file"/>
			</div>
			<div class="pic-preview">
				<div class="left-pic-wrap">
					<img src="image/default_cover.png"/>
				</div>
				<div class="right-pic-wrap">
					<p>头像预览</p>
					<div class="big-pic">
						<img src="image/picture.jpg"/>
					</div>
					<p>大尺寸头像（180x180像素）</p>
					<div class="small-pic">
						<img src="image/picture.jpg"/>
					</div>
					<p>小尺寸头像（40x40像素）</p>
				</div>
			</div>
			<div class="button-wrap">
				<input type="button" value="保存" onclick="savePic()"/>
				<input type="button" value="取消"  onclick="canclePic()"/>
			</div>
		</div>
	</div> 
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script src="js/areaLocation.js"></script>
	<script src="js/setting.js"></script>
</body>
</html>