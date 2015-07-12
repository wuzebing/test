var setting = {
	data : { // 必须使用data
		simpleData : {
			enable : true,
			idKey : "funcId", // id编号命名 默认
			pIdKey : "parentId", // 父id编号命名 默认
			rootPId : "0"
		// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
		}
	},
	callback: {
		onClick: zTreeOnClick
	},
	check: {
		nocheckInherit: true,
		chkStyle: "radio",
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y" : "ps", "N" : "s"}
	},
	callback:{
        onCheck:onCheck
    }
};

var treeNodes;
//var treeNodes = [ 
//{"id" : 1,"pId" : 0,"name" : "test1","urlForPage":"www.aaa","remark":"哈哈"},
//{"id" : 11,"pId" : 1,"name" : "test11","urlForPage":"www.aaa","remark":"哈哈"}, 
//{"id" : 12,"pId" : 1,"name" : "test12","urlForPage":"www.aaa","remark":"哈哈"}, 
//{"id" : 111,"pId" : 11,"name" : "test111","urlForPage":"www.aaa","remark":"哈哈"} ];
var zTreeObj;

function init(){
	queryMenusByRoleId();
	zTreeObj = $.fn.zTree.init($("#tree"), setting, treeNodes);
	expandAll();
}

function queryMenusByRoleId(){
	$.ajax({
		url : kview.path + "/admin/manage/role/assignPermission/query",
		type : "post",
		cache : false,
		async : false,
		dataType : "json",
		data : {
			roleId : roleID
		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			if (data.statusCode == 200) {
				var str = JSON.stringify(data.returnObj);
				var reg = new RegExp("isAssign","g");
				str = str.replace(reg, "checked");
				var str1 = JSON.parse(str);
				treeNodes = str1;
			}else{
				myBoxy.alert("error", returnObj);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function UnExpandAll(){
	zTreeObj.expandAll(false);
}

function expandAll(){
	zTreeObj.expandAll(true);
}

function onCheck(e,treeId,treeNode){
    
}

function submitInfo(){
    var nodes=zTreeObj.getCheckedNodes(true);
    var v="";
    for(var i=0;i<nodes.length;i++){
    v+=nodes[i].funcId + ",";
    }
//    alert(v); //获取选中节点的值
    $.ajax({
		url : kview.path + "/admin/manage/role/assignPermission/save",
		type : "post",
		cache : false,
		async : false,
		dataType : "json",
		data : {
			roleId : roleID,
			menus : v
		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			if (data.statusCode == 200) {
				myBoxy.alert("success", "权限分配成功");
				$("#right_content").load(
						kview.path + "/admin/manage/role/select");
			}else{
				myBoxy.alert("error", returnObj);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function goback(){
	$("#right_content").load(
			kview.path + "/admin/manage/role/select");
}

function zTreeOnClick(){
	
}

$(function(){
	init();
});