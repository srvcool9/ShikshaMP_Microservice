package com.netlink.rsk.visit_service.mapper.DashboardReports;

public interface DistrictWiseMapper {

     String getDistrictId();
     String getDistrictName();
     Long getTargetVisits();
     Long getCompletedVisits();
     Double getPercentage();
}
