package org.example.Clases;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.example.exceptions.RequestException;

public class Request extends HttpComponent {

    private String methodRequest;
    private String bodyRequest;
    private List<HttpParameter> queryParams;

    public Request(int id, String url, String methodRequest, String bodyRequest) {
        super(id, url);
        this.methodRequest = methodRequest;
        this.bodyRequest = bodyRequest;
    }
    public Request(int id, String url, String headerName, String headerValue, String methodRequest, String bodyRequest) {
        super(id, url, headerName, headerValue);
        this.methodRequest = methodRequest;
        this.bodyRequest = bodyRequest;
    }

    public String getMethodRequest() {
        return methodRequest;
    }
    public void setMethodRequest(String methodRequest) {
        this.methodRequest = methodRequest;
    }
    public String getBodyRequest() {
        return bodyRequest;
    }
    public void setBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
    }



    public String send() throws RequestException {

        final CloseableHttpClient httpClient = HttpClients.createDefault();
        String res;


        if (getMethodRequest() == "GET") {

            HttpGet request = new HttpGet(getUrl());
            request.setHeader(getHeaderName(), getHeaderValue());

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity);
                if (!res.isEmpty()) {
                    return res;
                }
                else{
                    return "Error al realizar la petición";
                }

            }catch (IOException e) {
                e.printStackTrace();
                throw new RequestException("Request.GET: " + e.getMessage());
            }
        }
        else if (getMethodRequest() == "POST") {

            HttpPost request = new HttpPost(getUrl());
            StringEntity json = new StringEntity(getBodyRequest(), ContentType.APPLICATION_JSON);
            request.setEntity(json);
            request.setHeader(getHeaderName(), getHeaderValue());

            try(CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 201) {
                    System.out.println("Error!" + response.getStatusLine().getStatusCode());
                    return response.getStatusLine().toString();
                } else {
                    return EntityUtils.toString(entity);
                }
            }catch (IOException e) {
                e.printStackTrace();
                throw new RequestException("Request.GET: " + e.getMessage());
            }
        }
        else if (getMethodRequest() == "DELETE") {
            HttpDelete request = new HttpDelete(getUrl());
            request.setHeader(getHeaderName(), getHeaderValue());
            try (CloseableHttpResponse response = httpClient.execute(request)) {

                HttpEntity entity = response.getEntity();
                if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 204) {
                    System.out.println("Error!" + response.getStatusLine().getStatusCode());
                    return response.getStatusLine().toString();
                } else {
                    return EntityUtils.toString(entity);
                }
            }catch (IOException e) {
                e.printStackTrace();
                throw new RequestException("Request.GET: " + e.getMessage());
            }
        }
        else if (getMethodRequest() == "PUT") {

            HttpPut request = new HttpPut(getUrl());
            StringEntity json = new StringEntity(getBodyRequest(), ContentType.APPLICATION_JSON);
            request.setEntity(json);
            request.setHeader(getHeaderName(), getHeaderValue());

            try(CloseableHttpResponse response = httpClient.execute(request)){
                HttpEntity entity = response.getEntity();

                if (response.getStatusLine().getStatusCode() != 200 && response.getStatusLine().getStatusCode() != 201) {
                    System.out.println("Error!" + response.getStatusLine().getStatusCode());
                    return response.getStatusLine().toString();
                } else {
                    return EntityUtils.toString(entity);
                }
            }catch (IOException e) {
                e.printStackTrace();
                throw new RequestException("Request.GET: " + e.getMessage());
            }
        }

        return "Error al realizar la petición";

    }
}
