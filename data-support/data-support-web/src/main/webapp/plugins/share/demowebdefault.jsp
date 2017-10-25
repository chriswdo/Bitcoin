<!doctype html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<html>
<head>
<fmt:setBundle basename="MainResource" var="main"/>
<title><fmt:message key="system.name" bundle="${main}"/></title>	
  
<sitemesh:head />
	<%@include file="/common/web-taglib.jsp" %>
	<%@include file="/common/common-head.jsp" %>
</head>

<body>
    <div class="jg_cont">
        <%@include file="/plugins/parts/header.jsp" %>
        <div class="u_sidebar jg_side" id='u_sidebar'></div>
        <div class="jg_main">
            <iframe src="${basePath}/example/test" id="jg_iframe" frameborder="0" marginheight="0" marginwidth="0" width="100%" height="100%"></iframe>
        </div>
        <div id="admin" class="u-padding-top15 up-container-fluid">
            <!-- <div class="u_tab_nav u_tab_grid">
                <ul class="u_tab_nav_body">
                    <li class="active" data-tab="#example_tab1">个人资料
                    </li>
                    <li data-tab="#example_tab2">修改密码
                    </li>
                </ul>
            </div>
            <div class="u_tabs">
                <div class="u_tab_body active" id='example_tab1'>
                    <form class="u_form u-padding-top15">
                        <div class="up-form-group">
                            <label class="up-control-label"><span class="up-text-danger">*</span>真实姓名：</label>
                            <div class="up-col-sm-24">
                                <div class="u_form_textmiddle">小明明</div>
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label"><span class="up-text-danger">*</span>会员身份：</label>
                            <div class="up-col-sm-24">
                                <div class="u_form_textmiddle">普通会员 <a href="javascript:void(0);">升级</a></div>
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">昵称：</label>
                            <div class="up-col-sm-24">
                               <div class="u_form_textmiddle">光头强</div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="u_tab_body" id='example_tab2'>
                    <form class="u_form u-padding-top15 up-col-sm-16">
                        <div class="up-form-group ">
                            <label class="up-control-label"><span class="up-text-danger">*</span>登录账号：</label>
                            <div class="up-col-sm-24">
                               <div class="u_form_textmiddle up-text-danger">admin</div>
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">旧密码：</label>
                            <div class="up-col-sm-24">
                                <input type="password" name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">新密码：</label>
                            <div class="up-col-sm-24">
                                <input type="password" name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                        <div class="up-form-group">
                            <label class="up-control-label">确认密码：</label>
                            <div class="up-col-sm-24">
                                <input type="password" name="" class="up-form-control up-input-sm">
                            </div>
                        </div>
                    </form>
                </div>
            </div> -->
        </div>
    </div>
    <%@include file="/plugins/parts/left.jsp" %>
</body>
</html>