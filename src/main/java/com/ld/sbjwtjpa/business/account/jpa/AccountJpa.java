package com.ld.sbjwtjpa.business.account.jpa;

import com.ld.sbjwtjpa.business.account.model.AccountModel;
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
public interface AccountJpa extends JpaSpecificationExecutor<AccountModel>,
        JpaRepository<AccountModel, String> {

    List<AccountModel> findByAccount(String account);

}
