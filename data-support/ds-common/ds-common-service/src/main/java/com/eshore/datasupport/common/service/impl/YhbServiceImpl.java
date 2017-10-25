package com.eshore.datasupport.common.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.eshore.datasupport.common.dao.IYhbDao;
import com.eshore.datasupport.common.dao.IYhzyDao;
import com.eshore.datasupport.common.dao.IZypzDao;
import com.eshore.datasupport.common.pojo.Yhb;
import com.eshore.datasupport.common.pojo.Yhzy;
import com.eshore.datasupport.common.service.IYhbService;
import com.eshore.khala.common.model.PageConfig;
import com.eshore.khala.core.data.api.dao.IBaseDao;
import com.eshore.khala.core.service.impl.BaseServiceImpl;

/**
 * �����֧��ƽ̨�û���ҵ��ӿ�ʵ��
 *
 * @author
 * @version 1.0 2012-10-19
 */
@Service
@Transactional
public class YhbServiceImpl extends BaseServiceImpl<Yhb> implements IYhbService {
    private static final Logger logger = Logger.getLogger(YhbServiceImpl.class);

    @Autowired
    IYhbDao yhbDao;

    @Autowired
    IZypzDao zypzDao;

    @Autowired
    IYhzyDao yhzyDao;

    @Override
    public IBaseDao<Yhb> getDao() {
        return (IBaseDao<Yhb>) yhbDao;
    }

    @Override
    public List<Yhb> listByYhmc(Map<String, String> params, PageConfig pc) {
        return yhbDao.listByYhmc(params, pc);
    }

    @Override
    public String getMd5Password(String fd_mm) {
        MessageDigest md;
        String encryStr = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(fd_mm.getBytes("utf-8"));
            byte[] b = md.digest();
            encryStr = byte2str(b);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.info(e);
        }
        return encryStr;
    }

    static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String byte2str(byte[] bytes) {
        int len = bytes.length;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            byte byte0 = bytes[i];
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }

    @Override
    public Yhb getYhbByFd_dlmc(String fd_dlmc) {
        return this.yhbDao.getYhbByFd_dlmc(fd_dlmc);
    }

    @Override
    public boolean Fd_dlmcIsExist(String fd_dlmc) {
        if (yhbDao.getYhbByFd_dlmc(fd_dlmc) != null) {
            return true;
        }
        return false;
    }

    @Override
    public void getNodes(List<Map<String, Object>> list, List<Map<String, Object>> nodes) {
        for (int i = 0; i < list.size(); i++) {
            if ("0".equals(list.get(i).get("fd_fjid"))) {
                nodes.add(generateNode(list, i));
            }
        }
    }

    private Map<String, Object> generateNode(List<Map<String, Object>> list, int flag) {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("icon", list.get(flag).get("fd_tb"));
        root.put("name", list.get(flag).get("fd_mc"));
        List<Map<String, Object>> node_list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(flag).get("id").equals(list.get(i).get("fd_fjid"))) {
                Map<String, Object> node_temp = new HashMap<String, Object>();
                node_temp.put("icon", list.get(i).get("fd_tb"));
                node_temp.put("name", list.get(i).get("fd_mc"));
                node_temp.put("url", list.get(i).get("fd_dz"));
                node_list.add(generateNode(list, i));
            }
        }
        //循环调用
        if (node_list == null || node_list.isEmpty()) {
            root.put("url", list.get(flag).get("fd_dz"));
        } else {
            root.put("children", node_list);
        }
        return root;
    }

    @Override
    public void modifyxSjybRecord(Yhb yhb, String nodes) {
        Yhb yhbDb = yhbDao.get(yhb.getId());
        yhbDb.setFd_yhmc(yhb.getFd_yhmc());
        yhbDb.setFd_lxfs(yhb.getFd_lxfs());
        yhbDb.setFd_yx(yhb.getFd_yx());
        yhbDb.setFd_zt(yhb.getFd_zt());
        yhbDb.setFd_gxr(yhb.getFd_gxr());
        yhbDb.setFd_gxsj(yhb.getFd_gxsj());
        saveMenuInfo(nodes, yhbDb);
        yhbDao.update(yhbDb);
    }

    private void saveMenuInfo(String nodes, Yhb yhb) {
        //清空该用户的用户资源表信息
        List<Yhzy> existYhzy = getSpecifylYhzy(yhb.getId());
        //将前台传送过来的json数据解析出来
        JSONArray jarray = JSONArray.parseArray(nodes);
        for (Object o : jarray) {
            Map<String, Object> map = (Map<String, Object>) o;
            saveSingleNode(map, yhb, existYhzy);
        }
    }

    private List<Yhzy> getSpecifylYhzy(String fd_yhid) {
        List<Yhzy> list = yhzyDao.getSpecifyYhzy(fd_yhid);
        return list;
    }

    private void saveSingleNode(Map<String, Object> map, Yhb yhb, List<Yhzy> existYhzy) {
        for (Yhzy yhzy : existYhzy) {
            if (yhzy.getFd_zyid().equals(map.get("id"))) {
                if (!(boolean) map.get("checked")) {
                    yhzyDao.delete(yhzy.getId());
                }
                //如果存在子节点   迭代处理
                nestedHandlerChildrenNode(map, yhb, existYhzy);
                return;
            }
        }
        if (!(boolean) map.get("checked")) {
            return;
        }
        Yhzy yhzy = new Yhzy();
        yhzy.setFd_zyid(map.get("id") + "");
        yhzy.setFd_yhid(yhb.getId());
        yhzy.setFd_cjr(yhb.getFd_gxr() == null ? yhb.getFd_cjr() : yhb.getFd_gxr());
        yhzy.setFd_cjsj(new Date());
        yhzyDao.save(yhzy);
        //如果存在子节点   迭代处理
        nestedHandlerChildrenNode(map, yhb, existYhzy);
    }

    private void nestedHandlerChildrenNode(Map<String, Object> map, Yhb yhb, List<Yhzy> existYhzy) {
        if (map.get("children") != null) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("children");
            for (Map<String, Object> map_temp : list) {
                saveSingleNode(map_temp, yhb, existYhzy);
            }
        }
    }

    @Override
    public void savexYhbRecord(Yhb yhb, String nodes) {
        saveMenuInfo(nodes, yhb);
        //设置初始密码
        yhb.setFd_mm(this.getMd5Password("12345678"));
        this.save(yhb);
    }
}
