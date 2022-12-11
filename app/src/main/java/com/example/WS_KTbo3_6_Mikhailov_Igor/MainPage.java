package com.example.WS_KTbo3_6_Mikhailov_Igor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);

		BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

		bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.nav_home: {
						Fragment fragment = new HomeFragment();
						fragment.setArguments(putUserInfo());
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.main_container_fragment, fragment).commit();
						return true;
					}
					case R.id.nav_sound: {
						Fragment fragment = new SoundFragment();
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.main_container_fragment, fragment).commit();
						return true;
					}
					case R.id.nav_profile: {
						Fragment fragment = new ProfileFragment();
						fragment.setArguments(putUserInfo());
						getSupportFragmentManager().beginTransaction()
								.replace(R.id.main_container_fragment, fragment).commit();
						return true;
					}
				}
				return false;
			}
		});

		bottomNavigationView.setSelectedItemId(R.id.nav_home);
	}

	private Bundle putUserInfo() {
		Bundle bundle = new Bundle();
		LoginResponse loginResponse = (LoginResponse) getIntent().getSerializableExtra("infoUser");
		bundle.putSerializable("token", loginResponse);
		return bundle;
	}
}