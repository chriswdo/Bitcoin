package com.eshore.datasupport.task.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.eshore.datasupport.task.dao.IKettleJobInfoDao;
import com.eshore.khala.core.data.jpa.dao.impl.JpaDaoImpl;

@Repository
public class KettleJobInfoDaoImpl  extends JpaDaoImpl implements IKettleJobInfoDao{
	
	public final static String CODE_TRANS="trans_object_id";

	@Override
	public List<String> getAllTransId(String jobId) {
		String sql = " select  value_str  from  r_jobentry_attribute where id_job= ?  and  code='trans_object_id' " ;
		List<Map<String,Object>>list = this.iSQLQuery.query(sql, new Object[]{jobId});
		List<String>retList = new ArrayList<String>();
		for(Map<String,Object>map:list){
			retList.add((String)map.get("VALUE_STR"));
		}
		return retList;
	}
	
	/* 
	 *选出输入信息，剔除掉连接在校验上的输入信息 
	 */
	@Override
	public List<String> getInputName(String transId) {
		String sql = "select t.name, hop.id_trans_hop  from  r_step t "+	
				"join r_step_type y 													"+
				"on   t.id_transformation=?                                             "+
				"     and y.code ='TableInput'                                          "+
				"     and t.id_step_type=y.id_step_type                                 "+
				"left join                                                              "+
				"     (select * from r_trans_hop t where id_transformation=? ) hop      "+
				"on t.id_step=hop.id_step_from                                          "+
				"     and hop.id_step_to in                                             "+
				"         (select id_step from r_step s                                 "+
				"                 join r_step_type t                                    "+
				"                 on t.code='Validator'                                 "+
				"                    and s.id_step_type = t.id_step_type                "+
				"                    and s.id_transformation=?                          "+
				"          )                                                            "+
				" where id_trans_hop is null                                            ";
		List<Map<String,Object>>list = this.iSQLQuery.query(sql, new Object[]{transId,transId,transId});
		List<String>retList = new ArrayList<String>();
		for(Map<String,Object>map:list){
			retList.add((String)map.get("NAME"));
		}
		return retList;
	}

	@Override
	public List<String> getOutputName(String transId) {
		String sql = 	"	select t.name from  r_step t  "+
						"	join r_step_type y on   t.id_transformation=?  "
						+ "	and (y.code ='TableOutput' or y.code = 'InsertUpdate') and t.id_step_type=y.id_step_type ";
		List<Map<String,Object>>list = this.iSQLQuery.query(sql, new Object[]{transId});
		List<String>retList = new ArrayList<String>();
		for(Map<String,Object>map:list){
			retList.add((String)map.get("NAME"));
		}
		return retList;
	}

	@Override
	public List<String> getValidByTransId(String transId) {
		String sql = 	"	select value_str  from    r_step_attribute t where t.id_transformation=?  and code='validator_field_validation_name' ";
		List<Map<String,Object>>list = this.iSQLQuery.query(sql, new Object[]{transId});
		List<String>retList = new ArrayList<String>();
		for(Map<String,Object>map:list){
			retList.add((String)map.get("VALUE_STR"));
		}
		return retList;
	}

	@Override
	public List<Map<String, Object>> getChartInfo(String jobId) {
		String sql = 	"	select t.name,t.id_jobentry,hop.id_jobentry_copy_to ,ty.code from r_jobentry t  "+
						"	join r_jobentry_type ty		"+
						"	on t.id_jobentry_type = ty.id_jobentry_type and t.id_job= ? " +
						"	left join r_job_hop  hop 		"+
						"	on t.id_jobentry = hop.id_jobentry_copy_from 	";
		List<Map<String,Object>>list = this.iSQLQuery.query(sql,new Object[]{jobId});
		return list;
	}

	@Override
	public Set<String> getKettleJobIdByinputSource(String inputDataSourceId) {
		String sql = 
				" select t.id_transformation from r_step_attribute t 										 "+
				" join r_step r on t.id_step=r.id_step  and t.code='id_connection'           				 "+
				" join dsjzc_sjyb sj on sj.fd_kid=t.value_num   and  sj.id = ?   							 "+
				" join r_step_type typ on typ.id_step_type=r.id_step_type and typ.code='TableInput'          "+
				" join r_trans_hop hop on hop.id_step_from=t.id_step                                         "+
				" join r_step st on st.id_step=hop.id_step_to                                                "+
				" left join r_step_type ty on ty.id_step_type= st.id_step_type and ty.code='Validator'       "+
				" where ty.id_step_type is null                                                              ";
		return getKettleJobIdBySource(sql ,inputDataSourceId);
	}

	@Override
	public Set<String> getKettleJobIdByoutputSource(String outputDataSourceId) {
		String sql = 
				 " select t.id_transformation from r_step_attribute t                     											"+	
				 " join r_step r on t.id_step=r.id_step  and t.code='id_connection'           										"+
				 " join dsjzc_sjyb sj on sj.fd_kid=t.value_num   and  sj.id = ?   													"+
				 " join r_step_type typ on typ.id_step_type=r.id_step_type and (typ.code='TableOutput' or typ.code='InsertUpdate')  ";
		return getKettleJobIdBySource(sql ,outputDataSourceId);
	}
	
	private Set<String> getKettleJobIdBySource(String sql ,String sourceId){
		List<Map<String,Object>>list = this.iSQLQuery.query(sql, new Object[]{sourceId});
		Set<String>retSet = new HashSet<String>();
		for(Map<String,Object>map:list){
			retSet.add(map.get("ID_TRANSFORMATION")+"");
		}
		String sql1 = "SELECT t.id_job,t.value_str FROM r_jobentry_attribute t WHERE t.CODE='trans_object_id' ";
		List<Map<String,Object>>list1 = this.iSQLQuery.query(sql1);
		Set<String>retIdSet = new HashSet<String>();
		for(Map<String,Object>map:list1){
			if(retSet.contains(map.get("VALUE_STR"))){
				retIdSet.add(map.get("ID_JOB")+"");
			}
		}
		return retIdSet;
	}
	
}
