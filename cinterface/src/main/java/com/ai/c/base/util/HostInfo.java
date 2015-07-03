package com.ai.c.base.util;

import java.net.InetAddress;

/**
 * 本机信息
 * 
 * @author zouning
 * @time 2014-8-7 下午2:53:30
 * 
 */

public final class HostInfo {

	/**
	 * 获取主机名
	 * 
	 * @return
	 */
	public static final String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取主机IP
	 * 
	 * @return
	 */
	public static final String getHostIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			return "";
		}
	}
}
