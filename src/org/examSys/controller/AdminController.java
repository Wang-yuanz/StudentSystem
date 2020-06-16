package org.examSys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.examSys.dao.AdminDao;
import org.examSys.pojo.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private AdminDao adminDao;

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String loginId, String loginPwd, HttpSession session) throws Exception {
		Admin admin = adminDao.login(loginId, loginPwd);
		if (admin != null) {
			session.setAttribute("login_admin", admin);
			return "redirect:/admin/main";
		}
		session.setAttribute("error", "用户名或者密码错误");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/main")
	public String main() throws Exception {
		return "main";
	}
	
}







 