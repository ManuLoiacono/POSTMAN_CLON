package org.example.Clases;

public class Historial{

    private Integer idH;
    private String nameH;
    private String methodH;
    private Request request;

    public Historial(Integer idH, String nameH, String methodH, Request request) {
        this.idH = idH;
        this.nameH = nameH;
        this.methodH = methodH;
        this.request = request;
    }

}
