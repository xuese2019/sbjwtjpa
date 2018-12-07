package com.ld.sbjwtjpa.business.user.people.serivice;

import com.ld.sbjwtjpa.business.user.people.model.PeopleModel;
import com.ld.sbjwtjpa.utils.ResponseResult;
import org.springframework.data.domain.Page;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PeopleService {

    ResponseResult<PeopleModel> save(PeopleModel model);

    ResponseResult<PeopleModel> deleteByUuid(String uuid);

    ResponseResult<PeopleModel> updateByUuid(PeopleModel model);

    ResponseResult<PeopleModel> updateByAccId(PeopleModel model);

    ResponseResult<PeopleModel> findOne(PeopleModel model);

    ResponseResult<Page<PeopleModel>> findAllPage(int pageNow, int pageSize, PeopleModel model);

}
