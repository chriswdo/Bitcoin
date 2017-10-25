package com.eshore.datasupport.metadata.controller;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.metadata.pojo.Zt;
import com.eshore.datasupport.metadata.service.IXxflService;
import com.eshore.datasupport.metadata.service.IZtService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/4/24.
 * 主题定义接口
 */
@Controller
@RequestMapping("/metaztcontroller")
public class ZtController extends BaseController<Zt> {
    @Autowired
    IZtService iZtService;

    @Autowired
    IXxflService iXxflService;

    @Override
    protected IBaseService<Zt> getService() {
        return iZtService;
    }

    @Override
    protected String getBasePath() {
        return "/metadata/";
    }

    public String getModuleName() {
        return "主题定义";
    }

    private static final Logger logger = Logger.getLogger(ZtController.class);

    /**
     * 跳转主题定义的jsp页面
     * @return
     */
    @RequestMapping(value = "getAll")
    public String getAll(){
        return getReturn(getBasePath()+"themeDefine");
    }

    /**
     * 查询主题分页，主题为空则遍历全部
     * @param fd_ztmc
     * @param pageConfig
     * @return
     */
    @RequestMapping(value = "ajaxAllData")
    @ResponseBody
    public Map<String,Object> ajaxAllData(String fd_ztmc,PageConfig pageConfig){
        List<Map<String, Object>> pageList;
        if(StringUtils.isNotBlank(fd_ztmc)){
            pageList = iZtService.getZtdataByztmc(fd_ztmc, pageConfig);
        }else{
            logger.info("获取所有主题信息");
            pageList = iZtService.getAllZtdata(pageConfig);
        }
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("total",pageConfig.getRowCount());
        retMap.put("list", pageList);
        return retMap;
    }

    /**
     * 查询主题分页
     * @param ztmc 主题名称
     * @param pageConfig 分页
     * @return
     */
    @RequestMapping(value = "ajaxThemeByName")
    @ResponseBody
    public Map<String,Object> ajaxThemeByName(String ztmc,PageConfig pageConfig){
        String ztmc1 = ztmc.trim();
        List<Map<String, Object>> telist = iZtService.getZtdataByztmc(ztmc1, pageConfig);
        Map <String,Object>  retMap = new HashMap<>();
        retMap.put("total",pageConfig.getRowCount());
        retMap.put("list", telist);
        return retMap;
    }

    /**
     * 新增主题
     * @param id 主键id
     * @param fd_ztmc 主题名称
     * @param fd_zt 状态
     * @param fd_ms 描述
     * @return
     */
    @RequestMapping(value="savexNewThemeData")
    @ResponseBody
    public String saveNewThemeData(String id,String fd_ztmc,String fd_zt,String fd_ms){
        Yhb yhb =SessionUtil.getCurrentUserInfo(getRequest());
        String fd_gxr = yhb.getId();
        if(StringUtils.isNotBlank(id)){
            iZtService.updateZt(id, fd_ztmc, fd_zt, fd_ms,fd_gxr);
        }else{
            iZtService.addNewZt(fd_ztmc,fd_zt,fd_ms,fd_gxr);
        }
        logger.info("保存或者更新了");
        return "success";
    }

    /**
     * 删除主题
     * @param id
     * @return
     */
    @RequestMapping(value = "deletexTheme")
    @ResponseBody
    public String deletexTheme(String id){
        if(iXxflService.findAnyBysjyId(id)){
            return "fail";
        }
        iZtService.deleteZt(id);
        return "success";
    }
}