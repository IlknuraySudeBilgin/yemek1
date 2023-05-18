package com.example.yemeksiparisokulprojesi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.yemeksiparisokulprojesi.model.cartItem;
import com.example.yemeksiparisokulprojesi.roomDb.CartDatabase;
import com.example.yemeksiparisokulprojesi.roomDb.cartDAO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SepetActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private CartDatabase cartDb;
    private cartDAO cartDao;
    private CompositeDisposable cb =new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sepet);
        cartDb= Room.databaseBuilder(getApplicationContext(),CartDatabase.class,"Yemekler").build();
        cartDao=cartDb.cartdao();
        recyclerView=findViewById(R.id.recyclerViewSepet);
        cb.add(cartDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(SepetActivity.this::showItems));
    }
    public void showItems(List<cartItem> list){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdapter itemAdapter= new itemAdapter(list);
        recyclerView.setAdapter(itemAdapter);
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cb.clear();
    }
}