package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.NoteBook;

public interface NoteBookMapperDao {
	public List<NoteBook> findByUserId(String userId);
	
	public int save(NoteBook book);
	/**
	 * 设置bookName和userId条件值
	 * @param data
	 * @return
	 */
	public NoteBook findByName(Map<String,Object> data);
}
