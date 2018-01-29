package com.answer.yx.dao;

import com.answer.yx.entity.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> getxiguaAnswerList(String appName);

}
