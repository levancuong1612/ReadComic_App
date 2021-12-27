package com.example.appreadcomic;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;

import com.example.appreadcomic.DBFireBase.DBTheLoai;
import com.example.appreadcomic.DBFireBase.DBTruyen;
import com.example.appreadcomic.databinding.ActivityDangTruyenBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;

public class DangTruyenActivity extends AppCompatActivity {

    ActivityDangTruyenBinding binding;
    DataBase db ;
    private String encodeImage;
    TheLoai theLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDangTruyenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent= getIntent();
        theLoai= (TheLoai) intent.getSerializableExtra("theloai");
        db= new DataBase(this, "comic.sqlite", null,1);
        setListener();
    }
    private  String getEncodeImage(Bitmap bitmap){
        int previewWidth=398;
        int previewHeight=bitmap.getHeight() * previewWidth/bitmap.getWidth();

        Bitmap previewBitmap=Bitmap.createScaledBitmap(bitmap,previewWidth,previewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte[] bytes=byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }

    void setListener(){
        binding.imageViewPostBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                pickImage.launch(intent);

            }
        });

        binding.buttonThemTruyenMoi.setOnClickListener(v->insertCSDL());

    }

    boolean KTDL(){
        if(String.valueOf(binding.inputTieudetruyen.getText()).isEmpty()){
            binding.inputTieudetruyen.setError("Tên truyện không được bỏ trống");
            return  false;
        }
        else if(encodeImage.isEmpty() ){
            Toast.makeText(DangTruyenActivity.this, "Vui lòng load ảnh bìa của truyện", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;


    }

    void insertCSDL(){
       try {

            String tentruyen= binding.inputTieudetruyen.getText().toString();
            String tacgia= binding.inputTacGiatruyen.getText().toString();
            String kettruyen= binding.inputKettruyen.getText().toString();
            String sochuong= binding.inputSochuong.getText().toString();
            String mota= binding.inputMota.getText().toString();
            String anhbia=encodeImage;
            String loaitruyen= binding.inputLoaiTruyen.getText().toString();





           FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
           HashMap<String,Object> data= new HashMap<>();
           data.put(DBTruyen.tentruyen,tentruyen);
           data.put(DBTruyen.hinhanh,encodeImage);
           data.put(DBTruyen.tacgia,tacgia);
           data.put(DBTruyen.sochuong,sochuong);
           data.put(DBTruyen.IDTheLoai,theLoai.id);
           data.put(DBTruyen.loaitruyen,loaitruyen);
           data.put(DBTruyen.kettruyen,kettruyen);
           data.put(DBTruyen.gioithieu,mota);
           dataBase.collection("Truyen").add(data).addOnSuccessListener(documentReference -> {
               Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
           }).addOnFailureListener(exception->{
               Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
           });

          setResult(101);
           finish();

        }catch (Exception e){
            Toast.makeText(DangTruyenActivity.this, "Thêm truyện thất bại", Toast.LENGTH_SHORT).show();

        }

    }


    private  final ActivityResultLauncher<Intent> pickImage= registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode()==RESULT_OK){
                    if(result.getData()!=null){
                        Uri imageUri= result.getData().getData();
                        try{
                            InputStream inputStream= getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap= BitmapFactory.decodeStream(inputStream);

                                binding.imageViewPostBia.setImageBitmap(bitmap);
                                encodeImage= getEncodeImage(bitmap);


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );


}