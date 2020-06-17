package org.examSys.pojo;

import java.io.Serializable;

/**
 * 选择题的选项
 * @author yaoyaomice
 *
 */
public class Options implements Serializable {
	private Integer id;
	
	private String opt;
	
	private Integer question_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpt() {
		return opt;
	}

	public void setOpt(String opt) {
		this.opt = opt;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	
}
  