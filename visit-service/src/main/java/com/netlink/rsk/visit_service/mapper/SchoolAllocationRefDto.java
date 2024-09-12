package com.netlink.rsk.visit_service.mapper;

import java.util.Date;


public interface SchoolAllocationRefDto {

    public Long getSchoolAllocationId();
    public int getMonth();
    public int getYear();
    public String getUdisecode();
    public String getEmployeeCode();
    public Date getAllocateddate();
    public Long getAllocatedId();
    public String getStatus();
    public String getDatestatus();
    public Long getCreatedby();
    public Date getCreatedOn();
    public Long getUpdatedby();
    public Date getUpdatedon();
    public int getRoleid();
    public int getTypeid();
    public String getTypename();
    public Boolean getIsdeleted();
}
