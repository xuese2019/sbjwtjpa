package com.ld.sbjwtjpa.business.user.people.jpa;

import com.ld.sbjwtjpa.business.user.people.model.PeopleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PeopleJpa extends JpaSpecificationExecutor<PeopleModel>,
        JpaRepository<PeopleModel, String> {

    List<PeopleModel> findByAccId(String accId);

}
