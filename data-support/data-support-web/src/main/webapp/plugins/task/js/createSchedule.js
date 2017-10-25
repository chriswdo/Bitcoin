			
        //选择function
    function chooseFun(id,fd_gzmc){
    	 require(['layer'], function() {
    		 layer.close(layer.index);
    		 $("#fd_gzmc").val(fd_gzmc);
    		 if($("#fd_rwmc").val()==""){
    			 $("#fd_rwmc").val(fd_gzmc);
    		 }
    		 globalObject.fd_gz_id=id;
    	   	 $("#fd_gzmc_c").val("");
    	   	 $("#tb_name_c").val("");
    	 })
    }
    
    //日历精确到秒
    require(["moment","daterangepicker"],function(){
		$('.u-daterange-single-init').daterangepicker({
		    singleDatePicker:!0,
		    showDropdowns:!0,
		    timePicker:!0,
		    timePicker24Hour:!0,
		    timePickerSeconds:!0,
		    autoUpdateInput: false,
		    locale: {
		          format : 'YYYY-MM-DD HH:mm:ss'
		    }
		  });

		  $('.u-daterange-single-init').on('apply.up-daterangepicker', function(ev, picker) {
		      $(this).val(picker.startDate.format('YYYY-MM-DD HH:mm:ss'));
		  });

    })
        	
	    require(['uskin'], function() {
		//选择规则
        $("#u_pagination_create").pagination({
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
                url : basePath+"/rulemacontroller/ajaxGzpzRecord",
                success:function(data){
                	recordList=data.list;
                	var infoStr = '';				
					for(var i=0;i<recordList.length;i++){
						infoStr=infoStr+
	                     "<tr>"+ 
	                         "<td><div style='max-width:160px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_GZMC+"' >"+recordList[i].FD_GZMC+"</div></td>"+ 
	                         "<td><div style='width:120px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SRSJY_MC+"' >"+recordList[i].FD_SRSJY_MC+"</div></td>"+ 
	                         "<td><div style='width:120px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].FD_SCSJY_MC+"' >"+recordList[i].FD_SCSJY_MC+"</div></td>"+ 
	                         "<td><div style='max-width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+recordList[i].R_JOB_NAME+"' >"+recordList[i].R_JOB_NAME+"</div></td>"+ 
	                         "<td><div style='max-width:100px;overflow:hidden;white-space: nowrap;text-overflow: ellipsis;' title='"+(recordList[i].R_JOB_DESC==null?"":recordList[i].R_JOB_DESC)+"' >"+(recordList[i].R_JOB_DESC==null?"":recordList[i].R_JOB_DESC)+"</div></td>"+ 
	                         "<td>"+ 
	                             '<button onclick="chooseFun(\''+recordList[i].ID+'\',\''+recordList[i].FD_GZMC+'\')"  class="up-btn up-btn-default up-btn-xs u-editor"><i class="icon-u-edit"></i>选择</button>'+ 
	                         "</td>"+ 
	                     "</tr>"; 
					}
                	$("#body-create").empty();
                	$("#body-create").append(infoStr);
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
        })
	    })
        
        
        $("#search-create").click(function() {
	   		//提取参数
	   		var fd_gzmc = $.trim($("#fd_gzmc_c").val());
	   		params = { "fd_gzmc": fd_gzmc,"fd_lx":fd_lx}
	       	require(['layer'], function() {
	       		$("#u_pagination_create").pagination('setParams', params);
	       		$("#u_pagination_create").pagination('remote');
	       	})
	    })
	    
	    $("#gz_check").click(function() {
	  			$("#search-create").click();
	            require(['layer'], function() {
	                layer.open({
	                    type: 1,
	                    title: '修改',
	                    content: $("#layer-check"),
	                    area: '900px'
	                });
	            })
	    })