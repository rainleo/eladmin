package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.repository.DocumentReviewerRepository;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.ApplicationDocumentsRepository;
import com.fn.modules.documents.service.ApplicationDocumentsService;
import com.fn.modules.documents.service.dto.ApplicationDocumentsDTO;
import com.fn.modules.documents.service.dto.ApplicationDocumentsQueryCriteria;
import com.fn.modules.documents.service.mapper.ApplicationDocumentsMapper;
import com.fn.utils.twitter.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.fn.utils.PageUtil;
import com.fn.utils.QueryHelp;

/**
 * @author jie
 * @date 2019-11-04
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ApplicationDocumentsServiceImpl implements ApplicationDocumentsService {

    @Autowired
    private ApplicationDocumentsRepository applicationDocumentsRepository;

    @Autowired
    private ApplicationDocumentsMapper applicationDocumentsMapper;

    @Autowired
    private DocumentReviewerRepository documentReviewerRepository;

    @Override
    public Object queryAll(ApplicationDocumentsQueryCriteria criteria, Pageable pageable) {
        Page<ApplicationDocuments> page = applicationDocumentsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(applicationDocumentsMapper::toDto));
    }

    @Override
    public Object queryAll(ApplicationDocumentsQueryCriteria criteria) {
        return applicationDocumentsMapper.toDto(applicationDocumentsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public ApplicationDocumentsDTO findById(Long id) {
        Optional<ApplicationDocuments> applicationDocuments = applicationDocumentsRepository.findById(id);
        ValidationUtil.isNull(applicationDocuments, "ApplicationDocuments", "id", id);
        return applicationDocumentsMapper.toDto(applicationDocuments.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApplicationDocumentsDTO create(ApplicationDocuments resources) {

        //入库申请单据表
        return applicationDocumentsMapper.toDto(applicationDocumentsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ApplicationDocuments resources) {
        Optional<ApplicationDocuments> optionalApplicationDocuments = applicationDocumentsRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalApplicationDocuments, "ApplicationDocuments", "id", resources.getId());
        ApplicationDocuments applicationDocuments = optionalApplicationDocuments.get();
        applicationDocuments.copy(resources);
        applicationDocumentsRepository.save(applicationDocuments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        applicationDocumentsRepository.deleteById(id);
    }
}