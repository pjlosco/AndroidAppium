package com.project.appium.api;

import com.google.common.base.Optional;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class ServiceHelper {

    private String UserAccessToken = "";

    public static final String PRODUCTION_HOST = "api.projectname.com/";

    public HttpResponse executePostServiceCall(URI uri, Optional<StringEntity> entity, String possibleErrorMessage) throws URISyntaxException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (entity.isPresent()) {
            httpPost.setEntity(entity.get());
        }
        if (!UserAccessToken.isEmpty()) {
            httpPost.setHeader("X-Access-Token", UserAccessToken);
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("POST to: " + uri);
        HttpResponse response = httpClient.execute(httpPost);
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println(" -> " + responseCode);
        if (responseCode > 204) {
            System.out.println("Unexpected response, " + responseCode + " code returned");
            throw new RuntimeException(possibleErrorMessage);
        }
        return response;
    }


    public HttpResponse executeGetServiceCall(URI uri, String possibleErrorMessage) throws URISyntaxException, IOException {
        HttpGet httpGet = new HttpGet(uri);
        if (!UserAccessToken.isEmpty()) {
            httpGet.setHeader("X-Access-Token", UserAccessToken);
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("GET to: " + uri);
        HttpResponse response = httpClient.execute(httpGet);
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println(" -> " + responseCode);
        if (responseCode > 204) {
            System.out.println("Unexpected response, " + responseCode + " code returned");
            throw new RuntimeException(possibleErrorMessage);
        }
        return response;
    }

    public HttpResponse executeDeleteServiceCall(URI uri, String possibleErrorMessage) throws URISyntaxException, IOException {
        HttpDelete httpDelete = new HttpDelete(uri);
        if (!UserAccessToken.isEmpty()) {
            httpDelete.setHeader("X-Access-Token", UserAccessToken);
        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        System.out.println("DELETE to: " + uri);
        HttpResponse response = httpClient.execute(httpDelete);
        int responseCode = response.getStatusLine().getStatusCode();
        System.out.println(" -> " + responseCode);
        if (responseCode > 204) {
            System.out.println("Unexpected response, " + responseCode + " code returned");
            throw new RuntimeException(possibleErrorMessage);
        }
        return response;
    }

    protected void setUserAccessToken(String userAccessToken) {
        this.UserAccessToken = userAccessToken;
    }

}
