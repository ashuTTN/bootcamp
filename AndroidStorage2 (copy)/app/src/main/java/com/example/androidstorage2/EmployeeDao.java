package com.example.androidstorage2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface EmployeeDao {
    @Query("SELECT * FROM employee")
    List<Employee> getAll();
    @Insert
    void insert(Employee employee);
    @Delete
    void delete(Employee employee);
    @Query("DELETE FROM employee WHERE name = :id")
    void deleteByEmployeeID(int id);
}
