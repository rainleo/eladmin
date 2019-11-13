package com.fn.modules.documents.repository;

import com.fn.modules.documents.domain.DocumentReviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-11-13
*/
public interface DocumentReviewerRepository extends JpaRepository<DocumentReviewer, Long>, JpaSpecificationExecutor {
}