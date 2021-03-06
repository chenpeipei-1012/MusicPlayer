<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>余音播放器</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="css/index.css" />
	<link type="text/css" rel="styleSheet"  href="css/head.css" />
	<link type="text/css" rel="styleSheet"  href="css/foot.css"/>
	<link type="text/css" rel="styleSheet"  href="css/footer.css"/>
	<link type="text/css" rel="styleSheet"  href="css/pop.css"/>
	<style>
		
	</style>
</head>
<body>
	<div class="head">
		<%@ include file="../header.jsp"%>
	</div> 
	<div class="content">
		<div id="container">
		 <!-- 滚动图片 -->
	    <div class="banner">
	        <div class="img-banner">
	            <ul class="img-list w">
	                <li>
	                    <a href="#">
	                        <img src="./image/1.jpg" alt="">
	                    </a>
	                </li>
	                <li>
	                    <a href="#">
	                        <img src="./image/2.jpg" alt="">
	                    </a>
	                </li>
	                <li>
	                    <a href="#">
	                        <img src="./image/3.jpg" alt="">
	                    </a>
	                </li>
	                <li>
	                    <a href="#">
	                        <img src="./image/4.jpg" alt="">
	                    </a>
	                </li>
	                <li>
	                    <a href="#">
	                        <img src="./image/5.jpg" alt="">
	                    </a>
	                </li>
	                <li>
	                    <a href="#">
	                        <img src="./image/6.jpg" alt="">
	                    </a>
	                </li>
	
	            </ul>
	        </div>
	
	        <div class="prev-next">
	            <a href="javascript:;" class="prev"></a>
	            <a href="javascript:;" class="next"></a>
	        </div>
	
	        <div class="pointer">
	            <a class="active" href="javascript:;"></a>
	            <a href="javascript:;"></a>
	            <a href="javascript:;"></a>
	            <a href="javascript:;"></a>
	            <a href="javascript:;"></a>
	            <a href="javascript:;"></a>
	        </div>
	    </div>
	
	    <div class="index-content">
	        <!-- 内容导航 -->
	        <div class="nav5">
	            <ul class="">
	                <li>
	                    <a href="javascript:;" class="everydayRcom">推荐</a>
	                </li>
	                <li>
	                    <a href="javascript:;" class="privateFM">FM</a>
	                </li>
	                <li>
	                    <a href="javascript:;" class="music-list">歌单</a>
	                </li>
	                <li>
	                    <a href="javascript:;" class="ranking-list">排行</a>
	                </li>
	                <li>
	                    <a href="javascript:;" class="radio-bot">电台</a>
	                </li>
	            </ul>
	
	        </div>
	
	        <div class="down-cont">
	            <!-- 左侧内容 -->
	            <div class="l-content">
	                <!-- 热门歌曲 -->
	                <div class="hotMusicList">
	                    <div class="hotm-nav">
	                        <ul>
	                            <li>
	                                <span class="hotm-nav-1"> 熱門歌單</span>
	                            </li>
	                            <li>
	                                <a href="javascript:;">華語</a>
	                            </li>
	                            <li>
	                                <span>|</span>
	                            </li>
	                            <li>
	                                <a href="javascript:;">流行</a>
	                            </li>
	                            <li>
	                                <span>|</span>
	                            </li>
	                            <li>
	                                <a href="javascript:;">搖滾</a>
	                            </li>
	                            <li>
	                                <span>|</span>
	                            </li>
	                            <li>
	                                <a href="javascript:;">民謠</a>
	                            </li>
	                            <li>
	                                <span>|</span>
	                            </li>
	                            <li>
	                                <a href="javascript:;">電子</a>
	                            </li>
	                        </ul>
	                    </div>
	                    <br>
	                    <div class="first-hotm">
	                        <div class="hotm-1 h">
	                            <img src="./image/hm1.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-1">正青春|风犬少年的天空</a>
	                        </div>
	                        <div class="hotm-2 h">
	                            <img src="./image/hm2.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-2">「立秋」我夏天才过了一下下呢</a>
	                        </div>
	                        <div class="hotm-3 h">
	                            <img src="./image/hm3.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-3">超级治愈英文|纯音乐</a>
	                        </div>
	                        <div class="hotm-4 h">
	                            <img src="./image/hm4.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-4">〔吉他与猫〕当夕阳落在琴弦上</a>
	                        </div>
	                    </div>
	
	                    <div class="secd-hotm">
	                        <div class="hotm-5 h">
	                            <img src="./image/hm5.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-5">泛着梦幻色泽的夏日汽水♡</a>
	                        </div>
	                        <div class="hotm-6 h">
	                            <img src="./image/hm6.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-6">『雾起爱意散』</a>
	                        </div>
	                        <div class="hotm-7 h">
	                            <img src="./image/hm7.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-7">聆听民谣里的专属爱情</a>
	                        </div>
	                        <div class="hotm-8 h">
	                            <img src="./image/hm8.jpeg" alt="">
	                            <br>
	                            <a href="javascript:;" class="hotM-8">月光所照 皆是故乡</a>
	                        </div>
	                    </div>
	
	                </div>
	
	
	                <!-- 单曲排行 -->
	                <div class="soloMusic">
	                    <div class="paihang">
	                        <ul>
	                            <li>
	                                <span>排行榜</span>
	                            </li>
	                            <li class="more">
	                                <a href="javascript:;">更多></a>
	                            </li>
	                        </ul>
	                    </div>
	                    <br>
	                    <div class="xingepaihang">
	                        <div class="xin1">
	                            <span>新歌排行</span>
	                        </div>
	                        <div class="x1 xg">
	                            <a href="javascript:;" class="xinge1 x">Fall</a>
	                        </div>
	                        <div class="x2 xgg">
	                            <a href="javascript:;" class="xinge2 x">my boo</a>
	                        </div>
	                        <div class="x3 xg">
	                            <a href="javascript:;" class="xinge3 x">念想</a>
	                        </div>
	                        <div class="x4 xgg">
	                            <a href="javascript:;" class="xinge4 x">大城小爱(live)</a>
	
	                        </div>
	                        <div class="x5 xg">
	                            <a href="javascript:;" class="xinge5 x">精彩才刚刚开始</a>
	
	                        </div>
	                        <div class="x6 xgg">
	                            <a href="javascript:;" class="xinge6 x">粉雾海</a>
	                        </div>
	                        <div class="x7 xg">
	                            <a href="javascript:;" class="xinge7 x">冷静与热情之间</a>
	
	                        </div>
	                        <div class="x8 xgg">
	                            <a href="javascript:;" class="xinge8 x">你说</a>
	
	                        </div>
	                        <div class="x9 xg">
	                            <a href="javascript:;" class="xinge9 x">陷落美好</a>
	
	                        </div>
	                        <div class="x10 xgg">
	                            <a href="javascript:;" class="xinge10 x">I adore you</a>
	
	                        </div>
	                        <div class="xg more">
	                            <a href="javascript:;" class="x">查看全部></a>
	                        </div>
	
	                    </div>
	                    <div class="meiripaihang">
	                        <div class="xin1">
	                            <span>每日排行</span>
	                        </div>
	                        <div class="x1 xg">
	                            <a href="javascript:;" class="xinge1 x">经济舱</a>
	                        </div>
	                        <div class="x2 xgg">
	                            <a href="javascript:;" class="xinge2 x">I Still Do</a>
	                        </div>
	                        <div class="x3 xg">
	                            <a href="javascript:;" class="xinge3 x">通宵</a>
	                        </div>
	                        <div class="x4 xgg">
	                            <a href="javascript:;" class="xinge4 x">想去海边</a>
	
	                        </div>
	                        <div class="x5 xg">
	                            <a href="javascript:;" class="xinge5 x">爱人错过</a>
	
	                        </div>
	                        <div class="x6 xgg">
	                            <a href="javascript:;" class="xinge6 x">最酷的小孩</a>
	                        </div>
	                        <div class="x7 xg">
	                            <a href="javascript:;" class="xinge7 x">阿司匹林</a>
	
	                        </div>
	                        <div class="x8 xgg">
	                            <a href="javascript:;" class="xinge8 x">我要</a>
	
	                        </div>
	                        <div class="x9 xg">
	                            <a href="javascript:;" class="xinge9 x">他只是经过</a>
	
	                        </div>
	                        <div class="x10 xgg">
	                            <a href="javascript:;" class="xinge10 x">童言无忌(live)</a>
	
	                        </div>
	                        <div class="xg more">
	                            <a href="javascript:;" class="x">查看全部></a>
	                        </div>
	                    </div>
	                    <div class="iTunes">
	                        <div class="xin1">
	                            <span>iTunes榜</span>
	                        </div>
	                        <div class="x1 xg">
	                            <a href="javascript:;" class="xinge1 x">Know</a>
	                        </div>
	                        <div class="x2 xgg">
	                            <a href="javascript:;" class="xinge2 x">25 hours</a>
	                        </div>
	                        <div class="x3 xg">
	                            <a href="javascript:;" class="xinge3 x">Tomboy</a>
	                        </div>
	                        <div class="x4 xgg">
	                            <a href="javascript:;" class="xinge4 x">xoxososo</a>
	
	                        </div>
	                        <div class="x5 xg">
	                            <a href="javascript:;" class="xinge5 x">k</a>
	
	                        </div>
	                        <div class="x6 xgg">
	                            <a href="javascript:;" class="xinge6 x">wave</a>
	                        </div>
	                        <div class="x7 xg">
	                            <a href="javascript:;" class="xinge7 x">Love or Like</a>
	
	                        </div>
	                        <div class="x8 xgg">
	                            <a href="javascript:;" class="xinge8 x">Love somebody</a>
	
	                        </div>
	                        <div class="x9 xg">
	                            <a href="javascript:;" class="xinge9 x">Shame</a>
	
	                        </div>
	                        <div class="x10 xgg">
	                            <a href="javascript:;" class="xinge10 x">Hreatbreak Hotel</a>
	
	                        </div>
	                        <div class="xg more">
	                            <a href="javascript:;" class="x">查看全部></a>
	                        </div>
	
	                    </div>
	                </div>
	
	            </div>
	            <!-- 右侧内容 -->
	            <div class="r-content">
	                <div class="logoin">
	                    <span>登录可以享受无限收藏的乐趣，并且无限同步到手机</span>
	                    <br>
	                    <div class="logoin-1">
	                        <a href="./login.html">请登录/注册</a>
	                    </div>
	
	                </div>
	                <!-- 小众乐队推荐 -->
	                <div class="bandRec">
	                    <div class="yuedui">
	                        <ul>
	                            <li>
	                                <span>乐队推荐</span>
	                            </li>
	                        </ul>
	                    </div>
	                    <div class="band1 b">
	                        <img src="./image/b1.png" alt="" class="b1">
	                        <span>霓虹花园 </span>
	                        <!-- <span class="nnn">无人的海边 </span> -->
	                    </div>
	                    <div class="band2 b">
	                        <img src="./image/b2.jpg" alt="" class="b1">
	                        <span>脆莓乐队</span>
	                    </div>
	                    <div class="band3 b">
	                        <img src="./image/b3.jpeg" alt="" class="b1">
	                        <span>fine乐团</span>
	                    </div>
	                    <div class="band5 b">
	                        <img src="./image/b5.jpeg" alt="" class="b1">
	                        <span>C-Block</span>
	                    </div>
	                </div>
	                <!-- 歌手推荐 -->
	                <div class="singerRec">
	                    <div class="yuedui">
	                        <ul>
	                            <li>
	                                <span>歌手推荐</span>
	                            </li>
	                        </ul>
	                    </div>
	                    <div class="b">
	                        <img src="./image/s1.jpg" alt="">
	                        <span>易烊千玺</span>
	                    </div>
	                    <div class="b">
	                        <img src="./image/s2.jpg" alt="">
	                        <span>周杰伦</span>
	                    </div>
	                    <div class="b">
	                        <img src="./image/s3.jpg" alt="">
	                        <span>林俊杰</span>
	                    </div>
	                    <div class="b">
	                        <img src="./image/s4.jpg" alt="">
	                        <span>陈粒</span>
	                    </div>
	
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <!-- 版权信息 -->
	    <div class="g-ft ">
        <div class="m-ft">
            <div class="wrap f-cb">
                <div class="copy">
                    <p class="link" id="music-link">
                        <a href="javascript:;" target="_blank" class="item s-fc4">服务条款 </a><span class="line">|</span>
                        <a href="javascript:;" target="_blank" class="item s-fc4">隐私政策 </a><span class="line">|</span>
                        <a href="javascript:;" target="_blank" class="item s-fc4">儿童隐私政策 </a><span class="line">|</span>
                        <a href="javascript:;" target="_blank" class="item s-fc4">版权投诉指引 </a><span class="line">|</span>
                        <a id="g_feedback" href="#" class="item s-fc4" onclick="nm.x.feedback();return false;"
                            hidefocus="true">意见反馈</a>
                        <span class="line">|</span><a class="s-fc4"></a></p>
                    <p class="right s-fc3">
                        <span class="sep span">123公司版权所有©2020-2020 </span><span class="span">湖南香蕉科技有限公司运营：</span><a
                            href="javascript:;" target="_blank" class="alink s-fc3">湘网文[2018]3506-263号</a>
                    </p>
                    <p class="contact s-fc3">
                        <span class="sep span">违法和不良信息举报电话：0731-123456789</span>
                        <span class="span">举报邮箱：</span><a class="alink" href="javascript:;">123456@123.com</a>
                    </p>
                    <p class="right s-fc3">
                        <span class="sep span">湘B2-20090191-18</span> <a href="javascript:;" rel="noopener noreferrer"
                            target="_blank" class="alink s-fc3">工业和信息化部备案管理系统网站</a>
                        <a href="javascript:;" rel="noopener noreferrer" target="_blank"
                            class="alink s-fc3 police-link">
                            <span class="police-logo"></span>
                            <span class="police-text">湘公网安备 123456789123456号</span>
                        </a>
                    </p>
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
	
	
	<script src="js/jquery-1.8.3.js"></script>
	<script src="js/head.js"></script>
	<script src="js/foot.js"></script>
	<script src="js/index.js"></script>
	<script src="js/pop.js"></script>
</body>
</html>