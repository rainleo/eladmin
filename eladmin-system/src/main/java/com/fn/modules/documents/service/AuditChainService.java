package com.fn.modules.documents.service;

import com.fn.modules.documents.domain.AuditChain;
import com.fn.modules.documents.service.dto.AuditChainDTO;
import com.fn.modules.documents.service.dto.AuditChainQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import com.fn.modules.system.domain.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author jie
* @date 2019-11-11
*/
//@CacheConfig(cacheNames = "auditChain")
public interface AuditChainService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AuditChainQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AuditChainQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    AuditChainDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AuditChainDTO create(AuditChain resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AuditChain resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * 根据sorted和source查询审批人
     * @param criteria
     * @return
     */
    List<User> queryAuditUserBySortedSource(AuditChainQueryCriteria criteria);
}