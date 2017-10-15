package org.tarena.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.dao.ShareMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.Share;
import org.tarena.note.util.MessageUtil;
import org.tarena.note.util.NoteParams;

@Service
public class NoteServiceImpl implements NoteService{

	@Resource
	private NoteMapperDao noteDao;
	@Resource 
	private ShareMapperDao shareDao;
	
	public NoteResult loadNoteByBookId(String bookId) {
		NoteResult result = new NoteResult();
		if(bookId==null || "".equals(bookId)){
			result.setStatus("1");
			result.setMsg("û�����");
			return result;
		}
		
		List<Note> list = noteDao.findByBookId(bookId);
		result.setStatus("0");
		result.setMsg("�ɹ�");
		result.setData(list);
		return result;
	}

	public NoteResult add(Note note) {
		NoteResult result = new NoteResult();
		//TODO ���ʼ��Ƿ�����
		//���
		String id = MessageUtil.createId();
		note.setCn_note_id(id);//��������
		note.setCn_note_create_time(
				System.currentTimeMillis());//���ô���ʱ��
		note.setCn_note_status_id(
				NoteParams.NORMAL_NOTE);//����״̬
		note.setCn_note_type_id(null);//���ñʼ�����
		note.setCn_note_body("");//��������
		
		noteDao.save(note);//����
		result.setStatus("0");
		result.setMsg("�ɹ�");
		result.setData(id);//������׷�ӵıʼ�ID
		return result;
	}

	public NoteResult update(Note note) {
		NoteResult result = new NoteResult();
		//���ø���ʱ��
		note.setCn_note_last_modify_time(
				System.currentTimeMillis());
		noteDao.update(note);//ִ�и���
		result.setStatus("0");
		result.setMsg("����ɹ�");
		return result;
	}

	public NoteResult loadNoteById(String noteId) {
		NoteResult result = new NoteResult();
		if(noteId==null || "".equals(noteId)){
			result.setStatus("1");
			result.setMsg("û���");
			return result;
		}
		Note note = noteDao.findById(noteId);
		result.setStatus("0");
		result.setMsg("�ɹ�");
		result.setData(note);
		return result;
	}

	public NoteResult updateStatus(String noteId, String statusId) {
		NoteResult result = new NoteResult();
		Map<String,Object> params = 
				new HashMap<String, Object>();
		params.put("id", noteId);
		params.put("status", statusId);
		noteDao.updateStatus(params);
		//TODO  如果是删除，要删除分享中的记录；
		result.setStatus("0");
		result.setMsg("�ɹ�");
		return result;
	}

	public NoteResult share(String noteId) {
		NoteResult result=new NoteResult();
		
		if(noteId==null||"".equals(noteId)){
			result.setStatus("1");
			result.setMsg("分享失败");	
		}else {
			Note note=noteDao.findById(noteId);
			Share share=new Share();
			share.setCn_note_id(note.getCn_note_id());
			share.setCn_share_body(note.getCn_note_body());
			share.setCn_share_id(MessageUtil.createId());
			share.setCn_share_title(note.getCn_note_title());
			shareDao.save(share);
			result.setStatus("0");
			result.setMsg("分享成功");
		}
		return result;
	}

	public NoteResult searchShareNote(String title) {
		NoteResult result=new NoteResult();
		
		if(title==null){
			result.setStatus("1");
			result.setMsg("提取错误");
		}else {
			title="%"+title+"%";
			List<Share> list=shareDao.search(title);
			result.setStatus("0");
			result.setMsg("提取成功");
			result.setData(list);
		}
		
		return result;
	}

}
