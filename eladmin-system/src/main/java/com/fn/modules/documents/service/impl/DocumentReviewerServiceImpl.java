package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.DocumentReviewerRepository;
import com.fn.modules.documents.service.DocumentReviewerService;
import com.fn.modules.documents.service.dto.DocumentReviewerDTO;
import com.fn.modules.documents.service.dto.DocumentReviewerQueryCriteria;
import com.fn.modules.documents.service.mapper.DocumentReviewerMapper;
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
* @date 2019-11-13
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DocumentReviewerServiceImpl implements DocumentReviewerService {

    @Autowired
    private DocumentReviewerRepository documentReviewerRepository;

    @Autowired
    private DocumentReviewerMapper documentReviewerMapper;

    @Override
    public Object queryAll(DocumentReviewerQueryCriteria criteria, Pageable pageable){
        Page<DocumentReviewer> page = documentReviewerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(documentReviewerMapper::toDto));
    }

    @Override
    public Object queryAll(DocumentReviewerQueryCriteria criteria){
        return documentReviewerMapper.toDto(documentReviewerRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DocumentReviewerDTO findById(Long id) {
        Optional<DocumentReviewer> documentReviewer = documentReviewerRepository.findById(id);
        ValidationUtil.isNull(documentReviewer,"DocumentReviewer","id",id);
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
        ValidationUtil.isNull( optionalDocumentReviewer,"DocumentReviewer","id",resources.getId());
        DocumentReviewer documentReviewer = optionalDocumentReviewer.get();
        documentReviewer.copy(resources);
        documentReviewerRepository.save(documentReviewer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        documentReviewerRepository.deleteById(id);
    }
}