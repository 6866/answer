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

public class ThreadDao extends Thread {
    private String appName = null;
    private HttpGet get;
    private CloseableHttpClient client = HttpClients.createDefault();
    private AnswerDaoImpl answerDao = new AnswerDaoImpl();
    public volatile List<Answer> answerList = new ArrayList<Answer>();
    public volatile boolean ThreadStatus = true;

    public ThreadDao(String appName) {
        this.appName = appName;
        this.start();
    }

    public void run() {
        System.out.println("开始运行");
        while (ThreadStatus) {
            try {
                Thread.currentThread().sleep(500);//毫秒
            } catch (Exception e) {
            }
            answerList = getAnswerList();
        }

    }

    private List<Answer> getAnswerList() {
        List<Answer> list = new ArrayList<Answer>();
        System.out.println("线程在运行");
        HttpResponse response = null;
        String data = "";
        try {
            String url = "http://140.143.49.31/api/ans2?key=" + appName + "&wdcallback=jQuery321019261683453805745_1516426940030&_="+ new Date().getTime();
            get = new HttpGet(url);
            get.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 4.4.2; SM-G955F Build/JLS36C) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 SogouSearch Android1.0 version3.0 AppVersion/5909");
            get.setHeader("Referer", "http://nb.sa.sogou.com/?mid=1d6f898600010580027&encryptcode=31faf9f8a8e682da9429451c62bed61b&clientid=009744b8b48578e235c8c2675d7b3b8c&umeng_pushid=AmzosbuRKYudbKedDCi1dsE46RTfv_GRxAK_GbeX6-Ai&mi_pushid=2wInkwmjk9pBTE5o%2FIFMaL9ErSzG2k3QPVHCuyPfanU%3D&opush_pushid=");

            response = client.execute(get);
            data = EntityUtils.toString(response.getEntity());

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject;
        jsonObject = JSON.parseObject(data.substring(data.indexOf("{"), data.length() - 1));

        System.out.println("取到数据");
        if (jsonObject.getInteger("code") == 0) {
            JSONArray array = jsonObject.getJSONArray("result");
            for (int i = 0, y = array.size(); i < y; i++) {
                Answer answer = new Answer();
                String answerString = array.getString(i);
                //System.out.println(answerString);
                JSONObject answerJson = JSON.parseObject(answerString);
                answer.setTitle(answerJson.getString("title"));
                answer.setResult(answerJson.getString("result"));
                System.out.println(answer);
                list.add(answer);
            }
        }

        if (appName.equals("xigua")) {
            String url = "http://answer.sm.cn/answer/curr?format=json&_t=" + new Date().getTime() + "&activity=million";
            get = new HttpGet(url);
            data = "";
            try {
                response = client.execute(get);
                data = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }

            jsonObject = JSON.parseObject(data);
            System.out.println("取到数据");
            Answer answer = new Answer();
            if (jsonObject.getInteger("status") == 0) {
                JSONObject jsonObjectData = jsonObject.getJSONObject("data");
                answer.setTitle(jsonObjectData.getString("title"));
                JSONArray jsonArray = jsonObjectData.getJSONArray("options");
                Integer correct = jsonObjectData.getInteger("correct");
                if (correct != null) {
                    jsonObjectData = jsonArray.getJSONObject(correct);
                    answer.setResult(jsonObjectData.getString("title"));
                    answer.setA("uc");
                    System.out.println(answer);
                    list.add(answer);
                }

            }
        }
        return list;
    }
}
