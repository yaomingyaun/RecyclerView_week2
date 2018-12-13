package com.bw.ymy.recyclerview_week2;

import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.ymy.recyclerview_week2.adapter.MyBase;
import com.bw.ymy.recyclerview_week2.bean.UserBean;
import com.bw.ymy.recyclerview_week2.presenter.PresenterImpl;
import com.bw.ymy.recyclerview_week2.vie.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IView {

    private XRecyclerView xrecview;
    private  int page=1;
    private PresenterImpl presenter;
    private MyBase adapter;
    private Button b;
    private ImageView image;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        xrecview=findViewById(R.id.xrecview);
        image=findViewById(R.id.image);
        //点击头像  放大在缩小
        animation=AnimationUtils.loadAnimation(this,R.anim.animmm);
       image.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               image.startAnimation(animation);
           }
       });

        presenter=new PresenterImpl(this);






        //创建一个管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrecview.setLayoutManager(layoutManager);
        //适配器
        adapter=new MyBase(this);
        xrecview.setAdapter(adapter);

        xrecview.setItemAnimator(new DefaultItemAnimator());




        //是否下拉刷新
        xrecview.setPullRefreshEnabled(true);
        //上啦加载
        xrecview.setLoadingMoreEnabled(true);
        //回调
        xrecview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                lodata();
            }

            @Override
            public void onLoadMore() {
                lodata();

            }
        });
        lodata();
    }
    private  void  lodata()
    {
        Map<String,String> map=new HashMap<>();
        map.put("page",page+"");
        presenter.getRequest(RPKS.TYPE_TITLE,map,UserBean.class);
    }

    @Override
    public void onsuccess(Object data) {

        if(data instanceof  UserBean)
        {
            UserBean userBean= (UserBean) data;
            if(page==1)
            {
                 adapter.setlist(userBean.getData());
            }else
            {
                adapter.addlist(userBean.getData());
            }
            page++;
            xrecview.refreshComplete();
            xrecview.loadMoreComplete();
        }

    }

    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.deteach();
    }
}
