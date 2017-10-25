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
                <a href="${basePath}/plugins/datasupport/clmonitor.jsp" class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i> 返回上页
                </a>
            </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>启动时间</th>
                    <th>结束时间</th>
                    <th>运行参数</th>
                    <th>运行结果</th>
                    <th>处理数据总数</th>
                    <th>备注</th>
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
            <div id="layer-editor" class="u-padding-top15 up-container-fluid" style="display: none">
       <div class="up-form-inline">
		    <div class="up-form-group up-col-sm-24">
		        <textarea class="up-form-control" id="footms" style="width:100%;" rows="8" readonly ="readonly"></textarea>    
		    </div>
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
                 url : "${basePath}/monitorrule/getRuledetail?jobid="+'${jobid}',
                success:function(data){
                	recordList=data.list;
                	//console.log(recordList);
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                        if(recordList[i].bzxx == "" ||recordList[i].bzxx == null){
                            recordList[i].bzxx = "";
                        }
                		infoStr=infoStr+"<tr><td>"+recordList[i].FD_QDSJ+"</td><td>"+
                		recordList[i].FD_JSSJ+"</td><td>"+recordList[i].FD_YXCS+"</td><td>"+
                		recordList[i].FD_ZXJG+"</td><td>"+recordList[i].FD_CLJLS+"</td><td><button class=\"up-btn up-btn-default up-btn-xs\" bzxx='"+recordList[i].bzxx+"' data-op=\""+recordList[i].ID+"\" id=\"option_"+recordList[i].ID+"\">备注信息</button></td></tr>";
                		
                	}
                	$("tbody").empty();
                	$("tbody").append(infoStr); 
                	$("button[id^='option_']").each(function(){
        				var opIndex=$(this).attr("data-op");
        				var bzxx=$(this).attr("bzxx");
        				var opId="#option_"+opIndex;
        				$(document).off("click",opId);
        				$(document).on("click",opId,function(){
        					require(['layer'], function() {
        						 layer.open({
		                                type: 1,
		                                title: '脚本日志',
		                                content: $("#layer-editor"),
		                                btn: ['确定'],
		                                btn2: function(index, layero) {		       
		                                    layer.close(index);
		                                },
		                                yes:function(index, layero) {
		                                	layer.close(index);
		                                },
		                                success: function(layero, index){
		                                	$("#footms").text(bzxx);
		                                },
		                                area: '700px'
        						 
        						 })
        						
        						
        					})
        					
        				})
                		
                		
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
        
/*         $("#selbtn").click(function() {
        	$.post('${basePath}/rulemacontroller/getRulelistdataBygzmc', {opt:$("#optext").val()},
        			function(data) {
            	recordList=data.list;
            	console.log(typeof data);
            	var infoStr = '';	
            	for(var i=0;i<recordList.length;i++){
            		infoStr=infoStr+"<tr><td>"+recordList[i].FD_GZMC+"</td><td>"+
            		recordList[i].srsjy+"</td><td>"+recordList[i].srysjb+"</td><td>"+
            		recordList[i].scsjy+"</td><td>"+recordList[i].scysjb+"</td><td>"+recordList[i].R_JOB_NAME+
            		"</td><td>"+recordList[i].R_JOB_DESC+
            		"</td><td><button class=\"up-btn up-btn-default up-btn-xs\" data-op=\""+recordList[i].ID+"\" id=\"rule_"+recordList[i].ID+"\">编辑</button></td>";;
            	}
            	$("tbody").empty();
            	$("tbody").append(infoStr); 
            	
        		$("button[id^='rule_']").each(function(){	
        			var jobname=$(this).attr("data-op");
        			var opId="#rule_"+jobname;
        			//alert(opId+"///"+jobname);
        			//console.log(opId);
        			$(document).on("click",opId,function(){
        				window.location.href="${basePath}/rulemacontroller/editRuledata?jobname="+jobname; 
        			});
        		});
        	},'json');
        })       */

      
    })
    </script>
</body>
</html>
