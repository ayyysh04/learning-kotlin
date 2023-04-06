package com.example.myapplication.roomDb.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_data_table")//Annotate this with Entity : to make this room entity
//Dataclass will automatically generate getter and setter functions
data class StudentDataClass(

    @PrimaryKey(autoGenerate = true)//PrimaryKey Annotation for making it primary key with autoGenerate as true to automatically generate the id
    @ColumnInfo(name = "student_id")//ColumnInfo Annotation for column name in table
    var id: Int,

    @ColumnInfo(name = "student_name")
    var name: String,

    @ColumnInfo(name = "student_email")
    var email: String

)