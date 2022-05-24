package com.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.Filter;

@Configuration
public class RequestLoggingConfig {

    @Bean
    public Filter requestLoggingFilter() {
        CommonsRequestLoggingFilter crlf = new CommonsRequestLoggingFilter();
        crlf.setIncludeClientInfo(true);
        crlf.setIncludeHeaders(false);
        crlf.setIncludeQueryString(true);
        crlf.setIncludePayload(true);
        crlf.setMaxPayloadLength(100000);
        return crlf;
    }
}
