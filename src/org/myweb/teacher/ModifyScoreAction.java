package org.myweb.teacher;

import java.sql.PreparedStatement;
import java.util.Map;

import org.myweb.dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ModifyScoreAction extends ActionSupport
{
   private int score;
   private Dao db = new Dao();
   ActionContext actionContext = ActionContext.getContext();//Action�����ģ����ڻ�ȡ������Session
   boolean flag = false;
   private String pid;
   
   public String getPid() {
	return pid;
   }
   
   public void setPid(String pid) {
	this.pid = pid;
   }
   
   public int getScore() {
	return score;
  }
   
   public void setScore(String score) {
	if(score.equals(""))
	{
		flag = true;
	}
	else
	{
		this.score = Integer.parseInt(score);
	}
  }
   
   public String execute()
   {
	   if(flag)
	   {
		   addFieldError("score", "�ɼ�����Ϊ��");
		   return "input";
	   }
	   Map session = actionContext.getSession();//ͨ���̳�����ActionContext��ȡSession����
	   String id = session.get("scoreId").toString();
	   String sql = "update score set score = ? where id = ?";
	   try
	   {
		   PreparedStatement state = db.getConn().prepareStatement(sql);
		   state.setObject(1, this.score);
		   state.setObject(2, id);
		   int isSuccess = db.executeUpdate(state);
		   if(isSuccess == 0)
		   {
			   return "SUCCESS";
		   }
		   else
		   {
			   return "FAILED";
		   }
	   }catch(Exception e)
	   {
		   addActionError("�ڲ�����");
		   return "ERROR";
	   }
	  
   }
   
}
