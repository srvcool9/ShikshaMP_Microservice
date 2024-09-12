package com.netlink.rsk.visit_service.mapper.offline;


import java.util.Date;

public interface VisitDomainMapper {

    public Long getDomainid();
    public Long getSubsectionid();
    public String getName();
    public Long getCreatedby();
    public Date getCreatedon();
    public Long getUpdatedby();
    public Date getUpdatedon();
    public long getTotalRows();
}
