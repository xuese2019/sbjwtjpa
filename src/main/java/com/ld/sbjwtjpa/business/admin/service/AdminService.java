package com.ld.sbjwtjpa.business.admin.service;

import com.ld.sbjwtjpa.business.admin.model.AdminModel;
import com.ld.sbjwtjpa.utils.ResponseResult;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AdminService {


    ResponseResult<AdminModel> findById(String uuid);

    ResponseResult<AdminModel> findByAccount(String account);

}
