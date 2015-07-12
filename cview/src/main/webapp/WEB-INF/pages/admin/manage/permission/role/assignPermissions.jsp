<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/base/base.jsp"%>
<%
	String roleID = request.getParameter("roleID");
%>
<%
	/**
	 * Description: 管理员-角色管理-分配权限
	 * Author: ganghh
	 * Since: 2015-02-04
	 * Update:
	 * Copyright 2015, 
	 */
%>
<html>
<head>
<script type="text/javascript">
	var roleID = '<%=roleID%>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中国电信码上天翼平台</title>
<meta name="description" content="中国电信码上天翼平台，扫码购机" />
<meta name="Keywords" content="码上天翼平台，扫码购机">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/home/findPass/findPass.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/admin/manage/permission/menu/assignPermissions.css" />
<script type="text/javascript"
	src="<%=path%>/resources/js/common/frameworks/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/assignPermissions.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/ztree/jquery.ztree.exedit-3.5.js"></script>
<link rel="stylesheet"
	href="<%=path%>/resources/css/admin/manage/permission/role/zTreeStyle/zTreeStyle.css"
	type="text/css">

</head>
<body>
	<div class="partnertitle">
		<span class="f_l partnertitletitbg"></span>
		<h2 class="f_l partnertitletit">分配权限</h2>
		<div class="f_r crumbs">
			<span>当前位置：</span><a>首页&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;</a>分配权限
		</div>
	</div>
	<div align="center" class="treeInfo" style="min-height: 200px;">
		<ul id="tree" class="ztree" style="margin-left: 50px;margin-top: 50px"></ul>
	</div>
	<div style="margin-top: 20px">
		<input class="save" type="button" value="全部收起" onclick="UnExpandAll()" style="margin-left: 20px;"/>
		<input class="save" type="button" value="全部展开" onclick="expandAll()" style="margin-left: 20px;"/>
		<input class="save" type="button" value="提交" onclick="submitInfo()" style="margin-left: 20px;"/>
		<input class="save" type="button" value="返回" onclick="goback()" style="margin-left: 20px;"/>
	</div>

</body>
</html>
