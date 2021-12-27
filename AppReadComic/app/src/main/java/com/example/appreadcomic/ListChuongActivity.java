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
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.appreadcomic.DBFireBase.DBChuongTruyen;
import com.example.appreadcomic.DBFireBase.DBTruyen;
import com.example.appreadcomic.databinding.ActivityListChuongBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class ListChuongActivity extends AppCompatActivity {

    ActivityListChuongBinding binding;
    ArrayAdapter arrayAdapter;
    Truyen truyen;
    ArrayList<String>list;
    private PreferenceManager preferenceManager;
    ArrayList<ChuongTruyen> truyenArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding=ActivityListChuongBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager= new PreferenceManager(ListChuongActivity.this);
        Intent intent= getIntent();
        truyen= (Truyen) intent.getSerializableExtra("truyen");
        list= new ArrayList<>();
        truyenArrayList= new ArrayList<>();
        getChuong();
        binding.listItemChuong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               ChuongTruyen chuong= truyenArrayList.get(i);
                Intent intent = new Intent(ListChuongActivity.this,NoiDungTruyenActivity.class);
                intent.putExtra("chuongtruyen",chuong);
                startActivity(intent);
            }
        });




        if(preferenceManager.getString(TaiKhoan.KEY_USER_ID)=="1"){
            binding.buttonFloatAdd.setVisibility(View.VISIBLE);
            binding.buttonFloatAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1= new Intent(ListChuongActivity.this,DangChuongTruyenActivity.class);
                    intent1.putExtra("hinhthuc","noadmin");
                    intent1.putExtra("truyen",truyen);
                    startActivityForResult(intent1,100);
                }
            });
            binding.listItemChuong.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ChuongTruyen chuong= truyenArrayList.get(i);
                    AlertDialog.Builder dialogXoa= new AlertDialog.Builder(ListChuongActivity.this);
                    dialogXoa.setMessage("Bạn có muốn xóa "+ chuong.tenChuong +" không");
                    dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            delete(chuong.id);

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
    public  void delete(String id){
        try {

            FirebaseFirestore dataBase= FirebaseFirestore.getInstance();
            HashMap<String,Object> data= new HashMap<>();


            dataBase.collection("ChuongTruyen").document(id).delete().addOnSuccessListener(documentReference -> {
                Toast.makeText(getApplicationContext(), "Xóa  thành công", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(exception->{
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            });
            recreate();

        }catch (Exception e){
            Toast.makeText(ListChuongActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();

        }
    }
    public  void getChuong(){
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("ChuongTruyen")
                .whereEqualTo("IDTruyen",truyen.id)
                .addSnapshotListener(eventListener);
    }
    private  final EventListener<QuerySnapshot> eventListener=(value, error)->{
        if(error!=null){
            return ;
        }if(value!=null){

            for(DocumentChange documentChange:value.getDocumentChanges()){
                if(documentChange.getType()==DocumentChange.Type.ADDED){
                    ChuongTruyen user= new ChuongTruyen();
                    user.id=documentChange.getDocument().getId();
                    user.tenChuong=documentChange.getDocument().getString(DBChuongTruyen.tenchuong);
                    user.idtruyen=documentChange.getDocument().getString(DBChuongTruyen.IDTruyen);
                    user.noiDung=documentChange.getDocument().getString(DBChuongTruyen.noidung);
                    String tenTruyen=documentChange.getDocument().getString(DBChuongTruyen.tenchuong);
                    truyenArrayList.add(user);
                    list.add(tenTruyen);
                }
            }
            arrayAdapter= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,list);
            binding.listItemChuong.setAdapter(arrayAdapter);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==101){
            recreate();
        }
    }
}