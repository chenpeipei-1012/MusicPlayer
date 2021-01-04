<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="styleSheet"  href="./css/head.css" />
	<link type="text/css" rel="styleSheet"  href="./css/foot.css"/>
	<link type="text/css" rel="styleSheet"  href="./css/footer.css"/>
	<link type="text/css" rel="styleSheet"  href="./css/display.css" />
	<link type="text/css" rel="styleSheet"  href="/MusicPlayer/css/pop.css" />
</head>
<body class="body">
		
	<!--头部-->
	<div class="head">
			<%@ include file="../header.jsp"%>
	</div> 
	<div class="content">
	<!--歌曲展示区-->
		<div class="content-wrap" id="container">		
		<div class="upon">
			<!--显示专辑封面-->
			<div class="showalbum">
				<img id="songphoto" src="/MusicPlayer/image/109951165541304779.jpg" class="j-img"
				style="position: absolute;
				display: block;
						height: 130px;
						width: 130px;
						margin:34px;
						border: 0;
						z-index: 2;">
				<img src="/MusicPlayer/image/overall.png"  data-src="/MusicPlayer/image/唱片.jpg" 
				style="position: absolute;width: 206px; height: 206px;z-index: 2;">
			
			</div>
			<!--歌曲详情展示区-->
			<div class="background">
				<div id="" class="song">
					<i class="mn-icn"></i>
					<span id="mname"></span>
				</div>
				<div class="fix_texture">
					<p style="color: #333333;
						height: 20px;
						margin-top: 0px;
						margin-bottom: 0px;">歌手：
						<span id="singer" title="是七叔呢" class="fix_texture">
							<a class="fix_texture" id="mauthor"></a>
						</span>
					</p>
				</div>
				<div class="singer">
					<p  class="fix_texture">所属专辑：
					<a style="color: #333333;" id="malbum"></a>
					</p>
				</div>
				
				<!-- 操作按钮 -->
				<div class="t_operator">
					<a class="list-play" id="one-play">
						<i class="list-play-bg">
							<em class="list-play-icon"></em>
							<span class="icon-font">播放</span>
						</i>
					</a>
					<a id="one-save">
						<i class="save-icon" id="save-icon">收藏</i>
					</a>
				</div>
				<div id="lyric-content" class="lrc" data-song-id="1804320463" data-third-copy="false" ,="" copy-from="">
					<div id="lyric-part">
						
					</div>

					<!-- 更多 -->
					<div id="flag_more" class="f-hide">
						
					</div>
					<div class="crl">
						<a class="flag_ctrl" href="javascript:void(0)">
							<span id="crl-tip">展开</span>
							<i class="u-icn"></i>
						</a>
					</div>	
					</div>
				</div>
			<div class="side">
				<div class="suggestion" >
					<b>推荐相似歌曲</b>
				</div>
				<!-- 歌曲列表 -->
				<ul id="recommend-song" style="list-style: none;">
				
				</ul>
			</div>
		</div>
			
		
		<!--评论区-->
		<div class="bott">
			<!--评论标题-->
			<div class="commentitle">
			<h3><span class="">评论</span></h3>
			</div>
			<div class="mcom">
				<!--用户头像-->
				<div class="comholder">
					<div id="tx" class="touxiang">
					<%@ page import="entity.*" %>
					<%
						String url = "/MusicPlayer/image/default_avatar.jpg";
						if(user != null){
							url = "/MusicPlayer/" + user.getUserPic();
						}
					%>
						<img id="user-pic" src=<%=url %>  width="100%" height="100%">
					</div>
					<!--评论框-->
					<div class="com-wrap">
						<textarea  id="comment" class="block" placeholder="评论" id="auto-id-2FTxFN1VQvwuMCrl" style="    width: 800px;
   height: 50px;"></textarea>
					</div>
				</div>
				<div class="cbtn-wrap">
						<a class="com-btn" id="addComment">评论</a>
				</div>
			</div>
					
				
				<!--精彩评论-->
				<h3 class="u-hd4">最新评论</h3>
				<div class="comment-wrap" id="comment-wrap"></div>
					
				
				
				
				
			</div>
		</div>
		
		
		</div>
		</div>
		
	</div>
	<div class="foot">
		<%@ include file="../foot.html"%>
	</div>

	<div id="pop-content"></div>
</body>
	<script src="./js/jquery-1.8.3.js"></script>
	<script src="./js/head.js"></script>
	<script src="./js/foot.js"></script>
	<script src="./js/display.js"></script>
	<script src="./js/pop.js"></script>
	<script>
		var music = <%=request.getAttribute("music") %>;
		initDisplayPage(music);
	</script>
</html>
