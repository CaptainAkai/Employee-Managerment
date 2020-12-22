package com.example.nodeis_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nodeis_app.adapter.EmpTypeAdapter;
import com.example.nodeis_app.adapter.EmployeeAdapter;
import com.example.nodeis_app.model.EmpType;
import com.example.nodeis_app.model.Employee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "http://192.168.137.1:8888";
    public static List<Employee> emps = new ArrayList<>();
    private EmployeeAdapter employeeAdapter;
    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        employeeAdapter = new EmployeeAdapter(this, emps);
        myListView = findViewById(R.id.lvEmployee);
        myListView.setAdapter(employeeAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestSeverInterface requestSeverInterface = retrofit.create(RequestSeverInterface.class);

        requestSeverInterface.getAllEmp().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                Log.d("DATA", response.body().toString());

                if(response.isSuccessful()){
                    List<Employee> employees = response.body();
//                    Toast.makeText(MainActivity.this, "check: " + employees.toString(), Toast.LENGTH_SHORT).show();
                    myNotifyDataSetChange(employees);
//                    Toast.makeText(MainActivity.this, emps.toString() + "", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("DATA", t.toString());
            }
        });

    }

    private void myNotifyDataSetChange(List<Employee> employees) {
        emps.clear();
        emps.addAll(employees);
        employeeAdapter.notifyDataSetChanged();
    }

}
