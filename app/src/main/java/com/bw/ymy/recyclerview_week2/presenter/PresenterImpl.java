package com.bw.ymy.recyclerview_week2.presenter;

import com.bw.ymy.recyclerview_week2.callback.MyCallBack;
import com.bw.ymy.recyclerview_week2.model.IModelIMpl;
import com.bw.ymy.recyclerview_week2.vie.IView;

import java.util.Map;

public class PresenterImpl implements Presenter {

    private IView mIView;
    private IModelIMpl iModelIMpl;

    public PresenterImpl(IView mIView) {
      this.  mIView = mIView;
        iModelIMpl=new IModelIMpl();
    }

    @Override
    public void getRequest(String urlstr, Map<String, String> map, Class clazz) {


        iModelIMpl.getRequest(urlstr, map, clazz, new MyCallBack() {
            @Override
            public void onsuccess(Object data) {
                mIView.onsuccess(data);
            }
        });

    }
    //解绑
    public void deteach(){
        mIView=null;
        iModelIMpl=null;
    }

}
