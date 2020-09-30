package cn.weit.tables.core;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author weitong
 */
@Component("columnFactory")
@Slf4j
public class ColumnFactory {
	private final Map<String, IColumn> iColumnMap = Maps.newConcurrentMap();

	public ColumnFactory(Map<String, IColumn> iColumnMap) {
		this.iColumnMap.clear();
		iColumnMap.forEach(this.iColumnMap::put);
	}

	public IColumn handler(String className){
		List<String> classNameList = Splitter.on(".").trimResults().splitToList(className);
		String type = classNameList.get(classNameList.size()-1).toLowerCase() + "Column";
		return this.iColumnMap.get(type);
	}
}
