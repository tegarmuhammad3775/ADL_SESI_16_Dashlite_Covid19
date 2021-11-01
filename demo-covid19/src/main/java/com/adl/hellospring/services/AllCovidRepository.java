package com.adl.hellospring.services;

import java.util.List;

import com.adl.hellospring.model.AllCovidInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AllCovidRepository {
	
	@GET("v1")
	Call<List<AllCovidInfo>>  getAll();
	
	Call<AllCovidInfo> getByCountry(@Path("country") String country);
}
