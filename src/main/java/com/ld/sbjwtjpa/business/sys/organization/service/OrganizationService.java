package com.ld.sbjwtjpa.business.sys.organization.service;

import com.ld.sbjwtjpa.business.sys.organization.model.OrganizationModel;
import com.ld.sbjwtjpa.utils.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface OrganizationService {

    ResponseResult<OrganizationModel> save(OrganizationModel model);

    ResponseResult<OrganizationModel> delete(String uuid);

    ResponseResult<OrganizationModel> update(OrganizationModel model);

    ResponseResult<OrganizationModel> findById(String uuid);

    ResponseResult<List<OrganizationModel>> findAll(OrganizationModel model);

    //    递归方式查询所有
    ResponseResult<List<OrganizationModel>> findRecursionAll();

}
