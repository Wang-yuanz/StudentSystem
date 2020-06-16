package org.examSys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.examSys.dao.QuestionTypeDao;
import org.examSys.pojo.QuestionType;
import org.examSys.utils.AxemsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/questionType")  
public class QuestionTypeController {
	@Resource
	private QuestionTypeDao questionTypeDao;

	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() throws Exception {
		return "questionType/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd() throws Exception {
		return "questionType/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(QuestionType questionType) throws Exception {
		questionTypeDao.add(questionType);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(QuestionType questionType) throws Exception {
		questionTypeDao.update(questionType);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/toedit",method=RequestMethod.GET)
	public String toEdit(int id,ModelMap map) throws Exception {
		QuestionType questionType = questionTypeDao.getById(id);
		map.put("questionType", questionType);
		return "questionType/edit";
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		questionTypeDao.deleteById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String, Object> batchDelete(AxemsModel model) throws Exception {
		questionTypeDao.deleteByIds(model.getIds().toArray(new Integer[model.getIds().size()]));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(defaultValue="0") int offset,@RequestParam(defaultValue="10") int limit, 
			QuestionType condition) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<QuestionType> list = questionTypeDao.getListByCondition(offset, limit, condition);
		int total = questionTypeDao.getCountByCondition(condition);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
}
