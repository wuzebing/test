<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
 /**
  * Description: base.jsp every page should reference the page
  * Author: wuzb3
  * Since: 2014-11-18
  * Update:
  * Copyright 2014, 
  */
%>
<%
	String path = request.getContextPath();
%>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="no-cache, width=device-width, maximum-scale=2.0">
<%-- 网站左侧图标需要ico图片 --%>
<%-- <link rel="shortcut icon" href="<%=path %>/images/ico/favicon.ico" type="image/x-icon" /> --%>

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/base/base.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/base/foot.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/css/base/head.css" />

<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/common/plugin/myJquery.page.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/common/plugin/myJquery.select.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/resources/js/common/plugin/myJquery.boxy.css" />
<script type="text/javascript" src="<%=path%>/resources/js/common/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/jquery/jquery.form.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/plugin/myJquery.Placeholder.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/plugin/myJquery.page.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/plugin/myJquery.select.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/plugin/myJquery.boxy.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/common/menuController.js"></script>
<script type="text/javascript">
	var cview = {
		path: "<%=path%>"
	};
	$(function(){
		//getToken();//获取token的方法，解决token失效时间前后台不一致，如果一直可以删除该方法，但是在三个head中加载该方法
		//checkLogin();//如果token失效时间和后台不一致则需要调用getToken()
<%-- 		Boxy.ask("请1秒种回答，林志玲是不是你的女朋友？", {"你竟然选择了是！请问你是？":"是", "你选择了不是。":"不是"}, function(r) { },{title:"信息提示框",closeable:true});
		myBoxy.alert("tip","请求参数为空，系统异常！");  --%>
		<%-- 	所有input框输入完后移走执行清空两侧空格  --%>
		$("input").blur(function(){
			$(this).val($.trim($(this).val()));
		});
	});
	
	<%--弹出提示框--%>
	var myBoxy = {
			alert:function(type,text,callback){
				if(type == "error"){
					Boxy.alert(
							"<div class='boxy_content'><img src="+cview.path+"/js/common/plugin/myJqueryBoxyImages/error.png /><div class='fontdiv'>"+
							text+"</div></div>",callback,{title:"信息提示框",closeable:true}
						  );
				}else if(type == "success"){
					Boxy.alert(
							"<div class='boxy_content'><img src="+cview.path+"/js/common/plugin/myJqueryBoxyImages/success.png /><div class='fontdiv'>"+
							text+"</div></div>",callback,{title:"信息提示框",closeable:true}
						  );
				}else if(type == "tip"){
					Boxy.alert(
							"<div class='boxy_content'><img src="+cview.path+"/js/common/plugin/myJqueryBoxyImages/tip.png /><div class='fontdiv'>"+
							text+"</div></div>",callback,{title:"信息提示框",closeable:true}
						  );
				}
				return false;
			}
	};
	<%--获取token的方法 */--%>
	function getToken(){
		var _token = $.cookie("token");
		if(!(_token == null || _token == undefined || _token == "" || _token == "-1")){
			$.ajax({
				url : cview.path+"/base/getToken",
				type : "post",
				cache : false,
				async : false,
				dataType : "json",
				data: {
				},
				traditional: true,
				success : function(data, textStatus){
					if(data.statusCode == 200){
						cview.token = data.returnObj;
					}else{
						cview.token = null;
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown){
					Boxy.alert(errorThrown);
					cview.token = null;
				}
			});
		}else{
			cview.token = null;
		}
		return cview.token;
	}
	
	<%--检测用户已登陆或登陆超时，页面初始化或调action之前需要调用此方法 */--%>
	function checkLogin(){
		var token = $.cookie("token");
		if(token == null || token == undefined || token == "" || token == "-1"){
			Boxy.alert("登陆过期，请您重新登陆!");
			locationLogin();
		}
	}
	
	<%--退出--%>
	function exitSystem(){
		$.cookie("token","-1",{expires: 1, path: '/'});
		window.location.href = cview.path + "/kplanindex.jsp";
	}
	
	<%--跳转登录页面--%>
	function locationLogin(url){
		var locationUrl = "";
		if(url){
			locationUrl = "?kurl="+url;
		}
		window.location.href = cview.path + "/homePage/home/login"+locationUrl;
	}
	
	<%--处理用户登录后访问的权限--%>
	function verifyUserType(theFirstChar){
		// TODO 该方法以后可以去掉，为了防止其他地方有引用报错，暂时保留该方法
	}
	<%--收藏本站--%>
	function addFavorite(obj, opts){
	    var _t, _u;
	    if(typeof opts != 'object'){
	        _t = document.title;
	        _u = location.href;
	    }else{
	        _t = opts.title || document.title;
	        _u = opts.url || location.href;
	    }
	    try{
	        window.external.addFavorite(_u, _t);
	    }catch(e){
	        if(window.sidebar){
	            obj.href = _u;
	            obj.title = _t;
	            obj.rel = 'sidebar';
	        }else{
	        	Boxy.alert('抱歉，您所使用的浏览器无法完成此操作。\n\n请使用 Ctrl + D 将本页加入收藏夹！');
	        }
	    }
	}
</script>
<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "//hm.baidu.com/hm.js?f45441cee00e4b134487672ab2866012";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
</script>
