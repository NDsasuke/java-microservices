package com.jolly.apigateway.utils;

import com.jolly.apigateway.ApiGatewayProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class URLRequestTransformer extends ProxyRequestTransformer {

    private ApiGatewayProperties apiGatewayProperties;

    public URLRequestTransformer(ApiGatewayProperties apiGatewayProperties) {
        this.apiGatewayProperties = apiGatewayProperties;
    }

    @Override
    public RequestBuilder transform(HttpServletRequest request) throws HttpRequestMethodNotSupportedException, URISyntaxException, IOException {
        String requestURI = request.getRequestURI();
        URI uri;
        if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
            uri = new URI(getServiceUrl(requestURI, request) + "?" + request.getQueryString());
        } else {
            uri = new URI(getServiceUrl(requestURI, request));
        }

        RequestBuilder rb = RequestBuilder.create(request.getMethod());
        rb.setUri(uri);
        return rb;
    }

    private String getServiceUrl(String requestURI, HttpServletRequest httpServletRequest) throws HttpRequestMethodNotSupportedException {
        ApiGatewayProperties.Endpoint endpoint = apiGatewayProperties.getEndpoints().stream()
                .filter(e -> requestURI.matches(e.getPath()) && e.getMethod() == RequestMethod.valueOf(httpServletRequest.getMethod()))
                .findFirst()
                .orElseThrow(() -> new HttpRequestMethodNotSupportedException(httpServletRequest));
        return endpoint.getLocation() + requestURI;
    }
}
