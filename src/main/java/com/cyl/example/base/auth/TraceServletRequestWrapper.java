package com.cyl.example.base.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class TraceServletRequestWrapper extends HttpServletRequestWrapper {
    public TraceServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }


}
