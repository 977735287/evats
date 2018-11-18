var aleadyFindConunt = 0;
var notFindConunt = 0;
$(function() {
	$.ajax({
		type : 'post',
		url : '/evats/admin/charts/echarts/data/pie',
		async : false,
		dataType : 'json',
		data : {},
		success : function(r){
			if(r.code === 0){
				aleadyFindConunt = r.aleadyFindConunt;
				notFindConunt = r.notFindConunt;
			}else{
//				alert(r.msg);
			}
		},
		error : function() {
//			alert("error");
		}
	});
	var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
	var pieoption = {
		title : {
			text : '失窃车辆找回率',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		toolbox : {
			show : true,
			feature : {
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		legend : {
			orient : 'vertical',
			x : 'left',
			data : [ '未找回', '已找回' ]
		},
		calculable : true,
		series : [ {
			name : '状态',
			type : 'pie',
			radius : '55%',
			center : [ '50%', '60%' ],
			data : [ {
				value : notFindConunt,
				name : '未找回'
			}, {
				value : aleadyFindConunt,
				name : '已找回'
			} ]
		} ]
	};
	pieChart.setOption(pieoption);
	$(window).resize(pieChart.resize);

});
