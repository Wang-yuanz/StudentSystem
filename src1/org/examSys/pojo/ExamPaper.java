package org.examSys.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 试卷
 * @author yaoyaomice
 *
 */
public class ExamPaper implements Serializable {
	private Integer id;
	
	private String name;
	
	private Date createDate;
	
	private List<ExamPaperParts> examPaperPartsList; //组成部分集合

	public List<ExamPaperParts> getExamPaperPartsList() {
		return examPaperPartsList;
	}

	public void setExamPaperPartsList(List<ExamPaperParts> examPaperPartsList) {
		this.examPaperPartsList = examPaperPartsList;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
 