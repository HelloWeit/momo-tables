package cn.weit.tables.constants;

/**
 * @author weitong
 */

public enum ExtType {
	/**
	 * 用户
	 */
	USER(1),
	/**
	 * 机构
	 */
	ORG(2),
	/**
	 * 设备
	 */
	DEVICE(3),
	;

	private int value;

	ExtType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
