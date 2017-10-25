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
    <style type="text/css">
        .process-con{
            padding-top:10px;
        }
        .process-con ul{
        	
        }
        .process-con ul li{
            list-style: none;
            float: left;
        }
        .process-l{
            position: relative;

        }
        .process-l i{
            font-size: 20px;
            border:1px solid #3c6481;
            border-radius: 4px;
        }
        .process-l p{
            position: absolute;
            top:32px;
            left:-7px;
            font-size: 14px;
            white-space: nowrap;
        }
        .color-g{
            color:#2dbe2f;
        }
        .color-b{
            color:#3c6481;
        }
        .color-w{
            color:#FFF;
        }
        .color-y{
            color:#ffc673;
        }
        .process-r{
            width:80px;
            height:1px;
            background: #2dbe2f;
            margin-top: 14px;
            position: relative;
        }
        .process-r i{
            font-size: 16px;
        }
        .process-r i.icon-u-suo{
            position: absolute;
            top:-13px;
            left:16px;
            font-size: 20px;
        }
        .process-r i.icon-u-youjiantou{
            position: absolute;
            top:-10px;
            right:10px;
        }
        .process-r i.icon-u-gou1{
            font-size: 12px;
            background: #2dbe2f;
            border-radius: 12px;
            position: absolute;
            top:-8px;
            left:20px;
            list-style: 14px;
        }
    </style>
<body class="u_scroll">
<div class="up-container-fluid">
		<form
			class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
			<div class="up-form-group">
				<label for="">规则名称 </label> <input type="text"
					class="up-form-control" id="optext" placeholder="">
			</div>
			<div class="up-form-group">
				<button type="button"
					class="up-btn up-btn-sm up-btn-primary up-btn-block" id="selbtn">
					<i class="icon-u-search"></i>查询
				</button>
			</div>
			<div class="up-form-group">
				<button type="button"
					class="up-btn up-btn-sm up-btn-default up-btn-block" id="caijiadd">
					<i class="icon-u-add-round"></i>新增
				</button>
			</div>
		</form>
		<table class="up-table up-table-bordered up-table-hover">
			<thead>
				<tr class="up-active">
					<th>规则名称</th>
					<th>输入数据源</th>
					<!-- <th>输入表</th> -->
					<th>输出数据源</th>
					<!-- <th>输出表</th> -->
					<th>数据采集动作</th>
					<th>数据采集描述</th>
					<th width="135">编辑</th>
				</tr>
			</thead>
			<tbody id="u_pagination_body">
			</tbody>
		</table>
		<div class="up-row">
			<div class="up-col-md-24">
				<div id="u_pagination" class="up-pull-right"></div>
			</div>
		</div>
	</div>
	<!--job弹框 -->
		<div id="layer-check" class="u-padding-top15 up-container-fluid"
				style="display: none">
			<div class="up-form-group " style="">
				<label for="" class="up-control-label up-col-sm-2" style="line-height:26px;padding:0;margin-bottom:15px">Job名称 </label>
				<div class="up-col-sm-18" style="padding-left:0;margin-bottom:15px"> 
					<input type="text" class="up-form-control" id="jobtext" placeholder="">
				</div>
				<div class="up-col-sm-4" style="padding-left:0;margin-bottom:15px">
						<button type="button"
							class="up-btn up-btn-sm up-btn-primary up-btn-block up-col-md-6" id="jobbtn">
							<i class="icon-u-search"></i>查询
						</button>
				</div>
			</div>				
				<table class="up-table up-table-bordered up-table-hover"
					style="max-width: 700px">
					<thead>
						<tr class="up-active">
							<th width="50">序号</th>
							<th>数据操作</th>
							<th>操作描述</th>
							<th width="135">操作</th>
						</tr>
					</thead>
					<tbody id="u_paginationadd_body">

					</tbody>
				</table>
				<div class="up-row" style="max-width: 700px">
					<div class="up-col-md-24">
						<div id="u_paginationadd" class="up-pull-right"></div>
					</div>
				</div>
			</div>

 <!--job详情页面  -->
		<div class="up-container-fluid" style="display: none;padding:0" id="layer-jobdetail">
			<form class="u_form up-clearfix" style="width: 450px;margin:20px auto 0">
				<div class="up-col-sm-24" style="margin:0px auto 20px">
						<div class="process-con" id="process_div">
							<ul class='up-clearfix' id="process_ul" style="padding-left:-40px"> 
							</ul>
				        </div>
				</div>
				<div class="up-form-group up-col-sm-24">
					<label class="up-control-label">输入</label>
					<div class="up-col-sm-18">
					 <textarea class="up-form-control" id="import_table" style="width:100%;" rows="3" readonly ="readonly"></textarea> 
<!-- 						<input type="text" name="" id="import_table" class="up-form-control"
							placeholder=""> -->
					</div>
				</div>
				<div class="up-form-group up-col-sm-24">
					<label class="up-control-label">输出</label>
					<div class="up-col-sm-18">
					   <textarea class="up-form-control" id="export_table" style="width:100%;" rows="3" readonly ="readonly"></textarea> 
<!-- 						<input type="text" name="" id="export_table" class="up-form-control"
							placeholder=""> -->
					</div>
				</div>
				<div class="up-form-group up-col-sm-24">
					<label class="up-control-label">检验规则</label>
					<div class="up-col-sm-18">
<!-- 						<input type="text" name="" id="rulename1" class="up-form-control"
							placeholder=""> -->
                       <textarea class="up-form-control" id="check_rule" style="width:100%;" rows="6" readonly ="readonly"></textarea>    							
					</div>
				</div>								
			</form>
		</div>
		
		<!-- 规则配置新增弹框 -->
				<div class="up-container-fluid" style="display: none" id="layer-caiji">
			<form class="u_form up-clearfix" style="width: 600px;margin:20px auto 0">
				<div class="up-form-group up-col-sm-24">
					<label class="up-control-label">规则名称 </label>
					<div class="up-col-sm-17">
						<input type="text" name="" id="rulename1" class="up-form-control"
							placeholder="">
					</div>
				</div>
				<div class="up-form-group up-col-sm-24">
					<label for="" class="up-control-label">输入数据源 </label>
					<div class="up-col-sm-17">
						<select name="" id="sr" class="up-form-control">
							<option value="">请选择</option>
							<c:forEach items="${selist}" var="item">
								<option value="${item.id}">${item.fd_mc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
<!-- 				<div class="up-form-group up-col-sm-24">
					<label for="" class="up-control-label">输入表 </label>
					<div class="up-col-sm-17">
						<select name="" id="srb" class="up-form-control">

						</select>
					</div>
				</div>	 -->			
				<div class="up-form-group up-col-sm-24">
					<label for="" class="up-control-label">输出数据源 </label>
					<div class="up-col-sm-17">
						<select name="" id="sc" class="up-form-control">
							<option value="">请选择</option>
							<c:forEach items="${selist}" var="item">
								<option value="${item.id}">${item.fd_mc}</option>
							</c:forEach>
						</select>
					</div>
				</div>
<!-- 				<div class="up-form-group up-col-sm-24">
					<label for="" class="up-control-label">输出表 </label>
					<div class="up-col-sm-17">
						<select name="" id="scb" class="up-form-control">

						</select>
					</div>
				</div> -->
				<div class="up-form-group up-col-sm-24">
					<label class="up-control-label">数据操作 </label>
					
						<div class="up-col-sm-17">
							<input type="text" name="fd_gzmc" id="jobid"
								class="up-form-control" placeholder="" readonly="readonly">
						</div>
					<div class="up-form-group up-col-sm-3" style="margin-bottom:0;margin-left:5px;">
						<button type="button"
								class="up-btn up-btn-sm up-btn-default up-pull-right u-check"
								id="seljob">
								<i class="icon-u-check"></i>选择
						</button>					
				</div>
				<div class="up-form-group up-col-sm-3" id="divbtn" style="margin-bottom:0;margin-left:5px;display: none">
						<button type="button"
								class="up-btn up-btn-sm up-btn-default up-pull-right u-check"
								id="dejob">
								<i class="icon-u-check"></i>详情
						</button>					
				</div>
						<input type="text" style="display: none" val="" id="r_kettlejob" />	
						<input type="text" style="display: none" val="" id="jobdesc" />	
						<input type="text" style="display: none" val="" id="update_jobid" />
						<input type="text" style="display: none" val="" id="index_id" />						
				</div>
			</form>
		</div>
    <script>
    function getContentArray(arr){
    	var innerHtml='';
		   for (var i = 0; i < arr.length; i++) {
    			 innerHtml+=arr[i]+"\n"
			   }
		return innerHtml
    }
    
    function findSPECIAL(chart){
    	for(var i=0;i<chart.length;i++){
    		if(chart[i].CODE=='SPECIAL'){
    			return chart[i];
    		}
    	}
    }
    
    function findTransById(id,chart){
    	for(var i=0;i<chart.length;i++){
    		if(chart[i].ID_JOBENTRY==id){
    			return chart[i];
    		}
    	}
    }
    
    function generateTrans(innerHtml,comp,chart){
		if(comp.ID_JOBENTRY_COPY_TO!=null){
			var trans = findTransById(comp.ID_JOBENTRY_COPY_TO,chart);
			if(trans.ID_JOBENTRY_COPY_TO!=null){
				var nameNew = trans.NAME;
				var namePX  = ((nameNew.length-2)/2)*14+1;
				if(trans.NAME.length>=5){
					nameNew=trans.NAME.substring(0,5)+"...";
					namePX = 29;
				}
				
				innerHtml = innerHtml+
								      " <li class='process-l'> "+
								      "      <div title="+trans.NAME+"><i class='icon-u-quanpingtuichu color-b'></i></div> "+
								      "      <p style='left:-"+namePX+"px'>"+nameNew+"</p> "+
								      "  </li> "+
								      "  <li class='process-r'> "+
								      "      <i class='icon-u-gou1 color-w'></i> "+
								      "      <i class='icon-u-youjiantou color-g'></i> "+
								      "  </li> ";
				return generateTrans(innerHtml,trans,chart);
			}
		}
		return innerHtml;
    }
    
    function generateChart(chart){
    	$("#process_div").css({"width":"420px","height":"80px"}).addClass("u_scroll");
    	$("#process_ul").width(110+(chart.length-2)*110+30+50);
    	var innerHtml="";
    	var special = findSPECIAL(chart);
    	innerHtml=innerHtml+
						    "   <li class='process-l'> "+
							"       <div><i class='icon-u-bofang color-g'></i></div> "+
							"        <p>START</p> "+
							"    </li> "+
						    "   <li class='process-r'> "+
			                "    <i class='icon-u-suo color-y'></i> " +
			                "    <i class='icon-u-youjiantou color-g'></i> " +
			                " </li> ";
		 innerHtml = generateTrans(innerHtml,special,chart);
		 innerHtml = innerHtml + 
						"	       <li class='process-l'>	"+
						"         <div><i class='icon-u-gou1 color-g'></i></div> "+
						"         <p style='left:1px'>成功</p>		"+
						"     </li>		";
		  return innerHtml;
    }
    
    //kettlejob详情弹框页面
    function  getJobview(job_id){
        require(['layer'], function() {
            layer.open({
                type: 1,
                title: '规则详情',
                content: $("#layer-jobdetail"),
                success:function(index,layero){
                	$.ajax({
                		type : "POST",
                		url : '${basePath}/kettleJob/ajaxKettleJobInfo',
                		dataType : "json",
                		data: {jobId:job_id},
                		success: function (data) {
                  		   var arr_valid=data.valid;
                  		   var arr_in=data.input;
                  		   var arr_out=data.output;
                  		   var chart = data.chart;
                  		   var inhtml=getContentArray(arr_in);
                  		   var outhtml=getContentArray(arr_out);
                  		   var validhtml=getContentArray(arr_valid);
                  		   var charthtml= generateChart(chart);
                  		   $("#import_table").val(inhtml);
                  		   $("#export_table").val(outhtml); 
                  		   $("#check_rule").html(validhtml);   
                  		   $("#process_ul").html(charthtml);   
                		}
                	    })
                }, 
                area: [ '500px','550px']
            });
        })
    }
    
    $("#dejob").click(function() {
    	var r_job_id=$("#r_kettlejob").val();
  	    getJobview(r_job_id);
   })  
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
                url : "${basePath}/rulemacontroller/ajaxRulelistdataBygzmc?lx=2",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                		 if(recordList[i].r_job_desc == "" ||recordList[i].r_job_desc == null){
                             recordList[i].r_job_desc = "";
                         }
                 		infoStr=infoStr+"<tr><td>"+recordList[i].fd_gzmc+"</td><td tdvalue='"+recordList[i].fd_srsjy_id+"'>"+
                 		recordList[i].fd_srsjy_name+"</td><td  tdvalue='"+recordList[i].fd_scsjy_id+"'>"+
                 		recordList[i].fd_scsjy_name+"</td><td >"+recordList[i].r_job_name+
                 		"</td><td>"+recordList[i].r_job_desc+
                 		"</td><td><button class=\"up-btn up-btn-default up-btn-xs\" data-jobid=\""+recordList[i].r_job_id+"\"  data-op=\""+recordList[i].id+"\" id=\"rule_"+recordList[i].id+"\"><i class=\"icon-u-edit\"></i>编辑</button><button style=\"margin-bottom:0;margin-left:5px\"  class=\"up-btn up-btn-default up-btn-xs\"    id=\"fir_btn\"  onclick='getJobview(\""+recordList[i].r_job_id+"\")'><i class=\"icon-u-check\"></i>详情</button></td></tr>";
                	}
                	$("#u_pagination_body").empty();
                	$("#u_pagination_body").append(infoStr); 
                	editLayer();
/*             		$("button[id^='rule_']").each(function(){	
            			var jobname=$(this).attr("data-op");
            			var opId="#rule_"+jobname;
            			//alert(opId+"///"+jobname);
            			//console.log(opId);
            			$(document).on("click",opId,function(){
            				window.location.href="${basePath}/rulemacontroller/editddRuledata?jobname="+jobname; 
            			});
            		}); */
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
        
        //新增规则的分页    
        $("#u_paginationadd").pagination({
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
                url : basePath+"/rulemacontroller/getddJoblist",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                        if(recordList[i].description == "" ||recordList[i].description == null){
                            recordList[i].description = "";
                        }
                		infoStr=infoStr+"<tr><td>"+recordList[i].id_job+"</td><td>"+
                		recordList[i].name+"</td><td>"+recordList[i].description+
                		"</td><td><button class=\"up-btn up-btn-default up-btn-xs\"  data-des=\""+
                		recordList[i].description+"\"  data-op=\""+
                		recordList[i].name+"\" data-id=\""+recordList[i].id_job+
                		"\"  id=\"option_"+recordList[i].id_job+"\"><i class=\"icon-u-check\"></i>选择</button><button class=\"up-btn up-btn-default up-btn-xs\"  style=\"margin-bottom:0;margin-left:5px\"   id=\"detail_btn\"  onclick='getJobview(\""+recordList[i].id_job+"\")'><i class=\"icon-u-check\"></i>详情</button></td></tr>";
                	}
                	$("#u_paginationadd_body").empty();
                	$("#u_paginationadd_body").append(infoStr); 
                	
            		$("button[id^='option_']").each(function(){	
            			var jobname=$(this).attr("data-op");
            			var jobdesc=$(this).attr("data-des");
            			var opIndex=$(this).attr("data-id");
            			var opId="#option_"+opIndex;
            			$(document).off("click",opId);
            			$(document).on("click",opId,function(){
                		  require(['layer'], function() {
                			//layer.close(layer.index);
                			layer.close($("#index_id").val());
            	               $("#jobid").val("");
            	               $("#jobid").val(jobname);
            	               $("#jobdesc").val("");
            	               $("#jobdesc").val(jobdesc);
            	               $("#update_jobid").val("");
            	               $("#update_jobid").val(opIndex);
            	               
            			})
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
    //编辑规则弹框
        function editLayer(){
        	$("button[id^='rule_']").each(function(){
    			var jobname=$(this).attr("data-op");
    			var opId="#rule_"+jobname;
    			var data_job=$(this).attr("data-jobid");
				$(document).off("click",opId);
				$(document).on("click",opId,function(){
					require(['layer'], function() {
		            			    layer.open({
		                                type: 1,
		                                title: '编辑',
		                                content: $("#layer-caiji"),
		                                btn: ['确定', '取消'],
		                                btn2: function(index, layero) {		       
		                                    layer.close(index)
		                                },
		                                success: function(layero, index){
		                                	$("#divbtn").css('display','block'); 
		                                   	var gzpzObj = new Array();
		                                   //	var biao="";
		                                    $(opId).parent().parent().find("td").each(function(i){
		                                   	 if(!($(this).attr("tdvalue")==undefined)){
		                                   		gzpzObj[i]=$(this).attr("tdvalue");
		                                   	 }else if(!($(this).attr("tdbvalue")==undefined)){
		                                   		gzpzObj[i]=$(this).attr("tdbvalue");
		                                   		//biao=biao+$(this).text()+",";
		                                   	 }else{
		                                   		gzpzObj[i]=$(this).text(); 
		                                   	 }                         	 
		                                    });
		                                   
		                                   //var biaostr=biao.split(",");
		                                   $("#rulename1").val(gzpzObj[0]);
		                                   $("#sr").val(gzpzObj[1]);
		                          		   //$("#srb").html("<option value=\""+gzpzObj[2]+"\" >"+$.trim(biaostr[0])+"</option>");
		                                   $("#sc").val(gzpzObj[2]);
		                          		  // $("#scb").html("<option value=\""+gzpzObj[4]+"\" >"+biaostr[1]+"</option>");
		                          		   $("#jobid").val(gzpzObj[3]); 
		                          		   $("#update_jobid").val(data_job);
		                          		   $("#r_kettlejob").val(data_job);	
		                          		   $("#jobdesc").val(gzpzObj[4]);
		                          		   gzid=jobname;	
/* 		                                	$.ajax({
		                                		type : "POST",
		                                		url : '${basePath}/rulemacontroller/editRuledata',
		                                		dataType : "json",
		                                		data: {jobname:jobname},
		                                		success: function (data) {
				                                	var relist=data.relist;
				                                	//console.log(relist);
			 	                           		   $("#rulename1").val(relist[0].FD_GZMC); 
				                          		   $("#sr").val(relist[0].FD_SRSJY_ID);
				                          		   $("#sc").val(relist[0].FD_SCSJY_ID);
				                          		   $("#srb").html("<option value=\""+relist[0].FD_SRB_ID+"\" >"+relist[0].srsjy+"</option>");
				                          		   $("#scb").html("<option value=\""+relist[0].FD_SCB_ID+"\" >"+relist[0].scsjy+"</option>");
				                          		   $("#jobid").val(relist[0].R_JOB_NAME); 
				                          		   gzid=relist[0].ID;	 
		                                		   }
		                                	    });	 */	            		            		
		                                  },
		                                yes: function(index, layero) {
		                            		if($("#rulename1").val()=="" || $("#sr").val()=="" || $("#sc").val()=="" || $("#jobid").val()=="" ){
		                               		    layer.alert("请输入相应的内容!");
		                               		    return;
		                               		}			                                	
		                                	var param = {rulename:$("#rulename1").val(),sryid:$("#sr").val(),srbid:" ",scyid:$("#sc").val(),scbid:" ",jobid:$("#update_jobid").val(),jobname:$("#jobid").val(),jobdesc:$("#jobdesc").val(),gzid:gzid};
		                                	$.ajax({
		                                		type : "POST",
		                                		url : '${basePath}/rulemacontroller/updateddRuledata',
		                                		dataType : "text",
		                                		data: param,
		                                		success: function (data) {
		                                            if(data=="ok"){
		                                                layer.alert("操作成功!",{closeBtn:0},function(){
		                                                	window.location.href="${basePath}/rulemacontroller/getddruleDatapage"
		                            						});
		                                             }else{
		                                            	 layer.alert("操作失败！");
		                                             }
		                                	    }
		                                	});
		                                  },
		                                area:[ '650px','300px']
		            			    });   
		            })

				});
			});
        }
        //新增规则弹框
        $("#caijiadd").click(function() {
        	//新增规则前，清空弹框的内容
  		   $("#rulename1").val(""); 
		   $("#sr").val("");
		   $("#sc").val("");
		  // $("#srb").html("");
		  // $("#scb").html("");
		   $("#jobid").val(""); 
		   gzid="";	
            require(['layer'], function() {
                layer.open({
                    type: 1,
                    title: '新增',
                    btn: ['确定', '取消'],
                    content: $("#layer-caiji"),
                    btn2: function(index, layero) {		       
                        layer.close(index)
                    },
                    success: function(layero, index){
                    	$("#divbtn").css('display','none'); 
                      },
                    yes: function(index, layero) {
                     	if($("#jobid").val()==""){
                      		 layer.alert("请选择job!");
                      		 return;
                      	   }
                    		if($("#rulename1").val()=="" || $("#sr").val()=="" || $("#sc").val()==""){
                   		    layer.alert("请输入相应的内容!");
                   		    return;
                   		}
                 var param = {rulename:$("#rulename1").val(),sryid:$("#sr").val(),srbid:" ",scyid:$("#sc").val(),scbid:" ",jobid:$("#update_jobid").val(),jobname:$("#jobid").val(),jobdesc:$("#jobdesc").val()};
                	$.ajax({
                		type : "POST",
                		url : '${basePath}/rulemacontroller/saveddRuledata',
                		dataType : "text",
                		data: param,
                		success: function (data) {
                            if(data=="ok"){

                                layer.alert("操作成功!",{closeBtn:0},function(){
                                	window.location.href="${basePath}/rulemacontroller/getddruleDatapage"
                                	$("#jobid").val("");
            						});
                             }else if(data=="repeat"){
                            	 layer.alert("规则名称已存在,请重新输入！");
                             }else{
                            	 layer.alert("操作失败！");
                             }
                	    }
                	}); 
                     },
                    area:[ '650px','300px']
                });
            })
        });
    //选择job
        $("#seljob").click(function() {
        	if($("#sr").val()=="" || $("#sc").val()=="" ){
        		layer.alert("请选择数据源！");
                   return;
        	}
            require(['layer'], function() {
                layer.open({
                    type: 1,
                    title: '选择',
                    content: $("#layer-check"),
                    success:function(index,layero){
                    	$("#jobtext").val("");
                    	//得到弹框的index
                    	$("#index_id").val(layer.index);
                    	var params={opt: "",sryid:$("#sr").val(),scyid:$("#sc").val()}
        	    	    $("#u_paginationadd").pagination('setParams',params);
                    	$("#u_paginationadd").pagination('remote');
                    	$(index.selector).find(".u_height").height(300).addClass("u_scroll");
                    }, 
                    area: [ '700px','340px']
                });
            })
        })
        
/*         $(document).on("change","#sc",function() {
        	// layer.alert("a");
           $.post(basePath+"/rulemacontroller/getSrbiao", {opt:$("#sc").val()},
	           function(data) {
        	   //console.log(typeof data);
                   
           	seclist=data.list;
           	var selyuan='';
           	for(var i=0;i<seclist.length;i++){
           		selyuan=selyuan+"<option value=\""+seclist[i].id+"\" >"+seclist[i].fd_bm+"</option>";
           		
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
        		selyuan=selyuan+"<option value=\""+seclist[i].id+"\" >"+seclist[i].fd_bm+"</option>";
        		
        	}
        	$("#srb").empty();
        	$("#srb").html(selyuan); 
      
   
           },"json");     
        });  */
    
        $("#selbtn").click(function() {
	       	 var params={opt: $("#optext").val()}
	    	 $("#u_pagination").pagination('setParams',params);
	    	 $("#u_pagination").pagination('remote');
        })  
     
        $("#jobbtn").click(function() {
	       	 var params={opt: $("#jobtext").val(),sryid:$("#sr").val(),scyid:$("#sc").val()}
	      
	    	 $("#u_paginationadd").pagination('setParams',params);
	    	 $("#u_paginationadd").pagination('remote');
     })       
    })
    </script>
</body>
</html>
