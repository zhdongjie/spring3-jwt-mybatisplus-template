package com.project.template.common.aspect;

import cn.hutool.core.util.ObjUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.project.template.common.aspect.annotation.Log;
import com.project.template.common.async.AsyncFactory;
import com.project.template.common.async.AsyncManager;
import com.project.template.common.constants.LogConstant;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.business.domain.entity.OperationLog;
import com.project.template.common.utils.AuthUtils;
import com.project.template.common.utils.ServletUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

@Aspect
@Component
public class LogAspect {

	/**
	 * 声明切点
	 */
	@Pointcut("@annotation(com.project.template.common.aspect.annotation.Log)")
	public void logPointCut() {
	}

	/**
	 * 请求后执行
	 */
	@AfterReturning(pointcut = "logPointCut()", returning = "responseEntity")
	public void doAfterReturning(JoinPoint joinPoint, ResponseEntity<?> responseEntity) {
		handle(joinPoint, responseEntity, null);
	}

	/**
	 * 异常后执行
	 */
	@AfterThrowing(pointcut = "logPointCut()", throwing = "throwable")
	public void doAfterReturning(JoinPoint joinPoint, Throwable throwable) {
		handle(joinPoint, null, throwable);
	}

	private void handle(JoinPoint joinPoint, ResponseEntity<?> responseEntity, Throwable throwable) {
		var methodSignature = (MethodSignature) joinPoint.getSignature();
		var method = methodSignature.getMethod();
		if (ObjUtil.isEmpty(method)) {
			return;
		}
		var logAnnotation = method.getAnnotation(Log.class);
		if (ObjUtil.isEmpty(logAnnotation)) {
			return;
		}
		var request = ServletUtils.getRequest();
		var requestMethod = request.getMethod();
		var headerNames = request.getHeaderNames();
		var params = new JSONObject();
		var headerMap = new HashMap<String, String>();
		while (headerNames.hasMoreElements()) {
			var headerName = headerNames.nextElement();
			headerMap.put(headerName, request.getHeader(headerName));
		}
		params.put("header", JSONArray.of(headerMap));
		var args = joinPoint.getArgs();
		Arrays.asList(args).forEach(arg -> params.put("body", JSONArray.of(arg)));
		String username = AuthUtils.getUsername();
		var operationLog = new OperationLog();
		operationLog.setCreated(new Date());
		operationLog.setTitle(logAnnotation.value());
		operationLog.setType(logAnnotation.type().name());
		operationLog.setUsername(username);
		operationLog.setMethod(requestMethod);
		if (logAnnotation.saveParam()) {
			operationLog.setParams(JSON.toJSONString(params));
		}
		if (ObjUtil.isNotEmpty(responseEntity)) {
			var result = (ResultBean<?>) responseEntity.getBody();
			operationLog.setStatus(LogConstant.OperationStatusEnum.SUCCESS.name());
			if (logAnnotation.saveResult()) {
				operationLog.setResult(result.toString());
			}
			operationLog.setRemark(result.getMessage());
		} else {
			operationLog.setStatus(LogConstant.OperationStatusEnum.FAIL.name());
			if (logAnnotation.saveResult()) {
				operationLog.setResult(throwable.toString());
			}
			operationLog.setRemark(throwable.getMessage());
		}
		AsyncManager.getInstance().executor(AsyncFactory.operationLog(operationLog));
	}
}
