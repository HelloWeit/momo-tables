package cn.weit.tables.cache;

import cn.weit.tables.core.ColumnInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author weitong
 */
@Component
public class MetaCache {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void saveMeta(String tableName, List<ColumnInfo> columnInfos) {
		// 实际场景不设置过期时间
		redisTemplate.opsForValue().set(tableName, columnInfos, 1, TimeUnit.HOURS);
	}

	public List<ColumnInfo> getMetas(String tableName) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Object obj = redisTemplate.opsForValue().get(tableName);
		if (obj != null) {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ColumnInfo.class);
			return mapper.readValue(mapper.writeValueAsString(obj), javaType);
		}
		return Lists.newArrayList();
	}
}