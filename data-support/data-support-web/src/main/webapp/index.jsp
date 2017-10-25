<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>首页</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
    <%@include file="/plugins/parts/left.jsp"%>
    <script src="${basePath}/resources/uskin/js/md5.js"></script>
    <style>
    html,
    body {
        height: 100%;
        width: 100%;
        overflow: hidden;
    }
    
    .jg_nav {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
    }
    
    .jg_side {
        position: fixed;
        left: 0;
        top: 50px;
        bottom: 0;
    }
    
    .jg_cont {
        padding: 50px 0 0 210px;
        height: 100%;
        transition: padding 0.5s
    }
    
    .jg_cont.cont_slide {
        padding-left: 50px;
    }
    
    .jg_main {
        height: 100%;
    }
    .u_node_arrow{
    	margin-top: -15px;
    }
    </style>
</head>

<body>
    <div class="jg_cont">
        <nav class="up-navbar up-navbar-inverse jg_nav" role="navigation">
            <div class="up-navbar-header" style="width: auto">
                <a class="up-navbar-brand" href="javascript:void(0);" ><strong>市场监管信息后台管理系统</strong></a>
            </div>
            <div class="up-collapse up-navbar-collapse" id="navbar-collapse">
                <ul class="up-nav up-navbar-nav up-pull-right">
                     <li>
                        <div class="u_nav_input u_search_input">
                            <a id="home" href="javascript:void(0);" style="color:#FFF;">首页</a> 
                        </div>
                    </li>
                    <li class="up-dropdown">
                        <a href="javascript:void(0);" class="up-dropdown-toggle" data-toggle="dropdown">${userInfo.fd_yhmc} <b class="up-caret"></b>
            </a>
                        <ul class="up-dropdown-menu">
                            <li id="information"><a href="javascript:void(0);">修改密码</a></li>
                            <li class="up-divider"></li>
                            
                            <li id="logout"><a href="javascript:void(0);">退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="u_sidebar jg_side" id='u_sidebar'></div>
        <!-- 主体展示层 -->
        <div class="jg_main">
            <iframe src="${basePath}/userManage/toHomeJsp" id="jg_iframe" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>
        </div>
        
        <div id="admin" class="u-padding-top15 up-container-fluid">
                <div class="u_tab_body active" id='example_tab3'>
                    <form class="u_form u-padding-top15 up-col-sm-22">
                        <div class="up-form-group ">
                            <label class="up-control-label"><span class="up-text-danger">*</span>登录账号：</label>
                            <div class="up-col-sm-24">
                               <div class="u_form_textmiddle up-text-danger">${userInfo.fd_dlmc}</div>
                            </div>
                        </div>                       
                        <div class="up-form-group" style="display: none" >
                            <label class="up-control-label">登陆名称：</label>
                            <div class="up-col-sm-24">
                                <input type="text" id="username" value="${userInfo.fd_dlmc}"  name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">旧密码：</label>
                            <div class="up-col-sm-24">
                                <input type="password" id="password_old" name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">新密码：</label>
                            <div class="up-col-sm-24">
                                <input type="password" id="password_new" name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">确认密码：</label>
                            <div class="up-col-sm-24">
                                <input type="password" id="password_sure" name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                    </form>
                </div>
<!--             </div> -->
        </div>
    </div>
</body>
<script>
function cleanForm(objE){//objE为form表单  
    $(objE).find(':input').each(  
        function(){  
             $(this).val('');  
        }     
    );  
}  

$("#information").click(function() {
    require(['layer'], function() {
        function f_check_str(str){
        	if(str=="" || str==undefined || str==null ){
        		return false;
        	}
        	return true;
        }
    	
    	 function checkValue(username,password_old,password_new,password_sure){
    	    	//验证类型
    	    	if(!f_check_str(password_old)  ){
    	    		layer.msg("原密码不能为空!")
    	    		return false;
    	    	}
    	    	//数据库名称
    	    	if(!f_check_str(password_new)  ){
    	    		layer.msg("新密码不能为空!")
    	    		return false;
    	    	}
    	    	//数据库名称
    	    	if(!f_check_str(password_sure)  ){
    	    		layer.msg("确认密码不能为空!")
    	    		return false;
    	    	}
    	    	//验证密码是否一致
    	    	if(password_new!=password_sure){
    	    		layer.msg("新密码和确认密码不一致!")
    	    		return false;
    	    	}
    	    	return true;
    	 }
    	
        layer.open({
            type: 1,
            title:'修改密码',
            content: $("#admin"),
            btn: ['确定','取消'],
            btn1:function(index, layero){
            	var username =  $.trim($("#username").val());
            	var password_old =  $.trim($("#password_old").val());
            	var password_new =  $.trim($("#password_new").val());
            	var password_sure =  $.trim($("#password_sure").val()); 
            	if(!checkValue(username,password_old,password_new,password_sure)){
            		return false;
            	}
            	var params={
            				"username":username,
            				"password_old":$.md5(password_old).toUpperCase(),
            				"password_new":$.md5(password_new).toUpperCase()
            			}
               	$.ajax({
               		type : "POST",
               		url : basePath + "/userManage/modifyxPassword",
               		dataType : "text",
               		data: params ,
               		success: function (data) {
               			if(data=="success"){
							layer.alert("修改成功!");
							layer.close(index);
							cleanForm("#admin");
               			}else if(data=="passwordErr"){
							layer.alert("原密码错误!");
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
                layer.close(index)
            },
            area:'400px'

        });
    })
})

$("#logout").click(function(){
	window.location.href="${basePath}/login.jsp"
})

$("#home").click(function(){
	 $("#jg_iframe").attr("src", basePath+"/userManage/toHomeJsp");
})

</script>
</html>
