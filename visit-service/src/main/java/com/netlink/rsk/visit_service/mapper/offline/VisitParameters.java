package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface VisitParameters {

    public Long getVparameterid();
    public Long getVsubsectionid();
    public String getParametertype();
    public String getPurposeid();
    public Long getTypeid();
    public String getQuaterid();
    public String getParametername();
    public String getGradegroupid();
    public String getVisitorroleid();
    public Boolean getMandatory();
    public Integer getOrdernumber();
    public Long getDomainmasterid();
    public Boolean getIsactive();
    public Long getCreatedby();
    public Date getCreatedon();
    public Long getUpdatedby();
    public Date getUpdatedon();
    public long getTotalRows();
}
