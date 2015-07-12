<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String roleId = request.getParameter("roleID");
%>
<%
	/**
	 * Description: 管理员-角色管理-给用户分配权限
	 * Author: ganghh
	 * Since: 2015-02-06
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
	href="<%=path%>/resources/css/common/frameworks/validform/css/validform.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/common/widgets/message/message.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/admin/manage/permission/role/roleList.css" />
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/userRole.js"></script>
<script type="text/javascript">
	var roleID = '<%=roleId%>';
</script>
</head>
<body>
	<div class="partnertitle">
		<span class="f_l partnertitletitbg"></span>
		<h2 class="f_l partnertitletit">用户赋权</h2>
		<div class="f_r crumbs">
			<span>当前位置：</span><a>首页&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;</a>用户赋权
		</div>
	</div>
	<div class="searchcon">
		<span class="search_name">用户名称：</span> <span> <input
			type="text" class="searchtxt" id="userName" />
		</span>
		<!-- <span class="search_name">创建时间：</span> <span> <input
			type="text" class="searchtxt" id="payTime"
			onblur="checkRequired(this,'required2')"
			onkeydown="checkRequired(this,'required2')"
			onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,readOnly:true,maxDate:'%y-%M-%d'});" />
		</span>  -->
		<input type="button" class="search_inp" value="搜索"
			onclick="searchByUserName()" />
	</div>
	<div class="table_box" align="center">
		<table class="table table_css">
			<thead class="thead">
				<tr>
					<td class="tableCell" width="200px">用户名称</td>
					<td class="tableCell" width="200px">角色名称</td>
					<td class="tableCell" width="200px">角色类型</td>
					<td class="tableCell" width="200px">创建时间</td>
					<td class="tableCell" width="200px">操作</td>
				</tr>
			</thead>
			<tbody class="tbody" id="userGrid"></tbody>
		</table>
		<div id="paginator" style="margin-bottom: 25px;"></div>
	</div>
</body>
</html>
