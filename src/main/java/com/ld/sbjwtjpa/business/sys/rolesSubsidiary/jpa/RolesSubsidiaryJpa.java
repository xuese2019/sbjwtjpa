package com.ld.sbjwtjpa.business.sys.rolesSubsidiary.jpa;

import com.ld.sbjwtjpa.business.sys.rolesSubsidiary.model.RolesSubsidiaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface RolesSubsidiaryJpa extends JpaSpecificationExecutor<RolesSubsidiaryModel>,
        JpaRepository<RolesSubsidiaryModel, String> {
}
