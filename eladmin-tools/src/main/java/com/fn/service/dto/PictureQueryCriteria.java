package com.fn.service.dto;

import com.fn.annotation.Query;
import lombok.Data;
import com.fn.annotation.Query;

/**
 * sm.ms图床
 *
 * @author leo
 * @date 2019-6-4 09:52:09
 */
@Data
public class PictureQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String filename;
    
    @Query(type = Query.Type.INNER_LIKE)
    private String username;
}
