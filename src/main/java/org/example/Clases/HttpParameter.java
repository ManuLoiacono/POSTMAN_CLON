package org.example.Clases;

public class HttpParameter {


    private Integer id;
    private String key;
    private String value;


    public HttpParameter() {
    }

    public HttpParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public HttpParameter(Integer id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
