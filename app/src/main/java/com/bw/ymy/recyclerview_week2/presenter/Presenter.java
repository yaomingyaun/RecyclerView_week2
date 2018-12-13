package com.bw.ymy.recyclerview_week2.presenter;

import java.util.Map;

public interface Presenter {

    void  getRequest(String urlstr, Map<String,String> map,Class clazz);
}
