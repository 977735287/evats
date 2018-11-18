<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/page/common/taglibs.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="decorator" content="single" />
<title>读写器控制</title>
<html:css name="bootstrap-3.3.7-dist" />
<!-- 全局js -->
<html:js
	name="jquery,iCheck,Validform,simditor,bootstrap-3.3.7-dist,bootstrap-switch" />
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="col-sm-2"></div>
				<div class="col-sm-8">
					<div class="portlet box  portlet-grey">
						<div class="portlet-header">
							<div class="caption">读写器控制</div>
							<div class="tools">
								<i class="fa fa-chevron-up"></i><i class="fa fa-refresh"
									onclick="javascript:location.reload();"></i><i
									class="fa fa-times"></i>
							</div>
						</div>
						<div class="portlet-body">
							<form:form id="readerForm" action="" method="post"
								class="form-horizontal">
								<div class="form-group col-sm-12">&nbsp;</div>
								<div class="form-group col-sm-12" style="text-align: center;">
								<h4 id="connectStatusLabel"></h4>
								<h4 id="startStatusLabel" style="margin-top:16px;"></h4>
								</div>
								<div class="form-group col-sm-12">&nbsp;</div>
								<!-- 								<div class="form-group col-sm-12"> -->
								<!-- 									<div class="col-sm-4"></div> -->
								<!-- 									<div class="col-sm-4"> -->
								<!-- 										<label class="control-label">读卡器开关</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input -->
								<!-- 											id="switchControl" type="checkbox" data-on-text="启动" -->
								<!-- 											data-off-text="停止" data-on-color="success" -->
								<!-- 											data-off-color="info" data-size="large" /> -->
								<!-- 									</div> -->
								<!-- 									<div class="col-sm-4"></div> -->
								<!-- 								</div> -->
								<div class="control-group col-sm-12">
									<div class="col-sm-3"></div>
									<div class="col-sm-3" style="text-align: right;"><label class="control-label">连接读卡器设备</label></div>
									<div class="col-sm-2">
										<input id="connectControl" class="btn btn-info btn-block" type="button"
											value="连接" />
									</div>
									<div class="col-sm-4"></div>
								</div>
								<div class="control-group col-sm-12" style="margin-top:16px;">
									<div class="col-sm-3"></div>
									<div class="col-sm-3" style="text-align: right;"><label class="control-label">启动车辆监控</label></div>
									<div class="col-sm-2">
										<input id="startControl" class="btn btn-info btn-block" type="button"
												value="启动" disabled="disabled"/>
									</div>
									<div class="col-sm-4"></div>
								</div>
								<div class="form-group">
									<div class="col-sm-10 col-sm-offset-1 text-right">
										<div class="hidden">
											<button id="submit" class="btn btn-primary" type="submit"></button>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</div>
				<div class="col-sm-2"></div>
			</div>

			<script type="text/javascript">
				
			</script>

			<!-- 自定义js -->
			<script src="${staticPath}/common/js/content.js?v=1.0.0"></script>
			<script src="${staticPath}/common/js/readerFunc.js"></script>
</body>

</html>