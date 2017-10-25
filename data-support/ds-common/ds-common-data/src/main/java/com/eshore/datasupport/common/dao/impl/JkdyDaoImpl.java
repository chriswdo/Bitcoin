package com.eshore.datasupport.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math.stat.descriptive.rank.Max;
import org.springframework.stereotype.Repository;

import com.eshore.datasupport.common.dao.IGjDao;
import com.eshore.datasupport.common.dao.IJkdyDao;
import com.eshore.datasupport.common.pojo.Gj;
import com.eshore.datasupport.common.pojo.Jkdy;
import com.eshore.datasupport.common.vo.JkdyVo;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class JkdyDaoImpl extends JpaDaoImpl<Jkdy> implements IJkdyDao{

	@Override
	public List<JkdyVo> getInterfaceMeg(String jkname, PageConfig pc) {
		StringBuilder  stb=new StringBuilder();
		List<Object> params_sql = new ArrayList<Object>();
		stb.append("select j.id,j.jk_code,j.jk_name,j.sjy_id,j.sql_info,j.keywords,j.jk_zt,j.fd_gxr,j.fd_jkbz,j.fd_gxsj,s.fd_mc,s.fd_sjklx  from dsjzc_jkdy j , dsjzc_sjyb s  where j.sjy_id=s.id");
		if(StringUtils.isNotBlank(jkname)){
			stb.append("  AND j.JK_NAME like ?");
			params_sql.add("%"+jkname+"%");
		}
		return this.iSQLQuery.query(stb.toString(), params_sql.toArray(), JkdyVo.class, pc);
	}

	@Override
	public List<Jkdy> getDataBynamecode(String code) {
        String sql = " select * from  dsjzc_jkdy   where jk_code=? ";
		PageConfig pc=new PageConfig();
		pc.setPageSize(Integer.MAX_VALUE);
        List<Jkdy> relist =this.iSQLQuery.query(sql,new Object[]{code},Jkdy.class,pc);
        return relist;
	}


	
}