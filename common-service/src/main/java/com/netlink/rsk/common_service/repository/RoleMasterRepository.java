package com.netlink.rsk.common_service.repository;

import com.netlink.rsk.common_service.model.RoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Long>, JpaSpecificationExecutor<RoleMaster> {
}
