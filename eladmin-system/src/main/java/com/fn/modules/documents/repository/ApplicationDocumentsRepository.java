package com.fn.modules.documents.repository;

import com.fn.modules.documents.domain.ApplicationDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-11-04
*/
public interface ApplicationDocumentsRepository extends JpaRepository<ApplicationDocuments, Long>, JpaSpecificationExecutor {
}