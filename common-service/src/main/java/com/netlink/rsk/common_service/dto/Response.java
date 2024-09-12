package com.netlink.rsk.common_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    public Response(String status, String message, List<?> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private String status;
    private String message;
    private Object object;
    private List<?> data;

    public Response(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }
}