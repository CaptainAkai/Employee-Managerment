package com.example.nodeis_app;

import com.example.nodeis_app.model.EmpType;
import com.example.nodeis_app.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RequestSeverInterface {

    @GET("/empTypeAndroid")
    Call<List<EmpType>> getAllType();

    @GET("/employeeAndroid")
    Call<List<Employee>> getAllEmp();

}
