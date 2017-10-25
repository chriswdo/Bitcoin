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
                    <label for="">规则名称 </label>
                    <input type="text" class="up-form-control" id="optext" placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" class="up-btn up-btn-sm up-btn-primary up-btn-block" id="selbtn"><i class="icon-u-search"></i>查询</button>
                </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>规则名称</th>
                    <th>任务名称</th>
                    <th>总调运行次数</th>
                    <th>运行成功次数</th>
                    <th>运行失败次数</th>
                    <th>最近一次运行时间</th>
                    <th>处理数据总数</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
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
                url : "${basePath}/monitorrule/getRulemonitordata?lx=2",
                success:function(data){
                	console.log(data);
                	recordList=data.list;
                	//console.log(recordList);
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                		infoStr=infoStr+"<tr><td>"+recordList[i].FD_GZMC+"</td><td>"+
                		recordList[i].R_JOB_NAME+"</td><td>"+recordList[i].all+"</td><td>"+
                		recordList[i].success+"</td><td>"+recordList[i].fail+"</td><td>"+recordList[i].retime+
                		"</td><td>"+recordList[i].datacount+
                		"</td><td><button class=\"up-btn up-btn-default up-btn-xs\" data-op=\""+recordList[i].ID+"\" id=\"rule_"+recordList[i].ID+"\">运行日志</button></td></tr>";;
                	}
                	$("tbody").empty();
                	$("tbody").append(infoStr); 
                	
            		$("button[id^='rule_']").each(function(){	
            			var jobid=$(this).attr("data-op");
            			var opId="#rule_"+jobid;
            			//alert(opId+"///"+jobname);
            			//console.log(opId);
            			$(document).on("click",opId,function(){
            				window.location.href="${basePath}/monitorrule/jumpPage?jobid="+jobid+"&flag=2"; 
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
          
            
        });
        
        $("#selbtn").click(function() {
	       	 var params={gzmc: $("#optext").val()}
	    	 $("#u_pagination").pagination('setParams',params);
	    	 $("#u_pagination").pagination('remote');
        })      

      

    </script>
</body>
</html>
