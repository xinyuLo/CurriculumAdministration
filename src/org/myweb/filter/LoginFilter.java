package org.myweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// ����ת�ͣ�HttpServletRequest��ServletRequest������
		HttpServletRequest req = (HttpServletRequest) arg0;
		// ����ת��
		HttpServletResponse res = (HttpServletResponse) arg1;
		// ��ȡSession����
		HttpSession session = req.getSession();
		// ��ȡ��ǰҳ������URL
		String url = req.getRequestURI();
		System.out.println("�����URLΪ��" + url);
		// �ж�����URL�ĺ�׺�Ƿ���jsp�����ǣ���һ�����������ǣ�����
		int t = url.lastIndexOf("jsp");
		if (t == url.length() - 3) {
			// ����ǣ������session��֤
			if (session.getAttribute("role") == null
					&& !url.equals(req.getContextPath() + "/Login.jsp")) {
				System.out.println("СԶСԶСԶ~~~");
				res.sendRedirect(req.getContextPath() + "/Login.jsp");
			}
			if (session.getAttribute("role") != null
					&& !url.equals(req.getContextPath() + "/Login.jsp")) {
				String role = session.getAttribute("role").toString();
				if (!url.contains(role)) {
					res.sendRedirect(req.getContextPath() + "/Login.jsp");
				}
			}
			// �ض������У�����ҳ��Ϊ�հ�
			arg2.doFilter(arg0, arg1);
		} else {
			arg2.doFilter(arg0, arg1);
		}
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
