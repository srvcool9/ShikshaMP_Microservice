package com.netlink.rsk.common_service.serviceimpl;

import com.netlink.rsk.common_service.dto.Response;
import com.netlink.rsk.common_service.model.RoleMaster;
import com.netlink.rsk.common_service.repository.RoleMasterRepository;
import com.netlink.rsk.common_service.service.IRoleMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class RoleMasterService implements IRoleMaster {

    @Autowired
    private RoleMasterRepository roleMasterRepository;




    @Override
    public Response getAllRoleMaster() {
       List<RoleMaster> roleMasterList = new ArrayList<>();
        roleMasterList = roleMasterRepository.findAll();
        if(roleMasterList.isEmpty()==true){
            return  new Response("Success","No Data",null);
        }
        return  new Response("Success","No Data",roleMasterList);
    }
}


