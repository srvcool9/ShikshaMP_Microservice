package com.netlink.rsk.visit_service.mapper.spotass;

import java.util.Date;

public interface SpotDomainMapper {

    public Long getDomainId();
    public String getDomainName();
    public Long getClassId();
    public Long getSubjectId();
    public String getSubjectName();
    public Boolean getIsActive();
    public Date getCreatedOn();
    public long getTotalRows();
}
