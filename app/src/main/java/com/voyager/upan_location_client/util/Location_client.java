package com.voyager.upan_location_client.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.voyager.upan_location_client.network.HttpUtil;
import com.voyager.upan_location_client.operation.Operaton;
import com.voyager.voyager.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/21.
 */
public class Location_client extends AppCompatActivity implements View.OnClickListener {

    Button getdata_btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_layout);
        getdata_btn = (Button)findViewById(R.id.get_lng_lat_btn);
        getdata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handle_request();
            }
        });

    }


    private void handle_request(){
        /*
        JSONObject jsonObj;
        try {
            jsonObj = query("ask_for_result");
            String temp = jsonObj.toString()+"asd";
            Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "system error!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }*/

        new Thread(new Runnable() {
            public void run() {

                Operaton operaton = new Operaton();
                String result = operaton.login("location", "get_lng_lat");
                Message msg = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            }
        }).start();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String str = (String) msg.obj;
            Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            System.out.println(str);
            super.handleMessage(msg);
        }
    };


    @Override
    public void onClick(View v) {

    }

    // 定义发送请求的方法
    private JSONObject query(String ask_for_result)
            throws Exception {
        // 使用Map封装请求参数
        Map<String, String> map = new HashMap<>();
        map.put("user", ask_for_result);//这个就是放两个串进来然后发过去
        // 定义发送请求的URL
        String url = HttpUtil.BASE_URL;
        // 发送请求
        return new JSONObject(HttpUtil.postRequest(url, map));
    }



}
