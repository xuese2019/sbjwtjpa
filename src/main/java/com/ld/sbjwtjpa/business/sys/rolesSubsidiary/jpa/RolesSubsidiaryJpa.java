package com.ld.sbjwtjpa.business.sys.rolesSubsidiary.jpa;

import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model.RolesSubsidiaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RolesSubsidiaryJpa extends JpaSpecificationExecutor<RolesSubsidiaryModel>,
        JpaRepository<RolesSubsidiaryModel, String> {

    List<RolesSubsidiaryModel> findByOrgIdAndJurId(String orgId, String jurId);
}
