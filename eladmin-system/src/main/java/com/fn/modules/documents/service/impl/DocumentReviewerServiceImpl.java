package com.fn.modules.documents.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.domain.ReimbursementDocuments;
import com.fn.modules.documents.repository.DocumentReviewerRepository;
import com.fn.modules.documents.service.ApplicationDocumentsService;
import com.fn.modules.documents.service.DocumentReviewerService;
import com.fn.modules.documents.service.ReimbursementDocumentsService;
import com.fn.modules.documents.service.dto.*;
import com.fn.modules.documents.service.mapper.DocumentReviewerMapper;
import com.fn.utils.PageUtil;
import com.fn.utils.QueryHelp;
import com.fn.utils.ValidationUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jie
 * @date 2019-11-13
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DocumentReviewerServiceImpl implements DocumentReviewerService {

    @Autowired
    private DocumentReviewerRepository documentReviewerRepository;

    @Autowired
    private DocumentReviewerMapper documentReviewerMapper;

    @Autowired
    private ReimbursementDocumentsService reimbursementDocumentsService;

    @Autowired
    private ApplicationDocumentsService applicationDocumentsService;

    @Override
    public Object queryAll(DocumentReviewerQueryCriteria criteria, Pageable pageable) {
        Page<DocumentReviewer> page = documentReviewerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(documentReviewerMapper::toDto));
    }

    @Override
    public Object queryAll(DocumentReviewerQueryCriteria criteria) {
        return documentReviewerMapper.toDto(documentReviewerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public DocumentReviewerDTO findById(Long id) {
        Optional<DocumentReviewer> documentReviewer = documentReviewerRepository.findById(id);
        ValidationUtil.isNull(documentReviewer, "DocumentReviewer", "id", id);
        return documentReviewerMapper.toDto(documentReviewer.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DocumentReviewerDTO create(DocumentReviewer resources) {
        return documentReviewerMapper.toDto(documentReviewerRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DocumentReviewer resources) {
        Optional<DocumentReviewer> optionalDocumentReviewer = documentReviewerRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalDocumentReviewer, "DocumentReviewer", "id", resources.getId());
        DocumentReviewer documentReviewer = optionalDocumentReviewer.get();
        documentReviewer.copy(resources);
        documentReviewerRepository.save(documentReviewer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        documentReviewerRepository.deleteById(id);
    }

    @Override
    public List<Map> queryAllDocuments(DocumentReviewerQueryCriteria criteria) {
        List<Map> list = new ArrayList<>();
        ApplicationDocumentsQueryCriteria applicationDocumentsQueryCriteria = new ApplicationDocumentsQueryCriteria();
        ReimbursementDocumentsQueryCriteria reimbursementDocumentsQueryCriteria = new ReimbursementDocumentsQueryCriteria();
        if (ObjectUtil.isNotNull(criteria) && ObjectUtil.isNotNull(criteria.getCompanyId())) {
            // 根据公司查询
            applicationDocumentsQueryCriteria.setCompanyId(criteria.getCompanyId());
            reimbursementDocumentsQueryCriteria.setCompanyId(criteria.getCompanyId());
        }
        applicationDocumentsQueryCriteria.setDeleted(0);
        List<ApplicationDocumentsDTO> allApplicationDocumentsList = (List<ApplicationDocumentsDTO>) applicationDocumentsService.queryAll(applicationDocumentsQueryCriteria);
        for (ApplicationDocumentsDTO applicationDocuments : allApplicationDocumentsList) {
            Map map = new HashMap();
            map.put("id", applicationDocuments.getId());
            map.put("source", "申请流程");
            map.put("applicationUser", applicationDocuments.getUser().getUsername());
            list.add(map);
        }

        List<ReimbursementDocumentsDTO> allReimbursementDocumentsRepositoryList = (List<ReimbursementDocumentsDTO>) reimbursementDocumentsService.queryAll(reimbursementDocumentsQueryCriteria);
        for (ReimbursementDocumentsDTO reimbursementDocuments : allReimbursementDocumentsRepositoryList) {
            Map map = new HashMap();
            map.put("id", reimbursementDocuments.getId());
            map.put("source", "报销流程");
            map.put("applicationUser", reimbursementDocuments.getUser().getUsername());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Integer> getDisableSorted(DocumentReviewerQueryCriteria criteria) {
        criteria.setDeleted(0);
        List<DocumentReviewerDTO> list = (List<DocumentReviewerDTO>) queryAll(criteria);
        // 将已存在的审批等级过滤掉，避免重复审批
        return list.stream().map(DocumentReviewerDTO -> DocumentReviewerDTO.getSorted()).collect(Collectors.toList());
    }
}