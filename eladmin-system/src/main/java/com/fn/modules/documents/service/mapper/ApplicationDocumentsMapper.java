package com.fn.modules.documents.service.mapper;

import com.fn.mapper.EntityMapper;
import com.fn.modules.documents.domain.ApplicationDocuments;
import com.fn.modules.documents.service.dto.ApplicationDocumentsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-11-04
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ApplicationDocumentsMapper extends EntityMapper<ApplicationDocumentsDTO, ApplicationDocuments> {

}