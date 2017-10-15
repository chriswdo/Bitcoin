package org.tarena.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.util.MessageUtil;

@Service("bookService")
public class NoteBookServiceImpl implements NoteBookService{
	@Resource
	private NoteBookMapperDao bookDao;
	
	public NoteResult loadListByUserId(String userId) {
		NoteResult result = new NoteResult();
		if(userId == null || "".equals(userId)){
			result.setStatus("1");
			result.setMsg("没有用户ID");
			result.setData(null);
			return result;
		}
		List<NoteBook> list = 
				bookDao.findByUserId(userId);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(list);
		return result;
	}

	public NoteResult add(NoteBook book) {
		NoteResult result = new NoteResult();
		//检查笔记本名称是否重复
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", book.getCn_user_id());
		params.put("bookName", book.getCn_notebook_name());
		NoteBook book_temp = 
			bookDao.findByName(params);
		if(book_temp != null){
			result.setStatus("1");
			result.setMsg("已存在该名称笔记本");
			return result;
		}
		//不重复追加
		String id = MessageUtil.createId();
		book.setCn_notebook_id(id);//设置主键ID
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//设置创建时间
		bookDao.save(book);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(id);//将笔记本ID返回
		return result;
	}

}
