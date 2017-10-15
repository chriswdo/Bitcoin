package org.tarena.note.service;

import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;

public interface UserService {
	public NoteResult checkLogin(String name,String password);
	public NoteResult checkLogin(String base64Msg);
	public NoteResult regist(User user);
}
