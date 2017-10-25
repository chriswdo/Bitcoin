<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>数据源管理</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
</head>

<body class="u_scroll">
	<div class="up-container-fluid">
     	<form class="up-form-inline u-margin-top10 u-margin-bottom20 up-clearfix">
     	        <div class="up-form-group ">
                    <label for="">连接名称 </label>
                    <input type="text" class="up-form-control" id="fd_mc_s"  placeholder="">
                </div>
     			<div class="up-form-group ">
                    <label for="fd_lx_s">类型</label>
	                <select name="fd_lx_s" id="fd_lx_s"  class="up-form-control">
	                 	<option value=""></option>
	                    <option value="1">子交换库</option>
	                    <option value="2">平台交换库</option>
	                    <option value="3">平台共享库</option>
	                    <option value="4">平台仓库</option>
	                    <option value="5">平台集市</option>		
				   </select>
                </div>
                <div class="up-form-group ">
                    <button type="button" id="search" class="up-btn up-btn-sm up-btn-primary up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
	       		<div class="up-form-group">
	               <button type="button" class="up-btn up-btn-sm up-btn-default u-add"> <i class="icon-u-add-round"></i> 新增
	               </button>
	           </div>
	    </form>
		<table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>连接名称</th>
                    <th>类型</th>
                    <th>数据库名称</th>
                    <th>IP</th>
                    <th>端口</th>
                    <th>数据库类型</th>
                    <th>描述</th>
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
		<div id="layer-add" class="u-padding-top15 up-container-fluid" style="display: none">
            <form id="add-form" class="u_form " >
                 <div class="up-form-group up-col-sm-24" style="display: none">
                    <label class="up-control-label">id </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="id" name="id" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">连接名称  </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_mc" name="fd_mc" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24">
	                <label for="fd_lx"  class="up-control-label">类型 </label>
	                <select name="fd_lx" id="fd_lx" class="up-form-control">
	                 	<option value="">请选择类型</option>
	                    <option value="1">子交换库</option>
	                    <option value="2">平台交换库</option>
	                    <option value="3">平台共享库</option>
	                    <option value="4">平台仓库</option>
	                    <option value="5">平台集市</option>
	                </select>
	            </div>
	            <div class="up-form-group up-col-sm-24">
	                <label for="fd_sjklx" class="up-control-label">数据库类型 </label>
	                <select name="fd_sjklx" id="fd_sjklx" class="up-form-control">
	                	<option value="">请选择数据库类型</option>
	                    <option value="mysql">mysql</option>
	                    <option value="oracle">oracle</option>
	                    <option value="db2">db2</option>
	                    <option value="greenplum">greenplum</option>
	                    <option value="sybase">sybase</option>
	                </select>
	            </div>
	            <div class="up-form-group up-col-sm-24 ">
                    <label class="up-control-label">数据库名称 </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_sjkmc" name="fd_sjkmc" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24">
                    <label class="up-control-label">IP </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_ip" name="fd_ip" class="up-form-control" >
                    </div>
                </div>
				<div class="up-form-group up-col-sm-24">
                    <label class="up-control-label">端口 </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_dk" name="fd_dk" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24">
                    <label class="up-control-label">用户名 </label>
                    <div class="up-col-sm-24">
                        <input type="text" id="fd_yhm" name="fd_yhm" class="up-form-control" >
                    </div>
                </div>
                <div class="up-form-group up-col-sm-24">
                    <label class="up-control-label">密码 </label>
                    <div class="up-col-sm-24">
                        <input type="password" id="fd_mm" name="fd_mm" class="up-form-control" >
                    </div>
                </div>
               	<div class="up-form-group up-col-sm-24">
                    <label class="up-control-label">描述 </label>
                    <div class="up-col-sm-24">
                        <textarea  id="fd_ms" name="fd_ms" class="up-form-control"  style="resize:none"></textarea>
                    </div>
                </div>
            </form>
        </div>
    <script>
    var fd_lxMap={"1":"子交换库","2":"平台交换库","3":"平台共享库","4":"平台仓库","5":"平台集市"}
    
    function cleanForm(objE){//objE为form表单  
        $(objE).find(':input').each(  
            function(){  
                 $(this).val('');  
            }     
        );  
		$("#fd_sjklx").val('');
		$("#fd_lx").val('');
    }  
    
    function f_check_IP(ip)      
    {  
       var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;//正则表达式     
       if(re.test(ip))     
       {     
           if( RegExp.$1<256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)   
           return true;     
       }     
       return false;      
    }  
    
    function f_check_str(str){
    	if(str=="" || str==undefined || str==null ){
    		return false;
    	}
    	return true;
    }
    
    //文本框失去焦点后
    $('form :input').blur(function(){
    	 var $parent = $(this).parent();
         //验证用户名
         if($(this).is('#fd_mc') ){
                if( !f_check_str(this.value) ){
                    var errorMsg = '名称不能为空!';
                    $parent.addClass('up-has-warning')
                    $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
                }
         }
         if($(this).is('#fd_sjkmc') ){
             if( !f_check_str(this.value) ){
                 var errorMsg = '数据库名称不能为空!';
                 $parent.addClass('up-has-warning')
                 $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
             }
      	 }
         if($(this).is('#fd_ip') ){
             if( !f_check_IP(this.value) ){
                 var errorMsg = '请输入正确ip地址!';
                 $parent.addClass('up-has-warning')
                 $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
             }
      	 }
         if($(this).is('#fd_dk') ){
             if( !f_check_str(this.value) || isNaN(Number(this.value)) ){
                 var errorMsg = '请输入正确端口!';
                 $parent.addClass('up-has-warning')
                 $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
             }
      	 }
         if($(this).is('#fd_yhm') ){
             if( !f_check_str(this.value) ){
                 var errorMsg = '用户名不能为空!';
                 $parent.addClass('up-has-warning')
                 $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
             }
      	 }
         if($(this).is('#fd_mm') ){
             if( !f_check_str(this.value) ){
                 var errorMsg = '密码不能为空!';
                 $parent.addClass('up-has-warning')
                 $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
             }
      	 }
    }).focus(function(){
    	 var $parent = $(this).parent();
    	 $parent.removeClass('up-has-warning')
    	 $parent.children().remove('.icon-u-warning')
    	 $parent.children().remove('.u_form_remind')
    })

    function cleanupForm(){
    	$("form .icon-u-warning").remove();
    	$("form .u_form_remind").remove();
    	$("form .up-has-warning").removeClass("up-has-warning");
    }
    
    function checkValue(fd_mc,fd_lx,fd_sjkmc,fd_ip,fd_dk,fd_sjklx,fd_ms,fd_yhm,fd_mm){
    	//验证名称
    	if(!f_check_str(fd_mc) ){
    		layer.msg("连接名称不能为空!");
    		return false;
    	}
    	//验证类型
    	if(!f_check_str(fd_lx)  ){
    		layer.msg("请选择类型!")
    		return false;
    	}
    	//数据库名称
    	if(!f_check_str(fd_sjkmc)  ){
    		layer.msg("数据库名称不能为空!")
    		return false;
    	}
    	//验证ip
		if(!f_check_IP(fd_ip)){
			layer.msg("请输入正确ip地址!")
			return false;
		}
    	//验证端口
    	if(!f_check_str(fd_dk) || isNaN(Number(fd_dk))){
    		layer.msg("请输入正确端口!")
    		return false;
    	}
    	//验证用户名
    	if(!f_check_str(fd_sjklx) ){
    		layer.msg("请选择数据库类型!");
    		return false;
    	}
    	//验证用户名
    	if(!f_check_str(fd_yhm) ){
    		layer.msg("用户名不能为空!");
    		return false;
    	}
    	//验证密码
    	if(!f_check_str(fd_mm) ){
    		layer.msg("密码不能为空!");
    		return false;
    	}
    	return true;
    }
    
    $("#search").click(function() {
		//提取参数
		var fd_mc = $.trim($("#fd_mc_s").val());
		var fd_lx = $.trim($("#fd_lx_s").val());
		var params = { "fd_mc": fd_mc,"fd_lx":fd_lx}
    	require(['layer'], function() {
    		$("#u_pagination").pagination('setParams', params);
    		$("#u_pagination").pagination('remote');
    	})
    })
    
    function btn1Fun(index,layero,url){
    	var fd_mc =  $.trim($("#fd_mc").val());
    	var fd_lx =  $.trim($("#fd_lx").val());
    	var fd_sjkmc =  $.trim($("#fd_sjkmc").val());
    	var fd_ip =  $.trim($("#fd_ip").val());
    	var fd_dk =  $.trim($("#fd_dk").val());
    	var fd_sjklx =  $.trim($("#fd_sjklx").val());
    	var fd_ms =  $.trim($("#fd_ms").val());
    	var fd_yhm =  $.trim($("#fd_yhm").val());
    	var fd_mm =  $.trim($("#fd_mm").val());
    	if(!checkValue(fd_mc,fd_lx,fd_sjkmc,fd_ip,fd_dk,fd_sjklx,fd_ms,fd_yhm,fd_mm)){
			return false;
    	}
    	var param = {"id":$("#id").val(),"fd_mc": fd_mc, "fd_lx": fd_lx ,  "fd_sjkmc": fd_sjkmc ,"fd_ip" : fd_ip ,"fd_dk" : fd_dk, "fd_sjklx" : fd_sjklx ,"fd_ms":fd_ms,"fd_yhm":fd_yhm,"fd_mm":fd_mm} ;
    	layer.load(2)
    	$.ajax({
    		type : "POST",
    		url : basePath + url,
    		dataType : "text",
    		data: param,
    		timeout:20000,
    		success: function (data) {
    			layer.closeAll('loading');
				if(data=="success"){
					layer.alert("保存成功!");
					$("#u_pagination").pagination('remote');
    			}else if(data=="sessionTimeout"){
					layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
						layer.closeAll();
					});
				}else{
					layer.alert("保存失败!")
				}
    	    },
			complete:function(XMLHttpRequest,status){
				layer.closeAll('loading');
				if(status=='timeout'){
					layer.closeAll('loading');
					layer.alert("连接失败!")
				}
			},
			error: function(e) {
				layer.closeAll('loading');
				layer.alert("服务器异常!")
			} 
    	});
		layer.close(index);
    }
    
    function btn2Fun(index,layero){
    	var fd_mc =  $.trim($("#fd_mc").val());
    	var fd_lx =  $.trim($("#fd_lx").val());
    	var fd_sjkmc =  $.trim($("#fd_sjkmc").val());
    	var fd_ip =  $.trim($("#fd_ip").val());
    	var fd_dk =  $.trim($("#fd_dk").val());
    	var fd_sjklx =  $.trim($("#fd_sjklx").val());
    	var fd_ms =  $.trim($("#fd_ms").val());
    	var fd_yhm =  $.trim($("#fd_yhm").val());
    	var fd_mm =  $.trim($("#fd_mm").val());
    	if(!checkValue(fd_mc,fd_lx,fd_sjkmc,fd_ip,fd_dk,fd_sjklx,fd_ms,fd_yhm,fd_mm)){
    		return false;
    	}
    	var param = {"fd_mc": fd_mc, "fd_lx": fd_lx , "fd_sjkmc" : fd_sjkmc ,"fd_ip" : fd_ip ,"fd_dk" : fd_dk, "fd_sjklx" : fd_sjklx ,"fd_ms":fd_ms,"fd_yhm":fd_yhm,"fd_mm":fd_mm} ;
    	layer.load(2)
    	$.ajax({
    		type : "POST",
    		url : basePath + "/ysjgl/checkConnecting",
    		dataType : "text",
    		data: param,
    		timeout:10000,
    		success: function (data) {
    			layer.closeAll('loading');
				if(data=="success"){
					layer.alert("连接成功!")
				}else{
					layer.alert("连接失败! "+data)
				}
    	    },
    		error: function(e) {
    			layer.closeAll('loading');
    			layer.alert("服务器异常!")
    		},
			complete:function(XMLHttpRequest,status){
				layer.closeAll('loading');
				if(status=='timeout'){
					layer.closeAll('loading');
					layer.alert("连接失败!")
				}
			}

    	});
    	return false;
    }
    
    function editorFun(id,fd_mc,fd_lx,fd_sjkmc,fd_ip,fd_dk,fd_sjklx,fd_ms,fd_yhm,fd_mm){
        require(['layer'], function() {
            layer.open({
                type: 1,
                title: '修改',
                content: $("#layer-add"),
                btn: ['确定','测试连接', '取消'],
                btn1: function(index,layero) {
                	return btn1Fun(index,layero,"/ysjgl/updateSjybRecord");
                },
                btn2: function(index, layero) {
                	return btn2Fun(index,layero);
                },
                btn3: function(index, layero) {
                	cleanupForm();
                    layer.close(index)
                },
                cancel: function(index, layero){ 
                	cleanupForm();
               	    layer.close(index)
               	}, 
                area: '600px'
            });
            $("#id").val(id);
        	$("#fd_mc").val(fd_mc);
        	$("#fd_lx").val(fd_lx);
        	$("#fd_sjkmc").val(fd_sjkmc);
        	$("#fd_ip").val(fd_ip);
        	$("#fd_dk").val(fd_dk);
        	$("#fd_sjklx").val(fd_sjklx);
        	$("#fd_ms").val((fd_ms));
        	$("#fd_yhm").val(fd_yhm);
        	$("#fd_mm").val(fd_mm);
        })
    }
    
    function detailFun(id,fd_mc,fd_lx,fd_sjkmc,fd_ip,fd_dk,fd_sjklx,fd_ms,fd_yhm,fd_mm){
    	$("form :input").attr("disabled",true);
        require(['layer'], function() {
            layer.open({
                type: 1,
                title: '详情',
                content: $("#layer-add"),
                cancel: function(index, layero){
                	$("form :input").attr("disabled",false);
               	    layer.close(index)
               	}, 
                area: '600px'
            });
            $("#id").val(id)
        	$("#fd_mc").val(fd_mc)
        	$("#fd_lx").val(fd_lx)
        	$("#fd_sjkmc").val(fd_sjkmc)
        	$("#fd_ip").val(fd_ip)
        	$("#fd_dk").val(fd_dk)
        	$("#fd_sjklx").val(fd_sjklx)
        	$("#fd_ms").val(fd_ms)
        	$("#fd_yhm").val(fd_yhm)
        	$("#fd_mm").val(fd_mm)
        })
    }
    
    function deleteFun(id){
        require(['layer'], function() {
            layer.confirm('确认删除',{
                btn: ['确定', '取消'],
                btn1: function(index,layero) {
                	var param = {"id":id};
	               	$.ajax({
	               		type : "POST",
	               		url : basePath + "/ysjgl/deleteSjybRecord",
	               		dataType : "text",
	               		data: param ,
	               		success: function (data) {
	               			if(data=="success"){
								layer.alert("删除成功!");
								$("#u_pagination").pagination('remote');
	               			}else if(data=="1"){
	               				layer.alert("该数据源正在被使用,无法删除!")
	               			}else{
	               				layer.alert("删除异常!")
	               			}
	               	    },
	            		error: function(e) {
	            			layer.alert("服务器异常!")
	            		} 
	               	}); 
	               	layer.close(index)
                },
                btn2: function(index, layero) {
                    layer.close(index)
                },
            });
        })
    }
    
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
                url : basePath+"/ysjgl/ajaxSjybRecord",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';				
                	for(var i=0;i<recordList.length;i++){
                		infoStr=infoStr+"<tr>"+
                		"<td> <div style='max-width:300px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].fd_mc+"'>"+recordList[i].fd_mc+"</div></td>"+
                		"<td ><div style='width:80px' >"+fd_lxMap[recordList[i].fd_lx]+"</div></td>"+
                		"<td >"+recordList[i].fd_sjkmc+"</td>"+
                		"<td ><div style='width:80px' >"+recordList[i].fd_ip+"</div></td>"+
                		"<td> <div style='width:35px' >"+recordList[i].fd_dk+"</div></td>"+
                		"<td><div style='width:60px' >"+recordList[i].fd_sjklx+"</div></td>"+
                		"<td><div  style='width:150px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+(recordList[i].fd_ms==null?"":recordList[i].fd_ms)+"'>"+(recordList[i].fd_ms==null?"":recordList[i].fd_ms)+"</div></td><td><div style='width:260px'>"+
    					"<button onclick='editorFun(\""+recordList[i].id+"\",\""+recordList[i].fd_mc+"\",\""+recordList[i].fd_lx+"\",\""+recordList[i].fd_sjkmc+"\",\""+recordList[i].fd_ip+"\",\""+recordList[i].fd_dk+"\",\""+recordList[i].fd_sjklx+"\",\""+(recordList[i].fd_ms==null?"":recordList[i].fd_ms)+"\",\""+recordList[i].fd_yhm+"\",\""+recordList[i].fd_mm+"\")'  class='up-btn up-btn-default up-btn-xs u-editor'><i class='icon-u-edit'></i>修改</button>"+
                        "<button onclick='deleteFun(\""+recordList[i].id+"\")' class='up-btn up-btn-default up-btn-xs u-detail'><i class='icon-u-eye'></i>删除</button>"+
                        "<button onclick='detailFun(\""+recordList[i].id+"\",\""+recordList[i].fd_mc+"\",\""+recordList[i].fd_lx+"\",\""+recordList[i].fd_sjkmc+"\",\""+recordList[i].fd_ip+"\",\""+recordList[i].fd_dk+"\",\""+recordList[i].fd_sjklx+"\",\""+(recordList[i].fd_ms==null?"":recordList[i].fd_ms)+"\",\""+recordList[i].fd_yhm+"\",\""+recordList[i].fd_mm+"\")'  class='up-btn up-btn-default up-btn-xs u-editor'><i class='icon-u-edit'></i>详情</button>"+
                        "<a href='${basePath}/metacontroller/getDatayuan?id="+recordList[i].id+"' class='up-btn up-btn-default up-btn-xs u-cog'><i class='icon-u-cog'></i>元数据管理</a>"+
						"</div></td></tr>"; 
                	}
                	$("tbody").empty();
                	$("tbody").append(infoStr);
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

		$(".u-add").click(function() {
			cleanForm($("#layer-add"))
            require(['layer'], function() {
                layer.open({
                    type: 1,
                    title: '添加数据源',
                    content: $("#layer-add"),
                    btn: ['确定','测试连接', '取消'],
                    btn1: function(index, layero) {
                    	return btn1Fun(index,layero,"/ysjgl/addSjybRecord");
                    },
                    btn2: function(index, layero) {
						return btn2Fun(index,layero);
                    },
                    btn3: function(index, layero) {
                    	cleanupForm();
                        layer.close(index)
                    },
                    cancel: function(index, layero){ 
                    	cleanupForm();
                   	    layer.close(index)
                   	}, 
                    area: '600px',
                });
            })
        })
    })
    </script>
</body>

</html>
