package com.alekseyz.testtask.httploggerspringbootstarter.logging;


import java.time.LocalDateTime;
import java.util.Map;

public class LogView {
    LocalDateTime logTime;
    String methodName;
    String requestURL;
    String requestQuery;
    String requestContentType;
    String responseContentType;
    String responseStatus;
    Map<String, String> requestHeaders;
    Map<String, String> responseHeaders;
    LocalDateTime requestReceiveTime;
    LocalDateTime responceSendTime;
    Long processingtime;

    public LogView() {
    }

    public LogView(LocalDateTime logTime, String methodName, String requestURL, String requestQuery,
                   String requestContentType, String responseContentType, String responseStatus,
                   Map<String, String> requestHeaders, Map<String, String> responseHeaders,
                   LocalDateTime requestReceiveTime, LocalDateTime responceSendTime, Long processingtime) {
        this.logTime = logTime;
        this.methodName = methodName;
        this.requestURL = requestURL;
        this.requestQuery = requestQuery;
        this.requestContentType = requestContentType;
        this.responseContentType = responseContentType;
        this.responseStatus = responseStatus;
        this.requestHeaders = requestHeaders;
        this.responseHeaders = responseHeaders;
        this.requestReceiveTime = requestReceiveTime;
        this.responceSendTime = responceSendTime;
        this.processingtime = processingtime;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getRequestQuery() {
        return requestQuery;
    }

    public void setRequestQuery(String requestQuery) {
        this.requestQuery = requestQuery;
    }

    public String getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(String requestContentType) {
        this.requestContentType = requestContentType;
    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType) {
        this.responseContentType = responseContentType;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public LocalDateTime getRequestReceiveTime() {
        return requestReceiveTime;
    }

    public void setRequestReceiveTime(LocalDateTime requestReceiveTime) {
        this.requestReceiveTime = requestReceiveTime;
    }

    public LocalDateTime getResponceSendTime() {
        return responceSendTime;
    }

    public void setResponceSendTime(LocalDateTime responceSendTime) {
        this.responceSendTime = responceSendTime;
    }

    public Long getProcessingtime() {
        return processingtime;
    }

    public void setProcessingtime(Long processingtime) {
        this.processingtime = processingtime;
    }

    @Override
    public String toString() {
        return logTime + "" + "\n" +
                "HTTP LOG SET START" + "\n" +
                "Тип запроса:            " + methodName + "\n" +
                "URL:                    " + requestURL + "\n" +
                "Параметры запроса:      " + requestQuery + "\n" +
                "ContentType запроса:    " + requestContentType + "\n" +
                "ContentType ответа:     " + responseContentType + "\n" +
                "Код статуса:            " + responseStatus + "\n" +
                "Заголовки запроса:      " + requestHeaders + "\n" +
                "Заголовки ответа:       " + responseHeaders + "\n" +
                "Запрос пришел:          " + requestReceiveTime + "\n" +
                "Ответ отправлен:        " + responceSendTime + "\n" +
                "Запрос обработан за:    " + processingtime + " мс" + "\n" +
                "HTTP LOG SET END";
    }
}
