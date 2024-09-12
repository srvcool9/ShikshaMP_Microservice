package com.netlink.rsk.common_service.controller;

import com.netlink.rsk.common_service.constants.StaticResponse;
import com.netlink.rsk.common_service.dto.Response;
import com.netlink.rsk.common_service.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("Token")
public class TokenController {

    private static final Logger LOGGER = LogManager.getLogger(TokenController.class);

    @Autowired
    private JwtUtil jwtUtils;

    @GetMapping(value={"/GetToken/{userName}"})
    public ResponseEntity<Response> getToken(@PathVariable(value = "userName") String userName, HttpServletRequest req) throws Exception {
        try {
            LOGGER.info("Client Ip real {}",req.getRemoteAddr());
            LOGGER.info("Fetching token...");
            final String token = jwtUtils.generateToken(userName);
            List<String> tokenList = new ArrayList<>();
            tokenList.add(token);
            return new ResponseEntity<>(new Response(StaticResponse.SUCCESS_Status,StaticResponse.DATA_FETCHED,tokenList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
