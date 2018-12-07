package com.ld.sbjwtjpa.business.user.people.serivice.impl;

import com.ld.sbjwtjpa.business.user.people.jpa.PeopleJpa;
import com.ld.sbjwtjpa.business.user.people.model.PeopleModel;
import com.ld.sbjwtjpa.business.user.people.serivice.PeopleService;
import com.ld.sbjwtjpa.utils.MyExceptions;
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
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleJpa jpa;

    //    查询条件
    private Specification<PeopleModel> queryTj(PeopleModel model) {
        return new Specification<PeopleModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<PeopleModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getUuid() != null && !model.getUuid().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("uuid").as(String.class), model.getUuid());
                        predicates.add(cb.and(p1));
                    }
                    if (model.getName() != null && !model.getName().isEmpty()) {
                        Predicate p1 = cb.like(root.get("name").as(String.class), "%" + model.getName() + "%");
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Transactional
    @Override
    public ResponseResult<PeopleModel> save(PeopleModel model) {
        if (model.getAccId() == null || model.getAccId().isEmpty())
            throw new MyExceptions("当前员工所关联的账户主键不能为空");
        List<PeopleModel> list = jpa.findByAccId(model.getAccId());
        if (list.size() > 0)
            throw new MyExceptions("4");
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<PeopleModel> deleteByUuid(String uuid) {
        if (uuid == null || uuid.isEmpty())
            throw new MyExceptions("3");
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<PeopleModel> updateByUuid(PeopleModel model) {
        if (model.getUuid() == null || model.getUuid().isEmpty())
            throw new MyExceptions("3");
        Optional<PeopleModel> opt = jpa.findById(model.getUuid());
        if (opt.orElse(null) == null)
            throw new MyExceptions("5");
        PeopleModel entity = opt.get();
        if (model.getVersion() - entity.getVersion() != 0)
            throw new MyExceptions("2");
        if (model.getName() != null && !model.getName().isEmpty())
            entity.setName(model.getName());
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<PeopleModel> updateByAccId(PeopleModel model) {
        if (model.getAccId() == null || model.getAccId().isEmpty())
            throw new MyExceptions("3");
        List<PeopleModel> list = jpa.findByAccId(model.getAccId());
        if (list.size() <= 0)
            throw new MyExceptions("5");
        PeopleModel entity = list.get(0);
        if (model.getVersion() - entity.getVersion() != 0)
            throw new MyExceptions("2");
        if (model.getName() != null && !model.getName().isEmpty())
            entity.setName(model.getName());
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<PeopleModel> findOne(PeopleModel model) {
        Specification<PeopleModel> queryTj = queryTj(model);
        Optional<PeopleModel> one = jpa.findOne(queryTj);
        if (one.orElse(null) == null)
            throw new MyExceptions("1");
        return new ResponseResult<>(true, "成功", one.get());
    }

    @Override
    public ResponseResult<Page<PeopleModel>> findAllPage(int pageNow, int pageSize, PeopleModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "dateOfEntry"));//排序信息
        orders.add(new Sort.Order(Sort.Direction.ASC, "name"));//排序信息
        Specification<PeopleModel> spec = queryTj(model);
        Page<PeopleModel> page = jpa.findAll(spec, PageRequest.of(pageNow, pageSize, Sort.by(orders)));
        if (page.getTotalElements() > 0)
            return new ResponseResult<>(true, "成功", page);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }
}
