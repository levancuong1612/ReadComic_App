package com.example.appreadcomic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class TruyenAdapter  extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<Truyen> list;

    public TruyenAdapter(Context context, int layout, List<Truyen> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private  class Viewholder{
        TextView txtTenTruyen,txtTacGia,txtTheLoai,txtSoChuong;
        ImageView img;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       Viewholder  viewholder;
        Truyen truyen= list.get(i);
        if(view==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewholder=new Viewholder();
            viewholder.img=(ImageView) view.findViewById(R.id.imageViewItemTruyen);
            viewholder.txtTenTruyen=(TextView)view.findViewById(R.id.textViewItemTenTruyen);
            viewholder.txtTacGia=(TextView)view.findViewById(R.id.textViewItemTenTacGia);
            viewholder.txtSoChuong=(TextView)view.findViewById(R.id.textViewItemSoChuong);
            viewholder.txtTheLoai=(TextView)view.findViewById(R.id.textViewItemTheLoaiTruyen);

            view.setTag(viewholder);
        }else{
            viewholder = (Viewholder) view.getTag();
        }
        byte[] bytes= Base64.decode(truyen.hinhAnh,Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        viewholder.img.setImageBitmap(bitmap);


        viewholder.txtTenTruyen.setText(truyen.tenTruyen);
        viewholder.txtTacGia.setText(truyen.tacGia);
        viewholder.txtSoChuong.setText(truyen.soChuong+"");
        viewholder.txtTheLoai.setText(truyen.theLoai);
        viewholder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(),ThongTinTruyenActivity.class);
                intent.putExtra("truyen",truyen);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
        return view;

    }
}
