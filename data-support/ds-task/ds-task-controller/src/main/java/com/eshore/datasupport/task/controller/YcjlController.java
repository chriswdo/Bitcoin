package com.eshore.datasupport.task.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eshore.datasupport.task.pojo.Ycjl;
import com.eshore.datasupport.task.service.IYcjlService;
import com.eshore.datasupport.task.vo.Group;
import com.eshore.khala.core.api.IBaseService;
import com.eshore.khala.core.controller.pub.action.BaseController;

@Controller
@RequestMapping("/ycjumonitor")
public class YcjlController extends BaseController<Ycjl> {

    @Autowired
    IYcjlService ycjlService;

    @Override
    protected String getBasePath() {
        return "/datasupport";
    }

    @Override
    protected IBaseService<Ycjl> getService() {
        return ycjlService;
    }

    public String getModuleName() {
        return "任务调度管理>异常记录";
    }

    private static final Logger logger = Logger.getLogger(YcjlController.class);

    @RequestMapping(value = "getExceptionJsonData")
    @ResponseBody
    /**
     * 跳转到异常信息界面
     * @param jobid  
     * @param yname
     * @return
     * @throws UnsupportedEncodingException
     */
    public Map<String,Object> getExceptionJsonData(String jobid, String yname) throws UnsupportedEncodingException {
        String jobidd = "";
        if (jobid != null && !jobid.isEmpty()) {
            jobidd = jobid;
        }
        Map <String,Object>  retMap = new HashMap<String ,Object>();
        Ycjl ycjl = ycjlService.get(jobidd);
        byte[] obj = ycjl.getFd_ycsj();

        String str = new String(obj, "UTF-8");
        JSONArray jsonArray = JSONArray.fromObject(str);
        JSONObject js = jsonArray.getJSONObject(0);
        List<JSONObject> cols = new ArrayList<>();
        List<JSONObject> tempcols = new ArrayList<>();
        Iterator it = js.keys();
        while (it.hasNext()) {
            JSONObject col = new JSONObject();
            String key = String.valueOf(it.next());
            if ("fd_dsjzc_cwbm".equals(key)) {
                col.put("label", "异常编码");
                col.put("name", "fd_dsjzc_cwbm");
                tempcols.add(col);
            } else if ("fd_dsjzc_cwl".equals(key)) {
                col.put("label", "异常字段");
                col.put("name", "fd_dsjzc_cwl");
                tempcols.add(col);
            } else if ("fd_dsjzc_cwms".equals(key)) {
                col.put("label", "异常描述");
                col.put("name", "fd_dsjzc_cwms");
                tempcols.add(col);
            } else if ("fd_dsjzc_cws".equals(key)) {
                col.put("label", "异常数");
                col.put("name", "fd_dsjzc_cws");
                tempcols.add(col);
            } else {
                col.put("label", key);
                col.put("name", key);
                cols.add(col);
            }
        }
        for (JSONObject jsonObject : tempcols) {
            cols.add(jsonObject);
        }
        retMap.put("cols", cols);
        retMap.put("str", str);
        //mv.setViewName(getBasePath() + "/jsondata");
        return retMap;
    }

    @RequestMapping(value = "getycJsondata")
    @ResponseBody
    /**
     * 得到异常数据
     * @param jobid
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getycJsondata(String jobid) throws UnsupportedEncodingException {
        String jobidd = jobid;
        if (jobidd == null || jobidd.isEmpty()) {
            jobidd = "";
        }
        Ycjl ycjl = ycjlService.get(jobidd);
        byte[] obj = ycjl.getFd_ycsj();

        String str = new String(obj, "GBK");
        return str;
    }

    @RequestMapping(value = "exportYcdata")
    @ResponseBody
    public List<Map<String, Object>> exportYcdata(String jobid) throws UnsupportedEncodingException {
        String jobidd = jobid;
        if (jobidd == null || jobidd.isEmpty()) {
            jobidd = "";
        }
        List<Map<String, Object>> li = ycjlService.exportYcdata(jobidd);
        return li;
    }

    @RequestMapping(value = "exportexcel")
    @ResponseBody
    /**
     * 异常数据导出功能
     * @param response
     * @param jobid
     * @param yname
     * @throws ServletException
     */
    public void exportexcel(HttpServletResponse response, String jobid, String yname)
            throws ServletException{
        response.setContentType("octets/stream");
        String excelName = "异常数据表";
        //转码防止乱码
        try {
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(excelName.getBytes("gb2312"), "ISO8859-1") + ".xls");
		 
            OutputStream out = response.getOutputStream();
            List<Map<String, Object>> li = exportYcdata(jobid);
            exportExcel(excelName, yname, li, out);
            out.close();
            logger.info("excel导出成功！");
        }catch (UnsupportedEncodingException e) {
        	logger.info(e);
		} catch (FileNotFoundException e) {
            logger.info(e);
        } catch (IOException e){
            logger.info(e);
        }
    }

     private String[] getpizhuComment( String[] cwms, String[] cwbm, String[] cwl){
		   String[] htmls = new String[cwl.length];
			  Arrays.sort(cwl);
			   for (int i = 0; i < cwl.length; i++) {
				   htmls[i]=cwbm[i]+":"+cwms[i]+" ";
			}
			   //得到重复元素,重复元素的下标
		    	 List<Group> res=new ArrayList<Group>();	    		    
		    	 for(int i = 0; i < cwl.length;)  
		    	 {  
		    	  Group gr=new Group();
		    	  int count = 0;  
		    	  List<Integer> index=new ArrayList<Integer>();
		    	  for(int j=i;j<cwl.length;j++)  
		    	  {  
		    	        
		    	   if(cwl[i] == cwl[j])  
		    	   {  
		    	        count++; 
		    	        index.add(j);

		    	   }  		    	     
		    	  }  
		    	  gr.setRepeats(index);
		    	  gr.setZdname(cwl[i]);
		    	  res.add(gr);
		    	  i+=count;  	    	    
		    	 }   
		   //拼接重复元素的批注
		    	String copys="";
		    	for (Group group : res) {
		    		if(group.getRepeats().size()>1){
		    			 for (int i = 0; i < group.getRepeats().size(); i++) {
		    				 copys=copys+htmls[group.getRepeats().get(i)];
						}
		    			 htmls[group.getRepeats().get(group.getRepeats().size()-1)]=copys;
		    			 copys="";
		    		} 
				} 
		
    	   return htmls;
     }
    protected void exportExcel(String title, String name, List<Map<String, Object>> mapList, OutputStream out) throws UnsupportedEncodingException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        HSSFPatriarch p = sheet.createDrawingPatriarch(); 
        //设置表格默认列宽度为15个字符
        sheet.setDefaultColumnWidth(20);
        //生成一个样式，用来设置标题样式
        HSSFCellStyle style = workbook.createCellStyle();
        //设置这些样式
        
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式,用于设置内容样式
        /*HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);*/

        //产生数据源栏
        HSSFRow rowy = sheet.createRow(0);
        rowy.createCell(0).setCellValue(String.valueOf(mapList.get(0).get("FD_YXCS")));
        rowy.createCell(1).setCellValue(name);

        //写入数据导excel
        byte[] obj = (byte[]) mapList.get(0).get("FD_YCSJ");
        String str = new String(obj, "UTF-8");
        logger.info("blob : " + str);
        JSONArray ja = JSONArray.fromObject(str);
        JSONObject js = ja.getJSONObject(0);
        List<String> cols = new ArrayList<String>();
        Iterator it = js.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            if (!"fd_dsjzc_cwbm".equals(key) && !"fd_dsjzc_cwl".equals(key) && !"fd_dsjzc_cwms".equals(key)) {
                cols.add(key);
            }
        }

        //产生表格标题行
        HSSFRow row = sheet.createRow(1);
        for (int i = 0; i < cols.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(cols.get(i));
            cell.setCellValue(text);
        }

        List<Map<String, Object>> li = (List<Map<String, Object>>) JSONArray.toCollection(ja, Map.class);
	        String[] cwms=null ;
	        String[] cwbm=null;
	        String[] cwl=null;
	       // HSSFCellStyle style3 = workbook.createCellStyle();
        for (int i = 0; i < li.size(); i++) {
            Map<String, Object> map = li.get(i);
			   cwms= map.get("fd_dsjzc_cwms").toString().split("\\|");
			   cwbm= map.get("fd_dsjzc_cwbm").toString().split("\\|");
			   cwl= map.get("fd_dsjzc_cwl").toString().split("\\|");
		       String[] titles = getpizhuComment(cwms,cwbm,cwl);
            row = sheet.createRow(i + 2);
            for (int k = 0; k < cwl.length; k++) {
                for (int j = 0; j < cols.size(); j++) {
                	Cell cell=row.createCell(j);
                	if(cols.get(j).equals(cwl[k])){     
            			style = workbook.createCellStyle();
            			style.setFillForegroundColor(IndexedColors.RED.getIndex());
            			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                        HSSFComment comment = p.createComment(new HSSFClientAnchor(0, 0, 0, 0,  
                                (short) 3, 3, (short) 5, 6));  
                        // 输入批注信息  
                        comment.setString(new HSSFRichTextString(titles[k]));  
                        cell.setCellComment(comment); 
                        cell.setCellStyle(style);
                	}
                	cell.setCellValue(String.valueOf(map.get(cols.get(j))));
                }
			}

        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            logger.info(e);
        }
    }

}
