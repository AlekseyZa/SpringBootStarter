package com.alekseyz.testtask.httploggerspringbootstarter.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.INFO;

@Component
public class HttpLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(HttpLoggingInterceptor.class);
    private LocalDateTime requestReceiveTime;
    private LocalDateTime responseSendTime;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        requestReceiveTime = LocalDateTime.now();
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) {
        responseSendTime = LocalDateTime.now();
        LogView logView = new LogView(
                LocalDateTime.now(),
                request.getMethod(),
                String.valueOf(request.getRequestURL()),
                request.getQueryString(),
                request.getContentType(),
                response.getContentType(),
                String.valueOf(response.getStatus()),
                getRequestHeaders(request),
                getResponseHeaders(response),
                requestReceiveTime,
                responseSendTime,
                Duration.between(requestReceiveTime, responseSendTime).toMillis());

        logger.log(INFO, logView.toString());
        if (ex != null) {
            logger.log(ERROR, "ERROR " + LocalDateTime.now() + " Произошла ошибка. Запрос: " +
                    request.getMethod() + ", URL: " + request.getRequestURL() + ".");
        }


    }

    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        response.getHeaderNames().forEach(headerName ->
                headers.put(headerName, response.getHeader(headerName)));
        return headers;
    }

    private Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            headers.put(key, value);
        }
        return headers;
    }
}
