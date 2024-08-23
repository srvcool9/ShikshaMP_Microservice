package com.netlink.rsk.visit_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
