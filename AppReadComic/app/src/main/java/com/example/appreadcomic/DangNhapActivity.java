package com.example.appreadcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appreadcomic.databinding.ActivityDangNhapBinding;

public class DangNhapActivity extends AppCompatActivity {

    ActivityDangNhapBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDangNhapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager= new PreferenceManager(getApplicationContext());
        if(preferenceManager.getBoolean(TaiKhoan.KEY_USER_SIGNED_IN)){
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        binding.buttonSignIn.setOnClickListener(v->dangnhap());
        binding.buttonKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferenceManager.putBoolean(TaiKhoan.KEY_USER_SIGNED_IN,true);
                preferenceManager.putString(TaiKhoan.KEY_USER_ID,"0");
                preferenceManager.putString(TaiKhoan.KEY_USER_MAIL,"");
                preferenceManager.putString(TaiKhoan.KEY_USER_NAME,"");
                preferenceManager.putString(TaiKhoan.KEY_USER_PASS,"");
                Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
    void dangnhap(){
        if(binding.inputEmail.getText().toString().equals("tranthevinh") && binding.inputPassword.getText().toString().equals("123456")){
            preferenceManager.putBoolean(TaiKhoan.KEY_USER_SIGNED_IN,true);
            preferenceManager.putString(TaiKhoan.KEY_USER_ID,"1");
            preferenceManager.putString(TaiKhoan.KEY_USER_MAIL,"tranthevinh2231@gmail.com");
            preferenceManager.putString(TaiKhoan.KEY_USER_NAME,"Trần Thế Vinh");
            preferenceManager.putString(TaiKhoan.KEY_USER_PASS,"123456");
            Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }else{
            Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

        }
    }
}