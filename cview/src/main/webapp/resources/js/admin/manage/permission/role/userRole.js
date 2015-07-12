var pageNo = 1, pageSize = 10;

function init() {
	initPaginator("paginator", queryUserInfo);
	queryUserInfo();
}

function queryUserInfo() {
	$("#userGrid").empty();
	var userName = $("#userName").val();
	$.ajax({
		url : kview.path + "/admin/manage/role/userInfo/query",
		type : "post",
		cache : false,
		async : true,
		dataType : "json",
		data : {
			userName : userName,
			pageNo : pageNo,
			pageSize : pageSize
		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			var returnObj = data.returnObj;
			if (data.statusCode == 200) {
				var array = returnObj.result;
				for ( var i in array) {
					var obj = array[i];
					addToGrid(obj);
				}
				// 分页
				setPaginator(returnObj.pageNo, returnObj.totalPages,
						returnObj.totalCount);
			} else {
				myBoxy.alert("error",returnObj);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			myBoxy.alert("error",errorThrown);
		}
	});
}

function addToGrid(obj) {
	var html = "<tr><td>" + obj.userName + "</td><td>" + obj.jobName
			+ "</td><td>" + obj.jobTypeName + "</td><td>" + obj.adminTime
			+ "</td><td><a href='javascript:void(0)' onclick='setRole(\""
			+ obj.userId
			+ "\")'>赋权</a></td></tr>";
	$("#userGrid").append(html);
}

function setRole(userId){
	$.ajax({
		url : kview.path + "/admin/manage/role/userInfo/save",
		type : "post",
		cache : false,
		async : true,
		dataType : "json",
		data : {
			userId : userId,
			roleId : roleID
		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			var returnObj = data.returnObj;
			if (data.statusCode == 200) {
				myBoxy.alert("success","分配成功");
				init();
			} else {
				myBoxy.alert("error",returnObj);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

$(function() {
	init();
});