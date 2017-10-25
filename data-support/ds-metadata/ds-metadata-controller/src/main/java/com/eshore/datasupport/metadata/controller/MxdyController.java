package com.eshore.datasupport.metadata.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.datasupport.metadata.pojo.Jcmxdy;
import com.eshore.datasupport.metadata.pojo.Xxzd;
import com.eshore.datasupport.metadata.service.IMxdyService;
import com.eshore.datasupport.metadata.vo.RequestParams;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/mxdyController")
public class MxdyController extends BaseController<Jcmxdy> {

    private final Logger logger = Logger.getLogger(MxdyController.class);

    @Autowired
    IMxdyService mxdyService;

    @Override
    protected String getBasePath() {
        return "/datasupport";
    }

    @Override
    protected IBaseService<Jcmxdy> getService() {
        return mxdyService;
    }

    public String getModuleName() {
        return "元数据管理>元数据表";
    }

    @RequestMapping(value = "getmxdyMessagedata")
    @ResponseBody
    /**
     * 得到模型定义的信息
     * @param zt 数据库ID
     * @param meg 信息ID
     * @param pc 分页类
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String, Object> getmxdyMessagedata(String zt, String meg, PageConfig pc) throws UnsupportedEncodingException {
        String megs = URLDecoder.decode(meg, "utf-8");
        List<Jcmxdy> relist = mxdyService.getmxdyMessagedata(zt, megs, pc);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("list", relist);
        retMap.put("total", pc.getRowCount());
        return retMap;


    }

    @RequestMapping(value = "getjcmxmetadata")
    @ResponseBody
    /**
     * 得到编辑界面展示的数据库字段信息
     * @param xxid 模型定义表ID
     * @return
     */
    public String getjcmxmetadata(String xxid) {
        //得到编辑界面的字段
        List<Map<String, Object>> relist = mxdyService.getjcmxmetadata(xxid);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("rows", relist);
        JSONObject jsonObject = JSONObject.fromObject(retMap);
        return jsonObject.toString();

    }

    @RequestMapping(value = "checkCols")
    @ResponseBody
    /**
     * 判断创建字段是否重复
     * @param xxid
     * @param zdmc
     * @return
     */
    public String checkCols(String xxid, String zdmc) {
        String res = "";
        List<Map<String, Object>> relist = mxdyService.getjcmxmetadata(xxid);
        for (Map<String, Object> map : relist) {
            if (map.get("FD_ZDMC").toString().equals(zdmc.trim())) {
                res = "repeat";
                break;
            }
        }
        return res;

    }

    @RequestMapping(value = "getxxmetadataBymc")
    @ResponseBody
    /**
     * 查询具体字段信息
     * @param xxid  信息ID
     * @param zdmc   字段名称
     * @return
     */
    public String getxxmetadataBymc(String xxid, String zdmc) {
        List<YsjzdVo> relist = mxdyService.getmxdymetadataBymc(xxid, zdmc);

        if ("VARCHAR".equals(relist.get(0).getZdlx()) || "NUMBER".equals(relist.get(0).getZdlx())) {
            return "cddemo";
        } else if ("NUMERIC".equals(relist.get(0).getZdlx())) {
            return "jddemo";
        } else {
            return "datedemo";
        }

    }

    @RequestMapping(value = "getmxdyDatainpage")
    @ResponseBody
    /**
     * 跳转到决策模型新增界面
     * @return
     */
    public ModelAndView getmxdyDatainpage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("megid", "xzxx");
        mv.setViewName(getBasePath() + "/jcmydyxz");
        return mv;
    }

/*    @RequestMapping(value = "editmxdymetadata")
    @ResponseBody
    *//**
     * 弹框到模型定义编辑界面
     * @param megid
     * @return
     *//*
    public Map<String, Object> editmxdymetadata(String megid) {
    	Map<String, Object> retMap = new HashMap<>();
        List<Map<String, Object>> relist = mxdyService.getjcmxmessageDataByid(megid);
        JSONArray arr = JSONArray.fromObject(relist);
        JSONObject ja = arr.getJSONObject(0);
        retMap.put("relist", ja);
        return retMap;
    }*/

    @RequestMapping(value = "getmxmegDatapage")
    /**
     * 跳转到模型定义界面
     * @return
     */
    public ModelAndView getmxmegDatapage() {
        ModelAndView mv = new ModelAndView();
        List<Map<String, Object>> selist = mxdyService.getZhutidata();
        mv.addObject("selist", selist);
        mv.setViewName(getBasePath() + "/jcmxdy");
        logger.info(getBasePath() + "/jcmxdy");
        return mv;

    }


  
    @RequestMapping(value = "updatemxdyMegdata")
    @ResponseBody
    /**
     * 更新模型定义远程表信息
     * @param params  请求参数类
     * @param request 
     * @return
     */
    public String updatemxdyMegdata(RequestParams params, HttpServletRequest request) {
        String res = "ok";
        Yhb user = SessionUtil.getCurrentUserInfo(request);
        String userid = user.getId();
        res = mxdyService.updatemxdyMegdata(params, userid);
        return res;
    }


    @RequestMapping(value = "addjcmxMegdata")
    @ResponseBody
    /**
     * 添加模型定义远程表信息
     * @param params 请求参数类
     * @param request
     * @return
     */
    public String addjcmxMegdata(RequestParams params, HttpServletRequest request) {
        String res = "";
        Yhb user = SessionUtil.getCurrentUserInfo(request);
        String userid = user.getId();
        res = mxdyService.addjcmxMegdata(params, userid);
        return res;
    }


}
