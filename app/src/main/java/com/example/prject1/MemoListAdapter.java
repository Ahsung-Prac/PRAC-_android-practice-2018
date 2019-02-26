package com.example.prject1;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MemoListAdapter extends BaseAdapter {
    private List memo;
    private Context context;
    public MemoListAdapter(List<MemoC> m,Context context){
        this.context = context;
        this.memo = m;
    }
    @Override
    public int getCount() {
        return this.memo.size();
    }

    @Override
    public Object getItem(int position) {
        return this.memo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView == null) {
            convertView = new LinearLayout(context);
            LinearLayout tempL = new LinearLayout(context);
            tempL.setOrientation(LinearLayout.HORIZONTAL);
            ((LinearLayout) convertView).setOrientation(LinearLayout.VERTICAL);

            TextView tvId = new TextView(context);
            tvId.setPadding(10, 0, 20, 0);
            tvId.setTextColor(Color.BLACK);
            tvId.setTextSize(15);
            TextView tvTitle = new TextView(context);
            tvTitle.setPadding(20, 0, 20, 0);
            tvTitle.setTextColor(Color.BLACK);
            tvTitle.setTextSize(15);
            TextView tvName = new TextView(context);
            tvName.setPadding(20, 0, 20, 0);
            tvName.setTextColor(Color.BLACK);
            tvName.setTextSize(15);
            TextView tvAge = new TextView(context);
            tvAge.setPadding(20, 0, 20, 0);
            tvAge.setTextColor(Color.BLACK);
            tvAge.setTextSize(15);
            TextView tvContents = new TextView(context);
            tvContents.setPadding(20, 0, 20, 0);
            tvContents.setTextColor(Color.BLACK);
            tvContents.setTextSize(15);
            tempL.addView(tvId);
            tempL.addView(tvTitle);
            tempL.addView(tvName);
            tempL.addView(tvAge);
            ((LinearLayout)convertView).addView(tempL);
            ((LinearLayout) convertView).addView(tvContents);
            /*((LinearLayout) convertView).addView(tvId);
            ((LinearLayout) convertView).addView(tvTitle);
            ((LinearLayout) convertView).addView(tvName);
            ((LinearLayout) convertView).addView(tvAge);
            ((LinearLayout) convertView).addView(tvContents);*/
            holder = new Holder();
            holder.viewId = tvId;
            holder.viewTitle = tvTitle;
            holder.viewName = tvName;
            holder.viewAge = tvAge;
            holder.viewContents = tvContents;

            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        MemoC memoC = (MemoC)getItem(position);
        holder.viewId.setText("No."+memoC.get_id()+"");
        holder.viewTitle.setText("제목: "+memoC.getTilte());
        holder.viewName.setText("이름: "+memoC.getName());
        holder.viewAge.setText("나이: "+memoC.getAge());
        holder.viewContents.setText("--메모--\n"+memoC.getContents());


        return convertView;
    }
    public class Holder{
        public TextView viewId;
        public TextView viewTitle;
        public TextView viewName;
        public TextView viewAge;
        public TextView viewContents;
    }
}

