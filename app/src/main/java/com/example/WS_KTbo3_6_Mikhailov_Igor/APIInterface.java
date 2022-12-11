package com.example.WS_KTbo3_6_Mikhailov_Igor;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
	@POST("user/login")
	Call<LoginResponse> loginResponse(@Body LoginModel loginModel);

	@GET("quotes")
	Call<QuotesResponse> getQuotes();
}
