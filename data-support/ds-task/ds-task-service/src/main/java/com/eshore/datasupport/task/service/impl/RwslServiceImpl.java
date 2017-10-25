package  com.eshore.datasupport.task.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.eshore.datasupport.task.service.IRwslService;
import com.eshore.datasupport.task.vo.ExceptionRecordVo;
import com.eshore.datasupport.task.vo.YsjbVo;
import  com.eshore.datasupport.task.pojo.Rwsl;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.task.dao.IGzpzDao;
import com.eshore.datasupport.task.dao.IRwslDao;

/**
 *  
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class RwslServiceImpl extends BaseServiceImpl<Rwsl> implements IRwslService {
   
	private String utfde="UTF-8";
	private static final Logger logger = Logger.getLogger(RwslServiceImpl.class);
	
	@Autowired ISjybService sjybService;
	
	@Autowired
	IRwslDao rwslDao;

	@Autowired
	IGzpzDao gzpzDao;
	@Override
	public IBaseDao<Rwsl> getDao() {
		return (IBaseDao<Rwsl>)rwslDao;
	}
	@Override
	public Rwsl findLastRwsl(String dsJobId,Date sjc){
		return rwslDao.findLastRwsl(dsJobId, sjc);
	}
	@Override
	public void saveRWSL(Rwsl rwsl){
		rwslDao.saveRWSL(rwsl);
	}
	@Override
	/**
	 * 得到日志监控数据
	 * @param gzm  规则名称
	 * @param pc   分页
	 * @param lx  规则类型
	 * @return
	 */
	public List<Map<String, Object>> getRulemonitordata(String gzm,PageConfig pc,int lx) {
		List<Map<String, Object>>  res=rwslDao.getRulemonitordata(gzm,pc,lx);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String datacout="";
		String runti="";
		for (Map<String, Object> map : res) {
			//Long ss=new Date().getTime();
			//System.out.println("start::="+ss);
			BigDecimal all= new BigDecimal(map.get("count").toString());
			Map  ma=rwslDao.getWorkcount(map.get("ID").toString());
			List<Map<String, Object>> srlist=(List<Map<String, Object>>) ma.get("suclist");
			List<Map<String, Object>> tilist=(List<Map<String, Object>>) ma.get("tilist");
			PageConfig pg=new PageConfig();
			pg.setPageSize(Integer.MAX_VALUE);
			//List<Rwsl>  li=rwslDao.getRuledetail(map.get("ID").toString(),pg);
			//List<Map<String, Object>> li=rwslDao.getRuledetailtime(map.get("ID").toString());
			BigDecimal success= new BigDecimal(srlist.get(0).get("oktime").toString());
			if(map.get("SUMCOUNT")==null){
				datacout="0";
			}else{
				datacout=map.get("SUMCOUNT").toString();
			}
			if(tilist.isEmpty()){
				runti="";
				 all= new BigDecimal("0");
			}else{
				runti=sdf.format(tilist.get(0).get("FD_QDSJ"));
			}
			
			BigDecimal fail = all.subtract(success);  
			map.put("success", success);
			map.put("all",all );
			map.put("fail", fail);
			map.put("datacount", datacout);
			map.put("retime",runti );
			//System.out.println("end::="+(new Date().getTime()-ss));
		}
		return res;
	}
	@Override
	/**
	 * 得到任务实例表的数据
	 * @param idd
	 * @param pc
	 * @return
	 */
	public List<Map<String, Object>> getRuledetail(String idd,PageConfig pc) {
		List<Rwsl>  detailsli=rwslDao.getRuledetail(idd,pc);
		List<Map<String, Object>> res=new ArrayList<Map<String, Object>>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		for (Rwsl rs : detailsli) {
			Map<String, Object> map =new HashMap<String, Object>();
			if(1==rs.getFd_zxjg()){
				map.put("FD_ZXJG", "成功"); 
			}else if(2==rs.getFd_zxjg()){
				map.put("FD_ZXJG", "中止"); 
			}else{
				map.put("FD_ZXJG", "失败"); 
			}	        
	        String str="";			
				try {
					byte[] obj = (byte[])rs.getFd_bzxx();
					str = new String(obj, utfde);
				} catch (UnsupportedEncodingException e) {
				   logger.info(e);
				}
			map.put("FD_QDSJ", sdf.format(rs.getFd_qdsj()));
			map.put("FD_JSSJ", sdf.format(rs.getFd_jssj()));
			map.put("FD_YXCS", rs.getFd_yxcs());
			map.put("FD_CLJLS", rs.getFd_cljls());
			map.put("ID", rs.getId());
			map.put("bzxx", str);
			//map.put("ruleObj", rs);
			res.add(map);
		}
		return res;
	}
	@Override
	/**
	 * 得到异常记录表的数据
	 * @param idd
	 * @param pc
	 * @return
	 */
	public List<ExceptionRecordVo> getYcjudetail(String idd, PageConfig pc) {
		List<ExceptionRecordVo>  res=rwslDao.getYcjudetail(idd,pc);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		for (ExceptionRecordVo map : res) {
			map.setFd_jssjTostring(sdf.format(map.getFd_jssj()));
			Sjyb sry=sjybService.get(map.getFd_srsjy_id());
					//gzpzDao.getsjyName(map.getFd_srsjy_id(),map.getFd_srb_id()); 
			map.setFd_srsjy_name(sry.getFd_mc());
		}
		return res;
	}
	
/*	@Override
	public List<Map<String, Object>> getYcjuofycsj(String idd) {
		List<Map<String, Object>>  res=rwslDao.getYcjuofycsj(idd);
		List<Map<String, Object>>  rename=rwslDao.getYcjuname(res.get(0).get("FD_SRB_ID").toString());
		for (Map<String, Object> map : res) {
			map.put("srsjy", rename.get(0).get("FD_MC"));
			map.put("srb", rename.get(0).get("FD_BM"));
		}
		return res;
	}*/
	@Override
	public List<Map<String, Object>> ajaxRwslStatistics(PageConfig pc) {
		 List<Map<String, Object>> list = rwslDao.ajaxRwslStatistics(pc);
		 return list;
	}
	@Override
	public void ajaxHomeRwslStatisticForEchart(Map<String, Object> retMap) {
		List<Map<String, Object>> list = rwslDao.ajaxHomeRwslStatisticForEchart();
		List<String>xAxis_data = new ArrayList<String>();
		List<BigDecimal>series_cl_data = new ArrayList<BigDecimal>();
		List<BigDecimal>series_yc_data = new ArrayList<BigDecimal>();
		List<BigDecimal>series_persent_data = new ArrayList<BigDecimal>();
		if(list==null){
			list = new ArrayList<Map<String, Object>>();
		}
		for(Map<String,Object>map : list){
			//添加
			xAxis_data.add((String)map.get("FD_MC"));
			BigDecimal cl_temp = (BigDecimal) (map.get("FD_CLJLS")==null?new BigDecimal(0):map.get("FD_CLJLS"));
			BigDecimal yc_temp = (BigDecimal) (map.get("FD_YCJLS")==null?new BigDecimal(0):map.get("FD_YCJLS"));
			series_cl_data.add(cl_temp);
			series_yc_data.add(yc_temp);
			//计算百分比
			if(cl_temp.add(yc_temp).compareTo(new BigDecimal(0))==0){
				series_persent_data.add(new BigDecimal(0));
			}else{
				BigDecimal persent = yc_temp.multiply(new BigDecimal(100)).divide(cl_temp.add(yc_temp),2,RoundingMode.HALF_UP);
				series_persent_data.add(persent);
			}

		}
		retMap.put("xAxis_data", xAxis_data);
		//处理记录数
		Map <String ,Object> cl_map = new HashMap<String,Object>();
		cl_map.put("name", "处理记录数");
		cl_map.put("type", "bar");
		cl_map.put("data", series_cl_data);
		retMap.put("series_cl_data", cl_map);
		//异常记录数
		Map <String ,Object> yc_map = new HashMap<String,Object>();
		yc_map.put("name", "异常记录数");
		yc_map.put("type", "bar");
		yc_map.put("data", series_yc_data);
		retMap.put("series_yc_data", yc_map);
		//异常记录占比
		Map <String ,Object> per_map = new HashMap<String,Object>();
		per_map.put("name", "异常记录数占比");
		per_map.put("type", "line");
		per_map.put("yAxisIndex", 1);
		per_map.put("data", series_persent_data);
		retMap.put("series_persent_data", per_map);
		//记录数最大值
		if(list!=null && list.isEmpty()){
			retMap.put("cl_max", 0);
		}else{
			BigDecimal cl_max = (BigDecimal) (list.get(0).get("FD_CLJLS")==null?new BigDecimal(0):list.get(0).get("FD_CLJLS"));
			retMap.put("cl_max", cl_max);
		}

		
	}
}
