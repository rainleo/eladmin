package com.fn.repository;

import com.fn.domain.EmailConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author leo
 * @date 2018-12-26
 */
public interface EmailRepository extends JpaRepository<EmailConfig,Long> {
}
