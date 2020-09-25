package cn.weit.tables.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * restful响应封装
 *
 * @author wmm
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult<T> {

	@ApiModelProperty("状态码，成功为0, 失败自定义")
	private String code;

	@ApiModelProperty("13位服务器时间戳")
	private long timestamp;

	@ApiModelProperty("错误提示，成功响应没有该字段")
	private String error;

	@ApiModelProperty("返回结果数据，默认为json格式")
	private T result;

	private final static String SUCCESS_CODE = "0";

	public static <T> RestResult<T> success() {
		RestResult<T> restResult = new RestResult<>();
		restResult.setCode(SUCCESS_CODE);
		restResult.setTimestamp(System.currentTimeMillis());
		return restResult;
	}


	public static <T> RestResult<T> success(T obj) {
		RestResult<T> restResult = new RestResult<>();
		restResult.setCode(SUCCESS_CODE);
		restResult.setTimestamp(System.currentTimeMillis());
		restResult.setResult(obj);
		return restResult;
	}

	public static RestResult<?> fail(String code, String msg) {
		RestResult<?> restResult = new RestResult<>();
		restResult.setCode(code);
		restResult.setTimestamp(System.currentTimeMillis());
		restResult.setError(msg);
		return restResult;
	}

}
