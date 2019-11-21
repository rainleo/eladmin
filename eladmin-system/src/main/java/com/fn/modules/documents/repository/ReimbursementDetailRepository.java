package com.fn.modules.documents.repository;

import com.fn.modules.documents.domain.ReimbursementDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
* @author jie
* @date 2019-11-18
*/
public interface ReimbursementDetailRepository extends JpaRepository<ReimbursementDetail, Long>, JpaSpecificationExecutor {
    List<ReimbursementDetail> findByReimbursementDocumentsId(Long id);
}