<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/base/base.jsp"%>
<%
	String kurl = request.getParameter("kurl");
	kurl = kurl==null?"":kurl;
	System.out.println(kurl);
%>
<%
	/**
	 * Description: login 
	 * Author: wuzb3
	 * Since: 2014-11-18
	 * Update:
	 * Copyright 2014, 
	 */
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录-码上天翼-中国电信</title>
<meta name="description" content="中国电信码上天翼平台，扫码购机" />
<meta name="Keywords" content="积分平台">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/home/login/login.css" />
<script>
var kurl = "<%=kurl%>";
</script>
<script type="text/javascript"
	src="<%=path%>/resources/js/home/login/login.js"></script>
</head>
<body>
	<div class="top w_1140">
		<div class="f_l logo">
			<a href="<%=path %>/kplanindex.jsp"><img src="<%=path%>/images/k_logo.png" /></a>
		</div>
		<div class="f_r login">
			<span><a href="<%=path %>cindex.jsp">欢迎来到积分平台</a></span><a href="<%=path%>/home/Regist">免费注册</a>|<span><a
				href="javascript:void(0);" onclick="addFavorite(this);">收藏本站</a></span>
		</div>
	</div>
	<div class="login_box">
		<div class="w_1140">
			<div class=" longlogin f_r">
				<h2>
					<span class="china">登录</span><span class="en">/</span>login
				</h2>
				<div class="loginform">
					<label>
						<span class="usernamebg f_l"></span>
						<input type="text" id="username" autocomplete="off" placeholder="手机号码 / 邮箱" class="username f_l" />
					</label> 
					<label>
						<span class="posswordbg f_l"></span>
						<input type="password" id="password" autocomplete="off" placeholder="密码" class="possword f_l" />
					</label> 
					<label>
						<input type="text" id="verifyText" autocomplete="off" maxlength="4" placeholder="验证码" class="valid f_l" />
						<span class="validbg f_l"><img id="verifyImg"
							src="<%=path%>/verifycode/getcode?width=87&height=34&codeCount=4&sessionName=loginVerify&t=<%=Math.random()%>" /></span>
							<a href="javascript:void(0);" class="toggle f_l" onclick="refreshVerify()">看不请，换一张</a>
					</label> 
					<label for="remember_username">
					<input type="checkbox" id="user_name_remember" class="f_l checkbox" />
						<span class="f_l forget">记住账号</span>
						<span class="f_l forget error" id="error_Message"></span>
					</label>
				</div>
				<div class="submit_box">
					<input type="button" value="登 录" id="loginIn" onclick="loginIn()" class="submit" />
				</div>
				<ul class="entries">
					<li class="f_l"><a href="<%=path%>/home/findpass/FindPass">忘记密码？</a></li>
					<li class="f_r">还不是积分会员，<a href="<%=path%>/home/Regist">免费注册</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="foot">版权所有 ©2012 中国电信集团公司 [ 增值电信业务经营许可证
		A2.B1.B2-20090001 ] ICP 证号：京 ICP 备 09031924号 客服电话 4008167189-9</div>

</body>
</html>