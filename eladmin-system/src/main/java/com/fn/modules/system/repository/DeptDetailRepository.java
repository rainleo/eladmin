package com.fn.modules.system.repository;

import com.fn.modules.system.domain.DeptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author jie
* @date 2019-11-18
*/
public interface DeptDetailRepository extends JpaRepository<DeptDetail, Long>, JpaSpecificationExecutor {
}