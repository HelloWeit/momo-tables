package cn.weit.tables.constants;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author weitong
 */

public enum ColType {
	/**
	 *
	 */
	booleanColumn,

	varcharColumn,

	error,

	;

	public static String getType(String fieldType) {
		return Arrays.stream(ColType.values()).filter(colType -> StringUtils.equals(colType.name(), fieldType))
				.findFirst().orElse(ColType.error).name();
	}

}
