package org.example.Clases;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.example.Clases.HttpParameter;

import java.util.List;

abstract class HttpComponent {

    private int id;
    private String url;
    private String headerName;
    private String headerValue;

    public HttpComponent(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public HttpComponent(int id, String url, String headerName, String headerValue) {
        this.id = id;
        this.url = url;
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderValue() {
        return headerValue;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }
}
