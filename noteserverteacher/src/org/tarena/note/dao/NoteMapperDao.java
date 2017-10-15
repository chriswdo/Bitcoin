package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.SearchBean;

public interface NoteMapperDao {
	public Note findById(String id);
	public List<Note> findByBookId(String bookId);
	public int save(Note note);
	public int update(Note note);
	/**
	 * ���±ʼǵ�״̬
	 * @param params id:�ʼǱ�ID;status:�ʼ�״̬
	 * @return
	 */
	public int updateStatus(Map<String,Object> params);
	
	public List<Map> searchShare(SearchBean bean);
}
