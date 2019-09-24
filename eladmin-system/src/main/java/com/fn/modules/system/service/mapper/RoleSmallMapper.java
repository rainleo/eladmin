package com.fn.modules.system.service.mapper;

import com.fn.mapper.EntityMapper;
import com.fn.modules.system.domain.Role;
import com.fn.modules.system.service.dto.RoleDTO;
import com.fn.modules.system.service.dto.RoleSmallDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author leo
 * @date 2019-5-23
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleSmallMapper extends EntityMapper<RoleSmallDTO, Role> {

}
