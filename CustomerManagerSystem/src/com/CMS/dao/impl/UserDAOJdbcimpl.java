package com.CMS.dao.impl;

import com.CMS.dao.DAO;
import com.CMS.dao.UserDAO;
import com.CMS.domain.User;

public class UserDAOJdbcimpl extends DAO<User> implements UserDAO{

	@Override
	public boolean Checkuser(User loginuser) {
		String sql="select * from user where name=? and password=?;";
		User user=get(sql, loginuser.getName(),loginuser.getPassword());
		if(user!=null) return true;
		return false;
	}

	@Override
	public boolean Registeruser(User registeruser) {
		try {
			String sql="insert into user values(null,?,?);";
			update(sql, registeruser.getName(),registeruser.getPassword());
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

}
