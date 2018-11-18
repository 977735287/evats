var vehicleRecordList = [];
var time1 = 0;
var time2 = 0;
var time3 = 0;
var time4 = 0;
var time5 = 0;
var time6 = 0;
var time7 = 0;
var time8 = 0;
var time9 = 0;
var time10 = 0;
var time11 = 0;
var time12 = 0;
var barChart;
var barData = [];
var flag = 0;
var allDateBtn = $('#allDate');
var preDayBtn = $('#preDay');
var preWeekBtn = $('#preWeek');
$(function() {
	$.ajax({
		type : 'post',
		url : '/evats/admin/charts/echarts/data/bar',
		async : false,
		dataType : 'json',
		data : {},
		success : function(r) {
			if (r.code === 0) {
				vehicleRecordList = r.vehicleRecordList;
			} else {
				// alert(r.msg);
			}
		},
		error : function() {
			alert("error");
		}
	});
	for (var i = 0; i < vehicleRecordList.length; i++) {
		var time = vehicleRecordList[i].readTime.substring(11, 16);
		if (time_range("00:00", "02:00", time)) {
			time1++;
		} else if (time_range("02:00", "04:00", time)) {
			time2++;
		} else if (time_range("04:00", "06:00", time)) {
			time3++;
		} else if (time_range("06:00", "08:00", time)) {
			time4++;
		} else if (time_range("08:00", "10:00", time)) {
			time5++;
		} else if (time_range("10:00", "12:00", time)) {
			time6++;
		} else if (time_range("12:00", "14:00", time)) {
			time7++;
		} else if (time_range("14:00", "16:00", time)) {
			time8++;
		} else if (time_range("16:00", "18:00", time)) {
			time9++;
		} else if (time_range("18:00", "20:00", time)) {
			time10++;
		} else if (time_range("20:00", "22:00", time)) {
			time11++;
		} else if (time_range("22:00", "24:00", time)) {
			time12++;
		}
		barData = [ time1, time2, time3, time4, time5, time6, time7, time8,
				time9, time10, time11, time12 ];
	}
	changeBtnColor(allDateBtn, preDayBtn, preWeekBtn, 1);

	barChart = echarts.init(document.getElementById("echarts-bar-chart"));
	var baroption = {
		color : [ '#3398DB' ],
		title : {
			text : '各时段车辆经过数量统计',
			x : 'center'
		},
		tooltip : {
			trigger : 'axis'
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : [ '00:00-02:00', '02:00-04:00', '04:00-06:00',
					'06:00-08:00', '08:00-10:00', '10:00-12:00', '12:00-14:00',
					'14:00-16:00', '16:00-18:00', '18:00-20:00', '20:00-22:00',
					'22:00-24:00' ]
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '车辆经过数量',
			type : 'bar',
			barWidth : '50%',
			data : barData,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				}, {
					type : 'min',
					name : '最小值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		} ]
	};
	barChart.setOption(baroption);
	window.onresize = barChart.resize;

	allDateBtn.click(function() {
		changeBtnColor(allDateBtn, preDayBtn, preWeekBtn, 1);
	});

	preDayBtn.click(function() {
		changeBtnColor(allDateBtn, preDayBtn, preWeekBtn, 2);
	});

	preWeekBtn.click(function() {
		changeBtnColor(allDateBtn, preDayBtn, preWeekBtn, 3);
	});
});

function changeBtnColor(btn1, btn2, btn3, n) {
	if (flag == 1) {
		if (n == 1) {
			btn1.removeClass('btn btn-info btn-sm');
			btn1.addClass('btn btn-default btn-sm');
			flag = 0;
		} else if (n == 2) {
			btn1.removeClass('btn btn-info btn-sm');
			btn1.addClass('btn btn-default btn-sm');
			btn2.removeClass('btn btn-default btn-sm');
			btn2.addClass('btn btn-info btn-sm');
			flag = 2;
		} else if (n == 3) {
			btn1.removeClass('btn btn-info btn-sm');
			btn1.addClass('btn btn-default btn-sm');
			btn3.removeClass('btn btn-default btn-sm');
			btn3.addClass('btn btn-info btn-sm');
			flag = 3;
		}
	} else if (flag == 2) {
		if (n == 2) {
			btn2.removeClass('btn btn-info btn-sm');
			btn2.addClass('btn btn-default btn-sm');
			flag = 0;
		} else if (n == 1) {
			btn2.removeClass('btn btn-info btn-sm');
			btn2.addClass('btn btn-default btn-sm');
			btn1.removeClass('btn btn-default btn-sm');
			btn1.addClass('btn btn-info btn-sm');
			flag = 1;
		} else if (n == 3) {
			btn2.removeClass('btn btn-info btn-sm');
			btn2.addClass('btn btn-default btn-sm');
			btn3.removeClass('btn btn-default btn-sm');
			btn3.addClass('btn btn-info btn-sm');
			flag = 3;
		}
	} else if (flag == 3) {
		if (n == 3) {
			btn3.removeClass('btn btn-info btn-sm');
			btn3.addClass('btn btn-default btn-sm');
			flag = 0;
		} else if (n == 1) {
			btn3.removeClass('btn btn-info btn-sm');
			btn3.addClass('btn btn-default btn-sm');
			btn1.removeClass('btn btn-default btn-sm');
			btn1.addClass('btn btn-info btn-sm');
			flag = 1;
		} else if (n == 2) {
			btn3.removeClass('btn btn-info btn-sm');
			btn3.addClass('btn btn-default btn-sm');
			btn2.removeClass('btn btn-default btn-sm');
			btn2.addClass('btn btn-info btn-sm');
			flag = 2;
		}
	} else if (flag == 0) {
		if (n == 1) {
			btn1.removeClass('btn btn-default btn-sm');
			btn1.addClass('btn btn-info btn-sm');
			flag = 1;
		} else if (n == 2) {
			btn2.removeClass('btn btn-default btn-sm');
			btn2.addClass('btn btn-info btn-sm');
			flag = 2;
		} else if (n == 3) {
			btn3.removeClass('btn btn-default btn-sm');
			btn3.addClass('btn btn-info btn-sm');
			flag = 3;
		}
	}
	if (flag == 0) {
		barData = [];
		refreshData(barData);
	} else if (flag == 1) {
		allDataShow();
		refreshData(barData);
	} else if (flag == 2) {
		preDayShow();
		refreshData(barData);
	} else if (flag == 3) {
		preWeekShow();
		refreshData(barData);
	}
}

function refreshData(barData) {
	if (!barChart) {
		return;
	}
	var option = barChart.getOption();
	option.series[0].data = barData;
	barChart.setOption(option);
	window.onresize = barChart.resize;
}

function allDataShow() {
	time1 = 0;
	time2 = 0;
	time3 = 0;
	time4 = 0;
	time5 = 0;
	time6 = 0;
	time7 = 0;
	time8 = 0;
	time9 = 0;
	time10 = 0;
	time11 = 0;
	time12 = 0;
	for (var i = 0; i < vehicleRecordList.length; i++) {
		var time = vehicleRecordList[i].readTime.substring(11, 16);
		if (time_range("00:00", "02:00", time)) {
			time1++;
		} else if (time_range("02:00", "04:00", time)) {
			time2++;
		} else if (time_range("04:00", "06:00", time)) {
			time3++;
		} else if (time_range("06:00", "08:00", time)) {
			time4++;
		} else if (time_range("08:00", "10:00", time)) {
			time5++;
		} else if (time_range("10:00", "12:00", time)) {
			time6++;
		} else if (time_range("12:00", "14:00", time)) {
			time7++;
		} else if (time_range("14:00", "16:00", time)) {
			time8++;
		} else if (time_range("16:00", "18:00", time)) {
			time9++;
		} else if (time_range("18:00", "20:00", time)) {
			time10++;
		} else if (time_range("20:00", "22:00", time)) {
			time11++;
		} else if (time_range("22:00", "24:00", time)) {
			time12++;
		}
		barData = [ time1, time2, time3, time4, time5, time6, time7, time8,
				time9, time10, time11, time12 ];
	}
}

function preDayShow() {
	time1 = 0;
	time2 = 0;
	time3 = 0;
	time4 = 0;
	time5 = 0;
	time6 = 0;
	time7 = 0;
	time8 = 0;
	time9 = 0;
	time10 = 0;
	time11 = 0;
	time12 = 0;
	for (var i = 0; i < vehicleRecordList.length; i++) {
		var limitTime = vehicleRecordList[i].readTime.substring(0, 10);
		if (getDayDate(0, 0, -1) == limitTime) {
			var time = vehicleRecordList[i].readTime.substring(11, 16);
			if (time_range("00:00", "02:00", time)) {
				time1++;
			} else if (time_range("02:00", "04:00", time)) {
				time2++;
			} else if (time_range("04:00", "06:00", time)) {
				time3++;
			} else if (time_range("06:00", "08:00", time)) {
				time4++;
			} else if (time_range("08:00", "10:00", time)) {
				time5++;
			} else if (time_range("10:00", "12:00", time)) {
				time6++;
			} else if (time_range("12:00", "14:00", time)) {
				time7++;
			} else if (time_range("14:00", "16:00", time)) {
				time8++;
			} else if (time_range("16:00", "18:00", time)) {
				time9++;
			} else if (time_range("18:00", "20:00", time)) {
				time10++;
			} else if (time_range("20:00", "22:00", time)) {
				time11++;
			} else if (time_range("22:00", "24:00", time)) {
				time12++;
			}
			barData = [ time1, time2, time3, time4, time5, time6, time7, time8,
					time9, time10, time11, time12 ];
		}
	}
}

function preWeekShow() {
	time1 = 0;
	time2 = 0;
	time3 = 0;
	time4 = 0;
	time5 = 0;
	time6 = 0;
	time7 = 0;
	time8 = 0;
	time9 = 0;
	time10 = 0;
	time11 = 0;
	time12 = 0;
	for (var i = 0; i < vehicleRecordList.length; i++) {
		var limitTime = vehicleRecordList[i].readTime.substring(0, 10);
		if (day_range(getDayDate(0, 0, -7),getDayDate(0, 0, 0),limitTime)) {
			var time = vehicleRecordList[i].readTime.substring(11, 16);
			if (time_range("00:00", "02:00", time)) {
				time1++;
			} else if (time_range("02:00", "04:00", time)) {
				time2++;
			} else if (time_range("04:00", "06:00", time)) {
				time3++;
			} else if (time_range("06:00", "08:00", time)) {
				time4++;
			} else if (time_range("08:00", "10:00", time)) {
				time5++;
			} else if (time_range("10:00", "12:00", time)) {
				time6++;
			} else if (time_range("12:00", "14:00", time)) {
				time7++;
			} else if (time_range("14:00", "16:00", time)) {
				time8++;
			} else if (time_range("16:00", "18:00", time)) {
				time9++;
			} else if (time_range("18:00", "20:00", time)) {
				time10++;
			} else if (time_range("20:00", "22:00", time)) {
				time11++;
			} else if (time_range("22:00", "24:00", time)) {
				time12++;
			}
			barData = [ time1, time2, time3, time4, time5, time6, time7, time8,
					time9, time10, time11, time12 ];
		}
	}
}

