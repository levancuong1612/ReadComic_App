package com.example.appreadcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appreadcomic.databinding.ActivityNoiDungTruyenBinding;

public class NoiDungTruyenActivity extends AppCompatActivity {

    ActivityNoiDungTruyenBinding binding;
    ChuongTruyen chuongTruyen;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNoiDungTruyenBinding.inflate(getLayoutInflater());
        Intent intent= getIntent();
        chuongTruyen= (ChuongTruyen) intent.getSerializableExtra("chuongtruyen");
        preferenceManager= new PreferenceManager(NoiDungTruyenActivity.this);
        setContentView(binding.getRoot());
        binding.textViewTenchuong.setText(chuongTruyen.tenChuong);
        binding.textViewnoidungchuong.setText(chuongTruyen.noiDung);
        if(preferenceManager.getString(TaiKhoan.KEY_USER_ID)=="1"){
            binding.buttonEditChuong.setVisibility(View.VISIBLE);
            binding.buttonEditChuong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1= new Intent(NoiDungTruyenActivity.this, DangChuongTruyenActivity.class);
                    intent1.putExtra("hinhthuc","admin");
                    intent1.putExtra("chuongtruyen",chuongTruyen);
                    startActivity(intent1);
                }
            });
        }
    }
}