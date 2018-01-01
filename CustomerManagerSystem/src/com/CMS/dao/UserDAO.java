package com.CMS.dao;

import com.CMS.domain.User;

public interface UserDAO {
	public boolean Checkuser(User loginuser);
	public boolean Registeruser(User registeruser);
}
