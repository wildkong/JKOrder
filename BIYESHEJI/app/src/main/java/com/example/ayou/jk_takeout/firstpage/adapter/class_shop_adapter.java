package com.example.ayou.jk_takeout.firstpage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ayou.jk_takeout.R;
import com.example.ayou.jk_takeout.firstpage.config.netConfig;
import com.example.ayou.jk_takeout.firstpage.model.class_shopBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AYOU on 2017/4/26.
 */

public class class_shop_adapter extends RecyclerView.Adapter<class_shop_adapter.ViewHolder> implements View.OnClickListener {


    private List<class_shopBean.DataBean.ListBean> shopList;
    private Context context;
    private LayoutInflater mInflater;
    private int n;
    private RecyclerView mRv;
    private OnItemClickListener listener;


    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public class_shop_adapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.firstpage_shop, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder holder1 = holder;
        if (shopList != null) {
            holder1.tvFirstpageShop.setVisibility(View.INVISIBLE);
            holder1.ivFirstpageShop.setVisibility(View.INVISIBLE);
            holder1.tvShopTitle.setText(shopList.get(position).getSellername());
            holder1.tvShopDistance.setText(shopList.get(position).getGdistance() + "米");
            Picasso.with(context).load(netConfig.IMG_URL + shopList.get(position).getImg()).error(R.drawable.log).placeholder(R.drawable.log).into(holder1.ivShopIcon);
            holder1.tvShopSell.setText("已售" + shopList.get(position).getSell_out() + "份");
            holder1.tvShopLowest.setText(shopList.get(position).getLowest_deliver_fee() + "");
            holder1.tvShopSend.setText(shopList.get(position).getDeliver_fee() + "");

            holder1.itemView.setOnClickListener(this);

        }


    }

    @Override
    public int getItemCount() {
        return shopList == null ? 0 : shopList.size();
    }

    public void setData(List<class_shopBean.DataBean.ListBean> list, boolean isLoad) {
        if (shopList == null) {
            shopList = new ArrayList<>();
        }
        if (!isLoad) {
            shopList.clear();
        }
        if (shopList.size() == list.size() && list.size() < 12) {
            shopList.clear();
        }

        shopList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRv = recyclerView;
    }

    //店铺点击事件
    @Override
    public void onClick(View v) {
        if (mRv != null){
            int childAdapterPosition = mRv.getChildAdapterPosition(v);
            if (listener != null){
                listener.onItemClick(childAdapterPosition);
            }
        }

    }
    //点击事件接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.view)
        View view;
        @BindView(R.id.iv_shop_icon)
        ImageView ivShopIcon;
        @BindView(R.id.tv_shop_title)
        TextView tvShopTitle;
        @BindView(R.id.tv_shop_sell)
        TextView tvShopSell;
        @BindView(R.id.tv_shop_distance)
        TextView tvShopDistance;
        @BindView(R.id.tv_shop_first)
        TextView tvShopFirst;
        @BindView(R.id.ll_shop_shou)
        LinearLayout llShopShou;
        @BindView(R.id.tv_shop_jian)
        TextView tvShopJian;
        @BindView(R.id.ll_shop_jian)
        LinearLayout llShopJian;
        @BindView(R.id.tv_shop_lowest)
        TextView tvShopLowest;
        @BindView(R.id.tv_shop_send)
        TextView tvShopSend;
        @BindView(R.id.iv_firstpage_shop)
        ImageView ivFirstpageShop;
        @BindView(R.id.tv_firstpage_shop)
        TextView tvFirstpageShop;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class_shopBean.DataBean.ListBean getShopData(int position) {
        return shopList.get(position);

    }


}
