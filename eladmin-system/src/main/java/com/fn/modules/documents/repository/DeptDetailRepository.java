package com.fn.modules.documents.repository;

import com.fn.modules.documents.domain.DeptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-11-11
*/
public interface DeptDetailRepository extends JpaRepository<DeptDetail, Long>, JpaSpecificationExecutor {
}