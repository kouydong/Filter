package com.example.filter.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccessLog {

    private String remoteHost;
    private int remotePort;
    private String requestUri;
    private String protocol;
    private String method;
    private String contentType;
    private String characterEncoding;
    private String headerNames;
    private String requestAt;

}



