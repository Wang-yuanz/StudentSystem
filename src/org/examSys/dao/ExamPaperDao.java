package org.examSys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.examSys.pojo.ExamPaper;
import org.examSys.pojo.ExamPaperParts;
import org.examSys.pojo.Question;

public interface ExamPaperDao extends CommonDao<ExamPaper, Integer>{

	/**
	 * 添加试卷组成部分
	 * @param parts
	 */
	public void addParts(ExamPaperParts parts);
	/**
	 * 添加试卷问题
	 * @param partId
	 * @param quesId
	 */
	public void addPaperQuestion(@Param("partId")Integer partId,@Param("quesId") Integer quesId);
	/**
	 * 根据组成部分名称获取对应的试题信息
	 * @param name
	 * @return
	 */
	public List<Question> getQuestionsByPartName(@Param("name")String name);
	
	/**
	 * 根据设置的策略返回符合条件的试题编号
	 * @param gradeId
	 * @param boardId
	 * @param difficultyId
	 * @param questionTypeId
	 * @return
	 */
	public List<Integer> getQuestions(@Param("gradeId")Integer gradeId,@Param("boardId") Integer boardId,@Param("difficultyId") Integer difficultyId,@Param("questionTypeId") Integer questionTypeId,@Param("quesCount")Integer quesCount);
}
 