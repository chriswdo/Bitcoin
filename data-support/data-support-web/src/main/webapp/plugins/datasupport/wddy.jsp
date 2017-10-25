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
<style type="text/css">
  td{
     white-space:nowrap; 
     text-overflow:ellipsis;
     overflow:hidden;
   }
</style>
</head>

<body class="u_scroll">

    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">维度名称 </label>
                    <input type="text" class="up-form-control" id="optext" placeholder="">
                </div>

                <div class="up-form-group">
                    <button type="button" class="up-btn up-btn-sm up-btn-primary up-btn-block" id="selbtn"><i class="icon-u-search"></i>查询</button>
                </div>
                <div class="up-form-group">
				<button type="button"
					class="up-btn up-btn-sm up-btn-default up-btn-block" id="wddyadd">
					<i class="icon-u-add-round"></i>新增
				</button>                     
<%--                     <a href="${basePath}/wddyController/getwddyDatainpage" class="up-btn up-btn-sm up-btn-default u-add"> <i class="icon-u-add-round"></i>新增
                    </a> --%>
                </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>维度名称</th>
                    <th>维度描述</th>
                    <th>表名</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="tableBody">
            </tbody>
        </table>
        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
    </div>
 	<!-- 新增编辑维度定义表的第一层弹框-->
	<div id="layer-wddy" class="u-padding-top15 up-container-fluid"
		style="display: none">
		<form class="u_form" action="">
            <div class="up-form-group up-col-sm-24">
               <label class="up-control-label">维度名称 </label>
                <div class="up-col-sm-24">
                    <input type="text" name="" id="xxmc"  class="up-form-control" placeholder="">
                </div>
            </div>
             <div class="up-form-group up-col-sm-24">
               <label class="up-control-label">表                  名 </label>
                <div class="up-col-sm-24">
                    <input type="text" name="" id="xxbm"  class="up-form-control" placeholder="" > 
                </div>
            </div>
            <div class="up-form-group up-col-sm-24">
               <label class="up-control-label">维度描述 </label>
                <div class="up-col-sm-24">
                    <input type="text" class="up-form-control" name="" rows="1"   id="xxms" style="resize:none" />
                </div>
            </div>   
		</form>
		<table id="xxgrid" class="jqgrid"></table>
		<div id="pager" style="height: 40px;"></div>
	</div>
	<!-- 编辑表字段的第二层弹框 -->
	<div id="layer-editor" class="u-padding-top15 up-container-fluid"
		style="display: none">
		<form class="u_form" id="gridForm" style="">
			<table width="100%">
				<tbody>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">列名</label></td>
						<td width="80%"><input type="text" name="FD_ZDMC"
							id="FD_ZDMC" class="up-form-control" style="width: 60%;" /></td>
					</tr>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">字段类型</label></td>
						<td width="80%"><select name="FD_ZDLX" id="FD_ZDLX"
							class="up-form-control" style="width: 60%;">
								<option value="请选择类型">请选择类型</option>
								<option value="VARCHAR">VARCHAR</option>
								<option value="DATE">DATE</option>
								<option value="TIMESTAMP">TIMESTAMP</option>
								<option value="NUMBER">NUMBER</option>
								<option value="NUMERIC">NUMERIC</option>
						</select></td>
					</tr>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">字段长度</label></td>
						<td width="80%"><input type="text" id="FD_ZDCD"
							name="FD_ZDCD" class="up-form-control" style="width: 60%;" /></td>
					</tr>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">字段精度</label></td>
						<td width="80%"><input type="text" id="FD_ZDJD"
							name="FD_ZDJD" class="up-form-control" style="width: 60%;" /></td>
					</tr>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">缺省值</label></td>
						<td width="80%"><input type="text" id="FD_QSZ" name="FD_QSZ"
							class="up-form-control" style="width: 60%;" /></td>
					</tr>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">字段备注</label></td>
						<td width="80%"><input type="text" id="FD_ZDBZ"
							name="FD_ZDBZ" class="up-form-control" style="width: 60%;" /></td>
					</tr>
					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">是否主键</label></td>
						<td width="80%" id="checkId"><label class="u_check">
								<input type="radio" name="FD_SFZJ" value="Y" style="width: 30%;" />
								<i class="up-text-primary "></i>Y
						</label> <label class="u_check"> <input type="radio"
								name="FD_SFZJ" value="N" style="width: 30%;" checked="true" /> <i
								class="up-text-primary icon-u-ok-circle"></i>N
						</label></td>
					</tr>

					<tr>
						<td align="right" width="20%"><label class="up-control-label"
							style="padding-right: 15px;">是否可为空</label></td>
						<td width="80%"><label class="u_check"> <input
								type="radio" name="FD_SFKWK" value="Y" style="width: 30%;"
								checked="true" /> <i class="up-text-primary icon-u-ok-circle"></i>Y
						</label> <label class="u_check"> <input type="radio" id="nid"
								name="FD_SFKWK" value="N" style="width: 30%;" /> <i
								class="up-text-primary"></i>N
						</label></td>
					</tr>

				</tbody>
			</table>
		</form>
	</div>
    <script>
    require(['uskin'], function() {
        var editlist= new Array(); 
        var addlist= new Array(); 
        var fieldname=new Array();
        var randnum=0;
        var widthWindow=parseInt($(window).width())-30;
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
                url : "${basePath}/wddyController/getwdMessagedata",
                params: {meg:""},
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                		infoStr=infoStr+"<tr><td><div style='width:"+(widthWindow*0.25-16)+"px' >"+recordList[i].fd_wdmc+"</div></td><td><div style='width:"+(widthWindow*0.3-16)+"px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' >"+
                		recordList[i].fd_ms+"</div></td><td><div style='width:"+(widthWindow*0.25-16)+"px' >"+recordList[i].fd_bm+
                		"</div></td><td><div style='width:"+(widthWindow*0.2-16)+"px' ><button class=\"up-btn up-btn-default up-btn-xs\" data-op=\""+recordList[i].id+"\" id=\"xxfl_"+recordList[i].id+"\">编辑</button></div></td></tr>";
                	}
                	$("#tableBody").empty();
                	$("#tableBody").append(infoStr); 
                	editLayer();
/*             		$("button[id^='xxfl_']").each(function(){	
            			var myid=$(this).attr("data-op");
            			var opId="#xxfl_"+myid;
            			//alert(opId+"///"+jobname);
            			//console.log(opId);
            			$(document).on("click",opId,function(){
            				window.location.href="${basePath}/wddyController/editwddymetadata?megid="+myid; 
            			});
            		});  */
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
        
        
        $(document).on("click","#selbtn",function() {
       	 var params={meg:encodeURI($("#optext").val())}
       	 $("#u_pagination").pagination('setParams',params);
       	 $("#u_pagination").pagination('remote');
       	 
        });
        
        //维度定义新增--弹框      
        $("#wddyadd").click(function() {
        	 $("#xxbm").val("");
        	 $("#xxms").val("");
        	 $("#xxmc").val(""); 
        	// $("#xxgrid").jqGrid("clearGridData");
        	require(['layer'], function() {
        		layer.open({
                    type: 1,
                    title: '新增',
                    btn: ['确定', '取消'],
                    content: $("#layer-wddy"),
                    btn2: function(index, layero) {		       
                        layer.close(index);
                        addlist.splice(0,addlist.length);
                        editlist.splice(0,editlist.length);
                    },
                    success: function(layero, index){
                        fieldname.splice(0,fieldname.length);
                    	getJqgridofAdd();
                    	$("#xxbm").attr("readonly",false);  
                    	$("#xxmc").attr("readonly",false);  
                   	 
                      },
                    yes: function(index, layero) {
                    	if($("#xxmc").val()==""|| $("#xxbm").val()=="" ){
                    		layer.alert("请输入表名,名称!");   
                    		return;
                    	}
                    	if(addlist.length==0){
                    		layer.alert("请填写字段信息!");
                    		return;
                    	}
                        for (var i=0;i<addlist.length;i++)
                       	{    
                        	var zdmc=addlist[i].FD_ZDMC
                             for(var j=0;j<editlist.length;j++){
                            	 if(zdmc==editlist[j].FD_ZDMC){
                            		 editlist[j].FD_ZDSX=addlist[i].FD_ZDSX;
                            		 addlist[i]=editlist[j];
                            	 }
                            }
                       	}
                    	var param = {xxmc:$("#xxmc").val(),xxms:$("#xxms").val(),xxid:'xzxx',xxbm:$("#xxbm").val(),addlist:JSON.stringify(addlist)};
                        	$.ajax({
                        		type : "POST",
                        		url : '${basePath}/wddyController/addwddyMegdata',
                        		dataType : "text",
                        		data: param,
                        		success: function (data) {
                                    if(data=="ok"){
                                        layer.alert("操作成功!",{closeBtn:0},function(){
                                        	window.location.href="${basePath}/plugins/datasupport/wddy.jsp"
                    						});
                                     }else if(data=="repeat"){
                                    	 layer.alert("表名已存在!");
         	            			}else if(data=="sessionTimeout"){
        								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
        									layer.closeAll();
        								});
                                     }else{
                                    	 layer.alert("操作失败!");
                                     }
                        	    }
                        	});
                    },
                    area: [ '730px','550px']
        			
        		});
        		
        	});	
        	
        });   

        //编辑信息分类--弹框
        function editLayer(){
        	$("button[id^='xxfl_']").each(function(){	
    			var megid=$(this).attr("data-op");
    			var opId="#xxfl_"+megid;
				$(document).off("click",opId);
				$(document).on("click",opId,function(){
					require(['layer'], function() {
        			    layer.open({
                            type: 1,
                            title: '编辑',
                            content: $("#layer-wddy"),
                            btn: ['确定', '取消'],
                            btn2: function(index, layero) {		       
                                layer.close(index);
                                addlist.splice(0,addlist.length);
                                editlist.splice(0,editlist.length);
                            },
                            success: function(layero, index){
                                fieldname.splice(0,fieldname.length);
                            	getjqgridofEdit(megid);
                            	var wddyObj = new Array();
                                
                                $(opId).parent().parent().parent().find("td").each(function(i){
                                	wddyObj[i]=$(this).find("div").text();                       	 
                                });
                                
                                $("#xxmc").val(wddyObj[0]);
                                $("#xxms").val(wddyObj[1]); 
                                $("#xxbm").val(wddyObj[2]);
                               
/*                             	$.ajax({
                            		type : "POST",
                            		url : '${basePath}/wddyController/editwddymetadata',
                            		dataType : "json",
                            		data: {megid:megid},
                            		success: function (data) {
	                                	var obj=data.relist;
	                               	 $("#xxbm").val(obj.FD_BM);
	                            	 $("#xxms").val(obj.FD_MS);
	                            	 $("#xxmc").val(obj.FD_WDMC);
                            		   }
                            	    });	 */
                            	$("#xxbm").attr("readonly",true);  
                            	$("#xxmc").attr("readonly",true);  
                            		
                              },
                            yes: function(index, layero) {       
                            	var param = {xxmc:$("#xxmc").val(),xxms:$("#xxms").val(),xxid:megid,xxbm:$("#xxbm").val(),editlist:JSON.stringify(editlist),addlist:JSON.stringify(addlist)};
                                	$.ajax({
                                		type : "POST",
                                		url : '${basePath}/wddyController/updatewddyMegdata',
                                		dataType : "text",
                                		data: param,
                                		success: function (data) {
                                            if(data=="ok"){
                                                layer.alert("操作成功!",{closeBtn:0},function(){
                                                	window.location.href="${basePath}/plugins/datasupport/wddy.jsp"
                            						});
                                             }else if(data=="repeat"){
                                            	 layer.alert("字段名已存在!");
                                             }else if(data=="fail"){
                                            	 layer.alert("操作失败！");
                 	            			}else if(data=="sessionTimeout"){
                								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
                									layer.closeAll();
                								});
                                             }else{
                                            	 layer.alert(data);
                                             }
                                	    }
                                	});
                            	
                              },
                            area:[ '730px','550px']
        			    });   
                    })
					
					

				});
			});
        }   
        function getJqgridofAdd(){
       	 require(["jqGrid"], function(grid) {
       		 $("#xxgrid").jqGrid({
                    url: "${basePath}/wddyController/getwddymetadata",
                    datatype: "json",
                    postData:{xxid:"xzxx"},
                    mtype: "GET",
                    colModel: [{
                        label: 'IDD',
                        name: 'XID',
                        hidden:true,
                        editable: true,
                        sortable: false 
                    },{
                        label: '列名',
                        name: 'FD_ZDMC',
                        width:160,
                        editable: true,
                        sortable: false 
                    }, {
                        label: '字段类型',
                        name: 'FD_ZDLX',
                        width:90,
                        editable: true,
                        sortable: false 
                    },{
                        label: '长度',
                        name: 'FD_ZDCD',
                        width:40,
                        editable: true,
                        sortable: false 
                    },{
                        label: '精度',
                        name: 'FD_ZDJD',
                        width:40,
                        editable: true,
                        sortable: false 
                    },{
                        label: '是否主键',
                        name: 'FD_SFZJ',
                        width:70,
                        editable: true,
                        sortable: false 
                    },{
                        label: '缺省值',
                        name: 'FD_QSZ',
                        width:50,
                        editable: true,
                        sortable: false 
                    },{
                        label: '是否可为空',
                        name: 'FD_SFKWK',
                        width:80,
                        editable: true,
                        sortable: false 
                    },{
                        label: '字段描述',
                        name: 'FD_ZDBZ',
                        width:160,
                        editable: true,
                        sortable: false 
                    }],
                    page: 1,
                    scrollOffset:3,
                    shrinkToFit:false,
                    width: 690,
                    height: 150,
                    viewrecords: true,
                    pager: "#pager",
                    loadComplete:function(){
                   	 
                    }
                });

                $('#xxgrid').navGrid("#pager", {
                    search: false, // show search button on the toolbar
                    add: false,
                    edit: false,
                    del: false,
                    refresh: false
                });
                $("#xxgrid").setGridParam(
            		    {
            		    postData:{xxid:'xzxx'}
            		    }
            		) .trigger("reloadGrid"); 
            //自定义新增按钮
                //删除自定义按钮
                $("#pager_left tr").html("");
                //重新加载自定义按钮
                $("#xxgrid").jqGrid('navButtonAdd', '#pager', {
                    caption : "新增",
                    buttonicon:"icon-u-add-round",
                    onClickButton : function() {
                       	$('#FD_ZDMC').removeAttr("disabled"); 
                    	$('#FD_ZDLX').removeAttr("disabled"); 
                      	 $("#FD_ZDCD").removeAttr("disabled");
                       	 $("#FD_ZDJD").removeAttr("disabled");
                    	//$("#FD_ZDMC").attr("value","");
                    	$("#FD_ZDMC").val("");
                    	$("#FD_ZDCD").val("");
                    	$("#FD_ZDJD").val("");
                    	$("#FD_QSZ").val("");
                    	$("#FD_ZDBZ").val("");
                    	$("#FD_ZDLX").val("请选择类型"); 
                    	$("tr").parent().find("span.red").remove();
	                    $(":radio[name='FD_SFZJ'][value='N']").click();
		                $(":radio[name='FD_SFKWK'][value='Y']").click();                   	
                     	require(['layer'], function() {
    		                layer.open({
    		                    type: 1,
    		                    title: '增加',
    		                    content: $("#layer-editor"),
    		                    btn: ['确定', '取消'],
                                yes: function(index, layero) {
                                  	 var obj = document.getElementById("layer-editor");
                                	 var havespan = obj.getElementsByTagName("span");
                                 	if(havespan.length>0){
                             		   return;
                             	    }
                                 	if($("#FD_ZDMC").val()=="" ){
                                 	
                                 		$("#FD_ZDMC").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">请输入正确的列名</span>");
                                 		return;
                                 	}
                                 	else if($("#FD_ZDLX").val()=="请选择类型" ){
                                 		$("#FD_ZDLX").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">请选择类型</span>");
                                 		return;
                                 	}
                                 	else if($("#FD_ZDLX").val()=="VARCHAR"   && $("#FD_ZDCD").val()=="" ){

                                 		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">长度请输入1-4000</span>");
                                 		return;
                                 	}
                                 	else if(($("#FD_ZDLX").val()=="NUMERIC" ||  $("#FD_ZDLX").val()=="NUMBER" )   && $("#FD_ZDCD").val()=="" ){

                                 		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">长度请输入1-38</span>");
                                 		return;
                                 	}
                                 	else if($("#FD_ZDLX").val()=="NUMERIC" && $("#FD_ZDJD").val()=="" ){
                                 		$("#FD_ZDJD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">精度小于等于长度且大于等于0</span>");
                                 		return;
                                 	}
                                    if($('input:radio[name="FD_SFZJ"]:checked').val()=="Y" &&  $('input:radio[name="FD_SFKWK"]:checked').val()=="Y")
                                    {
                                   	 layer.alert("主键不能为空!");   
                                   	 return;
                                    } 
                                    //判断字段重复
                                    var addMC=$("#FD_ZDMC").val();  
   					            for (var i=0;i<fieldname.length;i++)
   					        	{
   					          	  if(addMC==fieldname[i]){
   					        		  layer.alert("字段名重复，请重新填写!");
   					        		  return;
   					        	  }
   					        	} 
   					            ++randnum;
   					            var adddata={FD_ZDSX:randnum,FD_ZDMC:$("#FD_ZDMC").val(),FD_ZDLX:$("#FD_ZDLX").val(),FD_ZDCD:$("#FD_ZDCD").val(),FD_ZDJD:$("#FD_ZDJD").val(),FD_SFZJ:$('input:radio[name="FD_SFZJ"]:checked').val(),FD_QSZ:$("#FD_QSZ").val(),FD_SFKWK:$('input:radio[name="FD_SFKWK"]:checked').val(),FD_ZDBZ:$("#FD_ZDBZ").val()};                           
   					            $("#xxgrid").addRowData(randnum,adddata);
   					            fieldname.push(addMC);
   					            addlist.push(adddata);
    
                                    //var randomNum = parseInt(Math.random()*100);
                                    //$("#xxgrid").trigger("reloadGrid");
                                    layer.close(index);
    		                    	$(":radio[name='FD_SFZJ'][value='N']").click();
    		                    	$(":radio[name='FD_SFKWK'][value='Y']").click();
                                },
    		                    btn2: function(index, layero) {
    		                    	$("tr").parent().find("span.red").remove();
    		                        layer.close(index);
    		                    	$(":radio[name='FD_SFZJ'][value='N']").click();
    		                    	$(":radio[name='FD_SFKWK'][value='Y']").click();
    		                    },
    		                    success:function(){
    		                    },
    		                    area: '580px'
    		                });
    		            })
                    }
                  });	
           //自定义编辑按钮
                $("#xxgrid").jqGrid('navButtonAdd', '#pager', {
                    caption : "编辑",
                    buttonicon:"icon-u-edit",
                    onClickButton : function() {
                        var gsr = jQuery("#xxgrid").jqGrid('getGridParam', 'selrow');
                    	$("#FD_ZDMC").attr("disabled",true);
                    	$("#FD_ZDLX").attr("disabled",true);
            		    if (gsr) {
            		        jQuery("#xxgrid").jqGrid('GridToForm', gsr, "#gridForm");
            		        var rowData = $("#xxgrid").jqGrid('getRowData',gsr);
            		        if(rowData.FD_SFZJ=='Y'){
            		        	$(":radio[name='FD_SFZJ'][value='Y']").click();
            		        }else{
            		        	$(":radio[name='FD_SFZJ'][value='N']").click();
            		        }
            		        
            		        if(rowData.FD_SFKWK=='Y'){
            		        	$(":radio[name='FD_SFKWK'][value='Y']").click();
            		        }else{
            		        	$(":radio[name='FD_SFKWK'][value='N']").click();
            		        }
                         	require(['layer'], function() {
        		                layer.open({
        		                    type: 1,
        		                    title: '修改',
        		                    content: $("#layer-editor"),
        		                    btn: ['确定', '取消'],
        		        	  		success: function(layero, index){
        		        	  			$.each(addlist,function(name,value) {
        		        	  				if(name=="FD_ZDLX"){
                                               if(value=="VARCHAR" || value=="NUMBER"){
                                            	   $("#FD_ZDCD").removeAttr("disabled");
                                            	   $("#FD_ZDJD").attr("disabled","disabled");                               
                                                }else if(value=="NUMERIC"){
                                                	$("#FD_ZDCD").attr("disabled","disabled");  
                                                	$("#FD_ZDJD").attr("disabled","disabled");  
                                                }else{
                                                	 $("#FD_ZDCD").removeAttr("disabled");
                                                	 $("#FD_ZDJD").removeAttr("disabled");
                                                }
        		        	  				}
        		        	  			 })
        		        			},
                                    yes: function(index, layero) {
                                        var gsr = $("#xxgrid").jqGrid('getGridParam', 'selrow');
                                        //layer.alert(gsr);
                                        if (gsr) {
                                          $("#xxgrid").jqGrid('FormToGrid',gsr,"#gridForm");
                                        }
                                     	 var obj = document.getElementById("layer-editor");
                                    	 var havespan = obj.getElementsByTagName("span");
                                     	if(havespan.length>0){
                                 		   return;
                                 	}
                                        if($('input:radio[name="FD_SFZJ"]:checked').val()=="Y" &&  $('input:radio[name="FD_SFKWK"]:checked').val()=="Y")
                                        {
                                       	 layer.alert("主键不能为空!");   
                                       	 return;
                                        } 
                                   	var editmc=$("#FD_ZDMC").val();                            	
                                    var editdata={FD_ZDMC:$("#FD_ZDMC").val(),FD_ZDLX:$("#FD_ZDLX").val(),FD_ZDCD:$("#FD_ZDCD").val(),FD_ZDJD:$("#FD_ZDJD").val(),FD_SFZJ:$('input:radio[name="FD_SFZJ"]:checked').val(),FD_QSZ:$("#FD_QSZ").val(),FD_SFKWK:$('input:radio[name="FD_SFKWK"]:checked').val(),FD_ZDBZ:$("#FD_ZDBZ").val()}; 
                                    for (var i=0;i<editlist.length;i++)
                                   	{    
                                      if(editmc==editlist[i].FD_ZDMC){
                                     		 editlist[i]=editdata
                                   	  }else{
                                   		 editlist.push(editdata) 
                                   	  }
                                   	} 
                                    if(editlist.length==0){
                                        editlist.push(editdata);
                                    }
                                    layer.close(index)
                                    },
        		                    btn2: function(index, layero) {
        		                    	$("tr").parent().find("span.red").remove();
        		                        layer.close(index)
        		                    },
        		                    area: '580px'
        		                });
        		            })
            		        
            		      } else {
            		        layer.alert("请选择一行!")
            		      }

                      }
                  });

   		 })
        }
     
        function getjqgridofEdit(megid){
       	 require(["jqGrid"], function(grid) {
       		 $("#xxgrid").jqGrid({
                    url: "${basePath}/wddyController/getwddymetadata",
                    datatype: "json",
                    postData:{xxid:megid},
                    mtype: "GET",
                    colModel: [{
                        label: 'IDD',
                        name: 'XID',
                        hidden:true,
                        editable: true,
                        sortable: false 
                    },{
                        label: '列名',
                        name: 'FD_ZDMC',
                        width:160,
                        editable: true,
                        sortable: false 
                    }, {
                        label: '字段类型',
                        name: 'FD_ZDLX',
                        width:90,
                        editable: true,
                        sortable: false 
                    },{
                        label: '长度',
                        name: 'FD_ZDCD',
                        width:40,
                        editable: true,
                        sortable: false 
                    },{
                        label: '精度',
                        name: 'FD_ZDJD',
                        width:40,
                        editable: true,
                        sortable: false 
                    },{
                        label: '是否主键',
                        name: 'FD_SFZJ',
                        width:70,
                        editable: true,
                        sortable: false 
                    },{
                        label: '缺省值',
                        name: 'FD_QSZ',
                        width:50,
                        editable: true,
                        sortable: false 
                    },{
                        label: '是否可为空',
                        name: 'FD_SFKWK',
                        width:80,
                        editable: true,
                        sortable: false 
                    },{
                        label: '字段描述',
                        name: 'FD_ZDBZ',
                        width:160,
                        editable: true,
                        sortable: false 
                    }],
                    page: 1,
                    scrollOffset:3,
                    shrinkToFit:false,
                    width: 690,
                    height: 150,
                    viewrecords: true,
                    pager: "#pager",
                    loadComplete:function(){
                   	 
                    }
                });

                $('#xxgrid').navGrid("#pager", {
                    search: false, // show search button on the toolbar
                    add: false,
                    edit: false,
                    del: false,
                    refresh: false
                });
                $("#xxgrid").setGridParam(
             		    {
             		    postData:{xxid:megid}
             		    }
             		) .trigger("reloadGrid"); 
                //删除制自定义按钮
                $("#pager_left tr").html("");
                //重新添加自定义你按钮
                $("#xxgrid").jqGrid('navButtonAdd', '#pager', {
                    caption : "新增",
                    buttonicon:"icon-u-add-round",
                    onClickButton : function() {
                    	$('#FD_ZDMC').removeAttr("disabled"); 
                 	    $('#FD_ZDLX').removeAttr("disabled"); 
                   	    $("#FD_ZDCD").removeAttr("disabled");
                    	$("#FD_ZDJD").removeAttr("disabled");
                    	$("#FD_ZDMC").val("");
                    	$("#FD_ZDCD").val("");
                    	$("#FD_ZDJD").val("");
                    	$("#FD_QSZ").val("");
                    	$("#FD_ZDBZ").val("");
                    	$("#FD_ZDLX").val("请选择类型"); 
                    	$("tr").parent().find("span.red").remove();
	                    $(":radio[name='FD_SFZJ'][value='N']").click();
		                $(":radio[name='FD_SFKWK'][value='Y']").click();
                     	require(['layer'], function() {
    		                layer.open({
    		                    type: 1,
    		                    title: '增加',
    		                    content: $("#layer-editor"),
    		                    btn: ['确定', '取消'],
                                yes: function(index, layero) {
                                 	 var obj = document.getElementById("layer-editor");
                                	 var havespan = obj.getElementsByTagName("span");
                                 	if(havespan.length>0){
                             		   return;
                             	  }
                                 	if($("#FD_ZDMC").val()=="" ){
                                        
                                 		$("#FD_ZDMC").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">请输入正确的列名</span>");
                                 		return;
                                 	}
                                 	else if($("#FD_ZDLX").val()=="请选择类型" ){
                                 		$("#FD_ZDLX").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">请选择类型</span>");
                                 		return;
                                 	}
                                 	else if($("#FD_ZDLX").val()=="VARCHAR"   && $("#FD_ZDCD").val()=="" ){

                                 		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">长度请输入1-4000</span>");
                                 		return;
                                 	}
                                 	else if(($("#FD_ZDLX").val()=="NUMERIC" ||  $("#FD_ZDLX").val()=="NUMBER" )   && $("#FD_ZDCD").val()=="" ){

                                 		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">长度请输入1-38</span>");
                                 		return;
                                 	}
                                 	else if($("#FD_ZDLX").val()=="NUMERIC" && $("#FD_ZDJD").val()=="" ){
                                 		$("#FD_ZDJD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">精度小于等于长度且大于等于0</span>");
                                 		return;
                                 	}
                                    if($('input:radio[name="FD_SFZJ"]:checked').val()=="Y" &&  $('input:radio[name="FD_SFKWK"]:checked').val()=="Y")
                                    {
                                   	 layer.alert("主键不能为空!");   
                                   	 return;
                                    } 	
                                    
                                    //判断新添加字段重复
                                    var addMC=$("#FD_ZDMC").val();                            
   					            for (var i=0;i<fieldname.length;i++)
   					        	{
   					          	  if(addMC==fieldname[i]){
   					        		  layer.alert("字段名重复，请重新填写!");
   					        		  return;
   					        	  }
   					        	}  
   					            //判断字段与已存在字段重复
                               	$.ajax({
                            		type : "POST",
                            		url : '${basePath}/wddyController/checkCols',
                            		dataType : "text",
                            		data: {zdmc:$("#FD_ZDMC").val(),xxid:megid},
                            		success: function (data) {
                                       if(data=="repeat"){
                                        	 layer.alert("字段名重复，请重新填写!");                                 
                                         }else{
                                             var rowIds = $("#xxgrid").getDataIDs();
                                             var max = Math.max.apply(null, rowIds);  
                                             ++max;
                                             var adddata={FD_ZDSX:max,FD_ZDMC:$("#FD_ZDMC").val(),FD_ZDLX:$("#FD_ZDLX").val(),FD_ZDCD:$("#FD_ZDCD").val(),FD_ZDJD:$("#FD_ZDJD").val(),FD_SFZJ:$('input:radio[name="FD_SFZJ"]:checked').val(),FD_QSZ:$("#FD_QSZ").val(),FD_SFKWK:$('input:radio[name="FD_SFKWK"]:checked').val(),FD_ZDBZ:$("#FD_ZDBZ").val()}; 
                                             addlist.push(adddata);
                                             $("#xxgrid").addRowData(max,adddata);
                                             layer.close(index);    
             		                    	$(":radio[name='FD_SFZJ'][value='N']").click();
            		                    	$(":radio[name='FD_SFKWK'][value='Y']").click();
                                         }
                            	    }
                            	}); 
                                },
    		                    btn2: function(index, layero) {
    		                    	$("tr").parent().find("span.red").remove();
    		                        layer.close(index);
    		                    	$(":radio[name='FD_SFZJ'][value='N']").click();
    		                    	$(":radio[name='FD_SFKWK'][value='Y']").click();
    		                    },
    		                    area: '580px'
    		                });
    		            })
                    }
                  });
                
                $("#xxgrid").jqGrid('navButtonAdd', '#pager', {
                    caption : "编辑",
                    buttonicon:"icon-u-edit",
                    onClickButton : function() {
                        var gsr = jQuery("#xxgrid").jqGrid('getGridParam', 'selrow');
                    	$("#FD_ZDMC").attr("disabled",true);
                    	$("#FD_ZDLX").attr("disabled",true);
            		    if (gsr) {
            		        jQuery("#xxgrid").jqGrid('GridToForm', gsr, "#gridForm");
            		        var rowData = $("#xxgrid").jqGrid('getRowData',gsr);
            		        if(rowData.FD_SFZJ=='Y'){
            		        	$(":radio[name='FD_SFZJ'][value='Y']").click();
            		        }else{
            		        	$(":radio[name='FD_SFZJ'][value='N']").click();
            		        }
            		        
            		        if(rowData.FD_SFKWK=='Y'){
            		        	$(":radio[name='FD_SFKWK'][value='Y']").click();
            		        }else{
            		        	$(":radio[name='FD_SFKWK'][value='N']").click();
            		        }
            		        
                         	require(['layer'], function() {
        		                layer.open({
        		                    type: 1,
        		                    title: '修改',
        		                    content: $("#layer-editor"),
        		                    btn: ['确定', '取消'],
        		        	  		success: function(layero, index){
        		        	  			var gxr = $("#xxgrid").jqGrid('getGridParam', 'selrow');
                                        var rowData = $("#xxgrid").jqGrid('getRowData',gxr);
                                      //  layer.alert(rowData.FD_ZDMC);
                                        if(rowData.FD_ZDLX=="VARCHAR" ||  rowData.FD_ZDLX=="NUMBER"){
                                        	   $("#FD_ZDCD").removeAttr("disabled");
                                      	   $("#FD_ZDJD").attr("disabled","disabled"); 
                                          }else if(rowData.FD_ZDLX=="NUMERIC"){
                                          	 $("#FD_ZDCD").removeAttr("disabled");
                                          	 $("#FD_ZDJD").removeAttr("disabled");
                                          }else{
                                            	$("#FD_ZDCD").attr("disabled","disabled");  
                                          	$("#FD_ZDJD").attr("disabled","disabled");  
                                          }
/*                                     	$.ajax({
                                    		type : "POST",
                                    		url : '${basePath}/wddyController/getxxmetadataBymc',
                                    		dataType : "text",
                                    		data: {zdmc:rowData.FD_ZDMC,xxid:megid},
                                    		success: function (data) {
                                               if(data=="cddemo"){
                                            	   $("#FD_ZDCD").removeAttr("disabled");
                                            	   $("#FD_ZDJD").attr("disabled","disabled");                               
                                                }else if(data=="datedemo"){
                                                	$("#FD_ZDCD").attr("disabled","disabled");  
                                                	$("#FD_ZDJD").attr("disabled","disabled");  
                                                }else{
                                                	 $("#FD_ZDCD").removeAttr("disabled");
                                                	 $("#FD_ZDJD").removeAttr("disabled");
                                                }
                                    	    }
                                    	});  */
        		        			},
                                    yes: function(index, layero) {
                                        var gsr = $("#xxgrid").jqGrid('getGridParam', 'selrow');
                                        if (gsr) {
                                          $("#xxgrid").jqGrid('FormToGrid',gsr,"#gridForm");
                                        }
                                     	 var obj = document.getElementById("layer-editor");
                                    	 var havespan = obj.getElementsByTagName("span");
                                     	if(havespan.length>0){
                                 		   return;
                                 	}
                                     if($('input:radio[name="FD_SFZJ"]:checked').val()=="Y" &&  $('input:radio[name="FD_SFKWK"]:checked').val()=="Y")
                                     {
                                    	 layer.alert("主键不能为空!");   
                                    	 return;
                                     } 	
                                     var rowData = $("#xxgrid").jqGrid('getRowData',gsr);
                                    var editmc=$("#FD_ZDMC").val();
                                    var sign=0;
                                    var editdata={XID:rowData.XID,FD_ZDSX:gsr,FD_ZDMC:$("#FD_ZDMC").val(),FD_ZDLX:$("#FD_ZDLX").val(),FD_ZDCD:$("#FD_ZDCD").val(),FD_ZDJD:$("#FD_ZDJD").val(),FD_SFZJ:$('input:radio[name="FD_SFZJ"]:checked').val(),FD_QSZ:$("#FD_QSZ").val(),FD_SFKWK:$('input:radio[name="FD_SFKWK"]:checked').val(),FD_ZDBZ:$("#FD_ZDBZ").val()}; 

                                    for (var i=0;i<editlist.length;i++)
                                   	{    
                                      if(editmc==editlist[i].FD_ZDMC){
                                     		 editlist[i]=editdata;
                                     		sign=1;
                                   	  }
                                   	} 
                                    if(sign==0){
                                    	editlist.push(editdata);
                                    }
                                    layer.close(index)
                                    },
        		                    btn2: function(index, layero) {
        		                    	$("tr").parent().find("span.red").remove();
        		                        layer.close(index)
        		                    },
        		                    area: '580px'
        		                });
        		            })
            		      } else {
            		    	  layer.alert("请选择一行!")
            		      }

                      }
                  });         
                
       	 });
        }
   
        $(document).on("click","#checkId .u_check",function() {
      	  //this.blur(); 
      	  //this.focus(); 
       	  var str=$('input:radio[name="FD_SFZJ"]:checked').val();
        	 if(str=="Y"){
        		$(":radio[name='FD_SFKWK'][value='N']").click()
        	 }else{
        		$(":radio[name='FD_SFKWK'][value='Y']").click();
        	 }
      	
          });   

        $(document).on("blur","#FD_ZDMC",function() {
          	var str=$("#FD_ZDMC").val();
          	var regexp = /^[a-zA-Z][\w]{1,29}$/;
          	//console.log(typeof regexp.test(str));
          	$("#FD_ZDMC").parent().find("span").remove();
          	if(!regexp.test(str)){
          		$("#FD_ZDMC").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">请输入正确的列名</span>");
          	}
          });   
          
          $(document).on("blur","#FD_ZDCD",function() {
          	var str=$("#FD_ZDCD").val();
          	$("#FD_ZDLX").parent().find("span").remove();
          	$("#FD_ZDCD").parent().find("span").remove();
          	if($("#FD_ZDLX").val()=="VARCHAR"){     		  
          		  var regexp=/^([1-9]|[0-9]{2,3}|[1-3]\d\d\d|4000)$/;
                	if(!regexp.test(str)){
              		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">长度请输入1-4000</span>");
              	}
          	}else if($("#FD_ZDLX").val()=="NUMBER" || $("#FD_ZDLX").val()=="NUMERIC"){     		  
            		var regexp=/^([1-9]|[1-2][0-9]|3[0-8])$/;
                	if(!regexp.test(str)){
              		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">长度请输入1-38</span>");
              	}
          	}

          });   
          
          $(document).on("blur","#FD_ZDJD",function() {
            	var str=$("#FD_ZDJD").val();
            	var strint=parseInt(str);
            	var scd=$("#FD_ZDCD").val();
            	var scdint=parseInt(scd);
            	$("#FD_ZDJD").parent().find("span").remove();
            	var regexp=/^([0-9]|[1-2][0-9]|3[0-8])$/;
            	if(!regexp.test(str) || strint>scdint){
            		$("#FD_ZDJD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\" class=\"red\">精度小于等于长度且大于等于0</span>");
            	}
            });   
          
          $(document).on("change","#FD_ZDLX",function() {
        	   $("#FD_ZDJD").parent().find("span").remove();
      	   $("#FD_ZDCD").parent().find("span").remove();
              if($("#FD_ZDLX").val()=="VARCHAR"){
            	   $("#FD_ZDJD").val("");
             	 $("#FD_ZDCD").removeAttr("disabled");
             	 $("#FD_ZDJD").attr("disabled","disabled");
              }else if($("#FD_ZDLX").val()=="DATE"||$("#FD_ZDLX").val()=="TIMESTAMP"){
            	   $("#FD_ZDJD").val("");
          	   $("#FD_ZDCD").val("");
             	 $("#FD_ZDCD").attr("disabled","disabled");
             	 $("#FD_ZDJD").attr("disabled","disabled");
             	 
              }else if($("#FD_ZDLX").val()=="NUMBER"){
            	   $("#FD_ZDJD").val("");
               $("#FD_ZDCD").removeAttr("disabled");
             	 $("#FD_ZDJD").attr("disabled","disabled");
              }else if($("#FD_ZDLX").val()=="NUMERIC"){
             	 $("#FD_ZDCD").removeAttr("disabled");
             	 $("#FD_ZDJD").removeAttr("disabled");
              }
              if($("#FD_ZDLX").val()!="请选择类型"){
              	$("#FD_ZDLX").parent().find("span").remove();
              }
      }); 
    })
    </script>
</body>
</html>
