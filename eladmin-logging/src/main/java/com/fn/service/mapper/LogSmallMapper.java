package com.fn.service.mapper;

import com.fn.mapper.EntityMapper;
import com.fn.domain.Log;
import com.fn.mapper.EntityMapper;
import com.fn.service.dto.LogSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author leo
 * @date 2019-5-22
 */
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogSmallMapper extends EntityMapper<LogSmallDTO, Log> {

}