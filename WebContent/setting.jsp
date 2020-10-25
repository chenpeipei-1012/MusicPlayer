<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>个人设置</title>
	<link type="text/css" rel="styleSheet"  href="css/head.css" />
	<link type="text/css" rel="styleSheet"  href="css/setting.css" />
	<link type="text/css" rel="styleSheet"  href="css/foot.css"/>
	<style>
		html{
			height:100%;
			width:100%;
		}
		body{
			height:100%;
			overflow: hidden;
			box-sizing: border-box;
			padding-top: 75px;
			padding-bottom: 50px;
		}
	</style>
</head>
<body>
	<div class="head">
		<div class="head-top">
			<div class="wrapper">
				<div class="player-wrap">
					<img src="image/logo2.png" class="logo"/>
					<span>音乐播放器</span>
				</div>    
				<ul class="menu">
					<li id="findMusic" onMouseOver="blueMenu(this.id);" onMouseOut="moveMenu(this.id);">
						<a href="index.html">发现音乐</a>
					</li>
					<li id="myMusic" onMouseOver="blueMenu(this.id);" onMouseOut="moveMenu(this.id);">
						<a href="playlist.jsp">我的音乐</a>
					</li>
				</ul>
				<div class="search-wrap">
					<div class="search-box"> 
						<img src="image/search.png" class="search-image"/>
						<input type="text" name="search" id="search" placeholder="music"/>
					</div>
			    </div>
				<div class="userinfo-wrap">
					<div class="image-wrap" id="image-wrap"  >
						<img src="image/picture.jpg" class="user-image"/>
					</div>
					<div id="userinfo-list-wrap" class="userinfo-list-wrap" >
						<ul>
							<li id="li-setting">
								<a href="setting.jsp">
									<img src="image/set.png"/>
									<span>个人设置</span>
								</a>
							</li>
							<li id="li-logout" >
								<a href="index.html">
									<img src="image/logout.png"/>
									<span>退出登录</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
		    </div>
		</div>
	    <div class="head-line"></div>
	</div>
	<iframe src="settingContent.jsp" class="set-content"></iframe>
	<div class="m_player">
		<div class="wrap">
			<div class="btns">
				<div class="btns-wrap">
					<a href="#" class="before" title="上一首"></a>
					<a href="#" class="play-btn" title="播放"></a>
					<a href="#" class="next" title="下一首"></a>
			    </div>
			</div>
			<div class="head-pic">
				<img src="image/default_music_pic.png">
			</div>
			<div class="play"></div>
			<div class="save">
				<img src="image/button_save.png">
			</div>
			<div class="ctrl">
				<img src="image/button_voice.png">
				<img src="image/button_while.png">
			</div>
		</div>
	</div>

	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script src="js/head.js"></script>
</body>
</html>