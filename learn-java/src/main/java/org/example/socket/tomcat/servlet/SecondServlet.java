package org.example.socket.tomcat.servlet;

import org.example.socket.tomcat.http.GPResponse;
import org.example.socket.tomcat.http.GPRequest;
import org.example.socket.tomcat.http.GPServlet;

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
