package com.netlink.rsk.visit_service.mapper;

public interface MentoringReportMapper {

    public Long getVSectionId();
    public String getName();
    public Long getDomainId();
    public String getDomainName();
    public Integer getDomainOrder();
    public Long getCompetencyId();
    public String getCompetencyName();
    public Integer getCompetencyOrder();
    public Double getWeightage();
    public String getColor();
    public String getFeedback();
    public String getMentorId();
    public String getMentorName();
    public Integer getWeek();

}
