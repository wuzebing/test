var zTreeObj;
var setting = {
	data : { // 必须使用data
		simpleData : {
			enable : true,
			idKey : "funcId", // id编号命名 默认
			pIdKey : "parentId", // 父id编号命名 默认
			rootPId : "0",
			name : "funcName"
		// 用于修正根节点父节点数据，即 pIdKey 指定的属性值
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};

function addFolder(){
	alert("添加");
};

var treeNodeName;
var treeNodeurlForPage;
var treeNodeRemark;
var treeMenuType;
var nodeID;
function zTreeOnClick(event, treeId, treeNode){
	 goback();
	treeNodeName=treeNode.name;
	treeNodeurlForPage=treeNode.menuUrl;
	treeNodeRemark=treeNode.remark;
	treeMenuType=treeNode.menuType;
	nodeID= treeNode.funcId;
	setValue();
}


//var treeNodes = [ 
//{"id" : 1,"pId" : 0,"name" : "test1","urlForPage":"www.aaa","remark":"哈哈"},
//{"id" : 11,"pId" : 1,"name" : "test11","urlForPage":"www.aaa","remark":"哈哈"}, 
//{"id" : 12,"pId" : 1,"name" : "test12","urlForPage":"www.aaa","remark":"哈哈"}, 
//{"id" : 111,"pId" : 11,"name" : "test111","urlForPage":"www.aaa","remark":"哈哈"} ];
var treeNodes ;


function init(){
	getTreeInfo();
	$(".formMark").hide();
}

function addMenu(){
	if($("#menuName").val()==""){
		myBoxy.alert("error","请选择一个节点作为父节点进行添加操作");
	}else{
		$(".formMark").show();
		var parentNodeHtml="<td class='formField Validform_label'>上级菜单：</td>"
								+"<td class='formValue'><input type='text' class='formInput'"
									+"name='parentMenu' datatype='*1-20' id='parentMenu' /></td>"
								+"<td class='formMark'>*</td>"
								+"<td class='formMsg'></td>";
	$("#parentNode").append(parentNodeHtml);
	$("#parentMenu").attr("disabled",true);
	$("#parentMenu").val(treeNodeName);
	$("#menuName").val("");
	$("#menuURL").val("");
	$("#menuRemark").val("");
	ableInput();
	hideButtons();
	var buttonHtml="<input class='save' type='button' value='保存' id='addTreeInfoButton' onclick='saveMenu()'/>";
	buttonHtml+="<input class='save' type='button' value='返回' id='backButton' onclick='goback()'/>";
	$("#buttons").append(buttonHtml);
	$("#backButton").css("margin-left","40px");
	$("#backButton").css("margin-top","30px");
	
	}
}

function editMenu(){
	if($("#menuName").val()==""){
		myBoxy.alert("error","请选择一个需要修改的菜单");
	}else{
		$(".formMark").show();
		ableInput();
		hideButtons();
		var buttonHtml="<input class='save' type='button' value='保存' id='saveTreeInfoButton' onclick='updateMenu()'/>";
		buttonHtml+="<input class='save' type='button' value='返回' id='backButton' onclick='goback()'/>";
		$("#buttons").append(buttonHtml);
		$("#backButton").css("margin-left","20px");
		$("#backButton").css("margin-top","80px");
	}
}

function updateMenu(){
	var menuName=$("#menuName").val();
	var menuURL=$("#menuURL").val();
	var remark=$("#menuRemark").val();
		$.ajax({
			url : kview.path + "/admin/manage/menu/updateMenu",
			type : "post",
			cache : false,
			async : false,
			dataType : "json",
			data : {
				menuName : menuName,
				nodeID : nodeID,
				menuUrl : menuURL,
				remark : remark
			},
			traditional : true,// 使用传统方式序列化
			success : function(data, textStatus) {
				if(data.statusCode == 200){
					myBoxy.alert("success","修改成功");
					$("#right_content").load(kview.path + "/admin/manage/menu/queryAllMenus_kpage");
				}else{
					myBoxy.alert("error", data.returnObj);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				myBoxy.alert("error",errorThrown);
			}
		});
}

function deleteMenu(){
	if($("#menuName").val()==""){
		myBoxy.alert("error","请选择一个需要删除的菜单");
	}else{
		var ifdelete=confirm("确定要删除功能菜单： " + treeNodeName + "?");
		if(ifdelete){
			$.ajax({
				url : kview.path + "/admin/manage/menu/deleteMenu",
				type : "post",
				cache : false,
				async : false,
				dataType : "json",
				data : {
					menuID : nodeID
				},
				traditional : true,// 使用传统方式序列化
				success : function(data, textStatus) {
					if(data.statusCode == 200){
						myBoxy.alert("success","删除成功");
						$("#right_content").load(kview.path + "/admin/manage/menu/queryAllMenus_kpage");
					}else{
						myBoxy.alert("error", data.returnObj);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					myBoxy.alert("error",errorThrown);
				}
			});
		}
	}
}

function goback(){
	treeNodeName="";
	treeNodeurlForPage="";
	treeNodeRemark="";
	treeMenuType="";
	nodeID="";
	$(".formMark").hide();
	$("#buttons").empty();
	$("#parentNode").empty();
	disableInput();
	setValue();
	showButtons();
}


function saveMenu(){
	var menuName=$("#menuName").val();
	var menuURL=$("#menuURL").val();
	var remark=$("#menuRemark").val();
	if(menuName==""){
		myBoxy.alert("error","请填写菜单名称");
	}else{
		$.ajax({
			url : kview.path + "/admin/manage/menu/addNewMenu",
			type : "post",
			cache : false,
			async : false,
			dataType : "json",
			data : {
				menuName : menuName,
				treeMenuType: treeMenuType,
				parentID : nodeID,
				menuUrl : menuURL,
				remark : remark
			},
			traditional : true,// 使用传统方式序列化
			success : function(data, textStatus) {
				if(data.statusCode == 200){
					myBoxy.alert("success","添加成功");
					$("#right_content").load(kview.path + "/admin/manage/menu/queryAllMenus");
				}else{
					myBoxy.alert("error", data.returnObj);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				myBoxy.alert("error",errorThrown);
			}
		});
	}
}
$(function() {
	init();
	$("#tree").empty();
	zTreeObj = $.fn.zTree.init($("#tree"), setting, treeNodes);
});

function getTreeInfo() {
	$.ajax({
		url : kview.path + "/admin/manage/menu/queryAllMenus",
		type : "post",
		cache : false,
		async : false,
		dataType : "json",
//		data : {
//			parentID : parentID,
//			token : $.cookie("token")
//		},
		traditional : true,// 使用传统方式序列化
		success : function(data, textStatus) {
			var str = JSON.stringify(data.returnObj);
			var str1 = JSON.parse(str);
			treeNodes = str1;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

/**
 * 禁用输入框
 */
function disableInput(){
	$('#menuName').attr("disabled",true);
	$('#menuURL').attr("disabled",true);
	$('#menuRemark').attr("disabled",true);
}

/**
 * 取消输入框的禁用
 */
function ableInput(){
	$('#menuName').attr("disabled",false);
	$('#menuURL').attr("disabled",false);
	$('#menuRemark').attr("disabled",false);
}

/**
 * 隐藏操作按钮
 */
function hideButtons(){
	$('#addButton').hide();
	$('#editButton').hide();
	$('#deleteButton').hide();
}

/**
 * 显示操作按钮
 */
function showButtons(){
	$('#addButton').show();
	$('#editButton').show();
	$('#deleteButton').show();
}

/**
 * 给标签赋值
 */
function setValue(){
	$("#menuName").val(treeNodeName);
	$("#menuURL").val(treeNodeurlForPage);
	$("#menuRemark").val(treeNodeRemark);
}