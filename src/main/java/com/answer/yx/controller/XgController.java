package com.answer.yx.controller;

import com.answer.yx.dao.AnswerDao;
import com.answer.yx.dao.Impl.ThreadDao;
import com.answer.yx.dao.Impl.ThreadDaoStart;
import com.answer.yx.dao.Impl.ThreadUCDao;
import com.answer.yx.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "xigua")
public class XgController {
    @Autowired
    private AnswerDao answerDao;
/*
    @Autowired
    private ThreadDao threadDao;
    @Resource(name = "threadDaohj")
    private ThreadDao threadDaohj;
    @Resource(name = "threadDaocd")
    private ThreadDao threadDaocd;
    @Resource(name = "threadDaozs")
    private ThreadDao threadDaozs;*/

    @RequestMapping(value = "getAnswer" ,method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getAnswerMap(String app){
        List<Answer> list = new ArrayList<Answer>();
        if (app != null && app.equals("xigua")){
            list = ThreadDaoStart.threadDao.answerList;
        }else if(app.equals("hj")){
            list = ThreadDaoStart.threadDaohj.answerList;
        }else if(app.equals("cd")){
            list = ThreadDaoStart.threadDaocd.answerList;
        }else if(app.equals("zs")){
            list = ThreadDaoStart.threadDaozs.answerList;
        }else if(app.equals("hjsm")){
            list = ThreadDaoStart.threadDaohjsm.answerList;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("code",list.size());
        map.put("answer",list);
        return map;
    }

    


}
