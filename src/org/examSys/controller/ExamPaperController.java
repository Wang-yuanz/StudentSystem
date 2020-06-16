package org.examSys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.examSys.dao.BoardDao;
import org.examSys.dao.DifficultyDao;  
import org.examSys.dao.ExamPaperDao;
import org.examSys.dao.GradeDao;
import org.examSys.dao.QuestionTypeDao;
import org.examSys.pojo.Board;
import org.examSys.pojo.Difficulty;
import org.examSys.pojo.ExamPaper;
import org.examSys.pojo.ExamPaperParts;
import org.examSys.pojo.Grade;
import org.examSys.pojo.QuestionType;
import org.examSys.utils.AxEmsUtils;
import org.examSys.utils.AxemsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.glass.ui.View;

@Controller
@RequestMapping("/examPaper")
public class ExamPaperController {
	@Resource
	private ExamPaperDao examPaperDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private DifficultyDao difficultyDao;
	@Resource
	private BoardDao boardDao;
	@Resource
	private GradeDao gradeDao;

	public void setQuestionTypeDao(QuestionTypeDao questionTypeDao) {
		this.questionTypeDao = questionTypeDao;
	}

	public void setDifficultyDao(DifficultyDao difficultyDao) {
		this.difficultyDao = difficultyDao;
	}

	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}

	public void setExamPaperDao(ExamPaperDao examPaperDao) {
		this.examPaperDao = examPaperDao;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() throws Exception {
		return "examPaper/list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String toAdd(ModelMap modelMap) throws Exception {
		List<Grade> gradeList = gradeDao.getAll();
		List<Difficulty> difficulties = difficultyDao.getAll();
		List<QuestionType> questionTypes = questionTypeDao.getAll();
		List<Board> boards = boardDao.getAll();
		
		modelMap.put("grades", gradeList);
		modelMap.put("difficulties", difficulties);
		modelMap.put("questionTypes", questionTypes);
		modelMap.put("boards", boards);
		
		return "examPaper/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(ExamPaper examPaper) throws Exception {
		examPaperDao.add(examPaper);
		
		//获取试卷的组成部分，添加到数据库，并根据组成部分的策略设置，随机抽取题目
		List<ExamPaperParts> parts = examPaper.getExamPaperPartsList();
		for (ExamPaperParts part : parts) {
			part.setExamPaper_id(examPaper.getId());
			examPaperDao.addParts(part);
			//根据组成部分的策略设置，随机抽取题目
			List<Integer> ids = examPaperDao.getQuestions(part.getGrade_id(), part.getBoard_id(), part.getDifficulty_id(), part.getQuestionType_id(), part.getQuesCount());
			for (Integer id : ids) {
				examPaperDao.addPaperQuestion(part.getId(), id);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(ExamPaper examPaper) throws Exception {
		examPaperDao.update(examPaper);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/toedit",method=RequestMethod.GET)
	public String toEdit(int id,ModelMap map) throws Exception {
		ExamPaper examPaper = examPaperDao.getById(id);
		map.put("examPaper", examPaper);
		return "examPaper/edit";
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		examPaperDao.deleteById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	/**
	 * 导出试卷
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/export")
	public String export(Integer id,HttpServletRequest request) throws Exception {
		String url = "http://localhost:8080/examSys/examPaper/view/"+id+".shtml";
		AxEmsUtils.htmlToWord2(url, "exampaper.doc",request.getServletContext().getRealPath("/tem"));
		
		return "redirect:/tem/exampaper.doc";
	}
	
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String, Object> batchDelete(AxemsModel model) throws Exception {
		examPaperDao.deleteByIds(model.getIds().toArray(new Integer[model.getIds().size()]));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping("/view/{id}.shtml")
	public String view(@PathVariable("id")Integer id,ModelMap modelMap) throws Exception {
		ExamPaper examPaper = examPaperDao.getById(id);
		for (ExamPaperParts parts : examPaper.getExamPaperPartsList()) {
			parts.setQuestions(examPaperDao.getQuestionsByPartName(parts.getPartName()));
		}
		
		modelMap.put("examPaper", examPaper);
		return "examPaper/view";
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(defaultValue="0") int offset,@RequestParam(defaultValue="10") int limit, 
			ExamPaper condition) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ExamPaper> list = examPaperDao.getListByCondition(offset, limit, condition);
		int total = examPaperDao.getCountByCondition(condition);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
}
