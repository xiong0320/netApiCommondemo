package com.example.bear.netapicommondemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bear.netapicommondemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerAdapter extends BaseAdapter {
    private List<Object> datas;
    LayoutInflater layoutInflater;

    public SpinnerAdapter(List<Object> datas, Context context) {
        this.datas = datas;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas == null?0:datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas== null?null:datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void updateAll(List<Object> objects) {
        if (datas != null) {
            datas.clear();
        } else {
            datas = new ArrayList<>();
        }
        datas= objects;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.spinner_item,parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(getItem(position).toString());
        return view;
    }
    class ViewHolder{
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.check)
        ImageView check;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

    }
}
