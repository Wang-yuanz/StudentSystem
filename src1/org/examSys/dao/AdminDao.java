package org.examSys.dao;

import org.apache.ibatis.annotations.Param;
import org.examSys.pojo.Admin;

public interface AdminDao extends CommonDao<Admin, String>{
	 
	public Admin login(@Param("loginId")String loginId,@Param("loginPwd") String loginPwd);
}
  