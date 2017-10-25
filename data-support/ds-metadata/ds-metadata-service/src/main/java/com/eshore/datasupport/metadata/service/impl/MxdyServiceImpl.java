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
import com.eshore.datasupport.metadata.dao.IMxdyDao;
import com.eshore.datasupport.metadata.pojo.Jcmxdy;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import com.eshore.datasupport.metadata.service.IMxdyService;
import com.eshore.datasupport.metadata.service.IXxzdService;
import com.eshore.datasupport.metadata.service.util.TableOperationUtil;
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
public class MxdyServiceImpl extends BaseServiceImpl<Jcmxdy> implements IMxdyService {

    private final Logger logger = Logger.getLogger(MxdyServiceImpl.class);

    @Value("#{configProperties['js.username']}")
    private String userName;
    @Value("#{configProperties['js.password']}")
    private String password;
    @Value("#{configProperties['js.driverClassName']}")
    private String drivename;
    @Value("#{configProperties['js.url']}")
    private String url;

    @Value("#{configProperties['ck.username']}")
    private String ckuserName;
    @Value("#{configProperties['ck.password']}")
    private String ckpassword;
    @Value("#{configProperties['ck.driverClassName']}")
    private String ckdrivename;
    @Value("#{configProperties['ck.url']}")
    private String ckurl;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDrivename() {
        return drivename;
    }

    public String getUrl() {
        return url;
    }

    public String getCkuserName() {
        return ckuserName;
    }

    public String getCkpassword() {
        return ckpassword;
    }

    public String getCkdrivename() {
        return ckdrivename;
    }

    public String getCkurl() {
        return ckurl;
    }

    @Autowired
    IMxdyDao mxdyDao;

    @Autowired
    IXxzdService xxzdService;


    @Override
    public IBaseDao<Jcmxdy> getDao() {
        return (IBaseDao<Jcmxdy>) mxdyDao;
    }


    @Override
    /**
     * 得到模型定义的信息
     * @param zt 数据库ID
     * @param meg 信息ID
     * @param pc 分页类
     * @return
     * 
     */
    public List<Jcmxdy> getmxdyMessagedata(String zt, String meg, PageConfig pc) {
    	List<Jcmxdy> result = mxdyDao.getmxdyMessagedata(zt, meg, pc);
        return result;
    }


    @Override
    /**
     * 得到主题list
     * @return
     */
    public List<Map<String, Object>> getZhutidata() {
        List<Map<String, Object>> result = mxdyDao.getZhutidata();
        return result;
    }


/*    @Override
    *//**
     * 查询模型定义表通过ID
     * @param idd 模型定义表ID
     * @return
     *//*
    public List<Map<String, Object>> getjcmxmessageDataByid(String idd) {
        List<Map<String, Object>> result = mxdyDao.getjcmxmessageDataByid(idd);
        return result;
    }*/


    @Override
    /**
     * 得到编辑界面展示的数据库字段信息
     * @param megid 模型定义表ID
     * @return
     */
    public List<Map<String, Object>> getjcmxmetadata(String megid) {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        if (!"xzxx".equals(megid)) {
            result = mxdyDao.getjcmxmetadata(megid);
        }
        return result;
    }
    
	public List<YsjzdVo> getmxdymetadataForcompare(String megid) {
		List<YsjzdVo> result = new ArrayList<YsjzdVo>();
		if (!"xzxx".equals(megid)) {
			result = mxdyDao.getmxdymetadataForcompare(megid);
		}
		return result;
	}

    @Override
    /**
     * 查询具体字段信息
     * @param megid  信息ID
     * @param mc   字段名称
     * @return
     */
    public List<YsjzdVo> getmxdymetadataBymc(String megid, String mc) {
        List<YsjzdVo> result = new ArrayList<YsjzdVo>();
        if (!"xzxx".equals(megid)) {
            result = mxdyDao.getmxdymetadataBymc(megid, mc);
        }
        return result;
    }

    @Override
    /**
     *  更新本地的模型定义数据表
     * @param xxid  模型定义ID
     * @param xxzt  库信息
     * @param xxmc  模型定义名称
     * @param xxms  表描述
     * @param xxbm  本地模型定义表名
     * @param userid 用户ID
     * @return
     */
    public String updatexxflOFdata(String xxid, String xxzt, String xxmc, String xxms, String xxbm, String userid) {
        String res = "ok";
        Date tit = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ti = df.format(tit);
        try {
            mxdyDao.updateMegdata(xxzt, xxmc, xxms, xxid, userid, ti);
        } catch (Exception e) {
            logger.info(e);
            res = "fail";
        }

        return res;
    }



    //选择的数据库是oracle或者greeplum
    private List<String> getDatabaseMeg(String xxzt) {
        List<String> li = new ArrayList<String>();
        String currentdrive;
        String currenturl;
        String currentuser;
        String currentpassword;
        if ("1".equals(xxzt)) {
            currentdrive = getDrivename().trim();
            currenturl = getUrl().trim();
            currentuser = getUserName();
            currentpassword = getPassword();
        } else {
            currentdrive = getCkdrivename().trim();
            currenturl = getCkurl().trim();
            currentuser = getCkuserName();
            currentpassword = getCkpassword();
        }
        li.add(currentdrive);
        li.add(currenturl);
        li.add(currentuser);
        li.add(currentpassword);
        return li;
    }

    // 判断更新前字段信息为主键非空,非主键为空的判断--oracle数据库
    private List<String> checkZjNotnullOforacle(List<Xxzd> editcolumns, String xxid, String xxbm) {
        StringBuffer sql = new StringBuffer();
        List<String> sqls = new ArrayList<String>();
        for (Xxzd xzd : editcolumns) {
            List<YsjzdVo> relist = this.getmxdymetadataBymc(xxid, xzd.getFd_zdmc());
            YsjzdVo ysjvo = relist.get(0);
            if (("Y".equals(ysjvo.getSfzj()) && "N".equals(ysjvo.getSfkwk())) || ("N".equals(ysjvo.getSfzj()) && "Y".equals(ysjvo.getSfkwk()))) {
                if ("N".equals(xzd.getFd_sfzj()) && "N".equals(xzd.getFd_sfkwk())) {
                	sql.append("alter table ").append(xxbm).append(" modify ").append(ysjvo.getZdmc()).append(" not null") ;
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

    // 判断更新前字段信息为非主键非空的判断---oracle数据库
    private List<String> checkNotzjNotnullOforacle(List<Xxzd> editcolumns, String xxid, String xxbm) {
        StringBuffer sql = new StringBuffer();
        List<String> sqls = new ArrayList<String>();
        for (Xxzd xzd : editcolumns) {
            List<YsjzdVo> relist = this.getmxdymetadataBymc(xxid, xzd.getFd_zdmc());
            YsjzdVo ysjvo = relist.get(0);
            if ("N".equals(ysjvo.getSfzj()) && "N".equals(ysjvo.getSfkwk())) {
                if ("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
                	sql.append("alter table ").append(xxbm).append(" modify ").append(ysjvo.getZdmc()).append("  null") ;
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

  //原字段为非主键非空的sql语句
     private String  getNotzjNotnullOfgree(YsjzdVo ysjvo,Xxzd xzd ,String xxbm){
    	 StringBuffer sql = new StringBuffer();
         if ("N".equals(ysjvo.getSfzj()) && "Y".equals(ysjvo.getSfkwk())) {
             if ("N".equals(xzd.getFd_sfzj()) && "N".equals(xzd.getFd_sfkwk())) {
            	 sql.append("alter table ").append(xxbm).append(" modify ").append(ysjvo.getZdmc()).append(" DROP NOT NULL") ;
             } else {
                 sql.append("");
             }
         }
		return sql.toString();
     }
     
    // 判断更新前字段信息为主键非空,非主键为空的判断--gre数据库
    private List<String> checkZjNotnullOfgre(List<Xxzd> editcolumns, String xxid, String xxbm) {
        StringBuffer sql = new StringBuffer();
        List<String> sqls = new ArrayList<String>();
        for (Xxzd xzd : editcolumns) {
            List<YsjzdVo> relist = this.getmxdymetadataBymc(xxid, xzd.getFd_zdmc());
            YsjzdVo ysjvo = relist.get(0);
             //主键非空的sql语句
            if ("Y".equals(ysjvo.getSfzj()) && "N".equals(ysjvo.getSfkwk())) {
                if ("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
                	sql.append("alter table ").append(xxbm).append(" ALTER COLUMN ").append(ysjvo.getZdmc()).append(" DROP NOT NULL") ;
                } else {
                    sql.append("");
                }
            }
           //非主键非空的sql语句
            String fk=this.getNotzjNotnullOfgree(ysjvo, xzd, xxbm);
            sql.append(fk);
            if (!"".equals(sql.toString())) {
                sqls.add(sql.toString());
            }
        }

        return sqls;
    }


    // 判断更新前字段信息为非主键非空的判断---gre数据库
    private List<String> checkNotzjNotnullOfgre(List<Xxzd> editcolumns, String xxid, String xxbm) {
        StringBuffer sql = new StringBuffer();
        List<String> sqls = new ArrayList<String>();
        for (Xxzd xzd : editcolumns) {
            List<YsjzdVo> relist = this.getmxdymetadataBymc(xxid, xzd.getFd_zdmc());
            YsjzdVo ysjvo = relist.get(0);
            if ("N".equals(ysjvo.getSfzj()) && "N".equals(ysjvo.getSfkwk())) {
                if ("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
                	sql.append("alter table ").append(xxbm).append(" ALTER COLUMN ").append(ysjvo.getZdmc()).append(" DROP NOT NULL") ;
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
    //得到增加字段的是否为空sql语句 --greeplum
    private  List<String>  getAddsqlTosfwkOfgree(List<Xxzd> addcolumns,String xxbm){
 	    List<String> sqls = new ArrayList<>();
 	    StringBuffer sql=new StringBuffer();
 		for (Xxzd xzd : addcolumns) {
 			if (("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk()))
 					|| ("Y".equals(xzd.getFd_sfzj()) && "N".equals(xzd.getFd_sfkwk()))) {
 				sql.append("");
 			} else if ("Y".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
 				sql.append("alter table ").append(xxbm).append(" ALTER COLUMN ").append(xzd.getFd_zdmc()).append(" DROP NOT NULL") ;
 			} else {
 				sql.append("alter table ").append(xxbm).append(" ALTER COLUMN ").append(xzd.getFd_zdmc()).append(" SET NOT NULL") ;
 			}
 			if (!"".equals(sql.toString())) {
 				sqls.add(sql.toString());
 			}
 		}
 		return sqls;
    } 
    
    //得到增加字段的是否为空sql语句 --oracle
    private  List<String>  getAddsqlTosfwkOforacle(List<Xxzd> addcolumns,String xxbm){
 	    List<String> sqls = new ArrayList<>();
 	    StringBuffer sql=new StringBuffer();
 		for (Xxzd xzd : addcolumns) {
 			if (("N".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk()))
 					|| ("Y".equals(xzd.getFd_sfzj()) && "N".equals(xzd.getFd_sfkwk()))) {
 				sql.append("");
 			} else if ("Y".equals(xzd.getFd_sfzj()) && "Y".equals(xzd.getFd_sfkwk())) {
 				sql.append("alter table ").append(xxbm).append(" modify ").append(xzd.getFd_zdmc()).append(" null") ;
 			} else {
 				sql.append("alter table ").append(xxbm).append(" modify ").append(xzd.getFd_zdmc()).append(" not null") ;
 			}
 			if (!"".equals(sql.toString())) {
 				sqls.add(sql.toString());
 			}
 		}
 		return sqls;
    } 
    
    @Override
    /**
     * 添加模型定义远程表的执行sql信息
     * @param params  请求参数类
     * @param jsonadd  添加的字段信息
     * @param userid  用户ID
     * @return
     */
    public String addxxflMeg(RequestParams params, List<Xxzd> jsonadd, String userid) {
        String xxbm = params.getXxbm();
        String xxms = params.getXxms();
        String xxzt = params.getXxzt();
        String xxid=params.getXxid();
        String res = "ok";
        try {
            List<String> li = getDatabaseMeg(xxzt);
            String currentdrive = li.get(0);
            String currenturl = li.get(1);
            String currentuser = li.get(2);
            String currentpassword = li.get(3);
          //得到创建表格的sql
            List<String> sqls = TableOperationUtil.createTable(currentdrive, jsonadd, xxbm, xxms);
			//得到需要执行的是否为空的sql语句
			List<String> sqlOfsfwk = this.getxxmetsfwksql(xxzt, xxid, xxbm, jsonadd, null);
			sqls.addAll(sqlOfsfwk);
            res=mxdyDao.editxxmetadata(currenturl, currentdrive, sqls, currentuser, currentpassword);
        } catch (Exception e) {
            logger.info(e);
            res = "fail";
        }

        return res;
    }


    @Override
    public List<Map<String, Object>> getIDfromxxfl(String xxbm) {
        List<Map<String, Object>> result = mxdyDao.getIDfromxxfl(xxbm);
        return result;
    }


    @Override
    public List<Jcmxdy> getDataBybm(String bm) {
    	List<Jcmxdy> result = mxdyDao.getDataBybm(bm);
        return result;
    }

	 //得到用户编辑后的主键list
    private  List<String>  getKeylistOfedit(List<YsjzdVo> pkey,List<String> keys,List<Xxzd> zdEdtlist){
        
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
         List<String> editkeys=this.getKeylistOfedit(pkey, keys, zdEdtlist);
         
 		// 得到新增后的整合的主键list
 		for (Xxzd xzd : zdAddlist) {
 			if ("Y".equalsIgnoreCase(xzd.getFd_sfzj())) {
 				editkeys.add(xzd.getFd_zdmc());
 			}
 		}
      if(DataSourceUtil.equalList(editkeys, keycomp)){
    	  editkeys=new ArrayList<String>();
    	  editkeys.add("keep");
        }
 		return editkeys;
 	}
 	//编辑字段的参数，存入Xxzd Pojo中
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
  //添加字段的参数，存入Xxzd Pojo中
    private  List<Xxzd>addColmns(JSONArray jsonadd, String userid, Date dt, String xxid) {
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
            xzd.setFd_lx("1");
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
    
	// 得到编辑字段和增加字段的sql语句
	private List<String> getEditAndAddsql(String xxzt,String xxbm, List<Xxzd> zdAddlist, List<Xxzd> zdEditlist) {
		List<String> totalsql = new ArrayList<String>();
		List<String> sqls = null;
        List<String> li = getDatabaseMeg(xxzt);
        String currentdrive = li.get(0);
		// 得到新增字段信息的sql语句
		for (Xxzd xzd : zdAddlist) {
			sqls = TableOperationUtil.addField(currentdrive, xzd, xxbm);
			totalsql.addAll(sqls);
		}
		// 得到编辑字段的sql语句
		for (Xxzd xzd : zdEditlist) {
			sqls = TableOperationUtil.modifyField(currentdrive, xzd, xxbm);
			totalsql.addAll(sqls);
		}
		return totalsql;
	}
	
 // 得到页面新增编辑主键执行的sql语句
 	private List<String> getxxmetakeysql(String xxzt,String xxbm, List<String> keynames, String xxms) {
 		List<String> sqls = new ArrayList<String>();
        List<String> li = getDatabaseMeg(xxzt);
        String currentdrive = li.get(0);
        String currenturl = li.get(1);
        String currentuser = li.get(2);
        String currentpassword = li.get(3);
 		try {
 			String sql = TableOperationUtil.searchKey(currentdrive, xxbm);
 			String usr = DataSourceUtil.getKeyMessage(currenturl, currentdrive, sql, currentuser, currentpassword);
 			if (!keynames.isEmpty() && "you".equals(usr)) {
				if(!keynames.contains("keep")){
	 				sqls = TableOperationUtil.modifyKey(currentdrive, keynames, true, xxbm);
				}
 			} else if (keynames.isEmpty() && "you".equals(usr)) {
 				sqls.add("alter table " + xxbm + "  drop constraint "
 						+ ((xxbm.length() > 20) ? xxbm.substring(0, 20) : xxbm) + "_dsjzc_key cascade");
 			} else if (!keynames.isEmpty() && "kong".equals(usr)) {
 				if(!keynames.contains("keep")){
 				sqls = TableOperationUtil.modifyKey(currentdrive, keynames, false, xxbm);
 				}
 			}
 	
 			sqls.add("comment on table " + xxbm + " is '" + xxms.replaceAll("'", "\"") + "'");
 		} catch (Exception e) {
 			logger.info(e);
 		}
 		return sqls;
 	}
 	//得到是否可为空字段的sql语句
 	private List<String> getxxmetsfwksql(String xxzt,String xxid, String xxbm, List<Xxzd> addcolumns, List<Xxzd> editcolumns) {
		List<String> sqls = new ArrayList<String>();
		try {
			List<String> sqls1;
			List<String> sqls2;
            //编辑字段判断数据库空执行语句
            if (editcolumns != null) {
                if ("1".equals(xxzt)) {
                    // 判断更新前字段信息为主键非空,非主键为空的判断
                    sqls = this.checkZjNotnullOforacle(editcolumns, xxid, xxbm);
                    // 判断更新前字段信息为非主键非空的判断
                    sqls1 = this.checkNotzjNotnullOforacle(editcolumns, xxid, xxbm);
                    sqls.addAll(sqls1);
                } else {
                    // 判断更新前字段信息为主键非空,非主键为空的判断
                    sqls = this.checkZjNotnullOfgre(editcolumns, xxid, xxbm);
                    // 判断更新前字段信息为非主键非空的判断
                    sqls1 = this.checkNotzjNotnullOfgre(editcolumns, xxid, xxbm);
                    sqls.addAll(sqls1);                   
                }
            }
            
			//添加字段判断数据库空执行语句，得到执行语句
           if("1".equals(xxzt)){
   			sqls2= this.getAddsqlTosfwkOfgree(addcolumns,xxbm);
           }else{
        	 sqls2= this.getAddsqlTosfwkOforacle(addcolumns, xxbm);
           }

			sqls.addAll(sqls2);	
		} catch (Exception e) {
			logger.info(e);
		}
		return sqls;
	}

    @Override
	/**
	 * 执行远程修改表信息的方法
	 * @param xxzt  模型定义的数据库ID
	 * @param xxid  模型分类表ID
	 * @param xxbm  模型分类表名
	 * @param xxms  表描述
	 * @param addcolumns 页面新增的字段list
	 * @param editcolumns 页面编辑的字段list
	 * @return
	 */
	public String createTableOrcolumns(String xxzt,String xxid, String xxbm, String xxms,List<Xxzd> addcolumns,
			List<Xxzd> editcolumns) {
		String res = "ok";
		try {
			// 拿到修改前，表的主键list
			List<YsjzdVo> pkey = new ArrayList<YsjzdVo>();
			List<YsjzdVo> melist = this.getmxdymetadataForcompare(xxid);
			for (YsjzdVo map : melist) {
				if ("Y".equals(map.getSfzj())) {
					pkey.add(map);
				}
			}
			//得到需要更新主键信息的字段名称list
			List<String> keystr=this.getEditkeyList(editcolumns, addcolumns, pkey);
			//得到需要执行的主键sql语句
			List<String> sqls;
			sqls = this.getxxmetakeysql(xxzt,xxbm, keystr, xxms);
			//得到需要执行的是否为空的sql语句
			List<String> sqlOfsfwk = this.getxxmetsfwksql(xxzt,xxid, xxbm, addcolumns, editcolumns);
			sqls.addAll(sqlOfsfwk);
			//得到编辑和新增的字段信息sql语句
			List<String> sqlOfcolmuns= this.getEditAndAddsql(xxzt, xxbm, addcolumns, editcolumns);
			sqls.addAll(sqlOfcolmuns);
			//调用远程执行sql的方法
            List<String> li = getDatabaseMeg(xxzt);
            String currentdrive = li.get(0);
            String currenturl = li.get(1);
            String currentuser = li.get(2);
            String currentpassword = li.get(3);
			res=mxdyDao.editxxmetadata(currenturl, currentdrive, sqls, currentuser, currentpassword);
		} catch (Exception e) {
			res = "fail";
			logger.info(e);
		}
		return res;

	}
    
    @Override
    /**
     * 更新远程数据库表的字段信息
     */
    public String updatemxdyMegdata(RequestParams params, String userid) {
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
			List<Xxzd> editcolumns = editColmns(jsonedit, userid, dt, xxid);
			List<Xxzd> addcolumns= addColmns(jsonadd, userid, dt, xxid);
			//调用远程执行字段信息修改的sql语句方法，返回执行结果
			res= this.createTableOrcolumns(xxzt,xxid, xxbm, xxms,addcolumns, editcolumns);
			if (!"ok".equals(res)) {
				return res;
			}
            // 创建字段
            for (Xxzd xz : addcolumns) {
                xxzdService.save(xz);
            }

            // 修改字段
            for (Xxzd xz : editcolumns) {
                xxzdService.update(xz);
            }
            
            // 更新信息分类表
            res = this.updatexxflOFdata(xxid, xxzt, xxmc, xxms, xxbm, userid);
        } catch (Exception e) {
            logger.info(e);
            res = "fail";
        }

        return res;
    }

   

    @Override
    /**
     * 对远程库表进行添加操作
     */
    public String addjcmxMegdata(RequestParams params, String userid) {
        String res = "";
        Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ti = df.format(dt);
        String addlist = params.getAddlist();
        String xxbm = params.getXxbm();
        JSONArray jsonadd = JSONArray.fromObject(addlist);
        String xxuuid = "";
        try {
        	List<Jcmxdy> re = this.getDataBybm(xxbm);
            xxuuid = UUID.randomUUID().toString();
            params.setXxid(xxuuid);
            List<Xxzd> addcolumns= addColmns(jsonadd, userid, dt, xxuuid);
            // 创建表格
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
            mxdyDao.addxxflMeg(params, ti, userid, userid);
            // 创建字段
            for (Xxzd xz : addcolumns) {
                xxzdService.save(xz);
            }

        } catch (Exception e) {
            logger.info(e);
            res = "fail";
        }
        return res;
    }
}
