package com.eshore.datasupport.metadata.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Xxfl;
import com.eshore.datasupport.metadata.service.IXxflService;
import com.eshore.datasupport.metadata.vo.MessageVo;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/xxflcontroller")
public class XxflController extends BaseController<Xxfl> {
    private final Logger logger = Logger.getLogger(XxflController.class);

    @Autowired
    IXxflService xxflService;

    @Override
    protected String getBasePath() {
        return "/datasupport";
    }

    @Override
    protected IBaseService<Xxfl> getService() {
        return xxflService;
    }

    public String getModuleName() {
        return "元数据管理>元数据表";
    }

    @RequestMapping(value = "getMessagedata")
    @ResponseBody
    /**
     * 得到信息分类表信息
     * @param zt  主题ID
     * @param meg  信息
     * @param pc   分页
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, Object> getMessagedata(String zt, String meg, PageConfig pc) throws UnsupportedEncodingException {
        String megs = URLDecoder.decode(meg, "utf-8");
        List<MessageVo> relist = xxflService.getMessagedata(zt, megs, pc);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("list", relist);
        retMap.put("total", pc.getRowCount());
        return retMap;


    }

    @RequestMapping(value = "getxxmetadata")
    @ResponseBody
    /**
     * 通过信息ID得到信息字段表的对应信息表的字段
     * @param xxid  信息表ID
     * @return
     */
    public String getxxmetadata(String xxid) {
        List<Map<String, Object>> relist = xxflService.getmetaDatainpage(xxid);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("rows", relist);
        JSONObject jsonObject = JSONObject.fromObject(retMap);
        return jsonObject.toString();

    }

    @RequestMapping(value = "checkCols")
    @ResponseBody
    /**
     * 判断字段是否重复
     * @param xxid  信息ID
     * @param zdmc  字段名称
     * @return
     */
    public String checkCols(String xxid, String zdmc) {
        String res = "";
        List<Map<String, Object>> relist = xxflService.getmetaDatainpage(xxid);
        for (Map<String, Object> map : relist) {
            if (map.get("FD_ZDMC")!= null && map.get("FD_ZDMC").toString().equals(zdmc.trim())) {
                res = "repeat";
                break;
            }
        }
        return res;

    }

    @RequestMapping(value = "getxxmetadataBymc")
    @ResponseBody
    /**
     * 通过信息ID和字段名称查询信息字段表
     * @param xxid  信息ID
     * @param zdmc  字段名称
     * @return
     */
    public String getxxmetadataBymc(String xxid, String zdmc) {
        List<YsjzdVo> relist = xxflService.getxxmetadataBymc(xxid, zdmc);
        if ("VARCHAR".equals(relist.get(0).getZdlx()) || "NUMBER".equals(relist.get(0).getZdlx())) {
            return "cddemo";
        } else if ("NUMERIC".equals(relist.get(0).getZdlx())) {
            return "jddemo";
        } else {
            return "datedemo";
        }

    }

/*    @RequestMapping(value = "getDatainpage")
    @ResponseBody
    //获取新增弹框内容信息
    public Map<String,Object> getDatainpage() {
    	Map <String,Object>  retMap = new HashMap<String ,Object>();
        List<Map<String, Object>> telist = xxflService.getZhutidata();
        retMap.put("selist", telist);
        return retMap;
    }
*/
/*    @RequestMapping(value = "editmetadata")
    @ResponseBody
  //跳转到信息分类编辑界面
    public Map<String, Object> editmetadata(String megid) {
        Map<String, Object> retMap = new HashMap<>();
        List<Map<String, Object>> relist = xxflService.getmessageDataByid(megid);
        JSONArray arr = JSONArray.fromObject(relist);
        JSONObject ja = arr.getJSONObject(0);
        retMap.put("relist", relist);
      //  mv.addObject("megid", megid);
        return retMap;
    }*/

    @RequestMapping(value = "getmegDatapage")
  //跳转到信息分类界面
    public ModelAndView getmegDatapage() {
        ModelAndView mv = new ModelAndView();
        List<Map<String, Object>> selist = xxflService.getZhutidata();
        mv.addObject("selist", selist);
        mv.setViewName(getBasePath() + "/messagedivide");
        logger.info(getBasePath() + "/messagedivide");
        return mv;

    }


    //更新信息分类表信息
    @RequestMapping(value = "updateMegdata")
    @ResponseBody
    /**
     * 更新信息分类远程库表和本地库表的信息
     * @param params  参数请求类
     * @param request 
     * @return
     */
    public String updateMegdata(RequestParams params, HttpServletRequest request) {
        String res = "";
        Yhb user = SessionUtil.getCurrentUserInfo(request);
        String userid = user.getId();
        res = xxflService.updateMesagedata(params, userid);
        return res;
    }


    @RequestMapping(value = "addMegdata")
    @ResponseBody
    /**
     * 添加信息分类远程库表和本地库表的信息
     * @param params  参数请求类
     * @param request 
     * @return
     */
    public String addMegdata(RequestParams params, HttpServletRequest request) {
        String res = "";
        Yhb user = SessionUtil.getCurrentUserInfo(request);
        String userid = user.getId();
        res = xxflService.addMesagedata(params, userid);
        return res;
    }


}
