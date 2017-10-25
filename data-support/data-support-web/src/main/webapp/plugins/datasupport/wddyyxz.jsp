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

<style type="text/css">
.ui-pg-div{
	border:1px solid #ccc;
	padding:3px 20px;
	border-radius:3px;
}
.ui-jqgrid .ui-pg-button span.icon-u-new-window{
	display:none;
}
.ui-jqgrid-pager #pager_left .ui-pg-table .ui-pg-button:hover,
.ui-jqgrid-toppager #pager_left .ui-pg-table .ui-pg-button:hover {
  background: none; }
.ui-jqgrid-pager #pager_left .ui-pg-div:hover{
	background-color:#ddd;
}
.u_form table td{
	padding-bottom:10px;
}
.up-form-control{
	display:inline;
}
</style>
</head>

<body class="u_scroll">
    <div class="up-container-fluid">
        <form class="u_form up-clearfix" style="max-width: 500px;">
            <h5><strong>新建/修改信息</strong></h5>
            <div class="up-form-group up-col-sm-24">
               <label class="up-control-label">维度名称 </label>
                <div class="up-col-sm-24">
                    <input type="text" name="" id="xxmc"  class="up-form-control" placeholder="">
                </div>
            </div>
             <div class="up-form-group up-col-sm-24">
               <label class="up-control-label">表                  名 </label>
                <div class="up-col-sm-24">
                    <input type="text" name="" id="xxbm"  class="up-form-control" placeholder="" > 
                </div>
            </div>
            <div class="up-form-group up-col-sm-24">
               <label class="up-control-label">维度描述 </label>
                <div class="up-col-sm-24">
                    <textarea class="up-form-control" name="" id="xxms"></textarea>
                </div>
            </div>                      
        </form>
         <div class="u_tab_body" id="table_grid">
         <table id="xxgrid" class="jqgrid"></table>
         <div id="pager" style="height:40px;"></div> 


 			<form class="u_form" id="layer-editor" style="display: none;padding:15px;">
				<table width="100%">
					<tbody>
						<tr >
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">列名</label></td>
							<td width="80%"><input type="text" name="FD_ZDMC" id="FD_ZDMC" class="up-form-control" style="width:60%;"/></td>
						</tr>
						<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">字段类型</label></td>
							<td width="80%"><select name="FD_ZDLX" id="FD_ZDLX" class="up-form-control" style="width:60%;">
									<option value="请选择类型">请选择类型</option>
									<option value="VARCHAR">VARCHAR</option>
									<option value="DATE">DATE</option>
									<option value="TIMESTAMP">TIMESTAMP</option>
									<option value="NUMBER">NUMBER</option>
									<option value="NUMERIC">NUMERIC</option>
							</select></td>
						</tr>
						<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">字段长度</label></td>
							<td width="80%"><input type="text" id="FD_ZDCD" name="FD_ZDCD" class="up-form-control" style="width:60%;"/></td>
						</tr>
						<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">字段精度</label></td>
							<td width="80%"><input type="text" id="FD_ZDJD" name="FD_ZDJD" class="up-form-control" style="width:60%;"/></td>
						</tr>
						<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">是否主键</label></td>
							<td width="80%" id="checkId">
								<label class="u_check">
			                        <input type="radio"  name="FD_SFZJ"  value="Y"  style="width:30%;"/>
			                        <i class="up-text-primary "></i>Y
			                    </label>
			                    <label class="u_check">
			                        <input type="radio"  name="FD_SFZJ"  value="N"  style="width:30%;" checked="true"/>
			                        <i class="up-text-primary icon-u-ok-circle"></i>N
			                    </label>
							</td>
						</tr>
												<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">缺省值</label></td>
							<td width="80%"><input type="text" id="FD_QSZ" name="FD_QSZ" class="up-form-control" style="width:60%;"/></td>
						</tr>
						<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">是否可为空</label></td>
							<td width="80%">
								<label class="u_check">
			                        <input type="radio"  name="FD_SFKWK"  value="Y"  style="width:30%;" checked="true"/>
			                        <i class="up-text-primary icon-u-ok-circle"></i>Y
			                    </label>
			                    <label class="u_check">
			                        <input type="radio"  id="nid"  name="FD_SFKWK"  value="N"  style="width:30%;"/>
			                        <i class="up-text-primary"></i>N
			                    </label>
							</td>
						</tr>
												<tr>
							<td align="right" width="20%"><label class="up-control-label" style="padding-right:15px;">字段备注</label></td>
							<td width="80%"><input type="text" id="FD_ZDBZ" name="FD_ZDBZ" class="up-form-control" style="width:60%;"/></td>
						</tr>
					</tbody>
				</table>
			</form>

        
        <div class="u_form u-margin-top20" style="max-width: 250px">
            <div class="up-form-group" style="padding-left:0">
                <div class="up-col-sm-12 ">
                    <button class="up-btn up-btn-primary" id="okbtn">保  存</button>
                </div>
                <div class="up-col-sm-12">
                    <button class="up-btn" id="nobtn">取 消</button>
                </div>
            </div>
        </div>
    </div>
    <script>
    require(['uskin'], function() {   	
        var editlist= new Array(); 
        var addlist= new Array(); 
        var fieldname=new Array();
        var wid=parseInt($(".u_tab_body").width());
        var randnum=0;
      require(["jqGrid"], function(grid) {
        $(document).ready(function() {	       	                  	
        	$("#xxgrid").jqGrid({
                url: "${basePath}/wddyController/getwddymetadata?xxid="+'${megid}',
                datatype: "json",
                mtype: "GET",
                colModel: [{
                    label: '列名',
                    name: 'FD_ZDMC',
                    width: (wid*0.2-1),
                    editable: true
                }, {
                    label: '字段类型',
                    name: 'FD_ZDLX',
                    width: wid*0.2,
                    editable: true
                },{
                    label: '字段长度',
                    name: 'FD_ZDCD',
                    width: wid*0.1,
                    editable: true
                },{
                    label: '字段精度',
                    name: 'FD_ZDJD',
                    width: wid*0.1,
                    editable: true
                },{
                    label: '是否主键',
                    name: 'FD_SFZJ',
                    width: wid*0.1,
                    editable: true
                },{
                    label: '缺省值',
                    name: 'FD_QSZ',
                    width: wid*0.1,
                    editable: true
                },{
                    label: '是否可为空',
                    name: 'FD_SFKWK',
                    width: wid*0.1,
                    editable: true
                },{
                    label: '字段描述',
                    name: 'FD_ZDBZ',
                    width: wid*0.1,
                    editable: true
                }],
                page: 1,
                scrollOffset:3,
                shrinkToFit:false,
                autowidth: true,
                autoheight: true,
                viewrecords: true,
                pager: "#pager",
            });

            $('#xxgrid').navGrid("#pager", {
                search: false, // show search button on the toolbar
                add: false,
                edit: false,
                del: false,
                refresh: false
            });
          

            $("#xxgrid").jqGrid('navButtonAdd', '#pager', {
                caption : "添加",
                onClickButton : function() {
                   	$('#FD_ZDMC').removeAttr("disabled"); 
                	$('#FD_ZDLX').removeAttr("disabled"); 
                  	 $("#FD_ZDCD").removeAttr("disabled");
                   	 $("#FD_ZDJD").removeAttr("disabled");
                	//$("#FD_ZDMC").attr("value","");
                	$("#FD_ZDMC").val("");
                	$("#FD_ZDCD").val("");
                	$("#FD_ZDJD").val("");
                	$("#FD_QSZ").val("");
                	$("#FD_ZDBZ").val("");
/*                 	$(":radio[name='FD_SFZJ'][value='N']").prop("checked", "checked");
                	$(":radio[name='FD_SFKWK'][value='Y']").prop("checked", "checked"); */
                	$("#FD_ZDLX").val("请选择类型"); 
                	$("tr").parent().find("span").remove();
                 	require(['layer'], function() {
		                layer.open({
		                    type: 1,
		                    title: '增加',
		                    content: $("#layer-editor"),
		                    btn: ['确定', '取消'],
                            yes: function(index, layero) {
                             	 var obj = document.getElementById("layer-editor");
                            	 var havespan = obj.getElementsByTagName("span");
                             	if(havespan.length>0){
                         		   return;
                         	}
                             	if($("#FD_ZDMC").val()=="" ){
                        
                             		$("#FD_ZDMC").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">请输入正确的列名</span>");
                             		return;
                             	}
                             	else if($("#FD_ZDLX").val()=="请选择类型" ){
                             		$("#FD_ZDLX").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">请选择类型</span>");
                             		return;
                             	}
                             	else if($("#FD_ZDLX").val()=="VARCHAR"  && $("#FD_ZDCD").val()=="" ){

                             		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">长度请输入1-4000</span>");
                             		return;
                             	}
                             	else if(($("#FD_ZDLX").val()=="NUMERIC" ||  $("#FD_ZDLX").val()=="NUMBER" )   && $("#FD_ZDCD").val()=="" ){

                             		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">长度请输入1-38</span>");
                             		return;
                             	}
                             	else if($("#FD_ZDLX").val()=="NUMERIC" && $("#FD_ZDJD").val()=="" ){
                             		$("#FD_ZDJD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">精度小于等于长度且大于等于0</span>");
                             		return;
                             	}
                             	
                                if($('input:radio[name="FD_SFZJ"]:checked').val()=="Y" &&  $('input:radio[name="FD_SFKWK"]:checked').val()=="Y")
                                {
                               	 layer.alert("主键不能为空!");   
                               	 return;
                                } 
                                var addMC=$("#FD_ZDMC").val(); 
                                for (var i=0;i<fieldname.length;i++)
                            	{
                              	  if(addMC==fieldname[i]){
                            		  layer.alert("字段名重复，请重新填写!");
                            		  return;
                            	  }
                            	} 
                                ++randnum;
                                var adddata={FD_ZDSX:randnum,FD_ZDMC:$("#FD_ZDMC").val(),FD_ZDLX:$("#FD_ZDLX").val(),FD_ZDCD:$("#FD_ZDCD").val(),FD_ZDJD:$("#FD_ZDJD").val(),FD_SFZJ:$('input:radio[name="FD_SFZJ"]:checked').val(),FD_QSZ:$("#FD_QSZ").val(),FD_SFKWK:$('input:radio[name="FD_SFKWK"]:checked').val(),FD_ZDBZ:$("#FD_ZDBZ").val()};                           
                                $("#xxgrid").addRowData(randnum,adddata);
                                fieldname.push(addMC);
                                addlist.push(adddata);
                                layer.close(index);
		                    	$(":radio[name='FD_SFZJ'][value='N']").click();
		                    	$(":radio[name='FD_SFKWK'][value='Y']").click();
                            },
		                    btn2: function(index, layero) {
		                    	$("tr").parent().find("span").remove();
		                        layer.close(index);
		                    	$(":radio[name='FD_SFZJ'][value='N']").click();
		                    	$(":radio[name='FD_SFKWK'][value='Y']").click();
		                    },
		                    area: '580px'
		                });
		            })
                }
              });
            
            $("#xxgrid").jqGrid('navButtonAdd', '#pager', {
                caption : "编辑",
                onClickButton : function() {
                    var gsr = jQuery("#xxgrid").jqGrid('getGridParam', 'selrow');
                	$("#FD_ZDMC").attr("disabled",true);
                	$("#FD_ZDLX").attr("disabled",true);
        		    if (gsr) {
        		        jQuery("#xxgrid").jqGrid('GridToForm', gsr, "#layer-editor");
        		        var rowData = $("#xxgrid").jqGrid('getRowData',gsr);
        		        if(rowData.FD_SFZJ=='Y'){
        		        	$(":radio[name='FD_SFZJ'][value='Y']").click();
        		        }else{
        		        	$(":radio[name='FD_SFZJ'][value='N']").click();
        		        }
        		        
        		        if(rowData.FD_SFKWK=='Y'){
        		        	$(":radio[name='FD_SFKWK'][value='Y']").click();
        		        }else{
        		        	$(":radio[name='FD_SFKWK'][value='N']").click();
        		        }
                     	require(['layer'], function() {
    		                layer.open({
    		                    type: 1,
    		                    title: '修改',
    		                    content: $("#layer-editor"),
    		                    btn: ['确定', '取消'],
    		        	  		success: function(layero, index){
    		        	  			$.each(addlist,function(name,value) {
    		        	  				if(name=="FD_ZDLX"){
                                           if(value=="VARCHAR" || value=="NUMBER"){
                                        	   $("#FD_ZDCD").removeAttr("disabled");
                                        	   $("#FD_ZDJD").attr("disabled","disabled");                               
                                            }else if(value=="NUMERIC"){
                                            	$("#FD_ZDCD").attr("disabled","disabled");  
                                            	$("#FD_ZDJD").attr("disabled","disabled");  
                                            }else{
                                            	 $("#FD_ZDCD").removeAttr("disabled");
                                            	 $("#FD_ZDJD").removeAttr("disabled");
                                            }
    		        	  				}
    		        	  			 })
    		        			},
                                yes: function(index, layero) {
                                    var gsr = $("#xxgrid").jqGrid('getGridParam', 'selrow');
                                    //layer.alert(gsr);
                                    if (gsr) {
                                      $("#xxgrid").jqGrid('FormToGrid',gsr,"#layer-editor");
                                    }
                                 	 var obj = document.getElementById("layer-editor");
                                	 var havespan = obj.getElementsByTagName("span");
                                 	if(havespan.length>0){
                             		   return;
                             	}
                                    if($('input:radio[name="FD_SFZJ"]:checked').val()=="Y" &&  $('input:radio[name="FD_SFKWK"]:checked').val()=="Y")
                                    {
                                   	 layer.alert("主键不能为空!");   
                                   	 return;
                                    }   	
                                var editdata={FD_ZDMC:$("#FD_ZDMC").val(),FD_ZDLX:$("#FD_ZDLX").val(),FD_ZDCD:$("#FD_ZDCD").val(),FD_ZDJD:$("#FD_ZDJD").val(),FD_SFZJ:$('input:radio[name="FD_SFZJ"]:checked').val(),FD_QSZ:$("#FD_QSZ").val(),FD_SFKWK:$('input:radio[name="FD_SFKWK"]:checked').val(),FD_ZDBZ:$("#FD_ZDBZ").val()}; 
                                var editmc=$("#FD_ZDMC").val();
                                for (var i=0;i<editlist.length;i++)
                               	{    
                                  if(editmc==editlist[i].FD_ZDMC){
                                 		 editlist[i]=editdata
                               	  }else{
                               		 editlist.push(editdata) 
                               	  }
                               	} 
                                if(editlist.length==0){
                                    editlist.push(editdata);
                                 }
                                layer.close(index)
                                },
    		                    btn2: function(index, layero) {
    		                    	$("tr").parent().find("span").remove();
    		                        layer.close(index)
    		                    },
    		                    area: '580px'
    		                });
    		            })
        		      } else {
        		        layer.alert("请选择一行!");
        		      }

                  }
              });


        });
        


   });
      $(document).on("click","#checkId .u_check",function() {
    	  //this.blur(); 
    	  //this.focus(); 
     	  var str=$('input:radio[name="FD_SFZJ"]:checked').val();
      	 if(str=="Y"){
      		$(":radio[name='FD_SFKWK'][value='N']").click()
      	 }else{
      		$(":radio[name='FD_SFKWK'][value='Y']").click();
      	 }
    	
        });   

      $(document).on("blur","#FD_ZDMC",function() {
      	var str=$("#FD_ZDMC").val();
      	var regexp = /^[a-zA-Z][\w]{1,29}$/;
      	//console.log(typeof regexp.test(str));
      	$("#FD_ZDMC").parent().find("span").remove();
      	if(!regexp.test(str)){
      		$("#FD_ZDMC").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">请输入正确的列名</span>");
      	}
      });   
      
      $(document).on("blur","#FD_ZDCD",function() {
      	var str=$("#FD_ZDCD").val();
      	$("#FD_ZDLX").parent().find("span").remove();
      	$("#FD_ZDCD").parent().find("span").remove();
      	if($("#FD_ZDLX").val()=="VARCHAR"){     		  
      		  var regexp=/^([1-9]|[0-9]{2,3}|[1-3]\d\d\d|4000)$/;
            	if(!regexp.test(str)){
          		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">长度请输入1-4000</span>");
          	}
      	}else if($("#FD_ZDLX").val()=="NUMBER" || $("#FD_ZDLX").val()=="NUMERIC"){     		  
      		var regexp=/^([1-9]|[1-2][0-9]|3[0-8])$/;
          	if(!regexp.test(str)){
        		$("#FD_ZDCD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">长度请输入1-38</span>");
        	}
    	}

      });   
      
      $(document).on("blur","#FD_ZDJD",function() {
      	var str=$("#FD_ZDJD").val();
      	var strint=parseInt(str);
      	var scd=$("#FD_ZDCD").val();
      	var scdint=parseInt(scd);
      	$("#FD_ZDJD").parent().find("span").remove();
      	var regexp=/^([0-9]|[1-2][0-9]|3[0-8])$/;
      	if(!regexp.test(str) || strint>scdint){
      		$("#FD_ZDJD").parent().append("<span style=\"font-size:12px;color:red;margin-left:8px;\">精度小于等于长度且大于等于0</span>");
      	}
      });     
        
        $(document).on("change","#FD_ZDLX",function() {
        	   $("#FD_ZDJD").parent().find("span").remove();
        	   $("#FD_ZDCD").parent().find("span").remove();
            if($("#FD_ZDLX").val()=="VARCHAR"){
         	   $("#FD_ZDJD").val("");
              	 $("#FD_ZDCD").removeAttr("disabled");
              	 $("#FD_ZDJD").attr("disabled","disabled");
               }else if($("#FD_ZDLX").val()=="DATE"||$("#FD_ZDLX").val()=="TIMESTAMP"){
            	   $("#FD_ZDJD").val("");
            	   $("#FD_ZDCD").val("");
              	 $("#FD_ZDCD").attr("disabled","disabled");
              	 $("#FD_ZDJD").attr("disabled","disabled");
              	 
               }else if($("#FD_ZDLX").val()=="NUMBER"){
            	   $("#FD_ZDCD").val("");
                $("#FD_ZDCD").removeAttr("disabled");
              	 $("#FD_ZDJD").attr("disabled","disabled");
               }else if($("#FD_ZDLX").val()=="NUMERIC"){
              	 $("#FD_ZDCD").removeAttr("disabled");
              	 $("#FD_ZDJD").removeAttr("disabled");
               }
            if($("#FD_ZDLX").val()!="请选择类型"){
            	$("#FD_ZDLX").parent().find("span").remove();
            }
    }); 
   require(['layer'], function () {       
        $("#okbtn").click(function() {
        	if($("#xxmc").val()==""|| $("#xxbm").val()=="" ){
        		layer.alert("请输入表名,名称!");   
        		return;
        	}
        	if(addlist.length==0){
        		layer.alert("请填写字段信息!");
        		return;
        	}
            for (var i=0;i<addlist.length;i++)
           	{    
            	var zdmc=addlist[i].FD_ZDMC
                 for(var j=0;j<editlist.length;j++){
                	 if(zdmc==editlist[j].FD_ZDMC){
                		 editlist[j].FD_ZDSX=addlist[i].FD_ZDSX;
                		 addlist[i]=editlist[j];
                	 }
                }
           	}
        	var param = {xxmc:$("#xxmc").val(),xxms:$("#xxms").val(),xxid:'${megid}',xxbm:$("#xxbm").val(),addlist:JSON.stringify(addlist)};
            	$.ajax({
            		type : "POST",
            		url : '${basePath}/wddyController/addwddyMegdata',
            		dataType : "text",
            		data: param,
            		success: function (data) {
                        if(data=="ok"){
                            layer.alert("操作成功!",{closeBtn:0},function(){
                            	window.location.href="${basePath}/plugins/datasupport/wddy.jsp"
        						});
                         }else if(data=="repeat"){
                        	 layer.alert("表名已存在!");
	            		 }else if(data=="sessionTimeout"){
								layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
									layer.closeAll();
								});
                         }else{
                        	 layer.alert("操作失败!");
                         }
            	    }
            	});
        	
        }) 
   })    
   
        $("#nobtn").click(function() {
        	window.location.href="${basePath}/plugins/datasupport/wddy.jsp";
        })  
        
    }) 
    </script>
</body>
</html>


