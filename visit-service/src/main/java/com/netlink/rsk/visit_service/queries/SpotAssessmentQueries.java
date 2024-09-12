package com.netlink.rsk.visit_service.queries;

public class SpotAssessmentQueries {

    public static final String GET_COMPETENCYS="select distinct cm.Competencymaster,cm.Competencyname,dm.Domainid,dm.Domainname,dm.ClassId,sm.Subjectid,sm.Subjectname,cm.Isactive,cm.CreatedOn\\n\" +\n" +
            "            \"from SpotAss.Competencymaster cm\\n\" +\n" +
            "            \"inner join SpotAss.Domainmaster dm on dm.Domainid=cm.Domainid\\n\" +\n" +
            "            \"inner join SpotAss.Subjectmaster sm on sm.Subjectid=dm.Subjectid\\n\" +\n" +
            "            \"Order By cm.CreatedOn";
    public static final String UPDATE_COMPETENCY_STATUS="Update SpotAss.Competencymaster\n" +
            "SET Isactive=:Status\n" +
            "where Competencymaster=:CompetencyId";
    public static final String GET_ALL_COMPETENCY="select cmp.*,(select count(*) from SpotAss.CompetencyMaster) AS TotalRows \n" +
            "from Spotass.Competencymaster cmp";

    public static final String GET_COMPETENCYS_BY_DATE="select c.*,(select count(*) from SpotAss.CompetencyMaster) AS TotalRows\n" +
            "from Spotass.Competencymaster c\n" +
            "where cast(createdon as date) >= :LastUpdatedDate";
    public static final String GET_DOMAIN_COMPETENCY_MAPPING_DATA="select distinct d.Domainid,d.Domainname,cmp.Competencymaster,cmp.CompetencyName,qm.Weekrangeid,qp.QuestionPickerId,qp.NoQuestion\n" +
            "from SpotAss.Domainmaster d\n" +
            "inner join SpotAss.CompetencyMaster cmp on cmp.Domainid=d.Domainid\n" +
            "inner join SpotAss.Questionmaster qm on qm.Competencyid=cmp.Competencymaster\n" +
            "left join (\n" +
            "  select distinct qp.CompetencyId,qp.QuestionPickerId,qp.NoQuestion,wm.WeekId\n" +
            "  from SpotAss.QuestionPicker qp\n" +
            "  inner join SpotAss.WeekDetail wd on wd.WeekId=qp.WeekId\n" +
            "  inner join SpotAss.WeekMaster wm on wm.WeekId=wd.WeekId\n" +
            ")qp on qp.CompetencyId=cmp.Competencymaster and qp.WeekId=qm.Weekrangeid\n" +
            "where d.ClassId=:ClassId and d.Subjectid=:SubjectId and qm.Weekrangeid=:WeekRangeId";

    public static final String GET_SPOT_ASSESSMENT_INFO_COUNT="SELECT count(*) FROM SpotAss.Spotassesmentinfo where spotassessmentid=?2 and classid=?1 and status='Completed' ";

    public static final String GET_STUDENT_RESPONSE_BY_SPOT_SAMAGRA= "select spi.spotassessmentid,std.samagraid,resp.questionid,resp.answer,qm.Competencyid \n" +
            "from SpotAss.Spotassesment sp\n" +
            "inner join SpotAss.Spotassesmentinfo spi\n" +
            "on spi.spotassessmentid=sp.spotassessmentid\n" +
            "inner join SpotAss.Spotassesmentstudentinfo std\n" +
            "on std.spotassessmentinfoid=spi.spotassessmentinfoid\n" +
            "inner join SpotAss.Spotassesmenttestresponse resp\n" +
            "on resp.spotassesmentstudentinfoid=std.spotassesmentstudentinfoid\n" +
            "inner join SpotAss.Questionmaster qm on qm.Questionid= resp.questionid\n"+
            "where std.samagraid=:SamagraId And sp.spotassessmentid=:SpotAssessmentId And SPI.subjectid=:SubjectId";
    public static final String GET_UDISE_LO_ASSESSMENT_DATE="select s from Spotassesment s where  s.UDISECode.udiseCode=?1  and s.assesmentdate=?2 ";
    public static final String UPDATE_STATUS_BY_SPOT_ASSESSMENT_ID="update Spotassesment s set s.status = ?1 where s.spotassessmentid = ?2";

    public static final String GET_SPOT_ASSESSMENT_BY_EMPLOYEE_ROLE_UDISE_LO_DATE="select top (1) * from [SpotAss].[Spotassesment] s where s.employeecode = :employeecode and s.roleid = :roleid and s.UDISECode = :udiseCode  and cast(s.assesmentdate as Date) = cast(getdate() as Date) order by s.spotassessmentid DESC ";
    public static final String GET_TOTAL_SPOTS_FOR_MONTH_YEAR="select count(a.schoolallocationid) as TotalSpotAssessments\n" +
            "from(\n" +
            "select distinct x.schoolallocationid,sd.samagraid\n" +
            "from Visit.VisitUserSchoolSelfAllocationXref x\n" +
            "inner join SpotAss.Spotassesment sp on x.schoolallocationid=sp.schoolallocationid\n" +
            "inner join SpotAss.Spotassesmentinfo i on i.spotassessmentid=sp.spotassessmentid\n" +
            "inner join SpotAss.Spotassesmentstudentinfo sd on sd.spotassessmentinfoid=i.spotassessmentinfoid\n" +
            "where x.status=N'पूर्ण' AND x.month=:Month and x.year=:Year\n" +
            ")a";

    public static final String GET_SPOT_STUDENT_INFO_BY_SAMAGRA_INFO_ID="select count(*) from SpotAss.Spotassesmentstudentinfo s " +
            "where s.samagraid = ?1 and s.spotassessmentinfoid = ?2 and cast(s.createdon as Date)=cast(getDate() as Date)";

    public static final String GET_SPOT_STUDENT_DATA="select sp.spotassessmentid,stud.samagraid,spi.subjectid\n" +
            "from SpotAss.Spotassesment sp\n" +
            "inner join SpotAss.Spotassesmentinfo spi \n" +
            "on spi.spotassessmentid=sp.spotassessmentid\n" +
            "inner join SpotAss.Spotassesmentstudentinfo stud\n" +
            "on stud.spotassessmentinfoid=spi.spotassessmentinfoid\n" +
            "where sp.spotassessmentid=:SpotAssessmentId AND spi.subjectid=:SubjectId ";

    public static final String GET_STUDENT_DATA_COMPETENCY_WISE="select sp.spotassessmentid,CAST (stud.samagraid as int) as samagraid,spi.subjectid\n" +
            "from SpotAss.Spotassesment sp\n" +
            "inner join SpotAss.Spotassesmentinfo spi \n" +
            "on spi.spotassessmentid=sp.spotassessmentid\n" +
            "inner join SpotAss.Spotassesmentstudentinfo stud\n" +
            "on stud.spotassessmentinfoid=spi.spotassessmentinfoid\n" +
            "inner join SpotAss.Spotassesmenttestresponse resp\n" +
            "on resp.spotassesmentstudentinfoid=stud.spotassesmentstudentinfoid\n" +
            "inner join SpotAss.Questionmaster qm\n" +
            "on qm.Questionid=resp.questionid\n" +
            "inner join SpotAss.Competencymaster cm\n" +
            "on cm.Competencymaster=qm.Competencyid\n" +
            "where sp.spotassessmentid=:SpotAssessmentId AND spi.subjectid=:SubjectId AND cm.Competencymaster=:CompetencyId \n" +
            "Group By sp.spotassessmentid,stud.samagraid,spi.subjectid ";

    public static final String GET_ANSWER_SMCQ_ID="SELECT [Answer] FROM [ItemBank].[SpotAssmentSMCQ] where [SMCQId] = :smcqId";

    public static final String GET_ALL_COMPETENCY_LIST= "select distinct dm.Domainid,dm.Domainname,cm.Competencymaster,cm.Competencyname,dm.ClassId,dm.Subjectid\n" +
            "              from  SpotAss.Domainmaster dm\n" +
            "              inner join SpotAss.Competencymaster cm\n" +
            "              on cm.Domainid=dm.Domainid";
    public static final String GET_ALL_DOMAIN_LIST="select distinct dm.Domainid,dm.Domainname,dm.ClassId,s.SubjectId,s.SubjectName,dm.Isactive,dm.CreatedOn\n" +
            "from SpotAss.Domainmaster dm\n" +
            "inner join SpotAss.Subjectmaster s on dm.Subjectid=s.Subjectid\n" +
            "Order By dm.Createdon";
    public static final String GET_ALL_DOMAINS= "select dm.*,(select count(*) from SpotAss.Domainmaster) AS TotalRows \n" +
            "from SpotAss.Domainmaster dm";
    public static final String UPDATE_STATUS_FOR_DOMAIN="Update SpotAss.Domainmaster\n" +
            "SET Isactive=:Status\n" +
            "Where Domainid=:DomainId";

    public static final String GET_RANDOM_QUESTIONS_BY_WEEK_SUBJECT_CLASS= "SELECT TOP (5)  qm.[Questionid]\n" +
            "      ,qm.[Classid]\n" +
            "      ,qm.[Subjectid]\n" +
            "      ,qm.[Weekrangeid]\n" +
            "      ,qm.[Domainid]\n" +
            "      ,qm.[Competencyid]\n" +
            "      ,qm.[Imagepath]\n" +
            "      ,qm.[QuestionName]\n" +
            "      ,qm.[OptionA]\n" +
            "      ,qm.[OptionB]\n" +
            "      ,qm.[OptionC]\n" +
            "      ,qm.[Createdby]\n" +
            "      ,qm.[Createdat]\n" +
            "      ,qm.[Isactive]\n" +
            "      ,qm.[ImageName]\n "+
            "  FROM [RSKAcademic].[SpotAss].[Questionmaster] qm\n" +
            "  Join [RSKAcademic].[SpotAss].[WeekMaster] wm on wm.Weekid=qm.Weekrangeid\n" +
            "  where qm.Classid=:classId And qm.Subjectid=:subjectId And   (wm.Weekstrrange<=:weekid AND wm.Weekendrange>=:weekid) \n" +
            "  order by RAND()  ";

    public static final String DEACTIVATE_QUESTIONS="Update SpotAss.QuestionMaster\n" +
            "Set IsActive='False'\n" +
            "where QuestionId=:questionId";
    public static final String GET_ALL_QUESTIONS_DATE="select *\n" +
            "from SpotAss.Questionmaster\n" +
            "where cast(Createdat as date) >= :LastUpdatedDate";

    public static final String GET_SPOT_RANDOM_QUESTIONS="SELECT TOP (5) sm.[SMCQId]\n" +
            "      ,sm.[SubjectId]\n" +
            "      ,sm.[ChapterId]\n" +
            "      ,sm.[LOId]\n" +
            "      ,sm.[DLId]\n" +
            "      ,sm.[Skillid]\n" +
            "      ,sm.[Statusid] \n  " +
            "      ,sm.[QImage]\n" +
            "      ,sm.[Question]\n" +
            "      ,sm.[OptionA]\n" +
            "      ,sm.[Reasoninga]\n  "+
            "      ,sm.[OptionB]\n" +
            "      ,sm.[Reasoningb]\n  "+
            "      ,sm.[OptionC]\n" +
            "      ,sm.[Reasoningc]\n  "+
            "      ,sm.[OptionD]\n" +
            "      ,sm.[Reasoningd]\n  "+
            "      ,sm.[Answer]\n" +
            "      ,sm.[IPAddress] \n" +
            "      ,sm.[Createdon] \n" +
            "      ,sm.[weekid] \n                " +
            "  FROM [RSKAcademic].[ItemBank].[SpotAssmentSMCQ] sm \n" +
            "  Join [RSKAcademic].[ItemBank].[WeekMaster] wm on wm.weekid=sm.weekid \n "+
            "  where sm.SubjectId=:subjectId And (wm.weekstrrange<=:weekid AND wm.weekendrange>=:weekid) \n" +
            "  order by RAND() ";

    public static final String UPDATE_SUBJECT_STATUS="Update SpotAss.Subjectmaster\n" +
            "Set Isactive=:Status\n" +
            "Where Subjectid=:SubjectId";
    public static final String GET_ALL_WEEK_DETAIL="select wd.*,(select count(*) from SpotAss.WeekDetail) AS TotalRows \n" +
            "from SpotAss.WeekDetail wd";

    public  static  final  String GET_RANDOM_QUESTIONS = "SELECT TOP (5) [SMCQId]\n" +
            "      ,[TeacherId]\n" +
            "      ,[GradeId]\n" +
            "      ,[SubjectId]\n" +
            "      ,[ChapterId]\n" +
            "      ,[LOId]\n" +
            "      ,[DLId]\n" +
            "      ,[SkillId]\n" +
            "      ,[Question]\n" +
            "      ,[OptionA]\n" +
            "      ,[OptionB]\n" +
            "      ,[OptionC]\n" +
            "      ,[OptionD]\n" +
            "      ,[Answer]\n" +
            "  FROM [RSKAcademic].[ItemBank].[SMCQ]  \n" +
            "  where SubjectId=:subjectId AND GradeId=:gradeId AND LOId=:loId \n" +
            "  order by RAND() ";


    public static final String FIND_BY_STUDENT_ID = "SELECT  [TestId]\n" +
            "      ,[QuestionId]\n" +
            "      ,[Answer]\n" +
            "      ,[SpotAssessmentId]\n" +
            "      ,[SamagraId]\n" +
            "      ,[CreatedBy]\n" +
            "      ,[CreatedOn]\n" +
            "      ,[UpdatedBy]\n" +
            "      ,[UpdatedOn]\n" +
            "  FROM [RSKAcademic].[SpotAss].[TestResponse]\n" +
            "  where SamagraId= :samagraId";

    public  static  final  String FIND_DATA_BY_SPOT_ASSESSMENT = "SELECT  [TestId]\n" +
            "      ,[QuestionId]\n" +
            "      ,[Answer]\n" +
            "      ,[SpotAssessmentId]\n" +
            "      ,[SamagraId]\n" +
            "      ,[CreatedBy]\n" +
            "      ,[CreatedOn]\n" +
            "      ,[UpdatedBy]\n" +
            "      ,[UpdatedOn]\n" +
            "  FROM [RSKAcademic].[SpotAss].[TestResponse]\n" +
            "  where [SpotAssessmentId]= :spotAssessmentId";




}
