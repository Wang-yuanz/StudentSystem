package org.examSys.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 试卷组成部分
 * @author yaoyaomice
 *
 */
public class ExamPaperParts implements Serializable {
	private Integer id;
	
	 private String partName;
	
	private Integer grade_id; //所属年级
	
	private Integer difficulty_id; //难易度
	
	private Integer board_id; //版块编号
	
	private Integer questionType_id; //试题类型
	
	private Integer quesCount;
	
	private Integer disOrder = 0;
	
	private Integer examPaper_id;
	
	private List<Question> questions; //对应的试题集合

	public Integer getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}

	public Integer getDifficulty_id() {
		return difficulty_id;
	}

	public void setDifficulty_id(Integer difficulty_id) {
		this.difficulty_id = difficulty_id;
	}

	public Integer getBoard_id() {
		return board_id;
	}

	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
	}

	public Integer getQuestionType_id() {
		return questionType_id;
	}

	public void setQuestionType_id(Integer questionType_id) {
		this.questionType_id = questionType_id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public Integer getQuesCount() {
		return quesCount;
	}

	public void setQuesCount(Integer quesCount) {
		this.quesCount = quesCount;
	}

	public Integer getDisOrder() {
		return disOrder;
	}

	public void setDisOrder(Integer disOrder) {
		this.disOrder = disOrder;
	}

	public Integer getExamPaper_id() {
		return examPaper_id;
	}

	public void setExamPaper_id(Integer examPaper_id) {
		this.examPaper_id = examPaper_id;
	}
	
}
