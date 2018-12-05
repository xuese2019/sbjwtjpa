package com.ld.sbjwtjpa.business.sys.rolesSubsidiary.service.impl;

import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.jpa.RolesSubsidiaryJpa;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model.RolesSubsidiaryModel;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.service.RolesSubsidiaryService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class RolesSubsidiaryServiceImpl implements RolesSubsidiaryService {

    @Autowired
    private RolesSubsidiaryJpa jpa;

    //    查询条件
    private Specification<RolesSubsidiaryModel> queryTj(RolesSubsidiaryModel model) {
        return new Specification<RolesSubsidiaryModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<RolesSubsidiaryModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getOrgId() != null && !model.getOrgId().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("orgId").as(String.class), model.getOrgId());
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

    @Override
    public ResponseResult<RolesSubsidiaryModel> save(RolesSubsidiaryModel model) {
        List<RolesSubsidiaryModel> list = jpa.findByOrgIdAndJurId(model.getOrgId(), model.getJurId());
        if (list.size() > 0)
            return new ResponseResult<>(false, "当前记录已存在");
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<RolesSubsidiaryModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<List<RolesSubsidiaryModel>> findAll(RolesSubsidiaryModel model) {
        Specification<RolesSubsidiaryModel> spec = queryTj(model);
        List<RolesSubsidiaryModel> list = jpa.findAll(spec);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }
}
