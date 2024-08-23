package com.netlink.rsk.visit_service.serviceimpl;

import com.netlink.rsk.visit_service.constants.StaticConstants;
import com.netlink.rsk.visit_service.dto.Response;
import com.netlink.rsk.visit_service.exception.NoDataFoundException;
import com.netlink.rsk.visit_service.model.VRegularVisitSchedule;
import com.netlink.rsk.visit_service.repository.VRegularVisitScheduleRepository;
import com.netlink.rsk.visit_service.service.IVRegularVisitSchedule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VRegularVisitScheduleService implements IVRegularVisitSchedule {

    @Value("${common-service}")
    private String commonService;

    private final VRegularVisitScheduleRepository vRegularVisitScheduleRepository;
    private final RestTemplate restTemplate;

    VRegularVisitScheduleService(VRegularVisitScheduleRepository regularVisitScheduleRepository,RestTemplate restTemplate){
        this.vRegularVisitScheduleRepository=regularVisitScheduleRepository;
        this.restTemplate=restTemplate;
    }



    @Override
    public Response getAllRegularVisitSchedules() {
        String url = StaticConstants.URL + commonService+ StaticConstants.GET_ALL_ROLES ;
        Response response = restTemplate.getForObject(url ,Response.class);
        List<VRegularVisitSchedule> scheduleList=vRegularVisitScheduleRepository.findAll();
        if(scheduleList.isEmpty()){
            throw new NoDataFoundException("No data found...");
        }else {
           scheduleList.get(0).setList(response.getData());
            return new Response("200","Data Fetched Successfully",scheduleList);
        }
    }
}
