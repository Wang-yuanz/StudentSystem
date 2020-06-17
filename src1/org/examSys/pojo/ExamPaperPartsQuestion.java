 package org.examSys.pojo;

import java.io.Serializable;

public class ExamPaperPartsQuestion implements Serializable {
	private Integer id;
	
	private Integer part_id;
	
	private Integer question_id;
	
	private Integer disOrder;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPart_id() {
		return part_id;
	}

	public void setPart_id(Integer part_id) {
		this.part_id = part_id;
	}


	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public Integer getDisOrder() {
		return disOrder;
	}

	public void setDisOrder(Integer disOrder) {
		this.disOrder = disOrder;
	}
	
}
