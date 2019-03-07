package com.ayobuataplikasi.sqlite_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<mahasiswa> arrayList;

    public CustomAdapter(Context context, ArrayList<mahasiswa> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int index) {
        return arrayList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return 0;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lv_item, null, true);
            holder.tv_nim = view.findViewById(R.id.tv_nim);
            holder.tv_nama = view.findViewById(R.id.tv_nama);
            holder.tv_email = view.findViewById(R.id.tv_email);
            holder.tv_noHp = view.findViewById(R.id.tv_noHp);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.tv_nim.setText("NIM : "+arrayList.get(index).getNim());
        holder.tv_nama.setText("Nama : "+arrayList.get(index).getNama());
        holder.tv_email.setText("Email : "+arrayList.get(index).getEmail());
        holder.tv_noHp.setText("No. HP : "+arrayList.get(index).getNoHp());

        return view;
    }

    private class ViewHolder {
        protected TextView tv_nim, tv_nama, tv_email, tv_noHp;
    }
}
