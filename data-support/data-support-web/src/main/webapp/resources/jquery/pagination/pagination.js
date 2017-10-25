$.fn.extend({
		//更新数据
		updateTableItem: function(result, template, formatter) {
			var $tableBox = $(this),
				html = '',
				tempHtml = '',
				datas = result.rows,
				data = null,
				len = datas.length,
				size = 10 - len;
			for(var i = 0;i < len;i++) {
				data = datas[i];
				if('function' == typeof(formatter)) {
	    			data = formatter(data, i, result);
	    		}
				tempHtml = template;
				tempHtml = tempHtml.replace(/@\{(.*?)\}/g, function(all, key) {
					return data && (key in data) ? data[key] : '';
				});
			/*	tempHtml = tempHtml.replace(/&\{(.*?)\}/g, function(all, key) {
					return data && (key in data) ? data[key] : '';
				});*/
				html += tempHtml;
			}
			if(html === '' && datas.length === 0) {
				html = '<tr class="up-disabled"><td class="z-first" colspan="' + $tableBox.find('thead th').size() + '">' + 
					   '<div class="m-nf-tips up-text-center" style="color:#CD2626;font-size:26px;padding:30px;"><i class="up-icon-smile-o "></i> &nbsp;抱歉，找不到相关的数据！</div></td></tr>';
			}
			$tableBox.find('tbody').html(html);
			$(document).find('input[type="checkbox"]').uCheck('uncheck');
			return $tableBox;
		},
		
		//分页
		pagination: function(url, template, formatter) {
			var $tableBox = $(this),
				$pagerForm = $tableBox.find('tfoot form'),
				$crtPageNo = $pagerForm.find('.crtPageNo'),
				$totalPages = $pagerForm.find('.totalPages'),
				$pageSize = $pagerForm.find('.pageSize'),
				$nextPageBtn = $pagerForm.find('.nextPageBtn'),
				$prePageBtn = $pagerForm.find('.previousPageBtn'),
				$firstPageBtn = $pagerForm.find('.firstPageBtn'),
				$lastPageBtn = $pagerForm.find('.lastPageBtn'),
				$pageNoIpt = $pagerForm.find('.pageNoIpt'),
				$numBtnGroup = $('.numBtnGroup'),
				tableBoxId = $tableBox.attr('id');
			// load the first page data
		    submit(serializePageForm(1, parseInt($pageSize.find('option:selected').val())));
		    // 清除事件
		    if($pagerForm.attr('data-pagination') == "true") {
		    	$pagerForm.off('click change blur keydown');
		    	$('.u-btn[submit="' +tableBoxId+ '"][action="search"]').off('click');
		    }
		    // 标记该form已经pagination化
		    $pagerForm.attr('data-pagination', 'true');

			$pagerForm.on('click', '.firstPageBtn', function (){	// 跳至第一页
				var disabled = $(this).hasClass('up-disabled'), 
					pageSize = pageSize = parseInt($pageSize.val());
				if(!disabled){
					submit(serializePageForm(1, pageSize));
				}
			}).on('click', '.lastPageBtn', function (){	// 跳至最后一页
				var disabled = $(this).hasClass('up-disabled'), 
					pageSize = pageSize = parseInt($pageSize.val()),
					total = parseInt($totalPages.val());
				if(!disabled){
					submit(serializePageForm(total, pageSize));
				}
			}).on('click', '.nextPageBtn', function() {	// 下一页
				var $this = $(this),
			        disabled = $this.hasClass('up-disabled'),
			        crtPage = parseInt($crtPageNo.val()),
			        pageSize = parseInt($pageSize.val()),
			        total = parseInt($totalPages.val());

		        if (!disabled && verify(crtPage + 1, total)) {
		            submit(serializePageForm(crtPage + 1, pageSize));
		        }
			}).on('click', '.previousPageBtn', function() {	// 上一页
				var $this = $(this),
			        disabled = $this.hasClass('up-disabled'),
			        crtPage = parseInt($crtPageNo.val()),
			        pageSize = parseInt($pageSize.val()),
			        total = parseInt($totalPages.val());

		        if (!disabled && verify(crtPage - 1, total)) {
		            submit(serializePageForm(crtPage - 1, pageSize));
		        }
			}).on('keydown', '.pageNoIpt', function(event) {	// 修改页码
				if (event.which == 13) {
		    		getPageData($(this));
		    		$(this).val('')
		    		event.preventDefault();
		    	}
			}).on('change', '.pageSize', function() {	// 修改分页大小
		        submit(serializePageForm(1, $(this).val()));
		    }).on('click', '.pageNumBtn a', function() {//跳转到具体页
		    	var $this = $(this),
	    		crtPage = parseInt($this.html()),
		    	pageSize = parseInt($pageSize.val()),
		        total = parseInt($totalPages.val());
	    	
		    	if (verify(crtPage, total)) {
		            submit(serializePageForm(crtPage, pageSize));
		        }
		    });
		    $('#search').on('click', function() {	// 查询按钮事件
		    	var pageSize = parseInt($pageSize.find('option:selected').val()); 
		    	submit(serializePageForm(1, pageSize));
		    });
		    
		    $('#refresh').on('click', function() {	// 刷新列表
		    	var pageSize = parseInt($pageSize.find('option:selected').val());
		    	submit(serializePageForm($crtPageNo.val(), pageSize));
		    });
		    
		    $('#reload').on('click', function() {	// 重新检测
		    	var pageSize = parseInt($pageSize.find('option:selected').val());
		    	$('.toolbar>form').find('input[name="jarName"]').val('');
		    	submit(serializePageForm(1, pageSize));
		    });
		    
		 // 校检页码
			function verify(pageNo, total) {
		        if (pageNo < 1) {
		            return false;
		        } else {
		            return pageNo <= total ? true: false;
		        }
		    }
		    
		     // 传入当前页数输入控件，获取某一页的数据
		    function getPageData($this) {
				var iptVal = $.trim($this.val()),
					pageSize = parseInt($pageSize.val()),
			        total = parseInt($totalPages.val()),
			        crtPage;
				if(iptVal !== '' && !iptVal.match(/[^\d]+/g)){
					crtPage = parseInt(iptVal);
					if (!verify(crtPage, total)) {
		    			crtPage = crtPage > total ? total : crtPage;
		    			crtPage = crtPage < 1 ? 1 : crtPage;
		    		}
					submit(serializePageForm(crtPage, pageSize));
				} else {
					return;	// 输入的是非法字符或者空
				}
			}

		    // 绑定不在页脚表单的input元素，使之可以与页脚表单一起提交,只需把input元素与tableBox绑定即可,
		    // 如：<input submit="tableBoxId">
		    // return 表单序列化的值
		    function serializePageForm(crtPage, pageSize) {
		    	$crtPageNo.val(crtPage);
		    	$pageSize.val(pageSize);
		    	var selector = 'input[submit=' + tableBoxId + ']',
		    		formData = '',serializeObj = {},array = $pagerForm.serializeArray();  
	            $(array).each(function() {  
	                if(serializeObj[this.name]) {  
	                    if($.isArray(serializeObj[this.name])) {  
	                        serializeObj[this.name].push(this.value);  
	                    } else {  
	                        serializeObj[this.name] = [serializeObj[this.name],this.value];  
	                    }  
	                } else {  
	                    serializeObj[this.name] = this.value;
	                }  
	            });
	            formData = serializeObj;
		        // 添加要提交的input元素值
		        $(selector).each(function() {
		        	var $this = $(this);
		        	formData[$this.attr('name')] = $this.val();
		        });
		        return formData;
		    }

		    // 提交表单
		    function submit(formData) {
		    	var totals,
		    		page,
		    		size,
		    		count = $tableBox.find('th').size();
		    	$tableBox.find('tbody').html('<tr class="up-disabled"><td class="z-first" colspan="'+count+'">'+
	    								'<div class="m-nf-tips up-text-center" style="color:#00CD00;font-size:26px;padding:30px;"><i class="up-icon-spinner up-icon-spin"></i>  加载中...</div></td></tr>');
	
	    		$.ajax({
	    			type : 'POST',
	    			url : url,
	    			data : formData,
	    			dataType : "jsonp",
	    			timeout : 60000,
	    			success : function(result) {
	    				$tableBox.updateTableItem(result, template, formatter);
				    	updatePageNo(result.pager);
	    			},
	    			error : function(XMLHttpRequest, textStatus, errorThrown) {
	    				$tableBox.find('tbody').html('<tr class="up-disabled"><td class="z-first" colspan="'+count+'">'+
						'<div class="m-nf-tips up-text-center" style="color:#EE2C2C;font-size:26px;padding:30px"><i class="up-icon-warning"></i>&nbsp; 抱歉，程序猿开小差了，请联系我们...</div></td></tr>');
	    				updatePageNo(null);
	    			}
	    		});
		    }
		    
		    // 更新页码
		    function updatePageNo(pager) {
		    	/*
		    	 * 分页为空，则使按钮不可用
		    	 */
		    	if(pager == null){
		    		$('.up-nav-pills').find('li').addClass('up-disabled');
		    		$('.up-nav-pills').find('.refresh').removeClass('up-disabled');
		    		$pageNoIpt.val(0);
		    		$('.toolbar>form').children().find('a').each(function(){
		    			$(this).addClass('up-disabled');
		    		});
		    		return;
		    	}
		    	var crtPageNo = pager.crtPageNo,
			    	totalPages = pager.totalPage,
			    	pageSize = pager.pageSize,
			    	totalCount = pager.totalCount,
			    	i = 1;
			    	/*headHtml = '<li class="pageNumBtn @{z-active}"><a href="javascript:void(0);">',
	    			html = '';*/
		    	$crtPageNo.val(crtPageNo);
		    	$pageNoIpt.val(crtPageNo);
		    	$totalPages.val(totalPages);
		    	$pageSize.val(pageSize);
		    	if(totalCount == 0){
		    		$('.toolbar>form').children().find('a').each(function(){
		    			$(this).addClass('up-disabled');
		    		});
		    	} else {
		    		$('.toolbar>form').children().find('a').each(function(){
		    			$(this).removeClass('up-disabled');
		    		});
		    	}
		    	if(crtPageNo >= totalPages){
		    		$nextPageBtn.addClass('up-disabled') ;
		    		$lastPageBtn.addClass('up-disabled') ;
		    	} else{
		    		$nextPageBtn.removeClass('up-disabled');
		    		$lastPageBtn.removeClass('up-disabled');
		    	}
		    	if(crtPageNo <= 1){
		    		$prePageBtn.addClass('up-disabled');
		    		$firstPageBtn.addClass('up-disabled');
		    	} else{
		    		$prePageBtn.removeClass('up-disabled');
		    		$firstPageBtn.removeClass('up-disabled');
		    	}
		    	$tableBox.find('.m-pageInfo').html('共' + totalPages + '页');
		    	if(crtPageNo == 0){
		    		$tableBox.find('.m-totalInfo').html('显示0到0,共0条记录');
		    	} else if(crtPageNo * pageSize >= totalCount){
		    		$tableBox.find('.m-totalInfo').empty().html('显示' + ((crtPageNo - 1) * pageSize + 1) + '到' + totalCount + ',共' + totalCount+'条记录');
		    	} else {
		    		$tableBox.find('.m-totalInfo').html('显示' + ((crtPageNo - 1) * pageSize + 1) + '到' + crtPageNo * pageSize  + ',共' + totalCount+'条记录');
		    	}
		    	$tableBox.find('.m-page-totalCount').html(totalCount);
		    	/*
		    	 * 具体页码
		    	 */
		    	/*if($numBtnGroup.size() > 0) {
		    		console.log($numBtnGroup.find('.pageNumBtn'));
		    		$('.m-sp-pagination').find('.pageNumBtn').remove();
		    		if(totalPages <= 5 || crtPageNo <= 2) {
			    		setPageNumBtn(1);
			    	} else {
			    		// 如果激活按钮不是最后3个，则开始页码为crtPageNo - 2，否则为totalPages - 5
			    		setPageNumBtn(crtPageNo >= totalPages - 2 ? (totalPages - 4) : (crtPageNo - 2));
			    	}
			    	function setPageNumBtn(num) {
			    		for(i = 1; i <= 5 && i <= totalPages; i++) {
			    			html = num === crtPageNo ? headHtml.replace('@{z-active}', 'z-active') : 
			    				headHtml.replace('@{z-active}', '');
			    			$nextPageBtn.parent().before(html + num + '</a></li>');
			    			num++;
			    		}
			    	}
		    	}*/
		    	
		    }

		    return $tableBox;
		}
});