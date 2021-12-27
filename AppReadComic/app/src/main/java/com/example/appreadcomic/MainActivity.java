package com.example.appreadcomic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.appreadcomic.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottomnav);
        preferenceManager= new PreferenceManager(getApplicationContext());
        callFragment("Home");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case  R.id.action_home:

                        callFragment("Home");
                        break;
                    case  R.id.action_pen:

                        preferenceManager.putBoolean(TaiKhoan.KEY_USER_SIGNED_IN,false);
                        preferenceManager.putString(TaiKhoan.KEY_USER_ID,"");

                        Intent intent= new Intent(getApplicationContext(),DangNhapActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        callFragment("Write");
                        break;
                    case R.id.action_search:
                        callFragment("Search");
                        break;
                }
                return true;
            }
        });

    }
    void callFragment(String ten){
        FragmentManager fragmentManager= getFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        if(ten.equals("Home")){
            FragmentHome  fragmentA= new FragmentHome();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentA);
        }
        else if(ten.equals("Write")){
            FragmentWrite fragmentHome= new FragmentWrite();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentHome);
        }else if(ten.equals("Search")){
            FragmentSearch fragmentSearch= new FragmentSearch();
            fragmentTransaction.replace(R.id.fragmentcontenr,fragmentSearch);
        }
        fragmentTransaction.commit();
    }
}