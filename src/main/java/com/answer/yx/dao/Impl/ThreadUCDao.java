package com.answer.yx.dao.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.answer.yx.entity.Answer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThreadUCDao extends Thread{
    private String appName = null;
    private HttpGet get;
    private CloseableHttpClient client = HttpClients.createDefault();
    private AnswerDaoImpl answerDao = new AnswerDaoImpl();
    public volatile Answer answer = new Answer();
    public volatile boolean ThreadStatus = true;
    public ThreadUCDao(String appName){
        this.appName = appName;
        String url = "http://answer.sm.cn/answer/curr?format=json&_t="+ new Date().getTime()+"&activity=million";
        get = new HttpGet(url);
        this.start();
    }

    public void run(){
        System.out.println("开始运行");
        while (ThreadStatus){
            try
            {
                Thread.currentThread().sleep(500);//毫秒
            }
            catch(Exception e){}
            answer = getAnswerList();
        }

    }

    public Answer getAnswerList(){
        List<Answer> list = new ArrayList<Answer>();
        System.out.println("线程在运行");
        HttpResponse response = null;
        String data = "";
        try {
            response = client.execute(get);
            data = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject;
        jsonObject = JSON.parseObject(data);
        System.out.println("取到数据");
        Answer answer = new Answer();
        if(jsonObject.getInteger("status") == 0){
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            answer.setTitle(jsonObjectData.getString("title"));
            JSONArray jsonArray = jsonObjectData.getJSONArray("options");
            Integer correct = jsonObjectData.getInteger("correct");
            if (correct != null){
                jsonObjectData = jsonArray.getJSONObject(correct);
                answer.setResult(jsonObjectData.getString("title"));
                answer.setA("uc");
                System.out.println(answer);
            }

        }
        return answer;
    }



}
