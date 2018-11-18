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
					<td class="width-35"><form:input path="lostBrand"
							class="form-control " datatype="*" nullmsg="请输入车辆品牌！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车辆状态:</label></td>
					<td class="width-35"><form:radiobuttons path="LostStatus"
							htmlEscape="false" class="form-control" dict="vs" datatype="*"
							cssClass="i-checks required" /> <label
						class="Validform_checktip"></label></td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>车辆标签号:</label></td>
					<td class="width-35"  colspan="2"><form:input path="lostTag"
							class="form-control " datatype="*" nullmsg="请输入车辆品牌！"
							htmlEscape="false" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>用户标签号:</label></td>
					<td class="width-35" colspan="2"><form:input path="lostUserTag"
							class="form-control " nullmsg="请输入车辆品牌！"
							htmlEscape="true" /> <label class="Validform_checktip"></label>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td class="width-15 active text-right"><label>车辆描述:</label></td> -->
<%-- 					<td class="width-35" colspan="3"><form:textarea --%>
<%-- 							path="lostTag" htmlEscape="false" rows="1" maxlength="64" --%>
<%-- 							class="form-control " /></td> --%>
<!-- 				</tr><tr> -->
<!-- 					<td class="width-15 active text-right"><label>车辆描述:</label></td> -->
<%-- 					<td class="width-35" colspan="3"><form:textarea --%>
<%-- 							path="lostUserTag" htmlEscape="false" rows="1" maxlength="64" --%>
<%-- 							class="form-control " /></td> --%>
<!-- 				</tr> -->
				<tr>
					<td class="width-15 active text-right"><label><font
							color="red">*</font>失窃日期:</label></td>
					<td class="width-35"><form:input id="lostTime" path="lostTime"
							class="form-control " datatype="*" type="date" nullmsg="请输入日期！"
							htmlEscape="false"/> <label class="Validform_checktip"></label>
					</td>
					<td class="width-15 active text-right"><label>找回日期:</label></td>
					<td class="width-35"><form:input id="backTime" path="backTime"
							class="form-control " type="date"/></td>
				</tr>
				<tr>
					<td class="width-15 active text-right"><label>车辆描述:</label></td>
					<td class="width-35" colspan="3"><form:textarea
							path="LostDescribe" htmlEscape="false" rows="3" maxlength="200"
							class="form-control " /></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
<!-- 全局js -->
<html:js name="jquery,common,datepicker" />
</html>