<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>用户管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
</head>

<body class="u_scroll">
    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">用户名称 </label>
                    <input type="text" class="up-form-control" id="fd_yhmc_s"  placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" class="up-btn up-btn-sm  up-btn-primary up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
                <div class="up-form-group">
                    <button type="button"  id="add" class="up-btn up-btn-sm up-btn-default u-add"> <i class="icon-u-add-round"></i>新增
                    </button>
                </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover ">
            <thead>
                <tr class="up-active">
                    <th>用户名称</th>
                    <th>登录名称</th>
                    <th>联系方式</th>
                    <th>邮箱</th>
                    <th>状态</th>
					<th>操作</th>
                </tr>
            </thead>
            <tbody id="yh_table">
            </tbody>
        </table>
        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
		<div id="layer-add" class="u-padding-top5 up-container-fluid" style="display: none">
            <form id="add-form" class="u_form  " >
                 <div class="up-form-group up-col-sm-22" >
                    <label class="up-control-label">用户名称：</label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_yhmc" name="fd_yhmc" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-22 ">
                    <label class="up-control-label">登录名称：</label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_dlmc" name="fd_dlmc" class="up-form-control" >
                    </div>
                </div>
	            <div class="up-form-group up-col-sm-22 ">
                    <label class="up-control-label">联系方式：</label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_lxfs" name="fd_lxfs" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-22">
                    <label class="up-control-label">邮箱：</label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_yx" name="fd_yx" class="up-form-control" >
                    </div>
                </div>
	            <div class="up-form-group up-col-sm-22">
	                <label for="fd_zt" class="up-control-label">是否启用：</label>
	                <div id="fd_zt" class="up-col-sm-12">
	                    <label class="u_check">
	                        <input type="radio" name="fd_zt" value="Y"  id="fd_zt_y"  >
	                        <i class="up-text-primary icon-u-ok-circle" id="icon_fd_zt_y"></i>启用
	                    </label>
	                    <label class="u_check">
	                        <input type="radio" name="fd_zt" value="N"  id="fd_zt_n" checked="true">
	                        <i class="up-text-primary icon-u-circle-thin" id="icon_fd_zt_n"></i>停用
	                    </label>
	                </div>
	            </div>
	            <div class="up-form-group up-col-sm-22" > 
	           		<label class="up-control-label">菜单权限：</label>
						<div class="u_tab_body active "  style=" overflow:auto;height:200px">
							<ul id="treeMenu" class="ztree"></ul>
						</div>
                </div>
            </form>
        </div>

        <div id="layer-menu" class="u-padding-top20 u-padding-left20" style="display: none">
	         <div class='u_tabs  up-col-sm-18 u-margin-left20 '>
	                <div class="u_tab_body active " id="example_tab1">
							 <ul id="treeMenuForId" class="ztree"  style="height:'400px'"></ul>
	                </div>
	         </div>
        </div>
    <script>
    /*
    *公共方法
    */
    function f_check_str(str){
    	if(str=="" || str==undefined || str==null ){
    		return false;
    	}
    	return true;
    }
    
    function f_check_emall(emall){
    	var emallExp = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    	if(!emallExp.test(emall)){
    		return false;
    	}
    	return true;
    }
	
	function checkValue(fd_yhmc,fd_dlmc,fd_lxfs,fd_yx,fd_zt){
    	//用户名称
    	if(!f_check_str(fd_yhmc) ){
    		layer.msg("用户名称不能为空!");
    		return false;
    	}
    	//登录名称
    	if(!f_check_str(fd_dlmc)  ){
    		layer.msg("登录名称不能为空!")
    		return false;
    	}
    	//联系方式
    	if(!f_check_str(fd_lxfs) || isNaN(Number(fd_lxfs)) ){
    		layer.msg("联系方式不正确!")
    		return false;
    	}
    	//邮箱验证
    	if(!f_check_emall(fd_yx) ){
    		layer.msg("邮箱不正确!");
    		return false;
    	}
    	//状态
    	if(!f_check_str(fd_zt) ){
    		layer.msg("请选择状态!");
    		return false;
    	}
    	return true;
    }
	
    function cleanupForm(){
    	$("form .icon-u-warning").remove();
    	$("form .u_form_remind").remove();
    	$("form .up-has-warning").removeClass("up-has-warning");
        $($("#layer-add")).find(':input[type="text"]').each(  
                function(){  
                     $(this).val('');  
                }     
        );  
    }
    
    /*
    *
    */
    function editorFun(id,fd_yhmc,fd_dlmc,fd_lxfs,fd_yx,fd_zt){
    	$("#fd_yhmc").val(fd_yhmc);
    	$("#fd_dlmc").val(fd_dlmc);
    	$("#fd_lxfs").val(fd_lxfs);
    	$("#fd_yx").val(fd_yx);
    	if(fd_zt=='Y'){
    		$('#fd_zt_y').click();
    	}else{
    		$('#fd_zt_n').click();
    	}
    	$("#fd_dlmc").attr("readonly","readonly");
        require(['layer','ztree'], function() {
        	require(['ztree_check'],function(){
            	//提取参贷结构
           		$.ajax({
               		type : "POST",
               		url : basePath + "/userMenu/listxMenuById",
               		dataType : "json",
               		data: {"id":id} ,
               		success: function (data) {
        				var zNodes = data;
        				var setting = {
        						view: {
        							selectedMulti: false
        						},
        						check: {
        							enable: true
        						},
        						data: {
        							simpleData: {
        								enable: true
        							}
        						}
        					};
        				$.fn.zTree.init($("#treeMenu"), setting, zNodes);
        				
        				
        	            layer.open({
        	                type: 1,
        	                title: '修改',
        	                content: $("#layer-add"),
        	                btn: ['确定', '取消'],
        	                btn1: function(index,layero) {
        	                	var fd_yhmc =  $.trim($("#fd_yhmc").val());
        	                	var fd_dlmc =  $.trim($("#fd_dlmc").val());
        	                	var fd_lxfs =  $.trim($("#fd_lxfs").val());
        	                	var fd_yx =  $.trim($("#fd_yx").val());
        	                	var fd_zt =$.trim( $("#fd_zt input:radio:checked").val());
        	                	if(!checkValue(fd_yhmc,fd_dlmc,fd_lxfs,fd_yx,fd_zt)){
        							return false;
        	                	}
        	                	//获取菜单数据
        	                	var nodes = $.fn.zTree.getZTreeObj("treeMenu").getNodes();
        	                	var param = {"id":id,"fd_yhmc":fd_yhmc,"fd_dlmc":fd_dlmc,"fd_lxfs":fd_lxfs,"fd_yx":fd_yx,"fd_zt":fd_zt,"nodes": JSON.stringify(nodes)};
        		               	$.ajax({
        		               		type : "POST",
        		               		url : basePath + "/userManage/modifyxYhbRecord",
        		               		dataType : "text",
        		               		data: param ,
        		               		success: function (data) {
        		               			if(data=="success"){
        		               				layer.alert("保存成功!");
        		               			 	layer.close(index)
        									$("#u_pagination").pagination('remote');
        		            			}else if(data=="sessionTimeout"){
        									layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
        										layer.closeAll();
        									});
        		            			}
        		               	    },
        		            		error: function(e) {
        		            			layer.alert("服务器异常!")
        		            		} 
        		               	}); 
        	                },
        	                btn2: function(index, layero) {
        	                	cleanupForm();
        	                    layer.close(index)
        	                },
        	                cancel: function(index, layero){ 
        	                	cleanupForm();
        	               	    layer.close(index)
        	               	}, 
        	                area: '450px'
        	            });
        	            
        	            
        	            
               	    },
            		error: function(e) {
            			layer.alert("服务器异常!")
            		} 
               	});
        	})
        })
    }
    
    function initPasswordFun(id){
    	require(['layer'], function() {
    		layer.confirm('确认初始化?',{btn:['确认','取消']},
    				function(){
		    	       	$.ajax({
		    	       		type : "POST",
		    	       		url : basePath + "/userManage/initPassword",
		    	       		dataType : "text",
		    	       		data: {"id":id} ,
		    	       		success: function (data) {
		    	       			if(data=="success"){
		    	       				layer.alert("初始化成功!");
		    						$("#u_pagination").pagination('remote');
		    	       			}else{
		    	       				layer.alert("初始化异常!")
		    	       			}
		    	       	    },
		    	    		error: function(e) {
		    	    			layer.alert("服务器异常!")
		    	    		} 
		    	       	}); 
    				},
    				function(){
    					
    				})
    	})
    }
    
	$("#add").click(function() {
		$("#fd_dlmc").removeAttr("readonly");
        require(['layer','ztree'], function() {
        	require(['ztree_check'],function(){
            	//提取参贷结构
           		$.ajax({
               		type : "POST",
               		url : basePath + "/userMenu/listxMenuById",
               		dataType : "json",
               		success: function (data) {
        				var zNodes = data;
        				var setting = {
        						view: {
        							selectedMulti: false
        						},
        						check: {
        							enable: true
        						},
        						data: {
        							simpleData: {
        								enable: true
        							}
        						}
        					};
        				$.fn.zTree.init($("#treeMenu"), setting, zNodes);
        				
        				
        		        require(['layer'], function() {
        		            layer.open({
        		                type: 1,
        		                title: '添加用户',
        		                content: $("#layer-add"),
        		                btn: ['确定', '取消'],
        		                btn1: function(index, layero) {
        		                  	var fd_yhmc =  $.trim($("#fd_yhmc").val());
        		                   	var fd_dlmc =  $.trim($("#fd_dlmc").val());
        		                   	var fd_lxfs =  $.trim($("#fd_lxfs").val());
        		                   	var fd_yx =  $.trim($("#fd_yx").val());
        		                   	var fd_zt =$.trim( $("#fd_zt input:radio:checked").val());
        		                	if(!checkValue(fd_yhmc,fd_dlmc,fd_lxfs,fd_yx,fd_zt)){
        								return false;
        		                	}
        		                	var nodes = $.fn.zTree.getZTreeObj("treeMenu").getNodes();
        		                	var param = {"fd_yhmc": fd_yhmc, "fd_dlmc": fd_dlmc ,  "fd_lxfs": fd_lxfs ,"fd_yx" : fd_yx ,"fd_zt" : fd_zt,"nodes": JSON.stringify(nodes)} ;
        		                	layer.load(2)
        		                	$.ajax({
        		                		type : "POST",
        		                		url : basePath + "/userManage/savexYhbRecord",
        		                		dataType : "text",
        		                		data: param,
        		                		success: function (data) {
        		                			layer.closeAll('loading');
        									if(data=="success"){
        										layer.alert("添加成功!");
        										$("#u_pagination").pagination('remote');
        										layer.close(index);
        									}else if(data=="failed"){
        										layer.msg("该登录名称已经存在!")
        			            			}else if(data=="sessionTimeout"){
        										layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
        											layer.closeAll();
        										});
        			            			}
        		                	    },
        		                		error: function(e) {
        		                			layer.alert("服务器异常!")
        		                		} 
        		                	});
        		            		
        		                },
        		                btn2: function(index, layero) {
        		                	cleanupForm();
        		                    layer.close(index)
        		                },
        		                cancel: function(index, layero){ 
        		                	cleanupForm();
        		               	    layer.close(index)
        		               	}, 
        		                area: '450px',
        		            });
        		        })
        		        
        		        
        		        
        		        
               	    },
            		error: function(e) {
            			layer.alert("服务器异常!")
            		} 
               	});
        	})
        })

    })
    
    function menuInfoFun(id){
		require(['layer'], function() {
    		require(['ztree'], function() {
            	require(['ztree_check'],function(){
                	//提取参贷结构
               		$.ajax({
                   		type : "POST",
                   		url : basePath+"/userMenu/ajaxMenuListById",
                   		dataType : "json",
                   		data: {"id":id} ,
                   		success: function (data) {
            				var zNodes = data;
            				var setting = {
            						data: {
            							simpleData: {
            								enable: true
            							}
            						}
            					};
            				$.fn.zTree.init($("#treeMenuForId"), setting, zNodes);
            				
            	            layer.open({
            	                type: 1,
            	                title: '菜单信息',
            	                content: $("#layer-menu"),
            	                cancel: function(index, layero){ 
            	               	    layer.close(index)
            	               	}, 
            	                area: ['300px','350px'],
            	            });
            	            
                   	    },
                		error: function(e) {
                			layer.alert("服务器异常!")
                		} 
                   	});
            	})
    		})  
        })
	}
    
   
	$(".u-search").click(function() {
		var fd_yhmc = $.trim($("#fd_yhmc_s").val());
		params = { "fd_yhmc": fd_yhmc}
    	require(['uskin'], function() {
    		$("#u_pagination").pagination('setParams', params);
    		$("#u_pagination").pagination('remote');
    	})
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
                url : basePath+"/userManage/ajaxYhbRecord",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';				
                	for(var i=0;i<recordList.length;i++){
						infoStr=infoStr+"<tr><td>"+
                		recordList[i].fd_yhmc+"</td><td>"+
                		recordList[i].fd_dlmc+"</td><td><div  style='width:100px'>"+
                		recordList[i].fd_lxfs+"</div></td><td><div  style='width:200px'>"+
                		recordList[i].fd_yx+"</div></td><td><div  style='width:35px'>"+
                		(recordList[i].fd_zt=='Y'?'启用':'停用')+"</div></td><td><div  style='width:230px'>"+
    					"<button onclick='editorFun(\""+recordList[i].id+"\",\""+recordList[i].fd_yhmc+"\",\""+recordList[i].fd_dlmc+"\",\""+recordList[i].fd_lxfs+"\",\""+recordList[i].fd_yx+"\",\""+recordList[i].fd_zt+"\")'  class='up-btn up-btn-default up-btn-xs u-editor'><i class='icon-u-edit'></i>编辑</button>"+
                        "<button onclick='initPasswordFun(\""+recordList[i].id+"\")' class='up-btn up-btn-default up-btn-xs u-detail'><i class='icon-u-eye'></i>密码初始化</button>"+
                        "<button onclick='menuInfoFun(\""+recordList[i].id+"\")' class='up-btn up-btn-default up-btn-xs u-cog'><i class='icon-u-cog'></i>菜单列表</button>"+
                        "</div></td></tr>";
                	}
                	$("#yh_table").empty();
                	$("#yh_table").append(infoStr);
                },
                totalName:'total',
				pageParams: function(data){
					return {
						pageNum:data.pageIndex+1,
						pageSize:data.pageSize
					};
				}
             }    
        })
    })
    </script>
</body>

</html>
