package com.example.p_multiviewrecycelrview;
/*下面介紹一種新的方法，全部用一個RecyclerView或者listview顯示，用getItemViewType方法中不同的position，設置不同的ViewType

核心思想：
1.重寫RecyclerView.Adapter的getItemViewType(int position),在此方法中根據不同的position，設置不同的ViewType
2.編寫具體的RecyclerView.ViewHolder子類(不同子類對應不同View或Layout)
3.重寫RecyclerView.Adapter的onCreateViewHolder(ViewGroup parent,int viewType)
 在此方法中根據我們之前設置的ViewType來返回不同的RecyclerView.ViewHolder的子類*/

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//*不同的position家載不同的RecyclerView
//1.直接extends RecyclerView.Adapter,並實作方法
//2.overRidder getItemViewTypeㄨ
//3.準備兩個ViewHolder
//4.依照ViewType決定要接上哪個layoutInflater畫面
//5.依照資料的不同決定綁定哪個ViewHolder


//1.直接extends RecyclerView.Adapter,並實作方法
public class RecyclerAdapter extends RecyclerView.Adapter {
    private List<String> mData;
    private Context mContext;
    private String Tag = "hank";


    public RecyclerAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    //2.overOverride getItemViewType,當取得什麼值給他設定什麼type
    @Override
    public int getItemViewType(int position) {
        Log.v(Tag, "getItemViewType");
        if (mData.get(position).contains("title")) {
            return 0;
        }
        return 1;
    }


    //4.依照ViewType決定要接上哪個layoutInflater畫面
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v(Tag, "onCreateViewHolder" + "/viewType:" + viewType +"/parent:" + parent);
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;

        //如果viewType == 0,用itemB
        if (viewType == 0) {
            view = layoutInflater.inflate(R.layout.item_b, parent, false);
            return new ViewHolderOne(view);
        }
        //如果viewType != 0,用itemA
        view = layoutInflater.inflate(R.layout.item_a, parent, false);
        return new ViteHolderTwo(view);
    }


    //5.依照資料的不同決定綁定哪個ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.v(Tag, "onBindViewHolder" + "/position:" + position +"/holder:" + holder);
        if(mData.get(position).contains("title")){
            //bind ViewHolderOne
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.tvTitle.setText(mData.get(position));
        }else {
            //bind viteHolderTwo
            ViteHolderTwo viteHolderTwo = (ViteHolderTwo) holder;
            viteHolderTwo.tvName.setText(mData.get(position));
            viteHolderTwo.tvAge.setText(mData.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    //3.準備兩個ViewHolder ,這是其中一個
    class ViewHolderOne extends RecyclerView.ViewHolder {
        private TextView tvTitle;

        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

    //3.準備兩個ViewHolder ,這是其中一個
    class ViteHolderTwo extends RecyclerView.ViewHolder {
        private TextView tvName, tvAge;

        public ViteHolderTwo(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
        }
    }

}
