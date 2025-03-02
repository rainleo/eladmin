package com.fn.modules.documents.repository;

import com.fn.modules.documents.domain.AccountingSubjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-11-04
*/
public interface AccountingSubjectsRepository extends JpaRepository<AccountingSubjects, Long>, JpaSpecificationExecutor {
}