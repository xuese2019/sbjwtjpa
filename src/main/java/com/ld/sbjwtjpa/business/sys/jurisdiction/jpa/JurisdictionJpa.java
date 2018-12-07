package com.ld.sbjwtjpa.business.sys.jurisdiction.jpa;

import com.ld.sbjwtjpa.business.sys.jurisdiction.model.JurisdictionModel;
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
public interface JurisdictionJpa extends JpaSpecificationExecutor<JurisdictionModel>,
        JpaRepository<JurisdictionModel, String> {

    List<JurisdictionModel> findByUuidIn(List<String> list);

    @Query(value = "select j.* from jurisdiction_table j" +
            " left join roles_subsidiary_table r on r.jur_id = j.uuid" +
            " where r.org_id = ?1", nativeQuery = true)
    List<JurisdictionModel> findJurisdictionByOrg(@Param("orgId") String orgId);

    List<JurisdictionModel> findByJurParent(String jurParent);
}
