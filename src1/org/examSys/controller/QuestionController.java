package org.examSys.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;  
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.examSys.dao.BoardDao;
import org.examSys.dao.DifficultyDao;
import org.examSys.dao.GradeDao;
import org.examSys.dao.OptionDao;
import org.examSys.dao.QuestionDao;
import org.examSys.dao.QuestionTypeDao;
import org.examSys.pojo.Board;
import org.examSys.pojo.Difficulty;
import org.examSys.pojo.Grade;
import org.examSys.pojo.Options;
import org.examSys.pojo.Question;
import org.examSys.pojo.QuestionType;
import org.examSys.utils.AxEmsUtils;
import org.examSys.utils.AxemsModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Resource
	private QuestionDao questionDao;
	@Resource
	private QuestionTypeDao questionTypeDao;
	@Resource
	private DifficultyDao difficultyDao;
	@Resource
	private BoardDao boardDao;
	@Resource
	private GradeDao gradeDao;
	@Resource
	private OptionDao optionDao;

	public void setOptionDao(OptionDao optionDao) {
		this.optionDao = optionDao;
	}

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

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() throws Exception {
		return "question/list";
	}
	
	@RequestMapping(value="/import",method=RequestMethod.POST)
	public String batchImport(MultipartFile upload,ModelMap modelMap) throws Exception {
        XWPFDocument document=new XWPFDocument(upload.getInputStream());  
        List<XWPFTable> tables = document.getTables();
        //每个table是一道题，循环添加到数据库
        for (XWPFTable table : tables) {
            // 获取表格的行
            List<XWPFTableRow> rows = table.getRows();
            Question question = new Question();
            //读取题目
            question.setName(rows.get(0).getCell(1).getText());
            //题目类型
            question.setQuestionType(questionTypeDao.getById(Integer.parseInt(rows.get(1).getCell(1).getText())));
            //试题版块
            question.setBoard(boardDao.getById(Integer.parseInt(rows.get(2).getCell(1).getText())));
            //适用年级
            question.setGrade(gradeDao.getById(Integer.parseInt(rows.get(3).getCell(1).getText())));
            //难易度
            question.setDifficulty(difficultyDao.getById(Integer.parseInt(rows.get(4).getCell(1).getText())));
            
            questionDao.add(question);
            //如果是选择题，获取并添加选项
            if (question.getQuestionType().getName().equals("选择题")) {
				for (int i = 5; i < rows.size(); i++) {
					Options options = new Options();
					options.setQuestion_id(question.getId());
					options.setOpt(rows.get(i).getCell(1).getText());
					optionDao.add(options);
				}
			}
        }
        document.close();
        
        modelMap.put("total", tables.size());
        return "question/result";
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
		
		return "question/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> add(Question question) throws Exception {
		questionDao.add(question);
		//如果是选择题，则添加选项
		if (question.getQuestionType().getName().equals("选择题")) {
			for (Options options : question.getOptionsList()) {
				options.setQuestion_id(question.getId());
				optionDao.add(options);
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> edit(Question question) throws Exception {
		questionDao.update(question);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/toedit",method=RequestMethod.GET)
	public String toEdit(int id,ModelMap map) throws Exception {
		Question question = questionDao.getById(id);
		map.put("question", question);
		return "question/edit";
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(int id) throws Exception {
		questionDao.deleteById(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/batchDel")
	@ResponseBody
	public Map<String, Object> batchDelete(AxemsModel model) throws Exception {
		questionDao.deleteByIds(model.getIds().toArray(new Integer[model.getIds().size()]));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	
	@RequestMapping(value="/list",produces="application/json")
	@ResponseBody
	public Map<String, Object> list(@RequestParam(defaultValue="0") int offset,@RequestParam(defaultValue="10") int limit, 
			Question condition) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Question> list = questionDao.getListByCondition(offset, limit, condition);
		int total = questionDao.getCountByCondition(condition);
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String, String> upload(MultipartFile file, HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String realPath = request.getServletContext().getRealPath("/attached");
		String newName = AxEmsUtils.generateFileName(file.getOriginalFilename());
		File dest = new File(realPath + File.separator + newName);
		FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
		map.put("url", "attached/"+newName);
		return map;
	}
	
}
