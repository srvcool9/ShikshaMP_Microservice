package com.netlink.rsk.visit_service.queries;

public class VisitQueries {

    public static final String GET_MENTORING_DOMAIN_BY_SECTION = "select *\n" +
            "from Visit.MentoringDomainMaster \n" +
            "where IsActive =1 AND VSectionId=:SectionId";

    public static final String UPDATE_SECTION_TRANSACTION_STATUS =
            "update Visit.SectionDomainTransaction \n" +
            "set IsActive=:IsActive  WHERE TransactionId=:TransactionId";

    public static final String GET_ALL_SECTION_DOMAIN_TRANSACTION =
           "select sdt.TransactionId,vm.VsectionId,vm.Name as SectionName ,mdm.DomainId,\n" +
                   "mdm.DomainName,sdt.IsActive as Status  FROM Visit.VsectionMaster vm \n" +
                   "inner join Visit.SectionDomainTransaction sdt \n" +
                   "on vm.VsectionId = sdt.SectionId \n" +
                   "inner join Visit.MentoringDomainMaster mdm \n" +
                   "on mdm.DomainId = sdt.DomainId ;\n";


    public static final String GET_ALL_CLASS_MASTER_TRANS="select ct.*,(select count(*) from Visit.ClassMasterTransaction) AS TotalRows \n" +
            "from Visit.ClassMasterTransaction ct\n" +
            "where cast(createdon as date) >= :Date";
    public static final String GET_ALL_PARAMETER_WEEK_MAPPING_DATA="select pw.*,(select count(*) from Visit.VParameterWeekMapping) AS TotalRows \n" +
            "from Visit.VparameterWeekMapping pw";
    public static final String GET_ALL_PARAMETER_WEEK_MAPPING_DATA_BY_DATE="select pw.*,(select count(*) from Visit.VParameterWeekMapping) AS TotalRows\n" +
            "from Visit.VparameterWeekMapping pw\n" +
            "where cast(createdon as date) >= :LastUpdatedDate";
    public static final String GET_ALL_SCHOOLINFORMATIONID_BY_SCHOOLALLOCATIONID="SELECT schoolinformationid FROM Visit.SchoolInformation where schoolallocationid = :schoolallocationid";
    public static final String GET_ALL_STATE_REPORT="Select distinct  \n" +
            "              Round \n" +
            "              (ISNULL(cast(count(si.schoolinformationid) as float) / NULLIF(cast(count(xref.schoolallocationid) as float),0),0)*100.0,0) as Total   \n" +
            "                          from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "              left join (    \n" +
            "                                select *    \n" +
            "                                from Visit.SchoolInformation where answer=3 and classid in :ClassIds  \n" +
            "                          ) si on si.schoolallocationid=xref.schoolallocationid  \n" +
            "                          where xref.status=N'पूर्ण' and xref.month =:Month and xref.year=:Year";
    public static final String GET_ALL_District_REPORT="select final.DistrictId,final.DistrictName,final.Result\n" +
            "from(\n" +
            "select f.DistrictId,f.DistrictName,Round(ISNULL(cast(f.Result as float) / NULLIF(cast(f.CompletedVisits as float),0),0)*100.0,0) as Result \n" +
            "              from \n" +
            "              (select dst.DistrictId,dst.DistrictName,cast (count(xref.schoolallocationid) as FLOAT) as CompletedVisits,CAST(COUNT(si.schoolinformationid) AS FLOAT) as Result   \n" +
            "                                                    from UDISE.DistrictMaster dst   \n" +
            "                                                    inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId   \n" +
            "                                                    inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId   \n" +
            "                                                    inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId   \n" +
            "                                                    left join     \n" +
            "                                                    (    \n" +
            "                                                      Select  *   \n" +
            "                                                     from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' and month =:Month and year=:Year      \n" +
            "                                           ) xref on xref.udisecode = sm.UDISECode   \n" +
            "                                       left join (   \n" +
            "                                       select *   \n" +
            "                                       from Visit.SchoolInformation where answer=3  and classid in :ClassIds \n" +
            "                                       ) si on si.schoolallocationid=xref.schoolallocationid   \n" +
            "                                       Group By dst.DistrictId,dst.DistrictName \n" +
            "               )f\n" +
            "\t\t\t   )final order by final.Result desc";
    public static final String GET_ALL_SCHOOL_TIME_TABLE_REPORT_FOR_BLOCK="select \n" +
            "\t Round( ISNULL(cast(count(si.schoolinformationid) as FLOAT) / NULLIF(cast(count(xref.schoolallocationid) as FLOAT),0),0)*100.0,0) as Percentage\n" +
            "                            from UDISE.DistrictMaster dst \n" +
            "                            inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId \n" +
            "                            inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId \n" +
            "                            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "                            left join   \n" +
            "                            (  \n" +
            "                              Select  * \n" +
            "                             from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' \n" +
            "\t\t\t\t\t\t\t and month =:Month and year=:Year\n" +
            "                   ) xref on xref.udisecode = sm.UDISECode \n" +
            "               left join ( \n" +
            "               select *  \n" +
            "               from Visit.SchoolInformation where answer=3 and classid in :ClassIds \n" +
            "\t\t\t \n" +
            "               ) si on si.schoolallocationid=xref.schoolallocationid\n" +
            "\t\t\t   where bm.BlockId=:BlockId\n" +
            "               Group By dst.DistrictId,dst.DistrictName,bm.BlockName";
    public static final String GET_ALL_CLUSTER_REPORT_BY_BLOCK=" select *\n" +
            "\t\t\t   from(\n" +
            "\t\t\t   select dst.DistrictId,dst.DistrictName,bm.BlockName,cm.ClusterName,CAST(count(xref.schoolallocationid) AS float) as CompletedVisits,cast(count(si.schoolinformationid) as FLOAT) as Result,\n" +
            "                        Round( ISNULL(cast(count(si.schoolinformationid) as FLOAT) / NULLIF(cast(count(xref.schoolallocationid) as FLOAT),0),0)*100.0,0) as Percentage\n" +
            "\t\t\t\t\t\tfrom UDISE.DistrictMaster dst \n" +
            "                            inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId \n" +
            "                            inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId \n" +
            "                            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "                            left join   \n" +
            "                            (  \n" +
            "                              Select  * \n" +
            "                             from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' \n" +
            "\t\t\t\t\t\t\t  and month =:Month and year=:Year \n" +
            "                   ) xref on xref.udisecode = sm.UDISECode \n" +
            "               left join ( \n" +
            "               select *  \n" +
            "               from Visit.SchoolInformation where answer=3 and classid in :ClassIds \n" +
            "               ) si on si.schoolallocationid=xref.schoolallocationid \n" +
            "               where bm.BlockId=:BlockId\n" +
            "               Group By dst.DistrictId,dst.DistrictName,bm.BlockName,cm.ClusterName\n" +
            "\t\t\t   )f order by f.Percentage desc";
    public static final String GET_ALL_QUESTION_NAME_BY_CLASSID="select classid from visit.ClassMaster where question=:QuestionName";
    public static final String GET_ALL_CLASSES="SELECT c.*, (SELECT COUNT(*) FROM Visit.ClassMaster) AS TotalRows \n" +
            "FROM Visit.ClassMaster c;";
    public static final String GET_ALL_CLASS_DESCRIPTION_BY_DATE="select cq.*,(select count(*) from Visit.ClassQuestionDescription) AS TotalRows \n" +
            "from Visit.ClassQuestionDescription cq\n" +
            "where cast(cq.createdon as date) >= :Date";
    public static final String GET_ALL_CLASS_DESCRIPTION="select cq.*,(select count(*) from Visit.ClassQuestionDescription) AS TotalRows \n" +
            "from Visit.ClassQuestionDescription cq";
    public static final String GET_ALL_DISTRICT_WISE_QUESTION_RESP_PER="select f.DistrictId,f.DistrictName,Round(ISNULL(cast(f.Result as float) / NULLIF(cast(f.CompletedVisits as float),0),0)*100.0,0) as Result\n" +
            "\t  from\n" +
            "\t  (select dst.DistrictId,dst.DistrictName,cast (count(xref.schoolallocationid) as FLOAT) as CompletedVisits,CAST(COUNT(si.schoolinformationid) AS FLOAT) as Result  \n" +
            "                                        from UDISE.DistrictMaster dst  \n" +
            "                                        inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId  \n" +
            "                                        inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId  \n" +
            "                                        inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId  \n" +
            "                                        left join    \n" +
            "                                        (   \n" +
            "                                          Select  *  \n" +
            "                                         from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' and month =:Month and year=:Year     \n" +
            "                               ) xref on xref.udisecode = sm.UDISECode  \n" +
            "                           left join (  \n" +
            "                           select *  \n" +
            "                           from Visit.SchoolInformation where answer=3 and classid in :ClassIds\n" +
            "                           ) si on si.schoolallocationid=xref.schoolallocationid  \n" +
            "               where dst.DistrictId=:DistrictId\n" +
            "                           Group By dst.DistrictId,dst.DistrictName\n" +
            "\t\t\t\t\t\t   )f order by f.Result desc ";
    public static final String GET_BLOCK_WISE_QUES_RESP_PER="select f.DistrictId,f.DistrictName,f.BlockName,\n" +
            "\tRound(ISNULL(cast(f.Result as float) / NULLIF(cast(f.CompletedVisits as float),0),0)*100.0,0) as Result\n" +
            "\t  from\n" +
            "   (select dst.DistrictId,dst.DistrictName,bm.BlockName,cast(count(xref.schoolallocationid) as FLOAT) as CompletedVisits, cast(count(si.schoolinformationid) as FLOAT) as Result \n" +
            "                            from UDISE.DistrictMaster dst \n" +
            "                            inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId \n" +
            "                            inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId \n" +
            "                            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "                            left join   \n" +
            "                            (  \n" +
            "                              Select  * \n" +
            "                             from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' and month =:Month and year=:Year \n" +
            "                   ) xref on xref.udisecode = sm.UDISECode \n" +
            "               left join ( \n" +
            "               select *  \n" +
            "               from Visit.SchoolInformation where answer=3 and classid in :ClassIds \n" +
            "               ) si on si.schoolallocationid=xref.schoolallocationid \n" +
            "               where dst.DistrictId=:DistrictId\n" +
            "               Group By dst.DistrictId,dst.DistrictName,bm.BlockName \n" +
            "         \t   )f order by f.Result desc ";
    public static final String GET_ALL_PARAMETERS= "select distinct p.vparameterid as ParameterId,p.parametername as ParameterName\n" +
            "            from Visit.Domainmaster dm\n" +
            "            inner join Visit.VparameterMaster p on dm.domainid=p.domainmasterid\n" +
            "            inner join Visit.VParameterClass cl on cl.vparameterid=p.vparameterid\n" +
            "\t\t\tinner join Visit.ClassMasterTransaction cmt on cmt.ClassId=cl.ClassId\n" +
            "\t\t\tinner join Visit.ClassMaster cm on cm.classid=cmt.ClassMasterId\n" +
            "            where cm.ClassId =:ClassId and dm.domainid=:DomainId and p.isactive=1";
    public static final String GET_ALL_DOMAIN_LIST="select d.*,(select count(*) from Visit.Domainmaster) AS TotalRows \n" +
            "from Visit.Domainmaster d";
    public static final String GET_ALL_DOMAIN_LIST_BY_DATE="select d.*,(select count(*) from Visit.Domainmaster) AS TotalRows\n" +
            "from Visit.Domainmaster d\n" +
            "where cast(createdon as date) >= :LastUpdatedDate";
    public static final String GET_ACTIVE_DOMIANS_BY_SUB_SECTION="select distinct d.[domainid]\n" +
            "      ,d.[subsectionid]\n" +
            "      ,d.[name]\n" +
            "      ,d.[createdby]\n" +
            "      ,d.[createdon]\n" +
            "      ,d.[updatedby]\n" +
            "      ,d.[updatedon]\n" +
            "from Visit.Domainmaster d\n" +
            "inner join Visit.VparameterMaster p on p.domainmasterid=d.domainid\n" +
            "where d.subsectionid=:SubSectionId and p.isactive=1";
    public static final String GET_FIRST_ACTIVITY_BY_DOMAIN_AND_OBSERVATION="select TOP (1)*\n" +
            "from Visit.VisitClassroomObservationActivity act\n" +
            "where act.DomainId=:DomainId and act.ClassRoomObservationId=:ObservationId";
    public static final String GET_CHECK_ACTIVITY_EXISTS="SELECT CASE WHEN EXISTS (select * from \n" +
            "\t\t\t\tVisit.VClassroomObservationResponse r where r.ActivityId=:ActivityId) \n" +
            "            THEN CAST (1 AS BIT) \n" +
            "            ELSE CAST (0 AS BIT) END";
    public static final String GET_STATE_WISE_VISITS="select DISTINCT cast(a.DistrictId as varchar) as DistrictId,a.DistrictName,a.VisitedSchools,b.ClassRoomObservationId,c.ParameterValueId,c.orderno\n" +
            "\n" +
            " from (\n" +
            " select distinct dst.DistrictId,dst.DistrictName,count(xref.schoolallocationid) as VisitedSchools\n" +
            " from UDISE.DistrictMaster dst \n" +
            "inner join UDISE.BlockMaster blc on blc.DistrictId=dst.DistrictId\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join (select * from Visit.VisitUserSchoolSelfAllocationXref where month=:Month and status=N'पूर्ण' and year=:Year) xref on xref.udisecode=sm.UDISECode \n" +
            "Group By dst.DistrictId,dst.DistrictName\n" +
            ") a\n" +
            "left join (\n" +
            "\n" +
            "select distinct xref.schoolallocationid , obs.ClassRoomObservationId,xref.udisecode,obs.Status,dst.DistrictId,pv.vparametervalueid\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "inner join UDISE.SchoolMaster sm on xref.udisecode=sm.UDISECode\n" +
            "inner join UDISE.ClusterMaster cm on cm.ClusterId=sm.ClusterId\n" +
            "inner join UDISE.BlockMaster blc on blc.BlockId=cm.BlockCode\n" +
            "inner join UDISE.DistrictMaster dst on dst.DistrictId=blc.DistrictId\n" +
            "left join Visit.VClassroomObservation obs on obs.SchoolAllocationId=xref.schoolallocationid\n" +
            "left join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "left join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "left join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "where  xref.status=N'पूर्ण' and obs.Status='Completed' and xref.month=:Month and xref.year=:Year\n" +
            "Group By xref.schoolallocationid,obs.ClassRoomObservationId,xref.udisecode,obs.Status,dst.DistrictId,pv.vparametervalueid\n" +
            ") b on a.DistrictId=b.DistrictId\n" +
            "\n" +
            "left join (\n" +
            "\n" +
            "select resp.ParameterValueId,s.Name as SectionName,ss.Name as SubSectionName,pm.parametername,pv.orderno\n" +
            "from Visit.VisitClassroomObservationActivity  act\n" +
            "inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "inner join Visit.VParameterValues pv on resp.ParameterValueId=pv.vparametervalueid\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=pv.vparameterid\n" +
            "inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster s on s.VsectionId=ss.VsectionId\n" +
            "where pm.vparameterid in (:ParameterIds)\n" +
            "--where ss.Name=N'कक्षा में शिक्षण सामग्री की उपलब्धता' and pm.parametername=N'अभ्यास पुस्तिका' and s.VsectionId=@SectionId\n" +
            "\n" +
            ")c on c.ParameterValueId=b.vparametervalueid";
    public static final String GET_DISTRICT_WISE_VISITS=" select DISTINCT cast(a.BlockId as varchar) as BlockId,a.BlockName,a.VisitedSchools,b.ClassRoomObservationId,c.ParameterValueId,c.orderno\n" +
            " from (\n" +
            " select distinct blc.BlockId,BLC.BlockName,count(xref.schoolallocationid) as VisitedSchools\n" +
            "from UDISE.DistrictMaster dst\n"+
            "inner join UDISE.BlockMaster blc on blc.DistrictId=dst.DistrictId\n"+
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join (select * from Visit.VisitUserSchoolSelfAllocationXref where month=:Month and status=N'पूर्ण' and year=:Year) xref on xref.udisecode=sm.UDISECode \n" +
            " where dst.DistrictId=:Id\n"+
            "Group By blc.BlockId,blc.BlockName\n" +
            ") a\n" +
            "left join (\n" +
            "select distinct xref.schoolallocationid , obs.ClassRoomObservationId,xref.udisecode,obs.Status,blc.BlockId,pv.vparametervalueid\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "inner join UDISE.SchoolMaster sm on xref.udisecode=sm.UDISECode\n" +
            "inner join UDISE.ClusterMaster cm on cm.ClusterId=sm.ClusterId\n" +
            "inner join UDISE.BlockMaster blc on blc.BlockId=cm.BlockCode\n" +
            "left join Visit.VClassroomObservation obs on obs.SchoolAllocationId=xref.schoolallocationid\n" +
            "left join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "left join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "left join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "where  xref.status=N'पूर्ण' and obs.Status='Completed' and xref.month=:Month and xref.year=:Year\n" +
            "Group By xref.schoolallocationid,obs.ClassRoomObservationId,xref.udisecode,obs.Status,blc.BlockId,pv.vparametervalueid\n" +
            ") b on a.BlockId=b.BlockId\n" +
            "left join (\n" +
            "select resp.ParameterValueId,s.Name as SectionName,ss.Name as SubSectionName,pm.parametername,pv.orderno\n" +
            "from Visit.VisitClassroomObservationActivity  act\n" +
            "inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "inner join Visit.VParameterValues pv on resp.ParameterValueId=pv.vparametervalueid\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=pv.vparameterid\n" +
            "inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster s on s.VsectionId=ss.VsectionId\n" +
            "where pm.vparameterid in (:ParameterIds)\n" +
            "\n" +
            ")c on c.ParameterValueId=b.vparametervalueid";


    public static final String GET_CLUSTER_WISE_VISITS=" select DISTINCT cast(a.ClusterId as varchar) as ClusterId,a.ClusterName,a.VisitedSchools,b.ClassRoomObservationId,c.ParameterValueId,c.orderno\n" +
            " from (\n" +
            " select distinct cm.ClusterId,cm.ClusterName,count(xref.schoolallocationid) as VisitedSchools\n" +
            "from UDISE.BlockMaster bm\n"+
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId\n"+
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join (select * from Visit.VisitUserSchoolSelfAllocationXref where month=:Month and status=N'पूर्ण' and year=:Year) xref on xref.udisecode=sm.UDISECode \n" +
            "where bm.BlockId=:Id\n"+
            "Group By cm.ClusterId,cm.ClusterName\n" +
            ") a\n" +
            "left join (\n" +
            "select distinct xref.schoolallocationid , obs.ClassRoomObservationId,xref.udisecode,obs.Status,cm.ClusterId,pv.vparametervalueid\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "inner join UDISE.SchoolMaster sm on xref.udisecode=sm.UDISECode\n" +
            "inner join UDISE.ClusterMaster cm on cm.ClusterId=sm.ClusterId\n" +
            "left join Visit.VClassroomObservation obs on obs.SchoolAllocationId=xref.schoolallocationid\n" +
            "left join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "left join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "left join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "where  xref.status=N'पूर्ण' and obs.Status='Completed' and xref.month=:Month and xref.year=:Year\n" +
            "Group By xref.schoolallocationid,obs.ClassRoomObservationId,xref.udisecode,obs.Status,cm.ClusterId,pv.vparametervalueid\n" +
            ") b on a.ClusterId=b.ClusterId\n" +
            "left join (\n" +
            "select resp.ParameterValueId,s.Name as SectionName,ss.Name as SubSectionName,pm.parametername,pv.orderno\n" +
            "from Visit.VisitClassroomObservationActivity  act\n" +
            "inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "inner join Visit.VParameterValues pv on resp.ParameterValueId=pv.vparametervalueid\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=pv.vparameterid\n" +
            "inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster s on s.VsectionId=ss.VsectionId\n" +
            "where pm.vparameterid in (:ParameterIds)\n" +
            "\n" +
            ")c on c.ParameterValueId=b.vparametervalueid";
    public static final String UPDATE_STATUS_OF_SCHOOLALLOCATIONID="update VisitScheduledUserLog v set v.status = ?1 where v.schoolallocationid = ?2";
    public static final String FIND_BY_EMPLOYEECODE_And_ROLEID_AND_MONTH_AND_ISDELETED_ORDERBY_SCHOOLALLOCATIONIDDESC="SELECT schoolallocationid\n" +
            ",month\n" +
            ",year\n" +
            ",udisecode\n" +
            ",employeecode\n" +
            ",allocateddate\n" +
            ",allocatedid\n" +
            ",status \n" +
            " ,createdby\n" +
            ",createdon\n" +
            ",updatedby\n" +
            ",updatedon\n" +
            ",roleid\n" +
            ",typeid\n" +
            ",typename\n" +
            ",isdeleted\n" +
            " FROM Visit.VisitUserSchoolSelfAllocationXref where employeecode=?1 And roleid=?2 And month=?3 And year=?4 And isdeleted=?5 order  by schoolallocationid desc  ";
    public static final String GET_START_VISIT_LIST="SELECT schoolallocationid \n" +
            "\t\t\t,month \n" +
            "\t\t\t,year \n" +
            "\t\t\t,udisecode \n" +
            "\t\t\t,employeecode \n" +
            "\t\t\t,allocateddate \n" +
            "\t\t\t,allocatedid \n" +
            "\t\t\t,status  \n" +
            "\t\t\t,starttime \n" +
            "\t\t\t,endtime  \n" +
            "\t\t\t ,createdby \n" +
            "\t\t\t,createdon \n" +
            "\t\t\t,updatedby \n" +
            "\t\t\t,updatedon \n" +
            "\t\t\t,roleid \n" +
            "\t\t\t,typeid \n" +
            "\t\t\t,isdeleted \n" +
            "\t\t\t FROM Visit.VisitUserSchoolSelfAllocationXref where employeecode=:EmployeeCode And roleid=:RoleId And month=:Month And year=:Year And isdeleted=:IsDeleted order by schoolallocationid desc";
    public static final String GET_DATE_STATUS="\n" +
            "  select CASE WHEN CAST(:DateToCheck as date)< CAST(GETDATE() AS date) then 'Expired' else 'Not Expired' end as datestatus";
    public static final String FIND_BYEMPLOYEECODE_ALLOCATED_TODAY_ORDERBY_ALLOCATEDDATEDESC="select * from Visit.VisitUserSchoolSelfAllocationXref\n" +
            "  where employeecode = :employeecode and roleid = :roleid and \n" +
            "  cast(allocateddate as Date) = cast(getdate() as date) and isdeleted = 'false' order by schoolallocationid desc";
    public static final String UPDATE_STATUS_BY_SCHOOLALLOCATIONID= "UPDATE VisitUserSchoolSelfAllocationXref v SET v.status = :status, v.updatedon = CURRENT_TIMESTAMP, v.endtime = CURRENT_TIMESTAMP WHERE v.schoolallocationid = :allocationId";
    public static final String GET_AVERAGE_TIME="select (convert(varchar(5),avg(DateDiff(s, si.startime, vi.updatedon)/3600))+':'+convert(varchar(5),avg(DateDiff(s, si.startime, vi.updatedon)%3600/60))+':'+convert(varchar(5),avg(DateDiff(s,si.startime, vi.updatedon)%60)))" +
            "  from Visit.SchoolInformation si \n" +
            "  inner join Visit.VisitUserSchoolSelfAllocationXref vi on si.schoolallocationid = vi.schoolallocationid where vi.employeecode = :employeecode and vi.roleid = :roleId and vi.month=:month and vi.year = :year";
    public static final String GET_CURRENT_MONTH="select(MONTH( getDate()) )as Month";
    public static final String GET_CURRENT_YEAR=" select(YEAR(getDate())) as year";
    public static final String GET_CURRENT_TIME="SELECT CURRENT_TIMESTAMP";
    public static final String GET_CURRENT_DATE="SELECT CAST(GETDATE() AS DATE)";
    public static final String GET_VISIT_YEAR="select distinct year from Visit.VisitUserSchoolSelfAllocationXref ";
    public static final String GET_UNIQUE_SCHOOL_VISITS_FOR_BLOCK="select  Round( ISNULL(CAST(count(xref.schoolallocationid) AS float) / NULLIF(CAST(count(sm.UDISECode) AS FLOAT),0),0)*100.0,0) as Percentage\n" +
            "\t\t\t\t\t\t\tfrom UDISE.BlockMaster bm  \n" +
            "                            inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId \n" +
            "                            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "                            left join   \n" +
            "                            (  \n" +
            "                              Select  * \n" +
            "                             from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' \n" +
            "\t\t\t\t\t\t\t and month =:Month and year=:Year \n" +
            "                   ) xref on xref.udisecode = sm.UDISECode \n" +
            "               where bm.BlockId=:BlockId \n" +
            "               Group By bm.BlockName";
    public static final String GET_CLUSTER_UNIQUE_SCHOOL_VISIT_BY_BLOCK="select *\n" +
            "from(\n" +
            "select cm.ClusterName,CAST(count( sm.UDISECode) AS FLOAT)as TotalSchools,CAST(count(xref.schoolallocationid) AS float) as CompletedVisits,\n" +
            "Round( ISNULL(CAST(count(xref.schoolallocationid) AS float) / NULLIF(CAST(count(sm.UDISECode) AS FLOAT),0),0)*100.0,0) as Percentage\n" +
            "                            from UDISE.ClusterMaster cm  \n" +
            "                            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "                            left join   \n" +
            "                            (  \n" +
            "                              Select distinct schoolallocationid,udisecode \n" +
            "                             from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' \n" +
            "\t\t\t\t\t\t\tand month =:Month and year:Year \n" +
            "                   ) xref on xref.udisecode = sm.UDISECode \n" +
            "               where cm.BlockCode=:BlockId\n" +
            "               Group By cm.ClusterName ) f\n" +
            "               Order By f.Percentage desc";
    public static final String GET_VISIT_HOURS="select DATEDIFF(Hour, starttime, endtime) \n" +
            "from Visit.VisitUserSchoolSelfAllocationXref\n" +
            "Where schoolallocationid=:SchoolAllocationId";
    public static final String GET_UPDATE_VISIT_TIME="Update Visit.VisitUserSchoolSelfAllocationXref \n" +
            "SET starttime=CURRENT_TIMESTAMP\n" +
            "where schoolallocationid=:SchoolAllocId\n";
    public static final String UPDATE_STATUS_AND_START_TIME_AND_ENDTIME_BY_SCHOOLALLOCATIONID="update VisitUserSchoolSelfAllocationXref v set v.status = ?1, v.starttime = ?2, v.endtime = ?3 " +
            "where v.schoolallocationid = ?4";
    public static final String GET_TOTAL_VISITS= "select count(x.schoolallocationid) as Visits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "where x.status=N'पूर्ण' and x.month=:Month and x.year=:Year";
    public static final String GET_VISIT_PERCENT_TAKING_MORE_TIME="select Round(cast(count(d.schoolallocationid) as float)/cast(e.TotalVisits as float)*100,0) as Percentage\n" +
            "\tfrom\n" +
            "    (select Year(GETDATE()) as Year\n" +
            "\t)a\n" +
            "\tleft join (\n" +
            "\t  select a.schoolallocationid,a.Difference,Year(GETDATE()) as Year\n" +
            "\t  from\n" +
            "      (\n" +
            "      select x.schoolallocationid,DATEDIFF(HOUR,x.starttime,x.endtime) as Difference\n" +
            "      from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "      where x.status=N'पूर्ण' AND x.month=:Month and x.year=:Year" +
            "\n" +
            "      )a where a.Difference>1.5\n" +
            "\t) d on d.Year=a.Year\n" +
            "\tleft join (\n" +
            "\t      select Year(GETDATE()) as Year,count (x.schoolallocationid) as TotalVisits\n" +
            "          from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "          where x.status=N'पूर्ण' AND x.month=:Month and x.year=:Year\n" +
            "\t)e on e.Year=a.Year\n" +
            "\tGroup By e.TotalVisits";
    public static final String GET_VISIT_AVERAGE_TIME="\n" +
            "      select Round(cast(sum(a.Difference) as float)/cast(count(a.schoolallocationid)as float),1) as Average from\n" +
            "   (\n" +
            "   select x.schoolallocationid,DATEDIFF(HOUR,x.starttime,x.endtime) as Difference\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "    where x.status=N'पूर्ण' AND x.month=:Month and x.year=:Year\n" +
            "   )a";
    public static final String GET_UNIQUE_SCHOOLS_COUNT="select count(distinct x.udisecode) as UniqueSchools\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref X\n" +
            "   where x.status=N'पूर्ण' AND x.month=:Month and x.year=:Year";
    public static final String  GET_UNIQUE_SCHOOL_STATE_LEVELREPORT="select distinct dst.DistrictId,dst.DistrictName,Round(cast(a.UniqueSchools as float)/cast(a.schools as float)*100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dst\n" +
            "left join (\n" +
            "   select sm.DistrictId,count( distinct xref.udisecode) as UniqueSchools,count( xref.udisecode) as schools\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join UDISE.SchoolMaster sm on sm.UDISECode=xref.udisecode\n" +
            "   WHERE xref.status=N'पूर्ण' and xref.year=:Year and xref.month=:Month\n" +
            "   Group By sm.DistrictId\n" +
            ") a on a.DistrictId=dst.DistrictId\n" +
            "Order By dst.DistrictName";
    public static final String GET_UNIQUE_SCHOOL_DISTRICT_LEVEL_REPORT="select distinct dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName,Round(cast(a.UniqueSchools as float)/cast(a.schools as float)*100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dst\n" +
            "inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId\n" +
            "left join (\n" +
            "   select sm.BlockId,count( distinct xref.udisecode) as UniqueSchools,count( xref.udisecode) as schools\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join UDISE.SchoolMaster sm on sm.UDISECode=xref.udisecode\n" +
            "   WHERE xref.status=N'पूर्ण' and xref.year=:Year and xref.month=:Month\n" +
            "   Group By sm.BlockId\n" +
            ") a on a.BlockId=blc.BlockId\n" +
            "  where dst.DistrictId=:AreaId \n"+
            "Order By dst.DistrictName\n";
    public static final String GET_UNIQUE_SCHOOL_BLOCK_LEVEL_REPORT="select distinct dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName,cm.ClusterId,cm.ClusterName,Round(cast(a.UniqueSchools as float)/cast(a.schools as float)*100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dst\n" +
            "inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "left join (\n" +
            "   select sm.ClusterId,count( distinct xref.udisecode) as UniqueSchools,count( xref.udisecode) as schools\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join UDISE.SchoolMaster sm on sm.UDISECode=xref.udisecode\n" +
            "   WHERE xref.status=N'पूर्ण' and xref.year=:Year and xref.month=:Month\n" +
            "   Group By sm.ClusterId\n" +
            ") a on a.ClusterId=cm.ClusterId\n" +
            " where blc.BlockId=:AreaId\n"+
            "Order By dst.DistrictName";
    public static final String GET_COMPLETED_VISITS_BLOCKWISE="select distinct a.DistrictName,a.BlockName,a.EmployeeCode,a.EmployeeName,a.RoleName,b.visitpermonth as TargetVisits,c.Completed\n" +
            " from( \n" +
            "                  select distinct dst.DistrictName,blc.BlockName,um.EmployeeCode,emp.EmployeeName,r.RoleID,r.RoleName \n" +
            "                        from RoleAssignment rag \n" +
            "                        inner join UserMaster um on um.UserId=rag.UserId \n" +
            "                        inner join Employee.Dbo.EmployeeMaster emp on emp.EmployeeCode=um.EmployeeCode \n" +
            "                        inner join RoleArea ra on ra.RoleAssignmentId=rag.RoleAssignmentId \n" +
            "                        inner join RoleMaster r on r.RoleID=rag.RoleId \n" +
            "                        inner join UDISE.BlockMaster blc on blc.BlockId=ra.BlockId\n" +
            "            inner join UDISE.DistrictMaster dst on dst.DistrictId=blc.DistrictId \n" +
            "                        where  rag.RoleId in :roleIds\n" +
            "                        Group By  dst.DistrictName,BLC.BlockName,um.EmployeeCode,emp.EmployeeName,r.RoleID,r.RoleName \n" +
            "            ) a \n" +
            "\t\t\tleft join (\n" +
            "\t\t\tselect roleId,visitpermonth\n" +
            "\t\t\tfrom Visit.VregularVisitPlan \n" +
            "\t\t     where roleid in :roleIds  and month=:Month and year=:year\n" +
            "\t\t\t)b on b.roleid=a.RoleID\n" +
            "\t\t\tleft join (\n" +
            "\t\t\tselect  employeeCode as EmpCode,Count(1) as Completed,roleid\n" +
            "\t\t\tfrom Visit.VisitUserSchoolSelfAllocationXref \n" +
            "\t         where Month = :Month and status=N'पूर्ण' AND YEAR=:year and roleid in :roleIds\n" +
            "\t\t\t Group By employeecode,roleid\n" +
            "\t\t\t)c on a.EmployeeCode=c.EmpCode";
    public static final String GET_COMPLETED_VISITS_EMPLOYEEWISE="select emp.EmployeeCode,emp.EmployeeName,rm.RoleName,rv.visitpermonth as Target,count (xref.schoolallocationid) as Completed\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "inner join UserMaster um on  um.EmployeeCode=xref.employeecode\n" +
            "inner join RoleAssignment rag on rag.UserId=um.UserId\n" +
            "inner join RoleArea ra on ra.RoleAssignmentId=rag.RoleAssignmentId\n" +
            "inner join Visit.VregularVisitPlan rv on rv.roleid=xref.roleid\n" +
            "inner join Employee.Dbo.EmployeeMaster emp on emp.EmployeeCode=xref.employeecode\n" +
            "inner join Dbo.RoleMaster rm on rm.RoleID=xref.roleid\n" +
            "where ra.BlockId=:BlcId  AND xref.month=:Month AND status=N'पूर्ण' and xref.roleid in :roleIds\n" +
            "Group By emp.EmployeeCode,emp.EmployeeName,rm.RoleName,rv.visitpermonth";

    public static final String GET_SHCOOL_INFO_BLOCKWISE="select dst.DistrictId,dst.DistrictName,bm.BlockName,cast(count(xref.schoolallocationid) as FLOAT) as CompletedVisits, cast(count(si.schoolinformationid) as FLOAT) as Result\n" +
            "                from UDISE.DistrictMaster dst\n" +
            "                inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId\n" +
            "                inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId\n" +
            "                inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "                left join  \n" +
            "                ( \n" +
            "                  Select  *\n" +
            "                 from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' and month=:Month and year=:Year\n" +
            "\t\t       ) xref on xref.udisecode = sm.UDISECode\n" +
            "\t\t\t   left join (\n" +
            "\t\t\t   select * \n" +
            "\t\t\t   from Visit.SchoolInformation where answer=3 and classid in :ClassIds\n" +
            "\t\t\t   ) si on si.schoolallocationid=xref.schoolallocationid\n" +
            "\t\t\t   where dst.DistrictId=:DistrictId\n" +
            "\t\t\t   Group By dst.DistrictId,dst.DistrictName,bm.BlockName\n" +
            "\t\t\t   Order By dst.DistrictName";

    public static final String GET_COMPLETED_VISITS_BLOCK_NAMEWISE="select bm.BlockName,CAST(count(sm.UDISECode) AS FLOAT)as TotalSchools,CAST(count(xref.schoolallocationid) AS float) as CompletedVisits\n" +
            "                from UDISE.BlockMaster bm \n" +
            "                inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId\n" +
            "                inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "                left join  \n" +
            "                ( \n" +
            "                  Select  *\n" +
            "                 from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' and month=:Month and year=:Year\n" +
            "\t\t       ) xref on xref.udisecode = sm.UDISECode\n" +
            "\t\t\t   where bm.DistrictId=:DistrictId and sm.CategoryId in (1,2,3,6)\n" +
            "\t\t\t   Group By bm.BlockName\n" +
            "\t\t\t   Order By bm.BlockName";
    public static final String GET_BLOCK_VISIT_REPORT_FOR_PARAMETER="select final.BlockId,final.BlockName,\n" +
            "case when Round(final.Percentage,0) is null then 0 \n" +
            "     when Round(final.Percentage,0)>100 then 100\n" +
            "else Round(final.Percentage,0) end as Percentage\n" +
            "from(\n" +
            "select distinct dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName\n" +
            ",count(a.SchoolAllocationId) as TotalVisits,\n" +
            "sum(a.Ans) as Correct,\n" +
            "(cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage\n" +
            "from UDISE.DistrictMaster dst \n" +
            "inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join (\n" +
            "   select distinct obs.SchoolAllocationId,xref.udisecode,\n" +
            "   case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans \n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "   inner join  Visit.VClassroomObservation obs \n" +
            "   on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act \n" +
            "   on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp\n" +
            "   on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   where p.vparameterid =:ParameterId and xref.status=N'पूर्ण' and\n" +
            "   xref.month =:Month and xref.year=:Year \n" +
            "   Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId\n" +
            ") a on sm.UDISECode=a.udisecode\n" +
            "Group By dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName\n" +
            ")final\n" +
            "where final.DistrictId=:DistrictId\n" +
            "order by final.DistrictName";

    public static final String GET_VISIT_PERCENTAGE_FOR_BLOCK="select \n" +
            "              case when Round(final.Percentage,0) is null then 0  \n" +
            "              when Round(final.Percentage,0)>100 then 100 \n" +
            "             else Round(final.Percentage,0) end as Percentage \n" +
            "             from( \n" +
            "             select distinct dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName \n" +
            "             ,count(a.SchoolAllocationId) as TotalVisits, \n" +
            "             sum(a.Ans) as Correct, \n" +
            "             (cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage \n" +
            "             from UDISE.DistrictMaster dst  \n" +
            "             inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId \n" +
            "             inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId \n" +
            "             inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "             left join ( \n" +
            "                select distinct obs.SchoolAllocationId,xref.udisecode, \n" +
            "                case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans  \n" +
            "                from Visit.VisitUserSchoolSelfAllocationXref xref  \n" +
            "                inner join  Visit.VClassroomObservation obs  \n" +
            "                on xref.schoolallocationid=obs.SchoolAllocationId \n" +
            "                inner join Visit.VisitClassroomObservationActivity act  \n" +
            "                on act.ClassRoomObservationId=obs.ClassRoomObservationId \n" +
            "                inner join Visit.VClassroomObservationResponse resp \n" +
            "                on resp.ActivityId=act.ActivityId \n" +
            "                inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId \n" +
            "                inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid \n" +
            "                where p.vparameterid =:ParameterId and xref.status=N'पूर्ण' and \n" +
            "                xref.month =:Month and xref.year=:Year\n" +
            "                Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId \n" +
            "             ) a on sm.UDISECode=a.udisecode \n" +
            "             Group By dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName \n" +
            "             )final \n" +
            "             where final.BlockId=:BlockId";

    public static final String GET_ALL_CLUSTERS_BY_BLOCK_LIST="select ClusterId from UDISE.ClusterMaster where BlockCode in :blockLists \n";
    public static final String FETCH_ALL_CLUSTERS_BY_BLOCK_CODE="select ClusterId from UDISE.ClusterMaster where BlockCode=:BlkCode";
    public static final String FETCH_CLUSTERS_ID_BY_BLOCK_CODE="select ClusterId from UDISE.ClusterMaster where BlockCode in :BlkCode";

    public static final String FETCH_COMPLETED_VISITS_BY_CLUSTERS_WISE="select distinct a.DistrictName,a.BlockName,a.ClusterName,a.EmployeeCode,a.EmployeeName,a.RoleName,b.visitpermonth as TargetVisits,c.Completed \n" +
            "             from(  \n" +
            "                              select distinct dst.DistrictName,blc.BlockName,cm.ClusterName,um.EmployeeCode,emp.EmployeeName,r.RoleID,r.RoleName  \n" +
            "                                    from RoleAssignment rag  \n" +
            "                                    inner join UserMaster um on um.UserId=rag.UserId  \n" +
            "                                    inner join Employee.Dbo.EmployeeMaster emp on emp.EmployeeCode=um.EmployeeCode  \n" +
            "                                    inner join RoleArea ra on ra.RoleAssignmentId=rag.RoleAssignmentId  \n" +
            "                                    inner join RoleMaster r on r.RoleID=rag.RoleId  \n" +
            "                        inner join UDISE.ClusterMaster cm on cm.ClusterId=ra.ClusterId  \n" +
            "                                    inner join UDISE.BlockMaster blc on blc.BlockId=cm.BlockCode  \n" +
            "                        inner join UDISE.DistrictMaster dst on dst.DistrictId=blc.DistrictId  \n" +
            "                                    where  rag.RoleId in :roleIds \n" +
            "                                    Group By  dst.DistrictName,BLC.BlockName,cm.ClusterName,um.EmployeeCode,emp.EmployeeName,r.RoleID,r.RoleName--,rv.visitpermonth  \n" +
            "                        ) a  \n" +
            "            left join ( \n" +
            "            select roleId,visitpermonth \n" +
            "            from Visit.VregularVisitPlan  \n" +
            "                 where roleid in :roleIds  and month=:Month \n" +
            "            )b on b.roleid=a.RoleID \n" +
            "            left join ( \n" +
            "            select  employeeCode as EmpCode,Count(1) as Completed,roleid \n" +
            "            from Visit.VisitUserSchoolSelfAllocationXref  \n" +
            "                     where Month = :Month and status=N'पूर्ण' AND YEAR=:year and roleid in :roleIds \n" +
            "             Group By employeecode,roleid \n" +
            "            )c on a.EmployeeCode=c.EmpCode";

    public static final String CLUSTERWISE_COMPLETED_VISITS_PERCENTAGE="  select dst.DistrictId,dst.DistrictName,bm.BlockName,cm.ClusterName,CAST(count(xref.schoolallocationid) AS float) as CompletedVisits,cast(count(si.schoolinformationid) as FLOAT) as Result\n" +
            "                from UDISE.DistrictMaster dst\n" +
            "                inner join UDISE.BlockMaster bm ON dst.DistrictId=bm.DistrictId\n" +
            "                inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockId\n" +
            "                inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "                left join  \n" +
            "                ( \n" +
            "                  Select  *\n" +
            "                 from Visit.VisitUserSchoolSelfAllocationXref  where status=N'पूर्ण' and month=:Month and year=:Year\n" +
            "\t\t       ) xref on xref.udisecode = sm.UDISECode\n" +
            "\t\t\t   left join (\n" +
            "\t\t\t   select * \n" +
            "\t\t\t   from Visit.SchoolInformation where answer=3 and classid in :ClassIds\n" +
            "\t\t\t   ) si on si.schoolallocationid=xref.schoolallocationid\n" +
            "\t\t\t   where bm.BlockId=:BlockId\n" +
            "\t\t\t   Group By dst.DistrictId,dst.DistrictName,bm.BlockName,cm.ClusterName\n" +
            "\t\t\t   Order By dst.DistrictName";

    public static final String GET_SECTION_ID_BY_SUBSECTION_ID="select distinct ss.VsectionId\n" +
            "from Visit.VsubSectionMaster ss\n" +
            "where ss.VsubSectionId=:SubSection";

    public static final String GET_ALL_SUBSECTIONS_BY_DATE_FILTER="select ss.*,(select count(*) from Visit.VsubSectionMaster) AS TotalRows \n" +
            "from Visit.VsubSectionMaster ss\n" +
            "where cast(createdon as date) >= :LastUpdateDate";

    public static final String GET_ALL_SUBSECTIONS="select ss.*,(select count(*) from Visit.VsubSectionMaster) AS TotalRows \n" +
            "from Visit.VsubSectionMaster ss";


    public static final String GET_SUBSECTIONID_BY_SECTIONID="SELECT [VsubSectionId] FROM [Visit].[VsubSectionMaster] where VsectionId = :sectionId";

    public static final String GET_SUB_SECTION_ID_AND_NAME_BY_SECTION_ID = "select vsm.VsubSectionId , vsm.Name  FROM Shiksha.Visit.VsubSectionMaster vsm \n" +
            "inner join Shiksha.Visit.VsectionMaster vm \n" +
            "on vm.VsectionId  = vsm.VsectionId \n" +
            "where vm.VsectionId = :sectionId";


    public  static  final  String GET_UNIQUE_SCHOOL_VISITED_IN_ALL_DISTRICTS  = "select f.DistrictId,f.DistrictName,f.UniqueSchools as Percentage\n" +
            "from(\n" +
            "select dst.DistrictId,dst.DistrictName,count(a.udisecode) as UniqueSchools\n" +
            "from UDISE.DistrictMaster dst\n" +
            "inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join(\n" +
            "Select distinct udisecode\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref  \n" +
            "where status=N'पूर्ण' and month =:Month and year=:Year \n" +
            ")a on a.udisecode=sm.UDISECode\n" +
            "Group By dst.DistrictId,dst.DistrictName\n" +
            ") f order by f.UniqueSchools desc";


    public  static  final String GET_ALL_DISTRICT_VISIT_REPORT_FOR_PARAMETER = "select final.DistrictId,final.DistrictName,\n" +
            "case when Round(final.Percentage,0) is null then 0 \n" +
            "     when Round(final.Percentage,0)>100 then 100\n" +
            "else Round(final.Percentage,0) end as Percentage\n" +
            "from(\n" +
            "select distinct dst.DistrictId,dst.DistrictName,count(a.SchoolAllocationId) as TotalVisits,\n" +
            "sum(a.Ans) as Correct,\n" +
            "(cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage\n" +
            "from UDISE.DistrictMaster dst \n" +
            "inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join (\n" +
            "   select distinct obs.SchoolAllocationId,xref.udisecode,\n" +
            "   case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans \n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "   inner join  Visit.VClassroomObservation obs \n" +
            "   on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act \n" +
            "   on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp\n" +
            "   on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   where p.vparameterid =:ParameterId and xref.status=N'पूर्ण' and\n" +
            "   xref.month =:Month and xref.year=:Year \n" +
            "   Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId\n" +
            ") a on sm.UDISECode=a.udisecode\n" +
            "Group By dst.DistrictId,dst.DistrictName\n" +
            ")final\n" +
            "order by final.DistrictName";

    public  static  final  String GET_DIVISION_VISITS = "select distinct a.DivisionName,a.EmployeeCode,a.EmployeeName,a.RoleName,b.visitpermonth as TargetVisits,c.Completed\n" +
            " from( \n" +
            "                  select distinct dv.DivisionName,um.EmployeeCode,emp.EmployeeName,r.RoleID,r.RoleName \n" +
            "                        from RoleAssignment rag \n" +
            "                        inner join UserMaster um on um.UserId=rag.UserId \n" +
            "                        inner join Employee.Dbo.EmployeeMaster emp on emp.EmployeeCode=um.EmployeeCode \n" +
            "                        inner join RoleArea ra on ra.RoleAssignmentId=rag.RoleAssignmentId \n" +
            "                        inner join RoleMaster r on r.RoleID=rag.RoleId \n" +
            "                        inner join UDISE.DivisionMaster dv on dv.DivisionId=ra.DivisionId\n" +
            "                        where  rag.RoleId in :roleIds\n" +
            "                        Group By  dv.DivisionName,um.EmployeeCode,emp.EmployeeName,r.RoleID,r.RoleName \n" +
            "            ) a \n" +
            "\t\t\tleft join (\n" +
            "\t\t\tselect roleId,visitpermonth\n" +
            "\t\t\tfrom Visit.VregularVisitPlan \n" +
            "\t\t     where roleid in :roleIds\n" +
            "\t\t\t)b on b.roleid=a.RoleID\n" +
            "\t\t\tleft join (\n" +
            "\t\t\tselect  employeeCode as EmpCode,Count(1) as Completed,roleid\n" +
            "\t\t\tfrom Visit.VisitUserSchoolSelfAllocationXref \n" +
            "\t         where Month = :Month and status=N'पूर्ण' AND YEAR=:year and roleid in :roleIds\n" +
            "\t\t\t Group By employeecode,roleid\n" +
            "\t\t\t)c on a.EmployeeCode=c.EmpCode";


    public  static  final  String SAVE_EMERGENCY_VISIT_STATUS = "UPDATE [Visit].[EmergencyVisitMaster] set [status] = :status where [EmergencyVisitId] = :id";

    public static  final  String GET_CURRENT_DATE_EMERGENCY_VISIT = "SELECT  [ScheduleId]\n" +
            "      ,[EmergencyVisitId]\n" +
            "      ,[UDISECode]\n" +
            "      ,[ScheduledDate]\n" +
            "      ,[EmployeeId]\n" +
            "      ,[CreatedBy]\n" +
            "      ,[CreatedOn]\n" +
            "      ,[UpdatedBy]\n" +
            "      ,[UpdatedOn]\n" +
            "  FROM [RSKAcademic].[Visit].[EmergencyVisitSchedule]\n" +
            "  where [ScheduledDate]>= CAST(CAST(GETDATE() AS Date) AS DATETIME);";


    public  static  final  String GET_ALL_REGULAR_VISIT = "SELECT  [ScheduleId]\n" +
            "      ,[ScheduledDate]\n" +
            "      ,[Status]\n" +
            "      ,[SchoolAllocationId]\n"+
            "      ,[CreatedBy]\n" +
            "      ,[CreatedOn]\n" +
            "      ,[UpdatedBy]\n" +
            "      ,[UpdatedOn]\n" +
            "  FROM [RSKAcademic].[Visit].[RegularVisitSchedule]\n" +
            "   where [ScheduledDate]>= CAST(CAST(GETDATE() AS Date) AS DATETIME);";

    public  static  final  String FIND_BY_PARAMETER_ORDER_BY_UPDATED_DESC = "SELECT * FROM [RSKAcademic].[Visit].[SelfAssessmentVerification] s where s.ParameterId = :parameter ORDER BY updatedOn DESC";




    public static final String GET_PARAMETERID_BY_SUBJECT_NAME=" select  p.vparameterid\n" +
            "\t\t\t from Visit.VsubSectionMaster ss\n" +
            "\t\t\t inner join Visit.VparameterMaster p on ss.VsubSectionId=p.vsubsectionid\n" +
            "\t\t\t where ss.VsectionId=:VSectionId and parametername=:VparameterName";

    public static final String GET_ALL_PARAMETER_VALUES_ROWS="select pv.*,(select count(*) from Visit.VParameterValues) AS TotalRows \n" +
            "from Visit.VParameterValues pv";

    public static final String GET_PARAMETER_VALUES_BY_DATE_FILTER="select pv.*,(select count(*) from Visit.VParameterValues) AS TotalRows\n" +
            "from Visit.VParameterValues pv\n" +
            "where cast(createdon as date) >= :LastUpdatedDate";

    public static final String GET_PARAMETER_BY_SUB_SECTION_ID="select distinct p.vparameterid,p.parametername,case when cpt.TransactionId is null then 'false' else 'true' end AS isChecked\n" +
            "from Visit.VparameterMaster p\n" +
            "left join (select * from Visit.CompetencyParameterTransaction cpt\n" +
            " where cpt.CompetencyId=:CompetencyId\n" +
            " ) cpt\n" +
            "on cpt.VparameterId=p.vparameterid\n" +
            "where p.VsubSectionId=:VSubSectionId and p.isactive=1";

    public static final String GET_PARAMETER_NAME_BY_ID="select vparameterid from Visit.VparameterMaster where parametername=:ParameterName\n";

    public static final String GET_PARAMETER_BY_SECTION_ID_AND_NAME="select pm.vparameterid\n" +
            "from Visit.VsubSectionMaster  ss\n" +
            "inner join Visit.VparameterMaster pm on pm.vsubsectionid=ss.VsubSectionId\n" +
            "where ss.VsectionId=:VSectionId and parametername=:ParameterName";

    public static final String GET_DISTRICT_PERCENTAGE="select final.DistrictId,final.DistrictName, \n" +
            "            case when Round(final.Percentage,0) is null then 0  \n" +
            "                 when Round(final.Percentage,0)>100 then 100 \n" +
            "            else Round(final.Percentage,0) end as Percentage \n" +
            "            from( \n" +
            "            select distinct dst.DistrictId,dst.DistrictName,count(a.SchoolAllocationId) as TotalVisits, \n" +
            "            sum(a.Ans) as Correct, \n" +
            "            (cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage \n" +
            "            from UDISE.DistrictMaster dst  \n" +
            "            inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId \n" +
            "            inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId \n" +
            "            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "            left join ( \n" +
            "               select distinct obs.SchoolAllocationId,xref.udisecode, \n" +
            "               case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans  \n" +
            "               from Visit.VisitUserSchoolSelfAllocationXref xref  \n" +
            "               inner join  Visit.VClassroomObservation obs  \n" +
            "               on xref.schoolallocationid=obs.SchoolAllocationId \n" +
            "               inner join Visit.VisitClassroomObservationActivity act  \n" +
            "               on act.ClassRoomObservationId=obs.ClassRoomObservationId \n" +
            "               inner join Visit.VClassroomObservationResponse resp \n" +
            "               on resp.ActivityId=act.ActivityId \n" +
            "               inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId \n" +
            "               inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid \n" +
            "               where p.vparameterid =:ParamId and xref.status=N'पूर्ण' and \n" +
            "                xref.month =:Month and xref.year=:Year  \n" +
            "               Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId \n" +
            "            ) a on sm.UDISECode=a.udisecode \n" +
            "            Group By dst.DistrictId,dst.DistrictName \n" +
            "            )final\n" +
            "\t\t\twhere final.DistrictId=:DistrictId\n" +
            "            order by final.DistrictName";

    public static final String GET_BLOCK_PERCENTAGE=  "select final.BlockId,final.BlockName, \n" +
            "             case when Round(final.Percentage,0) is null then 0  \n" +
            "                  when Round(final.Percentage,0)>100 then 100 \n" +
            "             else Round(final.Percentage,0) end as Percentage \n" +
            "             from( \n" +
            "             select distinct dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName \n" +
            "             ,count(a.SchoolAllocationId) as TotalVisits, \n" +
            "             sum(a.Ans) as Correct, \n" +
            "             (cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage \n" +
            "             from UDISE.DistrictMaster dst  \n" +
            "             inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId \n" +
            "             inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId \n" +
            "             inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "             left join ( \n" +
            "                select distinct obs.SchoolAllocationId,xref.udisecode, \n" +
            "                case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans  \n" +
            "                from Visit.VisitUserSchoolSelfAllocationXref xref  \n" +
            "                inner join  Visit.VClassroomObservation obs  \n" +
            "                on xref.schoolallocationid=obs.SchoolAllocationId \n" +
            "                inner join Visit.VisitClassroomObservationActivity act  \n" +
            "                on act.ClassRoomObservationId=obs.ClassRoomObservationId \n" +
            "                inner join Visit.VClassroomObservationResponse resp \n" +
            "                on resp.ActivityId=act.ActivityId \n" +
            "                inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId \n" +
            "                inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid \n" +
            "                where p.vparameterid =:ParamId and xref.status=N'पूर्ण' and \n" +
            "                xref.month =:Month and xref.year=:Year  \n" +
            "                Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId \n" +
            "             ) a on sm.UDISECode=a.udisecode \n" +
            "             Group By dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName \n" +
            "             )final \n" +
            "             where final.DistrictId=:DistrictId\n" +
            "             order by final.Percentage desc";

    public static final String GET_DISTRICT_VISIT_REPORT_FOR_PARAMETER="select final.DistrictId,final.DistrictName,\n" +
            "case when Round(final.Percentage,0) is null then 0 \n" +
            "     when Round(final.Percentage,0)>100 then 100\n" +
            "else Round(final.Percentage,0) end as Percentage\n" +
            "from(\n" +
            "select distinct dst.DistrictId,dst.DistrictName,count(a.SchoolAllocationId) as TotalVisits,\n" +
            "sum(a.Ans) as Correct,\n" +
            "(cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage\n" +
            "from UDISE.DistrictMaster dst \n" +
            "inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId\n" +
            "inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId\n" +
            "left join (\n" +
            "   select distinct obs.SchoolAllocationId,xref.udisecode,\n" +
            "   case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans \n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref \n" +
            "   inner join  Visit.VClassroomObservation obs \n" +
            "   on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act \n" +
            "   on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp\n" +
            "   on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   where p.vparameterid =:ParameterId and xref.status=N'पूर्ण' and\n" +
            "   xref.month =:Month and xref.year=:Year \n" +
            "   Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId\n" +
            ") a on sm.UDISECode=a.udisecode\n" +
            "Group By dst.DistrictId,dst.DistrictName\n" +
            ")final\n" +
            "order by final.Percentage desc";

    public static final String GET_CLUSTER_REPORT_BY_PARAMETER="select final.DistrictId,final.DistrictName,final.BlockId,final.BlockName, \n" +
            "            final.ClusterId,final.ClusterName, \n" +
            "            case when Round(final.Percentage,0) is null then 0  \n" +
            "                 when Round(final.Percentage,0)>100 then 100 \n" +
            "            else Round(final.Percentage,0) end as Percentage \n" +
            "            from( \n" +
            "            select distinct dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName, \n" +
            "            cm.ClusterId,cm.ClusterName \n" +
            "            ,count(a.SchoolAllocationId) as TotalVisits, \n" +
            "            sum(a.Ans) as Correct, \n" +
            "            (cast((sum(a.Ans)) as float)/cast(count(a.SchoolAllocationId) as float))*100.0 as Percentage \n" +
            "            from UDISE.DistrictMaster dst  \n" +
            "            inner join UDISE.BlockMaster blc on dst.DistrictId=blc.DistrictId \n" +
            "            inner join UDISE.ClusterMaster cm on cm.BlockCode=blc.BlockId \n" +
            "            inner join UDISE.SchoolMaster sm on sm.ClusterId=cm.ClusterId \n" +
            "            left join ( \n" +
            "               select distinct obs.SchoolAllocationId,xref.udisecode, \n" +
            "               case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end as Ans  \n" +
            "               from Visit.VisitUserSchoolSelfAllocationXref xref  \n" +
            "               inner join  Visit.VClassroomObservation obs  \n" +
            "               on xref.schoolallocationid=obs.SchoolAllocationId \n" +
            "               inner join Visit.VisitClassroomObservationActivity act  \n" +
            "               on act.ClassRoomObservationId=obs.ClassRoomObservationId \n" +
            "               inner join Visit.VClassroomObservationResponse resp \n" +
            "               on resp.ActivityId=act.ActivityId \n" +
            "               inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId \n" +
            "               inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid \n" +
            "               where p.vparameterid =:ParameterId and xref.status=N'पूर्ण' and \n" +
            "               xref.month =:Month and xref.year=:Year\n" +
            "               Group By obs.SchoolAllocationId,xref.udisecode,pv.orderno,pv.vparametervalueid,resp.ParameterValueId \n" +
            "            ) a on sm.UDISECode=a.udisecode \n" +
            "            Group By dst.DistrictId,dst.DistrictName,blc.BlockId,blc.BlockName,cm.ClusterId,cm.ClusterName \n" +
            "            )final \n" +
            "\t\t\twhere final.BlockId=:BlockId\n" +
            "            order by final.Percentage desc";

    public static final String GET_MONTHLY_STATE_REPORT="select distinct m.Id,m.Month,count(a.schoolallocationid) as VisitCounts,b.Answers,\n" +
            "ROUND((cast(b.Answers as float)/cast(count(a.schoolallocationid) as float))*100,0) as Percentage\n" +
            "from Month m \n" +
            "left join(\n" +
            " select xref.month,xref.schoolallocationid\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   where xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            ")a on a.month=m.Id\n" +
            "left join (\n" +
            "   select xref.month, count(xref.schoolallocationid) as VisitCounts,\n" +
            "   sum(case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end) as Answers\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   Group By xref.month\n" +
            ")b on m.Id=b.month\n" +
            "Group by m.Id,m.Month,b.Answers\n" +
            "Order By m.Id";

    public static final String GET_MONTHLY_DISTRICT_REPORT="select distinct m.Id,m.Month,count(a.schoolallocationid) as VisitCounts,b.Answers,\n" +
            "ROUND((cast(b.Answers as float)/cast(count(a.schoolallocationid) as float))*100,0) as Percentage\n" +
            "from Month m \n" +
            "left join(\n" +
            "select distinct xref.month,xref.schoolallocationid\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   inner join UDISE.SchoolMaster sch on sch.UDISECode=xref.udisecode\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   and sch.DistrictId=:DistrictId \n" +
            ")a on a.month=m.Id\n" +
            "left join (\n" +
            " select distinct xref.month,\n" +
            "   sum(case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end) as Answers\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   inner join UDISE.SchoolMaster sch on sch.UDISECode=xref.udisecode\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   and sch.DistrictId=:DistrictId   \n" +
            "   Group by xref.month\n" +
            ")b on m.Id=b.month\n" +
            "Group By m.Id,m.Month,a.month,b.Answers\n" +
            "Order By m.Id";

    public static final String GET_MONTHLY_BLOCK_REPORT="select distinct m.Id,m.Month,count(a.schoolallocationid) as VisitCounts,b.Answers,\n" +
            "ROUND((cast(b.Answers as float)/cast(count(a.schoolallocationid) as float))*100,0) as Percentage\n" +
            "from Month m \n" +
            "left join(\n" +
            "select distinct xref.month,xref.schoolallocationid\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   inner join UDISE.SchoolMaster sch on sch.UDISECode=xref.udisecode\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   and sch.BlockId=:BlockId \n" +
            ")a on a.month=m.Id\n" +
            "left join (\n" +
            " select distinct xref.month,\n" +
            "   sum(case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end) as Answers\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   inner join UDISE.SchoolMaster sch on sch.UDISECode=xref.udisecode\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   and sch.BlockId=:BlockId   \n" +
            "   Group by xref.month\n" +
            ")b on m.Id=b.month\n" +
            "Group By m.Id,m.Month,a.month,b.Answers\n" +
            "Order By m.Id";

    public static final String GET_MONTHLY_CLUSTER_REPORT="select distinct m.Id,m.Month,count(a.schoolallocationid) as VisitCounts,b.Answers,\n" +
            "ROUND((cast(b.Answers as float)/cast(count(a.schoolallocationid) as float))*100,0) as Percentage\n" +
            "from Month m \n" +
            "left join(\n" +
            "select distinct xref.month,xref.schoolallocationid\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   inner join UDISE.SchoolMaster sch on sch.UDISECode=xref.udisecode\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   and sch.ClusterId=:ClusterCode \n" +
            ")a on a.month=m.Id\n" +
            "left join (\n" +
            " select distinct xref.month,\n" +
            "   sum(case when pv.orderno=3 and pv.vparametervalueid=resp.ParameterValueId then 1 else 0 end) as Answers\n" +
            "   from Visit.VisitUserSchoolSelfAllocationXref xref\n" +
            "   inner join Visit.VClassroomObservation obs on xref.schoolallocationid=obs.SchoolAllocationId\n" +
            "   inner join Visit.VisitClassroomObservationActivity act on act.ClassRoomObservationId=obs.ClassRoomObservationId\n" +
            "   inner join Visit.VClassroomObservationResponse resp on resp.ActivityId=act.ActivityId\n" +
            "   inner join Visit.VParameterValues pv on pv.vparametervalueid=resp.ParameterValueId\n" +
            "   inner join Visit.VparameterMaster p on p.vparameterid=pv.vparameterid\n" +
            "   inner join Visit.VsubSectionMaster ss on ss.VsubSectionId=p.vsubsectionid\n" +
            "   inner join UDISE.SchoolMaster sch on sch.UDISECode=xref.udisecode\n" +
            "   where  xref.status=N'पूर्ण' and  p.vparameterid=:Param and xref.year=:Year\n" +
            "   and sch.ClusterId=:ClusterCode  \n" +
            "   Group by xref.month\n" +
            ")b on m.Id=b.month\n" +
            "Group By m.Id,m.Month,a.month,b.Answers\n" +
            "Order By m.Id";

    public static final String CHECK_IF_PARAMETER_OF_CLASS="select distinct p.vparameterid,p.parametername\n" +
            "            from Visit.VparameterMaster p\n" +
            "            inner join Visit.VParameterClass pc on pc.vparameterid=p.vparameterid\n" +
            "\t\t\tinner join Visit.ClassMasterTransaction cmt on cmt.ClassId=pc.ClassId\n" +
            "\t\t\tinner join Visit.ClassMaster cm on cm.classid=cmt.ClassMasterId\n" +
            "            where p.vparameterid=:ParamId and cm.classid=:ClassId";

    public static final String GET_ALL_PARAMETER_ROWS="select p.*,(select count(*) from Visit.VparameterMaster) AS TotalRows \n" +
            "from Visit.VparameterMaster p";

    public static final String GET_ALL_PARAMETER_ROWS_BY_DATE="select p.*,(select count(*) from Visit.VparameterMaster) AS TotalRows\n" +
            "from Visit.VparameterMaster p\n" +
            "where cast(createdon as date) >= :LastUpdatedOn";

    public static final String CHANGE_PARAMETER_STATUS="Update Visit.VparameterMaster\n" +
            "set isactive=:Status\n" +
            "where vparameterid =:ParameterId";

    public static final String GET_PARAMETER_CLASS_ROWS="select pc.*,(select count(*) from Visit.VParameterClass) AS TotalRows \n" +
            "from Visit.VParameterClass pc";

    public static final String GET_PARAMETER_CLASS_ROWS_DATE="select pc.*,(select count(*) from Visit.VParameterClass) AS TotalRows\n" +
            "from Visit.VParameterClass pc\n" +
            "where cast(createdon as date) >= :LastUpdateDate";

    public static final String GET_DOMAIN_BY_SECTION="select * from Visit.MentoringDomainMaster\n" +
            "WHERE VsectionId=:SectionId";

    public static final String GET_COMPETENCY_BY_DOMAIN=
            "select mcm.CompetencyId,mcm.CompetencyName from \n" +
            "Visit.MentoringCompetencyMaster mcm\n" +
            "WHERE mcm.IsActive=1 AND DomainId=:DomainId";

    public static final String GET_ALL_MENTORING_DOMAINS_BY_DATE="select *\n" +
            "            from Visit.MentoringDomainMaster\n" +
            "            where cast(createdon as date) >= :LastUpdatedOn\n";
    public static final String GET_ALL_MENTORING_COMPETENCY_MASTER_BY_DATE="select *\n" +
            "            from Visit.MentoringCompetencyMaster\n" +
            "            where cast(createdon as date) >= :LastUpdatedOn";
    public static final String GET_ALL_COMPETENCY_PARAMETERS_TRANSACTION_BY_DATE="select *\n" +
            "            from Visit.CompetencyParameterTransaction\n" +
            "            where cast(createdon as date) >= :LastUpdatedOn";
    public static final String UPDATE_MENTORING_DOMAIN_STATUS="Update Visit.MentoringDomainMaster\n" +
            "SET IsActive=:Status\n" +
            "where DomainId=:DomainId";

    public static final String UPDATE_MENTORING_COMPETENCY_STATUS="Update Visit.MentoringCompetencyMaster\n" +
            "SET IsActive=:Status\n" +
            "where CompetencyId=:CompetencyId";

    public static final String GET_VISITS_FOR_MENTOR="select distinct x.schoolallocationid,x.allocateddate,FORMAT(x.allocateddate,'dd-MMM-yyyy') as Date,sm.UDISECode,sm.SchoolName\n" +
            "  from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "  inner join UDISE.SchoolMaster sm on sm.UDISECode=x.udisecode\n" +
            "  where x.status=N'पूर्ण' and x.employeecode=:EmployeeCode  Order By x.allocateddate desc \n";

    public static final String GET_VISITS_FOR_UDISE_CODE="\n" +
            "  select distinct x.schoolallocationid,x.allocateddate,FORMAT(x.allocateddate,'dd-MMM-yyyy') as Date,sm.UDISECode,sm.SchoolName,x.employeecode as Mentor\n" +
            "  from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "  inner join UDISE.SchoolMaster sm on sm.UDISECode=x.udisecode\n" +
            "  where x.status=N'पूर्ण' and x.udisecode=:UdiseCode Order By x.allocateddate desc";


    public static final String UPDATE_MENTORING_DOMAIN_OREDER_NUMBER = "UPDATE Visit.MentoringDomainMaster\n" +
            "SET OrderNo = :OrderNo\n" +
            "where DomainId = :DomainId\n";

    public static final String UPDATE_MENTORING_COMPETENCY_ORDER_NUMBER = "UPDATE Visit.MentoringCompetencyMaster \n" +
            "SET OrderNo = :OrderNo\n" +
            "where CompetencyId = :CompetencyId";

    public static final String GET_MENTORING_REPORT="SELECT DISTINCT    \n" +
            "    final.*,    \n" +
            "    fb.feedback,    \n" +
            "    fb.Color    \n" +
            "FROM (   \n" +
            "    SELECT DISTINCT    \n" +
            "        s.vsectionid,   \n" +
            "        s.name,   \n" +
            "        d.domainid,   \n" +
            "        d.domainname, \n" +
            "        CAST(SUBSTRING(res.WeekName, PATINDEX('%[0-9]%', res.WeekName), LEN(res.WeekName)) AS INT) AS Week, \n" +
            "        d.orderno AS domainorder,   \n" +
            "        c.competencyid,   \n" +
            "        c.competencyname,   \n" +
            "        c.orderno AS competencyorder,   \n" +
            "        res.weightage   \n" +
            "    FROM    \n" +
            "        Visit.VsectionMaster s  \n" +
            "    INNER JOIN     \n" +
            "        Visit.VsubSectionMaster sub ON s.VsectionId = sub.VsectionId   \n" +
            "    INNER JOIN    \n" +
            "        Visit.MentoringDomainMaster d ON d.VsectionId = sub.VsectionId   \n" +
            "    INNER JOIN    \n" +
            "        Visit.MentoringCompetencyMaster c ON d.DomainId = c.DomainId   \n" +
            "    LEFT JOIN (   \n" +
            "        SELECT    \n" +
            "            cpt.competencyid, \n" +
            "            wd.WeekName,   \n" +
            "            SUM(pv.weightage) AS weightage   \n" +
            "        FROM    \n" +
            "            Visit.VClassroomObservation obs   \n" +
            "        INNER JOIN    \n" +
            "            Visit.VisitClassroomObservationActivity act ON act.classroomobservationid = obs.ClassRoomObservationId   \n" +
            "        INNER JOIN    \n" +
            "            Visit.VClassroomObservationResponse r ON r.activityid = act.ActivityId   \n" +
            "        INNER JOIN    \n" +
            "            SpotAss.WeekDetail wd ON wd.Id = act.Week \n" +
            "        INNER JOIN    \n" +
            "            Visit.CompetencyParameterTransaction cpt ON cpt.vparameterid = r.ParameterId   \n" +
            "        INNER JOIN    \n" +
            "            Visit.VParameterValues pv ON pv.vparametervalueid = r.ParameterValueId   \n" +
            "        WHERE    \n" +
            "            obs.schoolallocationid = :SchoolAllocationId  \n" +
            "        GROUP BY    \n" +
            "            cpt.competencyid, wd.WeekName \n" +
            "    ) res ON res.competencyid = c.competencyid   \n" +
            ") final   \n" +
            "LEFT JOIN    \n" +
            "    Visit.MentoringWeightageFeedback fb   \n" +
            "ON final.weightage BETWEEN fb.ScoreRangeFrom AND fb.ScoreRangeTo    \n" +
            "AND final.competencyid = fb.competencyid   \n" +
            "WHERE final.weightage IS NOT NULL   \n" +
            "ORDER BY \n" +
            "    final.domainorder, \n" +
            "    final.competencyorder ASC";

    public static final String GET_VISIT_DETAILS="select distinct top(1) x.udisecode,sm.SchoolName,dst.DistrictName,blc.BlockName,cm.ClusterName,Format(x.allocateddate,'dd-MM-yyyy') as DateOfVisit,si.numberofstudents as AttendanceOnDate, \n" +
            "            emp.EmployeeName as TeacherName,cmt.ClassId as GradeObserved,case when sd.EnrolledStudents is null then 0 else sd.EnrolledStudents end as Enrollment, emp1.EmployeeCode as MentorId,emp1.EmployeeName as MentorName,emp1.Designation\n" +
            "            from Visit.VisitUserSchoolSelfAllocationXref x \n" +
            "            inner join UDISE.SchoolMaster sm on sm.UDISECode=x.udisecode \n" +
            "            inner join UDISE.ClusterMaster cm on cm.ClusterId=sm.ClusterId \n" +
            "            inner join UDISE.BlockMaster blc on blc.BlockId=cm.BlockCode \n" +
            "            inner join UDISE.DistrictMaster dst on dst.DistrictId=blc.DistrictId \n" +
            "            inner join Visit.SchoolInformation si on si.schoolallocationid=x.schoolallocationid \n" +
            "            left join Employee.Dbo.EmployeeMaster emp on si.teacherid=emp.EmployeeCode \n" +
            "\t\t\tleft join Employee.Dbo.EmployeeMaster emp1 on emp1.EmployeeCode=x.employeecode\n" +
            "            inner join Visit.ClassMasterTransaction cmt on cmt.ClassMasterId=si.classid \n" +
            "            left join ( \n" +
            "               select sd.UDISECode,count(sd.Samagra) as EnrolledStudents \n" +
            "               from Student.Dbo.StudentDetail25 sd \n" +
            "               Group By sd.UDISECode \n" +
            "             ) sd on sd.UDISECode=x.udisecode \n" +
            "            WHERE x.schoolallocationid=:SchoolAllocationId";

    public static final String UPDATE_PARAMETER_ORDERNO="Update Visit.VparameterMaster\n" +
            "SET ordernumber=:OrderNo\n" +
            "where vparameterid=:Vparameterid";

    public static final String GET_DISTRICT_LEVEL_STUDENT_ATTENDANCE_REPORT="With Schooldata as(select e.DistrictName,a.Schoolallocationid, a.udisecode,b.classid,b.numberofstudents, Count(c.Samagra) as registered, \n" +
            "            Case when b.numberofstudents = 0 then 0 \n" +
            "            else Round(Cast(b.numberofstudents as float)*100/NULLIF(Count(Cast(c.Samagra as float)),0),0) end as Present_Percentage \n" +
            "            from Visit.VisitUserSchoolSelfAllocationXref a \n" +
            "            inner join Visit.Schoolinformation b on b.Schoolallocationid=a.schoolallocationid \n" +
            "            inner join Visit.ClassMasterTransaction cmt on cmt.ClassMasterId= b.ClassId \n" +
            "            inner join Student.dbo.StudentDetail24 c on c.UDISECode=a.udisecode and c.ClassId=cmt.ClassId \n" +
            "            inner join School.dbo.SchoolMaster d on d.UDISECode=a.udisecode \n" +
            "            inner join UDISE.DistrictMaster e on e.DistrictCode=d.DistrictCode \n" +
            "            where a.year = :Year and a.month = :Month and b.classid = :ClassId \n" +
            "            group by e.DistrictName,a.Schoolallocationid, a.udisecode,b.classid,b.numberofstudents),     \n" +
            "Percentage_Bucket as(\n" +
            "\t\t\tselect DistrictName,schoolallocationid, udisecode, classid, numberofstudents, registered, Present_Percentage, \n" +
            "            Case  \n" +
            "            When Present_Percentage Between 0 and 20 then 'ZeroToTwenty' \n" +
            "            When Present_Percentage Between 21 and 40 then 'TwentyOneToForty' \n" +
            "            When Present_Percentage Between 41 and 60 then 'FortyOneToSixty' \n" +
            "            When Present_Percentage Between 61 and 80 then 'SixtyOneToEighty' \n" +
            "            Else 'EightyOneToHundred' end as Percentage_Bucket \n" +
            "            from Schooldata),\n" +
            "Count_Bucket as(select DistrictName, Count(*) as TotalSchools \n" +
            "\t\t\tfrom Percentage_Bucket\n" +
            "\t\t\tgroup by DistrictName)\n" +
            "select * from(\n" +
            "select x.DistrictName, x.Percentage_Bucket,  CONCAT(x.BucketSchools * 100/x.TotalSchools, '%') as Percentage\n" +
            "from\n" +
            "(\n" +
            "select pb.DistrictName, pb.Percentage_Bucket, Count(*) as BucketSchools, cb.TotalSchools from \n" +
            "Percentage_Bucket as pb\n" +
            "inner join Count_Bucket cb on cb.DistrictName=pb.DistrictName\n" +
            "group by pb.DistrictName, pb.Percentage_Bucket, cb.TotalSchools)x\n" +
            ")as Sourcetable\n" +
            "Pivot (\n" +
            "\t\tMax(Percentage)\n" +
            "\t\tFor Percentage_Bucket in([ZeroToTwenty],[TwentyOneToForty],[FortyOneToSixty],[SixtyOneToEighty],[EightyOneToHundred])\n" +
            ")as Pivottable\n" +
            "Order by DistrictName";


    public static final String GET_BLOCK_LEVEL_STUDENT_ATTENDANCE_REPORT="With Schooldata as(select d.DistrictName, d.BlockName,a.Schoolallocationid, a.udisecode,b.classid,b.numberofstudents, Count(c.Samagra) as registered, \n" +
            "            Case when b.numberofstudents = 0 then 0 \n" +
            "            else Round(Cast(b.numberofstudents as float)*100/NULLIF(Count(Cast(c.Samagra as float)),0),0) end as Present_Percentage \n" +
            "            from Visit.VisitUserSchoolSelfAllocationXref a \n" +
            "            inner join Visit.Schoolinformation b on b.Schoolallocationid=a.schoolallocationid \n" +
            "            inner join Visit.ClassMasterTransaction cmt on cmt.ClassMasterId= b.ClassId \n" +
            "            inner join Student.dbo.StudentDetail24 c on c.UDISECode=a.udisecode and c.ClassId=cmt.ClassId \n" +
            "            inner join School.dbo.SchoolMaster d on d.UDISECode=a.udisecode\n" +
            "            where a.year = :Year and a.month = :Month and b.classid = :ClassId and d.DistrictCode = :AreaId\n" +
            "            group by d.DistrictName,d.BlockName,a.Schoolallocationid, a.udisecode,b.classid,b.numberofstudents),     \n" +
            "Percentage_Bucket as(\n" +
            "\t\t\tselect DistrictName,BlockName,schoolallocationid, udisecode, classid, numberofstudents, registered, Present_Percentage, \n" +
            "            Case  \n" +
            "            When Present_Percentage Between 0 and 20 then 'ZeroToTwenty' \n" +
            "            When Present_Percentage Between 21 and 40 then 'TwentyOneToForty' \n" +
            "            When Present_Percentage Between 41 and 60 then 'FortyOneToSixty' \n" +
            "            When Present_Percentage Between 61 and 80 then 'SixtyOneToEighty' \n" +
            "            Else 'EightyOneToHundred' end as Percentage_Bucket \n" +
            "            from Schooldata),\n" +
            "Count_Bucket as(select DistrictName, BlockName,Count(*) as TotalSchools \n" +
            "\t\t\tfrom Percentage_Bucket\n" +
            "\t\t\tgroup by DistrictName, BlockName)\n" +
            "select * from(\n" +
            "select x.DistrictName,x.BlockName,x.Percentage_Bucket,  CONCAT(x.BucketSchools * 100/x.TotalSchools, '%') as Percentage\n" +
            "from\n" +
            "(\n" +
            "select pb.DistrictName, pb.BlockName,pb.Percentage_Bucket, Count(*) as BucketSchools, cb.TotalSchools from \n" +
            "Percentage_Bucket as pb\n" +
            "inner join Count_Bucket cb on cb.DistrictName=pb.DistrictName and cb.BlockName=pb.BlockName\n" +
            "group by pb.DistrictName, pb.BlockName,pb.Percentage_Bucket, cb.TotalSchools)x\n" +
            ")as Sourcetable\n" +
            "Pivot (\n" +
            "\t\tMax(Percentage)\n" +
            "\t\tFor Percentage_Bucket in([ZeroToTwenty],[TwentyOneToForty],[FortyOneToSixty],[SixtyOneToEighty],[EightyOneToHundred])\n" +
            ")as Pivottable\n" +
            "Order by DistrictName, BlockName";

    public static final String GET_CLUSTER_LEVEL_STUDENT_ATTENDANCE_REPORT="With Schooldata as(select d.DistrictName, d.BlockName,d.ClusterName,a.Schoolallocationid, a.udisecode,b.classid,b.numberofstudents, Count(c.Samagra) as registered, \n" +
            "            Case when b.numberofstudents = 0 then 0 \n" +
            "            else Round(Cast(b.numberofstudents as float)*100/NULLIF(Count(Cast(c.Samagra as float)),0),0) end as Present_Percentage \n" +
            "            from Visit.VisitUserSchoolSelfAllocationXref a \n" +
            "            inner join Visit.Schoolinformation b on b.Schoolallocationid=a.schoolallocationid \n" +
            "            inner join Visit.ClassMasterTransaction cmt on cmt.ClassMasterId= b.ClassId \n" +
            "            inner join Student.dbo.StudentDetail24 c on c.UDISECode=a.udisecode and c.ClassId=cmt.ClassId \n" +
            "            inner join School.dbo.SchoolMaster d on d.UDISECode=a.udisecode\n" +
            "            where a.year = :Year and a.month = :Month and b.classid = :ClassId and d.BlockCode = :AreaId\n" +
            "            group by d.DistrictName,d.BlockName,d.ClusterName,a.Schoolallocationid, a.udisecode,b.classid,b.numberofstudents),     \n" +
            "Percentage_Bucket as(\n" +
            "\t\t\tselect DistrictName,BlockName,ClusterName,schoolallocationid, udisecode, classid, numberofstudents, registered, Present_Percentage, \n" +
            "            Case  \n" +
            "            When Present_Percentage Between 0 and 20 then 'ZeroToTwenty' \n" +
            "            When Present_Percentage Between 21 and 40 then 'TwentyOneToForty' \n" +
            "            When Present_Percentage Between 41 and 60 then 'FortyOneToSixty' \n" +
            "            When Present_Percentage Between 61 and 80 then 'SixtyOneToEighty' \n" +
            "            Else 'EightyOneToHundred' end as Percentage_Bucket \n" +
            "            from Schooldata),\n" +
            "Count_Bucket as(select DistrictName, BlockName,ClusterName,Count(*) as TotalSchools \n" +
            "\t\t\tfrom Percentage_Bucket\n" +
            "\t\t\tgroup by DistrictName, BlockName,ClusterName)\n" +
            "select * from(\n" +
            "select x.DistrictName,x.BlockName,x.ClusterName,x.Percentage_Bucket,  CONCAT(x.BucketSchools * 100/x.TotalSchools, '%') as Percentage\n" +
            "from\n" +
            "(\n" +
            "select pb.DistrictName, pb.BlockName,pb.ClusterName,pb.Percentage_Bucket, Count(*) as BucketSchools, cb.TotalSchools from \n" +
            "Percentage_Bucket as pb\n" +
            "inner join Count_Bucket cb on cb.DistrictName=pb.DistrictName and cb.BlockName=pb.BlockName and cb.ClusterName=pb.ClusterName\n" +
            "group by pb.DistrictName, pb.BlockName,pb.ClusterName,pb.Percentage_Bucket, cb.TotalSchools)x\n" +
            ")as Sourcetable\n" +
            "Pivot (\n" +
            "\t\tMax(Percentage)\n" +
            "\t\tFor Percentage_Bucket in([ZeroToTwenty],[TwentyOneToForty],[FortyOneToSixty],[SixtyOneToEighty],[EightyOneToHundred])\n" +
            ")as Pivottable\n" +
            "Order by DistrictName, BlockName, ClusterName";

    public static final String GET_CO_PARAMETER_REPORT_FOR_ALL_DISTRICTS="With TotalVisits as(select e.DistrictName, e.DistrictCode, Count(*) as TotalCount\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and sm.VsectionId = :SubjectId\n" +
            "Group by e.DistrictName, e.DistrictCode),\n" +
            "TotalL6 as(\n" +
            "select e.DistrictName, e.DistrictCode, Count(*) [Total Visits With L6]\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and sm.VsectionId = :SubjectId and pv.orderno = 6\n" +
            "Group by e.DistrictName, e.DistrictCode\n" +
            ")\n" +
            "Select x.DistrictName, Case When x.Percentage = 100 then 100 else (100 - x.Percentage) end as Percentage\n" +
            "from\n" +
            "(Select dm.DistrictName,  Round(Cast(b.[Total Visits With L6] as float)/Cast(a.TotalCount as float) * 100,0) as Percentage\n" +
            "from\n" +
            "UDISE.DistrictMaster dm\n" +
            "left join TotalVisits a on dm.DistrictCode=a.DistrictCode\n" +
            "left join TotalL6 b on dm.DistrictCode=b.DistrictCode)x\n" +
            "Order by x.DistrictName";



    public static final String GET_CO_PARAMETER_REPORT_FOR_BLOCKS_OF_DISTRICT="With TotalVisits as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and e.DistrictCode = :DistrictId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode, sm.VsectionId),\n" +
            "TotalL6 as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode, Count (*) as TotalL6\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId= :ParameterId and pv.orderno = 6 and e.DistrictCode = :DistrictId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode)\n" +
            "Select x.DistrictName, x.BlockName, Case When x.Percentage = 100 then 100 else (100 - x.Percentage) end as Percentage\n" +
            "from\n" +
            "(select dm.DistrictName, bm.BlockName,Round(Cast(b.TotalL6 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "Left join TotalVisits a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode\n" +
            "left join TotalL6 b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode\n" +
            "where dm.DistrictCode = :DistrictId)x\n" +
            "Order by x.DistrictName, x.BlockName";


    public static final String GET_CO_PARAMETER_REPORT_FOR_CLUSTERS_OF_BLOCK="With TotalVisits as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and e.BlockCode = :BlockId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode, sm.VsectionId),\n" +
            "TotalL6 as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode, Count (*) as TotalL6\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and pv.orderno = 6 and e.BlockCode = :BlockId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode)\n" +
            "Select x.DistrictName, x.BlockName, x.ClusterName, \n" +
            "Case When x.Percentage = 100 then 100 else (100 - x.Percentage) end as Percentage" +
            "from\n" +
            "(select dm.DistrictName, bm.BlockName,cm.ClusterName,Round(Cast(b.TotalL6 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockCode\n" +
            "Left join TotalVisits a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode and cm.ClusterCode=a.ClusterCode\n" +
            "left join TotalL6 b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode and cm.ClusterCode=b.ClusterCode\n" +
            "where bm.BlockCode = :BlockId)x\n" +
            "Order by x.DistrictName, x.BlockName, x.ClusterName";






    public static final String GET_TEACHER_PERFORMANCE_STATE_REPORT_TOTAL_L4 = "With TotalVisits as(select e.DistrictName, e.DistrictCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and sm.VsectionId =:SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, sm.VsectionId),\n" +
            "TotalL4 as(select e.DistrictName, e.DistrictCode,Count (*) as TotalL4\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterValueId = :ParameterValueId\n" +
            "group by e.DistrictName, e.DistrictCode)\n" +
            "select dm.DistrictName,a.VsectionId as SubjectId ,Round(Cast(b.TotalL4 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "Left join TotalVisits a on dm.DistrictCode=a.DistrictCode\n" +
            "left join TotalL4 b on dm.DistrictCode=b.DistrictCode\n" +
            "Order by dm.DistrictName";



    public static final String GET_TEACHER_PERFORMANCE_DISTRICT_REPORT_TOTAL_L4 = "With TotalVisits as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and e.DistrictCode = :DistrictId and sm.VsectionId =:SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode, sm.VsectionId),\n" +
            "TotalL4 as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode, Count (*) as TotalL4\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterValueId = :ParameterValueId and e.DistrictCode = :DistrictId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode)\n" +
            "select dm.DistrictName, bm.BlockName,a.VsectionId as Subjectid, Round(Cast(b.TotalL4 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "left join TotalVisits a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode\n" +
            "left join TotalL4 b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode\n" +
            "where dm.DistrictCode = :DistrictId\n" +
            "Order by dm.DistrictName, bm.BlockName";

    public static final String GET_TEACHER_PERFORMANCE_BLOCK_REPORT_TOTAL_L4 = "With TotalVisits as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and e.BlockCode = :BlockId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode, sm.VsectionId),\n" +
            "TotalL4 as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode, Count (*) as TotalL4\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterValueId = :ParameterValueId and e.BlockCode = :BlockId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode)\n" +
            "select dm.DistrictName, bm.BlockName,cm.ClusterName,a.VsectionId as Subjectid, Round(Cast(b.TotalL4 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockCode\n" +
            "left join TotalVisits a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode and cm.ClusterCode=a.ClusterCode\n" +
            "left join TotalL4 b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode and cm.ClusterCode=b.ClusterCode\n" +
            "where bm.BlockCode = :BlockId\n" +
            "Order by dm.DistrictName, bm.BlockName, cm.ClusterName";


    public static final String GET_TEACHER_PERFORMANCE_TOTAL_L3_l4_STATE_REPORT = "With TotalVisits as(select e.DistrictName, e.DistrictCode, Count(*) as TotalCount\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and sm.VsectionId = :SubjectId\n" +
            "Group by e.DistrictName, e.DistrictCode),\n" +
            "TotalL3_L4 as(\n" +
            "select e.DistrictName, e.DistrictCode, Count(*) [Total Visits With L1 and L2]\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and sm.VsectionId = :SubjectId and pv.orderno in(3,4)\n" +
            "Group by e.DistrictName, e.DistrictCode\n" +
            ")\n" +
            "Select dm.DistrictName,  Round(Cast(b.[Total Visits With L1 and L2] as float)/Cast(a.TotalCount as float) * 100,0) as Percentage\n" +
            "from\n" +
            "UDISE.DistrictMaster dm\n" +
            "left join TotalVisits a on dm.DistrictCode=a.DistrictCode\n" +
            "left join TotalL3_L4 b on dm.DistrictCode=b.DistrictCode\n" +
            "order by dm.DistrictName";


    public static final String GET_TEACHER_PERFORMANCE_TOTAL_L3_l4_DISTRICT_REPORT = "With TotalVisits as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and e.DistrictCode = :DistrictId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode, sm.VsectionId),\n" +
            "TotalL4_L3 as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode, Count (*) as TotalL3_L4\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId= :ParameterId and pv.orderno in(3,4) and e.DistrictCode = :DistrictId and sm.VsectionId = :SubjectId \n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode)\n" +
            "select dm.DistrictName, bm.BlockName,Round(Cast(b.TotalL3_L4 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "Left join TotalVisits a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode\n" +
            "left join TotalL4_L3 b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode\n" +
            "where dm.DistrictCode = :DistrictId \n" +
            "Order by dm.DistrictName, bm.BlockName";


    public static final String GET_TEACHER_PERFORMANCE_TOTAL_L3_l4_BLOCK_REPORT = "With TotalVisits as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode,sm.VsectionId,Count (*) as TotalVisits\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and e.BlockCode = :BlockId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode, sm.VsectionId),\n" +
            "TotalL4 as(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode, Count (*) as TotalL4\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VParameterValues pv on pv.vparametervalueid=d.ParameterValueId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and d.ParameterId = :ParameterId and pv.orderno in(3,4) and e.BlockCode = :BlockId and sm.VsectionId = :SubjectId\n" +
            "group by e.DistrictName, e.DistrictCode, e.BlockName, e.BlockCode,e.ClusterName, e.ClusterCode)\n" +
            "select dm.DistrictName, bm.BlockName,cm.ClusterName, Round(Cast(b.TotalL4 as float)/Cast(a.TotalVisits as float) * 100,0) as Percentage\n" +
            "from UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "inner join UDISE.ClusterMaster cm on cm.BlockCode=bm.BlockCode\n" +
            "Left join TotalVisits a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode and cm.ClusterCode=a.ClusterCode\n" +
            "left join TotalL4 b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode and cm.ClusterCode=b.ClusterCode\n" +
            "where bm.BlockCode = :BlockId\n" +
            "Order by dm.DistrictName, bm.BlockName, cm.ClusterName";


    public static final String GET_WB_Usage_AND_Feedback_Rate_State_Report = "With Pivottable as(Select DistrictName, DistrictCode,[366],[335],\n" +
            "Case when [366] = 'Yes' and [335] = 'Yes' then '1' else '0' end as Bothyescount\n" +
            "from\n" +
            "(select e.DistrictName, e.DistrictCode,a.schoolallocationid, a.udisecode,d.ParameterId,\n" +
            "case when d.ParameterValueId = 936 then 'Yes'\n" +
            "\t when d.ParameterValueId = 1038 then 'Yes'\n" +
            "\t else 'No'\n" +
            "\t end as 'Response'\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and  sm.VsectionId= :SubjectId and d.ParameterId in(366, 335))as sorcetable\n" +
            "Pivot (\n" +
            "\t\tMax(Response)\n" +
            "\t\tFor ParameterId in([366],[335])\n" +
            ")as Pivottable),\n" +
            "Totalvisits as(Select a.DistrictName, a.DistrictCode,Count(*) as TotalVisits\n" +
            "from \n" +
            "Pivottable a\n" +
            "group by a.DistrictName,a.DistrictCode)\n" +
            "select dm.DistrictName, Round(Sum(Cast(a.Bothyescount as float))/ b.TotalVisits * 100,0) as Percentage\n" +
            "from \n" +
            "UDISE.DistrictMaster dm\n" +
            "left join Pivottable a on dm.DistrictCode=a.DistrictCode\n" +
            "left join Totalvisits b on dm.DistrictCode=b.DistrictCode\n" +
            "group by dm.DistrictName, b.TotalVisits\n" +
            "order by dm.DistrictName\n";


    public static final String GET_WB_Usage_AND_Feedback_Rate_District_Report = "With Pivottable as(Select DistrictName, DistrictCode,BlockName,BlockCode,[366],[335],\n" +
            "Case when [366] = 'Yes' and [335] = 'Yes' then '1' else '0' end as Bothyescount\n" +
            "from\n" +
            "(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,a.schoolallocationid, a.udisecode,d.ParameterId,\n" +
            "case when d.ParameterValueId = 936 then 'Yes'\n" +
            "\t when d.ParameterValueId = 1038 then 'Yes'\n" +
            "\t else 'No'\n" +
            "\t end as 'Response'\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and  sm.VsectionId= :SubjectId and d.ParameterId in(366, 335) and e.DistrictCode = :DistrictId \n" +
            ")as sorcetable\n" +
            "Pivot (\n" +
            "\t\tMax(Response)\n" +
            "\t\tFor ParameterId in([366],[335])\n" +
            ")as Pivottable),\n" +
            "Totalvisits as(Select a.DistrictName, a.DistrictCode,a.BlockName,a.BlockCode,Count(*) as TotalVisits\n" +
            "from \n" +
            "Pivottable a\n" +
            "group by a.DistrictName,a.DistrictCode,a.BlockName,a.BlockCode)\n" +
            "select dm.DistrictName, bm.BlockName,Round(Sum(Cast(a.Bothyescount as float))/ b.TotalVisits * 100,0) as Percentage\n" +
            "from \n" +
            "UDISE.DistrictMaster dm\n" +
            "inner join UDISE.BlockMaster bm on bm.DistrictCode=dm.DistrictCode\n" +
            "left join Pivottable a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode\n" +
            "left join Totalvisits b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode\n" +
            "where dm.DistrictCode = :DistrictId \n" +
            "group by dm.DistrictName, bm.BlockName, b.TotalVisits\n" +
            "order by dm.DistrictName, bm.BlockName";


    public  static final String GET_WB_Usage_AND_Feedback_Rate_Block_Report = "With Pivottable as(Select DistrictName, DistrictCode,BlockName,BlockCode,ClusterName,ClusterCode,[366],[335],\n" +
            "Case when [366] = 'Yes' and [335] = 'Yes' then '1' else '0' end as Bothyescount\n" +
            "from\n" +
            "(select e.DistrictName, e.DistrictCode,e.BlockName, e.BlockCode,e.ClusterName,e.ClusterCode,a.schoolallocationid, a.udisecode,d.ParameterId,\n" +
            "case when d.ParameterValueId = 936 then 'Yes'\n" +
            "\t when d.ParameterValueId = 1038 then 'Yes'\n" +
            "\t else 'No'\n" +
            "\t end as 'Response'\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref a\n" +
            "inner join Visit.VClassroomObservation b on b.SchoolAllocationId=a.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity c on c.ClassRoomObservationId=b.ClassRoomObservationId\n" +
            "inner join Visit.VClassroomObservationResponse d on d.ActivityId=c.ActivityId\n" +
            "inner join Visit.VparameterMaster pm on pm.vparameterid=d.ParameterId\n" +
            "inner join Visit.VsubSectionMaster ssm on ssm.VsubSectionId=pm.vsubsectionid\n" +
            "inner join Visit.VsectionMaster sm on sm.VsectionId=ssm.VsectionId\n" +
            "inner join School.dbo.SchoolMaster e on e.UDISECode=a.udisecode\n" +
            "where a.year= :Year and a.month= :Month and a.status= N'पूर्ण' and  sm.VsectionId= :SubjectId and d.ParameterId in(366, 335) and e.BlockCode = :BlockId \n" +
            ")as sorcetable\n" +
            "Pivot (\n" +
            "\t\tMax(Response)\n" +
            "\t\tFor ParameterId in([366],[335])\n" +
            ")as Pivottable),\n" +
            "Totalvisits as(Select a.DistrictName, a.DistrictCode,a.BlockName,a.BlockCode,a.ClusterName,a.ClusterCode,Count(*) as TotalVisits\n" +
            "from \n" +
            "Pivottable a\n" +
            "group by a.DistrictName,a.DistrictCode,a.BlockName,a.BlockCode,a.ClusterName,a.ClusterCode)\n" +
            "select dm.DistrictName, bm.BlockName, cm.ClusterName,Round(Sum(Cast(a.Bothyescount as float))/ b.TotalVisits * 100,0) as Percentage\n" +
            "from \n" +
            "UDISE.DistrictMaster dm\n" +
            "left join UDISE.BlockMaster bm on dm.DistrictCode=bm.DistrictCode\n" +
            "left join UDISE.ClusterMaster cm on bm.BlockCode=cm.BlockCode\n" +
            "left join Pivottable a on dm.DistrictCode=a.DistrictCode and bm.BlockCode=a.BlockCode and cm.ClusterCode=a.ClusterCode\n" +
            "left join Totalvisits b on dm.DistrictCode=b.DistrictCode and bm.BlockCode=b.BlockCode and cm.ClusterCode=b.ClusterCode\n" +
            "where bm.BlockCode =  :BlockId \n" +
            "group by dm.DistrictName, bm.BlockName, cm.ClusterName,b.TotalVisits\n" +
            "order by dm.DistrictName, bm.BlockName, cm.ClusterName";

    public static final String GET_MY_ALLOCATED_VISITS="select distinct x.schoolallocationid,x.allocateddate,x.createdby,x.createdon,x.status,N'सामान्य' as typename,x.udisecode,\n" +
            "  sm.SchoolName,sm.ClusterCode,sm.ClusterName,sm.BlockCode,sm.BlockName,sm.DistrictCode,sm.DistrictName,\n" +
            "  si.schoolinformationid,sp.spotassessmentid,DATEDIFF(Hour, x.starttime, x.endtime) as visittimetaken\n" +
            "  from Visit.VisitUserSchoolSelfAllocationXref x \n" +
            "  inner join UDISE.SchoolMaster sm on sm.UDISECode=x.udisecode\n" +
            "  inner join Visit.SchoolInformation si on si.schoolallocationid=x.schoolallocationid\n" +
            "  inner join SpotAss.Spotassesment sp on sp.schoolallocationid=x.schoolallocationid\n" +
            "  where x.month=:Month and x.year=:Year and x.roleid=:RoleId and x.employeecode=:EmpCode";
}
