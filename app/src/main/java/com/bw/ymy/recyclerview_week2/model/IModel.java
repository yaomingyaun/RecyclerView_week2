package com.bw.ymy.recyclerview_week2.model;

import com.bw.ymy.recyclerview_week2.callback.MyCallBack;

import java.util.Map;

public interface IModel {

    void  getRequest(String urlstr, Map<String,String> param, Class clazz, MyCallBack callBack);
}
