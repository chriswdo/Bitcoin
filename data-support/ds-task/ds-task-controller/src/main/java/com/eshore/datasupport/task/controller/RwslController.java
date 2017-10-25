package com.eshore.datasupport.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

import com.eshore.datasupport.task.service.IRwslService;
import com.eshore.datasupport.task.vo.ExceptionRecordVo;
import com.eshore.datasupport.task.pojo.Rwsl;

@Controller
@RequestMapping("/monitorrule")
public class RwslController extends BaseController<Rwsl> {

    @Autowired
    IRwslService rwslService;

    @Override
    protected String getBasePath() {
        return "/datasupport";
    }

    @Override
    protected IBaseService<Rwsl> getService() {
        return rwslService;
    }

    public String getModuleName() {
        return "任务调度管理>任务实例";
    }

    private static final Logger logger = Logger.getLogger(RwslController.class);

    @RequestMapping(value = "getRulemonitordata")
    @ResponseBody
    public Map<String, Object> getRulemonitordata(String gzmc, PageConfig pc, int lx) {
    	String gzmcc=gzmc;
        if (gzmcc == null || gzmcc.isEmpty()) {
        	gzmcc = "";
        }
        List<Map<String, Object>> relist = rwslService.getRulemonitordata(gzmcc.trim(), pc, lx);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("list", relist);
        retMap.put("total", pc.getRowCount());
        return retMap;

    }

    @RequestMapping(value = "getwjMonitorpage")
    /**
     * 跳转到挖掘日志监控页面
     * @return
     */
    public ModelAndView getwjMonitorpage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getBasePath() + "/wjmonitor");
        logger.info(getBasePath() + "/wjmonitor");
        return mv;

    }

    @RequestMapping(value = "getcjMonitorpage")
    /**
     * 跳转到采集日志监控页面
     * @return
     */
    public ModelAndView getcjMonitorpage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getBasePath() + "/cjmonitor");
        logger.info(getBasePath() + "/cjmonitor");
        return mv;

    }

    @RequestMapping(value = "getclMonitorpage")
    /**
     * 跳转到处理日志监控页面
     * @return
     */
    public ModelAndView getclMonitorpage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(getBasePath() + "/clmonitor");
        logger.info(getBasePath() + "/clmonitor");
        return mv;

    }

    @RequestMapping(value = "getRuledetail")
    @ResponseBody
    public Map<String, Object> getRuledetail(String jobid, PageConfig pc) {
    	String jid=jobid;
        if (jid == null || jid.isEmpty()) {
            jid = "";
        }
        List<Map<String, Object>> relist = rwslService.getRuledetail(jid.trim(), pc);
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("list", relist);
        retMap.put("total", pc.getRowCount());
        return retMap;

    }

    @RequestMapping(value = "jumpPage")
    @ResponseBody
    /**
     * 跳转到运行日志界面
     * @param jobid
     * @param flag
     * @return
     */
    public ModelAndView jumpPage(String jobid, int flag) {
    	String jid=jobid;
        if (jid == null || jid.isEmpty()) {
            jid = "";
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("jobid", jid);
        if (flag == 0) {
            mv.setViewName(getBasePath() + "/rwsldetail");
        } else if (flag == 1) {
            mv.setViewName(getBasePath() + "/cldetail");
        } else {
            mv.setViewName(getBasePath() + "/wjdetail");
        }
        return mv;

    }

    //跳转到异常记录页面
    @RequestMapping(value = "jumpPageycju")
    @ResponseBody
    public ModelAndView jumpPageycju(String jobid) {
    	String jid=jobid;
        if (jid == null || jid.isEmpty()) {
            jid = "";
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("jobid", jid);
        mv.setViewName(getBasePath() + "/ycju");
        return mv;

    }

    //加载异常记录数据
    @RequestMapping(value = "getYcjudetail")
    @ResponseBody
    public Map<String, Object> getYcjudetail(String jobid, PageConfig pc) {
    	String jid=jobid;
        if (jid == null || jid.isEmpty()) {
            jid = "";
        }
        List<ExceptionRecordVo> relist = rwslService.getYcjudetail(jid.trim(), pc);
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("list", relist);
        retMap.put("total", pc.getRowCount());
        return retMap;

    }

    //加载异常记录数据
    @RequestMapping(value = "ajaxHomeRwslStatistics")
    @ResponseBody
    public Map<String, Object> ajaxRwslStatistics(PageConfig pc) {
        List<Map<String, Object>> relist = rwslService.ajaxRwslStatistics(pc);
        Map<String, Object> retMap = new HashMap<String, Object>();
        retMap.put("list", relist);
        retMap.put("total", pc.getRowCount());
        return retMap;
    }

    /**
     * 首页图表展示rwsl统计信息
     *
     * @return
     */
    @RequestMapping(value = "ajaxHomeRwslStatisticForEchart")
    @ResponseBody
    public Map<String, Object> ajaxHomeRwslStatisticForEchart() {
        Map<String, Object> retMap = new HashMap<>();
        rwslService.ajaxHomeRwslStatisticForEchart(retMap);
        return retMap;
    }
}
