function init() {

}

function submitInfo() {
	var roleName = $("input[name='roleName']").val();
	var roleType = $("#roleType").val();
	var roleRemark = $("input[name='roleRemark']").val();
	$.ajax({
		url : kview.path + "/admin/manage/role/addRole",
		type : "post",
		cache : false,
		async : true,
		dataType : "json",
		data : {
			roleName : roleName,
			roleType : roleType,
			roleRemark : roleRemark
		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			var returnObj = data.returnObj;
			if (data.statusCode == 200) {
				myBoxy.alert("success", "添加成功");
				$("#right_content").load(kview.path + "/admin/manage/role/select_kpage");
			} else {
				myBoxy.alert("error", returnObj);
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