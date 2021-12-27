package com.example.appreadcomic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.appreadcomic.DBFireBase.DBTheLoai;
import com.example.appreadcomic.DBFireBase.DBTruyen;
import com.example.appreadcomic.databinding.ActivityListTruyenBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListTruyenActivity extends AppCompatActivity {

    ActivityListTruyenBinding binding;
    ArrayList<Truyen>truyens ;
    TruyenAdapter truyenAdapter;
    TheLoai theLoai;

    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListTruyenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager= new PreferenceManager(ListTruyenActivity.this);
        Intent intent= getIntent();
        theLoai= (TheLoai) intent.getSerializableExtra("theloai");
        truyens= new ArrayList<>();
        truyens.clear();

        getTruyen();
        if(preferenceManager.getString(TaiKhoan.KEY_USER_ID)=="1"){
            binding.buttonFloatAddTruyen.setVisibility(View.VISIBLE);
            binding.buttonFloatAddTruyen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1= new Intent(ListTruyenActivity.this,DangTruyenActivity.class);
                    intent1.putExtra("theloai",theLoai);
                    startActivityForResult(intent1,100);
                }
            });
            binding.listItemTruyen.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Truyen truyen= truyens.get(i);
                    AlertDialog.Builder dialogXoa= new AlertDialog.Builder(ListTruyenActivity.this);
                    dialogXoa.setMessage("Bạn có muốn xóa "+ truyen.tenTruyen +" không");
                    dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogXoa.show();
                            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
                            dataBase.collection("Truyen").document(truyen.id).delete().addOnSuccessListener(documentReference -> {
                                Toast.makeText(ListTruyenActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            }).addOnFailureListener(exception->{
                                Toast.makeText(ListTruyenActivity.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                        }
                    });
                    dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialogXoa.show();
                    return true;
                }
            });
        }
    }
    public  void getTruyen(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("Truyen")
                .whereEqualTo("IDTheLoai",theLoai.id)
                .addSnapshotListener(eventListener);
    }
    private  final EventListener<QuerySnapshot> eventListener=(value, error)->{
        if(error!=null){
            return ;
        }if(value!=null){
            int count=truyens.size();
            for(DocumentChange documentChange:value.getDocumentChanges()){
                if(documentChange.getType()==DocumentChange.Type.ADDED){
                    Truyen user= new Truyen();
                    user.id=documentChange.getDocument().getId();
                    user.hinhAnh=documentChange.getDocument().getString(DBTruyen.hinhanh);
                    user.tenTruyen=documentChange.getDocument().getString(DBTruyen.tentruyen);
                    user.tacGia=documentChange.getDocument().getString(DBTruyen.tacgia);
                    user.soChuong=documentChange.getDocument().getString(DBTruyen.sochuong);
                    user.idTheLoai=documentChange.getDocument().getString(DBTruyen.IDTheLoai);
                    user.theLoai=documentChange.getDocument().getString(DBTruyen.loaitruyen);
                    user.ketTruyen=documentChange.getDocument().getString(DBTruyen.kettruyen);
                    user.gioiThieu=documentChange.getDocument().getString(DBTruyen.gioithieu);
                    truyens.add(user);
                }
            }
                truyenAdapter= new TruyenAdapter(getApplicationContext(),R.layout.item_layout_truyen,truyens);
                binding.listItemTruyen.setAdapter(truyenAdapter);

        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==101){
            Toast.makeText(ListTruyenActivity.this, "load.....", Toast.LENGTH_SHORT).show();
            recreate();

        }
    }
}