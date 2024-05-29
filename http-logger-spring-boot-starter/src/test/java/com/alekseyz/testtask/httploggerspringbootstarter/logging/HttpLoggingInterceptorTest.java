package com.alekseyz.testtask.httploggerspringbootstarter.logging;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import static org.apache.log4j.Level.FATAL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@DisplayName("Тестирование класса интерсептора для перехвата запросов и логгирования HttpLoggingInterceptor")
@ExtendWith(MockitoExtension.class)
class HttpLoggingInterceptorTest {

    HttpLoggingInterceptor httpLoggingInterceptor;
    Logger logger;
    MockHttpServletRequest httpServletRequest;
    MockHttpServletResponse httpServletResponse;
    Object handler;

    @BeforeEach
    void setUp() {

        httpServletRequest = new MockHttpServletRequest();
        httpServletResponse = new MockHttpServletResponse();
        httpLoggingInterceptor = new HttpLoggingInterceptor();
        logger = Logger.getLogger(HttpLoggingInterceptor.class);
        handler = new Object();

        httpServletRequest.setMethod(HttpMethod.POST.name());
        httpServletRequest.setServerName("www.test.ru");
        httpServletRequest.setRequestURI("/api/v1/test");
        httpServletRequest.setContentType("application/json");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(200);
        httpServletRequest.addHeader("Content-Type", "application/json");
        httpServletResponse.addHeader("Content-Type", "application/json");
    }

    @DisplayName("Проверка, что при перехвате запроса, метод PreHandle() отрабатывает и возвращает TRUE")
    @Test
    void interceptRequestAndResponsePreHandle_ReturnTrue() {
        boolean preHandleResult = httpLoggingInterceptor.preHandle(httpServletRequest, httpServletResponse, handler);

        Assertions.assertTrue(preHandleResult);
    }

    @DisplayName("Проверка записи времени начала обработки запроса в методе PreHandle")
    @Test
    void interceptRequestAndResponsePreHandle_AddRequestReceivedTime() {
        httpLoggingInterceptor.preHandle(httpServletRequest, httpServletResponse, handler);

        Assertions.assertNotNull(httpLoggingInterceptor.getRequestReceiveTime());
    }

    @DisplayName("Проверка формирования записи лога LogView в методе AfterCompletion(), если не было исключений")
    @Test
    void interceptRequestAndResponseAfterCompletion_CreateLogView() {
        httpLoggingInterceptor.setRequestReceiveTime(LocalDateTime.now());

        httpLoggingInterceptor.afterCompletion(httpServletRequest, httpServletResponse, handler, null);

        Assertions.assertNotNull(httpLoggingInterceptor.getLogView());
        Assertions.assertEquals(httpLoggingInterceptor.getLogView().requestURL, "http://www.test.ru/api/v1/test");
    }

    @DisplayName("Проверка проведения записи лога логгером если нет исключений")
    @Test
    void interceptRequestAndResponseAfterCompletion_WriteLog() {
        httpLoggingInterceptor = Mockito.spy(new HttpLoggingInterceptor());
        httpLoggingInterceptor.setRequestReceiveTime(LocalDateTime.now());

        httpLoggingInterceptor.afterCompletion(httpServletRequest, httpServletResponse, handler, null);

        Mockito.verify(httpLoggingInterceptor,Mockito.atLeastOnce()).sendLog(eq(Level.INFO), any(String.class));
    }

    @DisplayName("Проверка проведения записи лога логгером если получено исключение")
    @Test
    void interceptRequestAndResponseAfterCompletionWithException_WriteLog() {
        httpLoggingInterceptor = Mockito.spy(new HttpLoggingInterceptor());
        httpLoggingInterceptor.setRequestReceiveTime(LocalDateTime.now());

        httpLoggingInterceptor.afterCompletion(httpServletRequest, httpServletResponse, handler, new Exception("Test Exception"));

        Mockito.verify(httpLoggingInterceptor,Mockito.atLeastOnce()).sendLog(eq(Level.ERROR), any(String.class));
    }

    @DisplayName("Проверка извлечения заголовков из запроса")
    @Test
    void interceptRequestGetHeaders_ReturnHederList() {
        Map<String,String> expectedRequestHeader = new HashMap<>();
        expectedRequestHeader.put("Content-Type", "application/json");

        Map<String,String> actualRequestHeaders = httpLoggingInterceptor.getRequestHeaders(httpServletRequest);

        Assertions.assertEquals(actualRequestHeaders, expectedRequestHeader);
    }

    @DisplayName("Проверка извлечения заголовков из ответа")
    @Test
    void interceptResponseGetHeaders_ReturnHederList() {
        Map<String,String> expectedResponseHeader = new HashMap<>();
        expectedResponseHeader.put("Content-Type", "application/json");

        Map<String,String> actualResponseHeaders = httpLoggingInterceptor.getResponseHeaders(httpServletResponse);

        Assertions.assertEquals(actualResponseHeaders, expectedResponseHeader);
    }

    @DisplayName("Проверка вычисления разницы двух дат")
    @Test
    void interceptGetDuration_ReturnLong() {
        LocalDateTime start = LocalDateTime.parse("2024-05-18T18:26:31.3990542");
        LocalDateTime end = LocalDateTime.parse("2024-05-18T18:26:32.3990542");

        Assertions.assertEquals(httpLoggingInterceptor.getDuration(start,end),1000);
    }

    @DisplayName("Проверка метода отправки логов")
    @Test
    void interceptSendLog_SendLog() {

        httpLoggingInterceptor = Mockito.mock(HttpLoggingInterceptor.class);

        httpLoggingInterceptor.sendLog(FATAL, "Fatal Error occured");

        Mockito.verify(httpLoggingInterceptor,Mockito.atLeastOnce()).sendLog(eq(Level.FATAL), any(String.class));
    }




}







