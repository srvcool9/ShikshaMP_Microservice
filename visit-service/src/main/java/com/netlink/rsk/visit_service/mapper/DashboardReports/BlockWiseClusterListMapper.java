package com.netlink.rsk.visit_service.mapper.DashboardReports;

public interface BlockWiseClusterListMapper {

    public String getDistrictId();
    public String getDistrictName();
    public String getBlockId();
    public String getBlockName();
    public String getClusterId();
    public String getClusterName();
    public Long getTotalSchools();
    public Long getTargetVisits();
    public Long getCompletedVisits();
    public Double getPercentage();
}
