package com.eshore.datasupport.common.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IGjsjDao;
import com.eshore.datasupport.common.pojo.Gjsj;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class GjsjDaoImpl extends JpaDaoImpl<Gjsj> implements IGjsjDao{

	@Override
	public List<Map<String, String>> ajaxHomeGjInfo(PageConfig pc) {
		String sql ="	SELECT sjy.fd_sjkmc,sjb.fd_bm, to_char(sj.fd_gjsj,'yyyy-mm-dd hh24:mi:ss') fd_gjsj ,sj.fd_gjnr  ,sj.fd_gjbz 	"+ 
					"	FROM dsjzc_gjsj sj	"+
					"	JOIN DSJZC_RWB rw ON sj.fd_rwid=rw.id  AND sj.fd_gjsj >= (sysdate - 1/48) 	"+
					"	JOIN DSJZC_GZPZ gz ON rw.fd_gz_id = gz.id 	"+
					"	JOIN DSJZC_SJYB sjy ON sjy.id = gz.fd_srsjy_id	"+
					"	JOIN DSJZC_YSJB sjb ON sjb.id = gz.fd_srb_id	"+
					"	ORDER BY sj.fd_gjsj DESC	";
		return this.iSQLQuery.query(sql, pc);
	}

}