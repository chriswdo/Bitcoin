<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>资源配置</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
    <link rel="stylesheet"  type="text/css" href="${basePath}/resources/uskin/css/jquery.fonticonpicker.min.css">    
     <link rel="stylesheet" type="text/css" href="${basePath}/resources/uskin/css/jquery.fonticonpicker.grey.min.css" /> 
	<script type="text/javascript" src="${basePath}/resources/uskin/js/jquery.fonticonpicker.min.js"></script> 
	<style type="text/css">
		.icons-selector .selector{
			height:31px;
		}
		.icons-selector .selector-button{
			width:38px;
			float:right;
		}
		.icons-selector .selected-icon i,.icons-selector .selector-button i{
			line-height:31px;
		}
		#layer-add .up-form-group{
			margin-bottom:25px;
		}
	</style>
</head>
<body>
    <div class="up-container-fluid">
        <div class="u-padding-top5">
	     	 <form class="up-form-inline u-margin-top10 u-margin-bottom5  u-margin-left15 up-clearfix">
		          	<div class="up-form-group">
		               <button type="button" class="up-btn up-btn-sm up-btn-default u-add"> <i class="icon-u-add-round"></i>新增</button>
		           </div>
		           <div class="up-form-group">
		               <button type="button" class="up-btn up-btn-sm up-btn-default u-editor"> <i class="icon-u-edit"></i>编辑</button>
		           </div>
		            <div class="up-form-group">
		               <button type="button" class="up-btn up-btn-sm up-btn-default u-delete"> <i class="icon-u-trash"></i>删除</button>
		           </div>
		    </form>
            <div class='u_tabs u-padding-top10 up-col-sm-22'>
                <div class="u_tab_body active" id="example_tab1">
                    <table id="grid1" class="jqgrid"></table>
                </div>
            </div>
			<div id="layer-add" class="u-padding-top5 up-container-fluid" style="display: none">
            <form id="add-form" class="u_form " >
            	<div class="up-form-group up-col-sm-22" style="margin-top:10px;" >
	                <label for="fd_tb"  class="up-control-label">ICON </label>
	                <select name="fd_tb" id="fd_tb" class="up-form-control">
						<option id="firtsIcon" value="">No icon</option>
						<c:forEach items="${icon}" var="item">		
						    <option value="${item.value}">${item.label}</option>
						</c:forEach>
	                </select>
	            </div>
                <div class="up-form-group up-col-sm-22">
	                <label for="fd_fjid"  class="up-control-label">父级菜单 </label>
	                <select name="fd_fjid" id="fd_fjid" class="up-form-control">
						<option value=""></option>
	                 	<option value="0">根目录</option>
						<c:forEach items="${parentMenu}" var="item">		
						     <option value="${item.id}" >${item.fd_mc}</option>
						</c:forEach>
	                </select>
	            </div>
	            <div class="up-form-group up-col-sm-22 ">
                    <label class="up-control-label">名称 </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_mc" name="fd_mc" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-22">
                    <label class="up-control-label">URL </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_dz" name="fd_dz" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-22">
                    <label class="up-control-label">菜单序号 </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_xh" name="fd_xh" class="up-form-control" >
                    </div>
                </div>
            </form>
        </div>
        </div>
    </div>
</body>
</html>
<script>

	//调节grid大小
    $(window).resize(function(){  
         $(window).unbind("onresize");  
         $("#grid1").setGridHeight($(window).height() - 120);  
         $("#grid1").setGridWidth($(window).width() - 120);
         $(window).bind("onresize", this);  
    });  
	
require(['uskin'], function() {
    require(["jqGrid","layer"], function(grid) {
    	
    	function mcFormatter  (cellvalue, options, rowObject)      
    	{      
    		var rowid=	String(rowObject.id);
            var temp = '<div class="up-form-group up-col-sm-12 " style="margin-bottom:0">'+
            			'<input style="margin-top: 0px" type="checkbox" id="chx'+rowid+'" name="'+rowid+'" class="u_check" value="'+rowid+'" onclick="clickCheckbox(&quot;'+rowid+'&quot;, this);" />&nbsp&nbsp&nbsp'+
            			'<span class="u_node_icon '+rowObject.fd_tb+'" style="margin-right:5px;"/>' +
            			'<label  style="margin: 0px" class="up-control-label">'+rowObject.fd_mc+'</label>' +
	       				'</div>';
			return temp;
    	}   
    	
    	//checkbox事件
    	clickCheckbox = function clickCheckbox(rowid, $this) {
    	};
    	
        $(document).ready(function() {
        	
        	tbfontIconPicker = $('#fd_tb').fontIconPicker();
        	 
            $('#grid1').jqGrid({
                "url":  basePath+"/common/ajaxMenuList",
                "colModel": [{
                    "name": "fd_ml",
                    "label": "目录",
                     formatter : mcFormatter,
                    "width": 100,
                },{
                    "name": "fd_dz",
                    "index": "fd_dz",
                    "label": "url",
                    "width": 150,
                }, 
                {
                    "name": "id",
                    "index": "id",
                    "label": "id",
                    "hidden": true
                },{
                    "name": "fd_fjid",
                    "index": "fd_fjid",
                    "label": "fd_fjid",
                    "hidden": true
                }, {
                    "name": "fd_tb",
                    "index": "fd_tb",
                    "label": "fd_tb",
                    "hidden": true
                },
                {
                    "name": "fd_mc",
                    "index": "fd_mc",
                    "label": "fd_mc",
                    "hidden": true
                },
                {
                    "name": "fd_xh",
                    "index": "fd_xh",
                    "label": "fd_xh",
                    "hidden": true
                },
                {
                    "name": "lft",
                    "hidden": true
                }, {
                    "name": "rgt",
                    "hidden": true
                }, {
                    "name": "level",
                    "hidden": true
                }, {
                    "name": "uiicon",
                    "hidden": true
                }],
				mtype: 'POST',
				"height":$(window).height() - 120,
				"width":$(window).width()-200,
// 				"scrollOffset":3,
// 				"shrinkToFit":false,
				"autoheight":true,
                "autowidth": true,
                "treeGrid": true,
                "ExpandColumn": "fd_ml",
                "treedatatype": "json",
                "treeGridModel": "nested",
                "treeReader": {
                    "left_field": "lft",
                    "right_field": "rgt",
                    "level_field": "level",
                    "leaf_field": "isLeaf",
                    "expanded_field": "expanded",
                    "loaded": "loaded",
                    "icon_field": "icon"
                },
                datatype: "json",
				"pager":"#pager1"
			});
            
            function cleanForm(objE){//objE为form表单  
                $(objE).find(':input').each(  
                    function(){  
                         $(this).val('');  
                    }     
                );  
            	$(objE).find('select').each(function(){
            		$(this).val('');
            	})
            	tbfontIconPicker.refreshPicker();
            
            }  
            
            function getValue(id){
            	return $.trim($("#"+id).val());
            }
            
            function f_check_str(str){
            	if(str=="" || str==undefined || str==null ){
            		return false;
            	}
            	return true;
            }
            
            function chechParams(fd_fjid,fd_mc,fd_dz,fd_xh,fd_tb){
            	if(!f_check_str(fd_fjid)){
            		layer.msg("请选择父级菜单!") ;
            		return false;
            	};
            	if(!f_check_str(fd_mc)){
        			layer.msg("请输入名称!") ;
        			return false;
            	}
            	if(!f_check_str(fd_xh) || isNaN(Number(fd_xh))){
        			layer.msg("请输入正确序号!") ;
        			return false;
            	}
            	if(!f_check_str(fd_tb)){
        			layer.msg("请选择图标!") ;
        			return false;
            	}
            	return true;
            }
            
            function refreshParentMenu(){
				$.ajax({
					type : "POST",
					url : basePath+"/common/ajaxParentMenu",
					dataType : "json",
					data: {} ,
					success: function (data) {
						if(data.result=="success"){
							parentList=data.parentList;
		                	var infoStr = '<option value=""></option><option value="0">根目录</option>';				
							for(var i=0;i<parentList.length;i++){
								infoStr=infoStr+
								     '<option value="'+parentList[i].id+'" >'+parentList[i].fd_mc+'</option>';
							}
		                	$("#fd_fjid").empty();
		                	$("#fd_fjid").append(infoStr);
						}
					},
					error: function(e) {
						layer.alert("服务器异常!")
					} 
				}); 
            }
            
            $(".u-add").click(function() { 
            	layer.open({
							type: 1,
							title: '新增',
							content: $("#layer-add"),
							btn: ['确定','取消'],
							btn1: function(index,layero) {
								 var fd_fjid = getValue("fd_fjid");
								 var fd_mc  = getValue("fd_mc");
								 var fd_dz  = getValue("fd_dz");
								 var fd_tb  = getValue("fd_tb");
								 var fd_xh  = getValue("fd_xh");
								 //检查参数
								 if(!chechParams(fd_fjid,fd_mc,fd_dz,fd_xh,fd_tb)){
									 return false;
								 }
								//获取参数
								var params ={
								 "fd_fjid" : fd_fjid,
								 "fd_mc" : fd_mc,
								 "fd_dz": fd_dz,
								 "fd_tb" : fd_tb,
								 "fd_xh" : fd_xh
								}
								$.ajax({
									type : "POST",
									url : basePath + "/common/savexZypzRecord",
									dataType : "text",
									data: params ,
									success: function (data) {
										if(data=="success"){
											cleanForm($("#layer-add"))
											layer.alert("保存成功!");
											$('#grid1').trigger("reloadGrid");
											refreshParentMenu();
				            			}else if(data=="sessionTimeout"){
											layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
												layer.closeAll();
											});
										}else{
											cleanForm($("#layer-add"))
											layer.alert("保存异常!")
										}
									},
									error: function(e) {
										layer.alert("服务器异常!")
									} 
								}); 
								layer.close(index)
							},
							btn2: function(index, layero) {
								cleanForm($("#layer-add"))
								layer.close(index)
							},
							cancel: function(index, layero){ 
								cleanForm($("#layer-add"))
								layer.close(index)
							}, 
							area: ['550px','420px']
						});

            });
            
            function getSelectedArray(){
            	var arrrowid = new Array();
            	$('.u_check').each(function(){
					if($(this).is(':checked')){
						arrrowid.push($(this).attr("name"))
					}            		
            	})
				return arrrowid;            	
            }
            
            $(".u-editor").click(function() { 
            	var arr = getSelectedArray();
				if(arr.length!=1){
					layer.alert("请选择一条记录!")
					return false;
				}
				var rowid=arr[0];
				//给编辑框赋值
				var rowData = $("#grid1").jqGrid("getRowData",rowid);
				$('#fd_fjid').val(rowData.fd_fjid);
				$('option[value='+rowData.id+']').hide();
				$('#fd_mc').val(rowData.fd_mc);
				$('#fd_dz').val(rowData.fd_dz);
				$('#fd_tb').val(rowData.fd_tb);
				$('#fd_xh').val(rowData.fd_xh);
				tbfontIconPicker.refreshPicker();
            	layer.open({
							type: 1,
							title: '编辑',
							content: $("#layer-add"),
							btn: ['确定','取消'],
							btn1: function(index,layero) {
								 var fd_fjid = getValue("fd_fjid");
								 var fd_mc  = getValue("fd_mc");
								 var fd_dz  = getValue("fd_dz");
								 var fd_tb  = getValue("fd_tb");
								 var fd_xh  = getValue("fd_xh");
								 //检查参数
								 if(!chechParams(fd_fjid,fd_mc,fd_dz,fd_xh,fd_tb)){
									 return false;
								 }
								//获取参数
								var params ={
								 "id"      : rowData.id,
								 "fd_fjid" : fd_fjid,
								 "fd_mc" :   fd_mc,
								 "fd_dz":    fd_dz,
								 "fd_xh" :   fd_xh,
								 "fd_tb" :   fd_tb
								}
								$.ajax({
									type : "POST",
									url : basePath + "/common/modifyxZypzRecord",
									dataType : "text",
									data: params ,
									success: function (data) {
										if(data=="success"){
											cleanForm($("#layer-add"))
											$('#grid1').trigger("reloadGrid");
											layer.alert("保存成功!");
											refreshParentMenu();
				            			}else if(data=="sessionTimeout"){
											layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
												layer.closeAll();
											});
										}else{
											layer.alert("保存异常!")
										}
									},
									error: function(e) {
										layer.alert("服务器异常!")
									} 
								}); 
								layer.close(index)
								$('option[value='+rowData.id+']').show();
							},
							btn2: function(index, layero) {
								cleanForm($("#layer-add"))
								layer.close(index)
								$('option[value='+rowData.id+']').show();
							},
							cancel: function(index, layero){
								cleanForm($("#layer-add"))
								layer.close(index)
								$('option[value='+rowData.id+']').show();
							}, 
							area: ['550px','420px']
				});

            });
            
            $(".u-delete").click(function() { 
				var arr = getSelectedArray();
				if(arr.length==0){
					layer.alert("请至少选择一条记录!")
					return false;
				}
				var hasParent=false;
				for(var i=0;i<arr.length;i++){
					 var records = $('#grid1').jqGrid('getNodeChildren',$("#grid1").jqGrid("getLocalRow", arr[i]));
					 if(records.length>0){
						 hasParent=true;
						 break;
					 }
				}
				if(hasParent){
					//给编辑框赋值
					layer.confirm("该操作会删除父级菜单及其子菜单，是否确认?",{btn: ['确认','取消']},
						function(){
							$.ajax({
									type : "POST",
									url : basePath + "/common/deletexZypzRecord",
									dataType : "json",
									data: {"idArrStr":JSON.stringify(arr)} ,
									success: function (data) {
										if(data.result=="success"){
											layer.alert("删除成功!");
											$('#grid1').trigger("reloadGrid");
											refreshParentMenu();
										}else if(data.result=="fail"){
											layer.alert(data.retInfo)
										}
									},
									error: function(e) {
										layer.alert("服务器异常!")
									} 
							}); 
						},function(){
						})
					return ;
				}
				//给编辑框赋值
				layer.confirm("确认删除菜单?",{btn: ['确认','取消']},
						function(){
							$.ajax({
								type : "POST",
								url : basePath + "/common/deletexZypzRecord",
								dataType : "json",
								data: {"idArrStr":JSON.stringify(arr)} ,
								success: function (data) {
									if(data.result=="success"){
										layer.alert("删除成功!");
										$('#grid1').trigger("reloadGrid");
										refreshParentMenu();
									}else if(data.result=="fail"){
										layer.alert(data.retInfo);
									}
								},
								error: function(e) {
									layer.alert("服务器异常!")
								} 
							}); 
						},function(){
				})


            });
        });

    })

    
})
</script>
