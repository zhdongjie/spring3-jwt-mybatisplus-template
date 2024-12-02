package com.project.template.business.controller;

import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.business.domain.dto.select.SelectOperationLogDto;
import com.project.template.business.domain.vo.OperationLogVo;
import com.project.template.business.service.OperationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/operationLog")
public class OperationLogController {
	private final OperationLogService userService;

	@GetMapping
	public ResponseEntity<ResultBean<PageVo<OperationLogVo>>> page(SelectOperationLogDto selectOperationLogDto) {
		return ResponseEntity.ok(userService.pageOperationLog(selectOperationLogDto));
	}
}
