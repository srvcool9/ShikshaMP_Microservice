package com.netlink.rsk.common_service.controller;

import com.netlink.rsk.common_service.dto.Response;
import com.netlink.rsk.common_service.service.IRoleMaster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("rolemaster")
public class RoleMasterController {

    @Autowired
    private IRoleMaster iRoleMaster;


    @GetMapping
    public ResponseEntity<Response> getAllRoles() throws Exception {
        try {
            Response response = iRoleMaster.getAllRoleMaster();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
