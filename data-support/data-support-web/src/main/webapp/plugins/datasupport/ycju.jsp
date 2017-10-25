<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">   
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
</head>

<body class="u_scroll">
    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
              <div class="up-form-group">
                <a href="${basePath}/plugins/datasupport/cjmonitor.jsp" class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i> 返回上页
                </a>
            </div>
<!--             <div class="up-form-group">
                    <button type="button" class="up-btn up-btn-primary up-btn-block" id="selbtn"><i class="icon-u-search"></i> 导 出</button>
                </div> -->
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>输入数据源</th>
                  <!--   <th>输入表</th> -->
                    <th>异常数据数</th>
                    <th>备注</th>
                    <th>结束时间</th>
                </tr>
            </thead>
            <tbody>
<%--                 <c:forEach items="${relist}" var="item">		
				     <tr><td>${item.fd_qdsj}</td><td>${item.fd_jssj}</td><td>${item.fd_yxcs}</td><td>${item.fd_zxjg}</td><td>${item.fd_cljls}</td><td>${item.fd_bzxx}</td></tr>    
				</c:forEach> --%>
            </tbody>
        </table>
        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
    </div>
    <!-- 异常数据弹框 -->
	<div class="up-container-fluid" id="layer-ycsj" style="display: none">
		<form class="up-form-inline u-margin-top20 u-margin-bottom20" action="">
<!-- 				<button type="button"
					class="up-btn up-btn-sm up-btn-default up-btn-block" id="exportid">
					<i class="icon-u-add-round"></i>导出
				</button> -->
<%-- 		              <div class="up-form-group">
		                <a href="${basePath}/ycjumonitor/exportexcel?jobid=${jobid}&yname=${yname}"   class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i> 导 出
		                </a>
		            </div> --%>
		 </form>   
		          
		     <div class="u_tab_body" id="example_tab4">
		           
		</div> 
	</div>
    <script>
    require(['uskin'], function() {

        $("#u_pagination").pagination({
        	pageIndex: 0,
            pageSize: 10,
            total: 60,
            pageBtnCount: 8,
            showFirstLastBtn: true,
            alwaysBtnShow: true,
            showJump: true,
            showPageSizes: true,
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
                 url : "${basePath}/monitorrule/getYcjudetail?jobid="+'${jobid}',
                success:function(data){

                	recordList=data.list;
                	//console.log(recordList);
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
  /*                       if(recordList[i].fd_bz == "" ||recordList[i].fd_bz == null){
                            recordList[i].fd_bz = "";
                        } */
                		infoStr=infoStr+"<tr><td>"+recordList[i].fd_srsjy_name+"</td><td><button class=\"up-btn up-btn-default up-btn-xs\"  data-ym=\""+recordList[i].fd_srsjy_name+"\"  data-op=\""+recordList[i].id+"\" id=\"rule_"+recordList[i].id+"\">异常数据</button></td><td>"+
                		recordList[i].fd_bz+"</td><td>"+recordList[i].fd_jssjTostring+"</td></tr>";
                	}
                            		
                	$("tbody").empty();
                	$("tbody").append(infoStr); 
            		$("button[id^='rule_']").each(function(){	
            			var jobid=$(this).attr("data-op");
            			var yname=$(this).attr("data-ym");
            			var opId="#rule_"+jobid;
            			//alert(opId);
            			//console.log(opId);
            			$(document).off("click",opId);
              			$(document).on("click",opId,function(){
              				$.ajax({
                        		type : "POST",
                        		url : "${basePath}/ycjumonitor/getExceptionJsonData",
                        		dataType : "json",
                        		data: {jobid:jobid,yname:yname},
                        		success: function (data) {
                              	  // var dataObj=eval("("+'${cols}'+")");//转换为json对象 
                            	  // var jsonObj='${str}';
                            	  var dataObj=data.cols;
                            	  var jsonObj=data.str;
                            	  var flag=true;
                            	   if(dataObj.length>10){
                            		   flag=false;
                            	   };
                            	   $("#example_tab4").html("");
                            	   $("#example_tab4").append('<table id="grid4" class="jqgrid"></table><div id="pager" style="height:40px;"></div> ');                           	   
                            	   getjqgridtable(jsonObj,dataObj,flag,jobid,yname); 
                        		 }
                        	 });	
        					
            			});
            		});
                
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
     
 	   function getjqgridtable(jsonObj,dataObj,flag,jobid,yname){
		    require(["jqGrid"], function(grid) { 
                var result=eval("("+jsonObj+")");
	        	$("#grid4").jqGrid({
	                datastr:result,
	                datatype: "jsonstring",
	                colModel: dataObj,
	                page: 1,
	                width: 800,
	                height:300,
	                viewrecords: true,
	                shrinkToFit:flag, 
	                autoScroll: true,
	                pager: "#pager",
	                loadComplete : function() {
	                  // console.log( typeof result); 
	                  
	                     $("#grid4").jqGrid( 'hideCol', "fd_dsjzc_cwl");
	                    $("#grid4").jqGrid( 'hideCol',"fd_dsjzc_cwms");
	                    $("#grid4").jqGrid( 'hideCol',"fd_dsjzc_cwbm");
	                    var initWid=800;
	                    var wid=initWid-35;
	                    //var height=parseInt($(window).height())-70-38-38-15;
	                    $("#grid4").setGridWidth(wid);
	                    /*console.log(result.length+"...................");
	                    var gridConHei=result.length*34;
	                    if(gridConHei>height){
	                    	$("#grid4").setGridHeight(height);
	                    }else{
	                    	$("#grid4").setGridHeight(gridConHei);
	                    }*/
	                    
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
			                    		  $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).text("").append("<div data-toggle='tooltip' data-container='body' data-trigger='click' data-html='true'  title='"+content[k]+"' data-placement='bottom'>"+str+"</div>");
										  $("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).css("background-color","red");  
										  //$("#grid4").find("tr.jqgrow").eq(i).find("td").eq(j).find("div").popover();
										  //$('[data-toggle="tooltip"]').tooltip();
		                            	  // var layerIndex=parseInt($(".layui-layer").css("z-index"))+1;
										  //$(".up-tooltip").css({"z-index":layerIndex});
									  }
								}
							}

	                    } 
	                    require(['layer'], function() {
      						 layer.open({
		                                type: 1,
		                                title: '异常数据',
		                                content: $("#layer-ycsj"),
		                                btn: ['导出'],
		                                yes:function(index, layero) {
		                                	window.location.href="${basePath}/ycjumonitor/exportexcel?jobid="+jobid+"&yname="+yname;
		                                	//layer.close(index);
		                                },
		                                success: function(layero, index){
		                                	
		                                },
		                                area: ['800px','500px']
      						 
      						 })
      						
      						
      					  })
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
	   }
 	   
	    //得到title属性的值
	     function gettitleValue(cwlm,cwbm,cwms){
	    	 var htmls=[]; 
	    	 var bianma=cwbm.split("|");
	    	 var cwms=cwms.split("|");
	    	 var zdmc=cwlm.sort();
	    	 for (var i = 0; i < zdmc.length; i++) {
	    		 htmls[i]=bianma[i]+":"+cwms[i]+"\n";
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