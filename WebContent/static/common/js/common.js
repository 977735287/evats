$(document).ready(function() {
	$('#lostTime').val(getCurrentDate(0, 0, 0));
// $('#backTime').val(getCurrentDate(5, 0, 0));
// $('#lostTime').datepicker({
// keyboardNavigation: false,
// forceParse: false,
// autoclose: true
// });
});

$(function() {
    $("#tagBtn1").click(function(){
        $(this).button('loading').delay(1000);
        getSelfTagNum();
    });
    
    $("#tagBtn2").click(function(){
    	$(this).button('loading').delay(1000);
    	getUserTagNum();
    });
});

 function getSelfTagNum() {
	$("#tagBtn2").prop('disabled',true);
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/vehicle/currentTag',
		dataType : "json",
		data : {},
		success : function(r) {
			if (r.code === 0) {
				if ($("#userTag").val() == r.tag) {
					alert("标签无法重复使用，请更换标签");
				} else {
					$("#selfTag").val(r.tag);
					$("#tagBtn1").val('重新获取')
				}
			} else {
				alert(r.msg);
			}
			$("#tagBtn1").button('complete');
			$("#tagBtn2").prop('disabled',false);
		},
		error : function() {
			alert("error!");
			$("#tagBtn1").button('reset');
			$("#tagBtn2").prop('disabled',false);
		}
	});
}
 
function getUserTagNum() {
	$("#tagBtn1").prop('disabled',true);
	$.ajax({
		type : 'post',
		url : '/evats/admin/user/vehicle/currentTag',
		dataType : "json",
		data : {},
		success : function(r) {
			if (r.code === 0) {
				if ($("#selfTag").val() == r.tag) {
					alert("标签无法重复使用，请更换标签");
				} else {
					$("#userTag").val(r.tag);
				}
			} else {
				alert(r.msg);
			}
			$("#tagBtn2").button('complete');
			$("#tagBtn1").prop('disabled',false);
		},
		error : function() {
			alert("error");
			$("#tagBtn2").button('reset');
			$("#tagBtn1").prop('disabled',false);
		}
	});
}

// function getSelfTagNum() {
// lockBtn("tagBtn1", "tagBtn2", "tagLoading1");
// $.ajax({
// type : 'post',
// url : '/evats/admin/user/vehicle/currentTag',
// dataType : "json",
// data : {},
// success : function(r) {
// if (r.code === 0) {
// if ($("#userTag").val() == r.tag) {
// alert("标签无法重复使用，请更换标签");
// } else {
// $("#selfTag").val(r.tag);
// }
// } else {
// alert(r.msg);
// }
// unlockBtn("tagBtn1", "tagBtn2", "tagLoading1");
// },
// error : function() {
// alert("error!");
// }
// });
// }
//
// function getUserTagNum() {
// lockBtn("tagBtn2", "tagBtn1", "tagLoading2");
// $.ajax({
// type : 'post',
// url : '/evats/admin/user/vehicle/currentTag',
// dataType : "json",
// data : {},
// success : function(r) {
// if (r.code === 0) {
// if ($("#selfTag").val() == r.tag) {
// alert("标签无法重复使用，请更换标签");
// } else {
// $("#userTag").val(r.tag);
// }
// } else {
// alert(r.msg);
// }
// unlockBtn("tagBtn2", "tagBtn1", "tagLoading2");
// },
// error : function() {
// alert("error");
// }
// });
// }
//
// function lockBtn(id1, id2, loadId) {
// var obj = document.getElementById(id1);
// var obj2 = document.getElementById(id2);
// var obj1 = document.getElementById(loadId);
// obj.setAttribute("class", "btn btn-info btn disabled");
// obj2.setAttribute("class", "btn btn-info btn disabled");
// obj1.style.display = "block";
// }
//
// function unlockBtn(id, id2, loadId) {
// var obj = document.getElementById(id);
// var obj2 = document.getElementById(id2);
// var obj1 = document.getElementById(loadId);
// obj.setAttribute("class", "btn btn-info btn");
// obj2.setAttribute("class", "btn btn-info btn");
// obj1.style.display = "none";
//
// }

function getDayDate(y, m, d) {
	var today = new Date();
	var date;
	var year = today.getFullYear() + y;
	var month = today.getMonth() + 1 + m;
	var day = today.getDate() + d;
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	date = year + "-" + month + "-" + day;
	return date;
}

function getCurrentDateToDay() {
	var date =  new Date().format('yyyy-MM-dd');
	return date;
}

function getCurrentDate() {
	var date =  new Date().format('yyyy-MM-dd hh:mm:ss');
	return date;
}

Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,                 // 月份
       "d+" : this.getDate(),                    // 日
       "h+" : this.getHours(),                   // 小时
       "m+" : this.getMinutes(),                 // 分
       "s+" : this.getSeconds(),                 // 秒
       "q+" : Math.floor((this.getMonth()+3)/3), // 季度
       "S"  : this.getMilliseconds()             // 毫秒
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}

function time_range (beginTime, endTime, nowTime) {
// alert(beginTime + "---" + endTime + "---" + nowTime);
	var strb = beginTime.split (":");
	if (strb.length != 2) {
		return false;
	}
	var stre = endTime.split (":");
	if (stre.length != 2) {
		return false;
	}
	var strn = nowTime.split (":");
	if (stre.length != 2) {
		return false;
	}
	var b = new Date ();
	var e = new Date ();
	var n = new Date ();
	b.setHours (strb[0]);
	b.setMinutes (strb[1]);
	e.setHours (stre[0]);
	e.setMinutes (stre[1]);
	n.setHours (strn[0]);
	n.setMinutes (strn[1]);
	if (n.getTime () - b.getTime () > 0 && n.getTime () - e.getTime () < 0) {
		return true;
	} else {
// alert ("当前时间是：" + n.getHours () + ":" + n.getMinutes () + "，不在该时间范围内！");
		return false;
	}
}

function day_range (beginTime, endTime, nowTime) {
	var startTime = beginTime.split('-');
	var planStartTime = Date.UTC(parseInt(startTime[0]), parseInt(startTime[1]), parseInt(startTime[2]));
	var stopTime = endTime.split('-');
	var planStopTime = Date.UTC(parseInt(stopTime[0]), parseInt(stopTime[1]), parseInt(stopTime[2]));
	var nowTimes = nowTime.split('-');
	var planNowTime = Date.UTC(parseInt(nowTimes[0]), parseInt(nowTimes[1]), parseInt(nowTimes[2]));
    if (planNowTime >= planStartTime && planNowTime <= planStopTime) {
          return true;
    } else {
        return false;
    }
}

function refreshCurrent(){
// alert();
	location.reload();
}