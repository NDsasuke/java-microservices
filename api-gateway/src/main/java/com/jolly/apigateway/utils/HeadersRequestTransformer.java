package com.jolly.apigateway.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;

public class HeadersRequestTransformer extends ProxyRequestTransformer {

    @Override
    public RequestBuilder transform(HttpServletRequest request) throws HttpRequestMethodNotSupportedException, URISyntaxException, IOException {
        RequestBuilder requestBuilder = predecessor.transform(request);

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            if (headerName.equals("x-access-token")) {
                requestBuilder.addHeader(headerName, headerValue);
            }
        }
        return requestBuilder;
    }
}
