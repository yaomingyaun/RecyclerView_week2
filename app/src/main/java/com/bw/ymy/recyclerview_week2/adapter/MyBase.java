package com.bw.ymy.recyclerview_week2.adapter;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.ymy.recyclerview_week2.R;
import com.bw.ymy.recyclerview_week2.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class MyBase extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserBean.DataBean> mdata;
    private Context context;

    public MyBase(Context context) {
        mdata = new ArrayList<>();
        this.context=context;
    }

    //栓新
    public  void  setlist(List<UserBean.DataBean> datas)
    {
        mdata.clear();
        mdata.addAll(datas);
        notifyDataSetChanged();
    }
    //加载
    public  void  addlist(List<UserBean.DataBean> datas)
    {

        mdata.addAll(datas);
        notifyDataSetChanged();
    }

   // private AdapterView.OnItemClickListener onItemClickListener;
    //删除


    private  static  final  int TYPE_TEXT=0;
    private  static  final  int TYPE_IMAGE=1;
    private  static  final  int TYPE_III=2;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder=null;
        if(i==TYPE_TEXT)
        {
            View view=LayoutInflater.from(context).inflate(R.layout.text_item,viewGroup,false);
            viewHolder=new ViewHolderOne(view);
        }else if(i==TYPE_IMAGE)
        {
            View view=LayoutInflater.from(context).inflate(R.layout.image_item,viewGroup,false);
            viewHolder=new ViewHolderTwo(view);

        }else
        {
            View view=LayoutInflater.from(context).inflate(R.layout.image3_item,viewGroup,false);
            viewHolder=new ViewHolderThree(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {

            //点击吐司
       /* viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,mdata.get(i).getTitle(),Toast.LENGTH_LONG).show();
            }
        });*/

        //长按删除
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("删除");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取删除的下标
                        int pos=viewHolder.getLayoutPosition();
                        //从数组里面删除
                        mdata.remove(i);
                        //刷新
                        notifyDataSetChanged();
                    }
                });
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });

        int type=getItemViewType(i);
        switch (type)
        {
            case  TYPE_TEXT:
                ViewHolderOne holderOne= (ViewHolderOne) viewHolder;
                holderOne.title.setText(mdata.get(i).getTitle());
                break;
            case  TYPE_IMAGE:
                final ViewHolderTwo holderTwo= (ViewHolderTwo) viewHolder;
                holderTwo.title.setText(mdata.get(i).getTitle());
                Glide.with(context).load(mdata.get(i).getThumbnail_pic_s()).into(holderTwo.icon1);
                //点击动画
                holderTwo.icon1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holderTwo.icon1, "alpha", 1f, 0f,1f);
                        alpha.setDuration(2000);
                        alpha.setRepeatCount(0);
                        alpha.start();
                    }
                });
                break;
            case  TYPE_III:
                final ViewHolderThree holderThree= (ViewHolderThree) viewHolder;
                holderThree.title.setText(mdata.get(i).getTitle());
                Glide.with(context).load(mdata.get(i).getThumbnail_pic_s02()).into(holderThree.icon13);
                Glide.with(context).load(mdata.get(i).getThumbnail_pic_s()).into(holderThree.icon23);
                Glide.with(context).load(mdata.get(i).getThumbnail_pic_s03()).into(holderThree.icon33);
                //点击第一张图片动画   无到有
             holderThree.icon13.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     ObjectAnimator alpha = ObjectAnimator.ofFloat(holderThree.icon13, "alpha", 1f, 0f,1f);
                     alpha.setDuration(2000);
                     alpha.setRepeatCount(0);
                     alpha.start();
                 }
             });
                //点击第2张图片动画   无到有
                holderThree.icon23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holderThree.icon23, "alpha", 1f, 0f,1f);
                        alpha.setDuration(2000);
                        alpha.setRepeatCount(0);
                        alpha.start();
                    }
                });
                //点击第3张图片动画   无到有
                holderThree.icon33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ObjectAnimator alpha = ObjectAnimator.ofFloat(holderThree.icon33, "alpha", 1f, 0f,1f);
                        alpha.setDuration(2000);
                        alpha.setRepeatCount(0);
                        alpha.start();
                    }
                });
                break;
                default:
                    break;
        }

    }

    //条目
    @Override
    public int getItemCount() {
        return mdata.size();
    }
    //多条目

    @Override
    public int getItemViewType(int position) {
        UserBean.DataBean bean=mdata.get(position);

        String thumbnail_pic_s = bean.getThumbnail_pic_s();
        String thumbnail_pic_s02 = bean.getThumbnail_pic_s02();
        String thumbnail_pic_s03 = bean.getThumbnail_pic_s03();

        if(thumbnail_pic_s!=null&&thumbnail_pic_s02==null&&thumbnail_pic_s03==null)
        {
            return TYPE_TEXT;
        }else if(thumbnail_pic_s!=null&&thumbnail_pic_s02!=null&&thumbnail_pic_s03==null)
        {
            return  TYPE_IMAGE;
        }
        else
        {
            return  TYPE_III;
        }

    }

    class  ViewHolderOne extends RecyclerView.ViewHolder {

        private TextView title;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
        }
    }
    class  ViewHolderTwo extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView icon1;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            icon1=itemView.findViewById(R.id.icon1);
        }
    }
    class  ViewHolderThree extends RecyclerView.ViewHolder {


        private TextView title;
        private ImageView icon13,icon23,icon33;
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            icon13=itemView.findViewById(R.id.icon13);
            icon23=itemView.findViewById(R.id.icon23);
            icon33=itemView.findViewById(R.id.icon33);
        }
    }

}
