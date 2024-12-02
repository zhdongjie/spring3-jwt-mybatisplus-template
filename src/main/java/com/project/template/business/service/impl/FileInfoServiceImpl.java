package com.project.template.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.common.constants.SystemConstant;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.common.exception.BaseException;
import com.project.template.business.domain.dto.insert.InsertFileInfoDto;
import com.project.template.business.domain.entity.FileInfo;
import com.project.template.business.domain.vo.FileInfoVo;
import com.project.template.business.mapper.FileInfoMapper;
import com.project.template.business.service.FileInfoService;
import com.project.template.configuration.properties.BaseProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
	private final BaseProperties baseProperties;
	private final FileInfoMapper fileInfoMapper;

	@Override
	@Transactional
	public ResultBean<FileInfoVo> upload(InsertFileInfoDto insertFileInfoDto) {
		return ResultBean.success(uploadFile(insertFileInfoDto));
	}

	@Override
	@Transactional
	public ResultBean<List<FileInfoVo>> uploadBatch(List<InsertFileInfoDto> insertFileInfoDtoList) {
		if (CollUtil.isEmpty(insertFileInfoDtoList)) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "参数异常");
		}
		var fileInfoVoList = insertFileInfoDtoList.stream().map(this::uploadFile).collect(Collectors.toList());
		return ResultBean.success(fileInfoVoList);
	}

	@Override
	@Transactional
	public ResultBean<ResponseEnum> deleteByBusinessId(Long businessId) {
		var queryWrapper = new LambdaQueryWrapper<FileInfo>();
		queryWrapper.eq(FileInfo::getBusinessId, businessId);
		var fileInfoList = fileInfoMapper.selectList(queryWrapper);
		fileInfoList.forEach(this::deleteFile);
		var delete = fileInfoMapper.delete(queryWrapper);
		if (delete < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除失败");
		}
		return ResultBean.success();
	}

	@Override
	@Transactional
	public ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto) {
		var queryWrapper = new LambdaQueryWrapper<FileInfo>();
		queryWrapper.in(FileInfo::getId, deleteBatchDto.getIds());
		var fileInfoList = fileInfoMapper.selectList(queryWrapper);
		fileInfoList.forEach(this::deleteFile);
		var delete = fileInfoMapper.delete(queryWrapper);
		if (delete < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除失败");
		}
		return ResultBean.success();
	}

	private void deleteFile(FileInfo fileInfo) {
		if (ObjUtil.isEmpty(fileInfo)) {
			throw new BaseException(ResponseEnum.RECORD_NOT_FOUND, "记录不存在");
		}
		var path = System.getProperty("user.dir");
		var filePath = path + fileInfo.getPath();
		var file = new File(filePath);
		if (file.exists()) {
			var delete = file.delete();
			if (!delete) {
				throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "删除失败");
			}
		}
	}

	private FileInfoVo uploadFile(InsertFileInfoDto insertFileInfoDto) {
		if (insertFileInfoDto == null) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "参数异常");
		}
		var filePath = StrUtil.isNotBlank(baseProperties.getFilePath())
				? SystemConstant.CHARACTER_FORWARD_SLASH + baseProperties.getFilePath()
				: SystemConstant.CHARACTER_FORWARD_SLASH + "uploadFile";
		var uploadFilePath = System.getProperty("user.dir") + filePath;
		var directory = new File(uploadFilePath);
		if (!directory.exists()) {
			var mkdir = directory.mkdirs();
			if (!mkdir) {
				throw new BaseException(ResponseEnum.INVALID_PARAMETER, "创建文件夹失败");
			}
		}
		var now = DateUtil.formatDate(new Date());
		var filePathClassify = uploadFilePath + SystemConstant.CHARACTER_FORWARD_SLASH + now;
		var filePathClassifyDirectory = new File(filePathClassify);
		if (!filePathClassifyDirectory.exists()) {
			var mkdir = filePathClassifyDirectory.mkdirs();
			if (!mkdir) {
				throw new BaseException(ResponseEnum.INVALID_PARAMETER, "创建文件夹失败");
			}
		}
		// 1. 获取文件信息
		var file = insertFileInfoDto.getFile();
		if (file == null) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "上传文件不能为空");
		}
		var fileOldName = file.getOriginalFilename();
		if (StrUtil.isBlank(fileOldName)) {
			throw new BaseException(ResponseEnum.INVALID_PARAMETER, "文件名称不能为空");
		}
		var fileSuffix = fileOldName.substring(fileOldName.lastIndexOf("."));
		var fileName = IdWorker.getIdStr() + fileSuffix;
		// 2. 存储文件
		try {
			file.transferTo(new File(filePathClassifyDirectory, fileName));
		} catch (IOException e) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "文件上传失败");
		}
		var fileInfo = BeanUtil.copyProperties(insertFileInfoDto, FileInfo.class);
		fileInfo.setPath(filePath + SystemConstant.CHARACTER_FORWARD_SLASH + now + SystemConstant.CHARACTER_FORWARD_SLASH + fileName);
		fileInfo.setCreated(new Date());
		var insert = fileInfoMapper.insert(fileInfo);
		if (insert < 1) {
			throw new BaseException(ResponseEnum.PLEASE_TRY_AGAIN_LATER, "文件上传失败");
		}
		return new FileInfoVo(fileInfo);
	}
}
