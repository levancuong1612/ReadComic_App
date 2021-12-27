package com.example.appreadcomic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class TheLoaiAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<TheLoai> list;

    public TheLoaiAdapter(Context context, int layout, List<TheLoai> list) {
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
        TextView txtTenTheLoai;
        ShapeableImageView img;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        TheLoai theLoai= list.get(i);
        if(view==null){

            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            viewholder=new Viewholder();
            viewholder.img=(ShapeableImageView) view.findViewById(R.id.imageItemTheLoai);
            viewholder.txtTenTheLoai=(TextView)view.findViewById(R.id.textViewItemTenTheloai);


            view.setTag(viewholder);
        }else{
            viewholder = (Viewholder) view.getTag();
        }

        Glide.with(view.getContext()).load(theLoai.hinhanh).into(viewholder.img);

        viewholder.txtTenTheLoai.setText(theLoai.ten);

        viewholder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(),ListTruyenActivity.class);
                intent.putExtra("theloai",theLoai);
                view.getContext().startActivity(intent);
            }
        });
        return view;


    }
}
