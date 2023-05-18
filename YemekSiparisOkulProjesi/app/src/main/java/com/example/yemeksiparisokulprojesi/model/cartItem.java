package com.example.yemeksiparisokulprojesi.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cartDb")
public class cartItem {

    @PrimaryKey(autoGenerate = true)
    public int Id;
    @ColumnInfo(name = "resimId")
    public String resimId;
    @ColumnInfo(name = "baslik")
    public String baslik;
    @ColumnInfo(name = "aciklama")
    public String aciklama;
    @ColumnInfo(name = "fiyat")
    public int fiyat;
    @ColumnInfo(name = "toplamFiyat")
    public String toplamFiyat;
    @ColumnInfo(name = "adet")
    public byte adet;

    public cartItem(String resimId, String baslik, String aciklama, int fiyat, String toplamFiyat, byte adet) {
        this.resimId = resimId;
        this.baslik = baslik;
        this.aciklama = aciklama;
        this.fiyat = fiyat;
        this.toplamFiyat = toplamFiyat;
        this.adet = adet;
    }

    public int getId() {
        return Id;
    }

    public String getResimId() {
        return resimId;
    }

    public int getFiyat() {
        return fiyat;
    }

    public byte getAdet() {
        return adet;
    }
}
