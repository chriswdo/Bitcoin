package com.eshore.datasupport.metadata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.common.vo.YsjbDescribeVo;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.dao.IYsjbDao;
import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.pojo.Ysjzd;
import com.eshore.datasupport.metadata.service.IYsjbService;
import com.eshore.datasupport.metadata.service.IYsjzdService;
import com.eshore.datasupport.metadata.service.IZdflService;
import com.eshore.datasupport.metadata.service.IZdmxService;

import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/metazdcontroller")
public class YsjzdController extends BaseController<Ysjzd> {
	private  final Logger logger = Logger.getLogger(YsjzdController.class);
	@Autowired
	IYsjzdService ysjzdService;
	@Autowired
	IYsjbService ysjbService;

	@Autowired
	IYsjbDao ysjbDao;
	@Autowired
	IZdmxService zdmcService;
	
	@Autowired
	IZdflService zdflService;
	@Override
	protected String getBasePath() {
		return "/datasupport";
	}

	@Override
	protected IBaseService<Ysjzd> getService() {
		return ysjzdService;
	}


	public String getModuleName() {
		return "元数据管理>元数据字段表";
	}

	@RequestMapping(value = "getysjzdData", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 根据表名查询元数据字段表
	 * @param request
	 * @return
	 */
	public Map <String,Object> getysjzdData(HttpServletRequest request) {
		String bm = request.getParameter("data");
	    //查询元数据字段通过表名
		List<Ysjzd> telist = ysjzdService.getysjzdData(bm);
		List<Map<String, Object>> relist = zdmcService.getAllZdflmc();
		Map <String,Object>  retMap = new HashMap<String ,Object>();
		retMap.put("list", telist);
		retMap.put("relist", relist);
		return retMap;

	}

	@RequestMapping(value = "editysjzdData", method = RequestMethod.POST)
	@ResponseBody
	//查询元数据字段data
	public Map<String,Object> editysjzdData(HttpServletRequest request) {
		String data = request.getParameter("data");
		String[] parms = data.split(",");
		Map <String,Object>  retMap = new HashMap<String ,Object>();
		//得到具体元数据字段list
		List<Map<String, Object>> telist = ysjzdService.editysjzdData(parms[0], parms[1]);
		List<Map<String, Object>> relist = zdmcService.getAllZdflmc();
		Ysjzd  selist = ysjzdService.get(parms[0]);
		retMap.put("datalist", telist);
		retMap.put("zdlist", relist);
		retMap.put("selist", selist);

		return retMap;

	}
	
	@RequestMapping(value = "getZdflAlldata", method = RequestMethod.POST)
	@ResponseBody
	//查询元数据字段data
	public Map<String,Object> getZdflAlldata(HttpServletRequest request) {
		Map <String,Object>  retMap = new HashMap<String ,Object>();
		List<Map<String, Object>> relist = zdmcService.getAllZdflmc();
		retMap.put("zdlist", relist);
		return retMap;

	}
	@RequestMapping(value = "saveysjzdData", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 修改字典数据
	 * @param request
	 * @return
	 */
	public String saveysjzdData(String selectval) {
		String res = "";
		JSONArray selectArr = JSONArray.fromObject(selectval);
		try {
			for (int i = 0; i < selectArr.size(); i++) {
				String job = selectArr.getString(i);
				String[] iddata = job.split(",");
				Ysjzd sjyb = ysjzdService.get(iddata[1]);
				sjyb.setFd_sjzd(iddata[0]);
				ysjzdService.update(sjyb);
			}
			res = "ok";
		} catch (Exception e) {
			logger.info("saveysjzdData-fail:"+e);
			res = "fail";
		}

		return res;

	}

	//同步比较远程数据库表字段信息与本地数据库表字段信息
	public String checkMetaData(List<YsjzdVo> synlist,String idd,String sjy){
		StringBuilder sb = new StringBuilder();
		
		for (YsjzdVo syn : synlist) {
			List<YsjzdVo>  tesre = ysjzdService.getysjzdDataBybm(idd, syn.getZdmc(),sjy);	
			if ( tesre.isEmpty()) {
				sb.append("<tr>");
				sb.append("<td value=\"" + syn.getZdmc() + "," + 1 + "\"><font color=\"Red\">"
						+ syn.getZdmc() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getZdlx() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getZdcd() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getZdjd() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getSfzj() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getQsz() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getSfkwk() + "</font></td>");
				sb.append("<td><font color=\"Red\">" + syn.getZdbz() + "</font></td>");
				sb.append("</tr>");
			} else {
				YsjzdVo ysjzdvo=tesre.get(0);
				sb.append("<tr>");
				sb.append("<td>" + syn.getZdmc() + "</td>");				
				if (!ysjzdvo.getZdlx().equals(syn.getZdlx())) {
					sb.append("<td><font color=\"Red\">" + syn.getZdlx() + "</font></td>");
				} else {
					sb.append("<td>" + syn.getZdlx() + "</td>");
				}
				if ((syn.getZdcd()-ysjzdvo.getZdcd())!=0 ) {
					sb.append("<td><font color=\"Red\">" + syn.getZdcd() + "</font></td>");
				} else {		
					sb.append("<td>" + syn.getZdcd() + "</td>");
				}
				if ((syn.getZdjd()-ysjzdvo.getZdjd())!=0 ) {
					sb.append("<td><font color=\"Red\">" + syn.getZdjd() + "</font></td>");
				} else {		
					sb.append("<td>" + syn.getZdjd() + "</td>");
				}
				if (!ysjzdvo.getSfzj().equals(syn.getSfzj())) {						
					sb.append("<td><font color=\"Red\">" + syn.getSfzj() + "</font></td>");
				} else {
					sb.append("<td>" + syn.getSfzj() + "</td>");
				}
				if (!syn.getQsz().equals(ysjzdvo.getQsz())) {
			
						sb.append("<td><font color=\"Red\">" + syn.getQsz() + "</font></td>");
											
				} else {
					sb.append("<td>" + syn.getQsz()+ "</td>");
				}
				if (!syn.getSfkwk().equals(ysjzdvo.getSfkwk())) {
					sb.append("<td><font color=\"Red\">" + syn.getSfkwk() + "</font></td>");
				} else {
					sb.append("<td>" + syn.getSfkwk() + "</td>");
				}
				if (!syn.getZdbz().equals(ysjzdvo.getZdbz()) ){
						sb.append("<td><font color=\"Red\">" + syn.getZdbz() + "</font></td>");

				} else {
					sb.append("<td>" + syn.getZdbz()+ "</td>");
				}
				sb.append("</tr>");
			}
		}		
		   return sb.toString();
	}
	@RequestMapping(value = "getDatatosyn")
	@ResponseBody
	/**
	 * 查询出远程数据库表的字段信息
	 * @param request
	 * @return
	 */
	public Map<String,Object> getDatatosyn(HttpServletRequest request) {
		String sjy = request.getParameter("opt");
		String synbm = request.getParameter("synbm").trim();
		Map <String,Object>retMap = new HashMap<String ,Object>();
		 String  idd="";
		 String bms="";
		List<Ysjb> messa=null;
		try {
			List<YsjzdVo> synlist = ysjzdService.getDatatosyn(sjy, synbm);
			// 根据表名找到对应表名id
			 messa = ysjbDao.getysjbidByname(synbm,sjy);
			 if( messa!=null  && !messa.isEmpty() ){
				 idd=messa.get(0).getId();
			 }
			//同步比较远程数据库表字段信息与本地数据库表字段信息
			 String sb=checkMetaData(synlist,idd,sjy);	
			 //得到表描述
			 List<YsjbDescribeVo> da= ysjbService.getbiaoSql(synbm,sjy);
		    	 if(!da.isEmpty() && !"".equals(da.get(0).getTableDescribe())){
		    		 bms=da.get(0).getTableDescribe();
		    	 }
		    	 retMap.put("bms", bms);
		    	 retMap.put("str", sb);
		} catch (Exception e) {
			logger.info("getDatatosyn:"+e);
		}

		return retMap;

	}

	
	@RequestMapping(value = "synysjData")
	@ResponseBody
	/**
	 * 同步远程表字段信息到本地库表
	 * @param opt  数据源ID
	 * @param synbm  表名
	 * @param bms    表描述
	 * @return
	 */
	public String synysjData(String opt,String synbm,String bms) {
        HttpServletRequest request = this.getRequest();
		Yhb user=SessionUtil.getCurrentUserInfo(request);
		String userid=user.getId();
		String res="";
		res=ysjzdService.synymetaData(opt, synbm,userid,bms);			
       return res;
	}

}
