package com.netlink.rsk.visit_service.mapper;

public interface SectionDomainTransactionMapper {

    public Long getTransactionId();
    public Long getVSectionId();
    public String getSectionName();
    public Long getDomainId();
    public String getDomainName();
    public Boolean getStatus();
//    public Boolean isActive();
}
