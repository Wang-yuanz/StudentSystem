package org.examSys.pojo;

import java.io.Serializable;

/**
 * 年级
 * @author yaoyaomice
 *
 */
public class Grade implements Serializable {
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
 