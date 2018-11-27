package com.ld.sbjwtjpa.business.account.service;

import com.ld.sbjwtjpa.business.account.model.AccountModel;
import com.ld.sbjwtjpa.utils.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccountService {

    ResponseResult<AccountModel> save(AccountModel model);

    ResponseResult<AccountModel> deleteByUuid(String uuid);

    ResponseResult<AccountModel> updateByUuid(AccountModel model);

    ResponseResult<AccountModel> findOne(AccountModel model);

    ResponseResult<Page<AccountModel>> findAllPage(int pageNow, int pageSize, AccountModel model);

}
