var pageNo = 1;
var pageSize = 10;

function init() {
	initPaginator("paginator", queryRoles);
	queryRoles();
}

function queryRoles() {
	$("#roleGrid").empty();
	var roleName = $("#roleName").val();
	$.ajax({
		url : kview.path + "/admin/manage/role/queryRoles",
		type : "post",
		cache : false,
		async : true,
		dataType : "json",
		 data : {
		  roleName: roleName
		 },
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			var returnObj = data.returnObj;
			if (data.statusCode == 200) {
				var array = returnObj.result;
				for ( var i in array) {
					var obj = array[i];
					addElementToList(obj);
				}
				// 分页
				setPaginator(returnObj.pageNo, returnObj.totalPages,
						returnObj.totalCount);
			} else {
				alert("查询失败");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function searchByRoleName(){
	queryRoles();
}

function addElementToList(obj) {
	var html = "<tr><td>" + obj.jobName + "</td><td>" + obj.adminName
			+ "</td><td>" + obj.remark + "</td><td>" + obj.adminTime
			+ "</td><td><a href='javascript:void(0)' onclick='grant(\""
			+ obj.jobId
			+ "\")'>菜单配置</a><a href='javascript:void(0)' onclick='gotoUserInfoPage(\""
			+ obj.jobId
			+ "\")'>分配角色</a></td></tr>";
//	+ "\")'>分配权限</a><a href='javascript:void(0)' onclick='deleteRole(\""
//	+ obj.jobId + "\")'>删除</a></td></tr>";
	$("#roleGrid").append(html);
}

function gotoUserInfoPage(id){
	$("#right_content").load(
			kview.path + "/admin/manage/role/userRolePage?roleID="
					+ id);
}

function deleteRole(id){
	alert(id);
}

function grant(id) {
	$("#right_content").load(
			kview.path + "/admin/manage/role/assignPermissionsPage?roleID="
					+ id);
}

$(function() {
	init();
});