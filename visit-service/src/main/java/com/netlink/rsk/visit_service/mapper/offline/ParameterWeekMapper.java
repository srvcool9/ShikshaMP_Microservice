package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface ParameterWeekMapper {

    public Long getParameterWeekId();
    public Long getWeekRangeId();
    public Long getVParameterId();
    public Long getCreatedBy();
    public Date getCreatedOn();
    public Long getUpdatedBy();
    public Date getUpdatedOn();
    public long getTotalRows();

}
