package com.bw.ymy.recyclerview_week2.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttoNutls {
    private  static OkhttoNutls instance;
    private Handler handler=new Handler(Looper.getMainLooper());
    private OkHttpClient httpClient;
    public static  OkhttoNutls getInstance()
    {
        if(instance==null)
        {
            synchronized (OkhttoNutls.class)
            {
                if(null==instance)
                {
                    instance=new OkhttoNutls();
                }
            }
        }
        return  instance;
    }

    private  OkhttoNutls()
    {
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient=new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

    }
    public  void PoseEnqueue(final String urlstr, Map<String,String> params, final Class clazz, final ICallBack callBack)
    {
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String> entry:params.entrySet())
        {
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody build=builder.build();
        Request build1=new Request.Builder()
                .post(build)
                .url(urlstr)
                .build();
        Call call=httpClient.newCall(build1);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                callBack.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                String result=response.body().string();
                final Object o=new Gson().fromJson(result,clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });


            }
        });

    }
}
