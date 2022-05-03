package org.myweb.account;

import java.sql.PreparedStatement;
import java.util.Map;

import org.myweb.dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyStuInfoAction extends ActionSupport
{
   private String loginName;
   private int grade;
   private String tel;
   private String address;
   private Dao db;
   ActionContext actionContext = ActionContext.getContext();//Action�����ģ����ڻ�ȡ������Session
   
   public String getLoginName() {
	return loginName;
   }
   
   public int getGrade() {
	return grade;
   }
   
   public String getTel() {
	return tel;
   }
   
   public String getAddress() {
	return address;
   }
   
   public void setLoginName(String loginName) {
	this.loginName = loginName;
    }
   
   
   public void setGrade(int grade) {
	this.grade = grade;
   }
   
   public void setAddress(String address) {
	this.address = address;
   }
   
   
   public void setTel(String tel) {
	this.tel = tel;
   }
   
   public String execute()
   {
	  //ͨ��session��ȡ�û�id
      Map session = actionContext.getSession();
   	  String id = session.get("id").toString();
     //׼���������������ݿ��ʼ�������ʽ����
      db = new Dao();
      try
      {
    	 PreparedStatement state0 = db.getConn().prepareStatement("update user set loginName = ? where id = ?");
     	 state0.setObject(1, this.loginName);
     	 state0.setObject(2, id);
    	 
     	 PreparedStatement state1 = db.getConn().prepareStatement("update student set tel = ? , address = ? , grade = ? where id = ?");
     	 state1.setObject(1, this.tel);
     	 state1.setObject(2, this.address);
     	 state1.setObject(3, this.grade);
     	 state1.setObject(4, id);
     	 
     	 //��ʼִ�и��²���
    	 int isSuccess0 = db.executeUpdate(state0);
    	 int isSuccess1 = db.executeUpdate(state1);
    	 
    	 if(isSuccess0 == 0 && isSuccess1 == 0)
    	 {
    		 return "SUCCESS";
    	 }
    	 else
    	 {
    		 addActionError("�޸�ʧ��");
    		 return "FAILED";
    	 }
     	 
     	 
      }catch(Exception e)
      {
    	  addActionError("�ڲ�����������");
    	  return "ERROR";
      }
   }
   
}
