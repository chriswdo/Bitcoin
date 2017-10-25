package  com.eshore.datasupport.metadata.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.eshore.datasupport.metadata.vo.DataSourceTs;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.dao.IYsjbDao;
import com.eshore.datasupport.metadata.dao.IYsjzdDao;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import  com.eshore.datasupport.metadata.pojo.Ysjzd;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.metadata.service.IYsjbService;
import  com.eshore.datasupport.metadata.service.IYsjzdService;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * Ԫ����ֶ�ҵ��ӿ�ʵ��
 * 
 * @author 
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class YsjzdServiceImpl extends BaseServiceImpl<Ysjzd> implements IYsjzdService {

	private  final Logger logger = Logger.getLogger(YsjzdServiceImpl.class);
	@Autowired
	IYsjzdDao ysjzdDao;

	@Autowired ISjybService sjybService;
	
	@Autowired IYsjbDao ysjbDao;
	
	@Autowired IYsjbService ysjbService;
	
	@Override
	public IBaseDao<Ysjzd> getDao() {
		return (IBaseDao<Ysjzd>)ysjzdDao;
	}
	
	@Override
	/**
	 * 查询元数据字段字段信息
	 * @param parm1  字段ID
	 * @param parm2 表ID
	 * @return
	 */
	public List<Map<String, Object>> editysjzdData(String parm1, String parm2) {
		List<Map<String, Object>> resdata=ysjzdDao.editysjzdData(parm1,parm2);
		for (Map<String, Object> map : resdata) {
			 if(map.get("FD_QSZ")==null){
				 map.put("FD_QSZ", "");
			 }
			 if(map.get("FD_ZDBZ")==null){
				 map.put("FD_ZDBZ", "");
			 }
		}
		return resdata;
	}

	@Override
	public int saveysjzdData(String pa1, String pa2) {
		return 0;
	}
	
	@Override
	/**
	 * 通过表名得到元数据字段数据
	 * @param bm  表名
	 * @return
	 */
	public List<Ysjzd> getysjzdData(String bm) {
		List<Ysjzd> resultrow=ysjzdDao.getysjzdData(bm);
		return resultrow;
	}

	 public String synBydataCompare(){
		 return "";
	 }
	@Override
	/**
	 *  得到远程数据表字段信息
	 * @param sjy  数据源
	 * @param bm   表名
	 * @return
	 */
	public List<YsjzdVo> getDatatosyn(String sjy, String bm) {
        Sjyb sjyb=sjybService.get(sjy);
        String url=DataSourceUtil.getUrl(sjyb.getFd_sjklx(),sjyb.getFd_ip(),sjyb.getFd_dk(),sjyb.getFd_sjkmc());
        String drivename=DataSourceUtil.getDriverName(sjyb.getFd_sjklx());
        List<YsjzdVo> res=ysjzdDao.getdatayuanByid(drivename,url,sjyb,bm);
          return res;
	}


	@Override
	public List<YsjzdVo> getysjzdDataBybm(String id, String bm,String sjy) {
		List<YsjzdVo> res=ysjzdDao.getysjzdDataBybm(id,bm,sjy);
		return res;
	}

	@Override
	public List<DataSourceTs> getDataSourceTs(String id) {
		return ysjzdDao.getDataSourceTs(id);
	}

	//缺省值同步判断
	 private String colmsCompareQsz(YsjzdVo syn,YsjzdVo objdata){
			if (!syn.getQsz().equals(objdata.getQsz())) {
				return syn.getQsz();						
		   }
		return objdata.getQsz();		 
	 }
	 /**
	  * 字段备注同步判断
	  * @param synColumns 远程数据库字段信息
	  * @param objdata 本地数据库字段信息
	  * @return
	  */
	 private String colmsCompareBz(YsjzdVo synColumns,YsjzdVo  objdata){
			if (!synColumns.getZdbz().equals(objdata.getZdbz())) {
				return synColumns.getZdbz();						
		   }
		return objdata.getZdbz();		 
	 }

	
	 /**
	  * 字段是否可为空同步判断
	  * @param synColumns 远程数据库字段信息
	  * @param objdata 本地数据库字段信息
	  * @return
	  */
	 private String colmsCompareSfkwk(YsjzdVo synColumns,YsjzdVo objdata){
			if (!synColumns.getSfkwk().equals(objdata.getSfkwk())) {
				return synColumns.getSfkwk();								
		   }
		return objdata.getSfkwk();		 
	 }
	 
	 /**
	  * 字段是否可主键同步判断
	  * @param synColumns 远程数据库字段信息
	  * @param objdata 本地数据库字段信息
	  * @return
	  */
	 private String colmsCompareSfzj(YsjzdVo synColumns,YsjzdVo objdata){
			if (!synColumns.getSfzj().equals(objdata.getSfzj())) {
				return synColumns.getSfzj();							
		   }
		return objdata.getSfzj();		 
	 }
	 
		
	 /**
	  * 字段长度同步判断	
	  * @param synColumns 远程数据库字段信息
	  * @param objdata 本地数据库字段信息
	  * @return
	  */
	 private Short colmsCompareCd(YsjzdVo synColumns,YsjzdVo objdata){
			if((synColumns.getZdcd()-objdata.getZdcd())!=0){
				 return synColumns.getZdcd();
			}

		return objdata.getZdcd();		 
	 }
	 
	 /**
	  * 字段精度度同步判断	
	  * @param synColumns 远程数据库字段信息
	  * @param objdata 本地数据库字段信息
	  * @return
	  */
	 private Short colmsCompareJd(YsjzdVo synColumns,YsjzdVo objdata){
			if((synColumns.getZdjd()-objdata.getZdjd())!=0){
				 return synColumns.getZdjd();
			}

		return objdata.getZdjd();		 
	 }	 
		
	 /**
	  * 字段类型同步判断	
	  * @param synColumns 远程数据库字段信息
	  * @param objdata 本地数据库字段信息
	  * @return
	  */
	 private String colmsCompareLx(YsjzdVo synColumns,YsjzdVo objdata){			
			if (!synColumns.getZdlx().equals(objdata.getZdlx())) {
				return synColumns.getZdlx();	
			}
		return objdata.getZdlx();		 
	 }
	 
/**
 *远程数据库字段信息与本地数据库字段信息判断
 * @param synColumns 远程书库库总字段信息
 * @param id  源数据表ID
 * @param userid  用户ID
 * @param sjy  数据源ID
 * @param tim
 */
	private void synCheckDa(YsjzdVo synColumns,String id,String userid,String sjy,Date tim){
		String sfkwk=synColumns.getSfkwk();
		List<YsjzdVo>  tesre = this.getysjzdDataBybm(id, synColumns.getZdmc(),sjy);
		if (tesre.isEmpty()) {
			Ysjzd sjyb = new Ysjzd();
			String suuid = UUID.randomUUID().toString();
			sjyb.setId(suuid);
			sjyb.setFd_zdmc(synColumns.getZdmc());
			sjyb.setFd_zdlx((String) synColumns.getZdlx());
			sjyb.setFd_zdcd(synColumns.getZdcd());
			sjyb.setFd_zdjd(synColumns.getZdjd());
			sjyb.setFd_sfkwk(sfkwk);
			sjyb.setFd_sfzj(synColumns.getSfzj());
			sjyb.setFd_zdbz(synColumns.getZdbz());
			sjyb.setFd_cjr(userid);
			sjyb.setFd_cjsj(tim);
			sjyb.setFd_ysjb_id(id);
			sjyb.setFd_sjzd("");
			sjyb.setFd_qsz(synColumns.getQsz());
			sjyb.setFd_zdsx((Short) synColumns.getZdsx());
			this.save(sjyb);
		} else {
			YsjzdVo objdata=tesre.get(0);
			Ysjzd sjyb = this.get(objdata.getId());
             Short zdcd=colmsCompareCd(synColumns, objdata);
             Short zdjd=colmsCompareJd(synColumns, objdata);
             String zdlx=colmsCompareLx(synColumns, objdata);
             String zdqsz=colmsCompareQsz(synColumns, objdata);
             String colbz=colmsCompareBz(synColumns, objdata);
             String colzj=colmsCompareSfzj(synColumns, objdata);
             String colwk=colmsCompareSfkwk(synColumns, objdata);
             sjyb.setFd_zdbz(colbz);
             sjyb.setFd_sfkwk(colwk);
             sjyb.setFd_qsz(zdqsz);
             sjyb.setFd_sfzj(colzj);
             sjyb.setFd_zdcd(zdcd);
             sjyb.setFd_zdjd(zdjd);
             sjyb.setFd_zdlx(zdlx);
			sjyb.setFd_gxr(userid); 
			sjyb.setFd_gxsj(tim);
			this.update(sjyb);
		}
	}
	@Override
	/**
	 * 同步远程表字段信息到本地库表
	 * @param opt  数据源ID
	 * @param synbm  表名
	 * @param userid  用户ID
	 * @param bms    表描述
	 * @return
	 */
	public String synymetaData(String sjy, String synbm,  String userid,String bms) {
		Date tim=new Date();
		String res="";
		try {
			//通过jdbc得到远程数据库表字段信息
			List<YsjzdVo> synColumnslist = this.getDatatosyn(sjy, synbm);
			// 根据表名找到对应表名id
			List<Ysjb> messa = ysjbDao.getysjbidByname(synbm,sjy);
			String suuid = UUID.randomUUID().toString();
			if(messa.isEmpty()){
				Ysjb  ysjb = new Ysjb();
				ysjb.setFd_bm(synbm);
				ysjb.setFd_cjr(userid);
				ysjb.setFd_cjsj(tim);
				ysjb.setFd_ms(bms);
				ysjb.setFd_sjy_id(sjy);
				ysjb.setFd_tbms("");
				ysjb.setFd_tbr("");
				ysjb.setFd_tbsj(tim);
				ysjb.setId(suuid);
				ysjbService.save(ysjb);
			} else{
				suuid=messa.get(0).getId();
			}
			//循环远程数据库字段信息同步到本地数据库
				for (YsjzdVo synColumns : synColumnslist) {
					synCheckDa(synColumns, suuid, userid, sjy, tim);
				}			
			res="ok";
		} catch (Exception e) {
			logger.info(e+"===同步操作失败");
			res="fail";
		}			
		return res;
	}
}
