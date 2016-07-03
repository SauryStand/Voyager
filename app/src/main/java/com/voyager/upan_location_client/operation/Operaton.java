package com.voyager.upan_location_client.operation;

import com.voyager.upan_location_client.network.ConnNet;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Operaton {

    public String login(String url, String str_temp) {
        String result = null;
        ConnNet connNet = new ConnNet();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("data_temp", str_temp));
        //params.add(new BasicNameValuePair("username", username));
        //params.add(new BasicNameValuePair("password", password));
        try {
            HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
            HttpPost httpPost = connNet.gethttPost(url);
            System.out.println(httpPost.toString());
            httpPost.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse httpResponse = client.execute(httpPost);
            result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            /*
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            } else {
                result = "lng_lat faild";
            }*/
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        } catch (ClientProtocolException e) {

            e.printStackTrace();
        } catch (ParseException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return result;
    }


}