package com.example.nodeis_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nodeis_app.R;
import com.example.nodeis_app.model.EmpType;
import com.example.nodeis_app.model.Employee;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private List<Employee> emps;
    private Context context;

    public EmployeeAdapter(Context context, List<Employee> emps){
        this.context = context;
        this.emps = emps;
    }
    @Override
    public int getCount() {
        return emps.size();
    }

    @Override
    public Object getItem(int position) {
        return emps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Employee emp = (Employee) getItem(position);
        viewHolder.tvFullName.setText(emp.getFullName());
        viewHolder.tvEmail.setText(emp.getEmail());
        viewHolder.tvPosition.setText(String.valueOf(emp.getPosition()));

        return convertView;
    }
    private class ViewHolder {
        TextView tvFullName, tvEmail, tvPosition;

        public ViewHolder(View view) {
            tvFullName = (TextView)view.findViewById(R.id.tvFullName);
            tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            tvPosition = (TextView) view.findViewById(R.id.tvPosition);
        }
    }
}
