package com.ld.sbjwtjpa.business.sys.admin.service.impl;

import com.ld.sbjwtjpa.business.sys.admin.jpa.AdminJpa;
import com.ld.sbjwtjpa.business.sys.admin.model.AdminModel;
import com.ld.sbjwtjpa.business.sys.admin.service.AdminService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminJpa jpa;

    @Override
    public ResponseResult<AdminModel> findById(String uuid) {
        Optional<AdminModel> one = jpa.findById(uuid);
        if (one.orElse(null) != null)
            return new ResponseResult<>(true, "成功", one.get());
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<AdminModel> findByAccount(String account) {
        List<AdminModel> list = jpa.findByAccount(account);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list.get(0));
        else
            return new ResponseResult<>(false, "未查询到记录");
    }
}
