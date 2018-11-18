<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>车辆经过记录</title>
  <meta name="decorator" content="list"/>
</head>
<body title="车辆经过记录">
<grid:grid id="vehicleRecordGridId" url="${adminPath}/user/vehicle/record/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
    <grid:column label="车辆标签号"  name="vehicleTagNum" width="80"/>
    <grid:column label="用户标签号"  name="userTagNum"  width="80"/>
    <grid:column label="描述信息"  name="describe"  width="166"/>
    <grid:column label="识别的时间"  name="readTime"  width="60"/>

<%-- 	<grid:toolbar function="create"/> --%>
<%-- 	<grid:toolbar   function="update"/> --%>
<%-- 	<grid:toolbar   function="delete"/> --%>

<%-- 	<grid:toolbar  function="search"/> --%>
<%-- 	<grid:toolbar  function="reset"/> --%>
</grid:grid>
</body>
</html>