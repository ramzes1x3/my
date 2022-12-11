package com.example.WS_KTbo3_6_Mikhailov_Igor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
	EditText signInEmail;
	EditText signInPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		signInEmail = findViewById(R.id.sign_in_email);
		signInPassword = findViewById(R.id.sign_in_password);

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
		String prefEmail = preferences.getString("email", "");

		if (!prefEmail.isEmpty()) {
			signInEmail.setText(prefEmail);
		}

		findViewById(R.id.login_register).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getApplicationContext(), Register.class));
			}
		});

		findViewById(R.id.login_sign_in_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String email = signInEmail.getText().toString();
				String password = signInPassword.getText().toString();
				LoginModel loginModel = new LoginModel(email, password);

				if (email.isEmpty()) {
					Toast.makeText(Login.this, "Введите email", Toast.LENGTH_SHORT).show();
				}

				if (!email.contains("@")) {
					Toast.makeText(Login.this, "Неверный формат email", Toast.LENGTH_SHORT).show();
					return;
				}

				if (password.isEmpty()) {
					Toast.makeText(Login.this, "Введите пароль", Toast.LENGTH_SHORT).show();
					return;
				}

				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("http://mskko2021.mad.hakta.pro/api/")
						.addConverterFactory(GsonConverterFactory.create())
						.build();

				retrofit.create(APIInterface.class).loginResponse(loginModel).enqueue(
						new Callback<LoginResponse>() {
							@Override
							public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
								if (response.isSuccessful()) {
									Intent main = new Intent(getApplicationContext(), MainPage.class);
									main.putExtra("userInfo", response.body());

									SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Login.this);
									SharedPreferences.Editor preferencesEditor = preferences.edit();
									preferencesEditor.putString("email", email);
									preferencesEditor.putString("password", password);
									preferencesEditor.commit();

									startActivity(main);
								} else {
									Toast.makeText(Login.this, "Введен неверный имейл или пароль", Toast.LENGTH_SHORT).show();
								}
							}

							@Override
							public void onFailure(Call<LoginResponse> call, Throwable t) {
								AlertDialog serverErrorDialog = new AlertDialog.Builder(Login.this)
										.setTitle("Серверная ошибка")
										.setMessage(t.getMessage())
										.create();

								serverErrorDialog.show();
							}
						});
			}
		});
	}
}