package com.fn.modules.documents.service;

import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.service.dto.DocumentReviewerDTO;
import com.fn.modules.documents.service.dto.DocumentReviewerQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author jie
* @date 2019-11-04
*/
//@CacheConfig(cacheNames = "documentReviewer")
public interface DocumentReviewerService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DocumentReviewerQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(DocumentReviewerQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    DocumentReviewerDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DocumentReviewerDTO create(DocumentReviewer resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(DocumentReviewer resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}