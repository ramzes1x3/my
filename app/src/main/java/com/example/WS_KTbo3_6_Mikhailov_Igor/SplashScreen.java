package com.example.WS_KTbo3_6_Mikhailov_Igor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
				String email = preferences.getString("email", "");
				String password = preferences.getString("password", "");
				LoginModel loginModel = new LoginModel(email, password);

				if (email.isEmpty() || password.isEmpty()) {
					startActivity(new Intent(getApplicationContext(), OnBoarding.class));
					return;
				}

				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("http://mskko2021.mad.hakta.pro/api/")
						.addConverterFactory(GsonConverterFactory.create())
						.build();

				retrofit.create(APIInterface.class).loginResponse(loginModel).enqueue(new Callback<LoginResponse>() {
					@Override
					public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
						Intent main = new Intent(getApplicationContext(), MainPage.class);
						main.putExtra("infoUser", response.body());
						startActivity(main);
					}

					@Override
					public void onFailure(Call<LoginResponse> call, Throwable t) {
						Notification.Builder notification = new Notification.Builder(SplashScreen.this, "serverError")
								.setContentTitle("Ошибка подключения к серверу")
								.setContentText("Отсутствует подключение к серверу")
								.setSmallIcon(R.drawable.icon);

						NotificationChannel channel = new NotificationChannel("serverError", "serverError", NotificationManager.IMPORTANCE_HIGH);
						NotificationManager notificationManager = getSystemService(NotificationManager.class);
						notificationManager.createNotificationChannel(channel);

						NotificationManagerCompat managerCompat = NotificationManagerCompat.from(SplashScreen.this);
						managerCompat.notify(1, notification.build());

						Toast.makeText(getApplicationContext(), "Ошибка подключения к серверу", Toast.LENGTH_SHORT).show();
						startActivity(new Intent(getApplicationContext(), OnBoarding.class));
					}
				});
			}
		}, 2000);
	}
}