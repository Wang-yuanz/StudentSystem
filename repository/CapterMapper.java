package com.alvis.exam.repository;

import com.alvis.exam.domain.Capter;
import com.alvis.exam.viewmodel.admin.education.CapterPageRequestVM;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CapterMapper extends BaseMapper<Capter>  {
    int deleteByPrimaryKey(Integer id);

    int insert(Capter record);

    int insertSelective(Capter record);

    Capter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Capter record);

    int updateByPrimaryKey(Capter record);

    List<Capter> getCapterByCapterName(String capterName);

    List<Capter> allCapter();

    List<Capter> page(CapterPageRequestVM requestVM);
}