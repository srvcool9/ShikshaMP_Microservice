package com.netlink.rsk.visit_service.mapper;
import java.util.Date;

public interface XrefMapper {

    public Long getSchoolallocationid();
    public int getMonth();
    public int getYear();
    public String getUdisecode();
    public String getEmployeecode();
    public Date getAllocateddate();
    public int getAllocatedid();
    public String getStatus();
    public Long getCreatedby();
    public Date getCreatedon();
    public Long getUpdatedby();
    public Date getUpdatedon();
    public int getRoleid();
    public int getTypeid();
    public Boolean getIsdeleted();
    public Date getStarttime();
    public Date getEndtime();

}
