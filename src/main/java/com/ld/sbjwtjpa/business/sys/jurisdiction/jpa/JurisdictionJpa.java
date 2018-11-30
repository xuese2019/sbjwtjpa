package com.ld.sbjwtjpa.business.sys.jurisdiction.jpa;

import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionJpa extends JpaSpecificationExecutor<JurisdictionModel>,
        JpaRepository<JurisdictionModel, String> {

    List<JurisdictionModel> findByUuidIn(List<String> list);
}
