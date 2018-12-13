package com.bw.ymy.recyclerview_week2.model;

import com.bw.ymy.recyclerview_week2.callback.MyCallBack;
import com.bw.ymy.recyclerview_week2.okhttp.ICallBack;
import com.bw.ymy.recyclerview_week2.okhttp.OkhttoNutls;

import java.util.Map;

public class IModelIMpl implements IModel {
    @Override
    public void getRequest(String urlstr, Map<String, String> param, Class clazz, final MyCallBack callBack) {

        OkhttoNutls.getInstance().PoseEnqueue(urlstr, param, clazz, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                callBack.onsuccess(obj);
            }

            @Override
            public void onFail(Exception e) {
                callBack.onsuccess(e);
            }
        });

    }
}
