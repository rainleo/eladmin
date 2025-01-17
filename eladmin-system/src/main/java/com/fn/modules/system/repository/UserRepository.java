package com.fn.modules.system.repository;

import com.fn.modules.system.domain.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Date;
import java.util.List;

/**
 * @author leo
 * @date 2019-09-22
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {

    /**
     * findByUsername
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * findByEmail
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 修改密码
     * @param username
     * @param pass
     */
    @Modifying
    @Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where username = ?1",nativeQuery = true)
    void updatePass(String username, String pass, Date lastPasswordResetTime);

    /**
     * 修改头像
     * @param username
     * @param url
     */
    @Modifying
    @Query(value = "update user set avatar = ?2 where username = ?1",nativeQuery = true)
    void updateAvatar(String username, String url);

    /**
     * 修改邮箱
     * @param username
     * @param email
     */
    @Modifying
    @Query(value = "update user set email = ?2 where username = ?1",nativeQuery = true)
    void updateEmail(String username, String email);

    /**
     * findByphone
     * @param phone
     * @return
     */
    User findByPhone(String phone);

    /**
     * findByJobIdIn
     * @param jobId
     * @return
     */
    List<User> findByJobIdIn(List<Long> jobId);

    /**
     * 修改密码
     * @param username
     * @param pass
     */
    @Modifying
    @Query(value = "update user set password = ?2 , last_password_reset_time = ?3 where phone = ?1",nativeQuery = true)
    void updatePassbyPhone(String username, String pass, Date lastPasswordResetTime);

}
