package org.myweb.teacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import org.myweb.dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AddScoreAction extends ActionSupport
{
   private String stuId;
   private int subject;
   private int score;
   private Dao db = new Dao();
   private String grade;
   private boolean flag = false;
   ActionContext actionContext = ActionContext.getContext();//Action�����ģ����ڻ�ȡ������Session
   
   
   public String getStuId() {
	return stuId;
   }
   
   
   public int getSubject() {
	return subject;
   }
   
   public String getGrade() {
	return grade;
   }
   
   
   public int getScore() {
	return score;
   }
   
   public void setStuId(String stuId) {
	this.stuId = stuId;
   }
   
   public void setSubject(int subject) {
	this.subject = subject;
   }
   
   public void setScore(String score) {
	   if(score.equals(""))
	   {
		   this.flag = true;
		   addFieldError("score", "�ɼ�����Ϊ��");
	   }
	   else
	   {
		   this.score = Integer.parseInt(score);
	   }
	 
   }
   
   public void setGrade(String grade) {
	this.grade = grade;
   }
   
   
   public String execute()
   {
	   if(flag)
	   {
		   return "input";
	   }
	   if(this.subject == 0)
	   {
		   addFieldError("score", "�γ��Ѿ���¼��������");
		   return "input";
	   }
	   String t = this.grade.substring(2,3);
	   int grade_num = Integer.parseInt(t);
	   Map session = actionContext.getSession();//ͨ���̳�����ActionContext��ȡSession����
	   String id = session.get("id").toString();
	   try
	   {
		   String sql0 = "select * from student where id = ?";
		   PreparedStatement state1 = db.getConn().prepareStatement(sql0);
		   state1.setObject(1, this.stuId);
		   ResultSet set = db.executeQuery(state1);
		   if(!set.next())
		   {
			   addActionError("ѧ��������");
			   return "NOTFOUND";
		   }
		   String sql = "insert into score (subject,teacher,stu,score,grade) values (?,?,?,?,?)";
		   PreparedStatement state0 = db.getConn().prepareStatement(sql);
		   state0.setObject(1, this.subject);
		   state0.setObject(2, id);
		   state0.setObject(3, this.stuId);
		   state0.setInt(4, this.score);
		   state0.setObject(5, grade_num);
		   int isSuccess = db.executeUpdate(state0);
		   if(isSuccess == 0)
		   {
			   return "SUCCESS";
		   }
		   else
		   {
			   addActionError("���ʧ��");
			   return "FAILED";
		   }
	   }catch(Exception e)
	   {
		   addActionError("�ڲ�����");
		   return "ERROR";
	   }
   }
   
}
