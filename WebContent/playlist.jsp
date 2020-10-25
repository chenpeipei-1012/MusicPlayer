<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>歌单列表</title>
	<link type="text/css" rel="styleSheet"  href="css/head.css" />
	<link type="text/css" rel="styleSheet" href="css/playlist.css"/>
	<link type="text/css" rel="styleSheet" href="css/foot.css"/>
	<link type="text/css" rel="styleSheet"  href="css/pop.css" />
	<style>
		/* 内容样式 */
		html{
			height:100%;
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
<body style="overflow-y: hidden;">
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
	<div class="content">
		<div class="content-wrap">
			<div class="content-left">
				<div class="left-warp">
					<h2 id="oper-music-list">
						<div class="created-list" onClick="operMusicList()">
							<img src="image/icon_open.png" id="file-img" >
							<span>创建的歌单</span>
						</div>
						
						<a class="creat" onClick="createNewMList()">
							<img src="image/new.png"/>
							<i>新建</i>
						</a>
					</h2>
					<ul id="music-file-list">
						<li class="selected" onClick="choiceMusicList(this)" id="love">
							<div class="left-item">
								<div class="left-item-image">
									<img src="image/music_list.jpg">
								</div>
								<div class="left-item-text">
									<div class="desc-item">我喜欢的音乐</div>
									<div class="num-item">3首</div>
								</div>
							</div>
						</li>
						<li class="no-selected" onClick="choiceMusicList(this)" id="list1">
							<div class="left-item">
								<div class="left-item-image">
									<img src="image/music_list.jpg">
								</div>
								<div class="left-item-text">
									<div class="desc-item">新建歌单1</div>
									<div class="num-item">0首</div>
								</div>
							</div>
						</li>
					</ul>
					
				</div>
			</div>
			<iframe id="rightiframe" class="cleft-iframe" src="playListRight.jsp"></iframe>
			
		</div>
		<!-- 设置弹窗 -->
		<div id="pop-bg" class="pop-bg" style="display: none"></div>
		<div id="pop" class="pop" style="display: none">
			<div class="pop-head">
				<span>新建歌单</span>
				<img src="image/close_pop.png" onClick="cancelMList()" />
			</div>
			<div class="pop-content">
				<div class="pop-content-wrap">
					<div class="music-list-name">
						<label>歌单名：</label>
						<input type="text" name="listname" id="listname"/>
					</div>
					<div id="error" class="error" style="display: none">
						<img src="image/error.png"/>
						<span id="error-msg">歌单名不能为空</span>
					</div>
					<p class="ml-tips">可通过“收藏”将音乐添加到新歌单中</p>
					<div class="button-wrap">
						<button type="submit" onClick="createMList()">新建</button>
						<button onClick="cancelMList()" class="cancel">取消</button>
					</div>
				</div>
			</div>
		</div>
		</div>
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
	
	<script src="js/head.js"></script>
	<script src="js/playlist.js"></script>
</body>
</html>