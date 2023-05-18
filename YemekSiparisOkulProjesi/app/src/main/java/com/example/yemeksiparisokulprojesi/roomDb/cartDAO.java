package com.example.yemeksiparisokulprojesi.roomDb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yemeksiparisokulprojesi.model.cartItem;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface cartDAO {
    @Insert
    Completable insert(cartItem cartItem);
    @Delete
    Completable delete(cartItem cartItem);
    @Query("select * from cartDb")
    Flowable<List<cartItem>> getAll();
    @Query("select * from cartDb where resimId=:resimId")
    Flowable<List<cartItem>> getItem(String resimId);
    @Query("update cartDb set adet=:adet,toplamFiyat=:toplamFiyat where id=:id")
    Completable  update(int id,String toplamFiyat,byte adet);

}
