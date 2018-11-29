package com.ld.sbjwtjpa.business.sys.jurisdiction.service.impl;

import com.ld.sbjwtjpa.business.sys.jurisdiction.jpa.JurisdictionJpa;
import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
import com.ld.sbjwtjpa.business.sys.jurisdiction.service.JurisdictionService;
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

@Service
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    private JurisdictionJpa jpa;

    //    查询条件
    private Specification<JurisdictionModel> queryTj(JurisdictionModel model) {
        return new Specification<JurisdictionModel>() {//查询条件构造
            @Override
            public Predicate toPredicate(Root<JurisdictionModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (model != null) {
                    if (model.getJurParent() != null && !model.getJurParent().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("jurParent").as(String.class), model.getJurParent());
                        predicates.add(cb.and(p1));
                    }
                    if (model.getJurFlag() != null && !model.getJurFlag().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("jurFlag").as(String.class), model.getJurFlag());
                        predicates.add(cb.and(p1));
                    }
                    if (model.getJurName() != null && !model.getJurName().isEmpty()) {
                        Predicate p1 = cb.equal(root.get("jurName").as(String.class), model.getJurName());
                        predicates.add(cb.and(p1));
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    @Transactional
    @Override
    public ResponseResult<JurisdictionModel> save(JurisdictionModel model) {
        jpa.save(model);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<JurisdictionModel> delete(String uuid) {
        jpa.deleteById(uuid);
        return new ResponseResult<>(true, "成功");
    }

    @Transactional
    @Override
    public ResponseResult<JurisdictionModel> update(JurisdictionModel model) {
        JurisdictionModel one = jpa.getOne(model.getUuid());
        if (one.getJurFlag() != null && !one.getJurFlag().isEmpty())
            one.setJurFlag(model.getJurFlag());
        if (one.getJurName() != null && !one.getJurName().isEmpty())
            one.setJurName(model.getJurName());
        return new ResponseResult<>(true, "成功");
    }

    @Override
    public ResponseResult<JurisdictionModel> findById(String uuid) {
        Optional<JurisdictionModel> one = jpa.findById(uuid);
        if (one.orElse(null) != null)
            return new ResponseResult<>(true, "成功", one.get());
        return new ResponseResult<>(false, "未查询到记录");
    }

    @Override
    public ResponseResult<List<JurisdictionModel>> findAll(JurisdictionModel model) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "jurName"));//排序信息
        Specification<JurisdictionModel> spec = queryTj(model);
        List<JurisdictionModel> list = jpa.findAll(spec, Sort.by(orders));
        if (list.size() > 0)
            return new ResponseResult<>(true, "成功", list);
        else
            return new ResponseResult<>(false, "未查询到数据");
    }

    @Override
    public ResponseResult<List<JurisdictionModel>> findRecursionAll() {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "jurName"));//排序信息
        List<JurisdictionModel> list = jpa.findAll(Sort.by(orders));
        if (list.size() > 0) {
            List<JurisdictionModel> list1 = buildByRecursive(list);
            return new ResponseResult<>(true, "成功", list);
        } else
            return new ResponseResult<>(false, "未查询到数据");
    }

    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    private static List<JurisdictionModel> buildByRecursive(List<JurisdictionModel> treeNodes) {
        List<JurisdictionModel> trees = new ArrayList<JurisdictionModel>();
        for (JurisdictionModel treeNode : treeNodes) {
            if ("0".equals(treeNode.getJurParent())) {
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
    private static JurisdictionModel findChildren(JurisdictionModel treeNode, List<JurisdictionModel> treeNodes) {
        for (JurisdictionModel it : treeNodes) {
            if (treeNode.getUuid().equals(it.getJurParent())) {
                treeNode.getList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }
}
