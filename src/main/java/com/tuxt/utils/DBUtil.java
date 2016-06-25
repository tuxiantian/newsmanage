package com.tuxt.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbutils.DbUtils;

import com.mysql.jdbc.PreparedStatement;

public class DBUtil {
	private static String DRIVER=null,MySqlUrl=null;
	private DBUtil(){}//定义私有构造方法防止使用时实例化
	/*自动加载数据库驱动*/
	static{
		Properties prop = new Properties();
		try {
			//仅能寻找src目录下的文件
			InputStream is = DBUtil.class.getResourceAsStream("../../../DbConfig.property");
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println("there is error to read config file...");
			e.printStackTrace();
		}
		DRIVER=prop.getProperty("DRIVER");
		MySqlUrl=prop.getProperty("MySqlUrl");

		try {
			DbUtils db = new DbUtils();
			// 加载数据库mysql的jdbc驱动
			db.loadDriver(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * read config file
	 */
	public static String getPara(String ParaName) {
		Properties prop = new Properties();
		try {
			//仅能寻找src目录下的文件
			InputStream is = DBUtil.class.getResourceAsStream("../../../DbConfig.property");
			prop.load(is);
			if (is != null)
				is.close();
		} catch (Exception e) {
			System.out.println("there is error to read config file...");
			e.printStackTrace();
		}
		System.out.println("数据库路径显示："+prop.getProperty(ParaName));
		return prop.getProperty(ParaName);
	}
	//公有数据库连接口
	public static Connection getConnect(){
		Connection conn=null;
		try
		{
			conn=DriverManager.getConnection(MySqlUrl);
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.print("连接数据库失败");
		}
		return conn;
	}
	public static void close(Connection conn,PreparedStatement stmt){
		DbUtils.closeQuietly(conn);
		DbUtils.closeQuietly(stmt);
	}
	public static void close(ResultSet rs,Statement st,Connection conn)//公有的数据库 关闭口
	{
		try
		{
			if(rs!=null){rs.close();}
			if(st!=null){st.close();}
			if(conn!=null){conn.close();}
		} 
		catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
