package com.ld.sbjwtjpa.business.sys.admin.jpa;

import com.ld.sbjwtjpa.business.sys.admin.model.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AdminJpa extends JpaSpecificationExecutor<AdminModel>,
        JpaRepository<AdminModel, String> {

    List<AdminModel> findByAccount(String account);

}
