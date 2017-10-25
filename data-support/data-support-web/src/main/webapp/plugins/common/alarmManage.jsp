<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>告警管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
</head>

<body class="u_scroll">
    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
                <div class="up-form-group ">
                    <label for="">规则名称 </label>
                    <input type="text" class="up-form-control" id="fd_gzmc_s"  placeholder="">
                </div>
                <div class="up-form-group ">
                    <label for="">用户名 </label>
                    <input type="text" class="up-form-control" id="fd_yhmc_s"  placeholder="">
                </div>
     			<div class="up-form-group ">
                    <label for="fd_gjfs_s">告警方式 </label>
	                <select name="fd_gjfs_s" id="fd_gjfs_s"  class="up-form-control">
	                <option value="">告警方式</option>
	                <option value="0">邮箱</option>
	                <option value="1">短信</option>	
	               			
				   </select>
                </div>
                <div class="up-form-group ">
                    <button type="button" id="search" class="up-btn up-btn-sm up-btn-primary up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
                <div class="up-form-group ">
                    <button type="button"  id="add" class="up-btn up-btn-sm up-btn-default u-add"> <i class="icon-u-add-round"></i>新增</button>
                </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover ">
            <thead>
                <tr class="up-active">
                    <th>规则名称</th>
                    <th>告警方式</th>
                    <th>用户名</th>
                    <th>邮箱</th>
                    <th>联系方式</th>
                    <th>状态</th>
					<th>操作</th>
                </tr>
            </thead>
            <tbody id="u_pagination_body">
            </tbody>
        </table>
        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
		<div id="layer-add" class="u-padding-top5  up-container-fluid" style="display: none">
            <form id="add-form" class="u_form u-margin-top20 up-clearfix u_cq_form"  style="max-width: 700px;">
                 <div class="up-form-group up-col-sm-24  " >
                    <label class="up-control-label">规则名称 </label>
                    <div class="up-col-sm-14">
                        <input type="text" id="layer_fd_gzmc" name="layer" class="up-form-control" readonly="readonly" >
                    </div>
                    <div class="up-col-sm-6">
                    	<button type="button" id="gzmc_button" class="u-margin-left30  up-btn up-btn-sm up-btn-default up-pull-right u-check"> <i class="icon-u-check"></i>选择
                    	</button>
                	</div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">用户 </label>
                    <div class="up-col-sm-14">
                        <input type="text" id="layer_fd_yhmc" name="layer_fd_yhmc" class="up-form-control"  readonly="readonly">
                    </div>
                    <div class="up-col-sm-6">
                    	<button type="button" id="yhmc_button" class="u-margin-left30 up-btn up-btn-sm up-btn-default up-pull-right u-check"> <i class="icon-u-check"></i>选择
                    	</button>
                	</div>
                </div>
                <div class="up-form-group up-col-sm-20">
	                <label for="layer_fd_gjfs"  class="up-control-label">类型 </label>
	                <div class="up-col-sm-14">
		                <select name="layer_fd_gjfs" id="layer_fd_gjfs" class="up-form-control">
		                 	<option value="">请选择告警方式</option>
		                 	<option value="0">邮箱</option>
		                    <option value="1">短信</option>
		                   
		                </select>
	                </div>
	            </div>
	            <div class="up-form-group up-col-sm-20">
	                <label for="" class="up-control-label">状态 </label>
	                <div id="layer_fd_zt" class="up-col-sm-12">
	                    <label class="u_check">
	                        <input type="radio" name="layer_fd_zt" value="Y" id="layer_fd_zt_y" >
	                        <i class="up-text-primary icon-u-ok-circle" id="layer_icon_zt_y"></i>启用
	                    </label>
	                    <label class="u_check">
	                        <input type="radio" name="layer_fd_zt" value="N"  id="layer_fd_zt_n" checked="true">
	                        <i class="up-text-primary icon-u-circle-thin" id="layer_icon_fd_zt_n"></i>停用
	                    </label>
	                </div>
	            </div>
            </form>
        </div>
        
        <!--选择规则 弹出框 -->
        <div id="layer-gzmc" class="u-padding-top15 up-container-fluid" style="display: none">
            <form class="up-form-inline u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">规则名称 </label>
                    <input type="text" class="up-form-control" id="layer_fd_gzmc_s" placeholder="">
                </div>
                <div class="up-form-group">
                    <label for="">数据库名 </label>
                    <input type="text" class="up-form-control" id="layer_db_name_s" placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" id="layer_gzmc_search" class="up-btn up-btn-sm up-btn-primary up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
            </form>
            <div class="u_height u-margin-bottom20" >
	            <table class="up-table up-table-bordered up-table-hover">
	                <thead>
	                    <tr class="up-active">
	                        <th>规则名称</th>
	                        <th>输入数据源</th>
<!-- 	                        <th>输入表</th> -->
	                        <th>输出数据源</th>
<!-- 	                        <th>输出表</th> -->
	                        <th>调度描述</th>
	                        <th>更新时间</th>
	                        <th>状态</th>
	                        <th>操作</th>
	                    </tr>
	                </thead>
	                <tbody id="layer_gzmc_u_pagination_body">
	                </tbody>
	            </table>
            </div>
            <div class="up-row">
           		<div class="up-col-md-24">
                	<div id="layer_gzmc_u_pagination" class="up-pull-right">
                	</div>
            	</div>
        	</div>
        </div>
        
        <!--选择用户弹出框 -->
        <div id="layer-yhmc" class="u-padding-top15 up-container-fluid" style="display: none">
            <form class="up-form-inline u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">用户名 </label>
                    <input type="text" class="up-form-control" id="layer_fd_yhmc_s" placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" id="layer_yhmc_search" class="up-btn up-btn-primary  up-btn-sm up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
            </form>
            <div class="u_height u-margin-bottom20" >
	            <table class="up-table up-table-bordered up-table-hover">
	                <thead>
	                    <tr class="up-active">
	                        <th>用户名称</th>
	                        <th>登陆名称</th>
	                        <th>联系方式</th>
	                        <th>邮箱</th>
	                        <th>状态</th>
	                       <th>操作</th>
	                    </tr>
	                </thead>
	                <tbody id="layer_yhmc_u_pagination_body">
	                </tbody>
	            </table>
            </div>
            <div class="up-row">
           		<div class="up-col-md-24">
                	<div id="layer_yhmc_u_pagination" class="up-pull-right">
                </div>
            </div>
        	</div>
        </div>
</div>

    <script>
    
    var chooseParams;
    /*
    *公共方法
    */
    /* start */
    function f_check_str(str){
    	if(str=="" || str==undefined || str==null ){
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
    
	function checkValue(layer_fd_gzmc,layer_fd_yhmc,layer_fd_gjfs){
		//用户名称
		if(!f_check_str(layer_fd_gzmc) ){
			layer.msg("请选择规则!");
			return false;
		}
		//登录名称
		if(!f_check_str(layer_fd_yhmc)  ){
			layer.msg("请选择用户!")
			return false;
		}
		//告警方式
		if(!f_check_str(layer_fd_gjfs)  ){
			layer.msg("请选择告警方式!")
			return false;
		}
		return true;
	}
    /* end */
    
    /*
  		  选择规则弹窗中的方法
  		  start
    */
    //选择function
    function chooseFun_gzmc(id,fd_gzmc){
    	 require(['layer'], function() {
    		 layer.close(layer.index);
    		 $("#layer_fd_gzmc").val(fd_gzmc);
    		 chooseParams.fd_rw_id=id;
    	 })
    }
    
    $("#gzmc_button").click(function() {
        require(['layer'], function() {
        	//弹出规则选择框
            layer.open({
                type: 1,
                title: '修改',
                content: $("#layer-gzmc"),
                area:['1000px','500px'],
                success:function(index,layero){
                	$(index.selector).find(".u_height").height(300).addClass("u_scroll");
                	//console.log(index);
                }
            });
        })
        $("#layer_gzmc_u_pagination").pagination('remote');
    })
    
    $("#layer_gzmc_search").click(function() {
		//提取参数
		var fd_gzmc = $.trim($("#layer_fd_gzmc_s").val());
		var db_name = $.trim($("#layer_db_name_s").val());
		var params = { "fd_gzmc": fd_gzmc,"db_name":db_name}
    	require(['layer'], function() {
    		$("#layer_gzmc_u_pagination").pagination('setParams', params);
    		$("#layer_gzmc_u_pagination").pagination('remote');
    	})
    })
    
    /* 选择规则弹窗中的方法 
    	end 
    */
    
    /*
    	选择用户的方法 start
    */
    function chooseFun_yhmc(id,fd_yhmc){
	   	 require(['layer'], function() {
	   		 layer.close(layer.index);
	   		 $("#layer_fd_yhmc").val(fd_yhmc);
	   		 chooseParams.fd_yh_id=id;
	   	 })
   }
    
    $("#yhmc_button").click(function() {
        require(['layer'], function() {
        	//弹出规则选择框
            layer.open({
                type: 1,
                title: '修改',
                content: $("#layer-yhmc"),
                area:['800px','500px'],
                success:function(index,layero){
                	$(index.selector).find(".u_height").height(300).addClass("u_scroll");
                	//console.log(index);
                }
            });
        })
    })
    
        $("#layer_yhmc_search").click(function() {
		//提取参数
		var fd_yhmc = $.trim($("#layer_fd_yhmc_s").val());
		var params = { "fd_yhmc": fd_yhmc,"fd_zt":"Y"}
    	require(['layer'], function() {
    		$("#layer_yhmc_u_pagination").pagination('setParams', params);
    		$("#layer_yhmc_u_pagination").pagination('remote');
    	})
    })
    /*
    	end
    */
    
    /*
    *  主界面中的方法
    */
    /* start */
    function editorFun(id,fd_rw_id,fd_yh_id,fd_gzmc,fd_gjfs,fd_yhmc,fd_zt){
    	chooseParams={};
    	chooseParams.fd_rw_id = fd_rw_id;
    	chooseParams.fd_yh_id = fd_yh_id;
    	$("#layer_fd_gzmc").val(fd_gzmc);
    	$("#layer_fd_gjfs").val(fd_gjfs);
    	$("#layer_fd_yhmc").val(fd_yhmc);
    	if(fd_zt=='Y'){
    		$('#layer_fd_zt_y').click();
    	}else{
    		$('#layer_fd_zt_n').click();
    	}
    	require(['layer'], function() {
	        layer.open({
	            type: 1,
	            title: '修改',
	            content: $("#layer-add"),
	            btn: ['保存', '取消'],
	            btn1: function(index,layero) {
	            	var layer_fd_gzmc =  $.trim($("#layer_fd_gzmc").val());
	               	var layer_fd_yhmc =  $.trim($("#layer_fd_yhmc").val());
	               	var layer_fd_gjfs =  $.trim($("#layer_fd_gjfs").val());
	            	var layer_fd_zt =$.trim( $("#layer_fd_zt input:radio:checked").val());
	            	if(!checkValue(layer_fd_gzmc,layer_fd_yhmc,layer_fd_gjfs)){
						return false;
	            	}
	            	var param = {"id":id , "fd_rwid": chooseParams.fd_rw_id, "fd_yh_id": chooseParams.fd_yh_id ,  "fd_gjfs": layer_fd_gjfs ,"fd_zt" : layer_fd_zt } ;
	            	$.ajax({
	            		type : "POST",
	            		url : basePath + "/alarm/modifyxGjRecord",
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
	            	cleanupForm();
	            	chooseParams={};
	            },
	            btn2: function(index, layero) {
	            	cleanupForm();
	                layer.close(index);
	                chooseParams={};
	            },
	            cancel: function(index, layero){ 
	            	cleanupForm();
	           	    layer.close(index);
	           	 	chooseParams={};
	           	}, 
	            area: '450px'
	        });
    	})
    }
   
	$("#add").click(function() {
		//初始化表单
		chooseParams={};
		$("#layer_fd_gjfs").val("");
		$('#layer_fd_zt_n').click();
        require(['layer'], function() {
            layer.open({
                type: 1,
                title: '新建告警',
                content: $("#layer-add"),
                btn: ['保存', '取消'],
                btn1: function(index, layero) {
                	var layer_fd_gzmc =  $.trim($("#layer_fd_gzmc").val());
                   	var layer_fd_yhmc =  $.trim($("#layer_fd_yhmc").val());
                   	var layer_fd_gjfs =  $.trim($("#layer_fd_gjfs").val());
                	var layer_fd_zt =$.trim( $("#layer_fd_zt input:radio:checked").val());
                	if(!checkValue(layer_fd_gzmc,layer_fd_yhmc,layer_fd_gjfs)){
						return false;
                	}
                	var param = {"fd_rwid": chooseParams.fd_rw_id, "fd_yh_id": chooseParams.fd_yh_id ,  "fd_gjfs": layer_fd_gjfs ,"fd_zt" : layer_fd_zt } ;
                	layer.load(2)
                	$.ajax({
                		type : "POST",
                		url : basePath + "/alarm/savexGjRecord",
                		dataType : "text",
                		data: param,
                		success: function (data) {
                			layer.closeAll('loading');
							if(data=="success"){
								layer.alert("添加成功!",{closeBtn:0},function(){
									$("#u_pagination").pagination('remote');
									layer.closeAll();
								});
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
                	chooseParams={};
                	cleanupForm();
                },
                btn2: function(index, layero) {
                	cleanupForm();
                    layer.close(index);
                    chooseParams={};
                },
                cancel: function(index, layero){ 
                	cleanupForm();
               	    layer.close(index);
               	 	chooseParams={};
               	}, 
                area: '450px',
            });
        })
    })
    
	$("#search").click(function() {
		var fd_yhmc_s= $.trim($("#fd_yhmc_s").val());
		var fd_gzmc_s = $.trim($("#fd_gzmc_s").val());
		var fd_gjfs_s = $.trim($("#fd_gjfs_s").val());
		params = { "fd_yhmc": fd_yhmc_s,"fd_gzmc":fd_gzmc_s,"fd_gjfs":fd_gjfs_s};
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
                url : basePath+"/alarm/listxAlarmRecord",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';				
                	for(var i=0;i<recordList.length;i++){
						infoStr=infoStr+"<tr><td>"+
                		recordList[i].FD_GZMC+"</td><td><div style='width:35px' >"+
                		(recordList[i].FD_GJFS=='1'?'短信':'邮箱')+"</div></td><td>"+
                		recordList[i].FD_YHMC+"</td><td>"+
                		recordList[i].FD_YX+"</td><td>"+
                		recordList[i].FD_LXFS+"</td><td><div style='width:35px' >"+
                		(recordList[i].FD_ZT=='Y'?'启用':'停用')+"</div></td><td>"+
    					"<button onclick='editorFun(\""+recordList[i].ID+"\",\""+recordList[i].FD_RW_ID+"\",\""+recordList[i].FD_YH_ID+"\",\""+recordList[i].FD_GZMC+"\",\""+recordList[i].FD_GJFS+"\",\""+recordList[i].FD_YHMC+"\",\""+recordList[i].FD_ZT+"\")'  class='up-btn up-btn-default up-btn-xs u-editor'><i class='icon-u-edit'></i>编辑</button>"+
                        "</td></tr>";
                	}
                	$("#u_pagination_body").empty();
                	$("#u_pagination_body").append(infoStr);
                },
                totalName:'total',
				pageParams: function(data){
					return {
						pageNum:data.pageIndex+1,
						pageSize :data.pageSize
					};
				}
             }    
        })
        
       //添加分页
       $("#layer_gzmc_u_pagination").pagination({
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
               url : basePath+"/alarm/ajaxTaskInfo",
               success:function(data){
               	recordList=data.list;
               	var infoStr = '';				
				for(var i=0;i<recordList.length;i++){
					infoStr=infoStr+
                     "<tr>"+ 
                         "<td><div style='max-width:200px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_GZMC+"'>"+recordList[i].FD_GZMC+"</div></td>"+ 
                         "<td><div style='width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SRSJY_MC+"'>"+recordList[i].FD_SRSJY_MC+"</div></td>"+ 
//                          "<td><div style='width:80px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SRB_MC+"'>"+recordList[i].FD_SRB_MC+"</div></td>"+ 
                         "<td><div style='width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SCSJY_MC+"'>"+recordList[i].FD_SCSJY_MC+"</div></td>"+ 
//                          "<td><div style='width:80px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SCB_MC+"'>"+recordList[i].FD_SCB_MC+"</div></td>"+ 
                         "<td><div  style='max-width:80px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+(recordList[i].FD_RWMS==null?"":recordList[i].FD_RWMS)+"'>"+(recordList[i].FD_RWMS==null?"":recordList[i].FD_RWMS)+"</div></td>"+ 
                         "<td><div style='width:120px' >"+(recordList[i].FD_GXSJ==null?"":recordList[i].FD_GXSJ)+"</div></td>"+ 
                         "<td><div style='width:30px' >"+(recordList[i].FD_RWZT=="Y"?"启用":"停用")+"</div></td>"+ 
                         "<td>"+ 
                             '<button onclick="chooseFun_gzmc(\''+recordList[i].ID+'\',\''+recordList[i].FD_GZMC+'\')"  class="up-btn up-btn-default up-btn-xs u-editor"><i class="icon-u-edit"></i>选择</button>'+ 
                         "</td>"+ 
                     "</tr>"; 
				}
               	$("#layer_gzmc_u_pagination_body").empty();
               	$("#layer_gzmc_u_pagination_body").append(infoStr);
               },
               totalName:'total',
			pageParams: function(data){
				return {
					pageNum:data.pageIndex+1,
					pageSize:data.pageSize
				};
			},
            }    
       })
       
          	//添加分页
       $("#layer_yhmc_u_pagination").pagination({
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
					infoStr=infoStr+
                     "<tr>"+ 
                         "<td>"+recordList[i].fd_yhmc+"</td>"+ 
                         "<td>"+recordList[i].fd_dlmc+"</td>"+ 
                         "<td>"+recordList[i].fd_lxfs+"</td>"+ 
                         "<td>"+recordList[i].fd_yx+"</td>"+ 
                         "<td>"+(recordList[i].fd_zt=='Y'?'启用':'停用')+"</td>"+
                         "<td>"+ 
                             '<button onclick="chooseFun_yhmc(\''+recordList[i].id+'\',\''+recordList[i].fd_yhmc+'\')"  class="up-btn up-btn-default up-btn-xs u-editor"><i class="icon-u-edit"></i>选择</button>'+ 
                         "</td>"+ 
                     "</tr>"; 
				}
               	$("#layer_yhmc_u_pagination_body").empty();
               	$("#layer_yhmc_u_pagination_body").append(infoStr);
               },
               totalName:'total',
			pageParams: function(data){
				return {
					pageNum:data.pageIndex+1,
					pageSize:data.pageSize
				};
			},
			params:{"fd_zt":"Y"}
            }    
       })
    })
    /* 主界面  end   */
    </script>
</body>

</html>
