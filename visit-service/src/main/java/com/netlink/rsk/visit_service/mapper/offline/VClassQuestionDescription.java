package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface VClassQuestionDescription {

    public Long getDescriptionid();
    public Long getClassid();
    public String getDescription();
    public Long getCreatedBy();
    public Date getCreatedOn();
    public Long getUpdatedBy();
    public Date getUpdatedOn();
    public long getTotalRows();
}
