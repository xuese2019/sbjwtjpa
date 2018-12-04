package com.ld.sbjwtjpa.business.sys.organization.service.impl;

import com.ld.sbjwtjpa.business.sys.organization.jpa.OrganizationJpa;
import com.ld.sbjwtjpa.business.sys.organization.model.OrganizationModel;
import com.ld.sbjwtjpa.business.sys.organization.service.OrganizationService;
import com.ld.sbjwtjpa.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationJpa jpa;

    //    查询条件
    private Specification<OrganizationModel> queryTj(OrganizationModel model) {
        return new Specification<OrganizationModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<OrganizationModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getOrgParent() != null && !model.getOrgParent().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("orgParent").as(String.class), model.getOrgParent());
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Transactional
    @Override
    public ResponseResult<OrganizationModel> save(OrganizationModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<OrganizationModel> delete(String uuid) {
        List<OrganizationModel> list = jpa.findByOrgParent(uuid);
        if (list.size() > 0)
            return new ResponseResult<>(false, "请先删除下级");
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<OrganizationModel> update(OrganizationModel model) {
        OrganizationModel one = jpa.getOne(model.getUuid());
        if (model.getOrgName() != null && !model.getOrgName().isEmpty())
            one.setOrgName(model.getOrgName());
        if (model.getOrgParent() != null && !model.getOrgParent().isEmpty())
            one.setOrgParent(model.getOrgParent());
        jpa.flush();
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<OrganizationModel> findById(String uuid) {
        if (uuid == null || uuid.isEmpty())
            return new ResponseResult<>(false, "未传入数据", null);
        Optional<OrganizationModel> model = jpa.findById(uuid);
        if (model.orElse(null) != null)
            return new ResponseResult<>(true, "成功", model.get());
        else
            return new ResponseResult<>(false, "未查询到数据", null);

    }

    @Override
    public ResponseResult<List<OrganizationModel>> findAll(OrganizationModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "orgParent"));//排序信息
//        orders.add(new Sort.Order(Sort.Direction.ASC, "account"));//排序信息
        Specification<OrganizationModel> spec = queryTj(model);
        List<OrganizationModel> list = jpa.findAll(spec);
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }

    @Override
    public ResponseResult<List<OrganizationModel>> findRecursionAll() {
        List<OrganizationModel> list = jpa.findAll();
        if (list.size() > 0) {
            List<OrganizationModel> list1 = buildByRecursive(list);
            return new ResponseResult<>(true, "成功", list1);
        }
        return new ResponseResult<>(false, "未查询到记录");
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    private static List<OrganizationModel> buildByRecursive(List<OrganizationModel> treeNodes) {
        List<OrganizationModel> trees = new ArrayList<>();
        for (OrganizationModel treeNode : treeNodes) {
            if ("0".equals(treeNode.getOrgParent())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     *
     * @param treeNodes
     * @return
     */
    private static OrganizationModel findChildren(OrganizationModel treeNode, List<OrganizationModel> treeNodes) {
        for (OrganizationModel it : treeNodes) {
            if (treeNode.getUuid().equals(it.getOrgParent())) {
                treeNode.getList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
