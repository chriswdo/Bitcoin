package com.eshore.datasupport.metadata.controller;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.metadata.pojo.Zdmx;
import com.eshore.datasupport.metadata.service.IZdmxService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/10.
 * 字典明细接口
 */
@Controller
@RequestMapping("/metazdmxcontroller")
public class ZdmxController extends BaseController<Zdmx> {
    @Autowired
    IZdmxService iZdmxService;

    @Override
    protected IBaseService<Zdmx> getService() {
        return iZdmxService;
    }

    @Override
    protected String getBasePath() {
        return "/datasupport/";
    }

    public String getModuleName() {
        return "字典明细管理";
    }

    private static final Logger logger = Logger.getLogger(ZdflController.class);

    /**
     * 跳转字典明细管理jsp页面
     * @return
     */
    @RequestMapping(value = "viewxZdmx")
    @ResponseBody
    public ModelAndView viewxZdmx(){
        ModelAndView mv = new ModelAndView();
        List<Map<String, Object>>  selist=iZdmxService.getAllZdflmc();
        mv.addObject("selist", selist);
        mv.setViewName(getBasePath() + "dodictionary");
        //detail of dictionary
        return mv;
    }

    /**
     * 根据条件获取字典明细的信息
     * @param fd_flbm 分类编码
     * @param fd_zdmc 字典名称
     * @param pageConfig 分页
     * @return
     */
    @RequestMapping(value = "ajaxAllData")
    @ResponseBody
    public Map<String,Object> ajaxAllData(String fd_flbm,String fd_zdmc,PageConfig pageConfig){
        List<Map<String, Object>> pageList;
        if(StringUtils.isNotBlank(fd_zdmc) || StringUtils.isNotBlank(fd_flbm)){
            pageList = iZdmxService.getZdmxdataByzdmc(fd_flbm,fd_zdmc, pageConfig);
            logger.info("查询部分数据");
        }else{
            logger.info("全部数据");
            pageList = iZdmxService.getAllZdmxdata(pageConfig);
        }

        Map<String,Object> retMap = new HashMap<>();
        retMap.put("total",pageConfig.getRowCount());
        retMap.put("list", pageList);
        return retMap;
    }

    /**
     * 新增、编辑字典明细
     * @param id 主键id
     * @param fd_zdmc 字典名称
     * @param fd_zdms 字典描述
     * @param fd_zdbm 字典编码
     * @param fd_flbm 分类编码
     * @return
     */
    @RequestMapping(value="savexDictionaryData")
    @ResponseBody
    public String savexDictionaryData(String id,String fd_zdmc,String fd_zdms,String fd_zdbm,String fd_flbm){
        Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
        String fd_gxr = yhb.getId();
        if(StringUtils.isNotBlank(id)){
            iZdmxService.updateZdmx(id, fd_zdmc, fd_gxr, fd_zdms);
            logger.info("更新数据");
        }else if(!iZdmxService.isExistzdbm(fd_zdbm,fd_flbm)){
            iZdmxService.saveZdmx(fd_flbm,fd_zdmc,fd_zdbm,fd_gxr,fd_zdms);
            logger.info("新增数据");
        }else{
            return "fail";
        }
        return "success";
    }

    /**
     * 删除单个字典
     * @param id
     * @return
     */
    @RequestMapping(value = "deletexDictionary")
    @ResponseBody
    public String deletexDictionary(String id){
        iZdmxService.deleteZdmx(id);
        return "success";
    }

    /**
     * 批量删除
     * @param list
     * @return
     */
    @RequestMapping(value = "deletexBatch")
    @ResponseBody
    public String deletexBatch(String list){
        iZdmxService.deleteMulZdmx(list);
        return "success";
    }
}
