package com.ld.sbjwtjpa.business.account.service.impl;

import com.ld.sbjwtjpa.business.account.jpa.AccountJpa;
import com.ld.sbjwtjpa.business.account.model.AccountModel;
import com.ld.sbjwtjpa.business.account.service.AccountService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountJpa jpa;

    //    查询条件
    private Specification<AccountModel> queryTj(AccountModel model) {
        return new Specification<AccountModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<AccountModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("uuid").as(String.class), model.getUuid());
                        predicates.add(cb.and(p1));
                    }
                    if (model.getAccount() != null && !model.getAccount().isEmpty()) {
                        Predicate p1 = cb.like(root.get("account").as(String.class), "%" + model.getAccount() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> save(AccountModel model) {
        List<AccountModel> list = jpa.findByAccount(model.getAccount());
        if (list.size() > 0)
            return new ResponseResult<>(false, "账户名称冲突");
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> deleteByUuid(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<AccountModel> updateByUuid(AccountModel model) {
        AccountModel one = jpa.getOne(model.getUuid());
        if (one != null) {
            if (model.getPassword() != null && !model.getPassword().isEmpty())
                one.setPassword(model.getPassword());
        }
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<AccountModel> findOne(AccountModel model) {
        Specification<AccountModel> queryTj = queryTj(model);
        Optional<AccountModel> one = jpa.findOne(queryTj);
        if (one.orElse(null) != null)
            return new ResponseResult<>(true, "成功", one.get());
        else
            return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<Page<AccountModel>> findAllPage(int pageNow, int pageSize, AccountModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "systimes"));//排序信息
        orders.add(new Sort.Order(Sort.Direction.ASC, "account"));//排序信息
        Specification<AccountModel> spec = queryTj(model);
        Page<AccountModel> page = jpa.findAll(spec, PageRequest.of(pageNow, pageSize, Sort.by(orders)));
        if (page.getTotalElements() > 0)
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }
}
