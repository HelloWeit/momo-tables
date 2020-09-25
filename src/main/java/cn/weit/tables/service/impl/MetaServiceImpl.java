package cn.weit.tables.service.impl;

import cn.weit.tables.cache.MetaCache;
import cn.weit.tables.core.ColumnFactory;
import cn.weit.tables.core.ColumnInfo;
import cn.weit.tables.dao.UserExtMapper;
import cn.weit.tables.dto.DemoExtDto;
import cn.weit.tables.dto.MetaColDefineDto;
import cn.weit.tables.dto.MetaDto;
import cn.weit.tables.model.BaseInfo;
import cn.weit.tables.service.MetaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author weitong
 */
@Service
@Slf4j
public class MetaServiceImpl implements MetaService {

	@Autowired
	private ColumnFactory columnFactory;

	@Autowired
	private MetaCache metaCache;


	@Autowired
	private UserExtMapper userExtMapper;

	@Override
	public void parse() {
		List<MetaColDefineDto> metaColDefineDtos = Lists.newArrayList();
		metaColDefineDtos.add(genData("user_name", "varchar"));
		metaColDefineDtos.add(genData("status", "boolean"));

		Map<String, List<MetaColDefineDto>> typeAndMetaColDefineDtoMap = Maps.newHashMap();
		metaColDefineDtos.forEach(metaColDefineDto -> typeAndMetaColDefineDtoMap
				.computeIfAbsent(metaColDefineDto.getColType(), k -> Lists.newArrayList())
				.add(metaColDefineDto));
		List<ColumnInfo> columnInfos = Lists.newArrayList();
		typeAndMetaColDefineDtoMap.forEach((type, metaColDefineDtoList)
				-> columnInfos.addAll(columnFactory.handler(type).genColumnInfos(metaColDefineDtoList)));
		if (columnInfos.isEmpty()) {
			log.info("未找到需要解析的field");
			return;
		}
		//存入缓存 默认就一个扩展表
		String tableName = "id_t_user_ext";
		metaCache.saveMeta(tableName, columnInfos);

	}

	private MetaColDefineDto genData(String colName, String colType) {
		MetaColDefineDto metaColDefineDto = new MetaColDefineDto();
		metaColDefineDto.setCategory("用户");
		metaColDefineDto.setCode("");
		metaColDefineDto.setColName(colName);
		metaColDefineDto.setColType(colType);
		metaColDefineDto.setDefineName("");
		metaColDefineDto.setStatus("enable");
		metaColDefineDto.setTableName("");
		metaColDefineDto.setTenantId("1");
		metaColDefineDto.setId(2L);
		return metaColDefineDto;
	}

	@Override
	public void addValue(DemoExtDto demoExtDto) {
		BaseInfo baseInfo = new BaseInfo();
		baseInfo.setId(demoExtDto.getId());
		List<String> keys = demoExtDto.getMetaDtos().stream().map(MetaDto::getKey).collect(Collectors.toList());
		List<Object> values = demoExtDto.getMetaDtos().stream().map(MetaDto::getValue).collect(Collectors.toList());
		userExtMapper.addAll(baseInfo, keys, values);
	}
}
