package com.project.template.business.controller;

import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.business.domain.dto.select.SelectLoginLogDto;
import com.project.template.business.domain.vo.LoginLogVo;
import com.project.template.business.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/loginLog")
public class LoginLogController {
	private final LoginLogService loginLogService;

	@GetMapping
	public ResponseEntity<ResultBean<PageVo<LoginLogVo>>> page(SelectLoginLogDto selectLoginLogDto) {
		return ResponseEntity.ok(loginLogService.pageLoginLog(selectLoginLogDto));
	}
}
