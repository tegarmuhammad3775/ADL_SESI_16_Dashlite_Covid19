package com.adl.hellospring.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adl.hellospring.model.AllCovidInfo;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class CovidApiService {
	
	private AllCovidRepository service;
	public String BASE_URL="https://covid-19.dataflowkit.com/";
	
	public CovidApiService() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		service= retrofit.create(AllCovidRepository.class);
				
	}
	
	public List<AllCovidInfo> getAllCovidInfo(){
		Call<List<AllCovidInfo>> callInfo = service.getAll();
		Response<List<AllCovidInfo>> response = null;
		try {
			response = callInfo.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response.body();
		
	}
	
	public AllCovidInfo getCovidInfo(String country){
		Call<AllCovidInfo> call = service.getByCountry(country);
	    Response<AllCovidInfo> response = null;
		try {
			response = call.execute();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return response.body();
	}
	
}
