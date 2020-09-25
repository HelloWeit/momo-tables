package cn.weit.tables.core;

import cn.weit.tables.dto.MetaColDefineDto;
import cn.weit.tables.util.CamelCaseUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weitong
 */
@Component("varcharColumn")
public class VarcharColumn implements IColumn {
	@Override
	public List<ColumnInfo> genColumnInfos(List<MetaColDefineDto> metaColDefineDtos) {
		return metaColDefineDtos.stream().map(this::gen).collect(Collectors.toList());
	}

	@Override
	public ColumnInfo genColumnInfo(Field field) {
		return genField(field);
	}

	private ColumnInfo genField(Field field) {
		ColumnInfo columnInfo = new ColumnInfo();
		columnInfo.setFieldName(CamelCaseUtil.humpToLine(field.getName()));
		columnInfo.setFieldType("varchar");
		columnInfo.setFieldLen(255);
		columnInfo.setKey(false);
		columnInfo.setAutoIncrement(false);
		return columnInfo;
	}

	private ColumnInfo gen(MetaColDefineDto metaColDefineDto) {
		ColumnInfo columnInfo = new ColumnInfo();
		columnInfo.setFieldName(metaColDefineDto.getColName());
		columnInfo.setFieldType("varchar");
		columnInfo.setFieldLen(255);
		columnInfo.setKey(false);
		columnInfo.setAutoIncrement(false);
		return columnInfo;
	}

}
