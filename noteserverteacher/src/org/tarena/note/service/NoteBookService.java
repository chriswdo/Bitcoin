package org.tarena.note.service;

import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;

public interface NoteBookService {
	public NoteResult loadListByUserId(String userId);

	public NoteResult add(NoteBook book);

}
