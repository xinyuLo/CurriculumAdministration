package org.myweb.teacher;

import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.myweb.dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DelStuAction extends ActionSupport
{
   private Dao db;
   private HttpServletRequest request;
   
   public String execute() throws Exception
   {
	   request = ServletActionContext.getRequest();
	   if(request.getParameter("id") != null)
	   {
		   db = new Dao();
		   String sql = "delete from user where id = ?";
		   PreparedStatement state = db.getConn().prepareStatement(sql);
		   state.setObject(1, request.getParameter("id").toString());
		    return db.executeUpdate(state) == 0 ? "SUCCESS" : "FAILED";//��Ŀ����� 
		    /*
		     * ��ʾ���ɾ���ɹ�������"SUCCESS"
		     * ���򷵻�"FAILED"
		     */
	   }
	   else
	   {
		   return "ERROR";
	   }
   }
}
