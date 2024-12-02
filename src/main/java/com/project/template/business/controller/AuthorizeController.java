package com.project.template.business.controller;

import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.LoginDto;
import com.project.template.business.domain.dto.RegisterDto;
import com.project.template.business.domain.vo.AuthTokenVo;
import com.project.template.business.service.AuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/front/authorize")
public class AuthorizeController {
	private final AuthorizeService authorizeService;

	@PostMapping("login")
	public ResponseEntity<ResultBean<AuthTokenVo>> login(@RequestBody LoginDto loginDto) {
		return ResponseEntity.ok(authorizeService.login(loginDto));
	}

	@PostMapping("register")
	public ResponseEntity<ResultBean<ResponseEnum>> register(@RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(authorizeService.register(registerDto));
	}
}
