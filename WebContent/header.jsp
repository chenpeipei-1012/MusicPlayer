<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="hwrap">
	<div class="logo"></div>
	<div class="menu">
		<ul>
			<li class="nav nav-bg" id="find"><a href="#">发现音乐</a></li>
			<li class="nav nav-bg" id="mymusic"><a href="javascript:void(0);" id="myMusic">我的音乐</a></li>
		</ul>
	</div>
	<div class="search">
		<div class="swrap">
			<span class="search-pic"></span>
			<input class="search-input fcolor" type="text" placeholder="music" />
		</div>
	</div>
	<div class="userpic">
		<!-- 第一种情况：未登录，显示登录   -->
		<%@ page import="entity.*" %>
		<%
		 	User user =(User)session.getAttribute("user");
			if(user == null){
		%>
        <a href="javascript:void(0);" class="login">登录</a>	
        <%  
        	}else{
        		String url = "/MusicPlayer/" + user.getUserPic();
        %>	
		<!-- 第二种情况：已登录，显示用户头像-->
		<div class="profile" id="profile" style="background: url(<%=url %>) no-repeat center center;background-size: cover;"></div>  
	    <div id="u-list" class="u-list" >
			<div class="arr"></div>
			<ul class="u-ctrl">
				<li class="u-item nav-bg">
					<div class="set pic"></div>
					<div class="word-item"><a href="/MusicPlayer/user/setting.jsp" class="u-font">个人设置</a></div>
				</li>
				<li class="u-item nav-bg">
					<div class="logout pic"></div>
					<div class="word-item"><a href="/MusicPlayer/logout" class="u-font" id="logout">退出</a></div>
				</li>
			</ul>
		</div>
		<%
        	}
		%>
	</div>
</div>
<div class="hline"></div>