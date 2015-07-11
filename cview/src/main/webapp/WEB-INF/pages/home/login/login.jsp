<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/base/base.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link href="<%=path %>/resources/css/home/login/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/resources/js/home/login/login.js"></script>
<script>
$(function(){
	login.init();
});
</script>
</head>
<body>
	<div class="w_990">
		<div class="logo">
			<a> <img
				src="<%=path %>/resources/images/index/banner/logo01.png"
				height="60" width="170">
			</a> <b></b>
		</div>
	</div>
	<div class="content">
		<div class="warp">
			<div class="w_990">
				<div class="login_contain">
					<div class="header" id="header">
						<span class="headline">用户登录</span>
					</div>
					<div class="login">
						<div class="form">
							<ul>
								<li class="sign clearfix">
									<i id="error_Message" class="error_Message"><i class="error_img"></i><i class="error_content" id="error_content">aaaa</i></i>
								</li>
								<li class="user-info name">
									<input class='tipInput' placeholder='用户名/手机号' type="text" name="username" id='username'>
								</li>
								<li class="user-info pwd">
									<input class='password' type="password" id="password" name="password" placeholder="密码">
								</li>
								<li class="user-info verify">
									<input class='verifyCode' id="verifyText" maxlength="4"  type="text" placeholder="验证码">
									<img class="verifyImg" id="verifyImg" onclick="login.refreshVerify()" src="<%=path%>/verifycode/getcode?width=100&height=38&codeCount=4&sessionName=loginVerify&t=<%=Math.random()%>"/>
									<img class="reflush" onclick="login.refreshVerify()" src="<%=path %>/resources/images/index/icon/reflush.png"/>
								</li>
								<li class="enter">
									<input id='btn_enter' type="submit" onclick="login.loginIn();" class="btn" value="" />
								</li>
								<li class="clearfix">
									<span class="auto">
										<input type="checkbox" id="user_name_remember" checked class="check" />记住账号
									</span> 
									<a class="getpwd" href='javascript:void(0);'>忘记密码</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="login_banner" style="background-color:#080860;">
				<div class="w_990">
					<div class="i_inner"
						style="background: url(<%=path %>/resources/images/index/banner/banner01.jpg) 0px 0px no-repeat; background-color:  #070960;"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="w_990">
		<div class="copyright">
            Copyright©2004-2015&nbsp;&nbsp;北京积分系统&nbsp;版权所有
        </div>
	</div>
</body>
</html>

