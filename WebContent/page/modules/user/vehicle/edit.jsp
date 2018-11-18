<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>车辆登记</title>
<meta name="decorator" content="form" />

</head>

<body class="white-bg" formid="vehicleForm">
	<form:form id="vehicleForm" modelAttribute="data" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<table
			class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
			<tbody>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车辆品牌:</label></td>
					<td class="width-55"><form:input path="brand"
							class="form-control " datatype="*" nullmsg="请输入车辆品牌！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车辆标签号:</label></td>
					<td class="width-35"><form:input path="selfTag" id="selfTag"
							class="form-control" htmlEscape="false" datatype="*"
							nullmsg="请输入标签号！" /> <label class="Validform_checktip"></label></td>
					<!-- 					<td class="width-15"><a id="tagBtn1" -->
					<!-- 						href="javascript:getSelfTagNum();" class="btn btn-info btn" -->
					<!-- 						role="button"><i class="fa fa-tag"></i> 读取</a><a id="tagLoading1" -->
					<!-- 						style="display: none">正在读取...</a></td> -->
					<td class="width-15">
						<button id="tagBtn1" class="btn btn-info"
							data-complete-text="重新读取" type="button">&nbsp;&nbsp;&nbsp;&nbsp;读取&nbsp;&nbsp;&nbsp;&nbsp;</button>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>用户标签号:</label></td>
					<td class="width-35"><form:input path="userTag" id="userTag"
							class="form-control" htmlEscape="false" datatype="*"
							nullmsg="请输入标签号！" /> <label class="Validform_checktip"></label></td>
					<td class="width-15">
						<button id="tagBtn2" class="btn btn-info"
							data-complete-text="重新读取" type="button">&nbsp;&nbsp;&nbsp;&nbsp;读取&nbsp;&nbsp;&nbsp;&nbsp;</button>
					</td>
<!-- 					<td class="width-15"><a id="tagBtn2" -->
<!-- 						href="javascript:getUserTagNum();" class="btn btn-info btn" -->
<!-- 						role="button"><i class="fa fa-tag"></i> 读取</a><a id="tagLoading2" -->
<!-- 						style="display: none">正在读取...</a></td> -->
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车主姓名:</label></td>
					<td class="width-55"><form:input path="userName"
							class="form-control " datatype="*" nullmsg="请输入车主姓名！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车主手机号码:</label></td>
					<td class="width-55"><form:input path="userPhone"
							class="form-control " datatype="*" nullmsg="请输入车主手机号码！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车主邮箱:</label></td>
					<td class="width-55"><form:input path="userEmail"
							class="form-control " datatype="*" nullmsg="请输入车主邮箱！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label>车辆描述:</label></td>
					<td class="width-35" colspan="3"><form:textarea
							path="describe" htmlEscape="false" rows="3" maxlength="200"
							class="form-control " /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
<!-- 全局js -->
<html:js name="jquery,common" />
</html>