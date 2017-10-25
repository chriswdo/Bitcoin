package com.eshore.datasupport.metadata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eshore.datasupport.common.util.DataSourceUtil;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.dao.IXxflDao;
import com.eshore.datasupport.metadata.pojo.Xxfl;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.metadata.service.IXxflService;
import com.eshore.datasupport.metadata.service.IXxzdService;
import com.eshore.datasupport.metadata.service.util.TableOperationUtil;
import com.eshore.datasupport.metadata.vo.MessageVo;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Ԫ��ݱ�ҵ��ӿ�ʵ��
 * 
 * @author
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class XxflServiceImpl extends BaseServiceImpl<Xxfl> implements IXxflService {

	private final Logger logger = Logger.getLogger(XxflServiceImpl.class);

	@Value("#{configProperties['gx.username']}")
	private String userName;
	@Value("#{configProperties['gx.password']}")
	private String password;
	@Value("#{configProperties['gx.driverClassName']}")
	private String drivename;
	@Value("#{configProperties['gx.url']}")
	private String url;
	@Autowired
	IXxflDao xxflDao;

	@Autowired
	ISjybService sjybService;

	@Autowired
	IXxzdService xxzdService;

	@Override
	public IBaseDao<Xxfl> getDao() {
		return (IBaseDao<Xxfl>) xxflDao;
	}

	@Override
	public List<MessageVo> getMessagedata(String zt, String meg, PageConfig pc) {
		List<MessageVo> result = xxflDao.getMessagedata(zt, meg, pc);
		return result;
	}

	@Override
	public List<Map<String, Object>> getZhutidata() {
		List<Map<String, Object>> result = xxflDao.getZhutidata();
		return result;
	}

/*	@Override
	public List<Map<String, Object>> getmessageDataByid(String idd) {
		List<Map<String, Object>> result = xxflDao.getmessageDataByid(idd);
		return result;
	}*/

	@Override
	public List<YsjzdVo> getxxmetadataBymc(String megid, String mc) {
		List<YsjzdVo> result = new ArrayList<YsjzdVo>();
		if (!"xzxx".equals(megid)) {
			result = xxflDao.getxxmetadataBymc(megid, mc);
		}
		return result;
	}

	@Override
	/**
	 * 更新信息分类表
	 */
	public String updatexxflOFdata(String xxid, String xxzt, String xxmc, String xxms, String xxbm, String userid) {
		String res = "ok";
		Date tit = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ti = df.format(tit);
		try {
			xxflDao.updateMegdata(xxzt, xxmc, xxms, xxid, userid, ti);
		} catch (Exception e) {
			logger.info(e);
			res = "fail";
		}

		return res;
	}

	// 得到编辑字段和增加字段的sql语句
	private List<String> getEditAndAddsql(String xxbm, List<Xxzd> zdAddlist, List<Xxzd> zdEditlist) {
		List<String> totalsql = new ArrayList<String>();
		List<String> sqls = null;
		// 得到新增字段信息的sql语句
		for (Xxzd xzd : zdAddlist) {
			sqls = TableOperationUtil.addField(drivename.trim(), xzd, xxbm);
			totalsql.addAll(sqls);
		}
		// 得到编辑字段的sql语句
		for (Xxzd xzd : zdEditlist) {
			sqls = TableOperationUtil.modifyField(drivename.trim(), xzd, xxbm);
			totalsql.addAll(sqls);
		}
		return totalsql;
	}

	// 得到页面新增编辑主键执行的sql语句
	private List<String> getxxmetakeysql(String xxbm, List<String> keynames, String xxms) {
		List<String> sqls = new ArrayList<String>();

		try {
			String sql = TableOperationUtil.searchKey(drivename.trim(), xxbm);
			String usr = DataSourceUtil.getKeyMessage(url.trim(), drivename.trim(), sql, userName, password);
			if (!keynames.isEmpty() && "you".equals(usr)) {
				if(!keynames.contains("keep")){
					sqls = TableOperationUtil.modifyKey(drivename.trim(), keynames, true, xxbm);
				}	
			} else if (keynames.isEmpty() && "you".equals(usr)) {
				sqls.add("alter table " + xxbm + "  drop constraint "
						+ ((xxbm.length() > 20) ? xxbm.substring(0, 20) : xxbm) + "_dsjzc_key cascade");
			} else if (!keynames.isEmpty() && "kong".equals(usr)) {
				if(!keynames.contains("keep")){
				sqls = TableOperationUtil.modifyKey(drivename.trim(), keynames, false, xxbm);
				}
			}

			sqls.add("comment on table " + xxbm + " is '" + xxms.replaceAll("'", "\"") + "'");
		} catch (Exception e) {
			logger.info(e);
		}
		return sqls;

	}

	// 判断更新前字段信息为主键非空,非主键为空的判断
	private List<String> checkZjNotnull(List<Xxzd> editcolumns, String xxid, String xxbm) {
		StringBuffer sql = new StringBuffer();
		List<String> sqls = new ArrayList<String>();
		for (Xxzd xzd : editcolumns) {
			List<YsjzdVo> relist = this.getxxmetadataBymc(xxid, xzd.getFd_zdmc());
			YsjzdVo ysjvo = relist.get(0);
			if (("Y".equals(ysjvo.getSfzj()) && "N".equals(ysjvo.getSfkwk()))
					|| ("N".equals(ysjvo.getSfzj()) && "Y".equals(ysjvo.getSfkwk()))) {
				if ("N".equals(xzd.getFd_sfzj()) && "N".equals(xzd.getFd_sfkwk())) {
					sql.append("alter table ").append(xxbm).append(" modify ").append(ysjvo.getZdmc()).append(" not null");
				} else {
					sql.append("");
				}
			}
			if (!"".equals(sql.toString())) {
				sqls.add(sql.toString());
			}
		}

		return sqls;
	}

	// 判断更新前字段信息为非主键非空的判断
	private List<String> checkNotzjNotnull(List<Xxzd> editcolumns, String xxid, String xxbm) {
		StringBuffer sql = new StringBuffer();
		List<String> sqls = new ArrayList<String>();
		for (Xxzd xzd : editcolumns) {
			List<YsjzdVo> relist = this.getxxmetadataBymc(xxid, xzd.getFd_zdmc());
			YsjzdVo ysjvo = relist.get(0);
			if ("N".equals(ysjvo.getSfzj()) && "N".equals(ysjvo.getSfkwk())) {
				if ("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
					sql.append("alter table ").append(xxbm).append(" modify ").append(ysjvo.getZdmc()).append(" null") ;
				} else {
					sql.append("") ;
				}
			}
			if (!"".equals(sql.toString())) {
				sqls.add(sql.toString());
			}
		}

		return sqls;
	}

	// 添加字段判断数据库空执行语句，得到执行语句
	private List<String> getAddsqlTosfwk(List<Xxzd> addcolumns, String xxbm) {
		List<String> sqls = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		for (Xxzd xzd : addcolumns) {
			if (("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk()))
					|| ("Y".equals(xzd.getFd_sfzj()) && "N".equals(xzd.getFd_sfkwk()))) {
				sql.append("");
			} else if ("Y".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
				sql.append("alter table ").append(xxbm).append(" modify ").append(xzd.getFd_zdmc()).append(" null") ;
			} else {
				sql.append("alter table ").append(xxbm).append(" modify ").append(xzd.getFd_zdmc()).append(" not null");
			}
			if (!"".equals(sql.toString())) {
				sqls.add(sql.toString());
			}
		}
		return sqls;
	}

	//得到是否可为空字段编辑后的远程执行sql语句
	private List<String> getxxmetsfwksql(String xxid, String xxbm, List<Xxzd> addcolumns, List<Xxzd> editcolumns) {
		List<String> sqls = new ArrayList<String>();
		try {
			// 编辑字段判断数据库空执行语句
			if (editcolumns != null) {
				// 判断更新前字段信息为主键非空,非主键为空的判断
				sqls = this.checkZjNotnull(editcolumns, xxid, xxbm);
				// 判断更新前字段信息为非主键非空的判断
				List<String> sqls1 = this.checkNotzjNotnull(editcolumns, xxid, xxbm);
				sqls.addAll(sqls1);

			}
			// 添加字段判断数据库空执行语句，得到执行语句
			List<String> sqls2 = this.getAddsqlTosfwk(addcolumns, xxbm);
			sqls.addAll(sqls2);
		} catch (Exception e) {
			logger.info(e);
		}
		return sqls;
	}

	@Override
	/**
	 * 执行创建远程数据库表格的sql语句
	 */
	public String addxxflMeg(RequestParams params, List<Xxzd> jsonadd, String userid) {
		String xxbm = params.getXxbm();
		String xxms = params.getXxms();
		String xxid=params.getXxid();
		String res = "ok";
		try {
			//得到创建表格的sql
			List<String> sqls = TableOperationUtil.createTable(drivename.trim(), jsonadd, xxbm, xxms);
			//得到需要执行的是否为空的sql语句
			List<String> sqlOfsfwk = this.getxxmetsfwksql(xxid, xxbm, jsonadd, null);
			sqls.addAll(sqlOfsfwk);
			res = xxflDao.editxxmetadata(url.trim(), drivename.trim(), sqls, userName, password);
		} catch (Exception e) {
			logger.info(e);
			res = "fail";
		}

		return res;
	}

	@Override
	public List<Map<String, Object>> getIDfromxxfl(String xxbm) {
		List<Map<String, Object>> result = xxflDao.getIDfromxxfl(xxbm);
		return result;
	}

	@Override
	public boolean findAnyBysjyId(String ztid) {
		List list = xxflDao.findAnyBysjyId(ztid);
		if (!list.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * 通过表名查询
	 */
	public List<Xxfl> getDataBybm(String bm) {
		List<Xxfl> result = xxflDao.getDataBybm(bm);
		return result;
	}

	@Override
	/**
	 * 根据信息分类表的ID得到信息分类字段信息
	 * 
	 * @param megid
	 *            信息ID(当信息ID为空，前台传入信息ID为"xzxx")
	 * @return
	 */
	public List<YsjzdVo> getxxmetadata(String megid) {
		List<YsjzdVo> result = new ArrayList<YsjzdVo>();
		if (!"xzxx".equals(megid)) {
			result = xxflDao.getxxmetadata(megid);
		}
		return result;
	}
    //将添加字段信息封装成pojo类
	private List<Xxzd> addColmns(JSONArray jsonadd, String userid, Date dt, String xxid) {
		List<Xxzd> zdAddlist = new ArrayList<Xxzd>();
		for (int i = 0; i < jsonadd.size(); i++) {
			JSONObject job = jsonadd.getJSONObject(i); // 遍历 jsonarray
			Short zdsx = Short.valueOf(job.getString("FD_ZDSX"));
			Xxzd xzd = new Xxzd();
			String uuid = UUID.randomUUID().toString();
			xzd.setFd_cjr(userid);
			xzd.setFd_cjsj(dt);
			xzd.setFd_gxr(userid);
			xzd.setFd_gxsj(dt);
			xzd.setFd_lx("0");
			xzd.setFd_qsz(job.getString("FD_QSZ"));
			xzd.setFd_sfkwk(job.getString("FD_SFKWK"));
			xzd.setFd_sfzj(job.getString("FD_SFZJ"));
			xzd.setFd_xx_id(xxid);
			xzd.setFd_zdbz(job.getString("FD_ZDBZ"));
			if ("VARCHAR".equals(job.getString("FD_ZDLX")) || "NUMBER".equals(job.getString("FD_ZDLX"))) {
				xzd.setFd_zdcd(Short.valueOf(job.getString("FD_ZDCD")));
			} else if ("NUMERIC".equals(job.getString("FD_ZDLX"))) {
				xzd.setFd_zdcd(Short.valueOf(job.getString("FD_ZDCD")));
				xzd.setFd_zdjd(Short.valueOf(job.getString("FD_ZDJD")));
			}
			xzd.setFd_zdlx(job.getString("FD_ZDLX"));
			xzd.setFd_zdmc(job.getString("FD_ZDMC"));
			xzd.setId(uuid);
			xzd.setFd_zdsx(zdsx);
			zdAddlist.add(xzd);
		}
		return zdAddlist;
	}

	 //得到用户编辑后的主键list
    private  List<String>  getKeySql(List<YsjzdVo> pkey,List<String> keys,List<Xxzd> zdEdtlist){
        
		for (Xxzd xzd : zdEdtlist) {
			if (pkey.isEmpty() && "Y".equalsIgnoreCase(xzd.getFd_sfzj())) {
				keys.add(xzd.getFd_zdmc());
			}
		    if (!keys.contains(xzd.getFd_zdmc()) && "Y".equalsIgnoreCase(xzd.getFd_sfzj())) {
					keys.add(xzd.getFd_zdmc());
				}
			for (YsjzdVo obj : pkey) {
				if (obj.getZdmc().equals(xzd.getFd_zdmc()) && "N".equalsIgnoreCase(xzd.getFd_sfzj())) {
					keys.remove(obj.getZdmc());
				}
			}

		}
		return keys;
    }
    
	// 得到用户编辑和新增后的主键list
	private List<String> getEditkeyList(List<Xxzd> zdEdtlist, List<Xxzd> zdAddlist, List<YsjzdVo> pkey) {
		List<String> keys = new ArrayList<String>();
		List<String> keycomp = new ArrayList<String>();
		//得到原表的主键list
		for (YsjzdVo obj : pkey) {
			keys.add(obj.getZdmc());
			keycomp.add(obj.getZdmc());
		}
		 //得到用户编辑后的主键list
        List<String> editkeys=this.getKeySql(pkey, keys, zdEdtlist);
        
		// 得到新增后的整合的主键list
		for (Xxzd xzd : zdAddlist) {
			if ("Y".equalsIgnoreCase(xzd.getFd_sfzj())) {
				editkeys.add(xzd.getFd_zdmc());
			}
		}
		//判断是否有修改主键信息
	      if(DataSourceUtil.equalList(editkeys, keycomp)){
	    	  editkeys=new ArrayList<String>();
	    	  editkeys.add("keep");
	        }
		return editkeys;
	}
	 //将编辑字段信息封装成pojo类
	private List<Xxzd> editColmns(JSONArray jsonedit, String userid, Date dt, String xxid) {
		List<Xxzd> zdEdtlist = new ArrayList<Xxzd>();
		for (int i = 0; i < jsonedit.size(); i++) {
			JSONObject job = jsonedit.getJSONObject(i); // 遍历 jsonarray
			String xid = job.getString("XID");
			Xxzd xzd = xxzdService.get(xid);
			xzd.setFd_sfkwk(job.getString("FD_SFKWK"));
			xzd.setFd_sfzj(job.getString("FD_SFZJ"));
			xzd.setFd_qsz(job.getString("FD_QSZ"));
			xzd.setFd_xx_id(xxid);
			xzd.setFd_zdbz(job.getString("FD_ZDBZ"));
			xzd.setFd_gxr(userid);
			xzd.setFd_gxsj(dt);
			if ("VARCHAR".equals(job.getString("FD_ZDLX")) || "NUMBER".equals(job.getString("FD_ZDLX"))) {
				xzd.setFd_zdcd(Short.valueOf(job.getString("FD_ZDCD")));
			} else if ("NUMERIC".equals(job.getString("FD_ZDLX"))) {
				xzd.setFd_zdcd(Short.valueOf(job.getString("FD_ZDCD")));
				xzd.setFd_zdjd(Short.valueOf(job.getString("FD_ZDJD")));
			}
			zdEdtlist.add(xzd);
		}
		return zdEdtlist;
	}

	@Override
	/**
	 * 执行远程修改表信息的方法
	 * 
	 * @param xxid
	 *            信息ID
	 * @param xxbm
	 *            信息表名
	 * @param xxms
	 *            信息描述
	 * @param addcolumns
	 *            页面新增的字段list
	 * @param editcolumns
	 *            页面编辑的字段list
	 * @return
	 */
	public String createTableOrcolumns(String xxid, String xxbm, String xxms,List<Xxzd> addcolumns,
			List<Xxzd> editcolumns) {
		String res = "ok";
		try {
			// 拿到修改前，表的主键list
			List<YsjzdVo> pkey = new ArrayList<YsjzdVo>();
			List<YsjzdVo> melist = this.getxxmetadata(xxid);
			for (YsjzdVo map : melist) {
				if ("Y".equals(map.getSfzj())) {
					pkey.add(map);
				}
			}
			List<String> sqls;
			//得到编辑和新增的字段信息sql语句
			sqls = this.getEditAndAddsql(xxbm, addcolumns, editcolumns);
			//得到需要更新主键信息的字段名称list
			List<String> keystr=this.getEditkeyList(editcolumns, addcolumns, pkey);
			//得到需要执行的主键sql语句
			List<String> sqlOfkeys = this.getxxmetakeysql(xxbm, keystr, xxms);
			sqls.addAll(sqlOfkeys);
			//得到需要执行的是否为空的sql语句
			List<String> sqlOfsfwk = this.getxxmetsfwksql(xxid, xxbm, addcolumns, editcolumns);
			sqls.addAll(sqlOfsfwk);
			//调用远程执行sql的方法
			res = xxflDao.editxxmetadata(url.trim(), drivename.trim(), sqls, userName, password);
		} catch (Exception e) {
			res = "fail";
			logger.info(e);
		}
		return res;

	}

	@Override
	/**
	 * 更新信息分类远程库表和本地库表的信息
	 */
	public String updateMesagedata(RequestParams params, String userid) {
		String res = "";
		Date dt = new Date();
		String xxid = params.getXxid();
		String editlist = params.getEditlist();
		String addlist = params.getAddlist();
		String xxbm = params.getXxbm();
		String xxms = params.getXxms();
		String xxzt = params.getXxzt();
		String xxmc = params.getXxmc();
		try {
			JSONArray jsonedit = JSONArray.fromObject(editlist);
			JSONArray jsonadd = JSONArray.fromObject(addlist);
			List<Xxzd> editcolumns = this.editColmns(jsonedit, userid, dt, xxid);
			List<Xxzd> addcolumns = this.addColmns(jsonadd, userid, dt, xxid);
			//调用远程执行字段信息修改的sql语句方法，返回执行结果
			res= this.createTableOrcolumns(xxid, xxbm, xxms,addcolumns, editcolumns);
			if (!"ok".equals(res)) {
				return res;
			}
			
			// 创建本地数据库字段
			for (Xxzd xz : addcolumns) {
				xxzdService.save(xz);
			}

			// 修改本地数据库字段
			for (Xxzd xz : editcolumns) {
				xxzdService.update(xz);
			}
			// 更新本地信息分类表
			res = this.updatexxflOFdata(xxid, xxzt, xxmc, xxms, xxbm, userid);
		} catch (Exception e) {
			logger.info(e);
			res = "fail";
		}

		return res;
	}

	@Override
	/**
	 * 添加信息分类远程库表和本地库表的信息
	 */
	public String addMesagedata(RequestParams params, String userid) {
		String res = "";
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ti = df.format(dt);
		String addlist = params.getAddlist();
		String xxbm = params.getXxbm();
		JSONArray jsonadd = JSONArray.fromObject(addlist);
		String xxuuid = "";
		try {
			List<Xxfl> re = this.getDataBybm(xxbm);
			xxuuid = UUID.randomUUID().toString();
			params.setXxid(xxuuid);
			List<Xxzd> addcolumns = addColmns(jsonadd, userid, dt, xxuuid);
			// //调用远程创建数据库表的sql语句方法，返回执行结果
			if (re == null || re.isEmpty()) {
				res = this.addxxflMeg(params, addcolumns, userid);
				if (!"ok".equals(res)) {
					return res;
				}
			} else {
				res = "repeat";
				return res;
			}
			//添加本地信息分类表数据
			xxflDao.addxxflMeg(params, ti, userid, userid);
			// 添加字段信息到本地信息字段表
			for (Xxzd xz : addcolumns) {
				xxzdService.save(xz);
			}
		} catch (Exception e) {
			logger.info(e);
			res = "fail";
		}
		return res;
	}

	@Override
	/**
	 * 通过信息ID得到信息字段表的对应信息表的字段
	 */
	public List<Map<String, Object>> getmetaDatainpage(String megid) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (!"xzxx".equals(megid)) {
			result = xxflDao.getmetaDatainpage(megid);
		}
		return result;
	}

}
