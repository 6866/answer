package com.answer.yx.dao.Impl;

public  class ThreadDaoStart {
    public static ThreadDao threadDao;
    public static ThreadDao threadDaohj;
    public static ThreadDao threadDaocd;
    public static ThreadDao threadDaozs;
    public static ThreadDao threadDaohjsm;

    static {
        threadDao = new ThreadDao("xigua");
        threadDaohj = new ThreadDao("huajiao");
        threadDaocd = new ThreadDao("cddh");
        threadDaozs = new ThreadDao("zscr");
        threadDaohjsm = new ThreadDao("hjsm");

    }
    public static void close(){
        threadDao.ThreadStatus = false;
        threadDaohj.ThreadStatus = false;
        threadDaocd.ThreadStatus = false;
        threadDaozs.ThreadStatus = false;
        threadDaohjsm.ThreadStatus = false;

    }
    public static void run(){

    }

}
