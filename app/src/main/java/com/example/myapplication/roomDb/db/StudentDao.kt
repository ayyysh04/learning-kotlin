package com.example.myapplication.roomDb.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    @Insert //as we are going to insert data in table value -> roomDb cant understand function name but only the annotation part
//    need to mark as suspend as room will not execute in main thread and use a separate coroutine
    suspend fun insertStudent(studentDataClass: StudentDataClass)

    //roomDb return rowId or array of rowId when inserting a list -> no need to specify the return type -> can specify long if we want
    @Update//updating table value
    suspend fun updateStudent(studentDataClass: StudentDataClass)

    @Delete//deleting table value
    suspend fun deleteStudent(studentDataClass: StudentDataClass)

    @Query("SELECT * FROM student_data_table") //displaying using sql query
    fun getAllStudents(): LiveData<List<StudentDataClass>>//getting data as liveData
    //room Db uses its own dispatcher to get the data using a different coroutine thread -> so no need to mark as suspend
}