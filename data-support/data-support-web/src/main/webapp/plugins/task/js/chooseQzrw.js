        $("#qz_search-create").click(function() {
	   		//提取参数
	   		var qzrw_mc = $.trim($("#qzrw_mc").val());
	   		params = { "qzrw_mc": qzrw_mc,"ids":ids_struct.getIdsStr()}
	       	require(['layer'], function() {
	       		$("#u_pagination_create_qz").pagination('setParams', params);
	       		$("#u_pagination_create_qz").pagination('remote');
	       	})
	    })    


	function cleanBuffer(){
		ids_struct.cleanItems();
	}
     $("#qz_check").click(function(){
    	 	$("#qz_search-create").click();
            require(['layer'], function() {
                 layer.open({
                     type: 1,
                     title: '修改',
                     content: $("#qz_layer-check"),
                     btn: ['添加', '取消'],
                     btn1:function(index, layero){
                    	 //将选择好的添加到已选择的
                    	 ids_struct.addToSelected();
                    	 generateCollectionSelect(ids_struct.getSelectedInfo());
                    	 layer.close(index);
                     },
                     btn2:function(index, layero){
                    	 cleanBuffer();
                     },
                     cancel:function(index,layero){
                    	 cleanBuffer();
                     },
                     area: '700px'
                 });
             })
     })
   
     function checkFunc(id,rwbh,rwmc,zt,event){
    	 var item = {
    			 id:id,
    			 fd_rwbh:rwbh,
    			 fd_gzmc:rwmc,
    			 fd_rwzt:zt
    	 }
    	 if(ids_struct.isFulled()){
    		 var tar = $(event.target);
    		 tar.attr("checked",false);
    		 layer.alert("前置任务数不能大于10!");
    		 return ;
    	 }
    	 if(event.target.checked==true){
    		 ids_struct.addId(item);
    	 }else{
    		 ids_struct.removeId(item);
    	 }
      }
     
     require(['uskin'], function() {
    	function isChecked(id){
	   		 for(var i=0;i<10;i++){
				 if(ids_struct.items[i].id==id){
					 return true;
				 }
			 }
	   		 return false;
    	}
        $("#u_pagination_create_qz").pagination({
            pageIndex: 0,
            pageSize: 5,
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
                url : basePath+"/taskRwb/ajaxQzrwRecord",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';				
					for(var i=0;i<recordList.length;i++){
						var status = isChecked(recordList[i].ID);
						infoStr=infoStr+
	                     "<tr>"+ 
	                     	 "<td><input type='checkbox' "+(status==true?"checked=true ":"")+" onclick='checkFunc(\""+recordList[i].ID+"\",\""+recordList[i].FD_RWBH+"\",\""+recordList[i].FD_GZMC+"\",\""+recordList[i].FD_RWZT+"\",event)'></td>"+ 
	                         "<td><div style='max-width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_RWBH+"' >"+recordList[i].FD_RWBH+"</div></td>"+ 
	                         "<td><div style='width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_GZMC+"' >"+recordList[i].FD_GZMC+"</div></td>"+ 
	                         "<td><div style='width:30px' >"+(recordList[i].FD_RWZT=="Y"?"启用":"停用")+"</div></td>"+ 
	                         "<td><div style='max-width:160px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_RWMS+"' >"+recordList[i].FD_RWMS+"</div></td>"+ 
	                     "</tr>"; 
					}
                	$("#qz_body-create").empty();
                	$("#qz_body-create").append(infoStr);
                },
                totalName:'total',
				pageParams: function(data){
					return {
						pageNum:data.pageIndex+1,
						pageSize:data.pageSize
					};
				},
				params:{"ids":ids_struct.getIdsStr()}
             }    
        })
     })