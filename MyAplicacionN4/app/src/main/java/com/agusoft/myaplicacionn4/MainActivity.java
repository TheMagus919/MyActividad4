package com.agusoft.myaplicacionn4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.view.View;

import com.agusoft.myaplicacionn4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel lg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                !=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1000);
        }

        lg = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        lg.getResultado().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if(bool){
                    binding.txErrorEmail.setVisibility(View.INVISIBLE);
                }else{
                    binding.txErrorEmail.setVisibility(View.VISIBLE);
                }

            }
        });
        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lg.login(binding.edEmail.getText().toString(),binding.edPassword.getText().toString());
            }
        });
    }
}