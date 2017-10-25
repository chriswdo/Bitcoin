<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no" name="viewport" id="viewport" />
    <meta name="renderer" content="webkit">
    <title>任务调度管理</title>
    <meta name="description" content="">
    <meta name="keywords" content=""> 
    <%@include file="/common/common.jsp"%>
    <style type="text/css">
    	
    </style>
</head>
<body class="u_scroll">
	<div class="up-container-fluid">
        <form class="up-form-inline u-margin-top20 u-margin-bottom20 up-clearfix">
                <div class="up-form-group">
                    <label for="">规则名称 </label>
                    <input type="text" class="up-form-control" id="fd_gzmc_s"  placeholder="">
                </div>
                <div class="up-form-group">
                    <label for="">数据库名称 </label>
                    <input type="text" class="up-form-control" id="db_name_s" placeholder="">
                </div>
                <div class="up-form-group">
                    <button type="button" id="search" class="up-btn up-btn-primary  up-btn-sm up-btn-block u-search"><i class="icon-u-search"></i>查询</button>
                </div>
                <div class="up-form-group">
                    <button type="button"  id="add" class="up-btn up-btn-sm up-btn-default u-add"> <i class="icon-u-add-round"></i>新增</button>
                </div>
        </form>
        <table class="up-table up-table-bordered up-table-hover">
            <thead>
                <tr class="up-active">
                    <th>调度编号</th>
                    <th>名称</th>
                    <th>输入数据源</th>
                    <th>输出数据源</th>
                    <th>调度描述</th>
                    <th>更新时间</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="body">
            </tbody>
        </table>
        <div class="up-row">
            <div class="up-col-md-24">
                <div id="u_pagination" class="up-pull-right">
                </div>
            </div>
        </div>
    </div>
    
    <%@ include file="createSchedule.jsp" %>
 	<%@ include file="chooseQzrw.jsp" %>
    
    <script>
    //标记任务类型
    var fd_lx = ${fd_lx};
    
    //规则id  控制在界面跳转中选择的规则id，  
    var  globalObject = {
    		fd_gz_id:"",
    		fd_rw_id:"",
    		cleanState:function(){
    			this.fd_gz_id="";
    			this.fd_rw_id="";
    		}
    }
    
    //定义选择的前置任务
   	var ids_struct = {
       	 size : 0 ,
       	 items  :  new Array(0,0,0,0,0,0,0,0,0,0),
       	 size_selected : 0,
       	 items_selected : new Array(0,0,0,0,0,0,0,0,0,0) ,
    	 addId : function(item){
    		 for(var i=0;i<10;i++){
    			 if(this.items[i]==0){
    				 this.items[i]=item;
    				 break;
    			 }
    		 }
    		 this.size++;
    	 },
    	 removeId: function(item){
    		 for(var i=0;i<10;i++){
    			 if(this.items[i].id==item.id){
    				 this.items[i]=0;
    				 break;
    			 }
    		 }
    		 this.size--;
    	 },
    	 isFulled:function(){
    		 return this.size+this.size_selected>9;
    	 },
    	 addSelected:function(item){
    		 for(var i=0;i<10;i++){
    			 if(this.items_selected[i]==0){
    				 this.items_selected[i]=item;
    				 break;
    			 }
    		 }
    		 this.size_selected++;
    	 },
    	 removeSelected:function(id){
    		 for(var i=0;i<10;i++){
    			 if(this.items_selected[i].id==id){
    				 this.items_selected[i]=0;
    				 break;
    			 }
    		 }
    		 this.size_selected--;
    	 },
    	 getIdsStr:function(){
    		 var idsStr = "";
       		 for(var i=0;i<10;i++){
    			 if(this.items_selected[i] != 0 ){
    				 if(idsStr==""){
    					 idsStr=this.items_selected[i].id;
    				 }else{
    					 idsStr = idsStr+"#"+this.items_selected[i].id;
    				 }
    			 }
    		 }
       		 return idsStr
    	 },
    	 addToSelected:function(){
    		 for(var i=0;i<10;i++){
    			 if(this.items[i] != 0){
    				 this.addSelected(this.items[i]);
    				 this.removeId(this.items[i]);
    			 }
    		 }
    	 },
    	 getSelectedInfo:function(){
    		 var idsArr = new Array();
    		 var j = 0;
       		 for(var i=0;i<10;i++){
    			 if(this.items_selected[i] != 0 ){
    				 idsArr[j++] = this.items_selected[i];
    			 }
    		 }
       		 return idsArr;
    	 },
    	 cleanItems:function(){
    		 this.items =  new Array(0,0,0,0,0,0,0,0,0,0);
    		 this.size = 0;
    	 },
    	 cleanItemsSelected:function(){
    		 this.items_selected =  new Array(0,0,0,0,0,0,0,0,0,0);
    		 this.size_selected = 0;
    	 }
    	 
    }
    
    function removeIdFun(id){
   		ids_struct.removeSelected(id);
   		generateCollectionSelect(ids_struct.getSelectedInfo());
    }
    
    
    function  getParams(){
		var fd_gzmc = $.trim($("#fd_gzmc_s").val());
		var db_name = $.trim($("#db_name_s").val());
		params = { "fd_gzmc": fd_gzmc,"db_name":db_name,"fd_lx":fd_lx};
		return params;
    }
    function startFun(id){
	   	 require(['layer'], function() {
           	 $.ajax({
            		type : "POST",
            		url : basePath + "/taskRwb/changeTaskStatus",
            		dataType : "text",
            		data: {"id":id,"status":"Y"} ,
            		success: function (data) {
            			if(data=="success"){
							var params = getParams();
				      		$("#u_pagination").pagination('setParams', params);
			        		$("#u_pagination").pagination('remote');
            			}else {
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
           		url : basePath + "/taskRwb/changeTaskStatus",
           		dataType : "text",
           		data: {"id":id,"status":"N"} ,
           		success: function (data) {
           			if(data=="success"){
						var params = getParams();
			      		$("#u_pagination").pagination('setParams', params);
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
    
	Date.prototype.format = function(format) {
	       var date = {
	              "M+": this.getMonth() + 1,
	              "d+": this.getDate(),
	              "h+": this.getHours(),
	              "m+": this.getMinutes(),
	              "s+": this.getSeconds(),
	              "q+": Math.floor((this.getMonth() + 3) / 3),
	              "S+": this.getMilliseconds()
	       };
	       if (/(y+)/i.test(format)) {
	              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	       }
	       for (var k in date) {
	              if (new RegExp("(" + k + ")").test(format)) {
	                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
	                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	              }
	       }
	       return format;
	}
    
    function f_check_str(str){
    	if(str=="" || str==undefined || str==null ){
    		return false;
    	}
    	return true;
    }
    
    //文本框失去焦点后
    $('#form-create :input').blur(function(){
    	 var $parent = $(this).parent();
		 //验证调度时间格式
         if($(this).is('#fd_ddpl') ){
           	 $.ajax({
           		type : "POST",
           		url : basePath + "/taskRwb/checkfd_ddsj",
           		dataType : "text",
           		data: {"fd_ddpl":this.value} ,
           		success: function (data) {
           			if(data=="false"){
                        var errorMsg = 'cron表达式错误!';
                        $parent.addClass('up-has-warning');
                        $("#fd_ddpl").after('<span class="u_form_remind">*'+errorMsg+'</span>'+'<i class="u_form_icon icon-u-warning"></i>');
           			}
           	    },
        		error: function(e) {
        			layer.alert("服务器异常!")
        		} 
             }); 
      	 }
         if($(this).is('#fd_sjl') ){
             if( !f_check_str(this.value) || isNaN(Number(this.value)) || parseInt(this.value)>60*24 ||parseInt(this.value)<=0){
                 $parent.addClass('up-has-warning')
             }
      	 }
         if($(this).is('#fd_rwms') ){
             if( !f_check_str(this.value) ){
                 var errorMsg = '描述不能为空!';
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
    
    
    function cancelFun(){
	     $("#fd_gzmc").val("");
	     $("#fd_ddpl").val("");
		 $("#fd_scqdsj").val("");
		 $("#fd_sjsjc").val("");
		 $("#fd_sjl").val("");
		 $("#fd_rwms").val("");
		 $("#fd_sjldw").val("");
		 ids_struct.cleanItemsSelected();
		 //清楚全局变量
		 globalObject.cleanState();
    }
    
		 function layerOpen(){
				//初始化表单
		        require(['layer'], function() {
		        	
		            function checkValue(fd_gzmc,fd_ddpl,fd_scqdsj,fd_sjsjc,fd_sjl,fd_rwms,fd_sjldw,fd_qzrw_id){
		            	if(!f_check_str(fd_gzmc)){
		            		layer.alert("请选择规则!") ;
		            		return false;
		            	};
		            	if(!f_check_str(fd_rwms)){
		        			layer.alert("任务描述不能为空!") ;
		        			return false;
		            	}
		    			 if(fd_lx=="2"){
		    	        	if(!f_check_str(fd_sjldw)){
		    	        		layer.alert("请选择挖掘周期!") ;
		    	        		return false;
		    	        	}
			            	if(!f_check_str(fd_scqdsj)){
			        			layer.alert("首次启动时间不能为空!") ;
			        			return false;
			            	}
		    			 }else{
			    			 if(fd_lx=="1"){
				    	        	if(!f_check_str(fd_qzrw_id)){
				    	        		layer.alert("请选择前置任务!") ;
				    	        		return false;
				    	        	}
			    			 }
		    	        	//cron表达式在后台验证  此处只验证不能为空
		    	        	if(!f_check_str(fd_ddpl)){
		    	        		layer.alert("调度频率不能为空!") ;
		    	        		return false;
		    	        	}
			            	if(!f_check_str(fd_scqdsj)){
			        			layer.alert("首次启动时间不能为空!") ;
			        			return false;
			            	}
			            	if(!f_check_str(fd_sjsjc)){
			        			layer.alert("起始数据时间戳不能为空!") ;
			        			return false;
			            	}
			            	if((!f_check_str(fd_sjl) || isNaN(Number(fd_sjl))) || parseInt(fd_sjl)<=0){
			        			layer.alert("请正确填写数据量!") ;
			        			return false;
			            	}
		    	        	if(parseInt(fd_sjl)>60*24){
		    	        		layer.alert("数据量不能大于一天");
		    	        		return false;
		    	        	}
		    			 }
		
		            	return  true;
		            }
		        	
		           		$('#fd_rwzt_n').click();
		        	
		   	            layer.open({
		   	                type: 1,
		   	                title: '新建任务',
		   	                content: $("#layer-create"),
		   	                btn: ['保存', '取消'],
		   	                btn1: function(index, layero) {
			   	    		     var fd_gzmc = $.trim($("#fd_gzmc").val());
				   	 			 var fd_rwzt =$.trim( $("#fd_rwzt input:radio:checked").val());
				   	 			 var fd_rwms = $.trim($("#fd_rwms").val());
				   	 			 var fd_scqdsj = $.trim($("#fd_scqdsj").val());
				   	 			 var params 
				   	 			 if(fd_lx=="2"){
				   	 				 var fd_sjldw = $.trim($("#fd_sjldw").val());
				   	 				 var fd_zntz =$.trim( $("#fd_zntz input:radio:checked").val());
				   	 				 params = {
				   	 					 		"fd_gz_id":globalObject.fd_gz_id,
				   	 					 		"fd_gzmc":fd_gzmc,
				   	 					 		"fd_sjldw":fd_sjldw,
				   	 					 		"fd_scqdsj_n":fd_scqdsj,
				   	 					 		"fd_rwzt":fd_rwzt,
				   	 					 		"fd_zntz":fd_zntz,
				   	 					 		"fd_rwms":fd_rwms,
				   	 					 		"fd_lx":fd_lx,
				   	 					 		"id":globalObject.fd_rw_id
				   	 					 };
				   	 				 if(!checkValue(fd_gzmc,fd_ddpl,fd_scqdsj,fd_sjsjc,fd_sjl,fd_rwms,fd_sjldw,fd_qzrw_id)){
				   	 					 return false;
				   	 				 };
				   	 			 }
				   	 			 if(fd_lx=="1"){
				   	 				 var fd_qzrw_id = ids_struct.getIdsStr();
				   	 				 var fd_sjl  = $.trim($("#fd_sjl").val());
				   	 				 var fd_sjsjc =$.trim( $("#fd_sjsjc").val());
				   	 				 var fd_ddpl = $.trim($("#fd_ddpl").val());
				   	 				 params = {
				   	 					 		"fd_gz_id":globalObject.fd_gz_id,
				   	 					 		"fd_gzmc":fd_gzmc,
				   	 					 		"fd_qzrw_id":fd_qzrw_id,
				   	 					 		"fd_ddpl":fd_ddpl,
				   	 					 		"fd_scqdsj_n":fd_scqdsj,
				   	 					 		"fd_sjsjc_n":fd_sjsjc,
				   	 					 		"fd_sjl":fd_sjl,
				   	 					 		"fd_rwzt":fd_rwzt,
				   	 					 		"fd_rwms":fd_rwms,
				   	 					 		"fd_lx":fd_lx,
				   	 					 		"id":globalObject.fd_rw_id
				   	 					 };
				   	 				 if(!checkValue(fd_gzmc,fd_ddpl,fd_scqdsj,fd_sjsjc,fd_sjl,fd_rwms,fd_sjldw,fd_qzrw_id)){
				   	 					 return false;
				   	 				 };
				   	 			 }
				   	 			 if(fd_lx=="0"){
				   	 				 var fd_ddpl = $.trim($("#fd_ddpl").val());
				   	 				 var fd_sjl  = $.trim($("#fd_sjl").val());
				   	 				 var fd_sjsjc =$.trim( $("#fd_sjsjc").val());
				   	 				 params = {
				   	 					 		"fd_gz_id":globalObject.fd_gz_id,
				   	 					 		"fd_gzmc":fd_gzmc,
				   	 					 		"fd_ddpl":fd_ddpl,
				   	 					 		"fd_scqdsj_n":fd_scqdsj,
				   	 					 		"fd_sjsjc_n":fd_sjsjc,
				   	 					 		"fd_sjl":fd_sjl,
				   	 					 		"fd_rwzt":fd_rwzt,
				   	 					 		"fd_rwms":fd_rwms,
				   	 					 		"fd_lx":fd_lx,
				   	 					 		"id":globalObject.fd_rw_id
				   	 					 };
				   	 				 if(!checkValue(fd_gzmc,fd_ddpl,fd_scqdsj,fd_sjsjc,fd_sjl,fd_rwms,fd_sjldw,fd_qzrw_id)){
				   	 					 return false;
				   	 				 };
				   	 			 }
				
				   	 	       	 $.ajax({
				   	 	       		 type : "POST",
				   	 	       		 url : basePath + "/taskRwb/addRwbRecord",
				   	 	       		 dataType : "text",
				   	 	       		 data: params,
				   	 	       		 success: function (data) {
				   	 					if(data=="success"){
				   	 						layer.alert("保存成功!");
				   	 						layer.close(index);
				   	 						cancelFun();
				   	 						//刷新rwb
				   	 						params_p = getParams();
				   	 						$("#u_pagination").pagination('setParams', params_p);
				   	 		       			$("#u_pagination").pagination('remote');
				   	 		       			//刷新gz
						   	 		   		params = { "fd_gzmc": "","tb_name":"","fd_lx":fd_lx}
						   	 		       	require(['layer'], function() {
						   	 		       		$("#u_pagination_create").pagination('setParams', params);
						   	 		       		$("#u_pagination_create").pagination('remote');
						   	 		       	})
				   	 					}else if(data=="cronError"){
				   	 						layer.alert("cron表达式错误!",{closeBtn:0},function(){
				   	 							layer.close(layer.index);
				   	 						})
				            			}else if(data=="sessionTimeout"){
											layer.alert("登录失效。请重新登录!",{closeBtn:0},function(){
												layer.closeAll();
											});
				   	 					}else{
				   	 						layer.alert("保存失败!",{closeBtn:0},function(){
				   	 							layer.close(layer.index);
				   	 						})
				   	 					}
				   	 	       	     },
				   	 	     		error: function(e) {
				   	 	    			layer.alert("服务器异常!")
				   	 	    			layer.close(index);
				   	 	    			cancelFun();
				   	 	    		} 
				   	 	       	 });
		   	                },
		   	                btn2: function(index, layero) {
		   	                 	cancelFun();
		   	                },
		   	                cancel: function(index, layero){ 
		   	                	cancelFun();
		   	               	}, 
		   	                area: ['550px','600px'],
		   	            });
		          }); 
		}

		 //将给定的集合拼接成表单内容
		function generateCollectionSelect(data){
	    	var recordList=data;
	    	var infoStr = '';				
			for(var i=0;i<recordList.length;i++){
				infoStr=infoStr+
                 "<tr>"+ 
                     "<td style='padding:3px 5px'><div style='max-width:180px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].fd_gzmc+"' >"+recordList[i].fd_gzmc+"</div></td>"+ 
                     "<td style='padding:3px 5px'><div style='width:25px' >"+(recordList[i].fd_rwzt=="Y"?"启用":"停用")+"</div></td>"+ 
                     "<td style='padding:3px 5px'><div style='width:20px'><button type='button' onclick='removeIdFun(\""+recordList[i].id+"\")'  class='up-btn up-btn-xs up-btn-default'> <i class='icon-u-remove'></i>删除</button></div></td> "+
                "</tr>"; 
			}
        	$("#qz_body").empty();
        	$("#qz_body").append(infoStr);
        	if(recordList.length>=4){
        		$("#qz_body").parent().parent().height(110).addClass("u_scroll").css({"overflow":"hidden"});
        	}else{
        		$("#qz_body").parent().parent().removeAttr("style");
        		$("#qz_body").parent().parent().height(40+recordList.length*23).removeClass("u_scroll").css({"overflow":"visible"});
        		$("#qz_div").getNiceScroll().hide();
        	}
        	
		}    
		
		 //将已选内容添加到已选数组
		function collectJobToSelected(data){
  		     for(var i =0 ; i<data.length;i++){
    		    	ids_struct.addSelected(data[i])
    		 }
		}
		
		 //如果是处理任务，前置任务需要做处理
		function generateHandle(data){
			generateCollectionSelect(data);
			collectJobToSelected(data);
		}
    
	$("#add").click(function(){
      	 $.ajax({
	       		 type : "post",
	       		 url : basePath + "/taskRwb/ajaxCreateScheduleJSP",
	       		 dataType : "json",
	       		 scriptCharset: 'utf-8',
	       		 data: {"fd_lx":fd_lx},
	       		 success: function (data) {
	       		    //如果是采集任务，生成采集选项框
	       		    if(fd_lx=='1'){
	       		    	generateHandle(data.collectList);
	       		    }
 	   				layerOpen();
	       	     },
	     		error: function(e) {
	    			layer.alert("服务器异常!")
	    		} 
	     });
	});
	
	function editorFun(id){
		 require(['layer'], function() {
	       	 $.ajax({
	 	       		 type : "post",
	 	       		 url : basePath + "/taskRwb/ajaxCreateScheduleJSP",
	 	       		 dataType : "json",
	 	       		 scriptCharset: 'utf-8',
	 	       		 data: {"id":id,"fd_lx":fd_lx},
	 	       		 success: function (data) {
	 	       		    //任务对象，如果有任务对象则是编辑页面，
		 	   		     $("#fd_gzmc").val(data.fd_gzmc);
		 	   	 	     $("#fd_ddpl").val(data.rwbObj.fd_ddpl);
		 	   	 	     var fd_scqdsj = data.rwbObj.fd_scqdsj+"";
		 	   	 		 fd_scqdsj = new Date(parseInt(fd_scqdsj)).format("yyyy-MM-dd hh:mm:ss"); 
		 	   			 $("#fd_scqdsj").val(fd_scqdsj);
		 	   	 		 var fd_sjsjc = data.rwbObj.fd_sjsjc+"";
		 	   	 		 fd_sjsjc = new Date(parseInt(fd_sjsjc)).format("yyyy-MM-dd hh:mm:ss"); 
		 	   			 $("#fd_sjsjc").val(fd_sjsjc);
		 	   			 $("#fd_sjl").val(data.rwbObj.fd_sjl);
		 	   			 $("#fd_rwms").val(data.rwbObj.fd_rwms);
		 	   			 if(data.rwbObj.fd_rwzt=="Y"){
		 	   				 $('#fd_rwzt_y').click();
		 	   			 }else{
		 	   				 $('#fd_rwzt_n').click();
		 	   			 };
		 	   			 if(data.rwbObj.fd_zntz=="Y"){
		 	   				 $('#fd_zntz_y').click();
		 	   			 }else{
		 	   				 $('#fd_zntz_n').click();
		 	   			 };
		 	   			 globalObject.fd_gz_id= data.rwbObj.fd_gz_id;
		 	   			 globalObject.fd_rw_id= data.rwbObj.id;
		 	   			 $("#fd_sjldw").val(data.rwbObj.fd_sjldw);
		       		     if(fd_lx=='1'){
		       		    	generateHandle(data.collectList);
		       		     }
		 	   			 layerOpen();
	 	       	     },
	 	     		error: function(e) {
	 	    			layer.alert("服务器异常!")
	 	    		} 
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
                url : basePath+"/rulemacontroller/ajaxRwbRecord",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';				
                	for(var i=0;i<recordList.length;i++){
                		infoStr=infoStr+"<tr>"+
                	     "<td>"+recordList[i].FD_RWBH+"</td>"+ 
                         "<td><div style='max-width:160px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_GZMC+"'>"+recordList[i].FD_GZMC+"</div></td>"+ 
                         "<td><div style='width:120px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SRSJY_MC+"'>"+recordList[i].FD_SRSJY_MC+"</div></td>"+ 
                         "<td><div style='width:120px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SCSJY_MC+"'>"+recordList[i].FD_SCSJY_MC+"</div></td>"+ 
                         "<td><div style='width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_RWMS+"'>"+recordList[i].FD_RWMS+"<div></td>"+ 
                         "<td><div style='width:120px'>"+(recordList[i].FD_GXSJ==null?"":recordList[i].FD_GXSJ)+"<div></td>";
                         if(recordList[i].FD_RWZT=="N"){
                        	 infoStr=infoStr+
                        	 "<td><div style='width:30px' >停用</div></td>";
                         }else if(recordList[i].FD_RWZT=="Y"){
                        	 infoStr=infoStr+
                        	 "<td><div style='width:30px' >启用</div></td>";
                         }
                         infoStr=infoStr+"<td><div style='width:100px'>";
                         if(recordList[i].FD_RWZT=="N"){
                        	 infoStr=infoStr+
                             	'<button type="button" onclick="startFun(\''+recordList[i].ID+'\')"  class="up-btn up-btn-xs up-btn-default"> <i class="icon-u-qiyong"></i>启用</button>'+ 
                             	'<button type="button" onclick="editorFun(\''+recordList[i].ID+'\')" class="up-btn up-btn-default up-btn-xs u-editor"><i class="icon-u-edit"></i>编辑</button>'
                         }else if(recordList[i].FD_RWZT=="Y"){
                        	 infoStr=infoStr+
                             	'<button type="button" onclick="closeFun(\''+recordList[i].ID+'\')"  class="up-btn up-btn-xs up-btn-default"> <i class="icon-u-tingyong"></i>停用</button> '
                         }
                        infoStr=infoStr+"</div></td></tr>";
                	}
                	$("#body").empty();
                	$("#body").append(infoStr);
                },
                totalName:'total',
				pageParams: function(data){
					return {
						pageNum:data.pageIndex+1,
						pageSize:data.pageSize
					};
				},
				params:{"fd_lx":fd_lx}
                
             }  
        });
        
        $("#search").click(function() {
    		//提取参数
    		params = getParams();
        	require(['layer'], function() {
        		$("#u_pagination").pagination('setParams', params);
        		$("#u_pagination").pagination('remote');
        	})
        })
       
    })
    </script>
    <script src="${basePath}/plugins/task/js/createSchedule.js"></script>
    <script src="${basePath}/plugins/task/js/chooseQzrw.js"></script>
</body>
</html>