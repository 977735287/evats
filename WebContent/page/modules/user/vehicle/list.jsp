<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>车辆管理</title>
  <meta name="decorator" content="list"/>
</head>
<body title="车辆管理">
<grid:grid id="vehicleGridId" url="${adminPath}/user/vehicle/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="车辆品牌"  name="brand"  width="50" query="true" condition="like"/>
    <grid:column label="车辆标签号"  name="selfTag" width="100" query="true" condition="like"/>
    <grid:column label="用户标签号"  name="userTag"  width="100"  query="true" condition="like"/>
    <grid:column label="车主姓名"  name="userName"  width="100"  query="true" condition="like"/>
    <grid:column label="车主手机号码"  name="userPhone"  width="100" />
    <grid:column label="车主邮箱"  name="userEmail"  width="100"/>
    <grid:column label="车辆描述"  name="describe"  width="100"/>
<%--     <grid:column label="创建时间"  name="createDate"  width="80"/> --%>

	<grid:toolbar function="create"/>
	<grid:toolbar   function="update"/>
	<grid:toolbar   function="delete"/>

	<grid:toolbar  function="search"/>
	<grid:toolbar  function="reset"/>
</grid:grid>
</body>
</html>