<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>首页</title>
<meta name="keywords"
	content="<spring:message code="sys.site.keywords" arguments="${platformName}"/>">
<meta name="description"
	content="<spring:message code="sys.site.description" arguments="${platformName}"/>">

<html:css name="favicon,bootstrap,font-awesome,animate" />
<link href="${staticPath}/common/css/main.css" rel="stylesheet">

</head>

<body class="gray-bg">

	<div class="huadong">
		<div class="huabox">
			<div class="hdimg">
				<img src="${staticPath}/common/img/lingdang.png">
			</div>
			<h5>【异常消息】</h5>
			<div class="notice_active">
				<ul>
					<li class="notice_active_ch" id="errorInfo">无异常信息！</li>
<!-- 					<li class="notice_active_ch"><span>车辆标签号为aa bb cc dd 11 -->
<!-- 							22 33 44的车辆在某小区存在异常骑行，车身颜色为黑白色</span>2017-01-18</li> -->
<!-- 					<li class="notice_active_ch"><span>车辆标签号为aa bb cc dd 11 -->
<!-- 							22 33 44的车辆在某小区存在异常骑行，车身颜色为黑白色</span>2017-01-18</li> -->
				</ul>
			</div>
		</div>
	</div>

	<div>
		<div class="fg-box" id="box">
			<div class="fg-line"></div>
			<dl>				
			</dl>
			<div class=fg-allRecord>
				<a href="${adminPath}/user/vehicle/record/list">查看所有记录>></a>
			</div>
		</div>
	</div>
<%-- 	<input type="text" id="normalScanInfo" value="${normalScanInfo}"/> --%>
<%-- 	<input type="text" id="errorScanInfo" value="${errorScanInfo}"/> --%>

	<!-- 全局js -->
	<html:js name="jquery,bootstrap" />

	<!-- 自定义js -->
	<script src="${staticPath}/common/js/common.js"></script>
	<script src="${staticPath}/common/js/main.js"></script>

</body>

</html>