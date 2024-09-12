package com.netlink.rsk.visit_service.mapper.DashboardReports;

public interface BlockWiseMapper {

    public String getDistrictId();
    public String getDistrictName();
    public String getBlockId();
    public String getBlockName();
    public Long getTargetVisits();
    public Long getCompletedVisits();
    public Double getPercentage();
}
