package org.examSys.pojo;

import java.io.Serializable;

/**
 * 版块
 * @author yaoyaomice
 *
 */
public class Board implements Serializable {
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
 