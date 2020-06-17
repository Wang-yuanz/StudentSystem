package org.examSys.utils;

import java.io.Serializable;
import java.util.List;

public class AxemsModel implements Serializable {
	private List<Integer> ids; //编号集合
	
	private List<Object> ids2; //编号集合，因为有些实体类主键不是int类型

	public List<Object> getIds2() {
		return ids2;
	}

	public void setIds2(List<Object> ids2) {
		this.ids2 = ids2;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	
}
   