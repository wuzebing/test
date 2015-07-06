package com.ai.c.cinterface.user.status;

public enum UserStatusConst {
	/** 未激活 */
	NOT_ACTIVATE("11"),
	/** 预开通 */
	PRE_OPEN("10"),
	/** 已申请成为 */
	APPLIED("12"),
	/** 申请成为审批拒绝 */
	APPROVAL_REJECT("13"),
	/** 用户开通 */
	ACTIVATE("00"),
	/** 用户冻结 */
	FREEZE("01"),
	/** 用户禁用 */
	FORBIDDEN("05");

	private String status;

	UserStatusConst(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
