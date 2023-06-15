package com.cyl.example.base.auth;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TraceServletResponseWrapper extends HttpServletResponseWrapper {


    public TraceServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }



}
