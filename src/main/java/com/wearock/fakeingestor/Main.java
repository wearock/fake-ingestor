package com.wearock.fakeingestor;

import javax.xml.ws.Endpoint;
import com.wearock.fakeingestor.services.*;

public class Main {
    public static void main(String[] args) {
        String server = "127.0.0.1:9999";
        if (args.length > 0) {
            server = args[0];
        }
        Endpoint.publish(String.format("http://%s/auth/ws", server), new Authentication());
        Endpoint.publish(String.format("http://%s/upload/ws", server), new Upload(server));
        System.out.println("Services up and running!");
    }
}
