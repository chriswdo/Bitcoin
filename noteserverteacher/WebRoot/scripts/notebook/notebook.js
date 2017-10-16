
//加载笔记本本列表
function loadBookList(){
	     var userId = getCookie("userId");
     $.ajax({
        url:"http://47.93.243.0:8080/noteserverteacher/notebook/loadlist.form",
        type:"post",
        data:{"userId":userId},
        dataType:"json",
        success:function(result){//获取服务器返回的json结果
             if(result.status=="0"){
                 var books = result.data;//获取数据中笔记本信息数组
                 for(i=0;i<books.length;i++){//循环遍历数组中的笔记本信息
                      var bookId = books[i].cn_notebook_id;//获取笔记本id
                      var bookName = books[i].cn_notebook_name;//获取笔记本名字
                      //构建循环生成的li
                     var $book_li = $("<li class='online'><a><i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"+bookName+"</a></li>");
                     $book_li.data("bookId",bookId);//给book_li元素绑定一组值
                 	 //给笔记本列表元素绑定单击事件
                     $book_li.click(loadNotesByBookId);
                     //将$book_li添加到笔记本列表中
                 	$("#book_list").append($book_li);
                 }
                 //默认将第一个li选中
                 $("#book_list li:first a").addClass("checked");
             	//调用第一个li的单击事件处理
                $("#book_list li:first").click();
             }
        },
        error:function(){
            alert("加载错误");
        }
     });
};

//弹出添加笔记本窗口
function showAddBookWindow(){
	   	//载入alert/alert_notebook.html
    	$("#can").load("alert/alert_notebook.html");
    	$("#can").show();//显示弹出框div
    	$(".opacity_bg").show();//显示灰背景色
};

//关闭窗口
function closePopWindow(){
	    $("#can").hide();//将窗口div隐藏
    	$(".opacity_bg").hide();//将背景色div隐藏
};

//创建笔记本
function sureAddNoteBook(){
	   	//获取笔记本名称信息
    	var bookName = $("#input_notebook").val().trim();
    	var userId = getCookie("userId");
    	//检测格式
    	if(bookName==""){
    		$("#bookname_msg").html("<font color='red'>不能为空</font>");
    		return;
    	}
    	//发送ajax请求
    	$.ajax({
    		url:"http://47.93.243.0:8080/noteserverteacher/notebook/add.form",
    		type:"post",
    		data:{"cn_user_id":userId,
    		         "cn_notebook_name":bookName},
    		dataType:"json",
    		success:function(result){
    			if(result.status=="1"){//名称重复
    				$("#bookname_msg").html("<font color='red'>"+result.msg+"</font>");
    			}else if(result.status=="0"){//成功
    		      	//关闭添加窗口
    		      	closePopWindow();
    		      	//将添加笔记本追加到列表中
    		        var bookId = result.data;
    		        //将原有笔记本列表中的li选中状态取消
    		        $("#book_list li a").removeClass("checked");
    		        //生成一个笔记本列表的li元素
    		        var $book_li = $("<li class='online'><a class='checked'><i class='fa fa-book' title='online' rel='tooltip-bottom'></i>"+bookName+"</a></li>");
                     $book_li.data("bookId",bookId);//给book_li元素绑定一组值
                 	//给li绑定单击事件
                     $book_li.click(loadNotesByBookId);
                     //将$book_li添加到笔记本列表中
                 	$("#book_list").prepend($book_li);
    		      }
    		}
    	});
};

//单击加载选中笔记本的笔记信息
function loadNotesByBookId(){
	 $("#pc_part_2").show();//显示笔记列表
	 $("#pc_part_6").hide();//隐藏搜索列表
    	 //将当前笔记本li设置成选中
	 $("#book_list li a").removeClass("checked");
	 $(this).find("a").addClass("checked");
	 //获取笔记本ID
	var bookId = $(this).data("bookId");
	//发送Ajax请求加载笔记
	 $.ajax({
		 url:"http://47.93.243.0:8080/noteserverteacher/note/loadlist.form",
		 type:"post",
		 data:{"bookId":bookId},
		 dataType:"json",
		 success:function(result){//获取笔记信息
			 if(result.status=="0"){
				 //先清除原有笔记列表的li
				 $("#note_list").empty();
				 //循环生成笔记的li显示
				 for(i=0;i<result.data.length;i++){
					 var note = result.data[i];//获取某个笔记的json对象
					 var noteId = note.cn_note_id;//获取笔记id
					 var noteTitle = note.cn_note_title;//获取笔记标题
					 //生成笔记的li元素
						var li = "";
				   	li+="<li class='online'>";
						li+="<a>";
						li+="	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteTitle+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
						li+="</a>";
						li+="<div class='note_menu' tabindex='-1'>";
						li+="	<dl>";
						li+="		<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
						li+="		<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>";
						li+="		<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>";
						li+="	</dl>";
						li+="</div>";
				   	li+="</li>";
						$li = $(li);//将li字符串转成jQuery对象
						$li.data("noteId",noteId);
						$("#note_list").append($li);//将li添加到笔记的ul中
				 }
			 }
		 }
	 });
};





