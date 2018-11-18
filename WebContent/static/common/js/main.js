// JavaScript Document
var _box = $('#box');
var _interval = 1000; // 刷新间隔时间1秒
var _interval1 = 500; // 刷新间隔时间0.5秒
var isShutDown = false;
var vehicleRecordList;

var count = 0;
var scanInfo = "";
var errorScanInfo = "";
var errorScanList = null;
var normalScanList = null;

$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/vehicle/record/getVehicleRecordList',
		async : false,
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				vehicleRecordList = r.vehicleRecordList;
			}else{
				alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
	if(vehicleRecordList.length > 6){
		while(count < 6){
			scanInfo = "<dd style='height:0px;'><div class='fg-left'><span>2018-05-30 14:35:25</span></div><div class='fg-right'><h4>------" + vehicleRecordList[vehicleRecordList.length - (6-count)].describe + "</h4></div></dd>";
			$(scanInfo).prependTo('#box dl');
			var _first = $('#box dl dd:first');
			_first.animate({
				height : '+53px'
			}, "slow");

			count ++;
		}
		
	}
});


function gdb() {
	$(
			"<dd style='height:0px;'><div class='fg-left'><span>2018-05-30 14:35:25</span></div><div class='fg-right'><h4>------车辆标签号为66 66 66 66 66 66 66 66的车辆经过小区门口</h4></div></dd>")
			.prependTo('#box dl');
	var _first = $('#box dl dd:first');
	_first.animate({
		height : '+53px'
	}, "slow");
	var _last = $('#box dl dd:last');
	_last.remove();
	setTimeout(function() {
		gdb();
	}, _interval);
};
// gdb();

function showCarScanInfo() {
//	scanInfo = "<dd style='height:0px;'><div class='fg-left'><span>2018-05-30 14:35:25</span></div><div class='fg-right'><h4>------车辆标签号为66 66 66 66 66 66 66 66的车辆在" + getCurrentDate() + "经过小区门口</h4></div></dd>";
//	alert($('#errorScanInfo').val());
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/reader/scanInfo',
		async : false,
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				errorScanList = r.errorScanList;
				normalScanList = r.normalScanList;
//				alert(normalScanList);
//				console.log(errorScanList);
//				console.log(normalScanList);
			}else{
				alert(r.msg);
				isShutDown = true;
			}
		},
		error : function() {
//			alert("error");
			isShutDown = true;
		}
	});
	if(normalScanList != null && normalScanList != ''){
		for(var i=0;i<normalScanList.length;i++){
			scanInfo = "<dd style='height:0px;'><div class='fg-left'><span>2018-05-30 14:35:25</span></div><div class='fg-right'><h4>------" + normalScanList[i] + "</h4></div></dd>";
			setTimeout(function() {
				if (count < 6) {
					count++;
					$(scanInfo).prependTo('#box dl');
					var _first = $('#box dl dd:first');
					_first.animate({
						height : '+53px'
					}, "slow");
				} else {
					$(scanInfo).prependTo('#box dl');
					var _first = $('#box dl dd:first');
					_first.animate({
						height : '+53px'
					}, "slow");
					var _last = $('#box dl dd:last');
					_last.remove();
				}
			}, _interval1);
		}
	}
	if(errorScanList != null && errorScanList != ''){
		for(var i=0;i<errorScanList.length;i++){
//			errorScanInfo = "<li class='notice_active_ch'><span>" + errorScanList[i] + "</span>" + getCurrentDate(0,0,0) + "</li>";
//			var ul = document.getElementById("errorInfo");
//			ul.removeChild(ul.childNodes[0]);
//			var li = document.createElement("li");
//			li.setAttribute("class", "notice_active_ch");
//			li.innerHTML = "<span>" + errorScanList[i] + "</span>" + getCurrentDate();
//			ul.appendChild(li);
			var li = document.getElementById("errorInfo");
			li.innerHTML = "<span>" + errorScanList[i] + "</span>" + getCurrentDateToDay();
		}
	}
	setTimeout(function() {
		if(!isShutDown){
			showCarScanInfo();
		}
	}, _interval);
}
showCarScanInfo();

function timer(opj) {
	$(opj).find('ul').animate({
		marginTop : "-3.5rem"
	}, 500, function() {
		$(this).css({
			marginTop : "0rem"
		}).find("li:first").appendTo(this);
	})
}
$(function() {
	var num = $('.notice_active').find('li').length;
	if (num > 1) {
		var time = setInterval('timer(".notice_active")', 3500);
		$('.gg_more a').mousemove(function() {
			clearInterval(time);
		}).mouseout(function() {
			time = setInterval('timer(".notice_active")', 3500);
		});
	}

	$(".news_ck").click(
			function() {
				location.href = $(".notice_active .notice_active_ch").children(
						":input").val();
			})
});