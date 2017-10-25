<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" />
<script>
var basePath='${basePath}';
var baseurl='${basePath}${path }';
</script>
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
</head>

<body class="u_scroll">

<!-- <div id="layer-editor" class="u-padding-top15 up-container-fluid" style="display: none">
   <div class="u_form">
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">表名：</label>
     <div class="up-col-sm-24">
      <input type="text" id="bm_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">列名：</label>
     <div class="up-col-sm-24">
      <input type="text" id="lm_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">字段类型：</label>
     <div class="up-col-sm-24">
      <input type="text" id="zdlx_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">字段长度：</label>
     <div class="up-col-sm-24">
      <input type="text" id="zdcd_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">是否主键：</label>
     <div class="up-col-sm-24">
      <input type="text" id="sfzj_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">缺省值：</label>
     <div class="up-col-sm-24">
      <input type="text" id="qsz_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">是否为空：</label>
     <div class="up-col-sm-24">
      <input type="text" id="sfwk_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">字段描述：</label>
     <div class="up-col-sm-24">
      <input type="text" id="zdms_id" class="up-form-control" disabled="true" value="" />
     </div>
    </div>
    <div class="up-form-group up-col-sm-12">
     <label class="up-control-label">字典分类：</label>
     <div class="up-col-sm-24">
      <select id="sjzd_id" class="up-form-control"></select>
     </div>
    </div>
   </div>
   <div id="test"></div> 
  </div> -->
  
	<div class="up-container-fluid">
	<form class="up-form-inline u-margin-top20 u-margin-bottom20" action="${basePath}/metacontroller/getMetedataTable"  method="post">
		<div class="up-form-group">
			<label for="">数据源</label> 
			<select name="opt" id="selid"
				class="up-form-control">
				<c:forEach items="${selist}" var="item">		
				     <option value="${item.id}" >${item.fd_mc}</option>
				</c:forEach>
				
			</select>
		</div>
		<div class="up-form-group">
			<button type=button class="up-btn up-btn-sm up-btn-primary up-btn-block"
				id="getID">
				<i class="icon-u-search"></i>查 询
			</button>
		</div>
        <div class="up-form-group">
             <a href="${basePath}/metacontroller/getDatapage?selectId=${selectId}" class="up-btn up-btn-sm up-btn-default u-add"><i class="icon-u-add-round"></i>新增
             </a>
        </div>

     </form>
		<table class="up-table up-table-bordered up-table-hover">
			<thead>
				<tr class="up-active">
					<th>表名</th>
					<th>描述</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="biao_body">

			</tbody>
		</table>
		<div class="up-row">
			<div class="up-col-md-14 up-pull-right">
				<div id="u_pagination" class="up-pull-right"></div>
			</div>
		</div>
		
	</div>
	<!-- 数据字典弹框 -->
			<div id="layer-zd" class="u-padding-top15 up-container-fluid"
				style="display: none">
	        <table class="up-table up-table-bordered up-table-hover" >
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
                    <th>数据字典</th>
                </tr>
               </thead>
             <tbody id="zd_body">

			</tbody>
      </table>
      </div>      
	<script>
    require(['uskin'], function() {
    	var widthWindow=parseInt($(window).width())-30;
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
                url : basePath+"/metacontroller/ajaxMetedataTable",
                params: { opt: $("#selid").val() }, 
                success:function(data){
                	recordList=data.list;
                	seclist=data.selist;
                	var infoStr = '';	
                	for(var i=0;i<recordList.length;i++){
                		infoStr=infoStr+"<tr><td><div style='width:"+(widthWindow*0.3-16)+"px'>"+recordList[i].fd_bm+"</div></td><td><div style='width:"+(widthWindow*0.4-16)+"px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;'>"+
                		recordList[i].fd_ms+
                		"</div></td><td><div style='width:"+(widthWindow*0.3-16)+"px'><button class=\"up-btn up-btn-default up-btn-xs\" data-op=\""+recordList[i].id+"\" id=\"option_"+recordList[i].id+"\"><i class=\"icon-u-edit\"></i>字段</button></div></td></tr>";
                	}
                	$("#biao_body").empty();
                	$("#biao_body").append(infoStr); 
                	layerPage();
                }, 
                totalName:'total',
				pageParams: function(data){
                	console.log(data)
					return {
						pageNum:data.pageIndex+1,
						pageSize:data.pageSize
					};
				}
             }  
          
            
        });
        

         $(document).on("click","#getID",function() {
        	 var params={opt: $("#selid").val()}
        	 $("#u_pagination").pagination('setParams',params);
        	 $("#u_pagination").pagination('remote');
         });   
        
    function layerPage(){   
		$("button[id^='option_']").each(function(){
				var opIndex=$(this).attr("data-op");
				var opId="#option_"+opIndex;
				//console.log(opId);
				$(document).off("click",opId);
				$(document).on("click",opId,function(){
					require(['layer'], function() {
        			    layer.open({
                            type: 1,
                            title: '编辑',
                            content: $("#layer-zd"),
                            btn: ['确定', '取消'],
                            btn2: function(index, layero) {		       
                                layer.close(index)
                            },
                            success: function(layero, index){
                            	$.ajax({
                            		type : "POST",
                            		url : '${basePath}/metazdcontroller/getysjzdData',
                            		dataType : "json",
                            		data: {data:opIndex},
                            		success: function (data) {
                            			var li=data.list;
                            			var zdlist=data.relist;
                            			var infoStr = '';
                            			var opStr='';
                            			for (var i = 0; i < li.length; i++) {
                            				var sjzd=li[i].fd_sjzd;
                            				if(sjzd==null || sjzd==""){
                            					sjzd="";
                                    			opStr="<option selected=\"selected\"  value=''>请选择字典</option>";
                            				}
                                			for(var j=0;j<zdlist.length;j++){
                                				if(zdlist[j].FD_FLBM==sjzd){
                                					opStr=opStr+"<option selected=\"selected\" value="+zdlist[j].FD_FLBM+">"+zdlist[j].FD_FLMC+"</option>"							                                					
                                				}else{
                                					opStr=opStr+"<option  value="+zdlist[j].FD_FLBM+">"+zdlist[j].FD_FLMC+"</option>"							                                					
                                				}
                                			}
                            				infoStr=infoStr+"<tr><td>"+li[i].fd_zdmc+"</td><td>"
                            				+li[i].fd_zdlx+"</td><td>"+li[i].fd_zdcd+"</td><td>"+(li[i].fd_zdjd==null?0:li[i].fd_zdjd)+"</td><td>"
                            				+li[i].fd_sfzj+"</td><td>"+li[i].fd_qsz+"</td><td>"
                            				+li[i].fd_sfkwk+"</td><td>"+li[i].fd_zdbz+"</td><td><select  class=\"selectLi\" data-op="+li[i].id+"  id=\"zd_select\" class=\"up-form-control\">"
                            				+opStr+"</select></td></tr>";									
        								}
                                    	$("#zd_body").empty();
                                    	$("#zd_body").append(infoStr); 
                            		}
                            	})
                            },
                            yes: function(index, layero) {
                            	var selObj=new Array();
                            	$('#layer-zd').find("select.selectLi").each(function(){
                            		var yid=$(this).attr("data-op");
                            		var va=$(this).find('option:selected').val();
                            		selObj.push(va+','+yid);
                            	})
                            	$.post('${basePath}/metazdcontroller/saveysjzdData', {selectval:JSON.stringify(selObj)},function(data){
                            		  if(data=="ok"){
                            			 // layer.close(index);
                            		layer.alert("操作成功!",{closeBtn:0},function(){layer.closeAll();});
                            			  // layer.alert("保存成功！"); 
                            		  }else{
                            			  layer.alert("保存失败！"); 
                            		  }												                                		  
                            	})                            	
/*                             	var str=$('#zd_select option:selected').text();
                            	var selectval;
                            	if(str=="请选择字典"){
                            		selectval="";
                            	}else{
                            		selectval=str;
                            	}
                            	console.log(selectval);
                            	$.post('${basePath}/metazdcontroller/saveysjzdData', {selectval:selectval,zdid:idd},function(data){
                            		  if(data=="ok"){
                            			 // layer.close(index);
                            		layer.alert("操作成功!",{closeBtn:0},function(){layer.close(index);});
                            			  // layer.alert("保存成功！"); 
                            		  }else{
                            			  layer.alert("保存失败！"); 
                            		  }												                                		  
                            	}); */
                            },
        			      area:[ '850px','400px']
        			     });                     
					})
				});
			});
        
    }
    
    })
    

  
    
   	
    
    </script>
</body>
</html>