package com.fn.modules.documents.service.mapper;

import com.fn.mapper.EntityMapper;
import com.fn.modules.documents.domain.DocumentReviewer;
import com.fn.modules.documents.service.dto.DocumentReviewerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-11-13
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentReviewerMapper extends EntityMapper<DocumentReviewerDTO, DocumentReviewer> {

}