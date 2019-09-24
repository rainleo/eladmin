package com.fn.repository;

import com.fn.domain.QiniuConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author leo
 * @date 2018-12-31
 */
public interface QiNiuConfigRepository extends JpaRepository<QiniuConfig,Long> {
}
