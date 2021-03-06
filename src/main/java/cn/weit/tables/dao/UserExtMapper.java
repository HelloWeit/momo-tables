package cn.weit.tables.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.weit.tables.model.BaseInfo;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weitong
 * @since 2020-09-25
 */
@Repository
public interface UserExtMapper  {

	int addAll(@Param("baseInfo") BaseInfo baseInfo, @Param("keys") List keys, @Param("values") List values);


}
