package com.project.template.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.template.common.domain.dto.DeleteBatchDto;
import com.project.template.common.domain.vo.PageVo;
import com.project.template.common.domain.vo.ResultBean;
import com.project.template.common.enums.ResponseEnum;
import com.project.template.business.domain.dto.insert.InsertUserDto;
import com.project.template.business.domain.dto.select.SelectUserDto;
import com.project.template.business.domain.dto.update.EnableUserDto;
import com.project.template.business.domain.dto.update.UpdateMeDto;
import com.project.template.business.domain.dto.update.UpdateUserDto;
import com.project.template.business.domain.dto.update.UpdateUserPassword;
import com.project.template.business.domain.entity.User;
import com.project.template.business.domain.vo.MeVo;
import com.project.template.business.domain.vo.UserVo;

public interface UserService extends IService<User> {
	ResultBean<ResponseEnum> insert(InsertUserDto insertUserDto);

	ResultBean<ResponseEnum> delete(DeleteBatchDto deleteBatchDto);

	ResultBean<ResponseEnum> changeStatus(EnableUserDto enableUserDto);

	ResultBean<ResponseEnum> resetPassword(Long id);

	ResultBean<PageVo<UserVo>> pageUser(SelectUserDto selectUserDto);

	ResultBean<MeVo> me();

	ResultBean<ResponseEnum> changePassword(UpdateUserPassword updateUserPassword);

	ResultBean<ResponseEnum> updateUser(UpdateUserDto updateUserDto);

	ResultBean<ResponseEnum> updateMe(UpdateMeDto updateMeDto);
}
