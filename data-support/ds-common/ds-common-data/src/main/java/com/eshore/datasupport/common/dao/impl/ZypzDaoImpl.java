package com.eshore.datasupport.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IZypzDao;
import com.eshore.datasupport.common.pojo.Zypz;

import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class ZypzDaoImpl extends JpaDaoImpl<Zypz> implements IZypzDao{

	@Override
	public List<Zypz> getChildrenZypz(String fd_fjid) {
		String hql = "FROM Zypz WHERE FD_FJID = ? ";
		return this.query(hql, new Object[]{fd_fjid});
	}
}