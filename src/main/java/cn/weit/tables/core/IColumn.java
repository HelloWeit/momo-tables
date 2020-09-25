package cn.weit.tables.core;

import cn.weit.tables.dto.MetaColDefineDto;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author weitong
 */
public interface IColumn {
	/**
	 * 设置多条 加快解析从元数据模板过来的数据
	 * @param metaColDefineDtos
	 * @return
	 */
	List<ColumnInfo> genColumnInfos(List<MetaColDefineDto> metaColDefineDtos);

	/**
	 * 单条解析 用在原始extInfo model
	 * @param field
	 * @return
	 */
	ColumnInfo genColumnInfo(Field field);
}
