package com.fn.modules.documents.service.impl;

import com.fn.modules.documents.domain.AccountingSubjects;
import com.fn.utils.ValidationUtil;
import com.fn.modules.documents.repository.AccountingSubjectsRepository;
import com.fn.modules.documents.service.AccountingSubjectsService;
import com.fn.modules.documents.service.dto.AccountingSubjectsDTO;
import com.fn.modules.documents.service.dto.AccountingSubjectsQueryCriteria;
import com.fn.modules.documents.service.mapper.AccountingSubjectsMapper;
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
public class AccountingSubjectsServiceImpl implements AccountingSubjectsService {

    @Autowired
    private AccountingSubjectsRepository accountingSubjectsRepository;

    @Autowired
    private AccountingSubjectsMapper accountingSubjectsMapper;

    @Override
    public Object queryAll(AccountingSubjectsQueryCriteria criteria, Pageable pageable){
        Page<AccountingSubjects> page = accountingSubjectsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(accountingSubjectsMapper::toDto));
    }

    @Override
    public Object queryAll(AccountingSubjectsQueryCriteria criteria){
        return accountingSubjectsMapper.toDto(accountingSubjectsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AccountingSubjectsDTO findById(Long id) {
        Optional<AccountingSubjects> accountingSubjects = accountingSubjectsRepository.findById(id);
        ValidationUtil.isNull(accountingSubjects,"AccountingSubjects","id",id);
        return accountingSubjectsMapper.toDto(accountingSubjects.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountingSubjectsDTO create(AccountingSubjects resources) {
        return accountingSubjectsMapper.toDto(accountingSubjectsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AccountingSubjects resources) {
        Optional<AccountingSubjects> optionalAccountingSubjects = accountingSubjectsRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAccountingSubjects,"AccountingSubjects","id",resources.getId());
        AccountingSubjects accountingSubjects = optionalAccountingSubjects.get();
        accountingSubjects.copy(resources);
        accountingSubjectsRepository.save(accountingSubjects);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        accountingSubjectsRepository.deleteById(id);
    }
}