package com.alvis.exam.repository;

import com.alvis.exam.domain.CapterQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CapterQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CapterQuestion record);

    void insertBatch(List<CapterQuestion> list);

    int insertSelective(CapterQuestion record);

    CapterQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CapterQuestion record);

    int updateByPrimaryKey(CapterQuestion record);

    int clearCapterQuestionByQuestionId(Integer questionId);
}