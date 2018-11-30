package com.ld.sbjwtjpa.business.sys.organization.jpa;

import com.ld.sbjwtjpa.business.sys.organization.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface OrganizationJpa extends JpaSpecificationExecutor<OrganizationModel>,
        JpaRepository<OrganizationModel, String> {

    List<OrganizationModel> findByOrgParent(String parent);

}
