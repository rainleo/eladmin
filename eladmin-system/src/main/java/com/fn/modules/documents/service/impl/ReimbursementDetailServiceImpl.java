package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.ReimbursementDetail;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.ReimbursementDetailRepository;
import com.fn.modules.documents.service.ReimbursementDetailService;
import com.fn.modules.documents.service.dto.ReimbursementDetailDTO;
import com.fn.modules.documents.service.dto.ReimbursementDetailQueryCriteria;
import com.fn.modules.documents.service.mapper.ReimbursementDetailMapper;
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
* @date 2019-11-18
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ReimbursementDetailServiceImpl implements ReimbursementDetailService {

    @Autowired
    private ReimbursementDetailRepository reimbursementDetailRepository;

    @Autowired
    private ReimbursementDetailMapper reimbursementDetailMapper;

    @Override
    public Object queryAll(ReimbursementDetailQueryCriteria criteria, Pageable pageable){
        Page<ReimbursementDetail> page = reimbursementDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(reimbursementDetailMapper::toDto));
    }

    @Override
    public Object queryAll(ReimbursementDetailQueryCriteria criteria){
        return reimbursementDetailMapper.toDto(reimbursementDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public ReimbursementDetailDTO findById(Long id) {
        Optional<ReimbursementDetail> reimbursementDetail = reimbursementDetailRepository.findById(id);
        ValidationUtil.isNull(reimbursementDetail,"ReimbursementDetail","id",id);
        return reimbursementDetailMapper.toDto(reimbursementDetail.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ReimbursementDetailDTO create(ReimbursementDetail resources) {
        return reimbursementDetailMapper.toDto(reimbursementDetailRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ReimbursementDetail resources) {
        Optional<ReimbursementDetail> optionalReimbursementDetail = reimbursementDetailRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalReimbursementDetail,"ReimbursementDetail","id",resources.getId());
        ReimbursementDetail reimbursementDetail = optionalReimbursementDetail.get();
        reimbursementDetail.copy(resources);
        reimbursementDetailRepository.save(reimbursementDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        reimbursementDetailRepository.deleteById(id);
    }
}