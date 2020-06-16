package org.examSys.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param; 

public interface CommonDao <T,PK extends Serializable> {
	/**
	 * 添加实体
	 * @param entity  
	 * @return
	 */
	public void add(T entity);
	
	/**
	 * 根据编号删除实体
	 * @param id 主键编号
	 */
	public void deleteById(PK id);
	
	/**
	 * 批量删除实体
	 * @param ids 编号数组
	 */
	public void deleteByIds(PK[] ids);
	
	/**
	 * 修改实体
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 根据编号查询对应的实体信息
	 * @param id 实体编号
	 * @return
	 */
	public T getById(PK id) ;
	
	/**
	 * 查询所有的实体信息
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * 分页查询实体信息
	 * @param start 起始条数
	 * @param limit 每页显示的数据条数
	 * @return
	 */
	public List<T> getList(@Param("start") int start,@Param("limit") int limit);
	
	/**
	 * 查询实体的总数据条数
	 * @return
	 */
	public Integer getCount();
	
	/**
	 * 按条件分页检索数据
	 * @param start 起始条数
	 * @param limit 每页显示的数据条数
	 * @param condition 查询条件
	 * @return
	 */
	public List<T> getListByCondition(@Param("start") int start,@Param("limit") int limit,@Param("condition") T condition);
	
	/**
	 * 按条件查询实体的条数
	 * @param condition 查询条件
	 * @return
	 */
	public Integer getCountByCondition(@Param("condition") T condition);
	
	
}






