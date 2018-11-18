var isConnect = false;
var isStart = false;
var connectStatus = "";
var startStatus = "";

$(document).ready(function() {
	
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/reader/status',
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
//				alert('connectStatus:' + r.connectStatus + "\nstartStatus:" + r.startStatus);
				isConnect = r.connectStatus;
				isStart = r.startStatus;
				if(isConnect){
					var readerVersion = r.readerVersion;
					connectStatus = "读写器已连接，版本为：" + readerVersion;
					changeConnect(connectStatus);
					if(isStart){
						startStatus = "车辆监控已启动";
						changeStart(startStatus);
					}else{
						startStatus = "车辆监控未启动";
						changeShutdown(startStatus);
					}
				}else{
					connectStatus = "读写器未连接"
					changeDisConnect(connectStatus);
					document.getElementById('startStatusLabel').innerHTML = "车辆监控未启动";
				}
			}else{
				alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
	
	$('#connectControl').click(function(){
		if(isConnect){
			disconnect();
		}else{
			connect();
		}
	});
	
	$('#startControl').click(function(){
		if(isStart){
			shutdown();
		}else{
			startup();
		}
	});
});

function changeConnect(connectStatus){
	$('#connectControl').val('断开');
	$('#connectControl').removeClass('btn btn-info');
	$('#connectControl').addClass('btn btn-danger');
	$('#startControl').attr('disabled',false);
	document.getElementById('connectStatusLabel').innerHTML = connectStatus;
}

function changeDisConnect(connectStatus){
	$('#connectControl').val('连接');
	$('#connectControl').removeClass('btn btn-danger');
	$('#connectControl').addClass('btn btn-info');
	$('#startControl').attr('disabled',true);
	document.getElementById('connectStatusLabel').innerHTML = connectStatus;
}

function changeStart(startStatus){
	$('#startControl').val('关闭');
	$('#startControl').removeClass('btn btn-info');
	$('#startControl').addClass('btn btn-danger');
	$('#connectControl').attr('disabled',true);
	document.getElementById('startStatusLabel').innerHTML = startStatus;
}

function changeShutdown(startStatus){
	$('#startControl').val('启动');
	$('#startControl').removeClass('btn btn-danger');
	$('#startControl').addClass('btn btn-info');
	$('#connectControl').attr('disabled',false);
	document.getElementById('startStatusLabel').innerHTML = startStatus;
}

function connect(){
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/reader/connect',
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				isConnect = true;
				readerVersion = r.readerVersion;
				connectStatus = "读写器已连接，版本为：" + readerVersion;
				changeConnect(connectStatus);
//				alert(r.readerVersion);
			}else{
				alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
}

function disconnect(){
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/reader/disconnect',
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				isConnect = false;
				connectStatus = "读写器已断开连接";
				changeDisConnect(connectStatus);
			}else{
				alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
}

function startup(){
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/reader/startup',
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				isStart = true;
				startStatus = "车辆监控已启动";
				changeStart(startStatus);
			}else{
				alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
}

function shutdown(){
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/reader/shutdown',
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				isStart = false;
				startStatus = "车辆监控已关闭";
				changeShutdown(startStatus);
			}else{
				alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
}