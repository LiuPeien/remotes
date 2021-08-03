package org.example.socket.tomcat;

import org.example.socket.tomcat.http.GPResponse;
import org.example.socket.tomcat.http.GPRequest;
import org.example.socket.tomcat.http.GPServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class GPTomcat {
    private int port = 8080;
    private ServerSocket server;
    private Map<String, GPServlet> servletMap = new HashMap<>();

    private Properties webxml = new Properties();

    private void init() {
        try {
            String WEB_INF = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(WEB_INF + "web.properties");

            webxml.load(fis);

            for(Object k: webxml.keySet()) {
                String key = k.toString();
                if(key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webxml.getProperty(key);
                    String className = webxml.getProperty(servletName + ".className");

                    GPServlet obj = (GPServlet)Class.forName(className).newInstance();
                    servletMap.put(url, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        init();

        try {
            server = new ServerSocket(this.port);
            System.out.println("Tomcat start, port is: " + this.port);

            while (true) {
                Socket client = server.accept();
                process(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void process(Socket client) throws Exception {
        InputStream is = client.getInputStream();
        OutputStream os = client.getOutputStream();

        GPRequest request = new GPRequest(is);
        GPResponse response = new GPResponse(os);

        String url = request.getUrl();

        if(servletMap.containsKey(url)) {
            servletMap.get(url).service(request, response);
        } else {
            response.write("404 - Not Found");
        }

        os.flush();
        os.close();

        is.close();
        client.close();
    }

    public static void main(String[] args) {
        new GPTomcat().start();
    }
}
