package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.domain.ReimbursementDocuments;
import com.fn.modules.documents.repository.DocumentReviewerRepository;
import com.fn.utils.NumGenerator;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.ReimbursementDocumentsRepository;
import com.fn.modules.documents.service.ReimbursementDocumentsService;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsDTO;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsQueryCriteria;
import com.fn.modules.documents.service.mapper.ReimbursementDocumentsMapper;
import com.fn.utils.twitter.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

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
public class ReimbursementDocumentsServiceImpl implements ReimbursementDocumentsService {

    @Autowired
    private ReimbursementDocumentsRepository reimbursementDocumentsRepository;

    @Autowired
    private ReimbursementDocumentsMapper reimbursementDocumentsMapper;

    @Autowired
    private DocumentReviewerRepository documentReviewerRepository;

    @Override
    public Object queryAll(ReimbursementDocumentsQueryCriteria criteria, Pageable pageable) {
        Page<ReimbursementDocuments> page = reimbursementDocumentsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(reimbursementDocumentsMapper::toDto));
    }

    @Override
    public Object queryAll(ReimbursementDocumentsQueryCriteria criteria) {
        return reimbursementDocumentsMapper.toDto(reimbursementDocumentsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public ReimbursementDocumentsDTO findById(Long id) {
        Optional<ReimbursementDocuments> reimbursementDocuments = reimbursementDocumentsRepository.findById(id);
        ValidationUtil.isNull(reimbursementDocuments, "ReimbursementDocuments", "id", id);
        return reimbursementDocumentsMapper.toDto(reimbursementDocuments.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReimbursementDocumentsDTO create(ReimbursementDocuments resources) {
        //生成单据号(时间戳)
        resources.setReimbursementNo(NumGenerator.getNumber());
        //入库申请单据表
        ReimbursementDocuments saveReimbursementDocuments = reimbursementDocumentsRepository.save(resources);
        //入库关联表document_reviewer
        DocumentReviewer documentReviewer = new DocumentReviewer();
        documentReviewer.setDocumentId(resources.getId());
        documentReviewer.setUserId(resources.getUser().getId());
        documentReviewer.setUserName(resources.getUser().getUsername());
        documentReviewer.setSorted(1);//新建从1开始
        documentReviewer.setSource(1);//申请单据
        documentReviewerRepository.save(documentReviewer);
        return reimbursementDocumentsMapper.toDto(saveReimbursementDocuments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ReimbursementDocuments resources) {
        Optional<ReimbursementDocuments> optionalReimbursementDocuments = reimbursementDocumentsRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalReimbursementDocuments, "ReimbursementDocuments", "id", resources.getId());
        ReimbursementDocuments reimbursementDocuments = optionalReimbursementDocuments.get();
        reimbursementDocuments.copy(resources);
        reimbursementDocumentsRepository.save(reimbursementDocuments);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        reimbursementDocumentsRepository.deleteById(id);
    }
}