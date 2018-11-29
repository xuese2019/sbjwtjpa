package com.ld.sbjwtjpa.business.sys.rolesSubsidiary.service;

import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model.RolesSubsidiaryModel;
import com.ld.sbjwtjpa.utils.ResponseResult;

import java.util.List;

public interface RolesSubsidiaryService {

    ResponseResult<RolesSubsidiaryModel> save(RolesSubsidiaryModel model);

    ResponseResult<RolesSubsidiaryModel> delete(String uuid);

    ResponseResult<List<RolesSubsidiaryModel>> findAll(RolesSubsidiaryModel model);
}
