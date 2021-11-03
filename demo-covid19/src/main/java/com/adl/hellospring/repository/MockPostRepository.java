package com.adl.hellospring.repository;

import com.adl.hellospring.model.MockPostModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MockPostRepository {
	
	@POST("posts")
    Call<MockPostModel> inputMock(@Body MockPostModel model); 
}
