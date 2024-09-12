package com.netlink.rsk.visit_service.mapper.baseline;

public interface BaselineQuestionMapper {

    public Long getQuestionMasterId();
    public Long getClassId();
    public String getQuestion();
    public Integer getGridColumn();

    public Long getSubjectId();
    public Double getQno();
    public String getImagePath();
    public String getInstruction();
    public String getDescription();
    public String getQuestionType();

}
