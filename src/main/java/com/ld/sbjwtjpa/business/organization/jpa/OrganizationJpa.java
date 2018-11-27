package com.ld.sbjwtjpa.business.organization.jpa;

import com.ld.sbjwtjpa.business.organization.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Resource
public interface OrganizationJpa extends JpaSpecificationExecutor<OrganizationModel>,
        JpaRepository<OrganizationModel, String> {

    List<OrganizationModel> findByOrgParent(String parent);

}
