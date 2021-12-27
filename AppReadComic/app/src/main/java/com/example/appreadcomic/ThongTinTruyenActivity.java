package com.example.appreadcomic;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import com.example.appreadcomic.databinding.ActivityThongTinTruyenBinding;

public class ThongTinTruyenActivity extends AppCompatActivity {

    ActivityThongTinTruyenBinding binding;
    Truyen truyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityThongTinTruyenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent= getIntent();
        truyen= (Truyen) intent.getSerializableExtra("truyen");
        byte[] bytes= Base64.decode(truyen.hinhAnh,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageViewAvtTruyen.setImageBitmap(bitmap);
        bytes= Base64.decode(truyen.hinhAnh,Base64.DEFAULT);
        bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.imageViewBiaTruyen.setImageBitmap(bitmap);
        binding.textviewTenTruyen.setText(truyen.tenTruyen);
        binding.textviewTenTacgia.setText(truyen.tacGia);
        binding.textviewNoiDungTruyen.setText(truyen.gioiThieu);
        binding.textviewSoChuong.setText(truyen.soChuong);
        binding.textviewTheLoai.setText(truyen.theLoai +"-" +truyen.ketTruyen);
        binding.buttonDocTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ThongTinTruyenActivity.this,ListChuongActivity.class);
                intent.putExtra("truyen",truyen);
                startActivity(intent);
            }
        });
    }
}