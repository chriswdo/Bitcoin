package com.eshore.datasupport.metadata.controller;

import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.eshore.datasupport.metadata.pojo.Sjyb;
import com.eshore.datasupport.metadata.service.ISjybService;
import com.eshore.datasupport.metadata.service.IYsjzdService;
import com.eshore.datasupport.metadata.vo.DataSourceTs;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.metadata.pojo.Ysjb;
import com.eshore.datasupport.metadata.service.IYsjbService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;


@Controller
@RequestMapping("/metacontroller")
public class YsjbController extends BaseController<Ysjb> {

    private final Logger logger = Logger.getLogger(YsjbController.class);
    @Autowired
    IYsjbService ysjbService;

    @Autowired
    IYsjzdService ysjzdService;

    @Autowired
    ISjybService sjybService;

    @Override
    protected String getBasePath() {
        return "/datasupport";
    }

    @Override
    protected IBaseService<Ysjb> getService() {
        return ysjbService;
    }

    public String getModuleName() {
        return "元数据管理>元数据表";
    }

    @RequestMapping(value = "ajaxMetedataTable")
    @ResponseBody
    /**
     * 根据数据源ID查元数据表
     * @param opt  数据源ID
     * @param pc   分页
     * @return
     */
    public Map<String, Object> ajaxMetedataTable(String opt, PageConfig pc) {
        String yuanid = opt;
        List<Ysjb> relist = ysjbService.getMetedataTable(yuanid, pc);
        //得到数据源list
		  PageConfig pg=new PageConfig();
		  pg.setPageSize(Integer.MAX_VALUE);
		  List<Sjyb>  selist=sjybService.list(new Sjyb(), pg);
       // List<Map<String, Object>> selist = ysjbService.getDatayuan();
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("list", relist);
        retMap.put("selist", selist);
        retMap.put("total", pc.getRowCount());
        return retMap;

    }

    @RequestMapping(value = "getDatayuan")
    public ModelAndView getDatayuan(String id) {
        ModelAndView mv = new ModelAndView();
		  PageConfig pg=new PageConfig();
		  pg.setPageSize(Integer.MAX_VALUE);
		  List<Sjyb>  telist=sjybService.list(new Sjyb(), pg);
       // List<Map<String, Object>> telist = ysjbService.getDatayuan();
        if (StringUtils.isNotBlank(id)) {
        	Sjyb tempMap = null;
            Iterator<Sjyb> it = telist.iterator();
            while (it.hasNext()) {
            	Sjyb m = it.next();
                if (id.equals(m.getId())) {
                    tempMap = m;
                    it.remove();
                }
            }
            telist.add(0, tempMap);
            mv.addObject("selectId", id);
        }
        mv.addObject("selist", telist);
        mv.setViewName(getBasePath() + "/metadatapage");
        logger.info(getBasePath() + "/metadatapage");
        return mv;

    }

    @RequestMapping(value = "getDatapage")
    public ModelAndView getDatapage(String selectId) {
        ModelAndView mv = new ModelAndView();
        //List<Map<String, Object>> telist = ysjbService.getDatayuan();
		  PageConfig pg=new PageConfig();
		  pg.setPageSize(Integer.MAX_VALUE);
		  List<Sjyb>  telist=sjybService.list(new Sjyb(), pg);
        if (StringUtils.isNotBlank(selectId)) {
        	Sjyb tempMap = null;
            Iterator<Sjyb> it = telist.iterator();
            while (it.hasNext()) {
            	Sjyb m = it.next();
                if (selectId.equals(m.getId())) {
                    tempMap = m;
                    it.remove();
                }
            }
            telist.add(0, tempMap);
        }
        mv.addObject("selist", telist);
        mv.setViewName(getBasePath() + "/ysjxz");
        logger.info(getBasePath() + "/ysjxz");
        return mv;
    }

    /**
     * http://localhost:8080/data-support-web/metacontroller/getDataSourceTs?tableName=test_t
     * 提供平台交换库的元数据表及结构查询接口
     * ,produces = "text/plain; charset=UTF-8"
     * @param tableName
     * @return
     */
    @RequestMapping(value = "getDataSourceTs",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getDataSourceTs(String tableName){
        Map<String,String>params = new HashMap<>();
        params.put("fd_lx", "2");
        List<Sjyb> sjyblist = sjybService.listSjybRecord(params, null);
        String tableComment = getComment(sjyblist,tableName);
        String id = ysjbService.getTableId(tableName,tableComment);
        List<DataSourceTs> list = ysjzdService.getDataSourceTs(id);
        logger.info("list大小"+list.size());
        Map<String, Object> map1 = new HashMap<>();
        map1.put("tableName", tableName);
        map1.put("tableComment", tableComment);
        map1.put("columns",list);
        return JSONObject.toJSONString(map1, SerializerFeature.WriteMapNullValue);
    }

    private String getComment(List<Sjyb> sjyblist,String bm){
        String tableComment="";
        for(Sjyb sjyb:sjyblist){
            String fd_sjy_id = sjyb.getId();
            tableComment = ysjbService.getTableComment(fd_sjy_id,bm);
            logger.info(tableComment+"--------");
            if(StringUtils.isNotBlank(tableComment)){
                return tableComment;
            }
        }
        return tableComment;
    }

}
