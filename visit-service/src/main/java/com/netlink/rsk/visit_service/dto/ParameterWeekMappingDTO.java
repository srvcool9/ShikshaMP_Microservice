package com.netlink.rsk.visit_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterWeekMappingDTO {

    private Long parameterWeekId;
    private Long weekRangeId;
    private Long vparameterMaster;
    private Long createdBy;
    private Date createdOn;
    private Long updatedBy;
    private Date updatedOn;
}
