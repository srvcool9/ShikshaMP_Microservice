package com.netlink.rsk.visit_service.mapper;

import java.util.Date;

public interface MyAllocatedVisitsMapper {

    public Long getSchoolAllocationId();
    public Date getAllocatedDate();
    public Long getCreatedBy();
    public Date getCreatedOn();
    public String getStatus();
    public String getTypeName();
    public String getUdiseCode();
    public String getSchoolName();
    public String getClusterCode();
    public String getClusterName();
    public String getBlockCode();
    public String getBlockName();
    public String getDistrictName();
    public String getDistrictCode();
    public Long getSchoolInformationId();
    public Long getSpotAssessmentId();
    public Integer getVisitTimeTaken();

}
