package com.adl.hellospring.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.adl.hellospring.model.MockPostModel;
import com.adl.hellospring.repository.MockPostRepository;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class MockPostService {
	
	private MockPostRepository service;
	
	public MockPostService(){
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		service= retrofit.create(MockPostRepository.class);
	}
	
	public MockPostModel inputModel(MockPostModel model){
	    Call<MockPostModel> call = service.inputMock(model);
	    Response<MockPostModel> response = null;
	    
	    try {
			call.execute();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return response.body();
	}

}
