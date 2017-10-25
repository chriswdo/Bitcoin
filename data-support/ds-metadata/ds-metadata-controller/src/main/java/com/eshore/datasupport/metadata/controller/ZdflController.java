package com.eshore.datasupport.metadata.controller;

import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.util.SessionUtil;
import com.eshore.datasupport.metadata.pojo.Zdfl;
import com.eshore.datasupport.metadata.service.IZdflService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsl on 2017/5/9.
 * 字典分类接口
 */
@Controller
@RequestMapping("/metazdflcontroller")
public class ZdflController extends BaseController<Zdfl> {
    @Autowired
    IZdflService iZdflService;

    @Autowired
    IZdmxService iZdmxService;

    @Override
    protected IBaseService<Zdfl> getService() {
        return iZdflService;
    }

    @Override
    protected String getBasePath() {
        return "/datasupport/";
    }

    public String getModuleName() {
        return "字典分类管理";
    }

    private static final Logger logger = Logger.getLogger(ZdflController.class);

    /**
     * 跳转字典分类jsp页面
     * @return
     */
    @RequestMapping(value = "viewxZdfl")
    public String viewxZdfl(){
        //Classification of dictionary
        return getReturn(getBasePath()+"codictionary");
    }

    /**
     * 根据分类名称查询，或遍历所有
     * @param fd_flmc
     * @param pageConfig
     * @return
     */
    @RequestMapping(value = "ajaxAllData")
    @ResponseBody
    public Map<String,Object> ajaxAllData(String fd_flmc,PageConfig pageConfig){
        List<Map<String, Object>> pageList;
        if(StringUtils.isNotBlank(fd_flmc)){
            pageList = iZdflService.getZdfldataByzdflmc(fd_flmc, pageConfig);
        }else{
            logger.info("全部数据");
            pageList = iZdflService.getAllZdfldata(pageConfig);
        }
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("total",pageConfig.getRowCount());
        retMap.put("list", pageList);
        return retMap;
    }

    /**
     * 新增、编辑字典分类
     * @param id 主键id
     * @param fd_flmc 分类名称
     * @param fd_zt 状态
     * @param fd_flbm 分类编码
     * @return
     */
    @RequestMapping(value="savexDictionaryData")
    @ResponseBody
    public String saveNewThemeData(String id,String fd_flmc,String fd_zt,String fd_flbm){
        Yhb yhb = SessionUtil.getCurrentUserInfo(getRequest());
        String fd_gxr = yhb.getId();
        if(StringUtils.isNotBlank(id)){
            iZdflService.updateZdfl(id, fd_flmc, fd_zt, fd_gxr);
            logger.info("更新数据");
        }else if(!iZdflService.isExistflbm(fd_flbm)){
            iZdflService.saveZdfl(fd_flmc,fd_flbm,fd_zt,fd_gxr);
            logger.info("新增数据");
        }else{
            return "fail";
        }
        return "success";
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @RequestMapping(value = "deletexDictionary")
    @ResponseBody
    public String deletexTheme(String id){
        Zdfl zdfl = iZdflService.getZdfldataByID(id);
        String fd_flbm = zdfl.getFd_flbm();
        if(iZdmxService.isExistFlbm(fd_flbm)){
            return "fail";
        }
        iZdflService.deleteZdfl(id);
        return "success";
    }
}
