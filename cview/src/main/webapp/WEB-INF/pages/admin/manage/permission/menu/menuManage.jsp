<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%
	/**
	 * Description: 管理员-角色管理-功能菜单
	 * Author: ganghh
	 * Since: 2015-01-27
	 * Update:
	 * Copyright 2015, 
	 */
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国电信码上天翼平台</title>
<meta name="description" content="中国电信码上天翼平台，扫码购机" />
<meta name="Keywords" content="码上天翼平台，扫码购机">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/common/inputText.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/common/searchArea.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/common/frameworks/validform/css/validform.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/common/widgets/message/message.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/common/form.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/admin/manage/permission/menu/menuManage.css" />
<script type="text/javascript"
	src="<%=path%>/resources/js/common/frameworks/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/menu/menuManage.js"></script>
<link rel="stylesheet"
	href="<%=path%>/resources/css/admin/manage/permission/role/zTreeStyle/zTreeStyle.css"
	type="text/css">

</head>
<body>
	<div class="partnertitle">
		<span class="f_l partnertitletitbg"></span>
		<h2 class="f_l partnertitletit">功能菜单管理</h2>
		<div class="f_r crumbs">
			<span>当前位置：</span><a>首页&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;</a>功能菜单管理
		</div>
	</div>
	<div id="panel">
		<table class="treeTable">
			<tr class="treeNodeInfoTr">
				<td class="treeTD" valign=top>
					<div>
						<ul id="tree" class="ztree"></ul>
					</div>
				</td>
				<td class="treeNodeInfoTD" valign=top>
					<form id="menuInfoForm" enctype="multipart/form-data" class="menuInfoForm">
						<table class="form">
							<tr class="treeNodeInfoTr">
								<td class="formField Validform_label">菜单名称：</td>
								<td class="formValue"><input type="text" class="formInput"
									name="menuName" datatype="s6-18" id="menuName" /></td>
								<td class="formMark">*</td>
								<td class="formMsg"></td>
							</tr>
							<tr class="treeNodeInfoTr">
								<td class="formField Validform_label">菜单URL：</td>
								<td class="formValue"><input type="text" class="formInput"
									name="menuURL" datatype="s6-18" id="menuURL" /></td>
							</tr>
							<tr class="treeNodeInfoTr">
								<td class="formField Validform_label">备注信息：</td>
								<td class="formValue"><input type="text" class="formInput"
									name="menuRemark" id="menuRemark" /></td>
							</tr>
							<tr id="parentNode" class="treeNodeInfoTr"></tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<div style="text-align: left; margin-left: 50px">
			<input class="save" type="button" value="新增" id="addButton"
				onclick="addMenu()" /> <input class="save" type="button" value="修改"
				id="editButton" onclick="editMenu()" /> <input class="save"
				type="button" value="删除" id="deleteButton" onclick="deleteMenu()" />
			<div id="buttons"></div>
		</div>
	</div>
</body>
</html>
