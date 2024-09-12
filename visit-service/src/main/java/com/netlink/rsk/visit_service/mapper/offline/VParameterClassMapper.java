package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface VParameterClassMapper {

    public Long getParameterClassId();
    public Long getClassId();
    public Long getVParameterId();
    public Long getCreatedBy();
    public Date getCreatedOn();
    public Long getUpdatedBy();
    public Date getUpdatedOn();
    public long getTotalRows();
}
