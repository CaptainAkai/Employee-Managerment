package com.example.nodeis_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nodeis_app.R;
import com.example.nodeis_app.model.EmpType;

import java.util.List;

public class EmpTypeAdapter extends BaseAdapter {
    private List<EmpType> types;
    private Context context;

    public EmpTypeAdapter(Context context, List<EmpType> types){
        this.context = context;
        this.types = types;
    }
    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public Object getItem(int position) {
        return types.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.emp_type_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        EmpType type = (EmpType) getItem(position);
        viewHolder.tvEmpType.setText(type.getTypeName());
        viewHolder.tvDesc.setText(type.getDescription());

        return convertView;
    }
    private class ViewHolder {
        TextView tvEmpType, tvDesc;

        public ViewHolder(View view) {
            tvEmpType = (TextView)view.findViewById(R.id.tvTypeName);
            tvDesc = (TextView) view.findViewById(R.id.tvDesc);
        }
    }
}
