package org.tarena.note.web.controller.note;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchBean;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class SearchShareNoteController {
	@Resource
	private NoteService noteService;
	@Resource
	private NoteMapperDao noteDao;
	@RequestMapping("/search.form")
	@ResponseBody
	public NoteResult searchShareNote(String title){
		NoteResult result=noteService.searchShareNote(title);
		
		return result;
	}
	
	@RequestMapping("/hightsearch.form")
	@ResponseBody
	public NoteResult searchNoteByCondition (String title,String beginDate,String endDate){
		SearchBean bean=new SearchBean();
		title="%"+title+"%";
		bean.setTitle(title);
		if(beginDate!=null&&!"".equals(beginDate)){
			Date d=Date.valueOf(beginDate);
			bean.setBeginDate(d.getTime());
		}
		if(endDate!=null && !"".equals(endDate)){
			long end = Date.valueOf(endDate).getTime();
			bean.setEndDate(end);
		}
		List<Map> list = noteDao.searchShare(bean);
		NoteResult result = new NoteResult();
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(list);
		return result;
		
	}
}
