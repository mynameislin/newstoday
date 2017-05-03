package com.bawei.today.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/11.
 */

public class HostListAdd {
    public static List<String> list=null;
    public static List<String> pinlist=null;
    public static List<String>  addList(){
        if(list==null){
            list=new ArrayList<>();
            list.add("体育");
            list.add("财经");
            list.add("科技");
            list.add("电影");
            list.add("汽车");
            list.add("笑话");
            list.add("游戏");
            list.add("时尚");
            list.add("情感");
            list.add("精选");
            list.add("电台");
            return list;
        }else {
            return list;
        }

    }
    public static List<String>  addList2(){
        if(pinlist==null){
            pinlist=new ArrayList<>();
           pinlist.add("彩票");
           pinlist.add("教育");
           pinlist.add("旅游");
            return pinlist;
        }else {
            return pinlist;
        }

    }
    public static List<String> urllist=null;
    public static List<String> url(){
        if(urllist==null){
            urllist=new ArrayList<>();
            urllist.add("http://c.m.163.com/nc/article/headline/T1348649079062/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348648756099/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348649580692/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348648650048/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348654060988/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1350383429665/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348654151579/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348650593803/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348650839000/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1370583240249/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1379038288239/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1356600029035/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348654225495/0-20.html");
            urllist.add("http://c.m.163.com/nc/article/headline/T1348654204705/0-20.html");
                    return urllist;
        }else {
            return urllist;
        }
    }

}
