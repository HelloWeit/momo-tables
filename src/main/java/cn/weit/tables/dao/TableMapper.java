package cn.weit.tables.dao;


import cn.weit.tables.core.TableSql;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author weitong
 */
@Repository
public interface TableMapper {

	/**
	 * 建表
	 * @param tableSql 建表语句
	 */
	void createTable(TableSql tableSql);

	/**
	 * 查询表是否存在，
	 * @param tableName 表名
	 * @return 存在返回1，不存在返回0
	 */
	int findTableCountByTableName(@Param("tableName") String tableName);

	/**
	 * 获取已知表结构
	 * @param tableName 表名
	 * @return 字段信息
	 */
	List<String> findTableEnsembleByTableName(@Param("tableName") String tableName);

	/**
	 * 增加字段
	 * @param tableSql 增加字段语句
	 */
	void addTableField(TableSql tableSql);

}
