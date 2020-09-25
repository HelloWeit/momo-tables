package cn.weit.tables.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @author weitong
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("id_t_user_ext")
@ApiModel(value="UserExt对象", description="")
public class BaseInfo {

    private Long id;

}
