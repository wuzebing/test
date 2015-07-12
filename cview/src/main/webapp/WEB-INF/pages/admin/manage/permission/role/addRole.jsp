<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%
	/**
	 * Description: 添加角色
	 * Author: ganghh
	 * Since: 2015-02-04
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
	href="<%=path%>/resources/css/common/frameworks/validform/css/validform.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/common/form.css" />
<link rel="stylesheet" type="text/css"
	href="<%=path%>/resources/css/admin/manage/permission/role/addRole.css" />

<script type="text/javascript"
	src="<%=path%>/resources/js/common/frameworks/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/common/frameworks/validform/plugin/passwordStrength/passwordStrength-min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/admin/manage/permission/role/addRole.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/home/findPass/findPass.js"></script>

</head>
<body>
	<div class="partnertitle">
		<span class="f_l partnertitletitbg"></span>
		<h2 class="f_l partnertitletit">添加角色</h2>
		<div class="f_r crumbs">
			<span>当前位置：</span><a>个人中心&nbsp;&gt;&nbsp;系统管理&nbsp;&gt;&nbsp;</a>添加角色
		</div>
	</div>
	<div id="panel">
		<form id="roleInfoForm" enctype="multipart/form-data">
			<table class="form">
				<tr class="formTR">
					<td class="formField Validform_label">角色名称：</td>
					<td class="formValue"><input type="text" class="formInput"
						name="roleName" datatype="*1-20" /></td>
					<td class="formMark">*</td>
					<td class="formMsg"></td>
				</tr>
				<tr class="formTR">
					<td class="formField Validform_label">角色类型：</td>
					<td class="formValue">
						<select class="formInput" name="roleType" datatype="*1-10" id="roleType">
							<option value=""></option>
							<option value="ADMIN">管理员</option>
							<option value="SUPPLIER">供货商</option>
							<option value="PARTNER">合伙人</option>
							<option value="VISITOR">游客</option>
						</select></td>
					<td class="formMark">*</td>
					<td class="formMsg"></td>
				</tr>
				<tr class="formTR">
					<td class="formField Validform_label">备注信息：</td>
					<td class="formValue"><input type="text" class="formInput"
						name="roleRemark" datatype="*1-20" /></td>
					<td class="formMark">*</td>
					<td class="formMsg"></td>
				</tr>
				<tr>
					<td >
						<input class="save" type="button" value="提交" id="submitButton" onclick="submitInfo()"
							/>
					</td>
				</tr>
			</table>
		</form>
		
	</div>
</body>
</html>
