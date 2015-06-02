package com.project.appium.api;

import com.google.common.base.Optional;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.project.appium.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class UserManagerHelper extends ServiceHelper {

    protected String host;
    private User user;

    public UserManagerHelper(boolean isProd, User user) {
        this.user = user;
        if (isProd) {
            host = PRODUCTION_HOST;
        } else {
            // set to a staging environment
        }
        if (user.getUserID() != null && !user.getUserID().isEmpty()) {
            loginAndSetAccessToken(user);
        } else {
            createUser(user);
        }
    }

    public String getHost() {
        return host;
    }

    public User getUser() {
        return user;
    }

    public void createUser(User user) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost(host)
                    .setPath("/registrations/create").build();
            JsonObject userInfoJson = new JsonObject();
            userInfoJson.addProperty("phone_number", "+1 " + user.getPhoneNumber());
            userInfoJson.addProperty("email", user.getEmail());
            userInfoJson.addProperty("name", user.getFullName());
            userInfoJson.addProperty("password", user.getPassword());
            userInfoJson.addProperty("platform", user.getPlatform());
            StringEntity stringEntity = new StringEntity(new GsonBuilder().create().toJson(userInfoJson), ContentType.create("application/json"));
            HttpResponse response = executePostServiceCall(uri, Optional.of(stringEntity), "ERROR: User was not created :'(");
            parseResponseAndSetToken(response, user);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginAndSetAccessToken(User user) {
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost(host)
                    .setPath("/access_tokens").build();
            JsonObject userInfoJson = new JsonObject();
            userInfoJson.addProperty("username", user.getEmail());
            userInfoJson.addProperty("password", user.getPassword());
            userInfoJson.addProperty("grant_type", "password");
            StringEntity stringEntity = new StringEntity(new GsonBuilder().create().toJson(userInfoJson), ContentType.create("application/json"));
            HttpResponse response = executePostServiceCall(uri, Optional.of(stringEntity), "ERROR: User was not logged in :'(");
            parseResponseAndSetToken(response, user);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseResponseAndSetToken(HttpResponse response, User user) throws IOException {
        try {
            // TODO - convert to gson
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = rd.readLine();
            while (line != null && !line.isEmpty()) {
                JSONParser j = new JSONParser();
                JSONObject o = (JSONObject)j.parse(line);
                Map jsonResponse = (Map)o.get("response");
                String accessToken = jsonResponse.get("access_token").toString();
                setUserAccessToken(accessToken);
                user.setAccessToken(accessToken);
                try {
                    user.setUserID(jsonResponse.get("user_id").toString());
                } catch (NullPointerException e) {
                    JSONObject userData = (JSONObject)jsonResponse.get("user");
                    user.setUserID(userData.get("id").toString());
                }
                line = rd.readLine();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
