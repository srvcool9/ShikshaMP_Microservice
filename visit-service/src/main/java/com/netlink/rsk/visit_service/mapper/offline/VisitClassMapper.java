package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface VisitClassMapper {

    public Long getClassId();
    public String getQuestion();
    public Long getCreatedBy();
    public Date getCreatedOn();
    public Long getUpdatedBy();
    public Date getUpdatedOn();
    public String getClassName();
    public Boolean getIsActive();
    public Long getTotalRows();
}
