<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>首页</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
 	<%@include file="/common/common.jsp"%>
 	<style>
	.up-panel-body {
		padding-top:0px;
		padding-bottom:0px;
	}
	</style>
</head>


<body class="u_scroll">
    <div class="up-container-fluid u-padding-top10">
            
        <div class="up-clearfix u-panel-box">
                <div class=" up-col-sm-12">
                    <div class="up-panel up-panel-default " style="margin-bottom:0px">
                        <div class="up-panel-heading up-clearfix">
                            <h4 class="up-panel-title">
                                <div class="up-pull-left">
                                     	 采集信息
                                </div>
                            </h4>
                        </div>
                        <div class="up-panel-body">
                            <div class="u_scroll up-w-list" style="height:160px;">
                                <table class="up-table up-table-td-bor up-table-center yyy_news_table">
                                        <thead>
                                            <tr>
                                            <th>连接名称</th>
						                    <th>库名</th>
						                    <th>IP</th>
						                    <th>采集数据量</th>
						                    <th>异常数据量</th>
                                            </tr>
                                        </thead>
                                        <tbody id="yc_tbody">
                                        </tbody>
                                    </table>
                            </div>
                            <div class="up-row u-padding-top10">
                                <div id="u_pagination" class="up-pull-right">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class=" up-col-sm-12">
                    <div class="up-panel up-panel-default" style="margin-bottom:0px">
                        <div class="up-panel-heading up-clearfix">
                            <h4 class="up-panel-title">
                                <div class="up-pull-left">
                                       	告警信息
                                    </div>
                            </h4>
                        </div>
                        <div class="up-panel-body">
                            <div class="u_scroll up-w-list" style="height:160px;">
                                <table class="up-table up-table-td-bor up-table-center yyy_news_table">
                                        <thead>
                                            <tr>
                                                <th>库名</th>
                                                <th>表名</th>
                                                <th>告警时间</th>
                                                <th>告警内容</th>
                                            </tr>
                                        </thead>
                                        <tbody id="gj_tbody">
                                        </tbody>
                                    </table>
                            </div>
                            <div class="up-row u-padding-top10">
                                <div id="u_pagination1" class="up-pull-right">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <div class="ubcharts1_outer up-col-sm-24 u-margin-top15" style="height:280px">
            <div class="ubcharts1 u-padding-top0" id="ubcharts1" style="width:100%;height:100%"></div>
        </div>
    </div>

  

    <script>

    require(['uskin'],function(){
            'use strict';
            $("#u_pagination").pagination({
                pageIndex: 0,
                pageSize: 5,
                total: 60,
                pageBtnCount: 8,
                showFirstLastBtn: true,
                alwaysBtnShow: true,
                showJump: false,
                showPageSizes: false,
                pageSizeItems: [5, 10, 15, 20],
                pageElementSort: ['$page', '$size', '$jump', '$info'],
                firstBtnText: "首页",
                lastBtnText: "尾页",
                prevBtnText: "上一页",
                nextBtnText: "下一页",
                jumpBtnText: "跳转",
                loadFirstPage: true,
                showInfo: true,
                infoFormat: "{start} ~ {end} 共 {total} 条数据",
                noInfoText: "没有任何数据",
                remote : {
                    url : basePath+"/monitorrule/ajaxHomeRwslStatistics",
                    success:function(data){
                    	var recordList=data.list;
                    	var infoStr = '';				
                    	for(var i=0;i<recordList.length;i++){
                    		infoStr=infoStr+"<tr><td>"+
                    		recordList[i].FD_MC+"</td><td>"+
                    		recordList[i].FD_SJKMC+"</td><td>"+
                    		recordList[i].FD_IP+"</td><td>"+
                    		((recordList[i].FD_CLJLS==null)?"0":recordList[i].FD_CLJLS)+"</td><td>"+
                    		((recordList[i].FD_YCJLS==null)?"0":recordList[i].FD_YCJLS)+"</td><td>"+
    						"</td></tr>"; 
                    	}
                    	$("#yc_tbody").empty();
                    	$("#yc_tbody").append(infoStr);
                    },
                    totalName:'total',
    				pageParams: function(data){
    					return {
    						pageNum:data.pageIndex+1,
    						pageSize:data.pageSize
    					};
    				}
                 } 
            });
            $("#u_pagination1").pagination({
                pageIndex: 0,
                pageSize: 10,
                total: 5,
                pageBtnCount: 8,
                showFirstLastBtn: true,
                alwaysBtnShow: true,
                showJump: false,
                showPageSizes: false,
                pageSizeItems: [5, 10, 15, 20],
                pageElementSort: ['$page', '$size', '$jump', '$info'],
                firstBtnText: "首页",
                lastBtnText: "尾页",
                prevBtnText: "上一页",
                nextBtnText: "下一页",
                jumpBtnText: "跳转",
                loadFirstPage: true,
                showInfo: true,
                infoFormat: "{start} ~ {end} 共 {total} 条数据",
                noInfoText: "没有任何数据",
                remote : {
                    url : basePath+"/alarm/ajaxHomeGjInfo",
                    success:function(data){
                    	var recordList=data.list;
                    	var infoStr = '';				
                    	for(var i=0;i<recordList.length;i++){
                    		infoStr=infoStr+"<tr><td>"+
                    		recordList[i].FD_SJKMC+"</td><td>"+
                    		recordList[i].FD_BM+"</td><td>"+
                    		recordList[i].FD_GJSJ+"</td><td><div  style='width:100px;height:18px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_GJNR+"'>"+
                    		recordList[i].FD_GJNR+"</div></td><td>"+
    						"</td></tr>"; 
                    	}
                    	$("#gj_tbody").empty();
                    	$("#gj_tbody").append(infoStr);
                    },
                    totalName:'total',
    				pageParams: function(data){
    					return {
    						pageNum:data.pageIndex+1,
    						pageSize:data.pageSize
    					};
    				}
                 } 
            });
            if ($("#ubcharts1")[0]) {
                require(["echarts","layer"], function(echarts) {
                	$.ajax({
                		type : "POST",
                		url : basePath + "/monitorrule/ajaxHomeRwslStatisticForEchart",
                		dataType : "json",
                		success: function (retData) {
                        	var colors = ['#5793f3', '#d14a61', '#000000'];
                        	var option = {
                        	    color: colors,
                        	    title:{
                        	    	text:'采集信息统计图',
                        	    	left:'center'
                        	    },
                        	    tooltip: {
                        	        trigger: 'axis',
                        	        axisPointer: {
                        	            type: 'cross'
                        	        },
                        	 		formatter: function(params){
                        	 			var result ="";
                        	 			for(var i=0;i<params.length;i++){
                        	 				result = result + params[i].seriesName +":" +params[i].data ;
                        	 				if(params[i].seriesName=="异常记录数占比"){
                        	 					result=result+"%";
                        	 				}
                        	 				result=result+"<br/>";
                        	 			}
                        	 			if(result!=""){
                        	 				result= params[0].name+ "<br/>" + result;
                        	 			}
                        	 			return result;
                        	 		}
                        	    },
                        	    grid: {
                        	        right: '10%'
                        	    },
                        	    toolbox: {
                        	        feature: {
                        	            dataView: {show: false, readOnly: false},
                        	            restore: {show: false},
                        	            saveAsImage: {show: false}
                        	        }
                        	    },
                        	    legend: {
                        	        data:['处理记录数','异常记录数','异常记录数占比'],
                        	        bottom:'0'
                        	    },
                        	    calculable : true,
                        	    yAxis: [
                        	        {
                        	            type: 'value',
                        	            name: '记录数',
                        	            min: 0,
                        	            position: 'left',
//                         	            axisLine: {
//                         	                lineStyle: {
//                         	                    color: colors[0]
//                         	                }
//                         	            },
                        	            axisLabel: {
                        	                formatter: '{value}'
                        	            },
                        	            splitArea: {
                        	                "show": false
                        	            },
                        	            splitLine: {
                        	                "show": false
                        	            },
                        	            axisTick: {
                        	                "show": false
                        	            },
                        	        },
                        	        {
                        	            type: 'value',
                        	            name: '异常记录数占比',
                        	            min: 0,
                        	            max: 100,
                        	            position: 'right',
                        	            axisLine: {
                        	                lineStyle: {
                        	                    color: colors[2]
                        	                }
                        	            },
                        	            axisLabel: {
                        	                formatter: '{value} %'
                        	            },
                        	            splitArea: {
                        	                "show": false
                        	            },
                        	            splitLine: {
                        	                "show": false
                        	            },
                        	            axisTick: {
                        	                "show": false
                        	            }
                        	        }
                        	    ]
                        	};
                           	//设置xAxis
                    	   	var xAxis_param = [
                        		{
                        			type: 'category',
                    	            splitArea: {
                    	                "show": false
                    	            },
                    	            splitLine: {
                    	                "show": false
                    	            },
                    	            axisTick: {
                    	                "show": false
                    	            },
                        	        data: retData.xAxis_data
                        	    }
                        	];
                        	option.xAxis =xAxis_param;
                        	//设置datazoom
                        	 var dataZoom = [{
	                	        show: true,
	                	        height: 10,
	                	        "xAxisIndex": [
	                	            0
	                	        ],
	                	        bottom: 25,
	                	        start : 1000/retData.xAxis_data.length,
	                	        end : 0,
	                	        textStyle:{
	                	        color:"#fff"},
	                	        borderColor:"#90979c"
	                	    },{
	                	        "type": "inside",
	                	        "show": true,
	                	        "height": 15,
	                	        "start": 1,
	                	        "end": 35
	                	    }]
                        	option.dataZoom =dataZoom;
                           	//设置series
                        	option.series=[retData.series_cl_data,retData.series_yc_data,retData.series_persent_data];
                            var myChart = echarts.init(document.getElementById('ubcharts1'));
                            myChart.setOption(option);
                        	$(window).on("resize",myChart.resize);
                	    },
                		error: function(e) {
                			layer.alert("服务器异常!")
                		} 
                	});
            });
            }

    })
</script>
</body>

</html>
