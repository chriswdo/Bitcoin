

function showAddNoteWindow(){
    	//����alert/alert_note.html
    	$("#can").load("alert/alert_note.html");
    	$("#can").show();//��ʾ������div
    	$(".opacity_bg").show();//��ʾ�ұ���ɫ
};

//�����ʼǰ�ť�Ĳ���
function sureAddNote(){
    	//��ȡ�ͻ�����Ҫ���ݵ����
    	var userId = getCookie("userId");
    	var bookId = $("#book_list a[class='checked']").parent().data("bookId");
    	var noteName = $("#input_note").val().trim();
    	//TODO �����Ƹ�ʽ
    	//����ajax����
    	$.ajax({
    		url:"http://47.93.243.0:8080/noteserverteacher/note/add.form",
    		type:"post",
    		data:{"cn_notebook_id":bookId,
    				"cn_user_id":userId,
    				"cn_note_title":noteName},
    		dataType:"json",
    		success:function(result){//�ص�������
    			if(result.status=="0"){//��ȷ
    			    closePopWindow();//�رմ���
    			    //��ȡ�ʼǵ�ID
    			    var noteId = result.data;
    			    //�������ıʼǱ���ӵ��б���
    			    var li = "";
				    li+="<li class='online'>";
					li+="<a>";
					li+="	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					li+="</a>";
					li+="<div class='note_menu' tabindex='-1'>";
					li+="	<dl>";
					li+="		<dt><button type='button' class='btn btn-default btn-xs btn_move' title='�ƶ���...'><i class='fa fa-random'></i></button></dt>";
					li+="		<dt><button type='button' class='btn btn-default btn-xs btn_share' title='����'><i class='fa fa-sitemap'></i></button></dt>";
					li+="		<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='ɾ��'><i class='fa fa-times'></i></button></dt>";
					li+="	</dl>";
					li+="</div>";
				    li+="</li>";
					$li = $(li);//��li�ַ�ת��jQuery����
					$li.data("noteId",noteId);
					$("#note_list").append($li);//��li��ӵ��ʼǵ�ul��
    				//����ǰli���ó�ѡ��
    				$("#note_list a").removeClass("checked");
    				$("#note_list li:last a").addClass("checked");
    				//���ʼǱ������õ��༭����
    				$("#input_note_title").val(noteName);
    				//���༭����������
    				um.setContent("");
    			}
    		}
    	});
    };

    //�����ʼǲ���
function saveAddNote(){
  	    //��ȡ������Ҫ�ύ�����
  	    var noteName = $("#input_note_title").val().trim();
  	    var noteId = $("#note_list a[class='checked']").parent().data("noteId");
  	    var noteBody = um.getContent();
  	    //TODO ��ʽ��飨�Ƿ�Ϊ�գ�
  		//����Ajax����
  		$.ajax({
  			url:"http://47.93.243.0:8080/noteserverteacher/note/update.form",
  			type:"post",
  			data:{"cn_note_id":noteId,
  					"cn_note_title":noteName,
  					"cn_note_body":noteBody},
  			dataType:"json",
  			success:function(result){//�ص�����
  				if(result.status=="0"){
  					//�жϱ����Ƿ���ı�
  					var li_noteName = $("#note_list a[class='checked']").text().trim();
  					if(!(noteName==li_noteName)){
  					   //��li�еıʼǱ���ı�
  					   var s = "	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
  					   $("#note_list a[class='checked']").html(s);
  					}
  					//��ʾ����ɹ�
  					alert(result.msg);
  				    //var msg = "<font color='red'>"+result.msg+"</font>";
  					//$("#input_note_title_msg").html(msg).hide(5000);//��ʾ
  				}
  			}
  		});
  	};
  
  //�ʼ��б���li�ĵ�������
 function clickNote(){
  		//�������li���ó�ѡ��״̬
  		$("#note_list li a").removeClass("checked");
  		$(this).find("a").addClass("checked");
  		//��ȡ�ʼǵ�ID
  		var noteId = $(this).data("noteId");
  		//����ajax����,��ݱʼǵ�ID��ȡ�ʼ���Ϣ
  		$.ajax({
  			url:"http://47.93.243.0:8080/noteserverteacher/note/loadnote.form",
  			type:"post",
  			data:{"noteId":noteId},
  			dataType:"json",
  			success:function(result){
  				//��ȡ���صıʼ���Ϣ��ʾ���༭��
  				if(result.status=="0"){
  					//��ȡ����
  					var noteName = result.data.cn_note_title;
  					$("#input_note_title").val(noteName);
  					//��ȡ����
  					var noteBody = result.data.cn_note_body;
  					um.setContent(noteBody);
  				}
  			}
  		});
  	};
   function deleteNote() {
  		//获取当前选中的笔记li
  		var $li = $("#note_list a[class='checked']").parent();
  		var noteId = $li.data("noteId");//获取笔记ID
  		//发送ajax请求
  		$.ajax({
  			url:"http://47.93.243.0:8080/noteserverteacher/note/delete.form",
  			type:"post",
  			data:{"noteId":noteId},
  			dataType:"json",
  			success:function(result){
  				if(result.status=="0"){//删除成功
  				   //将笔记li删除
  				   $li.remove();
  				   //清空编辑区显示的笔记信息
  				   $("#input_note_title").val("");
  				   um.setContent("");
  				   alert("删除成功");
  				}
  			}
  		});
  	}
    
   function shareNote(){
  		var $li=$("#note_list a[class='checked']").parent();
  		var noteId=$li.data("noteId")
  		$.ajax({
  			url:"http://47.93.243.0:8080/noteserverteacher/note/share.form",
  			data:{"noteId":noteId},
  			type:"post",
  			dataType:"json",
  			success:function(result){
  				if(result.status=="0"){
  					alert("分享成功");
  				}else{
  					alert("分享失败");
  				}
  				
  			}
  			
  		})
  	}
function searchNote(){
	var code=event.keyCode;
	if(code==13){
		var title=$("#search_note").val().trim();
		$.ajax({
			url:"http://47.93.243.0:8080/noteserverteacher/note/search.form",
			data:{"title":title},
			type:"post",
			dataType:"json",
			success:function(result){
					if(result.status=="0"){
							$("#pc_part_2").hide();//隐藏笔记列表
							$("#pc_part_6").show();//显示搜索列表
							//清除笔记列表内容
							$("#share_list").empty();
							for(i=0;i<result.data.length;i++){
							var share=result.data[i];
							var noteId=share.cn_note_id;
							var noteName=share.cn_note_title;
							var li = "";
						    li+="<li class='online'>";
							li+="<a>";
							li+="	<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>"+noteName+"<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
							li+="</a>";
							li+="<div class='note_menu' tabindex='-1'>";
							li+="	<dl>";
							//li+="		<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
							li+="	</dl>";
							li+="</div>";
						    li+="</li>";
						    $li=$(li);
						    $li.data("noteId",noteId);
						    $("#share_list").append($li);
						}
						
					}
			}
			
		})
	}
    	
}
    