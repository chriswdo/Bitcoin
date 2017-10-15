package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class DeleteNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/delete.form")
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = 
			noteService.updateStatus(noteId, "2");
		return result;
	}
	
}
