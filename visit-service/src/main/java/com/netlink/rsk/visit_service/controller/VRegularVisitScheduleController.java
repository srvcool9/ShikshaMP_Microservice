package com.netlink.rsk.visit_service.controller;

import com.netlink.rsk.visit_service.dto.Response;
import com.netlink.rsk.visit_service.service.IVRegularVisitSchedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vregularvisitschedule")
public class VRegularVisitScheduleController {

      private final IVRegularVisitSchedule vRegularVisitScheduleService;

      VRegularVisitScheduleController(IVRegularVisitSchedule vRegularVisitScheduleService){
          this.vRegularVisitScheduleService=vRegularVisitScheduleService;
      }

    @GetMapping
    public ResponseEntity<Response> getAllVRegularVisitSchedule() {
        try {
            Response response= vRegularVisitScheduleService.getAllRegularVisitSchedules();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
