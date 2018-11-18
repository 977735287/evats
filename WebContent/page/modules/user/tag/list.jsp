<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
  <title>标签管理</title>
  <meta name="decorator" content="list"/>
</head>
<body title="标签管理">
<grid:grid id="tagGridId" url="${adminPath}/user/tag/ajaxList">
	<grid:column label="sys.common.key" hidden="true"   name="id" width="100"/>
	<grid:column label="标签号"  name="tagNum"  width="100" query="true" condition="like"/>
    <grid:column label="配对标签号"  name="tagBratherNum" width="100" query="true" condition="like"/>
    <grid:column label="标签描述"  name="tagDetail"  width="80"/>
    <grid:column label="创建时间"  name="createDate"  width="80"/>
    <grid:toolbar   function="delete" />
	
	<grid:toolbar  function="search" />
	<grid:toolbar  function="reset" />
</grid:grid>
</body>
</html>