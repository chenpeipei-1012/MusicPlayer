<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link type="text/css" rel="styleSheet" href="css/playlist.css"/>
	<style>
		html{
			width:100%;
			height:100%;
		}
		body{
			width:100%;
			height:100%;
		}
	</style>
</head>
<body>
	<div class="content-right">
		<div class="content-right-top">
			<div class="t-warp">
				<div class="t-warp-img">
					<img src="image/music_list.jpg"/>
				</div>
				<div class="t-warp-content">
					<div class="t_name">
						<img src="image/icon_music.png"/>
						<h2>我的歌单</h2>
					</div>
					<div class="t_user">
						<img src="image/picture.jpg"/>
						<span class="font">super_佩</span>
						<span class="font">2020-10-12 创建</span>
					</div>
					<div class="t_operator">
						<a class="play">
							<button>播放</button>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="content-right-bottom">
			<div class="b_name">
				<span class="b_name_font">歌曲列表</span>
				<span class="font-num">3首歌</span>
			</div>
			<hr class="b_line"/>
			<div class="table-warp">
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
					<tbody>
						<tr id="table-rows-1" onMouseOver="tableRowsOver(this.id)" onMouseOut="tableRowsOut(this.id)">
						  <td class="w1">1</td>
						  <td class="w2">
							  <img src="image/play.png"/>
						  </td>
						  <td class="w3">想起了你</td>
						  <td class="w4">03:36</td>
						  <td class="w5">林俊杰</td>
						  <td class="w6">小时代</td>
						</tr>

						<tr>
						  <td class="w1">1</td>
						  <td class="w2">
							  <img src="image/play.png"/>
						  </td>
						  <td class="w3">想起了你</td>
						  <td class="w4">03:36</td>
						  <td class="w5">林俊杰</td>
						  <td class="w6">小时代</td>
						</tr>
						<tr>
						  <td class="w1">1</td>
						  <td class="w2">
							  <img src="image/play.png"/>
						  </td>
						  <td class="w3">想起了你</td>
						  <td class="w4">03:36</td>
						  <td class="w5">林俊杰</td>
						  <td class="w6">小时代</td>
						</tr>
					  </tbody>
					</table>
			</div>
		</div>
	</div>
</body>
</html>