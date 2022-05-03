package org.myweb.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.myweb.dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyPasswordAction extends ActionSupport
{
     private Dao db;
     private String pwd_original;
     private String pwd_new;
     private String pwd_confirm;
     ActionContext actionContext = ActionContext.getContext();//Action�����ģ����ڻ�ȡ������Session
     
     public void setPwd_original(String pwd_original) {
		this.pwd_original = pwd_original;
	}
     
     public void setPwd_new(String pwd_new) {
		this.pwd_new = pwd_new;
	}
     
     public void setPwd_confirm(String pwd_confirm) {
		this.pwd_confirm = pwd_confirm;
	}
     
     public String getPwd_original() {
		return pwd_original;
	}
     
     public String getPwd_new() {
		return pwd_new;
	}
     
     public String getPwd_confirm() {
		return pwd_confirm;
	}
     
     
     public String execute()
     {
    	 if(!pwd_new.equals(pwd_confirm))
    	 {
    		addActionError("�����������벻һ��");
    	    return "CONFIRMERROR";
    	 }
    	
    	try{
    		db = new Dao();
    		Map session = actionContext.getSession();//ͨ���̳�����ActionContext��ȡSession����
    		String id = session.get("id").toString();
    		PreparedStatement state0 = db.getConn().prepareStatement("select password from user where id = ?");
    		state0.setObject(1, id);
    		ResultSet set = db.executeQuery(state0);
    		if(set.next())
    		{
    			String pwd_old = set.getString(1);
    			if(!pwd_old.equals(pwd_original))
    			{
    				addActionError("ԭʼ�����������");
    				return "FAILED";
    			}
    		}
    		PreparedStatement stste = db.getConn().prepareStatement("update user set password = ? where id = ?");
    		stste.setObject(1, pwd_new);
    		stste.setObject(2, id);
    		int isSuccess = db.executeUpdate(stste);
    		if(isSuccess == 0)
    		{
    			return "SUCCESS";
    		}
    		else
    		{
    			addActionError("�޸�ʧ��");
    			return "FAILED";
    		}
    	}catch(SQLException e)
    	{
    		addActionError("�ڲ�����");
    		e.printStackTrace();
    		return "ERROR";
    	}
    	
     }
}
