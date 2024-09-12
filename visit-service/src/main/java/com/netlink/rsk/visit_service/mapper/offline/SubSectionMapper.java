package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface SubSectionMapper {

    public Long getVsubsectionid();
    public Long getVsectionid();
    public String getName();
    public String getDescription();
    public Boolean getIsactive();
    public Long getCreatedby();
    public Date getCreatedon();
    public Long getUpdatedby();
    public Date getUpdatedon();
    public Boolean getIsenabledomain();
    public long getTotalRows();
}
