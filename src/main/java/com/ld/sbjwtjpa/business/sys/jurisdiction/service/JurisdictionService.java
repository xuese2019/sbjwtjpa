package com.ld.sbjwtjpa.business.sys.jurisdiction.service;

import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
import com.ld.sbjwtjpa.utils.ResponseResult;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionService {

    ResponseResult<JurisdictionModel> save(JurisdictionModel model);

    ResponseResult<JurisdictionModel> delete(String uuid);

    ResponseResult<JurisdictionModel> update(JurisdictionModel model);

    ResponseResult<JurisdictionModel> findById(String uuid);

    ResponseResult<List<JurisdictionModel>> findAll(JurisdictionModel model);

    //    递归方式查询所有
    ResponseResult<List<JurisdictionModel>> findRecursionAll();

}
