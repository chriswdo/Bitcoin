package org.tarena.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.util.MessageUtil;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapperDao userDao;
	@Resource
	private NoteBookMapperDao bookDao;
	
	public NoteResult checkLogin(
			String name, String password) {
		NoteResult result = new NoteResult();
		
		User user = userDao.findByName(name);
		if(user == null){
			result.setStatus("1");
			result.setMsg("�û������");
			return result;
		}
		//���û�������������
		String md_password = MessageUtil.md5(password);
		//Ȼ������ݿ��е�����ȶ�
		if(!user.getCn_user_password().equals(md_password)){
			result.setStatus("2");
			result.setMsg("���벻��ȷ");
			return result;
		}
		result.setStatus("0");
		result.setMsg("��¼�ɹ�");
		//���һ�����ƺ�,���û�ID�����ƺ�һ�𷵻ظ�ͻ�
		String tokenId = MessageUtil.createToken();
		Map<String,Object> data = 
				new HashMap<String, Object>();
		data.put("token", tokenId);
		data.put("userId", user.getCn_user_id());
		result.setData(data);
		return result;
	}

	/**
	 * ����HTTP Basic Authenticationģʽ���� 
	 */
	public NoteResult checkLogin(String base64Msg) {
		String name = "";
		String password = "";
		try{
			//��base64���н���
			String base64_msg = base64Msg.split(" ")[1];
			//����base64����,��ȡ"�û���:����"����
			String msg = MessageUtil.base64Decode(base64_msg);
			System.out.println("msg"+msg);
			//��ȡ�û�������
			name = msg.split(":")[0];
			password = msg.split(":")[1];
		}catch(Exception ex){
			NoteResult result = new NoteResult();
			result.setStatus("-1");
			result.setMsg("���Ϸ�����");
			return result;
		}
		//�����û�����������checkLogin����
		return checkLogin(name,password);
	}
	
	//添加事务管理
//	@Transactional
	
	public NoteResult regist(User user) {
		NoteResult result = new NoteResult();
		//����û����Ƿ��ظ�
		User user_tmp = userDao.findByName(
				user.getCn_user_name());
		if(user_tmp != null){
			result.setStatus("1");
			result.setMsg("�û�����ռ��");
			return result;
		}
		//�������ID
		String id = MessageUtil.createId();
		user.setCn_user_id(id);
		//�������
		String md5_pwd = MessageUtil.md5(
				user.getCn_user_password());
		user.setCn_user_password(md5_pwd);
		userDao.save(user);
		//Ϊ�û�����һ��"Ĭ�ϱʼǱ�"
	
		NoteBook book = new NoteBook();
		book.setCn_notebook_name("Ĭ�ϱʼǱ�");
		book.setCn_user_id(id);//�����û�ID
		String bookId = MessageUtil.createId();
		book.setCn_notebook_id(bookId);//���ñʼǱ�ID
		Timestamp time = new Timestamp(
				System.currentTimeMillis());//���ô���ʱ��
		book.setCn_notebook_createtime(time);
		bookDao.save(book);//���Ĭ�ϱʼǱ�
		//����״̬��Ϣ
		result.setStatus("0");
		result.setMsg("ע��ɹ�");
		return result;
	}
}
