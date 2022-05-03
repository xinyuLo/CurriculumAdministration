package org.myweb.dao;
/*
 * ���ݿ������
 * Refference to csdn
 * Amend By СԶ
 * 2016-12-07
 */
import java.sql.*;

public class Dao {
	    Connection con = null;  //MySQL���Ӷ���
	    Statement stat = null;  //��ѯ�õ�Statement����
	    ResultSet rs = null;    //��ѯ���õĽ����
	      
	 /*********���췽��,�������ݿ�����**********/
	    public Dao() {  
	        try {  
	        Class.forName("com.mysql.jdbc.Driver");  
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/smxy_class","root","root");  
	        stat = con.createStatement();  
	        } catch (Exception e) {  
	        // TODO: handle exception  
	        con = null;  
	        }  
	    }  
	  /*******��ѯ���������ؽ����*********/  
	    public ResultSet executeQuery(PreparedStatement state) {  
	      try {
			rs =  state.executeQuery();
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs = null;
		  }
	      return rs;
	    }  
	  /********ɾ�����޸ģ�����*************/    
	    public int executeUpdate(PreparedStatement state) {  
	        try {  
	         state.executeUpdate();
	        return 0;  
	        } catch (Exception e) {  
	        // TODO: handle exception  
	        }  
	        return -1;  
	    } 
	    
	  /*****������ݿ�����*****/
	    public Connection getConn()
	    {
	    	return this.con;
	    }
}
