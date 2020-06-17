package org.examSys.pojo;

import java.io.Serializable;

/**
 * 试题类别
 * @author yaoyaomice
 *
 */
public class QuestionType implements Serializable {
	private Integer id;
	
	private String name;

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
	
}
 