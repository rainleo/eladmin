package com.fn.modules.documents.service;

import com.fn.modules.documents.domain.ReimbursementDocuments;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsDTO;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author jie
* @date 2019-11-04
*/
//@CacheConfig(cacheNames = "reimbursementDocuments")
public interface ReimbursementDocumentsService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(ReimbursementDocumentsQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(ReimbursementDocumentsQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    ReimbursementDocumentsDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    ReimbursementDocumentsDTO create(ReimbursementDocuments resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(ReimbursementDocuments resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}