<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>首页</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>

</head>
<body class="u_scroll">
<div class="up-container-fluid">
<form class="up-form-inline u-margin-top20 u-margin-bottom20" action="">

<%--               <div class="up-form-group">
                <a href="${basePath}/plugins/datasupport/ycju.jsp" class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i> 返回上页
                </a>
            </div> --%>
   
              <div class="up-form-group">
                <a href="${basePath}/ycjumonitor/exportexcel?jobid=${jobid}&yname=${yname}"   class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i> 导 出
                </a>
            </div>
 </form>   
          
     <div class="u_tab_body" id="example_tab4">
         <table id="grid4" class="jqgrid"></table>
         <div id="pager" style="height:40px;"></div>   
</div>        
   <script>
   var dataObj=eval("("+'${cols}'+")");//转换为json对象 
   var jsonObj='${str}';
   var flag=true;
   if(dataObj.length>10){
	   flag=false;
   }
   //console.log(dataObj[0].name);
   //console.log(eval("("+'${str}'+")"));
   require(['uskin'], function() {
	    require(["jqGrid"], function(grid) {
	        $(document).ready(function() {	         
	        	$("#grid4").jqGrid({
	               // url: "${basePath}/ycjumonitor/getycJsondata?jobid="+'${jobid}'+"&yname="+'${yname}',
	                //mtype: "GET",
	                datastr:jsonObj,
	                datatype: "jsonstring",
	                colModel: dataObj
	                	/* [{
	                    label: 'fd_app_id',
	                    name: 'fd_app_id',
	                    key: true,
	                    width: 175
	                }, {
	                    label: 'fd_avg_procces',
	                    name: 'fd_avg_procces',
	                    width: 50
	                },{
	                    label: 'fd_date',
	                    name: 'fd_date',
	                    width: 50
	                },{
	                    label: 'fd_failed',
	                    name: 'fd_failed',
	                    width: 50
	                },{
	                    label: 'fd_health_avg_score',
	                    name: 'fd_health_avg_score',
	                    width: 50
	                },{
	                    label: 'fd_health_min_score',
	                    name: 'fd_health_min_score',
	                    width: 50
	                },{
	                    label: 'fd_hour',
	                    name: 'fd_hour',
	                    width: 50
	                },{
	                    label: 'fd_id',
	                    name: 'fd_id',
	                    width: 100
	                },{
	                    label: 'fd_max_procces',
	                    name: 'fd_max_procces',
	                    width: 50
	                },{
	                    label: 'fd_min_procces',
	                    name: 'fd_min_procces',
	                    width: 50
	                },{
	                    label: 'fd_month',
	                    name: 'fd_month',
	                    width: 50
	                },{
	                    label: 'fd_overstock',
	                    name: 'fd_overstock',
	                    width: 50
	                },{
	                    label: 'fd_process_total',
	                    name: 'fd_process_total',
	                    width: 50
	                },{
	                    label: 'fd_susscces',
	                    name: 'fd_susscces',
	                    width: 50
	                },{
	                    label: 'fd_year',
	                    name: 'fd_year',
	                    width: 50
	                },{
	                    label: '异常编码',
	                    name: 'fd_dsjzc_cwbm',
	                    width: 50
	                },{
	                    label: '异常数目',
	                    name: 'fd_dsjzc_cwl',
	                    width: 50
	                },{
	                    label: '异常字段',
	                    name: 'fd_dsjzc_cwms',
	                    width: 50
	                },{
	                    label: '异常描述',
	                    name: 'fd_dsjzc_cws',
	                    width: 50
	                }  ] */,
	                //sortname: 'CustomerID',
	                //sortorder: 'asc',
	                page: 1,
	                autowidth: true,
	                autoheight: true,
	                viewrecords: true,
	                shrinkToFit:flag, 
	                autoScroll: true,
	                pager: "#pager",
	                loadComplete : function() {
	                    var result=eval("("+'${str}'+")");
	                   // alert("这个方法是执行加载数据完成之后的回调方法。我们可以尝试在此之后更新第13行数据。");
	                    $("#grid4").jqGrid( 'hideCol', "fd_dsjzc_cwl");
	                    $("#grid4").jqGrid( 'hideCol',"fd_dsjzc_cwms");
	                    $("#grid4").jqGrid( 'hideCol',"fd_dsjzc_cwbm");
	                    var wid=parseInt($(window).width())-30;
	                    var height=parseInt($(window).height())-70-38-38-15;
	                    $("#grid4").setGridWidth(wid);
	                    console.log(result.length+"...................");
	                    var gridConHei=result.length*34;
	                    if(gridConHei>height){
	                    	$("#grid4").setGridHeight(height);
	                    }else{
	                    	$("#grid4").setGridHeight(gridConHei);
	                    }
	                    
	                    for(var i = 0; i <result.length; i++){	
	                    	var na=result[i].fd_dsjzc_cwl;
	                    	var cwlm=na.split("|");
	                    	var cwbm=result[i].fd_dsjzc_cwbm;
	                    	var cwms=result[i].fd_dsjzc_cwms;
	                    	var content=gettitleValue(cwlm, cwbm, cwms);
	                    	for (var k = 0; k < cwlm.length; k++) {
		                    	for (var j = 0; j < dataObj.length; j++) {
		                    		  
									  if(cwlm[k]==dataObj[j].name){
										  var str= $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).text();
			                    		  $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).text("").append("<div>"+str+"</div>");
										  $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).css("background-color","red");  
										  $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).find("div").attr("data-toggle","tooltip").attr("data-container","body").attr("data-trigger","hover").attr("data-html",true).attr("data-placement","bottom").attr("data-content", content[k]);
										  $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).find("div").popover();
									  }
								}
							}

	                    }

	                  }
	            });

	            $('#grid4').navGrid("#pager", {
	                search: false, // show search button on the toolbar
	                add: false,
	                edit: false,
	                del: false,
	                refresh: false
	            });



	        })

	    })

	    //得到title属性的值
	     function gettitleValue(cwlm,cwbm,cwms){
	    	 var htmls=[]; 
	    	 var bianma=cwbm.split("|");
	    	 var cwms=cwms.split("|");
	    	 var zdmc=cwlm.sort();
	    	 for (var i = 0; i < zdmc.length; i++) {
	    		 htmls[i]=bianma[i]+":"+cwms[i]+"</br>";
			  }
	    	 //得到重复元素,重复元素的下标
	    	 var res=[];	    	 
	    	 for(var i = 0;i<zdmc.length;)  
	    	 {  
	    	    
	    	  var count = 0;  
	    	  var index=[];
	    	  for(var j=i;j<zdmc.length;j++)  
	    	  {  
	    	        
	    	   if(zdmc[i] == zdmc[j])  
	    	   {  
	    	        count++; 
	    	    	index.push(j);

	    	   }  
	    	     
	    	  }  
	    	  res.push([zdmc[i],index]);  
	    	  i+=count;  	    	    
	    	 }  
	    	 //根据重复元素的下标，拼接出相应异常的数据
	    	var xx="";
	    	 for (var j = 0; j < res.length; j++) {
				if(res[j][1].length>1){
			    	 for (var i = 0; i < res[j][1].length; i++) {
			    		 var idx=res[j][1];			    		 
						 xx=xx+htmls[idx[i]];
					}
					htmls[res[j][1][res[j][1].length-1]]=xx;
					xx="";
				}
			}	    	 
	    	return htmls;
	    }
	    
	})
    </script> 
</body>
</html>