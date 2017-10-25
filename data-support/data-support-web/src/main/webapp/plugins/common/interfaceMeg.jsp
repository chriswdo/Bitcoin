<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>接口服务</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <%@include file="/common/common.jsp"%>
</head>

<body class="u_scroll">
    <div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
                <div class="up-form-group ">
                    <label for="">接口名称 </label>
                    <input type="text" class="up-form-control" id="fd_jkmc"  placeholder="">
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
                    <th>接口名称</th>
                    <th>接口代码</th>
                    <th>备注</th>
                    <th>数据源</th>
                    <th>sql语句</th>
                     <!-- <th>更新时间</th> -->
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
        <!--接口服务新增修改弹框  -->
	<div class="up-container-fluid" style="display: none" id="layer-interface">
			<form class="u_form up-clearfix" style="width: 530px;margin:20px auto 0">
				<div class="up-form-group up-col-sm-26">
					<label class="up-control-label">接口名称 </label>
					<div class="up-col-sm-22">
						<input type="text" name="" id="jk_name" class="up-form-control"
							placeholder="">
					</div>
				</div>
				<div class="up-form-group up-col-sm-26">
					<label class="up-control-label">接口代码 </label>
					<div  class="up-col-sm-14">
					 <input type="text" disabled="true" value="http://IP:Port/data-support-web/query/"   id="" class="up-form-control"
							placeholder="">			
					</div>
					<div class="up-col-sm-7" style="margin-left:18px">
					<!-- <span>http://IP:Port/data-support-web/query/</span> -->
						<input type="text" name="" id="jk_code" class="up-form-control"
							placeholder="">
					</div>
				</div>		
				<div class="up-form-group up-col-sm-26">
					<label class="up-control-label">备注</label>
					<div class="up-col-sm-22">
						<input type="text" name="" id="jk_description" class="up-form-control"
							placeholder="">
					</div>
				</div>							
				<div class="up-form-group up-col-sm-26">
					<label for="" class="up-control-label">数据源 </label>
					<div class="up-col-sm-22">
						<select name="" id="jk_sjy" class="up-form-control">
							<option value="">请选择</option>
							<c:forEach items="${selist}" var="item">
							<c:if test="${item.fd_lx==3||item.fd_lx==4}"> 
								<option value="${item.id}">${item.fd_mc}</option>
							</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="up-form-group up-col-sm-26">
					<label class="up-control-label">分页前缀</label>
					<div class="up-col-sm-22">
						<input type="text" id="page_pre"  disabled="true"  value="SELECT * FROM   (  SELECT A.*  ,ROWNUM RN FROM ("    title="SELECT * FROM   (  SELECT A.*  ,ROWNUM RN FROM ("  class="up-form-control"
							placeholder="">
					</div>
				</div>	
				<div class="up-form-group up-col-sm-26">
					<label class="up-control-label">sql语句 </label>
					<div class="up-col-sm-22">
						<textarea  name="" id="jk_sqldes" placeholder="sql语句参数格式为\#{参数名},如果调用接口执行SELECT语句需带入分页参数startNum和endNum"  class="up-form-control">
						</textarea>
					</div>
				</div>	
				<div class="up-form-group up-col-sm-26">
					<label class="up-control-label">分页后缀</label>
					<div class="up-col-sm-22">
<!-- 						<input type="text" id="page_end" name=""  value=""  disabled="true"  class="up-form-control" -->
<!-- 							placeholder=""> -->
					
						<textarea  name="" id="page_end" class="up-form-control" disabled="true" >
						</textarea>
					</div>
				</div>									
			</form>
		</div>	
    <script>
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
            url : basePath+"/interfaceDefine/getInterfaceMeg",
            success:function(data){
            	recordList=data.list;
            	var infoStr = '';				
            	for(var i=0;i<recordList.length;i++){
            		infoStr=infoStr+"<tr>"+
            	     "<td><div>"+recordList[i].jk_name+"</div></td>"+ 
                     "<td><div style='max-width:160px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].jk_code+"'>"+recordList[i].jk_code+"</div></td>"+ 
                     "<td><div style='width:120px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+(recordList[i].fd_jkbz==null?"":recordList[i].fd_jkbz)+"'>"+(recordList[i].fd_jkbz==null?"":recordList[i].fd_jkbz)+"</div></td>"+ 
                     "<td tdvalue='"+recordList[i].sjy_id+"'><div style='width:120px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].fd_mc+"'>"+recordList[i].fd_mc+"</div></td>"+ 
                     "<td><div style='width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].sql_info+"'>"+recordList[i].sql_info+"<div></td>";
                     /* + 
                     "<td><div style='width:120px'>"+(recordList[i].fd_gxsj==null?"":recordList[i].fd_gxsj)+"<div></td>" */;
                     if(recordList[i].jk_zt=="N"){
                    	 infoStr=infoStr+
                    	 "<td><div style='width:30px' >停用</div></td>";
                     }else if(recordList[i].jk_zt=="Y"){
                    	 infoStr=infoStr+
                    	 "<td><div style='width:30px' >启用</div></td>";
                     }
                     infoStr=infoStr+"<td><div style='width:100px'>";
                     if(recordList[i].jk_zt=="N"){
                    	 infoStr=infoStr+
                         	'<button type="button" onclick="startFun(\''+recordList[i].id+'\')"  class="up-btn up-btn-xs up-btn-default"> <i class="icon-u-qiyong"></i>启用</button>'+ 
                         	'<button type="button" id="inter_'+recordList[i].id+'"  onclick="editorFun(\''+recordList[i].id+'\')" class="up-btn up-btn-default up-btn-xs u-editor"><i class="icon-u-edit"></i>编辑</button>'
                     }else if(recordList[i].jk_zt=="Y"){
                    	 infoStr=infoStr+
                         	'<button type="button" onclick="closeFun(\''+recordList[i].id+'\')"  class="up-btn up-btn-xs up-btn-default"> <i class="icon-u-tingyong"></i>停用</button> '
                     }
                    infoStr=infoStr+"</div></td></tr>";
            	}
            	$("#u_pagination_body").empty();
            	$("#u_pagination_body").append(infoStr);
            },
            totalName:'total',
			pageParams: function(data){
				return {
					pageNum:data.pageIndex+1,
					pageSize:data.pageSize
				};
			}/* ,
			params:{"fd_lx":fd_lx} */
            
         }  
    });
 })
 
     function cancelFun(){
	     $("#jk_name").val("");
	     $("#jk_code").val("");
		 $("#jk_description").val("");
		 $("#jk_sjy").val("");
		 $("#jk_sqldes").val("");
    }
         //新增接口弹框
        $("#add").click(function() {
            require(['layer'], function() {
                layer.open({
                    type: 1,
                    title: '新增',
                    btn: ['确定', '取消'],
                    content: $("#layer-interface"),
                    btn2: function(index, layero) {		       
                        layer.close(index);
                        cancelFun();
                    },
                    success: function(layero, index){
                    	 cancelFun();
                    	 $("#page_end").val(" ) A   WHERE ROWNUM <= "+"#"+"{endNum}   )  WHERE RN>= "+"#"+"{startNum}  ");
                    	 $("#page_end").attr("title"," ) A   WHERE ROWNUM <= "+"#"+"{endNum}   )  WHERE RN>= "+"#"+"{startNum}  ");
                    },
                    yes: function(index, layero) {
                  		if($("#jk_name").val()=="" || $("#jk_code").val()=="" || $("#jk_sjy").val()=="" || $("#jk_sqldes").val()==""){
               		    layer.alert("请确认你的接口名称,数据源,sql语句是否填写!");
               		    return;
               		}
                  		var regex=/^[a-zA-Z0-9]+$/;
                  		if(!regex.test($("#jk_code").val())){
                  			layer.alert("接口代码只能包含字母，数字!");
                  			return;
                  		}
                      	var key_sql=$("#jk_sqldes").val();
                        var keywords=getKeywords(key_sql);
                           var param = {jk_name:$("#jk_name").val(),jk_code:$("#jk_code").val(),fd_jkbz:$("#jk_description").val(),sjy_id:$("#jk_sjy").val(),sql_info:$("#jk_sqldes").val(),jk_zt:"N",keywords:keywords,page_pre:$("#page_pre").val(),page_suf:$("#page_end").val()};
		                	$.ajax({
		                		type : "POST",
		                		url : '${basePath}/interfaceDefine/savexJkMessage',
		                		dataType : "text",
		                		data: param,
		                		success: function (data) {
		                            if(data=="success"){
		                                //layer.alert("操作成功!");
		                                layer.close(index);
		                                $("#u_pagination").pagination('remote');	            						
		                             }else{
		                            	 layer.alert("接口英文名重复!");
		                             }
		                	    }
		                	}); 
                     },
                     area:[ '620px','510px']
                });
            })
        });
         
     function startFun(id){
	   	 require(['layer'], function() {
          	 $.ajax({
           		type : "POST",
           		url : basePath + "/interfaceDefine/changeInterdaceStatus",
           		dataType : "text",
           		data: {"id":id,"status":"Y"} ,
           		success: function (data) {
           			if(data=="success"){
		        		$("#u_pagination").pagination('remote');
           			}else{
           				layer.alert("更改异常!");
           			}
           	    },
        		error: function(e) {
        			layer.alert("服务器异常!")
        		} 
             }); 
	   	 })
	}
    
    function closeFun(id){
	   	 require(['layer'], function() {
          	 $.ajax({
           		type : "POST",
           		url : basePath + "/interfaceDefine/changeInterdaceStatus",
           		dataType : "text",
           		data: {"id":id,"status":"N"} ,
           		success: function (data) {
           			if(data=="success"){
		        		$("#u_pagination").pagination('remote');
           			}else{
           				layer.alert("更改异常!");
           			}
           	    },
        		error: function(e) {
        			layer.alert("服务器异常!")
        		} 
             }); 
	   	 })
	}
    
	function editorFun(id){
	   	 require(['layer'], function() {
             layer.open({
                 type: 1,
                 title: '新增',
                 btn: ['确定', '取消'],
                 content: $("#layer-interface"),
                 btn2: function(index, layero) {		       
                     layer.close(index);
                     cancelFun();
                 },
                 success: function(layero, index){
                	 var jkid="inter_"+id;
                	 var dataObj = new Array();
                	 $("#page_end").val(" ) A   WHERE ROWNUM <= "+"#"+"{endNum}   )  WHERE RN>= "+"#"+"{startNum}  ");
                	 $("#page_end").attr("title"," ) A   WHERE ROWNUM <= "+"#"+"{endNum}   )  WHERE RN>= "+"#"+"{startNum}  ");
                	  
                	 $("#"+jkid).parent().parent().parent().find("td").each(function(i){
                		 if(!($(this).attr("tdvalue")==undefined)){
                			     dataObj[i]=$(this).attr("tdvalue");
                        	 }else{
                        		 dataObj[i]=$(this).find("div").text();
                        	 } 
                	 });
            	     $("#jk_name").val(dataObj[0]);
            	     $("#jk_code").val(dataObj[1]);
            		 $("#jk_description").val(dataObj[2]);
            		 $("#jk_sjy").val(dataObj[3]);
            		 $("#jk_sqldes").val(dataObj[4]); 
                   },
                 yes: function(index, layero) {
               		if($("#jk_name").val()=="" || $("#jk_code").val()=="" || $("#jk_sjy").val()=="" || $("#jk_sqldes").val()==""){
               			layer.alert("请确认你的接口名称,数据源,sql语句是否填写!");
               		    return;
               		}
              		var regex=/^[a-zA-Z0-9]+$/;
              		if(!regex.test($("#jk_code").val())){
              			layer.alert("接口代码只能包含字母，数字!");
              			return;
              		}
                  	var key_sql=$("#jk_sqldes").val();                 
                    var keyWords=getKeywords(key_sql);
              var param = {id:id,jk_name:$("#jk_name").val(),jk_code:$("#jk_code").val(),fd_jkbz:$("#jk_description").val(),sjy_id:$("#jk_sjy").val(),sql_info:$("#jk_sqldes").val(),keywords:keyWords};
             	$.ajax({
             		type : "POST",
             		url : '${basePath}/interfaceDefine/updateJkMessage',
             		dataType : "text",
             		data: param,
             		success: function (data) {
                         if(data=="success"){
                        	    layer.close(index);
                             	$("#u_pagination").pagination('remote');
         					
                          }
             	    }
             	}); 
                  },
                  area:[ '620px','510px']
             });
	   	 })
	}
	function getKeywords(key_sql){
    	var keywords="";
	    while(key_sql.indexOf("#")!=-1 && key_sql.indexOf("{")!=-1 && key_sql.indexOf("}")!=-1 )
	  	  {
	  	   var firstIndex=key_sql.indexOf("{");
	  	   var secondIndex=key_sql.indexOf("}");
	  	   keywords+="#"+key_sql.substring(firstIndex+1,secondIndex);  
	  	   key_sql=key_sql.substring(secondIndex+1);
	  	  }
	    return keywords
    }	

    $("#search").click(function() {
   		//提取参数
   		var jk_name = $.trim($("#fd_jkmc").val());
   		params = { "jkname": jk_name}
       	require(['layer'], function() {
       		$("#u_pagination").pagination('setParams', params);
       		$("#u_pagination").pagination('remote');
       	})
    })
    </script>
</body>

</html>
