package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.domain.ReimbursementDetail;
import com.fn.modules.documents.domain.ReimbursementDocuments;
import com.fn.modules.documents.repository.DocumentReviewerRepository;
import com.fn.modules.documents.repository.ReimbursementDetailRepository;
import com.fn.utils.NumGenerator;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.ReimbursementDocumentsRepository;
import com.fn.modules.documents.service.ReimbursementDocumentsService;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsDTO;
import com.fn.modules.documents.service.dto.ReimbursementDocumentsQueryCriteria;
import com.fn.modules.documents.service.mapper.ReimbursementDocumentsMapper;
import com.fn.utils.twitter.SnowflakeIdUtils;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private ReimbursementDetailRepository reimbursementDetailRepository;

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
        // 附件表先删后增
        updateReimbursementDetail(resources);
        //入库申请单据表
        return reimbursementDocumentsMapper.toDto(reimbursementDocumentsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ReimbursementDocuments resources) {
        Optional<ReimbursementDocuments> optionalReimbursementDocuments = reimbursementDocumentsRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalReimbursementDocuments, "ReimbursementDocuments", "id", resources.getId());
        ReimbursementDocuments reimbursementDocuments = optionalReimbursementDocuments.get();
        reimbursementDocuments.copy(resources);
        reimbursementDocumentsRepository.save(reimbursementDocuments);
        updateReimbursementDetail(resources);
    }

    /**
     * 更新附件表
     * @param resources
     */
    private void updateReimbursementDetail(ReimbursementDocuments resources) {
        // 1.根据附件url删除附件表，然后新增
        List<ReimbursementDetail> reimbursementDetailList = resources.getReimbursementDetailList();
        if (reimbursementDetailList == null || reimbursementDetailList.isEmpty()) {
            return;
        }
        // 删除附件
        reimbursementDetailRepository.deleteInBatch(reimbursementDetailList);
        // 新增附件
        reimbursementDetailList.stream().filter(reimbursementDetail -> {
            reimbursementDetail.setDeleted(0);
            reimbursementDetail.setReimbursementDocumentsId(resources.getId());
            return true;
        }).collect(Collectors.toList());
        reimbursementDetailRepository.saveAll(reimbursementDetailList);
        // 2.远程删除七牛云文件 TODO

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        reimbursementDocumentsRepository.deleteById(id);
    }
}