package com.example.WS_KTbo3_6_Mikhailov_Igor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_home, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		view.findViewById(R.id.hamburger_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(getContext(), MenuActivity.class));
			}
		});

		view.findViewById(R.id.profile_icon).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Fragment profileFragment = new ProfileFragment();
				getFragmentManager().beginTransaction().replace(R.id.main_container_fragment, profileFragment).commit();
			}
		});
	}
}