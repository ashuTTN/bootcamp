package com.example.androidstorage2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Employee.class}, version = 2)
public abstract class EmployeeDatabase extends RoomDatabase {
    private static EmployeeDatabase INSTANCE;
    public abstract EmployeeDao employeeDao();
    public static EmployeeDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    EmployeeDatabase.class, EmployeeDatabase.class.getName()).allowMainThreadQueries()
                    //if you want create db only in memory, not in file
                    //Room.inMemoryDatabaseBuilder
                    //(context.getApplicationContext(), DataRoomDbase.class)
                    .build();
        }
        return INSTANCE;
    }

}
