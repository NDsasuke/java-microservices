package com.jolly.apigateway.controller;

import com.jolly.apigateway.ApiGatewayProperties;
import com.jolly.apigateway.utils.URLRequestTransformer;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class GatewayController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiGatewayProperties apiGatewayProperties;

    private HttpClient httpClient;

    @PostConstruct
    public void init() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
    }

    @RequestMapping(value = "/api/**", method = {GET, POST, DELETE})
    @ResponseBody
    public ResponseEntity<String> proxyRequest(HttpServletRequest request) throws HttpRequestMethodNotSupportedException, IOException, URISyntaxException {
        HttpUriRequest proxiedRequest =
    }

    private HttpHeaders makeResponseHeaders(HttpResponse response) {
        HttpHeaders result = new org.springframework.http.HttpHeaders();
        Header h = response.getFirstHeader("Content-Type");
        result.set(h.getName(), h.getValue());
        return result;
    }

    private HttpUriRequest createHttpUriRequest(HttpServletRequest request) throws URISyntaxException, HttpRequestMethodNotSupportedException, IOException {
        URLRequestTransformer urlRequestTransformer = new URLRequestTransformer(apiGatewayProperties);
    }
}
