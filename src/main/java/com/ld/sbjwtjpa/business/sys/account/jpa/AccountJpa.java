package com.ld.sbjwtjpa.business.sys.account.jpa;

import com.ld.sbjwtjpa.business.sys.account.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccountJpa extends JpaSpecificationExecutor<AccountModel>,
        JpaRepository<AccountModel, String> {

    List<AccountModel> findByAccount(String account);

}
