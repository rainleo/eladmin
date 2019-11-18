package com.fn.modules.documents.service.mapper;

import com.fn.mapper.EntityMapper;
import com.fn.modules.documents.domain.ReimbursementDetail;
import com.fn.modules.documents.service.dto.ReimbursementDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author jie
* @date 2019-11-18
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReimbursementDetailMapper extends EntityMapper<ReimbursementDetailDTO, ReimbursementDetail> {

}