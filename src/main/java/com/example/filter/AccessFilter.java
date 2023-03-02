package com.example.filter;

import ch.qos.logback.classic.Logger;


import com.example.filter.dto.AccessLog;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

public class AccessFilter implements Filter {
    private final Logger log = (Logger) LoggerFactory.getLogger(AccessFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 시작 시 필요한 메서드를 정의한다.
        log.info("필터를 시작합니다.");
    }

    @Override
    public void destroy() {
        // 필터 종료 시 필요한 메서드를 정의한다.
        log.info("필터를 종료합니다.");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 요청 시 처리할 내용을 정의한다.
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 헤더명 추출
        Iterator<String> headers = httpServletRequest.getHeaderNames().asIterator();
        StringBuilder headersNames = new StringBuilder();

        while(headers.hasNext()) {
            headersNames.append(headers.next() + " ");
        }

        AccessLog accessLog = AccessLog.builder()
                .remoteHost(httpServletRequest.getRemoteHost())
                .remotePort(httpServletRequest.getRemotePort())
                .requestUri(httpServletRequest.getRequestURI())
                .protocol(httpServletRequest.getProtocol())
                .method(httpServletRequest.getMethod())
                .contentType(httpServletRequest.getContentType())
                .characterEncoding(httpServletRequest.getCharacterEncoding())
                .headerNames(headersNames.toString())
                .requestAt(LocalDateTime.now().toString())
                .build();

        log.info(accessLog.toString());

        chain.doFilter(request, response);
    }
}
