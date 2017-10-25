<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
</head>

<body class="u_scroll">

    <div class="up-container-fluid">
        <form class="u-margin-top20 u-margin-bottom20 up-clearfix" action="${basePath}/metazdcontroller/synysjData"  method="post">
            <div class="up-form-inline">
              <div class="up-form-group">
			      <label for="">数据源</label> 
			       <select name="opt" id="selid" class="up-form-control">
				 <c:forEach items="${selist}" var="item">		
				     <option value="${item.id}" >${item.fd_mc}</option>
				</c:forEach>			
			        </select>
		         </div>
                <div class="up-form-group">
                    <input type="text" class="up-form-control" name="synbm"  id="inbm" placeholder="请输入表名">
                </div>
                <div class="up-form-group">
                    <button type="button"  id="synfind" class="up-btn up-btn-primary up-btn-block"><i class="icon-u-search"></i>查询</button>
                </div>
                <div class="up-form-group">
	              <button type="button"  disabled="disabled" id="synbtn" class="up-btn up-btn-sm up-btn-default u-cw" ><i class="icon-u-arrows-cw"></i>同步
                    </button>                

                </div>
            </div>
            <div class="u_form up-clearfix u-margin-top20">
                <div class="up-form-group up-cq-group">
                    <label class="up-control-label" style="padding-right:4px;">描述 </label>
                    <div class="up-col-sm-24">
                        <input type="text" class="up-form-control" id="bms" rows="1"	 disabled="disabled" style="resize:none" />
                    </div>
                </div>
            </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>列名</th>
                    <th>字段类型</th>
                    <th>字段长度</th>
                    <th>字段精度</th>
                    <th>是否主键</th>
                    <th>缺省值</th>
                    <th>是否可为空</th>
                    <th>字段描述</th>
                </tr>
            </thead>
            <tbody>
         
            </tbody>
        </table>

        
    </div>
    <script>
    require(['uskin'], function() {

      require(['layer'], function () {
        $("#synfind").click(function() {
        	$.post('${basePath}/metazdcontroller/getDatatosyn', {opt:$("#selid").val(),synbm:$("#inbm").val()},
        			function(data) {
        		var daraobj=eval("("+data+")");
        		        $("tbody").html(daraobj.str); 
        		        $("#bms").val(daraobj.bms);
        		        $("#bms").attr("title",daraobj.bms);
        		       // $("#synbtn").attr("disabled","false");
        		       //console.log("Aaaa"+$.contains("font",str));
        		       //console.log(stro.indexOf("font"));
        		       if(daraobj.str.indexOf("font")>=0){
        		    	  // console.log("Aaaa"+str.indexOf("font"));
        		    	   $("#synbtn").removeAttr("disabled");
        		       }     
        	});
        }) 
        
        $("#synbtn").click(function() {
        	$.post('${basePath}/metazdcontroller/synysjData', {opt:$("#selid").val(),synbm:$("#inbm").val(),bms:$("#bms").val()},
        			function(data) {
                        if(data=="ok"){                      	
    						layer.alert("同步成功！",{closeBtn:0},function(){
    							window.location.href="${basePath}/metacontroller/getDatayuan"
    						})
            			}else if(data=="sessionTimeout"){
							layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
								layer.closeAll();
							});
                         }else{
                        	 layer.alert("同步失败！");
                         }
        	});
        }) 
      })    
    })
    </script>
</body>
</html>