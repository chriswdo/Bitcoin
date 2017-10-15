package org.tarena.note.service;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;

public interface NoteService {
	public NoteResult loadNoteById(String noteId);
	
	public NoteResult loadNoteByBookId(String bookId);

	public NoteResult add(Note note);
	
	public NoteResult update(Note note);
	
	public NoteResult updateStatus(String noteId,String statusId);
	
	public NoteResult share(String noteId);
	
	public NoteResult searchShareNote(String title);

}
