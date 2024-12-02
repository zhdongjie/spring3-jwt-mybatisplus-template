package com.project.template.business.controller;

import com.project.template.common.aspect.annotation.Log;
import com.project.template.common.constants.LogConstant;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.update.UpdateMeDto;
import com.project.template.business.domain.dto.update.UpdateUserPassword;
import com.project.template.business.domain.vo.MeVo;
import com.project.template.business.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/system/me")
public class MeController {
	private final UserService userService;

	@GetMapping
	public ResponseEntity<ResultBean<MeVo>> me() {
		return ResponseEntity.ok(userService.me());
	}

	@PutMapping("changePassword")
	@Log(value = "修改密码", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> changePassword(@RequestBody UpdateUserPassword updateUserPassword) {
		return ResponseEntity.ok(userService.changePassword(updateUserPassword));
	}

	@PutMapping
	@Log(value = "修改个人信息", type = LogConstant.OperationTypeEnum.UPDATE)
	public ResponseEntity<ResultBean<ResponseEnum>> update(@RequestBody UpdateMeDto updateMeDto) {
		return ResponseEntity.ok(userService.updateMe(updateMeDto));
	}
}
