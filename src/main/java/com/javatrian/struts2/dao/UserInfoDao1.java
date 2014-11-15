package com.javatrian.struts2.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.javatrian.struts2.model.UserInfo;

public class UserInfoDao1 {
	static SessionFactory sf;
	static {
		Configuration cf = new Configuration();
		cf.configure();
		sf = cf.buildSessionFactory();
	}

	public UserInfo getUserInfoByLoginName(final String loginName) {
		System.out.println("loginName is:" + loginName);
		Session s = sf.openSession();

		Query q = s.createQuery(" from UserInfo where loginName = :loginName" );
			q.setString("loginName",loginName);

		List<UserInfo> list = (List<UserInfo>) q.list();
		if (null == list || list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	public UserInfo getUserInfoById(final Long id) {
		Session s = sf.openSession();
		return (UserInfo) s.get(UserInfo.class, id);
	}

	public List<UserInfo> getUserInfoList() {
		Session s = sf.openSession();
		Query q = s.createQuery(" from UserInfo ");
		return q.list();
	}

	public void update(final UserInfo userInfo) {
		Session s = sf.openSession();
		Transaction ts = s.beginTransaction(); // 事务
		s.update(userInfo);
		ts.commit(); // 提交事务
		s.close();
	}

	public void add(final UserInfo userInfo) {
		Session s = sf.openSession();
		Transaction ts = s.beginTransaction(); // 事务
		s.save(userInfo);
		ts.commit(); // 提交事务
		s.close();
	}

	public void delete(final Long id) {
		Session s = sf.openSession();
		Transaction ts = s.beginTransaction(); // 事务
		UserInfo user = (UserInfo) s.get(UserInfo.class, id);
		s.delete(user);
		ts.commit(); // 提交事务
		s.close();
	}
}
