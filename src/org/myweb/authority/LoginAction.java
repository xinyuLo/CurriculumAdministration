package org.myweb.authority;
/*
 * ��½Action
 * Written By СԶ
 * 2016-12-07
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


import org.myweb.dao.Dao;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//��½Action
public class LoginAction extends ActionSupport{

	String userName = "";//�û���
	String password = "";//����
	
/********************��������get set����**************************************/
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
   /*******��½Action********/
	public String login()
	{
		ActionContext actionContext = ActionContext.getContext();//Action�����ģ����ڻ�ȡ������Session
		Dao dao = new Dao();
		try {
			//�ȸ����û����������ѯ
			Connection conn = dao.getConn();
			PreparedStatement state = conn.prepareStatement("select * from user where loginName = ? and password = ?");
			state.setObject(1,userName);
			state.setObject(2, this.password);
			ResultSet rs = dao.executeQuery(state);
			Map session = actionContext.getSession();//ͨ���̳�����ActionContext��ȡSession����
			session.put("loginName", userName);
			if(rs.next())//����ж�Ӧ��¼
			{
				session.put("user", rs.getObject(2));//������ʵ����������JavaBean����ʵ��
				String identity = rs.getString(4);//����û����
				//������ݽ�����ת
				if(identity.equals("1"))
				{
				   session.put("id", rs.getObject(1));
				   session.put("role", "Teacher");
				   return "TEACHER";
				}
				else if(identity.equals("0"))
				{
				  session.put("id", rs.getObject(1));
				  session.put("role", "Student");
				   return "STUDENT";
				}
			   return "OK";
			}
			addFieldError("login", "��½ʧ��");
			return "FAILD";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILD";
		}
	}
	
}
