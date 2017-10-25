<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>

<%-- <link rel="stylesheet" href="${basePath }/resources/khala/css/common.css">
<link rel="stylesheet" href="${basePath }/resources/khala/css/main.css">
<script type="text/javascript" src="${basePath }/resources/jquery/2.1.1/jquery-2.1.1.min.js"></script>

<script type="text/javascript" src="${basePath }/resources/jquery/colResizable/js/colResizable-1.5.min.js"></script>
<script type="text/javascript" src="${basePath }/resources/khala/js/common.js"></script>
<script type="text/javascript" src="${basePath }/resources/khala/js/funtional/modle_list.js"></script>
<script type="text/javascript" src="${basePath }/resources/khala/js/funtional/modle_save.js"></script>
<script type="text/javascript" src="${basePath }/resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath }/resources/khala/js/common.js"></script>
<script type="text/javascript" src="${basePath }/resources/uplan/js/uplanui.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/uplan/css/uplanui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/uplan/css/uplanui.ext.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/uplan/css/uplan.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/uplan/css/admin.css">   --%>
<script type="text/javascript">
var basePath='${basePath}';
var baseurl='${basePath}${path }';
</script>



    <title>首页</title>
    <link rel="stylesheet" href="${basePath}/resources/uskin/css/uplan.css">
    <link rel="stylesheet" href="${basePath}/resources/uskin/css/uskin.css">
    <link rel="stylesheet" href="${basePath}/resources/uskin/css/iconfont.min.css">
    <script src="${basePath}/resources/uskin/js/require.min.js"></script>
    <script src="${basePath}/resources/uskin/js/jquery.min.js"></script>
    <script src="${basePath}/resources/uskin/js/main.min.js"></script>
    <!--[if (gte IE 6)&(lte IE 8)]>
      <script type="text/javascript" src="[JS library]"></script>
      <script type="text/javascript" src="js/selectivizr.min.js"></script>
      <noscript><link rel="stylesheet" href="[fallback css]" /></noscript>
      <script type="text/javascript" src="js/html5shiv.min.js"></script>
      <script type="text/javascript" src="js/respond.min.js"></script>
    <![endif]-->
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
    </style>


 