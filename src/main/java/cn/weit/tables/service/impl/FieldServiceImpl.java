package cn.weit.tables.service.impl;

import cn.weit.tables.cache.MetaCache;
import cn.weit.tables.core.ColumnFactory;
import cn.weit.tables.core.ColumnInfo;
import cn.weit.tables.core.ColumnSql;
import cn.weit.tables.core.TableSql;
import cn.weit.tables.dao.TableMapper;
import cn.weit.tables.service.FieldService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author weitong
 */
@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class FieldServiceImpl implements FieldService {

	@Autowired
	private TableMapper tableMapper;

	@Autowired
	private ColumnFactory columnFactory;

	@Autowired
	private MetaCache metaCache;


	@Override
	public void updateExtInfo() {
		String tableName = "id_t_user_ext";
		try {
			List<ColumnInfo> newFieldList = Lists.newArrayList();
			int exist = tableMapper.findTableCountByTableName(tableName);
			if (exist == 0) {
				// 表不存在，需要整理建表的字段
				newFieldList.addAll(constructFieldByModel());
			}
			List<ColumnInfo> metaFieldList = metaCache.getMetas(tableName);
			List<ColumnInfo> addFieldList = constructFieldByMeta(tableName, metaFieldList);

			// 创建表以及固有字段
			if (!newFieldList.isEmpty()) {
				createTable(tableName, newFieldList);
			}
			// 添加新的字段
			if (!addFieldList.isEmpty()) {
				addFields(tableName, addFieldList);
			}
		} catch (Exception e) {
			throw new RuntimeException("更新表字段失败", e);
		}
	}

	private List<ColumnInfo> constructFieldByMeta(String tableName, List<ColumnInfo> addFieldList) {
		List<String> columnNames = tableMapper.findTableEnsembleByTableName(tableName);
		List<ColumnInfo> finalFieldList = Lists.newArrayList();
		if (!addFieldList.isEmpty()) {
			addFieldList.forEach(commonColumn -> {
				if (!columnNames.contains(commonColumn.getFieldName().toLowerCase())) {
					finalFieldList.add(commonColumn);
				}
			});
		}
		return finalFieldList;
	}

	private List<ColumnInfo> constructFieldByModel() throws Exception {
		List<ColumnInfo> newFieldList = Lists.newArrayList();
		Class<?> clazz = ClassLoader.getSystemClassLoader().loadClass("cn.weit.tables.model.BaseInfo");
		Arrays.stream(clazz.getDeclaredFields()).map(field -> columnFactory.handler(field.getType().getTypeName())
				.genColumnInfo(field)).forEach(newFieldList::add);
		return newFieldList;
	}


	private void addFields(String tableName, List<ColumnInfo> addColumnInfos) {
		TableSql tableSql = this.getTableSql(tableName, addColumnInfos);
		tableMapper.addTableField(tableSql);
	}

	private void createTable(String tableName, List<ColumnInfo> newFieldList) {
		TableSql tableSql = this.getTableSql(tableName, newFieldList);
		tableMapper.createTable(tableSql);
	}

	private TableSql getTableSql(String tableName, List<ColumnInfo> columnInfos) {
		TableSql tableSql = new TableSql();
		tableSql.setName(tableName);
		List<String> primaryKeys = this.getPrimaryKey(columnInfos);
		List<ColumnSql> columnSqls = Lists.newArrayList();
		for (ColumnInfo column : columnInfos) {
			columnSqls.add(new ColumnSql(column, primaryKeys));
		}
		if (!primaryKeys.isEmpty()) {
			tableSql.setPrimaryKey(StringUtils.join(primaryKeys, ","));
		}
		tableSql.setColumnSqls(columnSqls);
		return tableSql;
	}

	private List<String> getPrimaryKey(List<ColumnInfo> columns) {
		List<String> primaryKeys = columns.stream().map(columnInfo -> {
            if (columnInfo.getKey()) {
                return columnInfo.getFieldName();
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
		if (primaryKeys.isEmpty()) {
			primaryKeys.add("id");
		}
		return primaryKeys;
	}
}
