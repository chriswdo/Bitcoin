package org.tarena.note.web.controller.notebook;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class AddNoteBookController {
	@Resource
	private NoteBookService bookService;
	
	@RequestMapping("/add.form")
	@ResponseBody
	public NoteResult execute(NoteBook book){
		NoteResult result = bookService.add(book);
		return result;
	}
	
}
