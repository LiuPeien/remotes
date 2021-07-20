package com.learn.socket.tomcat.servlet;

import com.learn.socket.tomcat.http.GPResponse;
import com.learn.socket.tomcat.http.GPRequest;
import com.learn.socket.tomcat.http.GPServlet;

public class SecondServlet extends GPServlet {
    @Override
    public void doGet(GPRequest request, GPResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is the second servlet");
    }
}
