package com.fn.modules.documents.service.mapper;

import com.fn.mapper.EntityMapper;
import com.fn.modules.documents.domain.AccountingSubjects;
import com.fn.modules.documents.service.dto.AccountingSubjectsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-11-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountingSubjectsMapper extends EntityMapper<AccountingSubjectsDTO, AccountingSubjects> {

}