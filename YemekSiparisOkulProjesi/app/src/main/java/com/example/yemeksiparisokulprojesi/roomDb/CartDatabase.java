package com.example.yemeksiparisokulprojesi.roomDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.yemeksiparisokulprojesi.model.cartItem;

@Database(entities = {cartItem.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase{
        public abstract cartDAO cartdao();

}
