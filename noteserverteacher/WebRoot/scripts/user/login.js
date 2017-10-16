$(function(){
	//���¼��ť�󶨵�������
	$("#login").click(function(){
	    //������ϴ���Ϣ���
	    $("#name_error").html("");
	    $("#pwd_error").html("");
	    //��ȡ�û��������
	    var name = $("#name").val().trim();
	    var password = $("#password").val().trim();
	    //����ݽ��и�ʽ���
	    var check = true;//��ʾͨ����
	    if(name == ""){
	        $("#name_error").html("�û�����Ϊ��");
	        check = false;
	    }
	    if(password == ""){
	         $("#pwd_error").html("���벻Ϊ��");
	         check = false;
	    }
	    //ͨ����ݸ�ʽ���,�ٷ���Ajax����
	  if(check){
		 //���û��������ƴ���ַ�
	    var msg = name+":"+password;
	    //����Ϣ����Base64����
	    var base64_msg = Base64.encode(msg);
	    //����Ϣ����header��������
	    $.ajax({
	    	url:"http://47.93.243.0:8080/noteserverteacher/user/login.form",
	    	type:"post",
	    	dataType:"json",
	    	beforeSend:function(xhr){
	    		xhr.setRequestHeader("Authorization","Basic "+base64_msg);
	    	},
	    	success:function(result){//result���������ص�json���
	              //��ݽ���status����
	              if(result.status=="0"){//�ɹ�
	                    //��ȡ�û�ID������
	                    var token = result.data.token;
	                    var userId = result.data.userId;
	                    //����Cookie
	                    addCookie("token",token,2);
	                    addCookie("userId",userId,2);
	                    window.location.href="edit.html";//����edit.html
	              }else if(result.status=="1"){//�û����
	                    $("#name_error").html(result.msg);
	              }else if(result.status=="2"){//�����
	                    $("#pwd_error").html(result.msg);
	              }
	          }
	    });
	    }
	});
});