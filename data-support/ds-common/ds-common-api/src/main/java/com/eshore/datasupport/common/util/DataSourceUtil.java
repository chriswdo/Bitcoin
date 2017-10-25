package com.eshore.datasupport.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.eshore.datasupport.common.vo.YsjbDescribeVo;
import com.eshore.datasupport.common.vo.YsjzdVo;
import com.eshore.khala.common.utils.type.StringUtils;

public class DataSourceUtil {
	private static final Logger logger = Logger.getLogger(DataSourceUtil.class);

	private DataSourceUtil() {

	}

	public static String getDriverName(String dbName) {
		String driverName = null;
		if (dbName != null) {
			DataSourceEnum[] enums = DataSourceEnum.values();
			for(DataSourceEnum en: enums){
				if(dbName.equalsIgnoreCase(en.getValue())){
					driverName = en.getDriverName();
				}
			}
		}
		return driverName;
	}


	/**
	 * 
	 * @param dbName
	 *            数据库类型名称
	 * @param ip
	 *            ip地址
	 * @param dk
	 *            端口
	 * @param sjkmc
	 *            数据库名称
	 * @return
	 */
	public static String getUrl(String dbName, String ip, int dk, String sjkmc) {
		String url = null;
		if (dbName != null) {
			if (DataSourceEnum.MYSQL.getValue().equalsIgnoreCase(dbName))
				url = "jdbc:mysql://" + ip + ":" + dk + "/" + sjkmc;
			if (DataSourceEnum.ORACLE.getValue().equalsIgnoreCase(dbName))
				url = "jdbc:oracle:thin:@" + ip + ":" + dk + ":" + sjkmc;
			if (DataSourceEnum.DB2.getValue().equalsIgnoreCase(dbName))
				url = "jdbc:db2://" + ip + ":" + dk + "/" + sjkmc;
			if (DataSourceEnum.GREENPLUM.getValue().equalsIgnoreCase(dbName))
				url = "jdbc:postgresql://" + ip + ":" + dk + "/" + sjkmc;
			if (DataSourceEnum.SYBASE.getValue().equalsIgnoreCase(dbName))
				url = "jdbc:jtds:sybase://" + ip + ":" + dk + "/" + sjkmc;
		}
		return url;
	}
	
    public  Connection getConnection(String driverName, String url, String fd_yhm,String fd_mm) {
        //测试连接
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,fd_yhm,fd_mm);
            return conn;
        } catch (ClassNotFoundException e) {
            logger.info(e);
        } catch (SQLException e) {
            logger.info(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    logger.info(e);
                }
            }
        }
        return null;
    }

	/**
	 * 数据库日期时间处理
	 * 
	 * @param db
	 *            数据库类型
	 * @param date
	 *            日期字符串 date为字段名 或者带''的格式后的字符串
	 * @param model
	 *            模式:0 字符串转日期 1：日期转字符串
	 */
	public static String getDateSqlString(String db, String date, String model) {
		if ("com.mysql.jdbc.driver".equalsIgnoreCase(db)) {
			if ("0".equals(model)) {// 转成日期类型
				return "str_to_date(" + date + "," + Conts.MYSQL_DATE + ")";
			} else {// 转成字符串
				return "DATE_FORMAT(" + date + "," + Conts.MYSQL_DATE + ")";
			}
		} else if ("oracle.jdbc.driver.OracleDriver".equalsIgnoreCase(db)) {
			if ("0".equals(model)) {
				return "to_date(" + date + "," + Conts.ORACLE_DATE + ")";
			} else {
				return "to_char(" + date + "," + Conts.ORACLE_DATE + ")";
			}
		} else {
			return null;
		}
	}

	public static String getKeyMessage(String url, String driveclass, String sql, String username, String password)
			throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String res = "";
		try {
			Class.forName(driveclass);
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				res = "kong";
			} else {
				res = "you";
			}
		} catch (Exception e) {
			logger.info(e);
		} finally {
			rsClose(rs);
			stmtClose(stmt);
			connClose(conn);
		}
		return res;
	}

	public static List<YsjzdVo> getcolmesBymysql(Connection conn, String bm) throws SQLException {
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		String cd = "";
		String jd = "0";
		List<YsjzdVo> relist = new ArrayList<YsjzdVo>();
		try {
			sql = "SHOW FULL COLUMNS FROM  "+bm+"";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String sfzj = "";
			short i = 1;
			while (rs.next()) {
				if (rs.getString(2).toString().indexOf(')') != -1) {
					String strNew = ((String) rs.getString(2)).replaceAll("[a-zA-Z]", "");
					String strNew1 = strNew.replace("(", "");
					cd = strNew1.replace(")", "").trim();
				} else if (rs.getString(2).toString().indexOf(",") != -1) {
					String str = rs.getString(2).toString().split(",")[0].replaceAll("[a-zA-Z]", "");
					cd = str.replace("(", "").trim();
					jd=rs.getString(2).toString().split(",")[1].replace(")", "").trim();
				} else {
					cd = "0";
				}
				if (rs.getString(5).toString().indexOf("P") != -1) {
					sfzj = "Y";
				} else {
					sfzj = "N";
				}

				YsjzdVo ysjzdvo = new YsjzdVo();
				ysjzdvo.setZdmc(rs.getString(1));
				ysjzdvo.setZdlx(rs.getString(2));
				ysjzdvo.setSfkwk(rs.getString(4));
				ysjzdvo.setSfzj(sfzj);
				ysjzdvo.setQsz(rs.getString(6));
				ysjzdvo.setZdbz(rs.getString(9));
				ysjzdvo.setZdcd(Short.valueOf(cd));
				ysjzdvo.setZdjd(Short.valueOf(jd));
				ysjzdvo.setZdsx(i++);
				relist.add(ysjzdvo);
			}
		} catch (Exception e) {
			logger.info(e);
		} finally {
			rsClose(rs);
			stmtClose(stmt);
			connClose(conn);
		}

		return relist;
	}

	public static List<YsjzdVo> getcolmesByoracle(Connection conn, String bm,String db) throws SQLException {
		String sql = "";
		PreparedStatement pstmt = null;
		List<YsjzdVo> relist = new ArrayList<YsjzdVo>();
		ResultSet rs = null;
		try {
			if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(db) ){
				sql = "select a.COLUMN_NAME,a.DATA_TYPE,a.CHAR_LENGTH,decode(c.constraint_type,'P','Y','N') as CONSTRAINT_TYPE,a.DATA_DEFAULT,a.NULLABLE,COALESCE(b.comments,'') as COMMENTS,a.COLUMN_ID,a.DATA_PRECISION,a.DATA_SCALE  from USER_TAB_COLUMNS a  inner join user_col_comments b on a.TABLE_NAME=b.table_name and a.COLUMN_NAME=b.column_name left join ( select cu.table_name,cu.column_name,au.constraint_type from user_cons_columns cu  left join user_constraints au on cu.constraint_name = au.constraint_name and au.constraint_type = 'P' where au.TABLE_NAME=? ) c on c.table_name=a.TABLE_NAME and c.column_name=b.column_name where a.TABLE_NAME=?  order by column_id";
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, bm.toUpperCase()); 
				 pstmt.setString(2, bm.toUpperCase());  
			}else{
				sql = "SELECT  a.name  COLUMN_NAME,a.coltype  DATA_TYPE ,a.longlength DATA_LENGTH, decode(n.uniquerule,'P','Y','N') CONSTRAINT_TYPE,a.default DATA_DEFAULT,a.nulls  NULLABLE,COALESCE(a.remarks, '')  COLUMN_COMMENTS,a.colno COLUMN_ID,a.LENGTH,a.SCALE  FROM sysibm.syscolumns a INNER JOIN sysibm.systables d on a.tbname=d.name  LEFT JOIN sysibm.sysindexes n on n.tbname=d.name and SUBSTR(colnames,2)=a.name where  d.type='T' and d.name=?";
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, bm.toUpperCase()); 
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				YsjzdVo ysjzdvo = new YsjzdVo();
				ysjzdvo.setZdmc(rs.getString(1));
				ysjzdvo.setZdlx(rs.getString(2));
				ysjzdvo.setSfkwk(rs.getString(6));
				ysjzdvo.setSfzj(rs.getString(4));
				ysjzdvo.setQsz(rs.getString(5));
				ysjzdvo.setZdbz(rs.getString(7));
				ysjzdvo.setZdcd(Short.valueOf(rs.getString(3)));
				ysjzdvo.setZdjd((rs.getString(10)==null?0:Short.valueOf(rs.getString(10))));
				ysjzdvo.setZdsx(rs.getShort(8));
				relist.add(ysjzdvo);
			}
		} catch (Exception e) {
			logger.info(e);
		} finally {
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}

		return relist;
	}

	public static boolean equalList(List<String> list1, List<String> list2) {
        return (list1.size() == list2.size()) && list1.containsAll(list2);
   }
	
	public static List<YsjzdVo> getcolmesBysybase(Connection conn, String bm) throws SQLException {
		String sql = "";
		List<YsjzdVo> relist = new ArrayList<YsjzdVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			sql = "select  a.colid as id,a.name as col_name,c.name as type,a.length as all_length,a.prec as length,a.scale,case isnull(a.status,0) when 0 then 'N' ELSE 'Y' END AS isnull  from  syscolumns a,sysobjects b,systypes c  where a.id=b.id and a.usertype=c.usertype and b.name=?  order by a.colid";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, bm); 
			 rs = pstmt.executeQuery();
				//得到有主键约束的字段名称
				List<String> columnames=getFieldNamesOfRelatedIndexByIndid(conn,bm);
			while (rs.next()) {
				YsjzdVo ysjzdvo = new YsjzdVo();
				ysjzdvo.setZdmc(rs.getString(2));
				ysjzdvo.setZdlx(rs.getString(3));
				ysjzdvo.setSfkwk(rs.getString(7));
				ysjzdvo.setQsz("");
				ysjzdvo.setZdbz("");
				ysjzdvo.setZdcd(Short.valueOf(rs.getString(4)));
				ysjzdvo.setZdjd((rs.getString(6)==null?0:Short.valueOf(rs.getString(6))));
				ysjzdvo.setZdsx(rs.getShort(1));
				ysjzdvo.setSfzj(columnames.contains(rs.getString(2))?"Y":"N");
				relist.add(ysjzdvo);
			}
			
		} catch (Exception e) {
			logger.info(e);
		} finally {
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}
		return relist;
	}

	 private static List<String> getFieldNamesOfRelatedIndexByIndid(Connection conn, String bm) throws SQLException{  
	        String sql="";
			ResultSet rs = null;
			PreparedStatement pstmt = null;
			List<String> li=new ArrayList<String>();
			int indid = 0;  //主键或索引在系统表 sysindexes 中的 indid 字段的值  
	        int keycnt = 0; //主键或索引涉及的列的数量
			sql = "select indid, keycnt from sysindexes where status&2048=2048 and id=object_id(?) ";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, bm); 
			 rs = pstmt.executeQuery();
			 if(rs.next()){  
			      indid = rs.getInt(1);    
		          keycnt = rs.getInt(2);   	
                  li=getkeyColumns(indid, keycnt, bm, conn);
			 }	 
			return li;
	 }
	
	private static List<String>  getkeyColumns(int indid, int keycnt, String bm,Connection conn) throws SQLException{
        StringBuffer buff = new StringBuffer(); 
        buff.append("select ");  
        Statement st=conn.createStatement();
        for (int i = 1; i <= keycnt; i++) {  
            if(i>1)  
                buff.append(", ");  
            buff.append("index_col('").append(bm).append("',").append(indid).append(",").append(i).append(")");  
        }  
        buff.append(" from sysindexes where indid=").append(indid).append(" and id=object_id('").append(bm).append("')");  	 
        ResultSet rs = st.executeQuery(buff.toString());
       List<String> li = new ArrayList<String>();  
        if(rs.next()){  
            String columnName = null;  
            for (int i = 1; i <= keycnt; i++) {  
                columnName = StringUtils.trimToNull(rs.getString(i));  
                if(columnName == null)  
                    break;  
                li.add(columnName);  
            }  
        }
       return li; 
	}
	
	public static List<YsjzdVo> getcolmesBygrem(Connection conn, String bm) throws SQLException {
		String sql = "";
		String cd = "";
		String jd = "0";
		String lxstr = "";
		List<YsjzdVo> relist = new ArrayList<YsjzdVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			sql = "select a.attname as COLUMN_NAME,format_type(a.atttypid, a.atttypmod) AS DATA_TYPE,decode(a.attnotnull,'t','N','Y') as NULLABLE, decode(d.contype,'p','Y','N') as CONSTRAINT_TYPE,COALESCE(e.description,'')  as COLUMN_COMMENTS,COALESCE(f.adsrc,'') as DATA_DEFAULT,a.attnum as COLUMN_ID from  pg_attribute a  inner join pg_class c on a.attrelid = c.oid   inner join pg_authid b on c.relowner = b.oid  left join  pg_constraint d on d.conrelid=a.attrelid and a.attnum = any(d.conkey)  left join pg_description e on e.objoid=a.attrelid and e.objsubid=a.attnum  left join pg_attrdef f on f.adrelid=a.attrelid and f.adnum=a.attnum  where  c.relname = ?   and a.attnum > 0    order by a.attnum;";
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, bm); 
			 rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString(2).toString().indexOf("(") != -1) {
					String strNew = ((String) rs.getString(2)).replaceAll("[a-zA-Z]", "");
					int a = rs.getString(2).toString().indexOf("(");
					lxstr = rs.getString(2).toString().substring(0, a);
					String strNew1 = strNew.replace("(", "");
					cd = strNew1.replace(")", "").trim();
				} else if (rs.getString(2).toString().indexOf(",") != -1) {
					String str = rs.getString(2).toString().split(",")[0].replaceAll("[a-zA-Z]", "");
					cd = str.replace("(", "").trim();
					jd=rs.getString(2).toString().split(",")[1].replace(")", "").trim();
				} else {
					cd = "0";
					lxstr = rs.getString(2);
				}

				YsjzdVo ysjzdvo = new YsjzdVo();
				ysjzdvo.setZdmc(rs.getString(1));
				ysjzdvo.setZdlx(lxstr);
				ysjzdvo.setSfkwk(rs.getString(3));
				ysjzdvo.setSfzj(rs.getString(4));
				ysjzdvo.setQsz(rs.getString(6));
				ysjzdvo.setZdbz(rs.getString(5));
				ysjzdvo.setZdcd(Short.valueOf(cd));
				ysjzdvo.setZdjd(Short.valueOf(jd));
				ysjzdvo.setZdsx(rs.getShort(7));
				relist.add(ysjzdvo);
			}
		} catch (Exception e) {
			logger.info(e);
		} finally {
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}
		return relist;
	}

	public static List<YsjzdVo> getMetadatacolm(String db, String bm, Connection conn) throws SQLException {
		List<YsjzdVo> res = new ArrayList<YsjzdVo>();
		if (DataSourceEnum.MYSQL.getDriverName().equalsIgnoreCase(db)) {
			res = getcolmesBymysql(conn, bm);
		} else if (DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(db) || DataSourceEnum.DB2.getDriverName().equalsIgnoreCase(db)) {
			res = getcolmesByoracle(conn, bm,db);
		}else if (DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(db)) {
			res = getcolmesBygrem(conn, bm);
		}else if (DataSourceEnum.SYBASE.getDriverName().equalsIgnoreCase(db)) {
			res = getcolmesBysybase(conn, bm);   //sybase数据库连接
		} else {
			return res;
		}

		return res;
	}

	// 根据驱动得到对数据库同步表的描述(oracle,db2)
	public static String getysjbDescribeSql(String db) {
		String str = "";
		if (DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(db)) {
			str = "select A.TABLE_NAME,B.comments from user_tables a inner join user_tab_comments b on a.TABLE_NAME=b.table_name and b.table_type='TABLE' where A.TABLE_NAME=?";
		} else if (DataSourceEnum.DB2.getDriverName().equalsIgnoreCase(db)) {
			str = "SELECT  d.name TABLE_NAME,COALESCE(d.remarks, '')  TABLE_COMMENTS FROM  sysibm.systables d  where  d.type='T' and d.name=?";
		}
		return str;
	}

	// 根据驱动得到对数据库同步表的描述(mysql,greeplum)
	public static String getysjbDescribeSql1(String db) {
		String str = "";
		if (DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(db)) {
			str = "select a.relname TABLE_NAME,COALESCE(b.description,'') as TABLE_COMMENTS from pg_class a left join  pg_description b on a.oid=b.objoid  and b.objsubid=0  where a.relname=?";
		} else if (DataSourceEnum.MYSQL.getDriverName().equalsIgnoreCase(db)) {
			str = "SHOW TABLE STATUS WHERE NAME =?";
		}
		return str;
	}
	
	public static List<YsjbDescribeVo> getysjbMeg(String db, String bm, Connection conn) throws SQLException {
		String str = "";
		List<YsjbDescribeVo> res = new ArrayList<YsjbDescribeVo>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			// 根据驱动得到对数据库同步表的描述
			if (DataSourceEnum.MYSQL.getDriverName().equalsIgnoreCase(db) ) {
				 str =getysjbDescribeSql1(db);
				 pstmt = conn.prepareStatement(str);
				 pstmt.setString(1, bm); 
				 rs = pstmt.executeQuery();
					while (rs.next()) {
						YsjbDescribeVo desVo=new YsjbDescribeVo();
						desVo.setTableName(rs.getString(1));
						desVo.setTableDescribe(rs.getString(18));
						res.add(desVo);
					}
				}else if( DataSourceEnum.GREENPLUM.getDriverName().equalsIgnoreCase(db)){
					 str =getysjbDescribeSql1(db);
					 pstmt = conn.prepareStatement(str);
					 pstmt.setString(1, bm); 
					 rs = pstmt.executeQuery();
						while (rs.next()) {
							YsjbDescribeVo desVo=new YsjbDescribeVo();
							desVo.setTableName(rs.getString(1));
							desVo.setTableDescribe(rs.getString(2));
							res.add(desVo);
						}
				} else if(DataSourceEnum.ORACLE.getDriverName().equalsIgnoreCase(db) || DataSourceEnum.DB2.getDriverName().equalsIgnoreCase(db))
				{
				 str = getysjbDescribeSql(db);
				 pstmt = conn.prepareStatement(str);
				 pstmt.setString(1, bm.toUpperCase()); 
				 rs = pstmt.executeQuery();
				while (rs.next()) {
					YsjbDescribeVo desVo=new YsjbDescribeVo();
					desVo.setTableName(rs.getString(1));
					desVo.setTableDescribe(rs.getString(2));
					res.add(desVo);
				}	
			}else{
				YsjbDescribeVo desVo=new YsjbDescribeVo();
				desVo.setTableName(bm);
				desVo.setTableDescribe("");
				res.add(desVo);
			}
		} catch (Exception e) {
			logger.info(e);
		} finally {
			rsClose(rs);
			pstmtClose(pstmt);
			connClose(conn);
		}
		return res;
	}

	/**
	 * 关闭连接
	 * @param rs
	 */
	private static void rsClose(ResultSet rs){
		try{
			if (rs != null) {
				rs.close();
			}
		}catch (Exception e){
			logger.info(e);
		}
	}

	/**
	 * 关闭连接
	 * @param stmt
	 */
	private static void stmtClose(Statement stmt){
		try{
			if (stmt != null ) {
				stmt.close();
			}
		}catch (Exception e){
			logger.info(e);
		}
	}

	/**
	 * 关闭连接
	 * @param stmt
	 */
	private static void pstmtClose(PreparedStatement stmt){
		try{
			if (stmt != null ) {
				stmt.close();
			}
		}catch (Exception e){
			logger.info(e);
		}
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	private static void  connClose(Connection conn){
		try{
			if (conn != null) {
				conn.close();
			}
		}catch (Exception e){
			logger.info(e);
		}
	}

	/**
	 * 
	 * @param url  
	 * @param driver
	 * @param yhm  用户名
	 * @param mm   密码
	 * @param sqlInfo  要执行的sql
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static List<Map<String, Object>> getQuerySql(String url, String driver, String yhm, String mm, String sqlInfo) throws  Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Map<String, Object>>retList = new ArrayList<Map<String, Object>>();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, yhm, mm);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlInfo);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				Map<String, Object> map = new HashMap<String,Object>();
				ResultSetMetaData meta = rs.getMetaData();
				int columnCount  = meta.getColumnCount();
				for(int i=1;i<=columnCount;i++){
					String columnLabel = meta.getColumnLabel(i);
					int type = meta.getColumnType(i);
					Object obj=null;
					if(type == Types.TIMESTAMP || type == Types.DATE){
						Timestamp objTime= rs.getTimestamp(columnLabel);
						obj = sdf.format(objTime);
					}else{
						obj = rs.getObject(columnLabel);
					}
					
					map.put(columnLabel, obj);
				}
				retList.add(map);
			}
		} finally {
			rsClose(rs);
			stmtClose(stmt);
			connClose(conn);
		}
		return retList;
	}
}
