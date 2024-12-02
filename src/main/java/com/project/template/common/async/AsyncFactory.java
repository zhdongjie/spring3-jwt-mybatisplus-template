package com.project.template.common.async;

import cn.hutool.extra.spring.SpringUtil;
import com.project.template.business.domain.entity.LoginLog;
import com.project.template.business.domain.entity.OperationLog;
import com.project.template.business.service.LoginLogService;
import com.project.template.business.service.OperationLogService;
import com.project.template.common.utils.ServletUtils;

import java.util.Date;

public class AsyncFactory {
	/**
	 * 记录登录日志
	 */
	public static Runnable loginLog(String username, String status) {
		return () -> {
			var request = ServletUtils.getRequest();
			String clientIp = ServletUtils.getClientIP(request);
			var loginLog = new LoginLog();
			loginLog.setUsername(username);
			loginLog.setStatus(status);
			loginLog.setLoginLocation("");
			loginLog.setCreated(new Date());
			loginLog.setIpAddress(clientIp);
			SpringUtil.getBean(LoginLogService.class).save(loginLog);
		};
	}

	/**
	 * 记录操作日志
	 */
	public static Runnable operationLog(OperationLog operationLog) {
		return () -> SpringUtil.getBean(OperationLogService.class).save(operationLog);
	}
}
