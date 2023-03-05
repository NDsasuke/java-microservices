package com.jolly.apigateway.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;
import java.net.URISyntaxException;

public abstract class ProxyRequestTransformer {

    protected ProxyRequestTransformer predecessor;

    public abstract RequestBuilder transform(HttpServletRequest request) throws HttpRequestMethodNotSupportedException, URISyntaxException, IOException;

    public void setPredecessor(ProxyRequestTransformer transformer) {
        this.predecessor = transformer;
    }
}
