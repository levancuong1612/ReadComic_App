package com.example.appreadcomic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appreadcomic.DBFireBase.DBChuongTruyen;
import com.example.appreadcomic.DBFireBase.DBTruyen;
import com.example.appreadcomic.databinding.ActivityDangChuongTruyenBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class DangChuongTruyenActivity extends AppCompatActivity {

    ActivityDangChuongTruyenBinding binding;
    DataBase db ;
    Truyen truyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDangChuongTruyenBinding.inflate(getLayoutInflater());
        db= new DataBase(this, "comic.sqlite", null,1);
        Intent intent= getIntent();
        truyen= (Truyen) intent.getSerializableExtra("truyen");
        String hinhthuc= intent.getStringExtra("hinhthuc");

        if(hinhthuc.equals("admin")){
            ChuongTruyen chuongTruyen= (ChuongTruyen) intent.getSerializableExtra("chuongtruyen");
            binding.buttonThemChuongMoi.setText("Chỉnh Sửa");
            binding.inputTenChuong.setText(chuongTruyen.tenChuong);
            binding.inputNoiDung.setText(chuongTruyen.noiDung);
            binding.buttonThemChuongMoi.setOnClickListener(v->updateDataToFirestore(chuongTruyen.id));
        }else{
            binding.buttonThemChuongMoi.setOnClickListener(v->insertCSDL());
        }
        setContentView(binding.getRoot());

    }
    private  void  updateDataToFirestore(String id){



        String tenchuong=binding.inputTenChuong.getText().toString();
        String noidung=binding.inputNoiDung.getText().toString();
        try {

            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
            HashMap<String,Object> data= new HashMap<>();
            data.put(DBChuongTruyen.tenchuong,tenchuong);
            data.put(DBChuongTruyen.noidung,noidung);

            dataBase.collection("ChuongTruyen").document(id).update(data).addOnSuccessListener(documentReference -> {
                Toast.makeText(getApplicationContext(), "Chỉnh sửa  thành công", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(exception->{
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            });
            setResult(101);
            finish();

        }catch (Exception e){
            Toast.makeText(DangChuongTruyenActivity.this, "Chỉnh sửa thất bại", Toast.LENGTH_SHORT).show();

        }

    }
    void insertCSDL(){
        String tenchuong=binding.inputTenChuong.getText().toString();
        String noidung=binding.inputNoiDung.getText().toString();
        try {

            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
            HashMap<String,Object> data= new HashMap<>();
            data.put(DBChuongTruyen.tenchuong,tenchuong);
            data.put(DBChuongTruyen.noidung,noidung);
            data.put(DBChuongTruyen.IDTruyen,truyen.id);
            dataBase.collection("ChuongTruyen").add(data).addOnSuccessListener(documentReference -> {
                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(exception->{
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            });
            setResult(101);
            finish();

        }catch (Exception e){
            Toast.makeText(DangChuongTruyenActivity.this, "Thêm chương mới thất bại", Toast.LENGTH_SHORT).show();

        }
    }
}