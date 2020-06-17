 package org.examSys.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 试题
 * @author yaoyaomice
 *
 */
public class Question implements Serializable {
	private Integer id;
	
	private String name; 
	
	private QuestionType questionType; //试题类型
	
	private Board board; //版块
	
	private Difficulty difficulty; //难易度
	
	private Grade grade; //适用年级
	
	private List<Options> optionsList; //选项集合

	public List<Options> getOptionsList() {
		return optionsList;
	}

	public void setOptionsList(List<Options> optionsList) {
		this.optionsList = optionsList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	
}
