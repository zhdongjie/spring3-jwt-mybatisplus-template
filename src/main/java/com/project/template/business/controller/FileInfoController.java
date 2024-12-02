package com.project.template.business.controller;

import com.project.template.common.domain.vo.ResultBean;
import com.project.template.business.domain.dto.insert.InsertFileInfoDto;
import com.project.template.business.domain.vo.FileInfoVo;
import com.project.template.business.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/fileInfo")
@RequiredArgsConstructor
public class FileInfoController {
	private final FileInfoService fileInfoService;

	@PostMapping("upload")
	public ResponseEntity<ResultBean<FileInfoVo>> upload(InsertFileInfoDto insertFileInfoDto) {
		return ResponseEntity.ok(fileInfoService.upload(insertFileInfoDto));
	}

	@PostMapping("uploadBatch")
	public ResponseEntity<ResultBean<List<FileInfoVo>>> uploadBatch(List<InsertFileInfoDto> insertFileInfoDtoList) {
		return ResponseEntity.ok(fileInfoService.uploadBatch(insertFileInfoDtoList));
	}
}
