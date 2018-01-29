package com.answer.yx.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class answerDaoTest {
    @Autowired
    private AnswerDao answerDao;
    @Test
    public void DaoTest(){
        //answerDao.getxiguaAnswerList("");
        try
        {
            Thread.currentThread().sleep(20000);//毫秒
        }
        catch(Exception e){}

    }


}
