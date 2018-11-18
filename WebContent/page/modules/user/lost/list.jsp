<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>失窃车辆管理</title>
  <meta name="decorator" content="list"/>
</head>
<body title="失窃车辆管理">
<grid:grid id="lostVehicleGridId" url="${adminPath}/user/lost/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="车辆品牌"  name="lostBrand"  width="100" query="true" condition="like"/>
    <grid:column label="车辆标签号"  name="lostTag" width="100" query="true" condition="like"/>
    <grid:column label="用户标签号"  name="lostUserTag" width="100"/>
<%--     <grid:column label="车主姓名"  name="user.realname"  width="80" query="true" condition="like"/> --%>
    <grid:column label="失窃日期"  name="lostTime"  width="80"/>
    <grid:column label="找回日期"  name="backTime"  width="80"/>
    <grid:column label="车辆状态"  name="lostStatus" query="true"  queryMode="select" width="80"  condition="eq"  dict="vs"/>
    <grid:column label="车辆描述"  name="lostDescribe"  width="100"/>

	<grid:toolbar function="create"/>
	<grid:toolbar   function="update"/>
	<grid:toolbar   function="delete"/>

	<grid:toolbar  function="search"/>
	<grid:toolbar  function="reset"/>
</grid:grid>
</body>
</html>