package com.answer.yx.dao;

import com.answer.yx.dao.Impl.ThreadDao;
import com.answer.yx.dao.Impl.ThreadUCDao;
import com.answer.yx.entity.Answer;
import org.junit.Test;
import sun.plugin2.main.server.HeartbeatThread;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThreadDaoTest {
    @Test
    public void threadDaoTest() {
        ThreadDao threadDao = new ThreadDao("xigua");

        // threadDao.start();

        List<Answer> list = new ArrayList<Answer>();

        for (int i = 0; i < 100; i++) {
            try {
                Thread.currentThread().sleep(2000);//毫秒
            } catch (Exception e) {
            }
            list = threadDao.answerList;
            System.out.println("-----");
            for (Answer answer : list) {
                System.out.println(answer);
            }
            System.out.println("-----");
        }

    }

    @Test
    public void TestDate() {

        System.out.println(new Date().getTime());
    }
@Test
    public void StringUTF8(){
        String str = "\\u5c0f\\u74dc\\u524d\\u516d\\u6b21\\u8003\\u8bd5\\u7684\\u5e73\\u5747\\u5206\\u53ea\\u670965\\u5206\\uff0c\\u6700\\u540e\\u4e24\\u6b21\\u8003\\u8bd5\\u662f\\u54ea\\u4e2a\\u9009\\u9879\\u624d\\u80fd\\u8ba9\\u6574\\u4f53\\u5e73\\u5747\\u5206\\u8fbe\\u523070\\u5206\\u53ca\\u4ee5\\u4e0a\\uff1f";
        try {
            String i = new String(str.getBytes("ISO8859-1"),"UTF-8");
            System.out.println(i);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getAnswerUcList(){
        ThreadUCDao threadUCDao = new ThreadUCDao("");
        //threadUCDao.ThreadStatus = false;
        for (int i = 0; i < 100; i++) {
            try {
                Thread.currentThread().sleep(2000);//毫秒
            } catch (Exception e) {
            }

        }
    }
}
