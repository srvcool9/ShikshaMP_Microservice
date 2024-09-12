package com.netlink.rsk.common_service.constants;

public class StaticResponse {
    public static  final String REGISTERED_TRAINEE = "Registered Trainee";
    public static final String SAVING_MENTORING_DOMAIN_ORDER = "Saving Mentoring Domain Order....";
    public static final String SAVING_MENTORING_COMPETENCY_ORDER="Saving Mentoring Competency order....";
    public static final String MENTORING_COMP_MASTER_ORDER_SAVED_SUCCESSFULLY = "Mentoring competency master orders saved successfully!";
    public static final String MENTORING_DOMAIN_MASTER_ORDER_SAVED_SUCCESSFULLY = "Mentoring domain master orders saved successfully!";
    public static final String ERR = "Error";
    public  static final  String DATA_SAVED_SUCCESSFULLY = "Data Saved Successfully!";
    public static final String NO_DATA="No Data Found";
    public static final String NO_SAVE_DATA=" No data to save";
    public static final String DATA_FETCHED=" Data Fetched Successfully!";
    public static final String DELETE_FAILURE=" Cannot delete record as it is already in use";
    public static final String SUCCESS_Status="200";
    public static final int SUCCESS_Status_INT=200;
    public static final int FAILURE_Status_INT=400;
    public static final String FAILURE_Status="400";
    public static final String SESSION_EXPIRED_STATUS="401";
    public static final String RED_FLAG="300";
    public static final String ADD_SUCCESS_MESSAGE=" has been added successfully";
    public static final String ADD_FAIL_MESSAGE=" could not be added";
    public static final String UPDATE_SUCCESS_MESSAGE=" has been updated successfully";
    public static final String UPDATE_FAIL_MESSAGE=" could not be updated";
    public static final String FIELD_MISSING=" Some fields are missing";
    public static final String DELETE_SUCCESS="  has been deleted successfully ";
    public static final String DATA_EXISTS="Data already exists ";
    public final static int DEFAULT_LENGTH = 4;
    public final static int OTP_TIME = 10;
    public final static String OTP_EXPIRED = "Your OTP has been expired";
    public static final String DELETE_FAIL="Can't delete record! It is already in use.";
    public final static String OTP_VERIFICATION_SUCCESS = "OTP has been verified successfully";
    public final static String BANK_BRANCH_VERIFICATION_SUCCESS = "Bank branch has been verified successfully";
    public final static String BANK_BRANCH_VERIFICATION_FAILURE = "Bank branch not found";
    public final static String MOBILE_REGISTERED = "Email or mobile already exists";
    public final static String INVALID_FILE="Sorry! Filename contains invalid path sequence";
    public static final String FILE_ALREADY_EXIST = "File Name Already Exist Please Select Different One";
    public static final String FIELD_ALREADY_EXIST = "Field Name Already Exist Please Select Different One";
    public static final String YEAR_ALREADY_EXIST = " Year Already Exist Please Select Different One";
    public static final String NON_READABLE_FILE="Could not read the file!";
    public static final String FILE_SAVED="File has been uploaded successfully";
    public static final String ERROR="Something went wrong";
    public static final String FILE_SIZE_ERROR="Maximum file size exceeded! Please upload file lesser or equal to 2 MB";
    public static final String FILE_TYPE_ERROR="Only PDF file could be uploaded";
    public static final String TRAINING_DOC_PREFIX="Training_ID_";
    public static final String MEETING_IMG_PREFIX="Meeting_ID_";
    public static final String AGENDA_DOC_PREFIX="Agenda_ID_";
    public static final String SEPRATOR="_";
    public static final String ENUMERATOR_DOC_PREFIX="Enumerator_ID_";
    public static final String SELF_ASSESSMENT="Self Assessment";
    public static final String CLASSROOM_ASSESSMENT_SECTION="Cls_Assessment_Section";
    public static final String CLASSROOM_ASSESSMENT_SUBSECTION="Cls_Assessment_SubSection";
    public static final String VISIT_INFO_="VISIT_INFO_";
    public static final String SCHOOL_INFO="SCHOOL_INFO";
    public static final String PROBLEM_DOC_PREFIX="Problem_ID_";
    public static final String ESCALATED_PROBLEM_DOC_PREFIX="EscalatedProblem_ID_";
    public static final String UDISE_CODE_INVALID="Entered UDISE code does not falls under your area";

    public static final String STATUS_UPDATED_SUCCESS="Status has been updated successfully.";
    public static final String STATUS_UPDATE_FAIL="Status not updated!! Please retry.";
    public static final String LO_PROGRESSION_DISTRICT_REPORT="With Weeklo as(select d.DistrictName,x.schoolallocationid, x.udisecode, a.ClassRoomObservationId, b.SubjectId, b.SubSectionId, b.Week as ActualWeek, '2024-06-17' as Syllabus_Startdate, Cast(b.CreatedOn as date) as visitdate,\n" +
            "DATEDIFF(week,'2024-07-01',Cast(b.Createdon as date))+1 as IdealWeek\t\n" +
            "from visit.VisitUserSchoolSelfAllocationXref x \n" +
            "inner join Visit.VClassroomObservation a on a.SchoolAllocationId=x.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity b on b.ClassRoomObservationId=a.ClassRoomObservationId\n" +
            "inner join School.dbo.SchoolMaster c on c.UDISECode=x.udisecode\n" +
            "inner join UDISE.DistrictMaster d on d.DistrictCode=c.DistrictCode\n" +
            "where x.year = :Year and x.month = :Month and b.SubjectId = :SubjectId),\n" +
            "LO_Bucket as(\n" +
            "select a.DistrictName,\n" +
            "case \n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -8 AND -7 then 'minusEightToMinusSeven'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -6 AND -5 then 'minusSixToMinusFive'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -4 AND -3 then 'minusFourToMinusThree'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -2 AND -1 then 'minusTwoToMinusOne'\n" +
            "\twhen a.ActualWeek-a.IdealWeek= 0 then 'zero'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 1 AND 2 then 'oneToTwo'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 3 AND 4 then 'threeToFour'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 5 AND 6 then 'fiveToSix'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 7 AND 8 then 'sevenToEight' \n" +
            "\tend as WeekRange\n" +
            "from Weeklo as a),\n" +
            "Total_LOs as (\n" +
            "Select DistrictName, Count(*) as TotalLOs \n" +
            "from LO_Bucket\n" +
            "group by DistrictName\n" +
            ")\n" +
            "select * from(\n" +
            "Select z.DistrictName, z.WeekRange, Concat(z.Buckettotal * 100/z.TotalLOs, '%') as Percentage from\n" +
            "(select a.DistrictName, a.WeekRange, Count(*) as Buckettotal , b.TotalLOs\n" +
            "from LO_Bucket as a\n" +
            "inner join Total_LOs as b on b.DistrictName=a.DistrictName\n" +
            "group by a.DistrictName, a.WeekRange, b.TotalLOs)z\n" +
            ")as Sourcetable\n" +
            "Pivot (\n" +
            "\t\tMAX(Percentage)\n" +
            "\t\tFor WeekRange in([minusEightToMinusSeven],[minusSixToMinusFive],[minusFourToMinusThree],[minusTwoToMinusOne],[zero],[oneToTwo],[threeToFour],[fiveToSix],[sevenToEight])\n" +
            ")as Pivottable\n" +
            "Order by DistrictName";

    public static final String LO_PROGRESSION_BLOCK_REPORT="With Weeklo as(select c.DistrictName,c.BlockName,x.schoolallocationid, x.udisecode, a.ClassRoomObservationId, b.SubjectId, b.SubSectionId, b.Week as ActualWeek, '2024-06-17' as Syllabus_Startdate, Cast(b.CreatedOn as date) as visitdate,\n" +
            "DATEDIFF(week,'2024-07-01',Cast(b.Createdon as date))+1 as IdealWeek\t\n" +
            "from visit.VisitUserSchoolSelfAllocationXref x \n" +
            "inner join Visit.VClassroomObservation a on a.SchoolAllocationId=x.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity b on b.ClassRoomObservationId=a.ClassRoomObservationId\n" +
            "inner join School.dbo.SchoolMaster c on c.UDISECode=x.udisecode\n" +
            "where x.year = :Year and x.month = :Month and b.SubjectId = :SubjectId and c.DistrictCode= :AreaId),\n" +
            "LO_Bucket as(\n" +
            "select a.DistrictName, a.BlockName,\n" +
            "case \n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -8 AND -7 then 'minusEightToMinusSeven'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -6 AND -5 then 'minusSixToMinusFive'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -4 AND -3 then 'minusFourToMinusThree'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -2 AND -1 then 'minusTwoToMinusOne'\n" +
            "\twhen a.ActualWeek-a.IdealWeek= 0 then 'zero'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 1 AND 2 then 'oneToTwo'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 3 AND 4 then 'threeToFour'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 5 AND 6 then 'fiveToSix'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 7 AND 8 then 'sevenToEight' \n" +
            "\tend as WeekRange\n" +
            "from Weeklo as a),\n" +
            "Total_LOs as (\n" +
            "Select DistrictName, BlockName,Count(*) as TotalLOs \n" +
            "from LO_Bucket\n" +
            "group by DistrictName, BlockName\n" +
            ")\n" +
            "select * from(\n" +
            "Select z.DistrictName, z.BlockName,z.WeekRange, Concat(z.Buckettotal * 100/z.TotalLOs, '%') as Percentage from\n" +
            "(select a.DistrictName, a.BlockName,a.WeekRange, Count(*) as Buckettotal , b.TotalLOs\n" +
            "from LO_Bucket as a\n" +
            "inner join Total_LOs as b on b.DistrictName=a.DistrictName and b.BlockName=a.BlockName\n" +
            "group by a.DistrictName, a.BlockName, a.WeekRange, b.TotalLOs)z\n" +
            ")as Sourcetable\n" +
            "Pivot (\n" +
            "\t\tMAX(Percentage)\n" +
            "\t\tFor WeekRange in([minusEightToMinusSeven],[minusSixToMinusFive],[minusFourToMinusThree],[minusTwoToMinusOne],[zero],[oneToTwo],[threeToFour],[fiveToSix],[sevenToEight])\n" +
            ")as Pivottable\n" +
            "Order by DistrictName, BlockName";

    public static final String LO_PROGRESSION_CLUSTER_REPORT="With Weeklo as(select c.DistrictName,c.BlockName,C.ClusterName,x.schoolallocationid, x.udisecode, a.ClassRoomObservationId, b.SubjectId, b.SubSectionId, b.Week as ActualWeek, '2024-06-17' as Syllabus_Startdate, Cast(b.CreatedOn as date) as visitdate,\n" +
            "DATEDIFF(week,'2024-07-01',Cast(b.Createdon as date))+1 as IdealWeek\t\n" +
            "from visit.VisitUserSchoolSelfAllocationXref x \n" +
            "inner join Visit.VClassroomObservation a on a.SchoolAllocationId=x.schoolallocationid\n" +
            "inner join Visit.VisitClassroomObservationActivity b on b.ClassRoomObservationId=a.ClassRoomObservationId\n" +
            "inner join School.dbo.SchoolMaster c on c.UDISECode=x.udisecode\n" +
            "where x.year = :Year and x.month = :Month and b.SubjectId = :SubjectId and c.BlockCode= :AreaId),\n" +
            "LO_Bucket as(\n" +
            "select a.DistrictName, a.BlockName, a.ClusterName,\n" +
            "case \n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -8 AND -7 then 'minusEightToMinusSeven'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -6 AND -5 then 'minusSixToMinusFive'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -4 AND -3 then 'minusFourToMinusThree'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN -2 AND -1 then 'minusTwoToMinusOne'\n" +
            "\twhen a.ActualWeek-a.IdealWeek= 0 then 'zero'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 1 AND 2 then 'oneToTwo'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 3 AND 4 then 'threeToFour'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 5 AND 6 then 'fiveToSix'\n" +
            "\twhen a.ActualWeek-a.IdealWeek BETWEEN 7 AND 8 then 'sevenToEight' \n" +
            "\tend as WeekRange\n" +
            "from Weeklo as a),\n" +
            "Total_LOs as (\n" +
            "Select DistrictName, BlockName,ClusterName,Count(*) as TotalLOs \n" +
            "from LO_Bucket\n" +
            "group by DistrictName, BlockName,ClusterName\n" +
            ")\n" +
            "select * from(\n" +
            "Select z.DistrictName, z.BlockName,z.ClusterName,z.WeekRange, Concat(z.Buckettotal * 100/z.TotalLOs, '%') as Percentage from\n" +
            "(select a.DistrictName, a.BlockName,a.ClusterName,a.WeekRange, Count(*) as Buckettotal , b.TotalLOs\n" +
            "from LO_Bucket as a\n" +
            "inner join Total_LOs as b on b.DistrictName=a.DistrictName and b.BlockName=a.BlockName and b.ClusterName=a.ClusterName\n" +
            "group by a.DistrictName, a.BlockName, a.ClusterName,a.WeekRange, b.TotalLOs)z\n" +
            ")as Sourcetable\n" +
            "Pivot (\n" +
            "\t\tMAX(Percentage)\n" +
            "\t\tFor WeekRange in([minusEightToMinusSeven],[minusSixToMinusFive],[minusFourToMinusThree],[minusTwoToMinusOne],[zero],[oneToTwo],[threeToFour],[fiveToSix],[sevenToEight])\n" +
            ")as Pivottable\n" +
            "Order by DistrictName, BlockName, ClusterName";
}


