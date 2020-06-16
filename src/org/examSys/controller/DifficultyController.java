package org.examSys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.examSys.dao.DifficultyDao;
import org.examSys.pojo.Difficulty;
import org.examSys.utils.AxemsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/difficulty")
public class DifficultyController {
	@Resource
	private DifficultyDao difficultyDao;

	public void setDifficultyDao(DifficultyDao difficultyDao) {
		this.difficultyDao = difficultyDao;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() throws Exception {
		return "difficulty/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd() throws Exception {
		return "difficulty/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Difficulty difficulty) throws Exception {
		difficultyDao.add(difficulty);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(Difficulty difficulty) throws Exception {
		difficultyDao.update(difficulty);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/toedit",method=RequestMethod.GET)
	public String toEdit(int id,ModelMap map) throws Exception {
		Difficulty difficulty = difficultyDao.getById(id);
		map.put("difficulty", difficulty);
		return "difficulty/edit";
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		difficultyDao.deleteById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String, Object> batchDelete(AxemsModel model) throws Exception {
		difficultyDao.deleteByIds(model.getIds().toArray(new Integer[model.getIds().size()]));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(defaultValue="0") int offset,@RequestParam(defaultValue="10") int limit, 
			Difficulty condition) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Difficulty> list = difficultyDao.getListByCondition(offset, limit, condition);
		int total = difficultyDao.getCountByCondition(condition);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
}
