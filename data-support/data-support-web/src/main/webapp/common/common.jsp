<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<script>
var basePath='${basePath}';
var baseurl='${basePath}${path }';
</script>

<link rel="stylesheet" href="${basePath}/resources/uskin/css/uplan.css">

    <link rel="stylesheet" href="${basePath}/resources/uskin/css/uskin.css">
     <link rel="stylesheet" href="${basePath}/resources/uskin/css/zTreeStyle.css">
    <link rel="stylesheet" href="${basePath}/resources/uskin/css/iconfont.min.css">
    <link rel="stylesheet" href="${basePath}/resources/uplan/css/app.css">
    <script src="${basePath}/resources/uskin/js/require.min.js"></script>
    <script src="${basePath}/resources/uskin/js/jquery.min.js"></script>
    <script src="${basePath}/resources/uskin/js/main.min.js"></script>
 