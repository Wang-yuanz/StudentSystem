package org.examSys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.examSys.dao.GradeDao;
import org.examSys.pojo.Grade;
import org.examSys.utils.AxemsModel;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/grade")
public class GradeController {
	@Resource
	private GradeDao gradeDao;

	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() throws Exception {
		return "grade/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd() throws Exception {
		return "grade/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Grade grade) throws Exception {
		gradeDao.add(grade);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(Grade grade) throws Exception {
		gradeDao.update(grade);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/toedit",method=RequestMethod.GET)
	public String toEdit(int id,ModelMap map) throws Exception {
		Grade grade = gradeDao.getById(id);
		map.put("grade", grade);
		return "grade/edit";
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		gradeDao.deleteById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String, Object> batchDelete(AxemsModel model) throws Exception {
		gradeDao.deleteByIds(model.getIds().toArray(new Integer[model.getIds().size()]));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(defaultValue="0") int offset,@RequestParam(defaultValue="10") int limit, 
			Grade condition) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Grade> list = gradeDao.getListByCondition(offset, limit, condition);
		int total = gradeDao.getCountByCondition(condition);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
}
