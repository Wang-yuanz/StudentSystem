package org.examSys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.examSys.dao.BoardDao;
import org.examSys.pojo.Board;
import org.examSys.utils.AxemsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Resource 
	private BoardDao boardDao;

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() throws Exception {
		return "board/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd() throws Exception {
		return "board/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Board board) throws Exception {
		boardDao.add(board);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(Board board) throws Exception {
		boardDao.update(board);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/toedit",method=RequestMethod.GET)
	public String toEdit(int id,ModelMap map) throws Exception {
		Board board = boardDao.getById(id);
		map.put("board", board);
		return "board/edit";
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		boardDao.deleteById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String, Object> batchDelete(AxemsModel model) throws Exception {
		boardDao.deleteByIds(model.getIds().toArray(new Integer[model.getIds().size()]));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(defaultValue="0") int offset,@RequestParam(defaultValue="10") int limit, 
			Board condition) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Board> list = boardDao.getListByCondition(offset, limit, condition);
		int total = boardDao.getCountByCondition(condition);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
}
