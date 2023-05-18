package com.example.yemeksiparisokulprojesi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yemeksiparisokulprojesi.model.cartItem;
import com.example.yemeksiparisokulprojesi.roomDb.CartDatabase;
import com.example.yemeksiparisokulprojesi.roomDb.cartDAO;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BurgerActivity extends AppCompatActivity {
    int fiyat,id=0;
    String resim,isim,aciklama,toplamfiyat;
    TextView steakBF,mushBF,bbqBF,jalapenoBF,pataseF;
    private CartDatabase cartDb;
    private cartDAO cartDao;
    private CompositeDisposable cb =new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.yemek)));
        steakBF=findViewById(R.id.steakbfiyat);
        mushBF=findViewById(R.id.mushroombfiyat);
        bbqBF=findViewById(R.id.bbqbfiyat);
        jalapenoBF=findViewById(R.id.jalapenobfiyat);
        pataseF=findViewById(R.id.patatesfiyat);
        cartDb= Room.databaseBuilder(getApplicationContext(),CartDatabase.class,"Yemekler").build();
        cartDao=cartDb.cartdao();

        steakBF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resim=String.valueOf(R.drawable.img_13);
                isim="Steak Burger";
                aciklama="Köfte,Füme Et, Domates,Soğan,Turşu,Marul,Özel Burger Sosu,Cheddar Peyniri";
                fiyat=120;
                toplamfiyat="120";
                cb.add(cartDao.getItem(resim).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(BurgerActivity.this::getData));
            }
        });
        mushBF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resim=String.valueOf(R.drawable.img_14);
                isim="Mushroom Burger";
                aciklama=getResources().getString(R.string.mushroomBaciklama);
                fiyat=115;
                toplamfiyat="115";
                cb.add(cartDao.getItem(resim).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(BurgerActivity.this::getData));
            }
        });


    }
    public void klasikB(View view){
        resim=String.valueOf(R.drawable.img_12);
        isim="Klasik Burger";
        aciklama="Köfte, Domates,Soğan,Cheddar Peyniri,Marul,Özel Burger Sosu";
        fiyat=90;
        toplamfiyat="90";
        cb.add(cartDao.getItem(resim).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(BurgerActivity.this::getData));

    }
    public void getData(List<cartItem> cartItems){
        if(cartItems.isEmpty()){
            cartItem cartItem =new cartItem(resim,isim,aciklama,fiyat,toplamfiyat,(byte) 1);
            cb.add(cartDao.insert(cartItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
            Toast.makeText(BurgerActivity.this, "Sepete Eklendi", Toast.LENGTH_SHORT).show();
        }
        else{
            cb.add(cartDao.update(cartItems.get(0).getId(),String.valueOf((cartItems.get(0).getAdet()+1)*fiyat), (byte) (cartItems.get(0).getAdet()+1)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
            }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cb.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sepet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Sepet:
                startActivity(new Intent(BurgerActivity.this,SepetActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}