package org.example.Clases;

import org.apache.http.Header;
import org.example.Clases.HttpParameter;
import org.example.Clases.Request;

import java.util.List;

public class Response extends HttpComponent {

    private Request request;
    private Integer statusCode;
    private String bodyResponse;

    public Response(int id, String url, String headerName, String headerValue, Request request, Integer statusCode, String bodyResponse) {
        super(id, url, headerName, headerValue);
        this.request = request;
        this.statusCode = statusCode;
        this.bodyResponse = bodyResponse;
    }

}
