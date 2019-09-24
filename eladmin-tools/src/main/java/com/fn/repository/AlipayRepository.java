package com.fn.repository;

import com.fn.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author leo
 * @date 2018-12-31
 */
public interface AlipayRepository extends JpaRepository<AlipayConfig,Long> {
}
