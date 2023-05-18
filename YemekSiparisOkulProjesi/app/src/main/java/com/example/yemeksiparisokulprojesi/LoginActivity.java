package com.example.yemeksiparisokulprojesi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText edLoginAd,edLoginSifre;
    Button btnGiris;
    TextView tvHesapYok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edLoginAd=findViewById(R.id.edKulAdi);
        edLoginSifre=findViewById(R.id.edSifre);
        btnGiris=findViewById(R.id.btnGiris);
        tvHesapYok=findViewById(R.id.tvHesapYok);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //giris yapabilmek icin istenen sartları yazalım
                String kullaniciAdi=edLoginAd.getText().toString();
                String sifre=edLoginSifre.getText().toString();
                Database db=new Database(getApplicationContext(),"Yemek Uygulamasi",null,1);

                if(kullaniciAdi.length()==0 || sifre.length()==0)
                {
                    Toast.makeText(getApplicationContext(), "Kullanıcı adı veya şifre boş bırakılamaz!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(db.giris(kullaniciAdi,sifre)==1){
                        Toast.makeText(getApplicationContext(), "Giriş Yapıldı", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this,YiyecekCesitleriActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Geçersiz Kullanıcı Adı veya Şifre!", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });

        tvHesapYok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,KayitActivity.class);
                startActivity(intent);
            }
        });

    }
}