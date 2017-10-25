package  com.eshore.datasupport.metadata.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.common.vo.YsjbDescribeVo;
import com.eshore.datasupport.metadata.dao.IYsjbDao;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import  com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.service.ISjybService;
import  com.eshore.datasupport.metadata.service.IYsjbService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * Ԫ��ݱ�ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class YsjbServiceImpl extends BaseServiceImpl<Ysjb> implements IYsjbService {

	@Autowired
	IYsjbDao ysjbDao;
	
	@Autowired ISjybService sjybService;

	@Override
	public IBaseDao<Ysjb> getDao() {
		return (IBaseDao<Ysjb>)ysjbDao;
	}

	@Override
	/**
	 * 根据数据源ID查元数据表
	 * @param sjy  数据源ID
	 * @param pc   分页
	 * @return
	 */
	public List<Ysjb> getMetedataTable(String sjy,PageConfig pc) {
		 List<Ysjb> resultrow=ysjbDao.getmetedataTable(sjy,pc);	 
/*     for (Ysjb map : resultrow) {
    	 List<YsjbDescribeVo> da= getbiaoSql(map.getFd_bm().toString(),sjy);
    	 if(!da.isEmpty() && !"".equals(da.get(0).getTableDescribe())){
    		 map.setFd_cjr(da.get(0).getTableDescribe());
    	 }else{
    		 map.setFd_cjr("");
    	 }
    	 
      }*/
		return resultrow ;
	}
	@Override
	/**
	 * 得到远程表描述信息
	 * @param bm   表名
	 * @param sjy  数据源
	 * @return
	 */
	public  List<YsjbDescribeVo> getbiaoSql(String bm,String sjy){
        Sjyb sjyb=sjybService.get(sjy);
        String url=DataSourceUtil.getUrl(sjyb.getFd_sjklx(),sjyb.getFd_ip(),sjyb.getFd_dk(),sjyb.getFd_sjkmc());
        String drivename=DataSourceUtil.getDriverName(sjyb.getFd_sjklx());
		 List<YsjbDescribeVo>  res=ysjbDao.getbiaoSql(drivename,url,sjyb,bm);
		return res;
	}
/*	@Override
	*//**
	 * 得到数据源list
	 * @return
	 *//*
	public List<Map<String, Object>> getDatayuan() {
		List<Map<String, Object>> resultrow=ysjbDao.getDatayuan();
		return resultrow;
	}*/

	@Override
	public boolean findAnyBysjyId(String sjy_id) {
		List list = ysjbDao.findAnyBysjyId(sjy_id);
		if(!list.isEmpty()){
            return true;
        }
		return false;
	}

	@Override
	public String getTableComment(String fd_sjy_id, String bm) {
		return ysjbDao.getTableComment(fd_sjy_id,bm);
	}

	@Override
	public String getTableId(String fd_bm, String fd_ms) {
		return ysjbDao.getTableId(fd_bm, fd_ms);
	}


}
