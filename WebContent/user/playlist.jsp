<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>余音播放器</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="styleSheet"  href="/MusicPlayer/css/head.css" />
	<link type="text/css" rel="styleSheet" href="/MusicPlayer/css/playlist.css"/>
	<link type="text/css" rel="styleSheet" href="/MusicPlayer/css/foot.css"/>
	<link type="text/css" rel="styleSheet"  href="/MusicPlayer/css/pop.css" />
	<style>
		/* 内容样式 */
		*{
			font-size: 14px;
			margin: 0;
			padding: 0;
			text-decoration: none;
			outline: none;
			font-style: normal;
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

<body style="overflow-y: hidden;">
	<div class="head">
	<%@ include file="../header.jsp"%>
	</div>
	<div class="content">
		<div id="container">
			<div class="content-wrap">
				<div class="content-left">
					<div class="left-warp">
						<h2 id="oper-music-list">
							<div class="created-list" onClick="operMusicList()">
								<img src="/MusicPlayer/image/icon_open.png" id="file-img" />
								<span class="font-list">创建的歌单</span>
							</div>
							
							<a class="creat" onClick="createNewMList()">
								<span class="create-img"></span>
								<i>新建</i>
							</a>
						</h2>
						<ul id="music-file-list">
							<li class="selected love" onClick="choiceMusicList(this)" id="">
								<div class="left-item">
									<div class="left-item-image">
										<img src="/MusicPlayer/image/love_list_pic.png">
									</div>
									<div class="left-item-text">
										<div class="desc-item">我喜欢的音乐</div>
										<div class="num-item">0首</div>
									</div>
								</div>
							</li>
							<li class="no-selected default" onClick="choiceMusicList(this)" id="">
								<div class="left-item">
									<div class="left-item-image">
										<img src="/MusicPlayer/image/default_save_pic.png">
									</div>
									<div class="left-item-text">
										<div class="desc-item">默认收藏</div>
										<div class="num-item">0首</div>
									</div>
								</div>
							</li>
						</ul>
						
					</div>
				</div>
				<div class="content-right" id="content-right">
					<div class="content-right-top">
						<div class="t-warp">
							<div class="t-warp-img">
								<img id="musiclist-pic" src="/MusicPlayer/image/love_list_pic.png"/>
							</div>
							<div class="t-warp-content">
								<div class="t_name">
									<img src="/MusicPlayer/image/icon_music.png"/>
									<h2 id="r-mlistname"></h2>
								</div>
								<div class="t_user">
									<% User curUser =(User)session.getAttribute("user");
									   String userName = "";
									   String userPic = "/MusicPlayer/image/picture.jpg";
									   if(curUser != null){ 
										   userName = curUser.getUserName();
										   userPic = "/MusicPlayer/" + curUser.getUserPic();
									   }%>
									<img src=<%=userPic %> >
									<span class="font" id="ml-username"><%=userName %></span>
									<span class="font" id="created-time"></span>
									<span class="font">创建</span>
								</div>
								<div class="t_operator">
								</div>
							</div>
						</div>
					</div>
					<div class="content-right-bottom">
						<div class="b_name">
							<span class="b_name_font">歌曲列表</span>
							<span class="font-num" id="music-num">0首歌</span>
						</div>
						<hr class="b_line"/>
						<div class="table-warp" id="table-warp">
							<table class="m-table">
								 <thead>
									<tr>
									  <th class="w1"></th>
									  <th class="w2"></th>
									  <th class="w3">歌曲标题</th>
									  <th class="w4">时长</th>
									  <th class="w5">歌手</th>
									  <th class="w6">专辑</th>
									</tr>
								</thead> 
								<tbody id="mymusiclist">
									
								  </tbody>
								</table>
						</div>
					</div>
				</div>
			</div>
			
			
		    
		</div>
	</div>
    <div class="foot">
    	<%@ include file="../foot.html"%>
    </div>

	<div id="pop-content"></div>
    
    <script src="/MusicPlayer/js/jquery-1.8.3.js"></script>
    <script src="/MusicPlayer/js/head.js"></script>
	<script src="/MusicPlayer/js/foot.js"></script>
	<script src="/MusicPlayer/js/playlist.js"></script>
	<script src="/MusicPlayer/js/pop.js"></script>

</body>
</html>