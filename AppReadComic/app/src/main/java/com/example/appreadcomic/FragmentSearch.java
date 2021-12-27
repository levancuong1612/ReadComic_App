package com.example.appreadcomic;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.appreadcomic.DBFireBase.DBTruyen;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FragmentSearch extends Fragment {
    ArrayList<Truyen> truyens ;
    TruyenAdapter truyenAdapter;
    EditText inputsearch;
    ListView lsvTruyen;
    DataBase db;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_search,container,false);

        inputsearch=(EditText) view.findViewById(R.id.inputtextSearch);
        lsvTruyen=(ListView)view.findViewById(R.id.list_item_Truyen_search);
        truyens= new ArrayList<>();


        inputsearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                truyens.clear();
               getTruyen();
                return false;
            }

        });
        return view;
    }
    public  void getTruyen(){
        String search=inputsearch.getText().toString();
        FirebaseFirestore database= FirebaseFirestore.getInstance();
        database.collection("Truyen")
                .whereGreaterThanOrEqualTo("tenTruyen",search)
                .addSnapshotListener(eventListener);
    }
    private  final EventListener<QuerySnapshot> eventListener=(value, error)->{
        if(error!=null){
            return ;
        }if(value!=null){

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
            int count=truyens.size();
            if(count==0){

                truyenAdapter= new TruyenAdapter(view.getContext(),R.layout.item_layout_truyen,truyens);
                lsvTruyen.setAdapter(null);
                truyenAdapter.notifyDataSetChanged();
            }else{
                truyenAdapter= new TruyenAdapter(view.getContext(),R.layout.item_layout_truyen,truyens);
                lsvTruyen.setAdapter(truyenAdapter);
                truyenAdapter.notifyDataSetChanged();

            }


        }
    };
}
