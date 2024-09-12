package com.netlink.rsk.visit_service.mapper.offline;

import java.util.Date;

public interface ParameterValueMapper {

    public Long getVparametervalueid();
    public Long getVparameterid();
    public Integer getOrderno();
    public String getDescription();
    public String getActionprompts();
    public Long getCreatedby();
    public Date getCreatedon();
    public Long getUpdatedby();
    public Date getUpdatedon();
    public long getTotalRows();
    public Float getWeightage();
    public Boolean getGr1();
}
