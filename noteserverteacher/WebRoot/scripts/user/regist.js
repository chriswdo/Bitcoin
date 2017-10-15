	//׷��ע�ᰴť�¼�����
    $(function(){
       $("#regist_button").click(function(){
            //��ȡ���
            var username = $("#regist_username").val();
            var nickname = $("#nickname").val();
            var password = $("#regist_password").val();
            //TODO �����ݸ�ʽ
            //����ajax����
            $.ajax({
               url:"http://localhost:8080/noteserverteacher/user/regist.form",
               data:{"cn_user_name":username,
               			"cn_user_desc":nickname,
               			"cn_user_password":password},
               	dataType:"json",
               	type:"post",
               	success:function(result){
               		if(result.status=="1"){//�û���ռ��
               		     $("#warning_1").html(result.msg).show();
               		}else if(result.status=="0"){//�ɹ�
               		     alert("��ϲ��ע��ɹ�");
               		     $("#back").click();//�������ذ�ť�ĵ����¼�
               		}
               	},
               	error:function(){
               	   alert("���ź�,ע��ʧ��");
               	}
            
            });
       });
    });