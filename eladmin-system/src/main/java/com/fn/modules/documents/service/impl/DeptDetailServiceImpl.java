package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.DeptDetail;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.DeptDetailRepository;
import com.fn.modules.documents.service.DeptDetailService;
import com.fn.modules.documents.service.dto.DeptDetailDTO;
import com.fn.modules.documents.service.dto.DeptDetailQueryCriteria;
import com.fn.modules.documents.service.mapper.DeptDetailMapper;
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
public class DeptDetailServiceImpl implements DeptDetailService {

    @Autowired
    private DeptDetailRepository deptDetailRepository;

    @Autowired
    private DeptDetailMapper deptDetailMapper;

    @Override
    public Object queryAll(DeptDetailQueryCriteria criteria, Pageable pageable){
        Page<DeptDetail> page = deptDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deptDetailMapper::toDto));
    }

    @Override
    public Object queryAll(DeptDetailQueryCriteria criteria){
        return deptDetailMapper.toDto(deptDetailRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeptDetailDTO findById(Long id) {
        Optional<DeptDetail> deptDetail = deptDetailRepository.findById(id);
        ValidationUtil.isNull(deptDetail,"DeptDetail","id",id);
        return deptDetailMapper.toDto(deptDetail.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeptDetailDTO create(DeptDetail resources) {
        return deptDetailMapper.toDto(deptDetailRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DeptDetail resources) {
        Optional<DeptDetail> optionalDeptDetail = deptDetailRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDeptDetail,"DeptDetail","id",resources.getId());
        DeptDetail deptDetail = optionalDeptDetail.get();
        deptDetail.copy(resources);
        deptDetailRepository.save(deptDetail);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deptDetailRepository.deleteById(id);
    }
}