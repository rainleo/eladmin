package com.fn.modules.documents.service;

import com.fn.modules.documents.domain.AccountingSubjects;
import com.fn.modules.documents.service.dto.AccountingSubjectsDTO;
import com.fn.modules.documents.service.dto.AccountingSubjectsQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author jie
* @date 2019-11-04
*/
//@CacheConfig(cacheNames = "accountingSubjects")
public interface AccountingSubjectsService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AccountingSubjectsQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AccountingSubjectsQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    AccountingSubjectsDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AccountingSubjectsDTO create(AccountingSubjects resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AccountingSubjects resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}