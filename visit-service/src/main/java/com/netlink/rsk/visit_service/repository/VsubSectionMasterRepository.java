package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.VSubSectionMapper;
import com.netlink.rsk.visit_service.mapper.offline.SubSectionMapper;
import com.netlink.rsk.visit_service.model.VsubSectionMaster;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VsubSectionMasterRepository extends JpaRepository<VsubSectionMaster, Long> {
    List<VsubSectionMaster> findByVsectionmaster_Vsectionid(Long vsectionid);

    @Query(value = VisitQueries.GET_SUBSECTIONID_BY_SECTIONID,nativeQuery = true)
    List<Integer> findSubsectionIds(int sectionId);

    @Query(value = VisitQueries.GET_ALL_SUBSECTIONS,nativeQuery = true)
    public List<SubSectionMapper> getAllSubSections();

    @Query(value = VisitQueries.GET_ALL_SUBSECTIONS_BY_DATE_FILTER,nativeQuery = true)
    public List<SubSectionMapper> getAllSubSectionsByDateFilter(@Param("LastUpdateDate")String date);

    @Query(value = VisitQueries.GET_SECTION_ID_BY_SUBSECTION_ID,nativeQuery = true)
    public Long getSectionIdBySubSectionId(@Param("SubSection")Long subSectionId);

    @Query(value = VisitQueries.GET_SUB_SECTION_ID_AND_NAME_BY_SECTION_ID,nativeQuery = true)
    List<VSubSectionMapper> getSubSectionBySectionID(@Param("sectionId")Long sectionId);

}