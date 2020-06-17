package org.examSys.pojo;

import java.io.Serializable;

/**
 * 管理员
 * @author yaoyaomice
 *
 */
public class Admin implements Serializable {
	private String loginId;
	
	private String loginPwd;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	
}
 