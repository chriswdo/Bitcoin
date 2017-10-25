<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>登录页</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
    <script src="${basePath}/resources/uskin/js/md5.js"></script>
    <style>
    .login_head {
        height: 100px;
        line-height: 100px;
        background: #fff;
    }
    
    .login_main {
        background: #0099cc url(${basePath}/resources/uskin/images/login_bg.jpg)  center 0;
    }
    
    .login_logo {
        line-height: 100px;
    }
    
    .login_logo img {
        margin-right: 15px;
        vertical-align: middle;
    }
    
    .login_logo span {
        height: 35px;
        width: 15px;
        vertical-align: middle;
        border-left: 1px solid #ddd;
    }
    
    .login_logo > * {
        display: inline-block;
    }
    
    .login_body {
        background-color: #fff;
        border: 1px solid #ddd;
        padding: 40px 20px;
        margin: 100px 0;
        border-radius: 5px
    }
     .login_body .up-input-group{ 
     	margin-bottom:15px; 
     } 
     .up-form-control{ 
     	height:40px; 
     } 
     .u_form_icon{ 
     	top:11px; 
     } 
    </style>
</head>

<body class="u_scroll">
    <div class="login_head">
        <div class="up-container">
            <div class='up-pull-left login_logo'>
                <img src="${basePath}/resources/uskin/images/login_logo.png" alt="" class="inlbk">
                <span></span>
                <h3>市场监管大数据支撑平台</h3>
            </div>
            <div class="up-pull-right">
                <span class="up-text-muted">欢迎来使用Welcome to use</span>
            </div>
        </div>
    </div>
    <div class="login_main">
        <div class="up-container">
            <div class="up-pull-right up-col-sm-8">
                <form class="u_form u_form_block login_body">
                    <div class="up-form-group">
                        <div class="up-col-sm-24">
                            <div class="up-input-group">
                                <span class="up-input-group-addon" id=""><i class="icon-u-user"></i></span>
                                <input type="text"  id="username" class="up-form-control" placeholder="请输入用户名" aria-describedby="basic-addon1" style="font-size:16px">
                            </div>
                        </div>
                    </div>
                    <div class="up-form-group">
                        <div class="up-col-sm-24">
                            <div class="up-input-group">
                                <span class="up-input-group-addon" id=""><i class="icon-u-lock"></i></span>
                                <input type="password" id="password" class="up-form-control" placeholder="请输入密码" aria-describedby="basic-addon1" style="font-size:16px">
                            </div>
                        </div>
                    </div>
                    <div class="up-form-group" hidden >
                        <div class="up-col-sm-24">
                         		<span id="errorMsg" class="up-help-block"></span>
                        </div>
                    </div>
                    <button type="button" class="up-btn up-btn-primary up-btn-lg up-btn-block">登录</button>
                </form>
            </div>
        </div>
    </div>
    <div class="u-padding-top25 up-text-center">版权信息 广东亿迅科技有限公司 中国电信股份公司广东分公司</div>
</body>
<script>
//回车键事件 绑定键盘按下事件 
$(document).keypress(function(e) { 
 // 回车键事件 
    if(e.which == 13) { 
		jQuery(".up-btn-primary").click(); 
    } 
}); 

require(['uskin','layer'], function() {
    //文本框失去焦点后
    $('form :input').blur(function(){
    	 var $parent = $(this).parent();
         //验证用户名
         if($(this).is('#username') ){
                if( !f_check_str(this.value) ){
                    var errorMsg = '用户名不能为空!';
                    $parent.addClass('up-has-warning')
                    $(this).after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
                }
         }
         if($(this).is('#password') ){
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
	
	
    function f_check_str(str){
    	if(str=="" || str==undefined || str==null ){
    		return false;
    	}
    	return true;
    }
    
    function checkValue(username,password){
    	//验证名称
    	if(!f_check_str(username) ){
    		layer.msg("用户名不能为空!");
    		return false;
    	}
    	//验证类型
    	if(!f_check_str(password)  ){
    		layer.msg("密码不能为空!")
    		return false;
    	}
    	return true;
    }
    
	$('.up-btn-lg').click(function(){
		var username = $.trim($("#username").val())
		var password = $.trim($("#password").val())
		if(!checkValue(username,password)){
			return false;
		}
       	$.ajax({
       		type : "POST",
       		url : basePath + "/userManage/login",
       		dataType : "text",
       		data: {"fd_dlmc":username,"fd_mm":$.md5(password).toUpperCase()} ,
       		success: function (data) {
       			if(data=="success"){
       				window.location.href=basePath+"/userManage/toindexJsp";
       			}else if(data=="unameErr"){
					layer.alert("该账号不存在!");
       			}else if(data=="pwErr"){
       				layer.alert("密码错误!");
       			}else if(data=="adminErr"){
       				layer.alert("管理员用户未创建!");
       			}else{
       				layer.alert("登录失败!");
       			}
       	    },
    		error: function(e) {
    			layer.alert("服务器异常!")
    		} 
       	}); 
		
	})
})
</script>

</html>
