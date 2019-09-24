package com.fn.modules.system.service.mapper;

import com.fn.modules.system.domain.Permission;
import com.fn.mapper.EntityMapper;
import com.fn.modules.system.service.dto.PermissionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author leo
 * @date 2019-09-23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

}
