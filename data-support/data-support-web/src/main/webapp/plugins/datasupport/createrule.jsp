<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
        <form class="u_form up-clearfix" style="max-width: 700px;">
            <h5><strong>新建/修改规则</strong></h5>
            <div class="up-form-group up-col-sm-24">
                <label class="up-control-label">规则名称   </label>
                <div class="up-col-sm-12">
                    <input type="text" name="" id="rulename1"  class="up-form-control" placeholder="">
                </div>
            </div>
            <div class="up-form-group up-col-sm-12">
                <label for="" class="up-control-label">输入数据源   </label>
                <div class="up-col-sm-24">
                    <select name="" id="sr" class="up-form-control">
                    <option value="">请选择</option>
						<c:forEach items="${selist}" var="item">		
						     <option value="${item.ID}" >${item.FD_MC}</option>
						</c:forEach>
                    </select>
                </div>
            </div>
            <div class="up-form-group up-col-sm-12">
                <label for="" class="up-control-label">输出数据源   </label>
                <div class="up-col-sm-24">
                    <select name="" id="sc" class="up-form-control">
                      <option value="">请选择</option>
						<c:forEach items="${selist}" var="item">		
						     <option value="${item.ID}" >${item.FD_MC}</option>
						</c:forEach>
                    </select>
                </div>
            </div>
            <div class="up-form-group up-col-sm-12">
                <label for="" class="up-control-label">输入表   </label>
                <div class="up-col-sm-24">
                    <select name="" id="srb" class="up-form-control">
                       
                    </select>
                </div>
            </div>
            <div class="up-form-group up-col-sm-12">
                <label for="" class="up-control-label">输出表   </label>
                <div class="up-col-sm-24">
                    <select name="" id="scb" class="up-form-control">
                       
                    </select>
                </div>
            </div>
            <div class="up-form-group up-col-sm-24">
                <label class="up-control-label">数据操作   </label>
                <div class="up-col-sm-24">
                    <div class="u_form_textmiddle"  id="jobid">请选择job</div>
                    <input type="text" style="display:none"  val=""  id="jobdesc" />
                </div>
            </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover" style="max-width: 700px">
            <thead>
                <tr class="up-active">
                    <th>序号</th>
                    <th>数据操作</th>
                    <th>操作描述</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>             
                
            </tbody>
        </table>
        <div class="up-row" style="max-width: 700px">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
        <div class="u_form u-margin-top20" style="max-width: 700px">
            <div class="up-form-group">
                <label class="up-control-label"></label>
                <div class="up-col-sm-12 up-text-center">
                    <button class="up-btn up-btn-primary" id="okbtn">确 定</button>
                </div>
                <div class="up-col-sm-12">
                    <button class="up-btn" id="nobtn">取 消</button>
                </div>
            </div>
        </div>
    </div>
    <script>
    require(['uskin'], function() {
    	var opIndex;
    	var gzid;
    	$(function(){
  		  if('${uniqueid}'=="edit"){
  		    // layer.alert("A");
  		  // var data = "${relist}";
  		  var data=eval("("+'${relist}'+")");
  		//console.log(data); 
  		//layer.alert(data[0].FD_GZMC);
  		   //var  woshilist = data.replace('[','').replace(']','').split(',');
  		   $("#rulename1").val(data[0].FD_GZMC); 
  		   $("#sr").val(data[0].FD_SRSJY_ID);
  		   $("#sc").val(data[0].FD_SCSJY_ID);
  		   $("#srb").html("<option value=\""+data[0].FD_SRB_ID+"\" >"+data[0].srsjy+"</option>");
  		   $("#scb").html("<option value=\""+data[0].FD_SCB_ID+"\" >"+data[0].scsjy+"</option>");
  		   $("#jobid").html(data[0].NAME); 
  		   gzid=data[0].ID;
  		   //layer.alert(gzid);
  		 //$("#jobid").attr();
  		   //console.log(woshilist);  		     
  		   //console.log(typeof name);
  		   
  		   //$("#rulename").html(); 
  		 //  $(".selector").val("pxx");
		  }
        });
    	
    	
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
                url : basePath+"/rulemacontroller/getJoblist",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                		//console.log(typeof recordList[i].ID_JOB);
                        if(recordList[i].DESCRIPTION == "" ||recordList[i].DESCRIPTION == null){
                            recordList[i].DESCRIPTION = "";
                        }
                		infoStr=infoStr+"<tr><td>"+recordList[i].ID_JOB+"</td><td>"+
                		recordList[i].NAME+"</td><td>"+recordList[i].DESCRIPTION+
                		"</td><td><button class=\"up-btn up-btn-default up-btn-xs\"  data-des=\""+recordList[i].DESCRIPTION+"\"  data-op=\""+recordList[i].NAME+"\" data-id=\""+recordList[i].ID_JOB+"\"  id=\"option_"+recordList[i].ID_JOB+"\">选择</button></td>";;
                	}
                	$("tbody").empty();
                	$("tbody").append(infoStr); 
                	
            		$("button[id^='option_']").each(function(){	
            			var jobname=$(this).attr("data-op");
            			var jobdesc=$(this).attr("data-des");
            			opIndex=$(this).attr("data-id");
            			var opId="#option_"+opIndex;
            			//layer.alert(opId+"///"+jobname);
            			//console.log(opId);
            			$(document).on("click",opId,function(){
            				opIndex=$(this).attr("data-id");
            				//layer.alert(opIndex);
            	               $("#jobid").empty();
            	               $("#jobid").append(jobname);
            	               $("#jobdesc").val("");
            	               $("#jobdesc").val(jobdesc);
            	               
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

             $(document).on("change","#sc",function() {
            	// layer.alert("a");
               $.post(basePath+"/rulemacontroller/getSrbiao", {opt:$("#sc").val()},
		           function(data) {
            	   //console.log(typeof data);
                       
               	seclist=data.list;
               	var selyuan='';
               	for(var i=0;i<seclist.length;i++){
               		selyuan=selyuan+"<option value=\""+seclist[i].ID+"\" >"+seclist[i].FD_BM+"</option>";
               		
               	}
               	$("#scb").empty();
               	$("#scb").html(selyuan); 
               
	   
                  },"json");     
         });  
   
        $(document).on("change","#sr",function() {
        	//var opt=$(this).find("option:selected").val();
            $.post(basePath+"/rulemacontroller/getSrbiao", {opt:$("#sr").val()},
		           function(data) {
         	   //console.log(typeof data);
                   // layer.alert("a");
            	seclist=data.list;
            	//console.log(seclist+"//////");
            	var selyuan='';
            	for(var i=0;i<seclist.length;i++){
            		selyuan=selyuan+"<option value=\""+seclist[i].ID+"\" >"+seclist[i].FD_BM+"</option>";
            		
            	}
            	$("#srb").empty();
            	$("#srb").html(selyuan); 
          
	   
               },"json");     
            });   
  require(['layer'], function () {       
        $("#okbtn").click(function() {
        	if($("#jobid").html()=="请选择job"){
        		 layer.alert("请选择job!");
        		 return;
        	}
        	var param = {rulename:$("#rulename1").val(),sryid:$("#sr").val(),srbid:$("#srb").val(),scyid:$("#sc").val(),scbid:$("#scb").val(),jobid:opIndex,jobname:$("#jobid").html(),jobdesc:$("#jobdesc").val(),gzid:gzid};
        	if('${uniqueid}'=="edit"){
            	$.ajax({
            		type : "POST",
            		url : '${basePath}/rulemacontroller/updateRuledata',
            		dataType : "text",
            		data: param,
            		success: function (data) {
                        if(data=="ok"){
                            layer.alert("操作成功!",{closeBtn:0},function(){
                            	window.location.href="${basePath}/plugins/datasupport/rulelist.jsp"
        						});
                         }else{
                        	 layer.alert("操作失败！");
                         }
            	    }
            	});
        	}else{
        		//layer.alert($("#scb").val());
        		if($("#rulename1").val()=="" || $("#sr").val()=="" || $("#sc").val()=="" || $("#srb").val()==null || $("#scb").val()==null){
        		    layer.alert("请输入相应的内容!");
        		    return;
        		}
        	$.ajax({
        		type : "POST",
        		url : '${basePath}/rulemacontroller/saveRuledata',
        		dataType : "text",
        		data: param,
        		success: function (data) {
                    if(data=="ok"){
                    	opIndex="";
                        layer.alert("操作成功!",{closeBtn:0},function(){
                        	window.location.href="${basePath}/plugins/datasupport/rulelist.jsp"
    						});
                     }else if(data=="repeat"){
                    	 layer.alert("规则名称已存在,请重新输入！");
                     }else{
                    	 layer.alert("操作失败！");
                     }
        	    }
        	});
        	}
        }) 
     })     
   
        $("#nobtn").click(function() {
        	window.location.href="${basePath}/plugins/datasupport/rulelist.jsp";
        }) 
        
    })
    </script>
</body>
</html>


