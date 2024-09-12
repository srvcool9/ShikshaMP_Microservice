package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.model.VisitClassRoomObservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitClassRoomObservationRepository extends JpaRepository<VisitClassRoomObservation, Long>, JpaSpecificationExecutor<VisitClassRoomObservation> {
   long deleteBySchoolAllocation_Schoolallocationid(Long schoolallocationid);

   boolean existsBySchoolAllocation_Schoolallocationid(Long schoolallocationid);
   public VisitClassRoomObservation findBySchoolAllocation_Schoolallocationid(Long schoolallocationid);

   VisitClassRoomObservation findFirstBySchoolAllocation_Schoolallocationid(Long schoolallocationid);

}
