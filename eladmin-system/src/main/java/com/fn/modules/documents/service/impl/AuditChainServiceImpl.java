package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.AuditChain;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.AuditChainRepository;
import com.fn.modules.documents.service.AuditChainService;
import com.fn.modules.documents.service.dto.AuditChainDTO;
import com.fn.modules.documents.service.dto.AuditChainQueryCriteria;
import com.fn.modules.documents.service.mapper.AuditChainMapper;
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
* @date 2019-11-11
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuditChainServiceImpl implements AuditChainService {

    @Autowired
    private AuditChainRepository auditChainRepository;

    @Autowired
    private AuditChainMapper auditChainMapper;

    @Override
    public Object queryAll(AuditChainQueryCriteria criteria, Pageable pageable){
        Page<AuditChain> page = auditChainRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(auditChainMapper::toDto));
    }

    @Override
    public Object queryAll(AuditChainQueryCriteria criteria){
        return auditChainMapper.toDto(auditChainRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AuditChainDTO findById(Long id) {
        Optional<AuditChain> auditChain = auditChainRepository.findById(id);
        ValidationUtil.isNull(auditChain,"AuditChain","id",id);
        return auditChainMapper.toDto(auditChain.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AuditChainDTO create(AuditChain resources) {
        return auditChainMapper.toDto(auditChainRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AuditChain resources) {
        Optional<AuditChain> optionalAuditChain = auditChainRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAuditChain,"AuditChain","id",resources.getId());
        AuditChain auditChain = optionalAuditChain.get();
        auditChain.copy(resources);
        auditChainRepository.save(auditChain);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        auditChainRepository.deleteById(id);
    }
}