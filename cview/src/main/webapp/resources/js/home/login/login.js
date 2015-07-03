$(function() {
	var user_name_remember = $.cookie("user_name_remember");
	if(!(user_name_remember == null || user_name_remember == undefined || user_name_remember == "" || user_name_remember == "-1")){
		$("#username").val(user_name_remember);
		$("#user_name_remember").attr("checked",'true');
	}else{
		$("#user_name_remember").removeAttr("checked");
	}
	document.onkeydown=function(event){ 
        e = event ? event :(window.event ? window.event : null); 
        if(e.keyCode==13){ 
            //执行的方法  
        	$("#verifyText").blur();
        	$("#loginIn").click();
        } 
    }; 
	// 校验图片点击事件
	$("#verifyImg").click(function() {
		refreshVerify();
	});
	$("#verifyText").blur(function() {
		verifyTextVerify(this);
	});
});

// 刷新验证码
function refreshVerify() {
	alert(321);
	debugger;
	var url = cview.path
			+ "/verifycode/getcode?width=87&height=34&codeCount=4&sessionName=loginVerify&it="
			+ Math.random();
	$("#verifyImg").attr("src", url);
}
function verifyTextVerify(obj){
	var verifyCode = $(obj).val();
	$.ajax({
		url : cview.path + "/loginPage/validateVerify",
		type : "post",
		cache : false,
		async : false,
		dataType : "json",
		data : {
			verifyCode : verifyCode
		},
		traditional : true,
		success : function(data, textStatus) {
			if (data == true || data == "true") {
				$("#error_Message").css("display", "none"); 
			} else {
				$("#error_Message").html("验证码不正确").css("display", "inline"); 
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("请求失败");
		}
	});
}

//登录
function loginIn() {
	$.ajax({
		url : cview.path + "/loginPage/user/login",
		type : "post",
		cache : false,
		async : false,
		dataType : "json",
		data : {
			username : $("#username").val(),
			password : $("#password").val(),
			verifyCode : $("#verifyText").val()
		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			if (data.statusCode == 200) {
				$.cookie("token", data.returnObj, {
					expires : 1,
					path : '/'
				});
				//互联网注册不允许登录
				/*var token = getToken();
				if(token != null && token.userAttribute == "15"){
					$.cookie("token", data.returnObj, {
						expires : -1,
						path : '/'
					});
					alert("非激活码用户暂时不提供登录。");
					return;
				}*/
				var checkedFlag = document.getElementById( "user_name_remember" ).checked;
				if(checkedFlag){
					$.cookie("user_name_remember", $("#username").val());
				}else{
					$.cookie("user_name_remember","");
				}
				var locationUrl = "";
				if(kurl){
					locationUrl = "?kurl="+kurl;
				}
				window.location.href = cview.path + "/loginPage/home/loginIn"+locationUrl;
			} else {
				$("#error_Message").html(data.returnObj).css("display", "inline"); 
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}
