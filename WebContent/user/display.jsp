<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link type="text/css" rel="styleSheet"  href="../css/head.css" />
	<link type="text/css" rel="styleSheet"  href="../css/foot.css"/>
	<link type="text/css" rel="styleSheet"  href="../css/footer.css"/>
	<link type="text/css" rel="styleSheet"  href="../css/display.css" />
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
				<img id="songphoto" src="../image/109951165541304779.jpg" class="j-img"
				style="position: absolute;
				display: block;
						height: 130px;
						width: 130px;
						margin:34px;
						border: 0;
						z-index: 2;">
				<img src="../image/overall.png"  data-src="../image/唱片.jpg" 
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
					<b>推荐热歌榜</b>
				</div>
				<!-- 歌曲列表 -->
				<li class="songbar1">
					<span class="lable1" >1</span>
					<a class="songlink" id="s1" href="">我都要赢</a>
				</li>
				<li class="songbar2">
					<span class="lable1" >2</span>
					<a class="songlink" id="s2" href="">我都要赢</a>
				</li>
				<li class="songbar1">
					<span class="lable1" >3</span>
					<a class="songlink" id="s3" href="">我都要赢</a>
				</li>
				<li class="songbar2">
					<span class="lable2" >4</span>
					<a class="songlink" id="s4" href="">我都要赢</a>
				</li>
				<li class="songbar1">
					<span class="lable2" >5</span>
					<a class="songlink" id="s5" href="">我都要赢</a>
				</li>
				<li class="songbar2">
					<span class="lable2" >6</span>
					<a class="songlink" id="s6" href="">我都要赢</a>
				</li>
				<li class="songbar1">
					<span class="lable2" >7</span>
					<a class="songlink" id="s7" href="">我都要赢</a>
				</li>
				<li class="songbar2">
					<span class="lable2" >8</span>
					<a class="songlink" id="s8" href="">我都要赢</a>
				</li>
				<li class="songbar1">
					<span class="lable2" >9</span>
					<a class="songlink" id="s9" href="">我都要赢</a>
				</li>
				<li class="songbar2">
					<span class="lable2" >10</span>
					<a class="songlink" id="s10" href="">我都要赢</a>
				</li>
				<li class="songbar1">
					<span class="lable2" >11</span>
					<a class="songlink" id="s11" href="">我都要赢</a>
				</li>

				
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
						String url = "";
						if(user != null){
							url = "/MusicPlayer/" + user.getUserPic();
						}
					%>
						<img id="user-pic" src=<%=url %> width="100%" height="100%">
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
				<div id="51829862001609074750161" class="itm">
					<div >
						<a  id="u1" href="/user/home?id=629762038">
						<img ass="touxiang" src="https://p1.music.126.net/BB_HxUD8Rdly-xxSsETdqw==/109951165161329042.jpg?param=50y50"></a>
					</div >				
					<div class="comid fix_texture">
						<a  id="u3nm" class="fix_texture u-name" >江东_jc</a>：
						<a id="u3cm"  class="fix_texture">高考这场战役&nbsp;你我都要赢</a>
					</div>				
				</div>
					
				<div id="51829862001609074750161" class="itm" data-id="5182986200">
					<div >
						<a  id="u2" href="/user/home?id=629762038">
						<img class="touxiang" src="https://p1.music.126.net/BB_HxUD8Rdly-xxSsETdqw==/109951165161329042.jpg?param=50y50"></a>
					</div>				
					<div class="comid fix_texture">
						<a  id="u3nm" class="fix_texture u-name" >江东_jc</a>：
						<a id="u3cm"  class="fix_texture">高考这场战役&nbsp;你我都要赢</a>
					</div>			
				</div>
				
				<div id="51829862001609074750161" class="itm" data-id="5182986200">
					<div >
						<a id="u3" href="/user/home?id=629762038">
						<img class="touxiang" src="https://p1.music.126.net/BB_HxUD8Rdly-xxSsETdqw==/109951165161329042.jpg?param=50y50"></a>
					</div>				
					<div class="comid fix_texture">
						<a  id="u3nm" class="fix_texture u-name" >江东_jc</a>：
						<a id="u3cm"  class="fix_texture">高考这场战役&nbsp;你我都要赢</a>
					</div>			
				</div>
				
			</div>
		</div>
		
		
		</div>
		</div>
		
	</div>
	<div class="foot">
		<%@ include file="../foot.html"%>
	</div>

</body>
	<script src="../js/jquery-1.8.3.js"></script>
	<script src="../js/head.js"></script>
	<script src="../js/foot.js"></script>
	<script src="../js/display.js"></script>
	<script>
	<%@ page import="net.sf.json.JSONObject"%>
	$(document).ready(function() {
	      $().ready(function() {
	    	  // 页面加载完毕后执行
	    	  // 拿到music对象
	    	  var music = <%=request.getAttribute("music") %>;
	    	  loadMusicInfo(music);
	      });
    });
	</script>
</html>
