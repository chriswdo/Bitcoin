package org.tarena.note.dao;

import java.util.List;

import org.tarena.note.entity.Share;

public interface ShareMapperDao {
	public void save(Share share);
	public List<Share> search(String title);
}
